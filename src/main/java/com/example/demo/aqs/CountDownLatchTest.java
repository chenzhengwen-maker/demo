package com.example.demo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class CountDownLatchTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName()+"开始运行");
                } catch (InterruptedException e) {
                    log.error("error",e);
                }
            },"线程"+i).start();
        }
        Thread.sleep(100);
        System.out.println("主线程发命令");
        countDownLatch.countDown();

    }
}
class CountDownLatchTest2{
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            int index = i;
            new Thread(()->{
                try {
                    Thread.sleep(100+ ThreadLocalRandom.current().nextInt(200));
                    System.out.println(Thread.currentThread().getName()+"finish task"+index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("主线程完成");
    }
}
