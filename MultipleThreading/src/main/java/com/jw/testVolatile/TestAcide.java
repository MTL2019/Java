package com.jw.testVolatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试volatile原子性
 * 可以使用原子类解决: 直接和操作系统挂钩，在内存中修改值，Unsafe类
 */
public class TestAcide {

    //
    //private  volatile static int num = 0;//加了volatile计算结果也不准确
    private  volatile static AtomicInteger num = new AtomicInteger();//原子类

//    public synchronized static void add(){//加synchronized锁可以解决
//        num++;
//    }

    public static void add() {//使用原子类方法
        num.getAndIncrement();//原子类加1 方法  ； 底层使用的是CAS
    }

    //计算结果理论上应该是200000
    public static void main(String[] args) {

        for (int i = 1; i <= 20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "   : " +num);
    }
}
