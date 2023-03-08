package com.example.demo.designpattern.abstractfactory;

public class OracleDataBaseUtil implements IDataBaseUtils{
    @Override
    public IConnection getConnection() {
        return new OracleConnection();
    }

    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}
