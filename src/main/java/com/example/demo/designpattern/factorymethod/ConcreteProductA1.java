package com.example.demo.designpattern.factorymethod;

public class ConcreteProductA1 extends Application{
    @Override
    Product createProduct() {
        return new ProductA1();
    }
}
