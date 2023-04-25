package com.example.demo.algorithm;

import java.util.Arrays;

public class LargestPerimeter {
    public static void main(String[] args) {
        System.out.println(largestPerimeter(new int[]{1,2,2}));

    }
    public static int largestPerimeter(int[] nums){
        Arrays.sort(nums);
        for (int i = nums.length-1; i >=2; --i) {
            if(nums[i]<nums[i-1]+nums[i-2]){
                return nums[i]+nums[i-1]+nums[i-2];
            }
        }
        return 0;
    }
}
