package com.jw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 分治排序，先分后治，效率很高
 * 分：从中间将数组分为2个数组，左右数组又递归分组，直到2个一组
 * 治：左右数组每次分完后，进行一次排序；左右数组从头开始比较，小的放到临时数组中的左侧，依次填满temp数组；最后把temp数组放回arr中
 * 递归分治，直到整个数组排序完成
 */
public class MergeSortTest {
    public static void main(String[] args) {

        //int arr[] = {8,4,5,7,1,3,6,2};
        //int temp[] = new int[arr.length];

        int max = 8000000;//数组容量
        int arr[] = new int[max];
        int temp[] = new int[arr.length];
        for (int i = 0; i < max; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间： "+data1Str);

        mergeSort(arr, 0,arr.length-1,temp );

        Date date2 = new Date();
        String data2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间： "+data2Str);

        //System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right) {
            int mid = (left + right) / 2;//中间索引

            mergeSort(arr,left,mid,temp);//左分
            mergeSort(arr,mid+1,right,temp);//右分
            merge(arr,left,mid,right,temp);//治
        }
    }
    //治
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左侧有序数组指针
        int j = mid + 1;//右侧有序数组指针
        int t = 0; //指向temp的指针，填充数组用
        //System.out.println(Arrays.toString(temp));
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            }else{
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        while (i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将temp数组拷贝回arr，并不是每次都拷贝整个数组
         t = 0;
        int tempLeft = left;//每次进来left和right的值都在变化，只拷贝left到right之间的值
        //System.out.println("tempLeft:" + tempLeft + "  right:" + right);
        while (tempLeft <= right){// =号表示要扫描到length-1位
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
