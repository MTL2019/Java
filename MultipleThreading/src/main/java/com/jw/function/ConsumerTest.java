package com.jw.function;

import java.util.function.Consumer;

/**
 * consumer:消费型接口;只有输入，没有返回值
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<>() {

            @Override
            public void accept(String o) {
                System.out.println(o);
            }
        };

        Consumer<String> consumer1 =(str)-> System.out.println(str);

        consumer1.accept("abd");
    }

}
