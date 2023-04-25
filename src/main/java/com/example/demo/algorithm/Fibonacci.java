package com.example.demo.algorithm;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(calculate(6));

    }
    public static int calculate(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return calculate(n-1)+calculate(n-2);
    }
}
