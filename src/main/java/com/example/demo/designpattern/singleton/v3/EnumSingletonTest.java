package com.example.demo.designpattern.singleton.v3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EnumSingletonTest {
    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.instance;
        EnumSingleton instance2 = EnumSingleton.instance;
        System.out.println(instance == instance2);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EnumSingleton"))) {
            oos.writeObject(instance);
        }catch (Exception e){
            e.printStackTrace();
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("EnumSingleton"))) {
            EnumSingleton instance3 = (EnumSingleton) inputStream.readObject();
            System.out.println(instance3 == instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

enum EnumSingleton {
    instance;
}
