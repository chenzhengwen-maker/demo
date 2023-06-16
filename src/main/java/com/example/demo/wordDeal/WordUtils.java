package com.example.demo.wordDeal;

import com.deepoove.poi.xwpf.NiceXWPFDocument;
import fr.opensagres.xdocreport.core.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @author czw
 * @description: WordUtils 手写 ---测试版
 * @time 2022/11/09 14:58
 **/
public class WordUtils {

    /**
     * @param inputFileUrl 模板读取路径
     * @return org.apache.poi.xwpf.usermodel.XWPFDocument
     * @description: 读取模板文件
     * @author czw
     * @time 2022/11/09 15:02
     **/
    public static XWPFDocument inputDocument(String inputFileUrl) throws IOException {
        XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputFileUrl));
        return document;
    }

    /**
     * @param document      文档文件
     * @param outputFileUrl 文件输出路径
     * @return java.lang.String
     * @description: 导出至目标文件
     * @author czw
     * @time 2022/11/09 15:06
     **/
    public static String outputDocument(XWPFDocument document, String outputFileUrl) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(outputFileUrl);
            document.write(os);
            os.close();
            return "文件导出完成";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件导出失败";
        }
    }

	/**
     * @param document 文档文件
     * @return java.io.ByteArrayOutputStream
     * @description: 导出文件为字节数组
     * @author czw
     * @time 2022/11/16 10:55
     * 直接操作字节数组
     **/
    public static ByteArrayOutputStream outputDocument(XWPFDocument document) {
        ByteArrayOutputStream out = null;
        try {

            // 直接操作字节数组会将模板内容更改, 请谨慎使用
            out = new ByteArrayOutputStream();
            document.write(out);
            document.close();
            return out;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * @param document 文档文件
     * @param dataMap  数据Map集合
     * @return java.lang.String
     * @description:
     * @author czw
     * @time 2022/11/09 15:34
     **/
    public static String parseDocument(XWPFDocument document, Map<String, Object> dataMap) {

        // 获取文档中所有对象 (段落 + 表格)
        List<IBodyElement> bodys = document.getBodyElements();
        // 模板文件(段落 + 表格) 总个数
        int templateSize = bodys.size();
        System.out.println("文档中(段落 + 表格)共有" + templateSize + "个");
        // 最终结果
        String result = "";
        // 遍历文档内容
        int curT = 0; // 当前操作表格对象的索引
        int curP = 0; // 当前操作段落对象的索引
        for (int i = 0; i < templateSize; i++) {
            // 单个 段落 或 表格
            IBodyElement body = bodys.get(i);
            // 表格类型
            try {
                if (BodyElementType.TABLE.equals(body.getElementType())) {
                    // 处理表格内容
                    table(body, curT, dataMap);
                    System.out.println("当前第" + curT + 1 + "个表格处理完成");
                } else if (BodyElementType.PARAGRAPH.equals(body.getElementType())) {
                    paragraph(body, curP, dataMap);
                    System.out.println("当前第" + curP + 1 + "个段落处理完成");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage();
                System.out.println("当前第" + curT + 1 + "个表格处理失败,错误为: " + result);
            }
        }
        result = "文档内容处理完成";
        return result;
    }

    /**
     * @param body    单个表格个体
     * @param index   表格所处文档索引
     * @param dataMap 所有数据内容
     * @return java.lang.String
     * @description: 处理表格内容
     * @author czw
     * @time 2022/11/09 16:18
     **/
    public static String table(IBodyElement body, int index, Map<String, Object> dataMap) {
        // 通过tables集合获取对应索引位置table
        List<XWPFTable> tables = body.getBody().getTables();
        XWPFTable table = tables.get(index);

        if (table != null) {
            // 获取所有行
            List<XWPFTableRow> rows = table.getRows();
            // 表格标题不容更改,只需替换相关内容
            for (XWPFTableRow row : rows) {
                // 获取单元格
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    // 判断单元格是否有需要替换数据
                    // 有,返回相关段落位置   没有,返回0
                    // 记录段落位置,防止对所需更换数据进行样式、格式操作时,对模板中设置好的段落文字进行改动
                    List<Integer> integers = checkText(cell);
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (int i = 0; i < paragraphs.size(); i++) {
                        if (integers.contains(i)) { // 存在的段落更换文本
                            List<XWPFRun> runs = paragraphs.get(i).getRuns();
                            for (XWPFRun run : runs) {
                                run.setFontFamily("宋体"); // 设置字体
                                run.setFontSize(9); // 小五号字体 如需其他字体自行查对应大小
                                Object ob = changeValue(run.toString(), dataMap);// 替换内容
                                if (ob instanceof String) {
                                    run.setText((String) ob, 0);
                                } else if (ob instanceof Map) {
                                    run.setText("", 0); //清空段落文字内容,方便填充图片`
                                    Map o = (Map) ob;
                                    // 获取元素
                                    int width = (int) o.get("width");
                                    int height = (int) o.get("height");
                                    int picType = getPictureType((String) o.get("type"));
                                    byte[] bs = (byte[]) o.get("con");
                                    try {
                                        // 写入图片
                                        run.addPicture(new ByteArrayInputStream(bs), picType, "123456.jpg", width, height);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return "元素替换失败：" + e.getMessage();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return "元素替换完成";
    }

    /**
     * @param body    单个段落个体
     * @param index   段落所处文档索引
     * @param dataMap 所有数据内容
     * @return java.lang.String
     * @description: 处理段落内容
     * @author czw
     * @time 2022/11/09 16:19
     **/
    public static String paragraph(IBodyElement body, int index, Map<String, Object> dataMap) {
        // 目前我个人需求未接触段落,后期补充
        List<XWPFParagraph> paragraphs = body.getBody().getParagraphs();
        for(XWPFParagraph paragraph:paragraphs){
            String text = paragraph.getText();
            if(checkText(text)){
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setFontFamily("宋体"); // 设置字体
                    run.setFontSize(9); // 小五号字体 如需其他字体自行查对应大小
                    Object ob = changeValue(run.toString(), dataMap);// 替换内容
                    if (ob instanceof String) {
                        run.setText((String) ob, 0);
                    } else if (ob instanceof Map) {
                        //run.setText("", 0); //清空段落文字内容,方便填充图片`
                        Map o = (Map) ob;
                        // 获取元素
                        int width = (int) o.get("width");
                        int height = (int) o.get("height");
                        int picType = getPictureType((String) o.get("type"));
                        byte[] bs = (byte[]) o.get("con");
                        try {
                            run.setText(o.get("name").toString(), 0); //清空段落文字内容,方便填充图片`
                            // 写入图片
                            run.addPicture(new ByteArrayInputStream(bs), picType, "Xiong.jpg",width, height);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return "元素替换失败：" + e.getMessage();
                        }
                    }
                }
            }
        }
        return "元素替换完成";
    }

    /**
     * @param type 图片类型
     * @return int
     * @description: 获取各类型图片对应字典值
     * @author czw
     * @time 2022/11/10 11:17
     **/
    private static int getPictureType(String type) {
        int res = XWPFDocument.PICTURE_TYPE_PICT;
        if (type != null) {
            if (type.equalsIgnoreCase("png")) {
                res = XWPFDocument.PICTURE_TYPE_PNG;
            } else if (type.equalsIgnoreCase("dib")) {
                res = XWPFDocument.PICTURE_TYPE_DIB;
            } else if (type.equalsIgnoreCase("emf")) {
                res = XWPFDocument.PICTURE_TYPE_EMF;
            } else if (type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("jpeg")) {
                res = XWPFDocument.PICTURE_TYPE_JPEG;
            } else if (type.equalsIgnoreCase("wmf")) {
                res = XWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    /**
     * @param value      模板需要替换的区域
     * @param dataSource 传入信息集合
     * @return java.lang.String 模板需要替换区域信息集合对应值
     * @description: 更换模板中所需替换信息
     * @author czw
     * @time 2022/11/09 16:47
     **/
    private static Object changeValue(String value, Map<String, Object> dataSource) {
        Set<Map.Entry<String, Object>> entries = dataSource.entrySet();
        Object val = "";
        for (Map.Entry<String, Object> entry : entries) {
            // 匹配格式 ${key}
            String key = "${" + entry.getKey() + "}";
            // 判断是否存在可替换内容
            if (value.indexOf(key) != -1) {
                if(value.indexOf("$") > 0 && !value.contains("image")){
                    val = value.substring(0,value.indexOf("$")) + entry.getValue();
                }else{
                    val = entry.getValue();
                    if(val instanceof Map){
                        ((Map<String, Object>) val).put("name",value.substring(0,value.indexOf("$")));
                    }
                }
            }
            // 判断该区域是否有需替换内容 (大概率无用,checkText()已综合所需替换段落)
//            if (value.indexOf("$") == -1) {
//                value = "";
//            }
        }
        return StringUtils.isBlank(val.toString()) ? value : val;
    }

    /**
     * @param cell 单元格
     * @return void
     * @description: 判断是否有数据需要进行更换
     * @author czw
     * @time 2022/11/09 16:31
     **/
    private static List<Integer> checkText(XWPFTableCell cell) {
        List<Integer> integers = new ArrayList<>();
        List<XWPFParagraph> paragraphs = cell.getParagraphs();
        int par = 0; // 记录段落位置
        for (XWPFParagraph paragraph : paragraphs) {
            // 获取段落文本
            String text = paragraph.getText();
            // 段落如有 $ 标明 , 则需更换数值
            if (text.indexOf("$") != -1) {
                // 记录段落位置
                integers.add(par);
            }
            par++;
        }
        return integers;
    }

    /**
     * @param imageUrl 图片文件路径地址
     * @return byte[]
     * @description: 将图片转换为字节数组
     * @author czw
     * @time 2022/11/10 10:52
     **/
    public static byte[] getImageByteArray(String imageUrl) {
        try {
            File file = new File(imageUrl);
            InputStream in = new FileInputStream(file);
            byte[] bytes = IOUtils.toByteArray(in);
            // 可以打印输出查看是否有误
//            System.out.println(Arrays.toString(bytes));
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 检查文本中是否包含指定的字符(此处为“$”)，并返回值 */
    public static boolean checkText(String text) {
        boolean check = false;
        if (text.contains("$")) {
            check = true;
        }
        return check;
    }

    /**
     * @param outputAlldocumentUrl  最终合并文档路径
     * @param outputFileUrl 子文件路径
     * @return java.lang.String
     * @description: 合并子目录下的所有word到最终合并文档路劲下
     * @author czw
     * @time 2022/11/09 15:06
     **/
    public static String outputAllDocument(String outputAlldocumentUrl, String outputFileUrl) throws Exception {
        //Blank Document
        XWPFDocument document= new XWPFDocument();
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(new File(outputAlldocumentUrl+"outPutWord.docx"));
            document.write(os);
            os.close();
            File f = new File(outputFileUrl);
            if (!f.exists()) {
                System.out.println(outputFileUrl + " not exists");//不存在就输出
                return "不存在合并目录";
            }
            File fa[] = f.listFiles();//用数组接收  F:笔记总结C#, F:笔记总结if语句.txt
            for (int i = 0; i < fa.length; i++) {//循环遍历
                File fs = fa[i];//获取数组中的第i个
                if (fs.isDirectory()) {
                    System.out.println(fs.getName() + " [目录]");//如果是目录就输出
                } else {
                    NiceXWPFDocument main = new NiceXWPFDocument(new FileInputStream(outputAlldocumentUrl+"outPutWord.docx"));
                    NiceXWPFDocument sub = new NiceXWPFDocument(new FileInputStream(outputFileUrl+fs.getName()));
                    //添加段落
                    XWPFParagraph p = main.createParagraph();
                    p.setPageBreak(true);
                    // 合并两个文档
                    NiceXWPFDocument newDoc = main.merge(sub);
                    // 生成新文档
                    FileOutputStream out = new FileOutputStream(outputAlldocumentUrl+"outPutWord.docx");
                    newDoc.write(out);
                    newDoc.close();
                    out.close();
                    System.out.println(fs.getName());//否则直接输出
                }
            }
            return "文件导出完成";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件导出失败";
        }
    }

}
