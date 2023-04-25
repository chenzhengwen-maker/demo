package com.example.demo.algorithm;

public class RemoveDuplicates {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,2,2,3,4}));

    }
    public static int removeDuplicates(int[] num){
        int i = 0;
        for (int j = 0; j < num.length; j++) {
            if(num[j] != num[i]){
                i++;
                num[i] = num[j];
            }
        }
        return i+1;
    }
}
