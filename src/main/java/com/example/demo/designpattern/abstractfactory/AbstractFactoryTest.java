package com.example.demo.designpattern.abstractfactory;

/**
 * 抽象工厂模式
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        IDataBaseUtils dataBaseUtil = new MysqlDataBaseUtil();
        dataBaseUtil.getConnection().connect();
        dataBaseUtil.getCommand().command();

    }
}
