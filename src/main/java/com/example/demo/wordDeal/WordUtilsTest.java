package com.example.demo.wordDeal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtilsTest {
    public static void main(String[] args) throws Exception {
        // 本地文件 自行更换
        // 模板文件地址
        String inputUrl = "D:\\wordTemplate\\wordtemplate.docx";
        // 新生产的模板文件
        String outputUrl = "D:\\wordTemplate\\subword\\";
        // 新生产的模板文件
        String allOutputUrl = "D:\\wordTemplate\\outputword\\";
        // 图片地址
        String imageUrl = "D:\\wordTemplate\\czw.jpg";
        //读取excel文件
        String excelFilePath = "D:\\wordTemplate\\data.xlsx";
        List<Map<String,Object>> dataList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for (int rowNumber = 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
            Row row = sheet.getRow(rowNumber);

            // 准备数据
            Map<String, Object> dataMap = new HashMap<String, Object>();
            for (int columnNumber = 0; columnNumber < row.getLastCellNum(); columnNumber++) {
                Cell cell = row.getCell(columnNumber);
                String columnName = sheet.getRow(0).getCell(columnNumber).getStringCellValue();
                if (columnName.equals("card")) {
                    dataMap.put(columnName, String.valueOf(getCellValue(cell)));
                    String imageFromUrl = "D:\\wordTemplate\\image\\"+Double.valueOf(getCellValue(cell).toString()).longValue()+".jpg";
                    byte[] bs = WordUtils.getImageByteArray(imageFromUrl);
                    Map<String, Object> imageMap = new HashMap<>();
                    imageMap.put("width", 2000000);
                    imageMap.put("height", 2000000); // 宽高设置尽量大些,底层沿用EMU计算
                    //EMU（英制公制单位）。1 EMU = 1/914400英寸= 1/36000 mm
                    String[] imageSplit = imageUrl.split("\\."); // 请根据各自的路径进行分割,反斜杠分割要写"\\\\"
                    // 可直接更换为文件名称后缀,后续会自行判断
                    imageMap.put("type", imageSplit[imageSplit.length - 1]);
                    imageMap.put("con", bs);
                    dataMap.put("image", imageMap);
                } else {
                    dataMap.put(columnName, String.valueOf(getCellValue(cell)));
                }
            }
            dataList.add(dataMap);
        }

        //byte[] bs = WordUtils.getImageByteArray(imageUrl);
        // model() 对应填充元素,自行填写  例如 dataMap.put("name","张三");
        // model() 这个方法自己写！！！要不然就在测试类直接往紧塞！！！ new 对象 .set 然后 .put 往紧塞！
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("name","xx");
//        dataMap.put("age","22");
//        dataMap.put("card","2223455");
//        // imageMap 填充单个图片元素集合,需与数据模型元素分开填充
//        // 多张图片请填充多个集合
//        Map<String, Object> imageMap = new HashMap<>();
//        imageMap.put("width", 700000);
//        imageMap.put("height", 700000); // 宽高设置尽量大些,底层沿用EMU计算
//        //EMU（英制公制单位）。1 EMU = 1/914400英寸= 1/36000 mm
//        String[] imageSplit = imageUrl.split("\\."); // 请根据各自的路径进行分割,反斜杠分割要写"\\\\"
//        // 可直接更换为文件名称后缀,后续会自行判断
//        imageMap.put("type", imageSplit[imageSplit.length - 1]);
//        imageMap.put("con", bs);
//        dataMap.put("image", imageMap);
//        dataList.add(dataMap);
//
//        Map<String, Object> dataMap2 = new HashMap<>();
//        dataMap2.put("name","ztm");
//        dataMap2.put("age","32");
//        dataMap2.put("card","12345");
//        // imageMap 填充单个图片元素集合,需与数据模型元素分开填充
//        // 多张图片请填充多个集合
//        Map<String, Object> imageMap2 = new HashMap<>();
//        imageMap2.put("width", 700000);
//        imageMap2.put("height", 700000); // 宽高设置尽量大些,底层沿用EMU计算
//        //EMU（英制公制单位）。1 EMU = 1/914400英寸= 1/36000 mm
//        String[] imageSplit2 = imageUrl.split("\\."); // 请根据各自的路径进行分割,反斜杠分割要写"\\\\"
//        // 可直接更换为文件名称后缀,后续会自行判断
//        imageMap2.put("type", imageSplit2[imageSplit2.length - 1]);
//        imageMap2.put("con", bs);
//        dataMap2.put("image", imageMap2);
//        dataList.add(dataMap2);
        for(Map<String,Object> datatemplateMap : dataList) {
            XWPFDocument document = WordUtils.inputDocument(inputUrl);
            // 处理文档数据
            String s = WordUtils.parseDocument(document, datatemplateMap);
            System.out.println(s);
            WordUtils.outputDocument(document, outputUrl+datatemplateMap.get("card")+".docx");

        }
        //合并导出
        WordUtils.outputAllDocument(allOutputUrl,outputUrl);
    }

    // 获取单元格的值
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return Double.valueOf(String.valueOf(cell.getNumericCellValue())).longValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }




}
