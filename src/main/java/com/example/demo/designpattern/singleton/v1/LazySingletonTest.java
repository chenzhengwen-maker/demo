package com.example.demo.designpattern.singleton.v1;

public class LazySingletonTest {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(LazySingleton.getLazySingleton());
        }).start();
        new Thread(()->{
            System.out.println(LazySingleton.getLazySingleton());
        }).start();

    }
}

class LazySingleton{

    private volatile static LazySingleton instance;

    private LazySingleton(){

    }
    public static LazySingleton getLazySingleton(){
        if(instance == null){
            synchronized (LazySingleton.class) {
                if(instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
