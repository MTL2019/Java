package com.jw.testVolatile;

import java.util.concurrent.TimeUnit;

/**
 * 测试volatile可见性
 */
public class TestVolatile {

    //不加volatile 程序就会死循环
    private volatile static int i = 0;//保证新线程里的i是最新的

    public static void main(String[] args) {

        new Thread(()->{
            while (i == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        i=1;
        System.out.println(i);
    }
}
