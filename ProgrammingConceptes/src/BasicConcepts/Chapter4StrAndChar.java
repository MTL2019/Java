package BasicConcepts;

import java.util.Scanner;

/**
 * @Auther:JW
 * @Date:2023-01-27 - 01 - 27 - 10:27 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter4StrAndChar {

    public static void main(String[] args){

        //Character Class methods
        //characterClass();

        //String Type
        //stringObj();
        //readString();
        //compareString();

        //Example: guess birthdays
        //guessBirthdays();//5位2进制推算

        //format output
        formatOutput();

        functionsAndSpecialChars();
    }

    private static void functionsAndSpecialChars() {
        //trigonometric methods
        System.out.printf("%.2f\n",Math.toDegrees(0.5));
        System.out.printf("%.2f\n",Math.toRadians(90));

        //Exponent Methods
        System.out.println(Math.exp(10));//e 10次方
        System.out.println(Math.log(2));
        System.out.println(Math.log10(2));
        System.out.println(Math.pow(2,4));
        System.out.println(Math.sqrt(16));
        System.out.println("-------------------------------");

        //rounding methods
        System.out.println(Math.ceil(3.2));//round up to nearest int
        System.out.println(Math.floor(3.2));//round down to nearest int
        System.out.println(Math.rint(3.5));//round to nearest int; if equal to two Integers, the even one is returned
        System.out.println(Math.round(3.2));//Returns (int)Math.floor(x + 0.5) if x is a float; and returns (long)Math.floor(x + 0.5) if x is a double.

        //special characters
        System.out.println("\b");//backspace
        System.out.println("\t");//tab
        System.out.println("\n");//linefeed
        System.out.println("\f");//formfeed 换页
        System.out.println("\r");//carriage return 回车
        System.out.println("\\");// "\"
        System.out.println("\"");// ' " '
    }

    private static void formatOutput(){
        System.out.printf("%b\n",10);
        System.out.printf("%c\n",'a');
        System.out.printf("%,010d\n",1234567);
        System.out.printf("%.2f\n",0.1234);
        System.out.printf("%10.2e\n",4.555);
        System.out.printf("Hello, %s\n","Java");
        System.out.printf("%-8d%-8s%-8.1f \n" , 1234,"java",5.64);//默认右对齐，加-线可以左对齐
    }
    private static void guessBirthdays() {
        String set1 = """
                 1 3  5  7
                9 11 13 15
                17 19 21 23
                25 27 29 31""";
        String set2 = """
                 2 3  6  7
                10 11 14 15
                18 19 22 23
                26 27 30 31""";
        String set3 = """
                 4 5  6  7
                12 13 14 15
                20 21 22 23
                28 29 30 31""";
        String set4 = """
                 8 9  10 11
                12 13 14 15
                24 25 26 27
                28 29 30 31""";
        String set5 = """
                16 17 18 19
                20 21 22 23
                24 25 26 27
                28 27 30 31""";

        int day = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Is your birthday in Set1?");
        System.out.print(set1);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        int answer = input.nextInt();
        if (answer == 1) {
            day += 1;
        }

        System.out.print("Is your birthday in Set2?");
        System.out.print(set2);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = input.nextInt();
        if (answer == 1) {
            day += 2;
        }

        System.out.print("Is your birthday in Set3?");
        System.out.print(set3);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = input.nextInt();
        if (answer == 1) {
            day += 4;
        }

        System.out.print("Is your birthday in Set4?");
        System.out.print(set4);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = input.nextInt();
        if (answer == 1) {
            day += 8;
        }

        System.out.print("Is your birthday in Set5?");
        System.out.print(set5);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = input.nextInt();
        if (answer == 1) {
            day += 16;
        }

        System.out.println("\nYour birthday is " + day + "!");
    }

    public static void compareString(){
        String s1 = "Welcome to Java";
        String s2 = "Welcome to Java";
        String s3 = "Welcome to C++";
        String s4 = "Welcome to JAVA";

        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s1.equalsIgnoreCase(s4));
        System.out.println(s1.compareTo(s4));
        System.out.println(s1.compareToIgnoreCase(s4));
        System.out.println(s1.startsWith("W"));
        System.out.println(s1.endsWith("A"));
        System.out.println(s1.contains("to"));

        System.out.println(s1.substring(0,11) + "HTML");//sub string

        //find char or string in a string
        System.out.println(s1.indexOf("a"));
        System.out.println(s1.indexOf("a",14));

        System.out.println(s1.indexOf("to"));
        System.out.println(s1.indexOf("va",13));

        System.out.println(s1.lastIndexOf("a"));
        System.out.println(s1.lastIndexOf("a",14));

        System.out.println(s1.lastIndexOf("to"));
        System.out.println(s1.lastIndexOf("va",13));
    }

    public static void readString(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a String: ");
        String message = input.next();//input next string ended with a whitespace
        System.out.println(message);
         message = input.nextLine();//input next string ended with a carriage return
        System.out.println(message);
        char ch = message.charAt(2);//read a char from input
        System.out.println(ch);
    }

    public static void stringObj(){
        String message = "Welcome to Java";
        System.out.println(message.length());
        System.out.println(message.charAt(1));
        System.out.println(message.concat("!"));//连接字符串
        System.out.println(message.toLowerCase());
        System.out.println(message.toUpperCase());
        System.out.println(message.trim());// trim whitespace on both sides
    }

    public static void characterClass(){
        System.out.println(Character.isDigit('a'));
        System.out.println(Character.isLetter('a'));
        System.out.println(Character.isLetterOrDigit('a'));
        System.out.println(Character.isLowerCase('a'));
        System.out.println(Character.isUpperCase('a'));
        System.out.println(Character.toUpperCase('a'));
        System.out.println(Character.toLowerCase('A'));
    }
}


