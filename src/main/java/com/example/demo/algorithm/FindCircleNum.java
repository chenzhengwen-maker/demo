package com.example.demo.algorithm;

public class FindCircleNum {
    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,2,3},{3,4}};
        System.out.println(nums.length);
        int[][] isConnected = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(isConnected[2][1]);
        System.out.println(findCircleNum(isConnected));
    }
    public static int findCircleNum(int[][] isConnected){
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if(!visited[i]){
                dfs(isConnected, visited, provinces, i);
                circles++;
            }
        }
        return circles;
    }

    public static void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
        for (int j = 0; j < provinces; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, provinces, j);
            }
        }
    }
}
