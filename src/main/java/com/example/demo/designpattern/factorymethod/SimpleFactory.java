package com.example.demo.designpattern.factorymethod;

/**
 * 简单工厂
 */
public class SimpleFactory {
    public static Product getProduct(String type){
        if(type.equals("0")){
            return new ProductA();
        }else{
            return new ProductA1();
        }
    }
    public static void main(String[] args) {
        Product product = getProduct("0");
        product.method();
        Product product1 = getProduct("");
        product1.method();

    }
}
