package com.example.demo.designpattern.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元模式
 */
public class FlyWeightTest {
    public static void main(String[] args) {
        TreeType treeType = TreeFactory.getTreeType("xx","xxx");
        TreeNode treeNode = new TreeNode(3,5,treeType);

    }
}
class TreeNode{
    private int x;
    private int y;
    private TreeType treeType;

    public TreeNode(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }
}
class TreeType{
    private final String name;
    private final String data;

    public TreeType(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
class TreeFactory{
    static Map<String,TreeType> treeTypeMap = new ConcurrentHashMap<>();
    public static TreeType getTreeType(String name,String data){
        if(treeTypeMap.get(name)!=null){
            return treeTypeMap.get(name);
        }else{
            TreeType treeType = new TreeType(name, data);
            treeTypeMap.put(name,treeType);
            return treeType;
        }
    }
}
