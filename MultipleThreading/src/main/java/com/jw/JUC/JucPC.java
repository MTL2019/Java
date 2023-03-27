package com.jw.JUC;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JucPC {
    public static void main(String[] args) {

    }

    @Test
    public void test02() {
        Ticket ticket = new Ticket();
        //在lambda表达式中调用Ticket对象的方法
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    ticket.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    ticket.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    ticket.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    ticket.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
    /**
     * 卖票案例：使用lock锁
     */
class Ticket {
        private int ticketNum = 50;

        Lock lock = new ReentrantLock();//使用可重入锁
        Condition condition = lock.newCondition();//条件判断代理wait/

        //生产者
        public void increment() throws InterruptedException {

            lock.lock();

            try {
                while (ticketNum != 0) {
                    condition.await();
                }
                ticketNum++;
                System.out.println(Thread.currentThread().getName() + " ---> " + ticketNum);
                condition.signalAll();//通知
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        //消费者
        public void decrement() throws InterruptedException {

            lock.lock();

            try {
                while (ticketNum == 0) {
                    condition.await();
                }
                ticketNum--;
                System.out.println(Thread.currentThread().getName() + " ---> " + ticketNum);
                condition.signalAll();//通知

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
}

