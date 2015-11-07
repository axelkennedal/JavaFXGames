package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashSet;

/**
 * A game utilizing a Sprite class and a Game class.
 * @author Axel Kennedal
 * @version 1.2
 * Created on 2015-11-07.
 */

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    protected static int WIDTH = 512;
    protected static int HEIGHT = 512;
    private static GraphicsContext graphicsContext;
    private static Scene mainScene;
    protected static HashSet<KeyCode> currentlyActiveKeys;

    private static double aBillion = 1000000000.0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("MoneyCollector");

        setUpCanvas(primaryStage);
        setUpEventHandlers();
        Game.setUp();
        runGameLoop();

        primaryStage.show();
    }

    private static void setUpCanvas(Stage primaryStage)
    {
        Group root = new Group();
        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();
    }

    private static void setUpEventHandlers()
    {
        currentlyActiveKeys = new HashSet<KeyCode>();
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.add(event.getCode());
            }
        });
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.remove(event.getCode());
            }
        });
    }

    private static void runGameLoop()
    {
        final long lastNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // expressed in seconds
                double elapsedTime = (currentNanoTime - lastNanoTime) / aBillion;

                // game logic
                Game.tick(elapsedTime);

                // render
                Game.render(graphicsContext);
            }
        }.start();
    }
}
