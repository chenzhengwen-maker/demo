package com.example.demo.algorithm;

public class ArrangeCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(8));

    }
    public static int arrangeCoins(int n){
        for (int i = 0; i < n; i++) {
            n = n-i;
            if(n<=i){
                return i;
            }
        }
        return 0;
    }
}
