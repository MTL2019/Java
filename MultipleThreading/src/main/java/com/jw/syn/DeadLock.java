package com.jw.syn;

//死锁：多个线程互相抱着对方需要的资源，形成僵持
public class DeadLock {
    public static void main(String[] args) {
        Makeup m1 = new Makeup(0, "灰姑娘");
        Makeup m2 = new Makeup(1, "白雪公主");

        m1.start();
        m2.start();
    }
}

//口红
class Lipstick{

}
//镜子
class Mirror{

}

class Makeup extends Thread{

    //static：保持两个资源只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;//选择
    String name;//化妆的人

    Makeup(int choice, String name){
        this.choice = choice;
        this.name = name;

    }
    @Override
    public void run() {
       //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //化妆：互相持有对方的锁，还需要对方的资源
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            //在拿到一把锁的时候，还想要对方的锁，就会卡死；把拿第二把锁的代码块拿到第一个代码块外面就可以解决
//            synchronized (lipstick){//获得口红的锁
//                System.out.println(this.name + "持有口红的锁");
//                Thread.sleep(1000);
//                synchronized (mirror){//一秒后想要镜子的锁
//                    System.out.println(this.name + "持有镜子的锁");
//                }
//            }
            synchronized (lipstick){//获得口红的锁
                System.out.println(this.name + "持有口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror){//一秒后想要镜子的锁
                System.out.println(this.name + "持有镜子的锁");
            }
        }else{
            synchronized (mirror){//获得镜子的锁
                System.out.println(this.name + "持有镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick){//一秒后想要口红的锁
                System.out.println(this.name + "持有口红的锁");
            }
        }
    }
}