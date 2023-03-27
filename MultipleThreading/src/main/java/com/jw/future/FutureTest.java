package com.jw.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Ajax
 * 异步请求
 * 成功回调
 * 失败回调
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //runAsynWithoutReturn();//没有返回值的异步回调，runAsync

        supplyAsyncWithReturn();//有返回值的异步回调，supplyAsync
    }

    private static void supplyAsyncWithReturn() throws InterruptedException, ExecutionException {
        ////有返回值的异步回调，supplyAsync
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"  supplyAsync => Integer ");
            int in = 1/0;
            return 200;
        });

        System.out.println(completableFuture.whenComplete((t,u)->{
            System.out.println(" t  => " + t);//正常的返回结果
            System.out.println(" u  => " + u);//错误的错误信息
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 404;//可以获取到错误时的返回结果
        }).get());
    }


    private static void runAsynWithoutReturn() throws InterruptedException, ExecutionException {
        //没有返回值的异步回调，runAsync
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  Async=>void ");
        });

        System.out.println("1111111");
        completableFuture.get();//获取执行结果，会阻塞等待结果
    }
}
