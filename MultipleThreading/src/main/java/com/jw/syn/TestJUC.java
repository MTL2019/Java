package com.jw.syn;

import java.util.concurrent.CopyOnWriteArrayList;
//concurrent java并发编程类
//测试JUC安全类型的集合
public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }

        System.out.println(list.size());
    }
}
