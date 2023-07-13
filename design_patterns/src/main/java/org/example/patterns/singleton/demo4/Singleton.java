package org.example.patterns.singleton.demo4;

//Lazy
public class Singleton {

    //1.Private constructor
    private Singleton(){  }

    //2.Create obj in its class
    //volatile : ensure visible and orderliness
    private static volatile Singleton instance ;

    //Public visit method
    public static Singleton getInstance(){

        if(instance == null ){ //instance != null 时不需要抢占锁
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;

    }
}
