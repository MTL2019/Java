package BasicConcepts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

/**
 * @Auther:JW
 * @Date:2023-01-27 - 01 - 27 - 10:27 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter12_ExceptionHandlingAndIO {

    public static void main(String[] args) throws FileNotFoundException{

        //the File class does not contain the methods for reading and writing file contents.
        //just encapsulates the properties of a file or a path
        //testFileClass();

        //text files
        //reading text data from a file : Scanner class
        //writing text data to a file:  PrintWriter class
        File file = new File("temp/scores.txt");
        testWrite(file);
        testRead(file);

        testGetLineSeparatorFromSystem();

        getDataFromUrl();

    }

    private static void getDataFromUrl() {
        try {
            URL url = new URL("http://www.google.com/index.html");
            int count = 0;
            Scanner input = new Scanner(url.openStream());
            while (input.hasNext()){
                String line = input.nextLine();
                count += line.length();
            }
            System.out.println("The file size is " + count + " characters");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testGetLineSeparatorFromSystem() {
        String lineSeparator = System.getProperty("line.separator");// find system separator
        System.out.println("lineSeparator-1" + lineSeparator);
    }

    private static void testRead(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);

        while (input.hasNext()){
            String firstName = input.next();
            String mi = input.next();
            String lastName = input.next();
            int score = input.nextInt();

            System.out.println(firstName + mi + lastName + ": " + score);
        }
    }
    private static void testWrite(File file) throws FileNotFoundException {

        if (file.exists()) {
            System.out.println("File already exists");
            //System.exit(1);
            Random rand = new Random();
            int randomNumber = rand.nextInt(1000);

            String[] list ;
            list = file.getPath().split("\\.");
            list[0] += randomNumber;
            file = new File(list[0] + "." + list[1]);
        }

        PrintWriter output = new PrintWriter(file);

        output.print("John T Smith ");
        output.println(90);
        output.print("Eric K jones ");
        output.println(85);

        output.close();
    }

    private static void testFileClass() {
        File file = new File("temp/US.png");

        System.out.println("exist? : " + file.exists());
        System.out.println("Length " + file.length());
        System.out.println("can Read? " + file.canRead());
        System.out.println("can Write? " + file.canWrite());
        System.out.println("is Directory? " + file.isDirectory());
        System.out.println("is File? " + file.isFile());
        System.out.println("is Absolute? " + file.isAbsolute());
        System.out.println("is Hidden ?" + file.isHidden());
        System.out.println("getAbsolutePath : " + file.getAbsolutePath());
        System.out.println("lastModified: " + new Date(file.lastModified()));
    }
}