package DataStructureAndAlgorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * stopping condition/base case: how to solve the simplest case
 * direct recursion: a recursive method that invokes itself
 * indirect recursion : occurs when method A invokes method B, which in turn directly or indirectly invokes method A
 * recursive helper method: help find a solution to the oringinal problem
 * Tail Recursion :  after recursive call, no other statement to be performed
 * \
 *
 */
public class Chapter18_Recursions {
    public static void main(String[] args) {

        //calculateFactorial();
        //calculateFabonacci();
        //testIsPalindrome();

        //with helper method, to avoid repeating create string
        //testIsPalindromeWithHelperMethod();

        //testRecursionSelectionSort();

        //testBinarySearch();

        towerOfHanoi();
    }

    private static void towerOfHanoi(){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of disks : ");
        int n = input.nextInt();

        System.out.println("\n The moves are: ");
        moveDisks(n,'A','B','C');
    }

    private static void moveDisks(int n,char fromTower,char toTower,char auxTower){

        if (n == 1) {
            System.out.println("Move Disk " + n + ": from " + fromTower + " to " + toTower);//move action
        }
        else
        {
            moveDisks(n-1,fromTower,auxTower,toTower);
            System.out.println("Move Disk " + n + ": from " + fromTower + " to " + toTower);//move action
            moveDisks(n-1,auxTower,toTower,fromTower);
        }
    }

    private static void testBinarySearch(){

        int[] list = {3,9,34,56,23,78,90,123,435,876,9809,1245,7564,2,5,56,50};

        //sort
        Arrays.sort(list);
        System.out.println(Arrays.toString(list));

        int index = recursionBinarySearch(list, 50);
        System.out.println("Index is  " + index);
    }

    private static int recursionBinarySearch(int[] list,int key){

        int low = 0;
        int high = list.length -1;
        return recursionBinarySearch(list,key,low,high);
    }

    private static int recursionBinarySearch(int[] list,int key,int low,int high){

        if (low > high) {
            return -low - 1;//without a match
        }

        int middle = (low + high) / 2;
        if (list[middle] < key) {
            return  recursionBinarySearch(list,key,middle + 1,high);
        }else if(list[middle] == key)
            return middle;
        else
            return recursionBinarySearch(list,key,low,middle -1);
    }

    private static void testRecursionSelectionSort(){
        double[] list = {2.2,3.2,4.3,5.4,6.5,7.6,5.3,8.9,7.8};

        recursionSelectionSort(list);
        System.out.println(Arrays.toString(list));
    }

    private static void recursionSelectionSort(double[] list,int low,int high){
        if (low < high) {
            int indexOfMin = low;
            double min = list[low];

            //Find the smallest no and its index in list[low .. high]
            for (int i = low + 1; i <= high; i++) {
                if (list[i] < min) {
                    min = list[i];
                    indexOfMin = i;
                }
            }

            //Swap the smallest in list with list[low]
            list[indexOfMin] = list[low];
            list[low] = min;

            recursionSelectionSort(list,low+1,high);//recursion helper again
        }
    }

    private static void recursionSelectionSort(double[] list){
        recursionSelectionSort(list,0,list.length-1);
    }

    private static void testIsPalindromeWithHelperMethod() {
        System.out.println("Is moon a palindrome? " + isPalindromeWithHelperMethod("moon"));
        System.out.println("Is noon a palindrome? " + isPalindromeWithHelperMethod("noon"));
        System.out.println("Is a a palindrome? " + isPalindromeWithHelperMethod("a"));
        System.out.println("Is aba a palindrome? " + isPalindromeWithHelperMethod("aba"));
        System.out.println("Is ab a palindrome? " + isPalindromeWithHelperMethod("ab"));
    }

    //helper method, can be called repeatedly
    private static boolean isPalindromeWithHelperMethod(String s,int low, int high) {
        if (high <= low) {
            return true;
        }else if (s.charAt(low) != s.charAt(high)) {
            return false;
        }else
            return isPalindromeWithHelperMethod(s,low + 1,high -1);//recursion
    }

    private static boolean isPalindromeWithHelperMethod(String s) {
        return isPalindromeWithHelperMethod(s,0,s.length()-1);// recursion method
    }

    private static void testIsPalindrome() {
        System.out.println("Is moon a palindrome? " + isPalindrome("moon"));
        System.out.println("Is noon a palindrome? " + isPalindrome("noon"));
        System.out.println("Is a a palindrome? " + isPalindrome("a"));
        System.out.println("Is aba a palindrome? " + isPalindrome("aba"));
        System.out.println("Is ab a palindrome? " + isPalindrome("ab"));
    }

    private static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }else if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }else
            return isPalindrome(s.substring(1,s.length()-1));//recursion
    }

    private static void calculateFabonacci() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index for Fibonacci number: ");
        int index = input.nextInt();

        System.out.println("\nThe Fibonacci number at index " + index + " is " + fib(index));
    }

    private static long fib(long index) {
        if (index == 0) {
            return 0;
        }else if (index == 1) {
            return 1;
        }else
            return fib(index - 1) + fib(index -2);
    }

    private static void calculateFactorial() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a nonnegative integer: ");
        int n = input.nextInt();

        System.out.println("\nFactorial of " + n + " is " + factorial(n));
    }

    public static long factorial(int n){

        if (n == 0) {//stopping condition
            return 1;
        }else{
            return n * factorial(n-1);
        }
    }
}
