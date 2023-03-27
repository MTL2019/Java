package com.jw.function;

import java.util.function.Predicate;

/**
 * 函数式接口都可以用lambda表达式简化
 * Predicate: 断定型接口：又一个参数，返回值为布尔值
 */

public class PredicateTest {
    public static void main(String[] args) {

        //判断字符串是否为空
//        Predicate<String> predicate = new Predicate<>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };

        //简化
        Predicate<String> predicate = (s)->s.isEmpty();

        System.out.println(predicate.test(""));
    }
}
