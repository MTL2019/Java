package com.jw.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLock {

    AtomicReference atomicReference = new AtomicReference<>();

    public void MyLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"===> MyLock");

        //自旋锁 加锁
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void MyUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"===> MyUnLock");

        //解锁
        atomicReference.compareAndSet(thread,null);
    }
}
