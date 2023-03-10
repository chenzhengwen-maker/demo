package com.example.demo.designpattern.strategy.v2;

/**
 * 策略模式
 */
public class StrategyTest {
    public static void main(String[] args) {
        Zombie zombie = new NormalZombie(new StepByStepMove(),new BiteAttack());
        zombie.display();
        zombie.move();
        zombie.attack();
        zombie.setAttackable(new HitAttack());
        zombie.attack();

    }
}
interface Moveable{
    void move();
}
interface Attackable{
    void attack();
}
abstract class Zombie{
    public abstract void display();
    public abstract void move();
    public abstract void attack();
    Moveable moveable;
    Attackable attackable;
    public Zombie(Moveable moveable,Attackable attackable){
        this.moveable = moveable;
        this.attackable = attackable;
    }

    public Moveable getMoveable() {
        return moveable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    public Attackable getAttackable() {
        return attackable;
    }

    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }
}
class StepByStepMove implements Moveable{

    @Override
    public void move() {
        System.out.println("一步一步移动");
    }
}
class BiteAttack implements Attackable{

    @Override
    public void attack() {
        System.out.println("咬");
    }
}
class HitAttack implements Attackable{

    @Override
    public void attack() {
        System.out.println("打.");
    }
}
class NormalZombie extends Zombie{

    public NormalZombie(Moveable moveable, Attackable attackable) {
        super(moveable, attackable);
    }

    @Override
    public void display() {
        System.out.println("我是普通僵尸");
    }

    @Override
    public void move() {
        moveable.move();
    }

    @Override
    public void attack() {
        attackable.attack();
    }
}
