package com.jw.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 学习步骤：
 * 1.  会用
 * 2。 货比三家
 * 3。 探究原理
 *
 * callable
 * 1。 有缓存；如果返回值为空才计算，否则从缓存中拿了返回
 * 2。 结果需要等待，可能会阻塞
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //new Thread(new Runable()).start();
        //new Thread(new FutureTask<V>()).start();等价于上面，FutureTask是Runable的实现类
        //new Thread(new FutureTask<V>(Callable)).start();
        MyThread myThread = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<>(myThread);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();//结果会被缓存，提高效率

        //get方法可能产生阻塞，因为他要等待结果返回
        //把get方法放到最后，或者使用异步通信
        Integer integer = (Integer) futureTask.get();//获取Callable的返回结果
        System.out.println(integer);
    }
}

class MyThread implements Callable<Integer> {

    //Callable<String>的范型是call方法的返回值
    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 123;
    }
}
