package com.example.demo.designpattern.abstractfactory;

public class MysqlDataBaseUtil implements IDataBaseUtils{
    @Override
    public IConnection getConnection() {
        return new MysqConnection();
    }

    @Override
    public ICommand getCommand() {
        return new MySqlCommand();
    }
}
