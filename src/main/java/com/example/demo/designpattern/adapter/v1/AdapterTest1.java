package com.example.demo.designpattern.adapter.v1;

/**
 * 适配器模式
 */
public class AdapterTest1 {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.output5v();

    }
}
class Adaptee{
    public int output220(){
        return  220;
    }
}
interface Target{
    int output5v();
}
class Adapter implements Target{
    private Adaptee adaptee;
    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    public int output5v(){
        int i = adaptee.output220();
        System.out.println(String.format( "原始电压： %d v  - >  输出电压： %d  v  ",i,5 ));
        return 5;
    }
}
