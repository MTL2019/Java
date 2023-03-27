package com.jw.mutithread;

//创建线程方法2： 实现Runnable方法；重写run方法；执行线程需要丢入Runnable接口实现类；调用start方法开启线程
public class CreateThread2 implements Runnable{

    @Override
    public void run() {
        //新线程
        for (int i = 0; i < 20000; i++) {
            System.out.println("我在看代码----" + i);
        }
    }

    public static void main(String[] args) {
        //创建Runnable接口实现类
        CreateThread2 createThread2 = new CreateThread2();

        //创建线程对象,通过线程对象开启我们的线程，代理
//        Thread thread = new Thread(createThread2);
//        thread.start();
        new Thread(createThread2).start();//start函数通过底层函数创建新线程，并回调对应的run方法

        //调用Start()方法开启线程

        for (int i = 0; i < 20000; i++) {
            System.out.println("我在学习多线程---" + i);
        }
    }
}
