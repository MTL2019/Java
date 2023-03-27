package com.jw.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：关于锁的8个问题
 * 1。标准情况下，先打印打电话还是发短信？ --》 先发短信，后打电话； 原因：锁的存在（synchronized： 被锁的对象是方法的调用者）
 * 2。发短信方法延时4秒，先打印打电话还是发短信？--> 先发短信，后打电话； 原因：锁的存在（synchronized： 被锁的对象是方法的调用者）
 * 3. 存在非synchronized方法hello,先打印打电话还是发短信？ --》  先打印hello  原因：hello没有锁
 * 4。两个对象，两个同步方法,先打印打电话还是发短信？  -- 》phone2先执行打印打电话  原因： 两个调用者，两把锁
 * 5。增加两个静态的同步方法，一个对象，先打印打电话还是发短信？ --》先发短信，后打电话； 原因： statis同步方法锁的是Class类，只有一个
 * 6. 两个静态的同步方法，两个对象，先打印打电话还是发短信？ --》先发短信，后打电话；原因： statis同步方法锁的是Class类，只有一个
 * 7. 一个普通同步方法，1个对象,先打印打电话还是发短信？ --》先打电话，后发短信；原因：两把锁，锁的对象不同
 * 8。一个普通同步方法，2个对象,先打印打电话还是发短信？ --》先打电话，后发短信；原因：两把锁，锁的对象不同
 */
public class Lock8Test4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();//测试第8个问题

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
            phone2.call();
        }, "C").start();


    }
}

/**
 * 资源类
 * synchronized： 被锁的对象是方法的调用者
 * 两个方法用的是同一个锁（锁的对象相同phone），谁先拿到谁先执行
 * static方法在类一加载就有了，锁的是Class；Class对象唯一
 */
class Phone4{
    //静态同步方法：锁的是Class模版
    public static synchronized void sendSms(){

        //测试第2个问题
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("1。发短信");
    }

    //普通同步方法，没有static,锁的是对象
    public synchronized void call(){
        System.out.println("2。打电话");
    }

}
