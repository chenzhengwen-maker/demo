package com.example.demo.algorithm;

public class FindMaxAverage {
    public static void main(String[] args) {
        int a[] = {1,2,3,4,8,5};
        System.out.println(findMaxAverage(a,3));

    }
    public static double findMaxAverage(int[] num,int k){
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum +=num[i];
        }
        int sumMax = sum;
        for (int i = k; i < num.length; i++) {
            sum = sum - num[i-k] + num[i];
            sumMax = Math.max(sumMax,sum);
        }
        return 1.0*sumMax/k;
    }
}
