package com.jw.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘问题
 */
public class HorseChessBoard {
    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int col = 1;
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        travalChessBoard(chessBoard,row-1,col-1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start)+"毫秒");

        //输出棋盘
        for (int[] rows:chessBoard) {
            for (int step:rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    private static int X,Y;//棋盘的列、行
    private static boolean visited[];//标记棋盘的各个位置是否被访问
    private static boolean finished;//true表示成功

    /**
     * 马踏棋盘算法
     * @param chessBoard 棋盘
     * @param row 马所在的行
     * @param col 马所在的列
     * @param step 当前是第几步
     */
    public static void travalChessBoard(int[][] chessBoard,int row,int col,int step){
        chessBoard[row][col] = step;
        visited[row * X + col] = true;//一维数组描述棋盘的位置，标记该位置为已访问

        ArrayList<Point> ps = next(new Point(col, row));
        sort(ps);//排序提高效率
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if (!visited[p.y * X + p.x]) {//还没访问过
                travalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
            if (step < X * Y && !finished) {
                chessBoard[row][col] = 0;
                visited[row * X + col] = false;
            }else{
                finished = true;
            }
    }
    /**
     * 根据当前点，计算可以走棋盘上的哪些点，放入ArrayList集合中
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {//判断马可以走的位置
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {//判断马可以走的位置2
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {//判断马可以走的位置3
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {//判断马可以走的位置4
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {//判断马可以走的位置5
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y ) {//判断马可以走的位置6
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {//判断马可以走的位置7
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {//判断马可以走的位置8
            ps.add(new Point(p1));
        }
        return ps;
    }

    //对当前位置的所有下一步的下一步的数目进行非递减排序，即允许重复递增排序，从可能性最小的位置开始尝试并回溯，以提高效率
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {

                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                }else if (count2 == count1) {
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
