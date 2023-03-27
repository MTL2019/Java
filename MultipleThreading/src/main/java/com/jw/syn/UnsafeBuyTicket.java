package com.jw.syn;

//不安全买票
//线程不安全，有负数
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station,"小明").start();
        new Thread(station,"警察").start();
        new Thread(station,"黄牛").start();
    }

}

class BuyTicket implements Runnable{

    //票
    private int ticketNums = 10;
    private Boolean flag = true;//标志位，外部停止方式

    @Override
    public void run() {
        while (flag){
            //买票
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //synchronized:同步方法；锁的是this对象，要有锁才能执行
    private synchronized void buy() throws InterruptedException {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "买到了第" + ticketNums-- + "张票");
    }
}
