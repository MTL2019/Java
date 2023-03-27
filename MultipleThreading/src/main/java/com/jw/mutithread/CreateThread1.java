package com.jw.mutithread;

//创建线程方法1： 继承Thread类；重写run方法；调用start方法开启线程
//不推荐这种方法，最好把业务和线程管理分开
public class CreateThread1 extends Thread{
    @Override
    public void run() {
        //新线程
        for (int i = 0; i < 20000; i++) {
            System.out.println("我在看代码----" + i);
        }
    }

    public static void main(String[] args) {
        //main线程，主线程

        //创建一个线程对象
        CreateThread1 createThread1 = new CreateThread1();

        //调用Start()方法开启线程
        createThread1.start();

        for (int i = 0; i < 20000; i++) {
            System.out.println("我在学习多线程---" + i);
        }
    }
}
