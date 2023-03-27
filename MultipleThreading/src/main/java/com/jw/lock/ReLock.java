package com.jw.lock;

/**
 * 使用synchronized：
 * 可重入锁：拿到外面的锁，会自动拿到里面的锁
 * 锁必须配对加锁解锁，否则就会死锁
 */
public class ReLock {
    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(()->{
            phone.sms();
        },"a").start();

        new Thread(()->{
            phone.sms();
        },"b").start();
    }
}

class Phone{
     public synchronized void sms(){
         System.out.println(Thread.currentThread().getName()+ "sms");
         call();//这里有一把锁
     }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+ "call");
    }
}
