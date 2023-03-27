package com.jw.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁：写锁：一次只能被一个线程占用
 * 共享锁：读锁：多个线程可以同时占用
 * ReadWriteLock
 * 读可以被多个线程读,写只能由1个线程写入
 *  读-读：多线程可以共存
 *  读-写：多线程不能共存
 *  写-写：多线程不能共存
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        //MyCache myCache = new MyCache();//不加锁写入时会被插队
        MyCacheLock myCache = new MyCacheLock();

        //写入
        for (int i = 1; i <= 6 ; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        //读取
        for (int i = 1; i <= 6 ; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}


/**
 * 加锁的缓存
 */
class MyCacheLock{
    private volatile Map<String,Object> map = new HashMap<>();
    //读写锁：更加细粒度的控制 --> 读写加不同类的锁，实现读写不同的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存，写入： 写入的时候不能被其他的线程打断，插入；
    public void put(String key,Object value){

        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    //取
    public void get(String key){

        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取OK,值为："+ result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}

/**
 * 自定义缓存
 */
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    //存，写入： 写入的时候不能被其他的线程打断，插入；
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入OK");
    }
    //取
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK,值为："+ result);
    }
}
