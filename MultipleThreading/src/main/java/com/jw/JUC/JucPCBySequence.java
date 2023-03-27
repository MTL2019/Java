package com.jw.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition类，实现精准通知，顺序调用线程执行
 */
public class JucPCBySequence {
    public static void main(String[] args) {

        Data data = new Data();
        new Thread(() -> {for (int i = 0; i < 30; i++) data.pringA();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 30; i++) data.pringB();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 30; i++) data.pringC();}, "C").start();
    }

    /**
     * 资源类
     */
static class Data {

        private Lock lock = new ReentrantLock();//使用可重入锁
        private Condition condition1 = lock.newCondition();//添加监视器
        private Condition condition2 = lock.newCondition();//添加监视器
        private Condition condition3 = lock.newCondition();//添加监视器

        private int num = 1; // 1:A  2:B  3:C

        /**
         * 使用Condition，实现精准通知,唤醒指定的线程
         * 业务流程： 判断 、 执行、 唤醒
         */
        public void pringA(){
            lock.lock();

            try {
                while (num!=1){
                    condition1.await();
                }
                num = 2;
                System.out.println(Thread.currentThread().getName() + "----> A");
                //精准通知,唤醒指定的线程
                condition2.signal();

            } catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void pringB(){
            lock.lock();

            try {
                while (num!=2){
                    condition2.await();
                }
                num = 3;
                System.out.println(Thread.currentThread().getName() + "----> B");
                //精准通知
                condition3.signal();

            } catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void pringC(){
            lock.lock();

            try {
                while (num!=3){
                    condition3.await();
                }
                num = 1;
                System.out.println(Thread.currentThread().getName() + "----> C");
                //精准通知
                condition1.signal();

            } catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}

