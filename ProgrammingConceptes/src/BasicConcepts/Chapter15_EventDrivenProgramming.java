package BasicConcepts;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/*
 * @Auther:JW
 * @Date:2023-02-04 - 02 - 04 - 2:46 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */

/**
 * Mouse Events
 * Key Events
 */

public class Chapter15_EventDrivenProgramming extends Application{
    public static void main(String[] args){
        Application.launch(args);
    }

    private Circle circle1 = new Circle(60);
    private Rectangle rectangle1 = new Rectangle(120, 120);
    private StackPane pane1 = new StackPane();

    @Override
    public void start(Stage primaryStage){
        //testEventHandler(primaryStage);

        //testMouseEvent(primaryStage);
        //testKeyEvent(primaryStage);

        //testListener(primaryStage);

        //Animation: pathTransition

    }

    private void testListener(Stage primaryStage) {
        circle1.setFill(Color.GRAY);
        rectangle1.setFill(Color.WHITE);
        rectangle1.setStroke(Color.ORANGE);

        pane1.getChildren().addAll(circle1,rectangle1);

        Scene scene = new Scene(pane1, 400, 400);
        primaryStage.setTitle("Resizable Circle Rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane1.widthProperty().addListener(ov->resize());
        pane1.heightProperty().addListener(ov->resize());
    }

    private void resize() {
        double length = Math.min(pane1.getWidth(), pane1.getHeight());
         circle1.setRadius(length / 2 - 15);
         rectangle1.setWidth(length - 30);
         rectangle1.setHeight(length - 30);
    }
    private void testKeyEvent(Stage primaryStage) {
        Pane pane = new Pane();
        Text text = new Text(20, 20, "Key Event Test");
        pane.getChildren().addAll(text);

        text.setOnKeyPressed(e->{
            switch (e.getCode()){
                case DOWN:text.setY(text.getY() + 50); break;
                case UP:text.setY(text.getY() - 50); break;
                case LEFT:text.setX(text.getX() - 50); break;
                case RIGHT:text.setX(text.getX() + 50); break;
                default:
                    if (e.getText().length() > 0) {
                        text.setText(e.getText());
                    }
            }
        });

        Scene scene = new Scene(pane, 400, 100);
        primaryStage.setTitle("KeyEnventDemo");
        primaryStage.setScene(scene);
        primaryStage.show();

        text.requestFocus();//receive the key input
    }

    private void testMouseEvent(Stage primaryStage) {
        Pane pane = new Pane();
        Text text = new Text(20, 20, "Programming is fun");
        pane.getChildren().addAll(text);
        text.setOnMouseDragged(e->{
            text.setX(e.getX());
            text.setY(e.getY());
        });

        Scene scene = new Scene(pane, 400, 100);
        primaryStage.setTitle("MouseEnventDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void testEventHandler(Stage primaryStage) {
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);
        Button btOK = new Button("OK");
        Button btCancel = new Button("CANCEL");
        OKHandlerClass okHandlerClass = new OKHandlerClass();
        CancelHandlerClass cancelHandlerClass = new CancelHandlerClass();
        btOK.setOnAction(okHandlerClass);//binding event handle class
        btCancel.setOnAction(cancelHandlerClass);

        pane.getChildren().addAll(btOK,btCancel);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("HandleEvent");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class OKHandlerClass implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("OK Button clicked");
    }
}

class CancelHandlerClass implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Cancel Button clicked");
    }
}
