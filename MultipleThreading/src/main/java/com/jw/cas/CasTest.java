package com.jw.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS: compareAndSet -->  CPU的并发原语
 *  compareAndSet(int expectedValue, int newValue):
 *  如果达到期望expectedValue，就更新值为newValue; 否则就不更新
 *
 *  java可以通过Undafe类操作内存； 正常情况下只能通过c++操作内存
 *
 *  ABA问题：自己的线程，并不知道主内存中的值2020是被人动过了的；捣乱的线程先改写，再改回原值
 */
public class CasTest {

    public static void main(String[] args) {
        //testCAS();

        /**
         * Integer: 使用了对象缓存机制，默认是-128～127，推荐使用静态工厂方法valueOf获得对象实例，而不是new；
         * 因为valueOf使用缓存，而new一定会创建新的对象并分配新的内存空间；所以下述值设置太大，测试不ok
         */
        //注意：如果范型是包装类，要注意对象引用问题
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("a1:  " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1,2,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1));
            System.out.println("a2:  " + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2,1,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1));
            System.out.println("a3:  " + atomicStampedReference.getStamp());

        },"a").start();

        //和乐观锁的原理相同
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("b1:  " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1,6,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1));
            System.out.println("b2:  " + atomicStampedReference.getStamp());


        },"b").start();

    }

    private static void testCAS() {

        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //捣乱的线程
        System.out.println(atomicInteger.compareAndSet(2020,2021));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2021,2020));
        System.out.println(atomicInteger.get());

        //ABA问题：自己的线程，并不知道2020是被人动过了的
        System.out.println(atomicInteger.compareAndSet(2020,6666));
        System.out.println(atomicInteger.get());

        //对于平时写的sql： 乐观锁
    }
}
