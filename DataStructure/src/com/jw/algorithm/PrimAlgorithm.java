package com.jw.algorithm;

import com.jw.graph.Graph;

import java.util.Arrays;

/**
 * 求最小生成树的第 1 种 算法 ： prim算法
 * 修路问题 -- prim 普里姆算法
 *
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int vertex = data.length;
        int[][] weight = new int[vertex][vertex];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[i].length; j++) {
                weight[i][j] = 10000;//初始化为大数，表示两个点不连通
            }
        }
        //设置图的初始权重
        weight[0][1] = 5;//A
        weight[0][2] = 7;
        weight[0][6] = 2;
        weight[1][0] = 5;//B
        weight[1][3] = 9;
        weight[1][6] = 3;
        weight[2][0] = 7;//C
        weight[2][4] = 8;
        weight[3][1] = 9;//D
        weight[3][5] = 4;
        weight[4][2] = 8;//E
        weight[4][5] = 5;
        weight[4][6] = 4;
        weight[5][3] = 4;//F
        weight[5][4] = 5;
        weight[5][6] = 6;
        weight[6][0] = 2;//G
        weight[6][1] = 3;//G
        weight[6][4] = 4;//G
        weight[6][5] = 6;//G

        MGraph graph = new MGraph(vertex);
        minTree minTree = new minTree();
        minTree.createTree(graph,vertex,data,weight);
        minTree.showGraph(graph);
        minTree.prim(graph,1);

    }


}
//创建最小生成树 --> 村庄的地图
class minTree{
    public void createTree(MGraph mGraph,int vertex,char[] data,int[][] weight ){
        int i,j;
        for (i = 0; i < vertex; i++) {
            mGraph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }
    public void prim(MGraph mGraph, int v){
        int visited[] = new int[mGraph.vetex];//标记顶点是否被访问过

        visited[v] = 1;//当前顶点已访问

        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//定义临时变量，存储权值最小边的权值

        for (int k = 1; k < mGraph.vetex; k++) {//一共是vetex-1条边

            //找到两个顶点的边的权值最小
            for (int i = 0; i < mGraph.vetex; i++) {
                for (int j = 0; j < mGraph.vetex; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && mGraph.weight[i][j] < minWeight) {
                        minWeight = mGraph.weight[i][j];//已访问点与未访问点的边的权值最小
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            System.out.println("边<" + mGraph.data[h1] + mGraph.data[h2] + ">"+" 权值： "+minWeight);
            visited[h2] = 1;
            minWeight = 10000;

        }
    }
    public void showGraph(MGraph mGraph){
        for (int[] link: mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
}

class MGraph{
    int vetex;//顶点数量
    char[] data;//存放顶点
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int vetex) {
        this.vetex = vetex;
        this.data = new char[vetex];
        this.weight = new int[vetex][vetex];
    }
}
