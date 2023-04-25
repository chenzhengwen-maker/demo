package com.example.demo.algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(binarySearch(16));

    }
    public static int binarySearch(int x){
        int l = 0, r = x, index = -1;
        while (l<=r){
            int mid = l+(r-1)/2;
            if((long)mid*mid <=x){
                index = mid;
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return index;
    }
}
