package com.example.demo.algorithm;

import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {
        int[] num1 = new int[]{1,4,2};
        int[] num2 = new int[]{5};
        int[] num3 = new int[num1.length+num2.length];
        merge(num3,0,num1,3);
        merge(num3,0,num2,1);

    }
    public static void merge(int[] num1,int m,int[] num2,int n){
        System.arraycopy(num2,0,num1,m,n);
        Arrays.sort(num1);
        System.out.println(Arrays.toString(num1));
    }
}
