package com.example.demo.algorithm;

/**
 * 递归三要素：1：作用 2：出口 3：范围递减的等式
 * 递归算法三要素
 * 递归的公式
 * 递归的终结点
 * 递归的方向必须走向终结点
 */
public class CanWin {
    public static void main(String[] args) {
        int[] nums = {5,200,2,3,1};
        int p = maxScore(nums,0,nums.length-1);
        System.out.println(p);

    }
    public static int maxScore(int[] nums,int l,int r){
        if(l == r){
            return nums[l];
        }
        int sLeft = 0,sRight = 0;
        if(r - l == 1){
            sLeft = nums[l];
            sRight = nums[r];
        }
        if(r - l >=2){
            sLeft = nums[l] + Math.min(maxScore(nums,l+2,r),maxScore(nums,l+1,r-1));
            sRight = nums[r] + Math.min(maxScore(nums,l+1,r-1),maxScore(nums,l,r-2));
        }
        return Math.max(sLeft,sRight);
    }
    //差值
    public static int getScore(int[] nums,int start,int end){
        if(start == end){
            return nums[start];
        }
        int selectLeft = nums[start] - getScore(nums,start+1,end);
        int selectRight = nums[end] - getScore(nums,start,end-1);
        return Math.max(selectLeft,selectRight);
    }
    //动态规划
    public static boolean predictTheWinner(int[] nums){
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length-2; i >=0 ; i--) {
            for (int j = i+1; j < length; j++) {
                dp[i][j] = Math.max(nums[i]-dp[i+1][j],nums[i]-dp[i][j-1]);
            }
        }
        return dp[0][length-1]>=0;
    }
}
