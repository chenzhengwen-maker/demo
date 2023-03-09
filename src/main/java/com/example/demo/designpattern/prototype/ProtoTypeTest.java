package com.example.demo.designpattern.prototype;

/**
 * 原型模式
 */

import java.io.Serializable;

public class ProtoTypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        BaseInfo baseInfo = new BaseInfo("tuling");
        Product product = new Product("1","2","3","4",baseInfo);
        Product clone = product.clone();
        System.out.println( "original: " + product );
        System.out.println( "clone:  " + clone );
        product.getBaseInfo().setCompany( "xxxx" );
        System.out.println( "original: " + product );
        System.out.println( "clone:  " + clone );

    }
}
class Product implements Cloneable,Serializable{
    private String part1;
    private String part2;
    private String part3;
    private String part4;
    private BaseInfo baseInfo;

    public Product(String part1, String part2, String part3, String part4, BaseInfo baseInfo) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
        this.baseInfo = baseInfo;
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String getPart3() {
        return part3;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }

    public String getPart4() {
        return part4;
    }

    public void setPart4(String part4) {
        this.part4 = part4;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    @Override
    protected Product clone() throws CloneNotSupportedException {
        Product product = (Product) super.clone();
        BaseInfo baseInfoClone = this.baseInfo.clone();
        product.setBaseInfo(baseInfoClone);
        return product;
    }

    @Override
    public String toString() {
        return super.hashCode() +"Product{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                ", part3='" + part3 + '\'' +
                ", part4='" + part4 + '\'' +
                ", baseInfo=" + baseInfo +
                '}';
    }
}
class BaseInfo implements Cloneable, Serializable {
    private String company;
    public BaseInfo(String company){
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    protected BaseInfo clone() throws CloneNotSupportedException {
        return (BaseInfo) super.clone();
    }

    @Override
    public String toString() {
        return hashCode() +"BaseInfo{" +
                "company='" + company + '\'' +
                '}';
    }
}
