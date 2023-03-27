package com.jw.staticproxy;

//静态代理总结
//1. 代理角色和真实角色都要是实现同一接口
//2. 代理对象要代理真实角色

//好处：
//代理角色可以做很多真实角色做不了的事情
//真实角色可以专注做自己要代理的事情
public class StaticProxy {

    public static void main(String[] args) {


//        MarryCompany marryCompany = new MarryCompany(new You());
//        marryCompany.HappyMarry();
        new MarryCompany(new You()).HappyMarry();//创建代理角色代理结婚

        //类似上面静态代理，Thread也是自定义线程的代理；start方法是共同实现的run方法
        //new Thread(()-> System.out.println("我爱你")).start();
    }

}


interface Marry{
    void HappyMarry();
}

class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("我结婚了");
    }
}

class MarryCompany implements Marry{

    private Marry target;//接口对象可以指向其实例化类的对象

    public MarryCompany(Marry target){
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();//结婚动作前后可插入代理公司其他动作
        this.target.HappyMarry();//通过调用构造器传入的结婚本人对象，完成结婚动作
        after();//结婚动作前后可插入代理公司其他动作
    }

    private void before(){
        System.out.println("结婚前，布置现场");
    }

    private void after(){
        System.out.println("结婚后，收尾款");
    }
}
