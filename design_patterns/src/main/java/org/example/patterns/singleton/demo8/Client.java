package org.example.patterns.singleton.demo8;

import org.example.patterns.singleton.demo7.Singleton;

import java.io.*;
import java.lang.reflect.Constructor;

//Test inflection to destroy singleton
public class Client {

    public static void main(String[] args) throws Exception {

        //1.Get Singleton binary obj
        Class clazz = Singleton.class;

        //2.get constructor without args
        Constructor cons = clazz.getDeclaredConstructor();
        //3. Cancel visit check
        cons.setAccessible(true);

        //4. Create obj by inflection
        Singleton s1 = (Singleton) cons.newInstance();
        Singleton s2 = (Singleton) cons.newInstance();
        System.out.println(s1 == s2);//false : destroy singleton

    }

}
