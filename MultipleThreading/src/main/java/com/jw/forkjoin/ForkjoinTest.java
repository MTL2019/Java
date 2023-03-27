package com.jw.forkjoin;

import java.util.LongSummaryStatistics;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 求和计算
 *
 * 如何使用forkjoin
 * 第一步： 使用forkjoinPool
 * 第二部：任务执行 forkjoinPool.excute(ForkJoinTask task)
 * 第三步：继承RecursiveTask
 */
public class ForkjoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test3();
        test1();

    }

    public static void test1(){
        long start = System.currentTimeMillis();

        Long sum = 0L;
        for (Long i = 1L; i <= 10_000_000L; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum= "+ sum+ "    时间："+ (end-start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask task = new Forkjoin(0L, 10_1000_1000L);
        ForkJoinTask<Long> sum = forkJoinPool.submit(task);
        Long re = sum.get();

        long end = System.currentTimeMillis();
        System.out.println("sum= "+ re+ "  时间："+ (end-start));
    }

    public static void test3(){
        long start = System.currentTimeMillis();

        //并行流
        long sum = LongStream.rangeClosed(1L, 10_1000_1000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum= "+ sum + "   时间："+ (end-start));
    }
}
