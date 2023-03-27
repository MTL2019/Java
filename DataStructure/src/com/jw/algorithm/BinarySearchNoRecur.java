package com.jw.algorithm;

/**
 * 二分查找：非递归 时间复杂度 O(log2n)
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] array = {1,3,8,10,11,67,100};
        int res = binarySearch(array,8);
        System.out.println(res);
    }

    public static int binarySearch(int[] arr,int value){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){//需要=号，此时只有一个值，若不是则退出
            int mid = (left + right) /2;
            if (arr[mid] == value) {
                return mid;
            }else if (arr[mid] < value){//在右侧查找
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
