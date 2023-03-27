package com.jw.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock：真正的两把锁
 * 可重入锁：拿到外面的锁，会自动拿到里面的锁
 * 锁必须配对加锁解锁，否则就会死锁
 */
public class ReLockWithReenLock {

    public static void main(String[] args) {

        Phone2 phone = new Phone2();

        new Thread(()->{
            phone.sms();
        },"a").start();

        new Thread(()->{
            phone.sms();
        },"b").start();
    }
}

class Phone2{

    ReentrantLock lock = new ReentrantLock();

    public  void sms(){
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+ "sms");
            call();//这里有一把锁
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void call(){
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+ "call");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
