package com.example.demo.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(()->{
            lock.lock();
            System.out.println("线程1开始运行");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1完成");
            condition2.signal();
            lock.unlock();
        },"thread-1").start();

        new Thread(()->{
            lock.lock();
            System.out.println("线程2开始运行");
            condition.signal();
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2完成");
            lock.unlock();
        },"thread-2").start();
    }
}
