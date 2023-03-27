package com.jw.algorithm;

import java.util.Arrays;

/**
 * floyd算法：寻找给定加权图中顶点最短路径的算法
 */
public class Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};

        Graph1 graph1 = new Graph1(vertex, matrix, vertex.length);
        graph1.show();
    }
}

class Graph1{
    private char[] vertex;//存放顶点的数组
    private int[][] dis;//从各个顶点到其他顶点的距离
    private int[][] pre;//保存到达目标顶点的前驱

    /**
     * 构造器
     * @param vertex 顶点数组
     * @param matrix 邻接矩阵
     * @param length 大小
     */
    public Graph1(char[] vertex, int[][] matrix, int length) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public void show(){
        char[] vertex = {'A','B','C','D','E','F','G'};

        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < pre.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();

            for (int j = 0; j < dis.length; j++) {
                System.out.print("("+vertex[i]+"-->"+vertex[j]+"最短路径为： "+dis[i][j] + ") ");
            }
            System.out.println();

        }
    }

    public void floyd(){
        int len = 0;
        for (int k = 0; k < dis.length; k++) {//中间顶点遍历
            for (int i = 0; i < dis.length; i++) {//从i顶点开始，遍历
                for (int j = 0; j < dis.length; j++) {//终点，遍历
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
