package com.jw.tree.heapSort;

import java.util.Comparator;

public class HeapSort {

    //sorts a list in a natural order using the Comparable interface
    public static <E> void heapSort(E[] list){
        heapSort(list, (e1, e2) -> ((Comparable<E>)e1).compareTo(e2));
    }

    //sorts a list using a specified comparator
    public static <E> void heapSort(E[] list, Comparator<E> c){

        Heap<E> heap = new Heap<>(c);

        for (int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }

        for (int i = list.length - 1;  i >=0 ; i--) {
            list[i] = heap.remove();
        }
    }

    public static void main(String[] args) {
        Integer[] list = {-44,-5,-3,3,3,1,-4,0,1,2,4,5,53};
        heapSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }
}
