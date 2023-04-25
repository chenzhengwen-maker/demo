package com.example.demo.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2 {
    public static void main(String[] args) {
        System.out.println(predictPartyVictory("RDD"));
    }
    public static String predictPartyVictory(String senate){
        Queue<Integer> RQueue = new LinkedList<>();
        Queue<Integer> DQueue = new LinkedList<>();
        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if(senate.charAt(i) == 'R'){
                RQueue.add(i);
            }else if(senate.charAt(i) == 'D'){
                DQueue.add(i);
            }
        }
        while(!RQueue.isEmpty() && !DQueue.isEmpty()){
            int r = RQueue.poll();
            int d = DQueue.poll();
            if(r<d){
                RQueue.offer(r+n);
            }else{
                DQueue.offer(d+n);
            }
        }
        return RQueue.isEmpty()?"D":"R";
    }
}
