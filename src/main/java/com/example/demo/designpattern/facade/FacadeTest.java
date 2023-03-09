package com.example.demo.designpattern.facade;

public class FacadeTest {
    public static void main(String[] args) {
        Facade facade1 = new Facade();
        facade1.doSomethingFacade();
        Facade facade2 = new Facade();
        facade2.doSomethingFacade();
    }
}
class Facade{
    private SubSystem1 subSystem1 = new SubSystem1();
    private SubSystem2 subSystem2 = new SubSystem2();
    private SubSystem3 subSystem3 = new SubSystem3();
    public void doSomethingFacade(){
        subSystem1.method1();
        subSystem2.method2();
        subSystem3.method3();
    };
}
class SubSystem1{
    public void method1(){
        System.out.println("subsystem1 execute");
    }
}
class SubSystem2{
    public void method2(){
        System.out.println("subsystem2 execute");
    }
}
class SubSystem3{
    public void method3(){
        System.out.println("subsystem3 execute");
    }
}
