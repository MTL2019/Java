package org.example.patterns.singleton.demo1;

//static var
public class Singleton {

    //1.Private constructor
    private Singleton (){  }

    //2.Create obj in its class
    private static Singleton instance = new Singleton();

    //3.Public visit method
    public static Singleton getInstance(){
        return instance;
    }
}
