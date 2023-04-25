package com.example.demo.concurrent.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockTest {
    public static ReentrantLock reentrantLock = new ReentrantLock(true);
    static volatile int ticket = 100;
    public static void main(String[] args) {
        TicketSell ticketSell = new TicketSell();
        new Thread(ticketSell,"线程1").start();
        new Thread(ticketSell,"线程2").start();
        new Thread(ticketSell,"线程3").start();
        new Thread(ticketSell,"线程4").start();
//        for (int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//                @SneakyThrows
//                @Override
//                public void run() {
//                    while (ticket>0) {
//                        reentrantLock.lock();
//                        if(ticket>0) {
//                            System.out.println(Thread.currentThread().getName() + "卖票，ticket=" + ticket--);
//                        }
//                        reentrantLock.unlock();
//                    }
//
//                }
//            },i+"线程").start();
//        }

    }
}

class TicketSell implements Runnable{
    public MyLock myLock = new MyLock();
    public ReentrantLock reentrantLock = new ReentrantLock();
    public Condition condition1 = myLock.newCondition();
    public Condition condition2 = myLock.newCondition();
    public Condition condition3 = myLock.newCondition();
    int ticket = 100;
    @Override
    public void run() {
        while (ticket>0) {
            synchronized (this) {
                ticket--;
                System.out.println(Thread.currentThread().getName() + "卖票，ticket="+ticket);
            }
        }

    }
}

