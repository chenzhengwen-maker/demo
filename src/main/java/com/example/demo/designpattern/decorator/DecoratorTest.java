package com.example.demo.designpattern.decorator;

/**
 * 装饰器模式
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Component component = new ConcreteComponent1(new ConcreteComponent2(new ConCreteComponent()));
        component.operation();

    }
}
interface Component{
    void operation();
}
class ConCreteComponent implements Component{

    @Override
    public void operation() {
        System.out.println("拍照");
    }
}
abstract class Decorator {
    public Component component;
    public Decorator(Component component){
        this.component = component;
    }
}
class ConcreteComponent1 extends Decorator implements Component{

    public ConcreteComponent1(Component component){
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("添加美颜");
        component.operation();
    }
}

class ConcreteComponent2 extends Decorator implements Component{

    public ConcreteComponent2(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("添加滤镜");
        component.operation();
    }
}
