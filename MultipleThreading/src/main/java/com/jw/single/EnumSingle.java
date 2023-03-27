package com.jw.single;

import java.lang.reflect.Constructor;

//枚举本身也是一个类
//有一个有参构造
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance(){
        return INSTANCE;
    }
}

class Test{
    public static void main(String[] args) throws Exception {

        EnumSingle instance1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();//通过反射创建，测试是否可以避免单例被破坏
        //报错是Cannot reflectively create enum objects  -->  不能通过反射创建枚举类型

        System.out.println(instance1);
        System.out.println(instance2);
    }
}
