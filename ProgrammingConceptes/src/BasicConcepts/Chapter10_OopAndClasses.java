package BasicConcepts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @Auther:JW
 * @Date:2023-01-27 - 01 - 27 - 10:27 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter10_OopAndClasses {

    public static void main(String[] args) {
        //OOP
        //association, aggregation, composition, and inheritance.
        //1.association 关联
        //2. Aggregation 聚合： a special form of association that represents an ownership relationship between
        //two objects. Aggregation models has-a relationships.
        //3. composition 复合 Aggregation的一种，当被聚合的类依赖于聚合类存在时，即认定为是复合关系
        //Composition implies exclusive ownership, One object owns another object. When the owner object is destroyed, the dependent
        //object is destroyed as well.

        //class can be used in 2 ways:
        //1. create instance of the class : composition
        //2. define subclass extends the class  : inherit

        //10.7 Wrap the primitive data type
        //features: no no-arg constructors; immutable; 
        //createWrapClass();

        //boxing & unboxing
        //boxingOrUnboxing();

        //10.9 bigInteger / bigDecimal ： immutable
        // be used to represent integers or decimal numbers of any size and precision.
        //useBigIntegerOrDecimal();

        //String Class : immutable
        //useInternedString();

        //replacingAndSplittingStrings();
        //matchingString();

        //convertStringAndArrays();
        //formatString();

        //StringBuilder StringBuffer:  not immutable
        //StringBuffer : synchronized, 同步的， only one task is allowed to execute the methods
        //StringBuilder 与StringBuffer类似，多用户不安全， 单用户时效率更高
        stringBuilderAndBuffer();

    }

    /**
     * 总结：
     * 1. 如果string长度不变，用String
     * 2. 如果string长度要变动,但不要求多线程安全，用StringBuilder
     * 3. 2. 如果string长度要变动,但要求多线程安全，用StringBuffer
     */
    public static void stringBuilderAndBuffer() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Welcome");
        stringBuilder.append(" ");
        stringBuilder.append("to");
        stringBuilder.append(" ");
        stringBuilder.append("Java");
        System.out.println(stringBuilder);

        stringBuilder.insert(11,"HTML and ");
        System.out.println(stringBuilder);

        stringBuilder.delete(8,11);
        System.out.println(stringBuilder);

        stringBuilder.reverse();
        System.out.println(stringBuilder);

        stringBuilder.reverse();
        stringBuilder.replace(8,16,"to HTML");
        System.out.println(stringBuilder);

        stringBuilder.setCharAt(0,'w');
        System.out.println(stringBuilder);

        //except setCharAt; other functions return a reference; so they can be invoked in a chain
        stringBuilder.reverse().delete(8,11).replace(8,16,"hello");
        System.out.println(stringBuilder);//invoke toString()
        System.out.println(stringBuilder.capacity());//current capacity of the string builder
        System.out.println(stringBuilder.charAt(1));
        System.out.println(stringBuilder.length());//the number of characters actually stored in the string builder.
        stringBuilder.setLength(50);
        System.out.println(stringBuilder.substring(5));
        System.out.println(stringBuilder.substring(5,10));
        System.out.println(stringBuilder.capacity() + "\t" + stringBuilder.length());
        stringBuilder.trimToSize();
        System.out.println(stringBuilder.capacity() + "\t" + stringBuilder.length());
    }

    public static void formatString() {

        //与printf区别： 返回格式化的字符串
        String str = String.format("%7.2f%6d%-4s",45.556,14,"AB");
        System.out.println(str + "1");
    }

    public static void convertStringAndArrays() {

        char[] chars = "Java".toCharArray();//convert
        for (char e : chars) {
            System.out.print(e + "\t");
        }
        System.out.println();

        char[] dst = {'J', 'A', 'V', 'A', '1', '3', '0', '1'};
        "CS3720".getChars(2,6,dst,4);//copy from specific postion
        for (char e : dst) {
            System.out.print(e + "\t");
        }
        System.out.println();

        String str = new String(new char[]{'J','a','v','a'});
        System.out.println(str);

        String str1 = String.valueOf(new char[]{'J','a','v','a','1'});
        System.out.println(str1);
    }

    public static void matchingString() {

        System.out.println("Java is powerful".matches("Java.*"));
        System.out.println("440–02–4534".matches("\\d{3}–\\d{2}–\\d{4}"));// \\d代表一位数字，{3}代表3位数字

        String s = "a+b$#c".replaceAll("[$+#]", "NNN");
        System.out.println(s);

        String[] tokens = "Java,C?C#,C++".split("[.,:;?]");
        for (String token : tokens) System.out.println(token);
    }

    public static void replacingAndSplittingStrings() {

        //String is immutable, so replace it with a new String
        System.out.println("Welcome".replace('e','A'));
        System.out.println("Welcome".replaceFirst("e","AB"));
        System.out.println("Welcome".replace("e","AB"));
        System.out.println("Welcome".replace("el","AB"));
        System.out.println("Welcome".replaceAll("e","AB"));

        String[] tokens = "Java#HTML#Perl".split("#");
        for (String token : tokens) System.out.print(token + " ");

        System.out.println(Arrays.toString(tokens));

    }

    public static void useInternedString() {
        String s1 = "Welcome to Java";
        String s2 = new String("Welcome to Java");
        String s3 = "Welcome to Java";//InternedString, do not create new one, just point to s1
        String s4 = new String("Welcome to Java");
        System.out.println("s1 == s2 is " + (s1 == s2));//false
        System.out.println("s1 == s3 is " + (s1 == s3));//true
        System.out.println("s2 == s4 is " + (s2 == s4));//false
    }

    public static void useBigIntegerOrDecimal() {
        BigInteger a = new BigInteger("9223372036854775807");
        BigInteger b = new BigInteger("2");
        BigInteger c = a.multiply(b); // 9223372036854775807 * 2
        System.out.println(c);

        BigDecimal e = new BigDecimal("1.0");
        BigDecimal f = new BigDecimal("3");
        //to specify a scale and a rounding mode to avoid ArithmeticException exception
        BigDecimal g = e.divide(f, 20, RoundingMode.HALF_UP);
        System.out.println(g);
    }

    public static void boxingOrUnboxing(){
        Integer intObject = 2;//autoboxing

        Integer[] intArray = {1,2,3};//autoBoxing
        System.out.println(intArray[0] + intArray[1] + intArray[2]);//autoUnboxing
    }

    public static void createWrapClass() {

        //use valueOf to create instance will be better, because it does not create a new object
        Integer x1 = new Integer("32");
        Integer x2 = new Integer("32");
        Integer x3 = Integer.valueOf("32");
        Integer x4 = Integer.valueOf("32");
        Integer x5 = 32;
        System.out.println("x1 == x2 is " + (x1 == x2)); // Display false
        System.out.println("x1 == x3 is " + (x1 == x3)); // Display false
        System.out.println("x3 == x4 is " + (x3 == x4)); // Display true
        System.out.println("x3 == x5 is " + (x3 == x5)); // Display true

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(Double.valueOf(12.4).compareTo(Double.valueOf(12.3)));// returns 1;
        System.out.println(Double.valueOf(12.3).compareTo(Double.valueOf(12.3)));// returns 0;
        System.out.println(Double.valueOf(12.3).compareTo(Double.valueOf(12.51)));// returns –1;

        System.out.println(Integer.parseInt("11",2));
        System.out.println(Integer.parseInt("12",8));
        System.out.println(Integer.parseInt("13",10));
        System.out.println(Integer.parseInt("1A",16));

        System.out.printf("%x%n",26);
    }

}