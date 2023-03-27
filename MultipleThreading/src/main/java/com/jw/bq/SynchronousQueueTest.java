package com.jw.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * SynchronousQueue不存储元素，只能写一个取一个；实测可以连续写入2个？
 */
public class SynchronousQueueTest {

    public static void main(String[] args) {
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"  put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"  put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"  put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"  get  "+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"  get  "+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"  get  "+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
