package com.example.demo.algorithm;

import java.util.Arrays;

public class ThreeNumsMul {
    public static void main(String[] args) {
        System.out.println(getMax(new int[]{-1,8,9,1,2,3}));
        int a[] = {0,1};
        System.out.println(a[0]);
    }
    public static int getMax(int[] num){
        Arrays.sort(num);
        return Math.max(num[0]*num[1]*num[num.length-1],num[num.length-3]*num[num.length-2]*num[num.length-1]);
    }
}
