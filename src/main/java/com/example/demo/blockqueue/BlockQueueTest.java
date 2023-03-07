package com.example.demo.blockqueue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * BlockingQueue常用方法示例
 * 当队列满了无法添加元素，或者是队列空了无法移除元素时：
 * 抛出异常：add、remove、element
 * 返回结果但不抛出异常：offer、poll、peek
 * 阻塞：put、take
 * BlockingQueue和JDK集合包中的Queue接口兼容，同时在其基础上增加了阻塞功能。
 * 入队：
 * （1）offer(E e)：如果队列没满，返回true，如果队列已满，返回false（不阻塞）
 * （2）offer(E e, long timeout, TimeUnit unit)：可以设置阻塞时间，如果队列已满，则进行阻塞。超过阻塞时间，则返回false
 * （3）put(E e)：队列没满的时候是正常的插入，如果队列已满，则阻塞，直至队列空出位置
 * 出队：
 * （1）poll()：如果有数据，出队，如果没有数据，返回null   （不阻塞）
 * （2）poll(long timeout, TimeUnit unit)：可以设置阻塞时间，如果没有数据，则阻塞，超过阻塞时间，则返回null
 * （3）take()：队列里有数据会正常取出数据并删除；但是如果队列里无数据，则阻塞，直到队列里有数据
 */
public class BlockQueueTest {
    public static void main(String[] args) {
        //addTest();
        elementTest();

    }
    /**
     * add 方法是往队列里添加一个元素，如果队列满了，就会抛出异常来提示队列已满。
     */
    private static void addTest(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue(2);
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add(1));
    }
    /**
     * remove 方法的作用是删除元素并返回队列的头节点，如果删除的队列是空的， remove 方法就会抛出异常。
     */
    @Test
    public void removeTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(2);
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    /**
     * element 方法是返回队列的头部节点，但是并不删除。如果队列为空，抛出异常
     */
    private static void elementTest(){
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        System.out.println(blockingQueue.element());
    }

    /**
     * poll 方法作用也是移除并返回队列的头节点。 如果队列为空，返回null
     */
    @Test
    public void pollTest(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue(2);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * peek 方法返回队列的头元素但并不删除。 如果队列为空，返回null
     */
    @Test
    public  void peekTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        System.out.println(blockingQueue.peek());
    }

    /**
     * put 方法的作用是插入元素。如果队列已满就无法继续插入,阻塞插入线程，直至队列空出位置
     */
    @Test
    public void putTest(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        try {
            blockingQueue.put(1);
            blockingQueue.put(2);
            blockingQueue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * take 方法的作用是获取并移除队列的头结点。如果执队列里无数据，则阻塞，直到队列里有数据
     */
    @Test
    public void takeTest(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        try {
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
