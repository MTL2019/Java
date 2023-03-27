package com.jw.search;

import java.util.ArrayList;

/**
 * 插值查找适合 元素分布比较均匀的情况，效率比二分查找要高；不均匀的时候不一定比二分查找快
 */
public class InsertValueSearch {
    public static void main(String[] args) {

        int arr1[] = {1,8,10,89,89,100,100,100,123,123,124};

        ArrayList<Integer> resList = insertValueSearch(arr1,0,arr1.length-1,100);
        if (resList.size() == 0) {
            System.out.println("没找到");
        }else{
            System.out.println("找到了，下标为：" + resList.toString());
        }
    }

    public static ArrayList<Integer> insertValueSearch(int[] arr, int left, int right, int findVal){

        if (left > right || findVal < arr[0] || findVal > arr[arr.length -1]) {//说明递归结束
            return new ArrayList<>();//没找到
        }

        //插值查找，根据查找点在数列中的位置计算插值点
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);//与二分查找的区别
        int midValue = arr[mid];

        if (midValue < findVal) {
            return insertValueSearch(arr,mid+1,right,findVal);
        }
        else if (midValue > findVal) {
            return insertValueSearch(arr,left,mid-1,findVal);
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
