package BasicConcepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Auther:JW
 * @Date:2023-01-27 - 01 - 27 - 10:27 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter11_Polymorphism {

    public static void main(String[] args) {

        //ArrayList : generic class with a generic type E. 可存储 通用类对象
        //testArrayList();

        methodsForLists();


    }

    public static void methodsForLists(){
        String[] array = {"red","green","blue"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        System.out.println(list.toString());

        //list to array
        String[] array1 = new String[list.size()];
        list.toArray(array1);
        System.out.println(Arrays.toString(array1));

        //Collections类工具函数
        Integer[] array2 = {3,5,95,4,15,34,3,6,5};
        ArrayList<Integer> list2 =  new ArrayList<>(Arrays.asList(array2));
        Collections.sort(list2);
        System.out.println(list2);
        System.out.println(Collections.max(list2));

        Collections.shuffle(list2);
        System.out.println(list2);
    }

    public static void testArrayList(){

        ArrayList<String> cityList = new ArrayList<>();

        cityList.add("London");
        cityList.add("Denver");
        cityList.add("Paris");
        cityList.add("Miami");
        cityList.add("Seoul");
        cityList.add("Tokyo");

        cityList.add(2,"Montreal");
        cityList.remove("Seoul");
        cityList.remove(0);

        System.out.println(cityList.size());
        System.out.println(cityList.contains("Miami"));
        System.out.println(cityList.indexOf("Denver"));
        System.out.println(cityList.isEmpty());
        System.out.println(cityList.toString());
        System.out.println(cityList.get(1));
        cityList.clear();

        //通用类
        ArrayList<Circle> list = new ArrayList<>();

        list.add(new Circle(2));
        list.add(new Circle(3));

        System.out.println(list.get(0).getArea());
    }
}

class Circle{
    double radius;

    Circle(double radius){
        this.radius = radius;
    }

    public Circle() {

    }

    public double getArea(){
        return 3.14 * radius * radius;
    }
}