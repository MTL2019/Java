package com.jw;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> temp = createPascalTriangle(5);

        Integer[][] actual = new Integer[temp.size()][];
        Integer[] blankArray = new Integer[0];

        for(int i = 0; i < temp.size(); i++) {
            actual[i] = temp.get(i).toArray(blankArray);
        }

        System.out.println(Arrays.deepToString(actual));
    }



    public static ArrayList<ArrayList<Integer>> createPascalTriangle(int numRows) {


        // numRows = 5
        // [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

        // numRows = 1
        // [1]
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < numRows; i++) {

            ArrayList element = new ArrayList<Integer>();

            if (i == 0) {
                element.add(1);
            }else {

                for (int k = 0; k < i + 1; k++) {
                    if (k == 0 || k == i) {
                        element.add(1);
                    }else {
                        int sum = arrayLists.get(i - 1).get(k) + arrayLists.get(i - 1).get(k - 1);
                        element.add(sum);
                    }
                }
            }

            arrayLists.add(element);
        }

        return arrayLists;
    }
}
