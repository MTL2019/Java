package org.example.principles.demo1;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Client {
    public static void main(String[] args) {

        SougouInput input = new SougouInput();

        //DefaultSkin skin = new DefaultSkin();
        DarkSkin skin = new DarkSkin();
        input.setSkin(skin);
        input.display();
    }
}