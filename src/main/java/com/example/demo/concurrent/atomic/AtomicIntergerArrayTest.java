package com.example.demo.concurrent.atomic;

import net.minidev.json.JSONArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntergerArrayTest {
    static int[] a = {1,2,3,4,5,6};
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(a);
    public static void main(String[] args) {
        atomicIntegerArray.set(0,100);
        atomicIntegerArray.addAndGet(1,2);
        System.out.println(atomicIntegerArray);
    }
}
