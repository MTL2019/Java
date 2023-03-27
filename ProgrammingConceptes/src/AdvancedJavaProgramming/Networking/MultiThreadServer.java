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

public class MultiThreadServer extends Application {
    // Text area for displaying contents
    private TextArea ta = new TextArea();

    // Number a client
    private int clientNo = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("MultiThreadServer"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));

                //Different
                while (true) {
                    // Listen for a connection request
                    Socket socket = serverSocket.accept();//wait for connections

                    clientNo++;

                    Platform.runLater(() -> {
                        ta.appendText("Starting thread for client " + clientNo + " at " + new Date() + '\n');

                        InetAddress inetAddress = socket.getInetAddress();
                        ta.appendText("Client " + clientNo + "'s host name is "
                                + inetAddress.getHostName() + "\n");
                        ta.appendText("Client " + clientNo + "'s IP Address is"
                                + inetAddress.getHostAddress() + "\n");
                    });

                    // Create and start a new thread for the connection
                    new Thread(new HandleAClient(socket)).start();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket

        /**
         * Construct a thread
         */
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());

                // Continuously serve the client
                while (true) {
                    // Receive radius from the client
                    double radius = inputFromClient.readDouble();

                    // Compute area
                    double area = radius * radius * Math.PI;

                    // Send area back to the client
                    outputToClient.writeDouble(area);

                    Platform.runLater(() -> {
                        ta.appendText("radius received from client: " +
                                radius + '\n');
                        ta.appendText("Area found: " + area + '\n');
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
