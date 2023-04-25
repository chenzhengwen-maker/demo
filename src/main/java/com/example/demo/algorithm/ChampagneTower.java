package com.example.demo.algorithm;

public class ChampagneTower {
    public static void main(String[] args) {
        System.out.println(champagneTower(5,2,1));

    }
    public static double champagneTower(int poured,int query_row,int query_glass){
        double[][] ca = new double[100][100];
        ca[0][0] = poured;
        for (int r = 0; r < query_row; r++) {
            for (int l = 0; l < r; l++) {
                double d = (ca[r][l] - 1.0)/2;
                if(d > 0){
                    ca[r+1][l] += d;
                    ca[r+1][l+1] += d;
                }
            }
        }
        return Math.max(1.0,ca[query_row][query_glass]);
    }
}
