package com.jw.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同理如List:  并发写入异常 java.util.ConcurrentModificationException
 * 解决方案2: Collections.synchronizedList(new ArrayList<>());
 * 解决方案3: List<String> list = new CopyOnWriteArrayList<>();;
 */
public class SetList {
    public static void main(String[] args) {
        //Set<String> set = new HashSet<>();
        //1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 900; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();

        }
    }
}
