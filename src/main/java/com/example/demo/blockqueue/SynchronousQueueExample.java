package com.example.demo.blockqueue;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
 
    static class SynchronousQueueProducer implements Runnable {
 
        protected BlockingQueue<String> blockingQueue;
        final Random random = new Random();
 
        public SynchronousQueueProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }
 
        @Override
        public void run() {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    System.out.println("Put: " + data);
                    blockingQueue.put(data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }
 
    static class SynchronousQueueConsumer implements Runnable {
 
        protected BlockingQueue<String> blockingQueue;
 
        public SynchronousQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }
 
        @Override
        public void run() {
            while (true) {
                try {
                    String data = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName()
                            + " take(): " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }
 
    public static void main(String[] args) {
        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();
 
        SynchronousQueueProducer queueProducer = new SynchronousQueueProducer(
                synchronousQueue);
        new Thread(queueProducer).start();
 
        SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(
                synchronousQueue);
        new Thread(queueConsumer1).start();
 
        SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(
                synchronousQueue);
        new Thread(queueConsumer2).start();
        ArrayList<students> stu = new ArrayList<>();
        stu.add(new students("rank",20));
        stu.add(new students("Jack",16));
        stu.add(new students("Tom",18));
        Collections.sort(stu,(o1,o2)->{
            return o1.getAge()-o2.getAge();
        });
    }
}
class students {
    private String name;
    private int age;

    public students(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public students() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "students{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}