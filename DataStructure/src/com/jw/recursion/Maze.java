package com.jw.recursion;

/**
 * 用数组模拟走迷宫：递归回溯
 */
public class Maze {
    public static void main(String[] args) {

        int[][] maze = new int[8][7];

        //将周圈设为1，代表墙
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                maze[0][j] = 1;
                maze[7][j] = 1;
                maze[i][0] = 1;
                maze[i][6] = 1;
            }
        }

        //设置挡墙
        maze[3][1] = 1;
        maze[3][2] = 1;

        printMaze(maze, 8, 7);//打印地图

        setMaze(maze, 1, 1);//走迷宫

        printMaze(maze, 8, 7);//打印地图
    }

    /**
     * 约定：
     * 点的取值： 0： 还没走过 1： 墙，不能走； 2：已走成功 3： 走过，不成功
     * 策略：走的顺序： 下 - 右 - 上 - 左
     * @param maze  设置好边界的地图
     * @param i  起点坐标
     * @param j  起点坐标
     * @return   true： 可以走 ； false：此路不同，回溯到上一点
     */
    public static boolean setMaze(int[][] maze, int i,int j){
        if (maze[6][5] == 2) {
            return true;
        }else{
            if (maze[i][j] == 0) {

                maze[i][j] = 2;//假设可以走通
                if (setMaze(maze, i+1, j)) {//往下走
                    return true;
                }else if (setMaze(maze, i, j + 1)) {//往右走
                    return true;
                }else if (setMaze(maze, i - 1, j)) {//往上走
                    return true;
                }else if (setMaze(maze, i, j - 1)) {//往左走
                    return true;
                }else {//走不通
                    maze[i][j] = 3;//走不通
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    //打印地图
    public static void printMaze(int[][] maze,int m,int n){

        System.out.println("============地图更新如下========");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(maze[i][j] + " ");
            }

            System.out.println();
        }
    }
}
