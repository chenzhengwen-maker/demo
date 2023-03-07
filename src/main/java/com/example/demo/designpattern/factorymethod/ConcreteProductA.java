package com.example.demo.designpattern.factorymethod;

public class ConcreteProductA extends Application{
    @Override
    Product createProduct() {
        return new ProductA();
    }
}
