package com.jw.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 输出结果分析
 * a===> MyLock     a 线程拿到锁
 * b===> MyLock     b 线程进入，自旋
 * a===> MyUnLock   a线程释放锁后，
 * b===> MyUnLock   b线程自旋结束，执行释放
 */
public class SpinLockTest {
    public static void main(String[] args) throws InterruptedException {

        SpinLock lock = new SpinLock();

        new Thread(()->{

            lock.MyLock();
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.MyUnLock();
            }
        },"a").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{

            lock.MyLock();
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.MyUnLock();
            }
        },"b").start();
    }
}
