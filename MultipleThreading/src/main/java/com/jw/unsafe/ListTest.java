package com.jw.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        /*
         * java.util.ConcurrentModificationException 并发修改异常
         * 并发下ArrayList不安全
         * 解决方案1: List<String> list = new Vector<>( );
         * 解决方案2: Collections.synchronizedList(new ArrayList<>());
         * 解决方案3: List<String> list = new CopyOnWriteArrayList<>();;
         */
        //List<String> list = new ArrayList<>();
        //ist<String> list = new Vector<>( );

        //CopyOnWrite 写入时复制  COW:计算机程序设计领域的一种优化策略
        //多线程调用的时候，list，读取的时候，固定的
        //在写入的时候，避免覆盖，造成数据问题
        //CopyOnWriteArrayList使用lock，Vector使用synchronized，使用synchronized效率比较低

        //读写分离  MyCat 数据库级别的

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();

        }
    }
}
