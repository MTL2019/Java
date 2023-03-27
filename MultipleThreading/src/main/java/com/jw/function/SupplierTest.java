package com.jw.function;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Supplier:供给型接口; 没有输入，只有返回值
 */
public class SupplierTest {
    public static void main(String[] args) {

        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "null";
            }
        };

        Supplier<String> supplier1 =()-> "str";

        System.out.println(supplier1.get());
    }

}
