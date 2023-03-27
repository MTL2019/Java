package com.jw.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式单例，需要时创建
 */
public class LazyMan {

    //private static LazyMan lazyMan;

    //    public static LazyMan getInstance(){
//        if(lazyMan == null){
//            lazyMan = new LazyMan();
//        }
//        return lazyMan;
//    }
    private static boolean flag = false;
    private LazyMan(){
        //System.out.println(Thread.currentThread().getName()+ "ok");
        synchronized (LazyMan.class){
            if (flag == false) {        //加flag防止两个都由反射创建对象的不一致问题
                flag = true;
            }else{
                throw new RuntimeException("不要试图使用反射破坏单例");
            }
        }
    }

    private volatile static LazyMan lazyMan;//所以必须加volatile
    //双重锁检测模式 懒汉式单例 --> DCL 懒汉式单例
    public static LazyMan getInstance(){

        if(lazyMan == null){
            synchronized (LazyMan.class){
                if(lazyMan == null){
                    lazyMan = new LazyMan();//不是原子性操作
                    /**
                     * new LazyMan()创建步骤
                     * 1。 分配内存空间
                     * 2。 执行构造方法，初始化对象
                     * 3。 把这个对象指向这个空间
                     * 有可能指令重排； 多线程可能导致对象还没创建好就返回使用了
                     * ！！ 所以必须加volatile避免指令重排
                     */
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws Exception {
        //mutilThreadDo();

        //反射: 会破坏单例
        Field flag = LazyMan.class.getDeclaredField("flag");
        flag.setAccessible(true);//通过反射改变flag的值

        //LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);//无视构造器的私有属性
        LazyMan instance2 = declaredConstructor.newInstance();

        flag.set(instance2,false);//破坏构造函数中的flag检测

        LazyMan instance3 = declaredConstructor.newInstance();

        //System.out.println(instance);//getInstance() 和反射创建的对象不一样
        System.out.println(instance2);//两个对象不一样
        System.out.println(instance3);//两个都由反射创建的对象也不一样
    }

    private static void mutilThreadDo() {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                LazyMan.getInstance();
            }).start();
        }
    }
}
