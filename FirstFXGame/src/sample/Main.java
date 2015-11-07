package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage mainStage)
    {
        mainStage.setTitle("GameLoop Example");

        Group root = new Group();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);

        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        final Image earth = new Image(Main.class.getResource("earth.png").toString());
        final Image sun = new Image(Main.class.getResource("sun.png").toString());
        final Image space = new Image(Main.class.getResource("space.png").toString());

        final AnimatedImage ufo = new AnimatedImage();
        ufo.frames = new Image[6];
        for (int i = 0; i < 6; i++)
        {
            ufo.frames[i] = new Image(Main.class.getResource("ufo_" + i + ".png").toString());
        }
        ufo.duration = 0.100;

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double timeSinceStart = (currentNanoTime - startNanoTime) / 1000000000.0;

                double xPos = 232 + 128 * Math.cos(timeSinceStart);
                double yPos = 232 + 128 * Math.sin(timeSinceStart);

                // drawing background first clears canvas
                gc.drawImage(space, 0, 0);
                gc.drawImage(earth, xPos, yPos);
                gc.drawImage(sun, 196, 196);

                gc.drawImage(ufo.getFrame(timeSinceStart), 450, 25);
            }
        }.start();

        mainStage.show();
    }
}

class AnimatedImage
{
    public Image[] frames;
    public double duration;

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
}