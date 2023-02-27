package com.example.demo.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static AtomicInteger sum = new AtomicInteger();
    public static CountDownLatch countDownLatch = new CountDownLatch(10);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{sum.getAndIncrement();countDownLatch.countDown();}).start();
        }
        countDownLatch.await();
        System.out.println(sum.get());
    }
}
