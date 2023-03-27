package com.jw.bq;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue 阻塞队列
 */
public class BqTest {
    public static void main(String[] args) throws InterruptedException {

        //test1();
        //test2();
        //test3();
        test4();


    }

    /**
     * 抛出异常
     */
    public static void test1() {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));

        System.out.println(queue.element());//查看对首元素 a
        //抛出异常，队列已满
        //System.out.println(queue.add("d"));

        System.out.println(queue.remove());//先进先出FIFO
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        //抛出异常，没有元素NoSuchElementException
        //System.out.println(queue.remove());
    }

    /**
     * 有返回值，不抛出异常
     */
    public static void test2() {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));

        System.out.println(queue.peek());//查看对首元素 a
        //抛出异常，队列已满
        //System.out.println(queue.offer("d"));//返回false，不抛出异常

        System.out.println(queue.poll());//先进先出FIFO
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        //不抛出异常，//没有元素，弹出null
        System.out.println(queue.poll());
    }

    /**
     * 等待，一直阻塞
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);

        queue.put("a");
        queue.put("b");
        queue.put("c");
        //queue.put("d");//一直等待

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
       //System.out.println(queue.take());//一直等待
    }
    /**
     * 等待，超时退出
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));

        //System.out.println(queue.peek());//查看对首元素 a
        //抛出异常，队列已满
        System.out.println(queue.offer("d",2, TimeUnit.SECONDS));//超时2秒退出

        System.out.println(queue.poll());//先进先出FIFO
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        //不抛出异常，//没有元素，弹出null
        System.out.println(queue.poll(2, TimeUnit.SECONDS));//超时2秒退出
    }
}
