package com.example.demo.concurrent.aqs;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁Demo
 */
public class ReentrantReadWriteLockDemo {
 
    class MyObject {
        private Object object;
 
        private ReadWriteLock lock = new ReentrantReadWriteLock();
 
        public void get() throws InterruptedException {
            lock.readLock().lock(); //上读锁
            try {
                System.out.println(Thread.currentThread().getName() + "准备读取数据");
                Thread.sleep(new Random().nextInt(1000));
                System.out.println(Thread.currentThread().getName() + "读数据为：" + this.object);
            } finally {
                lock.readLock().unlock(); //释放读锁
            }
        }
 
        public void put(Object object) throws InterruptedException {
            lock.writeLock().lock(); //上写锁
            try {
                System.out.println(Thread.currentThread().getName() + "准备写数据");
                Thread.sleep(new Random().nextInt(1000));
                this.object = object;
                System.out.println(Thread.currentThread().getName() + "写数据为" + this.object);
            } finally {
                lock.writeLock().unlock(); //释放写锁
            }
        }
    }
 
    public static void main(String[] args) throws InterruptedException {
        final MyObject myObject = new ReentrantReadWriteLockDemo().new MyObject();
 
        class WorkerRead implements Runnable
        {
            private CountDownLatch latch;
 
            public WorkerRead(CountDownLatch latch) {
                this.latch = latch;
            }
 
            public void run()
            {
                try {
                    myObject.get();//读操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }
 
        class WorkerWrite implements Runnable
        {
            private CountDownLatch latch;
 
            public WorkerWrite(CountDownLatch latch) {
                this.latch = latch;
            }
 
            public void run()
            {
                try {
                    myObject.put(new Random().nextInt(1000));//写操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }
 
        CountDownLatch latch = new CountDownLatch(10);
 
        //启动5个写线程
        for(int i =0; i < 5; i++)
        {
            new Thread(new WorkerWrite(latch)).start();
        }
 
        //启动5个读线程
        for(int i =0; i < 5; i++)
        {
            new Thread(new WorkerRead(latch)).start();
        }
 
        latch.await();
    }
}