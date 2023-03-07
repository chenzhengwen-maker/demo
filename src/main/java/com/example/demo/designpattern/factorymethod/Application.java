package com.example.demo.designpattern.factorymethod;

abstract class Application {
    abstract Product createProduct();
    public Product getProduct(){
        return createProduct();
    }
}
