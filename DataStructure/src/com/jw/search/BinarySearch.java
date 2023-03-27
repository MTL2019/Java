package com.jw.search;

import java.util.ArrayList;

/**
 * 二分查找：数列必须是有序的
 * 思路：按中间对分，如果中间值大于查找值，则在左侧递归，否则在右侧递归
 */
public class BinarySearch {
    public static void main(String[] args) {

        //int arr[] = {1,8,10,89,1000,1234};
        int arr1[] = {1,8,10,89,89,100,123,123};
        //int res = binarySearch(arr,0,arr.length,100);
//        if (res == -1) {
//            System.out.println("没找到");
//        }else{
//            System.out.println("找到了，下标为：" + res);
//        }

        ArrayList<Integer> resList = binarySearch2(arr1,0,arr1.length-1,123);
        if (resList.size() == 0) {
            System.out.println("没找到");
        }else{
            System.out.println("找到了，下标为：" + resList.toString());
        }
    }

    public static int binarySearch(int[] arr,int left,int right,int findVal){

        if (left > right) {//说明递归结束
            return -1;//没找到
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (midValue < findVal) {
            return binarySearch(arr,mid+1,right,findVal);
        }
        else if (midValue > findVal) {
            return binarySearch(arr,left,mid-1,findVal);
        }
        else{
            return mid;//找到
        }
    }

    /**
     * 支持数列中包含重复的数字
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return 可返回所有于查找数相同的数字的下标的集合
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal){

        if (left > right) {//说明递归结束
            return new ArrayList<>();//没找到
        }

        int mid = (left + right) / 2;//二分查找，插值点固定为1/2位置
        int midValue = arr[mid];

        if (midValue < findVal) {
            return binarySearch2(arr,mid+1,right,findVal);
        }
        else if (midValue > findVal) {
            return binarySearch2(arr,left,mid-1,findVal);
        }
        else{
            //如果找到查询值，则分别从左和右搜索是否还有等于findVal/mid的值
            ArrayList<Integer> list = new ArrayList<>();

            int temp = mid -1;
            while (true){
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            list.add(mid);//加入中间值
            temp = mid +1;

            while (true){
                if (temp > arr.length -1 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;//找到
        }
    }
}
