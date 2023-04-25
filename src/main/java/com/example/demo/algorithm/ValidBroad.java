package com.example.demo.algorithm;

public class ValidBroad {
    public static void main(String[] args) {
        System.out.println(validBroad(new String[]{"xxx"," ox","oo "}));
    }
    public static boolean validBroad(String[] broad){
        int xcount = 0,ocount = 0;
        for (String row : broad) {
            for(char c : row.toCharArray()){
                if(c == 'x'){
                    xcount++;
                }
                if(c == 'o'){
                    ocount++;
                }
            }
        }
        if(ocount != xcount && ocount != xcount-1) return false;
        if(win(broad,"xxx") && ocount!=xcount-1) return false;
        if(win(broad,"ooo") && ocount!=xcount) return false;
        return true;
    }
    public static boolean win(String[] board,String flag){
        for (int i = 0; i < 3; ++i) {
            //纵向3连
            if (flag.equals("" + board[i].charAt(0) + board[i].charAt(1) + board[i].charAt(2)))
                return true;
            //横向3连
            if (flag.equals(board[i]))
                return true;
        }
        // \向3连
        if (flag.equals(""+board[0].charAt(0)+board[1].charAt(1)+board[2].charAt(2)))
            return true;
        // /向3连
        if (flag.equals(""+board[0].charAt(2)+board[1].charAt(1)+board[2].charAt(0)))
            return true;
        return false;
    }
}
