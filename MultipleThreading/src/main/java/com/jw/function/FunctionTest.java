package com.jw.function;

import java.util.function.Function;

/**
 * 函数式接口都可以用lambda表达式简化
 * Function: 函数型接口：第1个参数为入参，与apply方法形参匹配；第2个为返回值，与apply方法返回值匹配
 */
public class FunctionTest {
    public static void main(String[] args) {

        //工具类：输出输入的值
        //第1个参数为入参，与apply方法形参匹配；第2个为返回值，与apply方法返回值匹配
//        Function<String, String> function = new Function<>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };
        //简化写法,与上面功能相同
        //Function<String, String> function = (str)-> {return str;};
        //再简化
        Function<String, String> function = (str)-> str;

        System.out.println(function.apply("input"));
    }

}
