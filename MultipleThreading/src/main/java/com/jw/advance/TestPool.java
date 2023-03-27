package com.jw.advance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//测试线程池
public class TestPool {
    public static void main(String[] args) {
        //创建服务，创建线程池；参数为池子大小
        ExecutorService pool = Executors.newFixedThreadPool(10);

        //执行
        //excute执行Runnable接口的，无返回值
        //submit执行callable接口的，有返回值
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());

        //关闭
        pool.shutdown();
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {

            System.out.println(Thread.currentThread().getName() );

    }
}