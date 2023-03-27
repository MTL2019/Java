package com.jw.single;

//静态内部类实现单例
public class Holder {

    private Holder(){

    }

    public static Holder getInstance(){
        return InnerHolder.HOLDER;
    }
    private static class InnerHolder{
        private static final Holder HOLDER= new Holder();
    }
}
