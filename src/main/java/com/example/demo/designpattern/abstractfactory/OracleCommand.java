package com.example.demo.designpattern.abstractfactory;

public class OracleCommand implements ICommand{
    @Override
    public void command() {
        System.out.println("oracle command");
    }
}
