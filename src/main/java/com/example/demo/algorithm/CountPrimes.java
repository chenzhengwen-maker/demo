package com.example.demo.algorithm;

public class CountPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(5));

    }
    public static int countPrimes(int n){
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrimes(i)?1:0;
        }
        return ans;
    }
    public static boolean isPrimes(int x){
        for (int i = 2; i * i <= x; i++) {
            if(x % i == 0){
                System.out.println(x);
                return false;
            }
        }
        return true;
    }
}
