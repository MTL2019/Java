package com.jw.algorithm.divideandconquer;

/**
 * 汉诺塔问题
 * 思路：把A塔的盘看作 上面n-1 个盘 和最下面的n盘
 * 1。 把上面n-1个从a移动到b
 * 2。 把最下面的盘n从a移动到c
 * 3。 把b塔的所有盘移动到c
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(10,'a','b','c');//测试
    }

    public static void hanoiTower(int num,char a, char b,char c){
        if (num == 1) {
            System.out.println("第1个盘从 "+a+"->"+c);
        }else {
            //始终看作两个盘；上面n-1个盘 和 最下面的第n个
            hanoiTower(num -1,a,c,b);//1。先把上面n-1个从a移动到b，移动过程中会使用到c
            System.out.println("第"+num+"个盘从 "+a+"->"+c);//2。把最下面的盘n从a移动到c，移动过程中会使用到b
            hanoiTower(num-1,b,a,c);//3。把b塔的所有盘移动到c，移动过程中会使用到a
        }
    }
}
