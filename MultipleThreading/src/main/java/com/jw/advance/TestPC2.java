package com.jw.advance;

/**
 * PC : 生产者、消费者
 * 利用缓冲区解决: 信号灯法
 * 使用synchronized wait / notifyAll 实现
 */

//测试生产者消费者模式2：信号灯法
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}
//演员
class Player extends Thread{
     TV tv;
    public Player(TV tv){
        this.tv = tv;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 20; i++)
        {
            if (i % 2 == 0)
            {
                this.tv.play("快乐大本营");
            }else
            {
                this.tv.play("广告");
            }
        }
    }
}
//观众
class Watcher extends Thread{

     TV tv;
    public Watcher(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}
//节目
class TV{
    //演员表演，观众等待 true；
    // 观众观看，演员等待  false
    String voice;
    Boolean flag = true;

    public synchronized void play(String voice){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了：" + voice);

        //通知观众观看
        this.notifyAll();
        this.voice = voice;
        this.flag = !this.flag;
    }

    public synchronized void watch(){
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了：" + voice);

        //通知演员表演
        this.notifyAll();
        this.flag = !this.flag;
    }
}
