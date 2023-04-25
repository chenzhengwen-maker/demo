package com.example.demo.concurrent.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest2 {
    private static int sum = 0;
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        try {
            lock.lock();
            method1();
        }finally {
            lock.unlock();
        }

    }
    public static void method1(){
        try {
            lock.lock();
            System.out.println("execute method1");
            method2();
        }finally {
            lock.unlock();
        }
    }
    public static void method2(){
        try {
            lock.lock();
            System.out.println("execute method2");
            method3();
        }finally {
            lock.unlock();
        }
    }
    public static void method3(){
        try {
            lock.lock();
            System.out.println("execute method3");
        }finally {
            lock.unlock();
        }
    }
}
