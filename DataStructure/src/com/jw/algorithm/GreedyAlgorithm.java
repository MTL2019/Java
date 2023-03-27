package com.jw.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 * broadcasts ： 电台K1\K2\K3的集合，不同电台覆盖部分地区
 * allArea : 所有电台未覆盖的地区，初始为所有地区，后面根据选择，不断剔除
 * 原理：遍历broadcasts所有电台，统计各电台包含未覆盖地区的数目，选择最多数目的电台加入到selects,并删除allArea对应的地区；直到allArea为空
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {

        HashMap<String, HashSet> broadcasts = new HashMap<>();//电台的集合

        HashSet<String> hashSet1 = new HashSet<>();//存放单个电台所覆盖的地区字符串
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashSet<String> hashSet4 = new HashSet<>();
        HashSet<String> hashSet5 = new HashSet<>();

        HashSet<String> allAreas = new HashSet<>();//存放所有电台所覆盖的地区字符串，不重复

        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("深圳");
        hashSet5.add("杭州");
        hashSet5.add("大连");
        hashSet4.add("上海");
        hashSet4.add("天津");

        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("深圳");
        allAreas.add("广州");
        allAreas.add("成都");
        allAreas.add("大连");
        allAreas.add("天津");
        allAreas.add("杭州");

        //加入到map  电台合集
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        ArrayList<String> selects = new ArrayList<>();//存放选择电台的集合，最终覆盖所有地区的最少的集合
        HashSet<String> tempSet = new HashSet<>();//临时集合，存放遍历过程中的电台覆盖的地区和还没有覆盖的地区的交集
        String maxKey = null;//定义最大未覆盖的地区对应的电台的key，当不为空时，要添加到selects中
        
        while(allAreas.size() != 0){
            maxKey = null;//为下次作准备***
            for (String key: broadcasts.keySet()) {

                tempSet.clear();//每次循环也需要清空

                HashSet areas = broadcasts.get(key);//当前key所覆盖的地区集合
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);//求当前key的覆盖地区和所有需要选中地区的交集

                //如果交集非空，maxKey为空或交集大于maxKey对应的覆盖地区，则更新maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);//将最大未覆盖地区的电台加入到选择数组中
                allAreas.removeAll(broadcasts.get(maxKey));//将选中的电台覆盖的地区，从所有地区中移除
            }
        }

        System.out.println(selects);
    }
}
