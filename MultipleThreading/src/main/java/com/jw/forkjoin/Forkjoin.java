package com.jw.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算
 *
 * 如何使用forkjoin
 * 第一步： 使用forkjoinPool
 * 第二部：任务执行 forkjoinPool.excute(ForkJoinTask task)
 * 第三步：继承RecursiveTask
 */
public class Forkjoin extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private Long temp = 10000L;

    public Forkjoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else
        {//使用forkjoin;递归
            long middle = (end+start)/2;
            Forkjoin task1 = new Forkjoin(start,middle);
            task1.fork();//拆分任务，把线程压入队列
            Forkjoin task2 = new Forkjoin(middle+1,end);
            task2.fork();
            return task1.join()+task2.join();
        }
    }
}
