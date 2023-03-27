package com.jw.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean isVisited[];

    public static void main(String[] args) {

        //String vertexs[] = {"A","B","C","D","E"};
        String vertexs[] = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(vertexs.length);

        for (String vertex:vertexs) {
            graph.insertVertex(vertex);
        }

//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);

        graph.showGraph();
        graph.depthFirstSearch();
        System.out.println();
        graph.broadFirstSearch();
    }

    //构造器
    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        isVisited = new boolean[n];
    }

    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1,int v2){

        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 为需要遍历的当前顶点
    private void depthFirstSearch(boolean[] isVisited,int i){
        System.out.print(getValueByIndex(i)+" --> ");//输出当前i顶点
        isVisited[i] = true;//标记为已访问

        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]) {///如果w存在，还未被访问
                depthFirstSearch(isVisited,w);
            }

            w = getNextNeighbor(i,w);//如果w存在，且已被访问
        }
    }

    //重载，遍历所有节点，内部再深度遍历；以排除从某个顶点遍历不到有些节点的情况
    public void depthFirstSearch(){
        isVisited = new boolean[vertexList.size()];

        for (int i = 0; i < getNumOfVertexs(); i++) {
            if (!isVisited[i]) {
                depthFirstSearch(isVisited,i);
            }
        }
    }

    public void broadFirstSearch(){
        isVisited = new boolean[vertexList.size()];

        for (int i = 0; i < getNumOfVertexs(); i++) {
            if (!isVisited[i]) {
                broadFirstSearch(isVisited,i);
            }
        }
    }

    private void broadFirstSearch(boolean[] isVisited,int i){
        int u;//表示队列的头节点的下标
        int w;//邻接节点的下标
        LinkedList<Object> queue = new LinkedList<>();

        System.out.print(getValueByIndex(i)+" --> ");//输出当前i顶点
        isVisited[i] = true;//标记为已访问
        queue.addLast(i);//从尾部加入，从头部取

        while (!queue.isEmpty()){
            u = (Integer) queue.removeFirst();//取队列头节点的下标
            w = getFirstNeighbor(u);

            while (w != -1){
                if (!isVisited[w]) {///如果w存在，还未被访问
                    System.out.print(getValueByIndex(w)+" --> ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //对于u为初始节点，找w以后的下一个邻接节点
                w = getNextNeighbor(u,w);//如果w存在，且已被访问
            }
        }
    }

    //返回节点个数
    public int getNumOfVertexs(){
        return vertexList.size();
    }

    public int getNumOfEdges(){
        return numOfEdges;
    }

    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    public void showGraph(){
        for (int[] link:edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
