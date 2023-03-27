package Examples;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FlashText_32 extends Application {

    private String text = "";

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane pane = new StackPane();
        Label lblText = new Label("Programming is fun");
        pane.getChildren().add(lblText);

        //Creates a Runnable object in an anonymous inner class
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    while (true) {
//                        if (lblText.getText().trim().length() == 0) {
//                            text = "Welcome";
//                        } else
//                            text = "";
//
//                        //tells the system to run this Runnable object in the application thread.
//                        Platform.runLater(new Runnable() {// Run from JavaFX GUI
//                            @Override
//                            public void run() {
//                                lblText.setText(text);
//                            }
//                        });
//
//                        Thread.sleep(200);
//                     }
//
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();//runs continuously to change the text in the label

        //we can use  lambda expression to simplify
        new Thread(() -> {

            try{
                while(true){
                    if (lblText.getText().trim().length() == 0) {
                        text = "Welcome";
                        } else
                            text = "";

                    Platform.runLater(()-> lblText.setText(text));

                    Thread.sleep(200);
                    }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();


        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 200, 50);
        primaryStage.setTitle("FlashText");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
