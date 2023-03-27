package com.jw.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序：空间换时间，需要耗费额外的内存 --> 稳定排序 ：有负数的时候不要用基数排序！！可参考支持负数的基数排序方案
 * 1。 10个桶，每个桶的深度为排序数组的长度
 * 2。 从个位开始，把每个数字按个位数放入对应数字的桶中，然后按0，1，2号桶重新将桶内数字放回数字，实现第一次排序
 * 3。 从十位一次到最高位，排序
 *
 * 稳定排序：原序列中r[i]=r[j], 排序后i和j的位置不变； 否则为不稳定排序
 */
public class RadixSortTest {
    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        radixSort(arr);

        System.out.println("最终数组排序为： "+ Arrays.toString(arr));
    }

    public static void radixSort(int[] arr){

        //求数组中数的最大位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();//即为最大位数

        int bucket[][] = new int[10][arr.length];//10个桶，每个桶的深度为排序数组的长度
        int count[] = new int[10];//10个桶的对应指针，记录桶内元素个数

        for (int k = 0,n = 1; k < maxLength; k++,n *=10) {
            //针对每个数字的个位进行排序
            for (int i = 0; i < arr.length; i++) {
                int digit = arr[i] / n % 10;//取个位

                bucket[digit][count[digit]] = arr[i];
                count[digit]++;

            }
            //将桶中数据放回arr
            int index = 0;
            for (int i = 0; i < 10; i++) {
                if (count[i] !=0 ) {
                    for (int j = 0; j < count[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                    count[i] = 0;
                }
            }

            System.out.printf("第 %d 轮，数组排序为：%s \n ",k+1, Arrays.toString(arr));
        }

    }
}
