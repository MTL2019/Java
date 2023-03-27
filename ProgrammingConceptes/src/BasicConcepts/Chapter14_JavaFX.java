package BasicConcepts;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

/**
 * @Auther:JW
 * @Date:2023-02-03 - 02 - 03 - 11:24 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */

/*
*   package javafx.application does not exist
*   Project structure - Modules - dependencies - add 4 libs
 */
public class Chapter14_JavaFX extends Application{
    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage){
         //primaryStage is automatically created by JVM when lunched
            // Create a scene and place a button in the scene
        //Stage stage = getFirstStage(primaryStage);
        //buttonInPaneStage(primaryStage);

        //showCircle(primaryStage);

        //property binding
        //showCenterdCircle(primaryStage);

        //Node : style and rotate
        //style : javaFX CSS , prefix -fx-;  styleName:value , use ";" to separate
        //other functions: contains(); setScaleX/Y()
        rotateAndStyleNode(primaryStage);

        //Color / Font Class
        //Image / ImageView Class
        //Layout Panes and Groups
        /*
        Pane : Base class for layout panes. It contains the getChildren() method for returning a list of nodes in the pane.
        StackPane : Places the nodes on top of each other in the center of the pane.
        FlowPane : Places the nodes row-by-row horizontally or column-by-column vertically.
        GridPane : Places the nodes in the cells in a two-dimensional grid.
        BorderPane : Places the nodes in the top, right, bottom, left, and center regions.
        HBox : Places the nodes in a single row.
        VBox : Places the nodes in a single column.
         */
        //Shapes
    }

    private void rotateAndStyleNode(Stage primaryStage) {
        StackPane pane = new StackPane();
        Button btOK = new Button("OK");
        btOK.setStyle("-fx-border-color: blue;");
        pane.getChildren().add(btOK);

        pane.setRotate(45);
        pane.setStyle(
                "-fx-border-color: red; -fx-background-color: lightgray;");

        Scene scene = new Scene(pane, 200, 250);
        primaryStage.setTitle("NodeStyleRotateDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void showCenterdCircle(Stage primaryStage) {
        Pane pane = new Pane();

        Circle circle = new Circle();
        //target.bind(source)
        //centerX : value's getter and setter
        //centerXProperty(): return DoubleProperty instance
        circle.centerXProperty().bind(pane.widthProperty().divide(2));//upper-left is (0,0)
        circle.centerYProperty().bind(pane.heightProperty().divide(2));//upper-left is (0,0)
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        pane.getChildren().add(circle);

        Scene scene = new Scene(pane, 800, 200);
        primaryStage.setTitle("Show Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showCircle(Stage primaryStage) {
        Circle circle = new Circle();
        circle.setCenterX(200);//upper-left is (0,0)
        circle.setCenterY(100);
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        Pane pane = new Pane();
        pane.getChildren().add(circle);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Show Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buttonInPaneStage(Stage primaryStage) {
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("OK"));
        Scene scene = new Scene(pane, 300, 50);
        primaryStage.setTitle("Button in pane");
        primaryStage.setScene(scene);
         primaryStage.show();
    }

    private void getFirstStage(Stage primaryStage) {
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 400, 250);
        primaryStage.setTitle("MyJavaFX"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        //stage => scene => pane => node => button
        /*
         * pane: automatically laying out the nodes in a desired location and size
         * node: visual component such as a shape, an image view, a UI control, a group, or a pane
         * shape: a text, line, circle, ellipse, rectangle, arc, polygon, polyline
         * UI control: a label, button, check box, radio button, text field, text area
         * group: a container that groups a collection of nodes
         */
        Stage stage = new Stage(); // Create a new stage
        stage.setTitle("Second Stage"); // Set the stage title
        // Set a scene with a button in the stage
        stage.setScene(new Scene(new Button("New Stage"), 300, 250));
        stage.show(); // Display the stage
    }
}
