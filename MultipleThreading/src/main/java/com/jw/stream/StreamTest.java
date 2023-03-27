package com.jw.stream;

import com.jw.POJO.User;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 一条语句完成下面的筛选：
 * 1。 id为偶数
 * 2。 年龄大于23
 * 3。 用户名转为大写字母
 * 4。 用户名字倒着排序
 * 5。 只输出一个用户
 */
public class StreamTest {

    public static void main(String[] args) {
        User u1 = new User(1, "a", 32);
        User u2 = new User(2, "b", 12);
        User u3 = new User(3, "c", 50);
        User u4 = new User(4, "d", 26);
        User u5 = new User(5, "e", 37);
        User u6 = new User(6, "f", 77);

        //集合就是存储
        List<User> lists = Arrays.asList(u1, u2, u3, u4, u5,u6);
        //System.out.println(lists);
        //计算交给流;流中的对象为User
        //函数式接口、lambda表达式、链式编程、stream流式计算
        lists.stream().filter(u->u.getId()%2==0)//id为偶数
                .filter(u->u.getAge()>23)       //年龄大于23
                .map(u->u.getName().toUpperCase())//用户名转为大写字母
                .sorted((p1,p2)->p2.compareTo(p1)) //用户名字倒着排序
                .limit(1)//只输出一个用户
                .forEach(System.out::println);  //循环遍历打印

    }
}
