package com.example.demo.designpattern.abstractfactory;

public class MySqlCommand implements ICommand{
    @Override
    public void command() {
        System.out.println("mysql command");
    }
}
