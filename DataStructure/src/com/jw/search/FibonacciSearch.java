package com.jw.search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 斐波那契（黄金分割法）
 * 斐波那契数前后两个值的比接近黄金分割比，0.618，以此比来确定插值点mid
 */
public class FibonacciSearch {

    private static int maxSize = 20;
    public static void main(String[] args) {

        int arr[] = {1,8,10,89,100,123};
        int res = fibonacciSearch(arr,123);
        if (res < 0) {
            System.out.println("没找到");
        }else{
            System.out.println("找到了，下标为：" + res);
        }
    }

    public static int[] fib(){
        int fib[] = new int[maxSize];

        fib[0] = 1;
        fib[1] = 1;

        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }

        return fib;
    }

    public static int fibonacciSearch(int[] arr, int key){
        int low = 0;
        int high = arr.length -1;//最后一个数的下标
        int k = 0;
        int mid = 0;
        int f[] = fib();

        //找到合适的k，让数组最大值与斐波那契数接近
        while (high > f[k] - 1){
            k++;
        }
        //用数组最大值填充数组最大值与斐波那契数之间的空档
        int temp[] = Arrays.copyOf(arr,f[k]);//不足部分用0填充

        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];//将用0填充的数字全部用arr[high]，数组最大值填充
        }

        while(low <= high){
            //当数组长度为f[k]-1时，则数组可拆分为f[k-1]-1 + f[k-2]-1 + 1的两个数组
            //这也是上面填充数组的目的
            mid = low + f[k-1] -1;

            if (key < temp[mid]) {
                high = mid -1;
                k--;//左边的斐波那契数为f[k-1]
            }else if (key > temp[mid]) {
                low = mid +1;
                k -= 2;//右边的斐波那契数为f[k-2]
            }else{
                if (mid <= high) {
                    return mid;
                }else{
                    return high;//因为high下标后面的填充数都和high下标的数相等，所以当mid大于high时，即返回high下标
                }
            }
        }
        return -1;//没找到
    }
}
