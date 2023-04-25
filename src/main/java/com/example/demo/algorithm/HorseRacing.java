package com.example.demo.algorithm;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class HorseRacing {
    public static void main(String[] args) {
        int[] A = {2,1,5,8};
        int[] B = {9,1,3,7};
        System.out.println(Arrays.toString(advantageCount(A,B)));
    }
    public static int[] advantageCount(int[] A,int[] B){
        int[] sortB = B.clone();
        Arrays.sort(sortB);
        Arrays.sort(A);
        Map<Integer, Deque<Integer>> bMap = new HashMap<>();
        for (int b:B) {
            bMap.put(b,new LinkedList<>());
        }
        Deque<Integer> aq = new LinkedList<>();
        int j = 0;
        for (int a:A){
            if(a > sortB[j]){
                bMap.get(sortB[j++]).add(a);
            }else{
                aq.add(a);
            }
        }
        int[] ans = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            if(bMap.get(B[i]).size()>0){
                ans[i] = bMap.get(B[i]).removeLast();
            }else{
                ans[i] = aq.removeLast();
            }
        }
        return ans;
    }
}
