package com.example.demo.designpattern.abstractfactory;

public interface IDataBaseUtils {
    IConnection getConnection();
    ICommand getCommand();
}
