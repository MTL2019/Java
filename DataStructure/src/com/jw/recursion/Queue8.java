package com.jw.recursion;

/**
 * 8皇后问题：算法理解是重点
 */
public class Queue8 {
    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();

        queue8.check(0);
        System.out.printf("8皇后问题一共有 %d 种摆法 \n", queue8.count);
        System.out.printf("8皇后问题一共judge了 %d 次 \n", queue8.judgeNum);//15720次，效率低
    }

    int max = 8;
    int[] arr = new int[max];
    int count = 0;
    int judgeNum = 0;

    private void check(int n){
        if (n == max) {//说明已经全部摆好，是一种解法，直接打印，并计数
            print();
            return ;
        }

        for (int i = 0; i < max; i++) {//从第1个皇后开始摆放
            arr[n] = i;//i表示列的位置

            if(judge(n)){//如果不冲突,则摆放下一个皇后的位置
                check(n+1);
            }
        }
    }

    /**
     * 判断皇后摆放是否有冲突
     * @param n 第n个皇后和之前的所有皇后位置是否在同一行、列、斜线上
     * @return true: 不在
     */
    private boolean judge(int n){
        judgeNum++;
        for (int i = 0; i < n; i++) {
            //arr[i] == arr[n] i和n皇后在同一列
            //(Math.abs(n-i) == Math.abs(arr[n] - arr[i])) i和n皇后在斜线上
            if (arr[i] == arr[n] || (Math.abs(n-i) == Math.abs(arr[n] - arr[i]))) {
                return false;
            }
        }
        return true;
    }

    //输出某一种解法
    private void print(){

        for (int i = 0; i < max; i++) {
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
        count++;
    }
}
