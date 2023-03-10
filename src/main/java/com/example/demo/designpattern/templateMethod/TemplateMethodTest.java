package com.example.demo.designpattern.templateMethod;

public class TemplateMethodTest {
    public static void main(String[] args) {
        AbstractClass abstractClass = new SubClass();
        abstractClass.operation();
    }
}
abstract class AbstractClass{
    void operation(){
        System.out.println("pre");
        System.out.println("step1");
        System.out.println("step2");
        dosomethig();
    }
    public abstract void dosomethig();
}
class SubClass extends AbstractClass{

    @Override
    public void dosomethig() {
        System.out.println("step3");
    }
}
