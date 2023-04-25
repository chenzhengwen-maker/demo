package com.example.demo.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{1,2,3,4},5)));
        System.out.println(Arrays.toString(twoSumMap(new int[]{1,2,3,4},5)));
    }
    public static int[] twoSum(int[] num,int target){
        int length = num.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if(num[i]+num[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoSumMap(int[] num,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            if(map.containsKey(target-num[i])){
                return new int[]{map.get(target-num[i]),i};
            }
            map.put(num[i],i);
        }
        return new int[0];
    }
}
