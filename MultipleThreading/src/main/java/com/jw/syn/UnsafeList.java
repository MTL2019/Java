package com.jw.syn;

import java.util.ArrayList;
import java.util.List;

//线程不安全的集合
//使用同步快锁住变量
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                synchronized (list){//锁住list，最终结果是10000
                    list.add(Thread.currentThread().getName());
                }
            }
            ).start();
        }
        System.out.println(list.size());//两个线程添加到同一个list位置，导致实际list大小小于10000
    }
}
