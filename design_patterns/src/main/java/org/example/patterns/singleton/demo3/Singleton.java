package org.example.patterns.singleton.demo3;

//Lazy
public class Singleton {

    //1.Private constructor
    private Singleton (){  }

    //2.Create obj in its class
    private static Singleton instance ;

    //Public visit method
    public static synchronized Singleton getInstance(){

        if(instance == null ){
            //多线程不安全;
            //线程安全要 在函数名上 加synchronized
            instance = new Singleton();
        }
        return instance;

    }
}
