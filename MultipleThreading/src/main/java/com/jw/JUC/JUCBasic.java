package com.jw.JUC;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JUCBasic {
    public static void main(String[] args) {

    }

    @Test
    public void test02(){
        Ticket ticket = new Ticket();
        //在lambda表达式中调用Ticket对象的方法
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"C").start();
    }

    public void test01(){
        //CPU密集型  IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());

        Thread thread = new Thread();
        //System.out.println(Thread.State.);
        //thread.State;
        try {
            TimeUnit.SECONDS.sleep(10);//企业中使用的多
            System.out.println("sleep with 10 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 卖票案例：使用lock锁
     */
    class Ticket{
        private int ticketNum = 50;

        Lock lock= new ReentrantLock();//使用可重入锁

        public void sale(){
            lock.lock();
            try{
                if (ticketNum > 0) {
                    System.out.println(Thread.currentThread().getName()+ "卖出了" +
                            (ticketNum--) + "剩余：" + ticketNum);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        }

    }
}
