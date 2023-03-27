package BasicConcepts;

import java.util.Scanner;

/**
 * @Auther:JW
 * @Date:2023-01-27 - 01 - 27 - 10:27 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter2Elementary {

    final double PI = 3.14159;//named constants

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
//        byte radius = input.nextByte();
//        short radius = input.nextShort();
//        int radius = input.nextInt();
//        long radius = input.nextLong();
//        float radius = input.nextFloat();
        //System.out.print("Enter a number for radius: ");
        //double radius = input.nextDouble();
        //System.out.println(radius);

        //System.out.println(Math.pow(2,3));

        //2.11 JShell
        // JShell is a command line tool for quickly evaluating an expression and executing a statement.
        //windows  cmd - jshell

        //2.12
        //华氏度转摄氏度
        System.out.print("Enter a degree in Fahrenheit: ");
        double fahrenheit = input.nextDouble();
        System.out.printf("%.2f",fahrenheitToCelsius(fahrenheit));

        //2.13
        //System.out.println(System.currentTimeMillis());
        //System.nanoTime();

        //3
        //double x = Math.random();// 0<= x < 1.0

        System.out.print("\nEnter a year: ");
        int year = input.nextInt();

        //System.exit(0);    //非0非非正常终止
        isLeapYear(year);// is leap year? 被400整除；或者被4整除而不被100整除的年份
        chineseZodiac(year); //zodiac year
    }

    public static double fahrenheitToCelsius(double temperature){
        return (5.0 / 9) * (temperature - 32);
    }


    //determining Leap Year
    public static void isLeapYear(int year){

//        boolean isLeapYear = (year % 4 == 0);
//        isLeapYear = isLeapYear && (year % 100 != 0);
//        isLeapYear = isLeapYear || (year % 400 == 0);

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        System.out.println(year + " is leap year? " + isLeapYear);
    }

    public static void chineseZodiac(int year){

        switch (year % 12) {
            case 0 -> System.out.println("monkey");
//公鸡
            case 1 -> System.out.println("rooster");
            case 2 -> System.out.println("dog");
            case 3 -> System.out.println("pig");
            case 4 -> System.out.println("rat");
//牛
            case 5 -> System.out.println("ox");
            case 6 -> System.out.println("tiger");
            case 7 -> System.out.println("rabbit");
            case 8 -> System.out.println("dragon");
            case 9 -> System.out.println("snake");
            case 10 -> System.out.println("horse");
            case 11 -> System.out.println("sheep");
        }
    }
}
