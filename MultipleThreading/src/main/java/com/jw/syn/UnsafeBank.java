package com.jw.syn;

//模拟银行取款
public class UnsafeBank {

    public static void main(String[] args) {
        Account account = new Account(1000, "耍钱");

        Drawing d1 = new Drawing(account, 50, "小明");
        Drawing d2 = new Drawing(account, 100, "小明老婆");

        d1.start();
        d2.start();
    }
}

class Account{
    int money;//余额
    String name;//卡号

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread{
    Account account;
    int nowMoney;
    int drawingMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    @Override
    public void run() {

        //synchronized代码块，锁的对象是变化的量，即需增删改的量
        synchronized (account){
            if (account.money - drawingMoney < 0 ) {
                System.out.println(Thread.currentThread().getName() + "钱不够了");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //取钱
            account.money = account.money - drawingMoney;
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name + "卡内余额为：" + account.money);
            System.out.println(this.getName() + "手里的钱为：" + nowMoney);
        }

    }
}
