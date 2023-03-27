package com.jw.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加法计数器
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {

        //集齐7颗龙珠召唤神龙；等待7个线程执行完，响应执行后面的方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！");
        });

        for (int i = 1; i <= 7 ; i++) {
            final int temp = i;//目的是让lambda表达式类里面可以获取i的值
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ "收到了"+temp+"颗龙珠");

                try {
                    cyclicBarrier.await();//等待7个线程执行完成，就跳转到runable方法System.out.println("召唤神龙成功！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
