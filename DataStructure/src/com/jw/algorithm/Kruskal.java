package com.jw.algorithm;

import java.util.Arrays;

/**
 * 求最小生成树的第 2 种 算法 ： kruskal算法
 * 公交站问题
 * 两个顶点如果指向同一个终点，将形成回路
 */
public class Kruskal {

    private int edgeNum;
    private char[] vertex;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;//表示两点不连通

    public Kruskal(char[] vertex,int[][] matrix){
        //初始化顶点，复制拷贝，内部修改不影响外部vertex变量的值
        this.vertex = new char[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }

        this.matrix = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i+1; j < vertex.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] != INF) {
                    edgeNum++;//统计边数
                }
            }
        }
    }

    public void kruskal() {
        int index = 0;//最后结果数组的索引
        int ends[] = new int[edgeNum];//保持"已有最小生成树"中的每个顶点在最小生成树的终点

        EdgeData[] res = new EdgeData[edgeNum];//保存最终的最小生成树
        EdgeData[] edges = getEdges();//获取图中所有边的集合
        System.out.println("图中的边"+Arrays.toString(edges)+"  边数为：" + edges.length);

        sortEdges(edges);//从小到大排序

        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);//获取顶点的下标，从字符获取数组下标数字
            int p2 = getPosition(edges[i].end);//获取顶点的下标，从字符获取数组下标数字

            int m = getEnd(ends,p1);//获取已有生成树中顶点的连通终点
            int n = getEnd(ends,p2);//获取已有生成树中顶点的连通终点
            if (m != n) {
                ends[m] = n;//保存该顶点的连通终点
                res[index++] = edges[i];//不是回路，该边入选
            }
        }

        System.out.println("输出最小生成树:");
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }
    }
    public void print(){
        System.out.println("邻接矩阵为：  ");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d" ,matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边的权值排序
     * @param edges
     */
    public void sortEdges(EdgeData[] edges){
        for (int i = 0; i < edges.length -1; i++) {
            for (int j = 0; j < edges.length -1; j++) {
                if (edges[j].weight > edges[j+1].weight) {
                    EdgeData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值
     * @return 顶点的下标
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertex.length; i++) {
            if(vertex[i] == ch ){
                return i;
            }
        }
        return -1;
    }

    private EdgeData[] getEdges(){
        int index = 0;
        EdgeData[] edges = new EdgeData[edgeNum];

        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EdgeData(vertex[i],vertex[j],matrix[i][j] );
                }
            }
        }

        return edges;
    }

    /**
     * 获取下标为i的顶点的重点
     * @param ends ： 各个终点对应的终点，根据加入的边，逐渐形成的
     * @param i : 传入的下标为i的顶点
     * @return ： i顶点的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
    public static void main(String[] args) {
        char[] vertex = new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}};

        Kruskal kruskal = new Kruskal(vertex, matrix);
        kruskal.print();

        EdgeData[] edge = kruskal.getEdges();
        System.out.println("排序前："+Arrays.toString(edge));
        kruskal.sortEdges(edge);
        System.out.println("排序后："+Arrays.toString(edge));

        kruskal.kruskal();

    }
}

class EdgeData{
    char start;
    char end;
    int weight;

    public EdgeData(char start,char end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeData{" +
                "<" + start +
                ", " + end +
                "> = " + weight +
                '}';
    }
}
