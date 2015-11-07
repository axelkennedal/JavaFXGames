package sample;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * A game utilizing a Sprite class.
 * @author Axel Kennedal
 * @version 1.0
 */

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    private static int WIDTH = 512;
    private static int HEIGHT = 512;
    private static GraphicsContext graphicsContext;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("MoneyCollector");

        setUpCanvas(primaryStage);

        

        primaryStage.show();
    }

    private static void setUpCanvas(Stage primaryStage)
    {
        Group root = new Group();
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();
    }
}
