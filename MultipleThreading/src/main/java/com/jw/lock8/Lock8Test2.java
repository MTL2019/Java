package com.jw.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：关于锁的8个问题
 * 1。标准情况下，先打印打电话还是发短信？ --》 先发短信，后打电话； 原因：锁的存在（synchronized： 被锁的对象是方法的调用者）
 * 2。发短信方法延时4秒，先打印打电话还是发短信？--> 先发短信，后打电话； 原因：锁的存在（synchronized： 被锁的对象是方法的调用者）
 * 3. 存在非synchronized方法hello,先打印打电话还是发短信？ --》  先打印hello  原因：hello没有锁
 * 4。两个对象，两个同步方法,先打印打电话还是发短信？  -- 》phone2先执行打印打电话  原因： 两个调用者，两把锁
 */
public class Lock8Test2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        Phone2 phone1 = new Phone2(); //测试第4个问题

        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();

        new Thread(() -> {
            phone.hello();
        }, "C").start();

        new Thread(() -> {
            System.out.println("phone1对象 -- 2。打电话");
            phone1.call();
        }, "D").start();
    }
}

/**
 * 资源类
 * synchronized： 被锁的对象是方法的调用者
 * 两个方法用的是同一个锁（锁的对象相同phone），谁先拿到谁先执行
 */
class Phone2{
    public synchronized void sendSms(){

        //测试第2个问题
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("1。发短信");
    }

    public synchronized void call(){
        System.out.println("2。打电话");
    }

    //这里没有锁，不受锁的影响，不是同步方法
    public  void hello(){
        System.out.println("3。问候");
    }
}
