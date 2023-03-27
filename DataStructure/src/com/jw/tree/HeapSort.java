package com.jw.tree;

import java.util.Arrays;

/**
 * 堆排序: 时间复杂度为O(nlogn)
 * 1. 先构建大顶堆或小顶堆  大顶堆：父节点值大于左、右子节点的值； 小顶堆，相反
 * 2. 将root节点值放到数组最后，然后对数组n-1个数重新构建大顶堆，依次缩小数组，直到最后一个值结束
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr){
        int temp = 0;

        //构建一个堆，升序为大顶堆；降序为小顶堆
        for (int i = arr.length / 2 -1; i >=0 ; i--) {//从下往上调整，0 为root节点;
            adjustHeap(arr,i,arr.length);
        }

        //将root节点值放到数组最后，然后对 j-1 个数继续调整成大顶堆
        for (int j = arr.length-1; j > 0 ; j--) {
            temp = arr[j];
            arr[j] = arr[0];//0节点为大顶堆的顶点root，值最大
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }

        System.out.println("堆排序结果：  "+ Arrays.toString(arr));
    }

    //调整第 i 节点，使该节点值大于子节点的值
    public static void adjustHeap(int[] a,int i,int length){
        int temp = a[i];

        for (int k = 2*i + 1 ; k < length; k = 2*k + 1) {//深入子节点
            if (k + 1 < length && a[k] < a[k+1]) {//右子节点比左子节点值大
                k++;//k指向右子节点
            }

            if (a[k] > temp) {
                a[i] = a[k];//将大的值付给父节点
                i = k;//进入子节点层，继续循环
            }else {
                break;
            }
        }
        a[i] = temp;//将原来i节点的值放在调整后的位置
    }
}
