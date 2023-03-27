package com.jw.algorithm.kmp;

import java.util.Arrays;

/**
 * kmp算法：
 * 1。 找部分匹配表
 * 2。 搜索，跳过重复部分
 */
public class Kmp {
    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int index = kmpSearch(str1,str2,next);
        System.out.println(index);
    }

    /**
     * kmp搜索
     * @param str1 原字符串
     * @param str2 用于匹配的字符串
     * @param next 匹配字符串值数组
     * @return
     */
    public static int kmpSearch(String str1,String str2, int[] next){


        for (int i = 0,j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
    public static int[] kmpNext(String str){
        int next[] = new int[str.length()];
        next[0] = 0;//如果字符串长度为1，部分匹配值表为0

        //获取字符串的部分匹配值表
        for (int i = 1,j = 0; i < str.length(); i++) {

            //kmp关键算法
            while (j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j-1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                j++;//部分匹配值加1
            }
            next[i] = j;
        }

        return next;
    }
}
