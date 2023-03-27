package com.jw.advance;

//测试生产者消费者模式：利用缓冲区解决:管程法

/**
 * PC : 生产者、消费者
 * 利用缓冲区解决: 管程法
 * 使用synchronized wait / notifyAll 实现
 */

//生产者、消费者、产品、缓冲区
//生产者、消费者：第1中方法创建的多线程类
//缓冲区SynContainer：生产者、消费者的属性，构造时传入同一个缓冲区对象
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Customer(container).start();
    }
}
//生产者
class Productor extends Thread{

    SynContainer container ;
    public Productor(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了第" + i + "只鸡");
        }
    }
}
//消费者
class Customer extends Thread{
    SynContainer container ;
    public Customer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了第" + container.pop().id + "只鸡");
        }
    }
}
//产品
class Chicken{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}


//缓冲区
class SynContainer{

    Chicken[] chickens = new Chicken[10];//容器大小
     int count = 0; //容器计数器

    /**
     * 生产者消费
     * 思路： 等待 -- 业务 -- 通知
     * 为防止虚假唤醒，所有判断从if改为while，循环判断
     */
    //生产者放入产品
    public synchronized void push(Chicken chicken){

        //如果容器满了，则等待消费者消费
        if (count == chickens.length) {
            //生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满，我们需要丢入
        chickens[count] = chicken;
        count++;

        //可以通知消费者消费了
        this.notifyAll();
    }

    /**
     * 消费者消费
     * 思路： 等待 -- 业务 -- 通知
     */
    //消费者消费
    public synchronized Chicken pop(){
        //判断是否可以消费
        if (count == 0) {
            //消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费
        count--;
        Chicken chicken = chickens[count];

        //吃完了，通知生产者生产
        this.notifyAll();
        return chicken;
    }

}
