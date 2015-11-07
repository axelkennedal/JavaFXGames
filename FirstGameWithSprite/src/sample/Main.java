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

import java.util.ArrayList;
import java.util.HashSet;

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
    private static Scene mainScene;
    private static HashSet<KeyCode> currentlyActiveKeys;

    private static double aBillion = 1000000000.0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("MoneyCollector");

        setUpCanvas(primaryStage);
        setUpEventHandlers();
        createSprites();

        final long lastNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // expressed in seconds
                double elapsedTime = (currentNanoTime - lastNanoTime) / aBillion;

                // game logic

            }
        }.start();

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

    private static void createSprites()
    {
        // player sprite
        Sprite briefcase = new Sprite("briefcase.png", 200, 0);

        // create collectibles
        ArrayList<Sprite> moneybags = new ArrayList<Sprite>();
        for (int i = 0; i < 15; i++)
        {
            Sprite moneybag = new Sprite("moneybag.png", 350 * Math.random() + 50, 350 * Math.random() + 50);
            moneybags.add(moneybag);
        }
    }
}
