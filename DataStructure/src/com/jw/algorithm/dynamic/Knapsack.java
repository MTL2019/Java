package com.jw.algorithm.dynamic;

/**
 * 动态规划算法
 * 背包问题： 可通过填表推理
 *  val[i] : i 物品的价值
 *  w[i] ：  i 物品的重量
 *  v[i][j] : 前 i 个物品中能装入容量为 j 的背包的最大价值
 * 1。 w[i] > j ---> v[i][j] = v[i - 1][j]  此时说明单个物品i的重量已经超过背包容量，那么
 * 2. w[i] < j ---> v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
 *
 * 注意：
 * 01背包中，不能重复放 1种物品
 * 试放第i个物品时，可以放前i-1种物品的组合，并且前i-1种物品的组合的最大价值 存放在v[i - 1][j]中
 */
public class Knapsack {
    public static void main(String[] args) {
        int w[] = {1,4,3};//物品的重量,磅
        int val[] = {1500,3000,2000};//物品的价值
        int n = val.length;//物品种类
        int m = 4;//背包的容量

        // v[i][j] : 前 i 个物品中能装入容量为 j 的背包的最大价值
        int[][] v = new int[n+1][m+1];//
        int[][] path = new int[n+1][m+1];//记录背包中放了哪些物品

        //初始化第1行和第1列，参照
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //根据前面的公式来动态规划
        //i / j 从1开始，剔除第1行和第1列；所以对w / val数组取值时用 i-1 / j-1
        for (int i = 1; i < v.length; i++) {//遍历行，即物品种类
            for (int j = 1; j < v[0].length; j++) {//遍历列，即容量
                if (w[i - 1] > j) {//w[i - 1] > j ： 单个物品i 的容量 已超过背包容量，不能放
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;//标记后者为最大值的情况
                    }else {
                        v[i][j] = v[i - 1][j];//v[i - 1][j]在i-1的情况中已标记，此处不重复标记最大值，且此处也不是i-1的最优解
                    }
                }
            }
        }

        //输出v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("===========================");
        //输出 path
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                System.out.printf("第%d个商品放入到背包： \n" ,i);
//            }
//        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0){//从后向前遍历，
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包： \n" ,i);
                j -= w[i-1];
            }
            i--;
        }
    }
}
