package com.example.demo.algorithm;

public class Rob {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(maxMoney(nums,nums.length-1));
        System.out.println(maxMoney(nums));
    }
    public static int maxMoney(int[] nums,int index){
        if(nums == null || index < 0){
            return 0;
        }
        if(index == 0){
            return nums[0];
        }
        return Math.max(maxMoney(nums,index-1),maxMoney(nums,index-2)+nums[index]);
    }
    public static int maxMoney(int[] nums){
        int length = nums.length;
        if(nums == null || length == 0){
            return 0;
        }
        if(length == 1){
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[length-1];

    }
}
