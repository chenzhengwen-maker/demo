package com.example.demo.algorithm;

public class Recursion {
    public static void main(String[] args) {
        System.out.println(forsum(100));
        System.out.println(recurisonSum(100));
        System.out.println(recurisonSum2(1));

    }
    public static int forsum(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
    public static int recurisonSum(int n){
        if(n == 1){
            return 1;
        }else{
            return recurisonSum(n-1) + n;
        }
    }

    public static int recurisonSum2(int n){
        if(n == 10){
            return 1;
        }else{
            return 2*recurisonSum2(n+1)+2;
        }
    }
}
