package com.example.demo.designpattern.singleton.v2;

public class HungrySingletonTest {
    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getIntance();
        HungrySingleton instance1 = HungrySingleton.getIntance();
        System.out.println(instance == instance1);

    }
}
class HungrySingleton{
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){

    }
    public static HungrySingleton getIntance(){
        return instance;
    }
}
