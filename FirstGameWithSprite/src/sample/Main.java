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
 * A game utilizing a Sprite class and a Game class. The player collects randomly placed collectibles
 * and the game is quit when all are collected.
 * @author Axel Kennedal
 * @version 1.35
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
    private static HashSet<KeyCode> currentlyActiveKeys;
    private static AudioManager audioManager;

    public static AudioManager getAudioManager()
    {
        return audioManager;
    }

    public static HashSet<KeyCode> getCurrentlyActiveKeys()
    {
        return currentlyActiveKeys;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("MoneyCollector");

        setUpSounds();
        setUpCanvas(primaryStage);
        setUpEventHandlers();
        Game.setUp();
        runGameLoop();

        primaryStage.show();
    }

    private static void setUpSounds()
    {
        audioManager = new AudioManager();
        audioManager.addSound("pickup.wav", "pickup", true);
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
        new AnimationTimer()
        {
            final long lastNanoTime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
                // expressed in seconds
                double elapsedTime = (currentNanoTime - lastNanoTime) / GameTools.A_BILLION;

                // game logic
                if (!Game.tick(elapsedTime))
                {
                    stop();
                    System.exit(0);
                }

                Game.render(graphicsContext);

                GameTools.displayFPS(graphicsContext);
            }
        }.start();
    }
}
