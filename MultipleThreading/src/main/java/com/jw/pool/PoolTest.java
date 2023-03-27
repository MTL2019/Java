package com.jw.pool;

import java.util.concurrent.*;

/**
 *
 */
public class PoolTest {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//固定数目线程
        //ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的，遇强则强，遇弱则弱

        /**
         * 自定义线程池：使用7大参数
         * 实例：银行办业务
         * int corePoolSize,常开的线程数 --》 常开的办理窗口
         * int maximumPoolSize,等候区满了，可以支持的最大线程数  --》 动态开关的窗口
         * long keepAliveTime,超过此时间释放线程池          --》 超时没人就关闭窗口的等待时间
         * TimeUnit unit,时间单位
         * BlockingQueue<Runnable>：等候区队列        --》等候区的座位数
         * ThreadFactory：创建线程池的工厂
         * RejectedExecutionHandler：拒绝策略         --》当等候区也满了，还有人进来时的拒绝策略：4种
         *         AbortPolicy：拒绝处理，抛出异常
         *         CallerRunsPolicy:哪来的去哪里； 谁调用的由谁处理
         *         DiscardPolicy:队列满了，丢弃任务，不会抛出异常
         *         DiscardOldestPolicy：队列满了，和最早的线程竞争，失败就丢弃，成功就执行，不会抛出异常
         */
        //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors(),//CPU密集型，设置为电脑核数
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(()->{//有线程池后，要用线程池创建线程
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } finally {
            threadPool.shutdown();//关闭线程池
        }
    }
}
