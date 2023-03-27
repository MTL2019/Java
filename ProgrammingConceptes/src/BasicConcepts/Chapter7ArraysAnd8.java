package BasicConcepts;

import java.util.Arrays;

/**
 * @Auther:JW
 * @Date:2023-01-27 - 01 - 27 - 10:27 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter7ArraysAnd8 {

    public static void main(String[] args) {

//        boolean[] tag  = new boolean[2];
//        System.out.println(tag[0]);//default value is false

        //for each
//        int[] myNumber = new int[10];
//        for (int e: myNumber) {
//            System.out.println(e);
//        }

        //copy array
        //buildInCopyArrayMethod();

        //examples
        //deckOfCards();

        //Class Arrays
       //displayArrays();

        //deep copy and shalow copy
        //testDeepAndShalowCopy();

    }

    private static void testDeepAndShalowCopy() {
        int[] num1 = {1,2,3};

        int[] num2 = Arrays.copyOf(num1, num1.length);
        System.out.println(Arrays.toString(num2));

        int[] num3 = Arrays.copyOf(num1, 2);//exclude index
        System.out.println(Arrays.toString(num3));

        int[] num4 = Arrays.copyOf(num1, 5);//exclude index
        System.out.println(Arrays.toString(num4));

        //shalow copy
        System.out.println(Arrays.toString(num1));
        num1[0] = 100;
        System.out.println(Arrays.toString(num1));

        //deep copy
        int[] deep = new int[num2.length];
        //use build-in function arraycopy
        System.arraycopy(num2, 0, deep, 0, num2.length);
        System.out.println(Arrays.toString(deep));
        num2[0] = 100;
        System.out.println(Arrays.toString(num2));
        System.out.println(Arrays.toString(deep));
    }

    private static void displayArrays() {
        double[] numbers = {6.0, 4.4, 1.9, 2.9, 3.4, 3.5};
        Arrays.sort(numbers); // Sort the whole array
        Arrays.parallelSort(numbers); // Sort the whole array
        System.out.println(Arrays.toString(numbers));

        char[] chars = {'a', 'A', '4', 'F', 'D', 'P'};
        Arrays.sort(chars, 1, 3); // Sort part of the array
        Arrays.parallelSort(chars, 1, 3); // Sort part of the array, faster in multiple cores computer
        System.out.println(Arrays.toString(chars));

        //binarySearch, the array should be presorted
        int[] list = {2, 4, 7, 10, 11, 45, 50, 59, 60, 66, 69, 70, 79};
        System.out.println("1. Index is " + Arrays.binarySearch(list, 11));
        System.out.println("2. Index is " + Arrays.binarySearch(list, 12));//if not find , return the -(insertionIndex + 1)
        char[] chars1 = {'a', 'c', 'g', 'x', 'y', 'z'};
        System.out.println("3. Index is " + Arrays.binarySearch(chars1, 'a'));
        System.out.println("4. Index is " + Arrays.binarySearch(chars1, 't'));

        int[] list1 = {2, 4, 7, 10};
        int[] list2 = {2, 4, 7, 10};
        int[] list3 = {4, 2, 7, 10};
        System.out.println(Arrays.equals(list1, list2)); // true
        System.out.println(Arrays.equals(list2, list3)); // false

        int[] list4 = {2, 4, 7, 10};
        int[] list5 = {2, 4, 7, 7, 7, 10};
        Arrays.fill(list4, 5); // Fill 5 to the whole array
        Arrays.fill(list5, 1, 5, 8); // Fill 8 to a partial array
        System.out.println(Arrays.toString(list4));
        System.out.println(Arrays.toString(list5));
    }

    private static void buildInCopyArrayMethod() {
        int[] arr1 = new int[4];
        int[] arr2 = new int[4];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i;
        }
        System.arraycopy(arr1,0,arr2,0,4);//different memory allocation for two arrays
        System.out.println(Arrays.toString(arr2));
    }

    public static void deckOfCards(){

        int[] deck = new int[52];
        String[] suits = {"Spades","Hearts","Diamonds","Clubs"};
        String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};

        //initialize
        for (int i = 0; i < deck.length; i++) {
            deck[i] = i;//record the card by number
        }

        //shuffle the cards
        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }

        //display the first 4 cards
        for (int i = 0; i < 4; i++) {
            String suit = suits[deck[i] / 13];
            String rank = ranks[deck[i] % 13];
            System.out.println("Card No. " + deck[i] + ": " + rank + " of " + suit);
        }
    }
}