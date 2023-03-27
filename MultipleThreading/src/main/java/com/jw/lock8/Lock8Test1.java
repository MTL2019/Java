package com.jw.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：关于锁的8个问题
 * 1。标准情况下，先打印打电话还是发短信？ --》 先发短信，后打电话； 原因：锁的存在
 * 2。发短信方法延时4秒，先打印打电话还是发短信？--> 先发短信，后打电话； 原因：锁的存在
 */
public class Lock8Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

/**
 * 资源类
 * synchronized： 被锁的对象是方法的调用者
 * 两个方法用的是同一个锁（锁的对象相同phone），谁先拿到谁先执行
 */
class Phone{
    public synchronized void sendSms(){

        //测试第2个问题
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }
}
