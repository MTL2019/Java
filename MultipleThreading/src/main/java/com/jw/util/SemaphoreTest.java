package com.jw.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * semaphore.acquire()： 获得资源；如果满了，等到直到获得资源位置 -1
 * semaphore.release()：释放，会将当前信号量释放 +1；然后唤醒等待的线程
 * 作用：1。 多个共享资源的互斥的使用； 2。 并发限流，保证最大的线程数
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //线程数：停车位，限流！
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//抢车位；
                    System.out.println(Thread.currentThread().getName()+ "抢到了车位");
                    TimeUnit.SECONDS.sleep(2);//停车2秒
                    System.out.println(Thread.currentThread().getName()+ "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放车位
                }
            }).start();
        }
    }
}
