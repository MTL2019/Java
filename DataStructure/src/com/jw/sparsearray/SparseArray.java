package com.jw.sparsearray;

/**
 *   二维数组 转 稀疏数组的思路
 * 1. 遍历  原始的二维数组，得到有效数据的个数 sum
 * 2. 根据sum 就可以创建 稀疏数组 sparseArr   int[sum + 1] [3]
 * 3. 将二维数组的有效数据数据存入到 稀疏数组
 *
 * 稀疏数组转原始的二维数组的思路
 *
 * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
 * 2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
 */
public class SparseArray {

    public static void main(String[] args) {

        //新建原始的11*11数组
        int initArr[][] = new int[11][11];
        initArr[1][2] = 1;
        initArr[2][3] = 2;
        initArr[3][5] = 1;

        printArr(initArr,false);//输出正常数组

        int[][] sparseArr = getSparseArr(initArr);//转换为稀疏数组
        //打印稀疏数组
        printArr(sparseArr,true);//输出稀疏数组

        //还原原始数组
        int[][] initArray1 = getInitArr(sparseArr);
        //输出还原数组initArray1
        printArr(initArray1,false);//输出还原数组

    }

    /**
     * 获取正常数组
     * @param sparseArr：稀疏数组
     * @return：正常数组
     */
    private static int[][] getInitArr(int[][] sparseArr) {
        int initArray1[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i <= sparseArr[0][2]; i++) {

            initArray1[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return initArray1;
    }

    /**
     * 输出稀疏数组
     * @param initArr:正常数组
     * @return： 稀疏数组
     */
    private static int[][] getSparseArr(int[][] initArr) {
        //1. 转换为稀疏数组
        int sum = 0;

        //获取数组值的个数
        for (int i = 0; i < initArr.length; i++) {
            for (int j = 0; j< initArr[i].length; j++) {
                if (initArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2. 创建稀疏数组
        int sparseArr[][] = new int[sum+1][3];

        //3 放入值
        sparseArr[0][0] = initArr.length;
        sparseArr[0][1] = initArr[0].length;
        sparseArr[0][2] = sum;

        int count = 0;//稀疏数组的行
        for (int i=0; i < sparseArr[0][0];i++) {
            for (int j=0;j< sparseArr[0][1];j++) {
                if (initArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = initArr[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 打印数组
     * @param Arr 要输出的数组
     * @param flag false：正常数组； true: 稀疏数组
     */
    private static void printArr(int[][] Arr,boolean flag) {
        if (flag == false) {
            //输出正常数组
            System.out.println("==============原始数组===================");
            for (int[] row : Arr) {
                for (int col : row) {
                    System.out.printf("%d   ", col);
                }
                System.out.println();
            }
        }else{
            //输出稀疏数组
            System.out.println("==============稀疏数组===================");
            for (int i = 0; i <= Arr[0][2];i++) {

                System.out.printf("%d   %d   %d",Arr[i][0],Arr[i][1],Arr[i][2]);
                System.out.println();
            }
        }
    }
}
