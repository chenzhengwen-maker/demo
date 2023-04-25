package com.example.demo.algorithm;

import java.util.Arrays;

public class PivotIndex {
    public static void main(String[] args) {
        int[] num = {1,2,5,0,1,2,5};
        System.out.println(pivotIndex(num));

    }
    public static int pivotIndex(int[] num){
        int sum1 = Arrays.stream(num).sum();
        int sum2 = 0;
        for (int i = 0; i < num.length; i++) {
            sum2 += num[i];
            if(sum1 == sum2){
                return i;
            }
            sum1 -= num[i];
        }
        return -1;
    }
}
