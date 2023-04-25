package com.example.demo.algorithm;

public class LemonadeChange {
    public static void main(String[] args) {
        System.out.println(lemoadeChange(new int[]{5,10,5,5,20}));

    }
    public static boolean lemoadeChange(int[] bills){
        int five = 0,ten = 0;
        for (int bill:bills){
            if(bill == 5){
                five++;
            }else if(bill == 10){
                if(five == 0){
                    return false;
                }
                if(five>0){
                    ten++;
                    five--;
                }
            }else{
                if(five>=3){
                    five -=3;
                }else if(five>0 && ten>0){
                    five--;
                    ten--;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
