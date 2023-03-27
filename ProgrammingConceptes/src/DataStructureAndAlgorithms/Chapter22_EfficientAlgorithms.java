package DataStructureAndAlgorithms;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Order of magnitude: growth rates
 * Time Complexity of Common Algorithms:
 *    Binary Search: O(logn)
 *    Linear search: O(n)
 *    Merge sort: O(n logn)
 *    Selection Sort : O(n2).
 *    Tower of Hanoi Problem : O(2n)
 *    Recursive Fibonacci algorithm : O(2n)
 *
 *    Dynamic programming: to solve each subproblem only once and store the results for subproblems for later use
 *    to avoid redundant computing of the subproblems.
 *
 *    Finding primes:
     *    Brute-force :O(n2)
     *    Checking divisors up to 2n:O(n * sqrt(n))
     *    Checking prime divisors up to 2n : O(n * sqrt(n) / logn)
     *    Sieve of Eratosthenes: Better than O(n * sqrt(n) / logn), but with limited memory case
 *
 *    FindingClosestPairOfPoints by Divide-and-Conquer 分治
 */
public class Chapter22_EfficientAlgorithms {

    static final int NUMBER_PER_LINE = 10; // Display 10 per line
    public static void main(String[] args) {

        //improvedFibonacci();

        //improvedFindingGreatestCommonDivisors();

        //testFindingPrimeNumber();

        //Divide-and-Conquer 分治
        //testFindingClosestPairOfPoints(); // go to  example
    }

    //A better algorithms to find primes
    //The Sieve of Eratosthenes algorithm is good for a small n such that the array primes can fit in the memory
    public static void sieveOfEratosthenes(int n){

        boolean[] primes = new boolean[n + 1];// Prime number sieve

        for (int i = 0; i < primes.length; i++) {
            primes[i]  = true;
        }

        for (int k = 2; k <= n / k; k++) {//we don’t care about primes[0],primes[1]
            if (primes[k]) {
                for (int i = k; i <= n / k; i++) {
                    primes[k * i] = false;  // k * i is not prime
                }
            }
        }

        int count = 0;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                count++;
                if (count %  NUMBER_PER_LINE == 0) {
                    System.out.printf("%7d\n", i);
                }else
                    System.out.printf("%7d", i);
            }
        }

        System.out.println("\n" + count +
            " prime(s) less than or equal to " + n);
    }

    //Another example of dynamic programming
    public static void improvedFindingPrimeNumber(int n){

        int count = 0; // Count the number of prime numbers
        int number = 2; // A number to be tested for primeness
        ArrayList<Integer> list = new ArrayList<>();
        int squareRoot = 1;

        while(number <= n){
            boolean isPrime = true;

            if (squareRoot * squareRoot < number) squareRoot++;

            for (int k = 0; k < list.size() && list.get(k) <= squareRoot; k++) {
                if (number % list.get(k) == 0) {
                    isPrime = false;//Exists factor for number, so to exclude this number and go to next loop
                    break;
                }
            }

            if (isPrime) {
                count++;//count the primes' number
                list.add(number);

                if (count %  NUMBER_PER_LINE == 0) {
                    System.out.printf("%7d\n", number);
                }else
                    System.out.printf("%7d", number);
            }

            //check the next number is prime
            number++;
        }

        System.out.println("\n" + count +
                " prime(s) less than or equal to " + n);
    }

    public static void findingPrimeNumber(int n){

        int count = 0; // Count the number of prime numbers
        int number = 2; // A number to be tested for primeness
        int squareRoot = 0;

        while(number <= n){
            boolean isPrime = true;

            //int squareRoot = (int) Math.sqrt(number);
            if (squareRoot * squareRoot < number) squareRoot++;

            for (int divisor = 2; divisor <= squareRoot; divisor++) {
                if (number % divisor == 0) {
                    isPrime = false;//Exists factor for number, so to exclude this number and go to next loop
                    break;
                }
            }

            if (isPrime) {
                count++;//count the primes' number

                if (count %  NUMBER_PER_LINE == 0) {
                    System.out.printf("%7d\n", number);
                }else
                    System.out.printf("%7d", number);
            }

            //check the next number is prime
            number++;
        }

        System.out.println("\n" + count +
                 " prime(s) less than or equal to " + n);
    }

    public static void testFindingPrimeNumber(){

        Scanner input = new Scanner(System.in);
        System.out.print("Find all prime numbers <= n, enter n: ");
        int n = input.nextInt();

        System.out.println("The prime numbers are:");

        //findingPrimeNumber(n);
        //improvedFindingPrimeNumber(n);

        sieveOfEratosthenes(n);

    }
    public static void improvedFindingGreatestCommonDivisors(){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter first integer: ");
        int m = input.nextInt();
        System.out.print("Enter second integer: ");
        int n = input.nextInt();

        long startTime = System.currentTimeMillis();
        System.out.println("The greatest common divisor for  " + m + " and " + n + " is " + gcd(m,n));
        System.out.println("Time lasts :" + (System.currentTimeMillis() - startTime));
         startTime = System.currentTimeMillis();
        System.out.println("The greatest common divisor for  " + m + " and " + n + " is " + gcdEuclid(m,n));
        System.out.println("Time lasts with gcdEuclid method :" + (System.currentTimeMillis() - startTime));
    }

    /**
     *  Suppose m % n = r. Thus, m = qn + r, where q is the quotient of m / n. Any number that divides m and n evenly must also
     *  divide r evenly. Therefore, gcd(m, n) ====== gcd(n, r), where r = m % n.
     *  Time Complexity : O(log n)
     * @param m
     * @param n
     * @return
     */
    public static int gcdEuclid(int m, int n) {
        if (m % n == 0) {
            return n;
        }else
            return gcdEuclid(n,m % n);
    }

    public static int gcd(int m, int n) {

        int gcd = 1;

        if (m % n == 0) {
            return n;
        }

        for (int k = n / 2; k >= 1 ; k--) {
            if (m % k == 0 && n % k == 0) {
                gcd = k;
                break;
            }
        }

        return gcd;
    }

    public static void improvedFibonacci(){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index for the Fibonacci number: ");
        int index = input.nextInt();

        System.out.println("Fibonacci number at index " + index + " is " + fib(index));
    }

    //dynamic programming: to solve each subproblem only once and store the results
    //for subproblems for later use to avoid redundant computing of the subproblems
    public static long fib(long n) {

        //improved efficiency, because of using three vars, save times to invoke fib with same arguments
        long f0 = 0;//fib(0)
        long f1 = 1;//fib(1)
        long f2 = 1;//fib(2)

        if (n == 0) {
            return f0;
        } else if (n == 1) {
            return f1;
        } else if(n == 2) {
            return f2;
        }

        for (int i = 3; i <= n; i++) {
            f0 = f1;
            f1 = f2;
            f2 = f1 + f0;
        }

        return f2;
    }
}
