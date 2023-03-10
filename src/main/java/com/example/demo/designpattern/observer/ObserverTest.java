package com.example.demo.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver(new Task1());
        subject.addObserver(new Task2());
        subject.notifyObserver("xxxxx");

    }
}
interface Observer{
    void update(Object object);
}
class Subject{
    private List<Observer> container = new ArrayList<>();
    public void addObserver(Observer observer){
        container.add(observer);
    }
    public void deleteObserver(Observer observer){
        container.remove(observer);
    }
    public void notifyObserver(Object object){
        for (Observer observer:container){
            observer.update(object);
        }
    }
}
class Task1 implements Observer{

    @Override
    public void update(Object object) {
        System.out.println(" task1 received: "+object);
    }
}

class Task2 implements Observer{

    @Override
    public void update(Object object) {
        System.out.println(" task1 received: "+object);
    }
}
