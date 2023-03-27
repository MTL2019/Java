package com.jw.mutithread;

//多个线程使用同一个对象
//抢火车票的例子
//多个线程操作同一个资源，两人抢到同一张票，数据紊乱，线程不安全
public class TestTread2 implements Runnable{

    private int ticketNums = 10;

    @Override
    public void run() {

        while (true){
            if (ticketNums <= 0 ) {
                break;
            }
            //模拟演示
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--> 买到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestTread2 ticket = new TestTread2();

        new Thread(ticket,"小明").start();
        new Thread(ticket,"老师").start();
        new Thread(ticket,"黄牛").start();
    }
}
