package AdvancedJavaProgramming.Networking;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Server extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        TextArea ta = new TextArea();//Area for displaying contents

        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server"); // Set the stage title
         primaryStage.setScene(scene); // Place the scene in the stage
         primaryStage.show(); // Display the stage

        new Thread(() -> {
            try{
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(()->ta.appendText("Server started at " + new Date() + '\n'));

                // Listen for a connection request
                 Socket socket = serverSocket.accept();//wait for connections

                // Listen for a connection request
                // Create data input and output streams
                 DataInputStream inputFromClient = new DataInputStream(
                         socket.getInputStream());
                 DataOutputStream outputToClient = new DataOutputStream(
                         socket.getOutputStream());

                 while (true){
                     double radius = inputFromClient.readDouble();

                     double area = radius * radius * Math.PI;

                     outputToClient.writeDouble(area);

                     Platform.runLater(() ->{
                         ta.appendText("Radius received from client: " + radius + '\n');
                         ta.appendText("Area is:" + area +'\n');
                     });
                 }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
