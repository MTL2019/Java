package com.jw.algorithm;

import java.util.Arrays;

/**
 * Dijkstra 算法，计算从某一顶点到其他顶点的最短路径
 */
public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];//邻接矩阵
        final int N = 65535;
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

        //graph.dijkstra(6);
        graph.dijkstra(2);//从C顶点出发
        graph.show();
    }
}

class VisitedVertex{
    public int[] alreadyArr;//记录各顶点是否访问过，1为已访问，0为未访问；动态更新
    private int[] preVisited;//每个下标对应的值为前一个顶点的下标
    public int[] dis;//记录出发点到其他所有顶点的距离

    /**
     * 构造器
     * @param length 顶点个数
     * @param index 从哪个顶点出发，为下标
     */
    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];

        Arrays.fill(dis,65535);//65535为未连通
        this.alreadyArr[index] = 1;//出发顶点设为已访问
        this.dis[index] = 0;//自己连通自己，设为0
    }

    public void show(){
        System.out.println("========================");
        for (int i:alreadyArr ) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i:preVisited ) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i:dis ) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count]+"("+i+") ");
            }else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
    //继续选择并返回新的访问节点，出发顶点不变
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    //判断下标为index的顶点是否被访问过
    public boolean in(int index){
        return alreadyArr[index] == 1;
    }

    //更新出发点到index顶点的距离
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    //更新节点的前驱为index的顶点
    public void updatePre(int pre,int index){
        preVisited[pre] = index;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }


}
class Graph{
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;//已经访问顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void show(){
        vv.show();
    }
    //狄杰斯特拉算法实现
    public void dijkstra(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);
        }
    }

    //更新index顶点到周围顶点的距离，和周围顶点的前驱顶点
    public void update(int index){
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.getDis(index) + matrix[index][i];//len = 出发顶点到index的距离 + index顶点到j顶点的距离
            if (!vv.in(i) && len < vv.getDis(i)) {//如果顶点没有被访问过，并且len < dis中的距离，则需要更新
                vv.updatePre(i,index);//更新index顶点的前驱
                vv.updateDis(i,len);//更新出发顶点到index顶点的距离
                
            }
        }
    }

    public void showGraph(){
        for (int[] link:matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
}
