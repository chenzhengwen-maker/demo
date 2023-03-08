package com.example.demo.designpattern.abstractfactory;

public class MysqConnection implements IConnection{
    @Override
    public void connect() {
        System.out.println("mysql connect");
    }
}
