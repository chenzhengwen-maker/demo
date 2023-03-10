package com.example.demo.exception;

public class ThrowExceptionTest {
    public static void main(String[] args){
        A a = new A();
        a.a();
        try {
            a.b();
        }catch (Exception e){
            System.out.println("catch b方法的异常");
        }
        //a.b();
        System.out.println("main 方法完成");

    }
}
class A{
    public void a(){
        System.out.println("方法a执行了");
    }
    public void b(){
        System.out.println("方法b执行了");
        throw new RuntimeException("方法b中抛出异常");
    }
}
