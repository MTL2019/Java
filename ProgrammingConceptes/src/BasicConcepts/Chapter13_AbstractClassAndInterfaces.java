package BasicConcepts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @Auther:JW
 * @Date:2023-02-03 - 02 - 03 - 9:30 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter13_AbstractClassAndInterfaces {
    public static void main(String[] args){

        //test abstract class Number
        ArrayList<Number> list = new ArrayList<>();
        list.add(45);
        list.add(4325.87);
        list.add(new BigInteger("375647583764562"));
        list.add(new BigDecimal("2.0956473643758374462"));

        System.out.println(getLargestNumber(list));
    }

    public static Number getLargestNumber(ArrayList<Number> list){
        if (list == null || list.size() == 0) {
            return null;
        }

        Number number = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (number.doubleValue() < list.get(i).doubleValue()) {
                number = list.get(i);
            }
        }
        return number;
    }
}
