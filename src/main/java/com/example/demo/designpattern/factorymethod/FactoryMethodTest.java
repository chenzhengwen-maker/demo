package com.example.demo.designpattern.factorymethod;

/**
 * 工厂方法
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        Application application = new ConcreteProductA();
        Product product = application.getProduct();
        product.method();
        Application application1 = new ConcreteProductA1();
        Product product1 = application1.getProduct();
        product1.method();
    }
}
