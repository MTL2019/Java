package com.jw.util;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器
 * 倒计时记数
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //有必须要执行的任务的时候使用
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "go out");
                countDownLatch.countDown();//计数-1
            }).start();
        }

        countDownLatch.await();//等待计数器归0再向下执行
        System.out.println("close the door");
    }
}
