package com.jw.algorithm.kmp;


public class VoilenceMatch {
    public static void main(String[] args) {

        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        int res = voilenceMatch(str1,str2);
        System.out.println(res);
    }
    
    //暴力匹配算法
    public static int voilenceMatch(String str1,String str2){

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int lenS1 = s1.length;
        int lenS2 = s2.length;

        int i = 0;
        int j = 0;
        while (i < lenS1 && j < lenS2){
            if (s1[i] == s2[j]) {
                i++;
                j++;
            }else {
                i = i - (j-1);
                j = 0;
            }
        }

        if (j == lenS2) {
            return i - j;
        }else {
            return -1;
        }
    }
}
