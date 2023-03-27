package com.jw;

//测试lambda表达式

/**
 * lambda表达式本质：一个实现了一个函数式接口的类的简写；函数式接口如Runable接口，只有一个方法的接口
 * 例如 new Thread(()->{System.out.println("hello")}).start();
 * 解释：()->{System.out.println("hello")} 表示匿名类，该类实现了Runable接口，并重写了run方法，方法体为lambda表达式的大括号内的语句；
 * 总结：lambda表达式可以实现任何的函数式接口，并重写其唯一的方法
 */
//1. 定义一个函数式接口
interface ILike{
    void lambda(int a,int b);
}

//2。实现函数式接口
class Like implements ILike{
    @Override
    public void lambda(int a,int b) {
        System.out.println("I like Lambda");
    }
}

public class TestLambda {

    //3。 为了简化，将外部类移到内部，成为静态内部类
    static class Like3 implements ILike{
        @Override
        public void lambda(int a,int b) {
            System.out.println("I like Lambda3");
        }
    }

    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda(1,2);

        like = new Like3();
        like.lambda(1,3);

        //4。 为了简化，将静态内部类移到方法内部，成为局部内部类
        class Like4 implements ILike{
            @Override
            public void lambda(int a,int b) {
                System.out.println("I like Lambda4");
            }
        }

        like = new Like4();
        like.lambda(1,4);

        //5。 匿名内部类，没有类名，必须借助接口或父类
        like = new ILike() {
            @Override
            public void lambda(int a,int b) {
                System.out.println("I like Lambda5");
            }
        };
        like.lambda(1,5);

        //6。 JDK8后，lambda表达式再简化;因为函数式接口中接口和方法都只有一个，一一对应；
        // 所以就去掉接口名字、函数名字，将函数参数列表后面 用 -> 指向函数体 即可
        like = (a,b)->{
                System.out.println("I like Lambda6");//相当于是重写接口中lambda()方法的函数体
        };
        like.lambda(1,2);//所以调用lambda()才会打印lambda6

        //7。 再简化；
        // 规则：
        // 1。参数列表可以省略参数类型；多个参数时都一起省略，加（）包裹
        // 2. 函数题有多行语句时，大括号不能省；一条语句时可以省略
        // 3。 接口必须是函数式接口
        like = (a,b)->{
            System.out.println("I like Lambda7 : " + a + b);//相当于是重写接口中lambda()方法的函数体
        };
        like.lambda(1,7);
    }
}

