package com.example.demo.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static int sum = 0;
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                lock.lock();
                try {
                    for (int j = 0; j < 10000; j++) {
                        sum ++;
                    }
                }finally {
                    lock.unlock();
                }

            }).start();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

    }
}
