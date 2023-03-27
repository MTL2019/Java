package com.jw;

//测试Stop线程方法
//建议线程正常停止，使用次数，不建议使用死循环
//建议使用标志位
//不建议使用stop/destroy等过时方法

public class TestThreadState implements Runnable{

    private Boolean flag = true;//标志位

    @Override
    public void run() {
        int i=0;
        while (flag){
            System.out.println("线程被执行了" + i++ + "次");
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestThreadState testThreadState = new TestThreadState();

        new Thread(testThreadState).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main : " + i);
            if (i == 900) {
                testThreadState.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
