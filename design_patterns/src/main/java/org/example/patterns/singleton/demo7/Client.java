package org.example.patterns.singleton.demo7;

import java.io.*;

//Destroy the singleton by Serializable
public class Client {

    public static void main(String[] args) throws Exception {
        //writeObject2File ();
        readObjectFromFile();
        readObjectFromFile();
    }

    public static void readObjectFromFile() throws Exception{

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\swiss\\tempFile\\a.txt"));

        Singleton instance = (Singleton) ois.readObject();
        System.out.println(instance);
        ois.close();
    }
    public static void writeObject2File () throws IOException {
        Singleton instance = Singleton.getInstance();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\swiss\\tempFile\\a.txt"));

        oos.writeObject(instance);

        oos.close();
    }
}
