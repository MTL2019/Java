package com.jw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 排序算法
 * 1. 冒泡排序 a bubble sort or sinking sort   O(n2)
 * 2. 选择排序： O(n2)
 * 3. 插入排序 Insertion Sort    O(n2)
 * 4. 希尔排序：优化的插入排序 有交换法 和 移动法
 * 5. 快速排序 Quick Sort： 对冒泡排序的改进
 * 6. Merge sort: divide-conquer思想      O(nlogn)
 * 7. Heap Sort  O(nlogn)
 * 8. Bucket Sort / Radix Sorts
 */
public class SortTest {
    public static void main(String[] args) {

        //int arr[] = {1,-3,-5,4,3,2};
        int arr1[] = {2,9,-2,3,-1,1,8,5,-5,-4,4,-3,7,6,0};
        int max = 80;//数组容量
        int arr[] = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int)(Math.random() * max);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间： "+data1Str);

        //bubbleSort(arr);//冒泡排序
        //selectSort(arr);
        //insertSort(arr);
        //shellSortByExchange(arr);//交换式
        //shellSortByMove(arr);//移位式
        //quickSort(arr,0,arr.length-1);
        //mergeSort(arr);

        Date date2 = new Date();
        String data2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间： "+data2Str);

        System.out.println(Arrays.toString(arr));

    }

    public static void bubbleSort(int[] arr){

        int temp = 0;
        boolean flag = true;
        //for (int i = 0; i < arr.length -1 && flag; i++) { // Integrate the flag check in the for loop
        for (int i = 0; i < arr.length -1; i++) {//j+1可以访问到最后一个数
             flag = false;
            for (int j = 0; j < arr.length -1 - i; j++) {
                if (arr[j] >= arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {
                break;
            }
        }
    }

    /**
     * 选择排序：
     * 1. 先找到数组后续中最小的一个数，暂存不交换（提高效率）；
     * 2. 等此轮循环完，与头部数比较，将小的放到头部
     * 3. 循环1、2 ， 直到尾部
     * 交换放到内层循环外，提高了效率
     * @param arr
     */
    public static void selectSort(int[] arr){

        int temp;
        for (int i = 0; i < arr.length; i++) {

            int min = arr[i];
            int index = i;
            for (int j = i +1 ; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    index = j;//此处比冒泡交换少了一句
                }
            }

            if (index != i) {

                arr[index] = arr[i];//此处语句比冒泡少了一次循环，所以要快
                arr[i] = min;

            }
        }
    }

    /**
     * 把数组分两段，前段为有序，后段为无序
     * 每次把后段的第一个数插入到前段中适当位置，保持有序；
     * 插入时，用while循环，对前段数组从后往前扫描，没找到合适位置，将数组值往后移一位
     * @param arr
     */
    public static void insertSort(int[] arr){

        int currentValue ;//把变量定义放到循环外面，节省开销
        int index;
        for (int i = 1; i < arr.length; i++) {

            currentValue = arr[i];//提取的数
            index = i - 1;//提取数前一个数的下标
            //for (index = i − 1; index >= 0 && list[index] > currentValue; k−−) {
            while (index >= 0 && currentValue < arr[index]){
                //从后往前扫描，没找到位置就把数字向后移一位
                arr[index + 1] = arr[index];
                index--;
            }

            //加个判断提高效率
            //如果value >= arr[index]，没必要后移和插入，即不进入while；此时也不需要再赋值
            if (index + 1 != i) {
                arr[index + 1] = currentValue;
            }
        }
    }

    public static void shellSortByExchange(int[] arr){

        int temp;
        //逐渐缩小gap的值，当gap=1时，就是全体逐一插入
        for (int gap = arr.length / 2;gap >=1 ; gap /= 2) {
            //分组后，从第1组第2个数开始逐一与前面同组的比较
            for (int i = gap; i < arr.length ; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {//同组后面的数字与同组前面的数字比较，从后向前扫描，将小的换到前面
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }

        //System.out.println(Arrays.toString(arr));
    }

    public static void shellSortByMove(int[] arr){

        int temp;
        //逐渐缩小gap的值，当gap=1时，就是全体逐一插入
        for (int gap = arr.length / 2;gap >=1 ; gap /= 2) {
            //分组后，从第1组第2个数开始逐一与前面同组的比较
            for (int i = gap; i < arr.length ; i++) {
                int j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {//无序组提取元素 小于 有序组最后一个元素，则开始查找和移位
                    while (j - gap >=0 && temp < arr[j - gap]){//如果一直小于，则一直往前查找
                        arr[j] = arr[j -gap];//j - gap的值往后移位，j - gap位置空出
                        j -= gap;//j = j - gap， 此时j的位置空出
                    }

                    arr[j] = temp;//将空出的位置赋值temp
                }
            }
        }

        //System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){

        int l = left;//左侧的值比中值pivot小
        int r = right;//右侧的值比中值pivot大
        int pivot = arr[(left + right) / 2];
        int temp;

        while(l < r){
            while (arr[l] < pivot){//如果没找到异常的值，则扫描下一位
                l++;
            }

            while (arr[r] > pivot){//如果没找到异常的值，则扫描下一位
                r--;
            }

            if (l >= r) {
                break; //提前退出while
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {//如果交换前，右侧的值==pivot，则向前移动移位，提前规避while循环，防止死循环
                r--;
            }
            if (arr[r] == pivot) {//如果交换前，左侧的值==pivot，则向前移动移位，提前规避while循环，防止死循环
                l++;
            }
        }

        if (l == r) {//不这么做，会栈溢出
            l++;
            r--;
        }

        //调用递归，再分两半，各自排序
        if (left < r) {
            quickSort(arr,left,r);
        }
        if (right > l) {
            quickSort(arr,l,right);
        }
    }

    public static void mergeSort(int[] list){
        if (list.length > 1) {
            //Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list,0,firstHalf,0,list.length / 2);
            mergeSort(firstHalf);

            //Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list,list.length / 2,secondHalf,0,secondHalfLength);
            mergeSort(secondHalf);

            //Merge firstHalf and secondHalf into list
            merge(firstHalf,secondHalf,list);
        }
    }

    public static void merge(int[] list1,int[] list2,int[] temp){
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length){
            if (list1[current1] < list2[current2]) {
                temp[current3++]  = list1[current1++];
            }else{
                temp[current3++]  = list2[current2++];
            }
        }

        while (current1 < list1.length){
                temp[current3++]  = list1[current1++];
        }

        while (current2 < list2.length){
            temp[current3++]  = list2[current2++];
        }
    }

}
