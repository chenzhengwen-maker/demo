package com.example.demo.designpattern.strategy.v1;

/**
 * 策略模式
 */
public class ZombieTest {
    public static void main(String[] args) {
        AbstractZombie zombie = new NormalZombie();
        AbstractZombie flagZombie = new FlagZombie();
        AbstractZombie headZombie = new BigHeadZombie();
        zombie.display();
        zombie.move();
        zombie.attack();
        headZombie.display();
        headZombie.move();
        headZombie.attack();
    }
}
abstract class AbstractZombie{
    public abstract void display();
    public void attack(){
        System.out.println("咬");
    };
    public void move(){
        System.out.println("一步一步移动");
    };
}
class NormalZombie extends AbstractZombie{

    @Override
    public void display() {
        System.out.println("我是普通僵尸");
    }
}
class FlagZombie extends AbstractZombie{

    @Override
    public void display() {
        System.out.println("我是旗手僵尸");
    }
}
class BigHeadZombie extends AbstractZombie{

    @Override
    public void display() {
        System.out.println("我是大头僵尸");
    }

    @Override
    public void attack() {
        System.out.println("用头攻击");
    }
}
