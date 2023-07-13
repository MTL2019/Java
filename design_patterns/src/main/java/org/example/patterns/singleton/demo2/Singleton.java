package org.example.patterns.singleton.demo2;

//static code block
public class Singleton {

    //1.Private constructor
    private Singleton (){  }

    //2.Create obj in its class
    private static Singleton instance ;

    //3.static code block
    static{
        instance = new Singleton();
    }

    public static Singleton getInstance(){
        return instance;
    }
}
