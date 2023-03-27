package com.jw.single;

//恶汉式单例: 可能浪费内存资源
public class Hungry {

    private Hungry(){

    }
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
