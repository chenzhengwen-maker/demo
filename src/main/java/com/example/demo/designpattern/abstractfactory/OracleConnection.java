package com.example.demo.designpattern.abstractfactory;

public class OracleConnection implements IConnection{
    @Override
    public void connect() {
        System.out.println("oracle connect");
    }
}
