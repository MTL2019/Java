package BasicConcepts;


import java.io.*;
import java.util.Date;

/**
 * InputStream/OutputStream: root class of read/write binary data
 * FileInputStream/FileOutputStream: reading/writing bytes from/to files
 * FilterInputStream/FilterOutputStream: filter bytes for some purpose(specific data types)
 * DataInputStream/DataOutputStream: filter/convert primitive numeric types to byte(binary) - implements DataInput interface
 * BufferedInputStream/BufferedOutputStream: be used to speed up input and output by reducing the number of disk reads and writes
 *
 * Object I/O
 * ObjectInputStream/ObjectOutputStream: be used to read/write serializable objects; can replace DataInputStream/DataOutputStream completely
 * Serializable interface : a marker interface.
 *      When a serializable object is stored, the class of the object is encoded;but excludes the static variables
 *      transient keyword: to tell the JVM to ignore those non-serializable instance data when writing the object to an object stream
 *      when store an object again in the stream, only store its serial number
 *      for Array: all the elements should be serializing
 *
 *
 * RandomAccessFile: allow data to be read from and written to at any locations in the file.
 *          using the RandomAccessFile class to open;
 *          A random-access file consists of a sequence of bytes. A special marker called a file pointer is positioned at one of these bytes
 */
public class Chapter17_IO {
    public static void main(String[] args) throws ClassNotFoundException,IOException{

        //testFileInAndOutputStream();

        //testDataInAndOutputStream();

        //testObjectInAndOutputStream();//It should be serializable object

        //testObjectStreamForArray();

        testRandomAccessFile();
    }

    private static void testRandomAccessFile() {

        try{
            try(
                    RandomAccessFile inOut = new RandomAccessFile("temp/array.dat","rw")
            ){
                inOut.setLength(0);
                for (int i = 0; i < 200; i++) {
                    inOut.writeInt(i);
                }

                System.out.println("Current file length is " + inOut.length());

                for (int i = 0; i < inOut.length(); i++) {
                    inOut.seek(4 * i);//move the number position
                    System.out.printf("The %d number is %d \n" , i , inOut.readInt());
                }
            }

        }catch (EOFException ex){//detect the end of the file
            System.out.println("All data were read");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void testObjectStreamForArray() throws ClassNotFoundException {

        int[] numbers = {1,2,3,4,5};
        String[] strings = {"John","Susan","Kim"};

        try{
            try(
                    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("temp/array.dat"))
            ){
                output.writeObject(numbers);
                output.writeObject(strings);
            }

            try(
                    ObjectInputStream input = new ObjectInputStream(new FileInputStream("temp/array.dat"))
            ){
                int[] newNums = (int[]) (input.readObject());
                String[] newStrs = (String[]) (input.readObject());

                for (int i : newNums) {
                    System.out.print(newNums[i] + " ");
                }

                System.out.println("\n==================");

                for (String newStr : newStrs) {
                    System.out.print(newStr + " ");
                }
            }
        }catch (EOFException ex){//detect the end of the file
            System.out.println("All data were read");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void testObjectInAndOutputStream() throws ClassNotFoundException {
        try{
            try(
                    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("temp/object.dat"))
            ){
                //ObjectOutputStream can handle object, in addition to primitive types
                output.writeUTF("John");
                output.writeDouble(43.25);
                output.writeObject(new Date());
            }

            try(
                    ObjectInputStream input = new ObjectInputStream(new FileInputStream("temp/object.dat"))
            ){
                String name = input.readUTF();
                double score = input.readDouble();
                Date date = (Date) (input.readObject());
                System.out.println(name + " " + score + " " + date);
            }
        }
        catch (EOFException ex){//detect the end of the file
            System.out.println("All data were read");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void testDataInAndOutputStream() {
        try{
            try(
                    DataOutputStream output = new DataOutputStream(new FileOutputStream("temp/test.dat"))
                    ){
                output.writeDouble(4.5);
                output.writeDouble(43.25);
                output.writeDouble(3.2);
            }

            try(
                    DataInputStream input = new DataInputStream(new FileInputStream("temp/test.dat"))
                    ){
                while (true){
                    System.out.println(input.readDouble());
                }
            }
        }
        catch (EOFException ex){//detect the end of the file
            System.out.println("All data were read");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void testFileInAndOutputStream() throws IOException {

        //implement the AutoClosable interface: uses the try-with-resources to declare and create input and output streams so they
        //will be automatically closed after they are used
        try (
                //if the file exists, it will be deleted and rewrited
                FileOutputStream fileOutputStream = new FileOutputStream("temp/fileOutput.dat")
        ){
            for (int i = 0; i < 11; i++) {
                fileOutputStream.write(i);
            }
        }

        try (FileInputStream fileInputStream = new FileInputStream("temp/fileOutput.dat")
        ){
            int value;
            while ((value = fileInputStream.read()) != -1){
                System.out.print(value + " ");
            }

            //catch EOFException can be used to detect the end of the file
        }

        //they can be used with Scanner or PrintWriter
        //new PrintWriter(new FileOutputStream("temp.txt", true));
    }
}
