package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Useful utilites for game development.
 * @author Axel Kennedal
 * @version 1.0
 * Created on 2015-11-07.
 */
public class GameTools
{
    public static final double A_BILLION = 1000000000.0;
    private static long lastFrameTime = System.nanoTime();

    /**
     * Render text in upper left corner, displaying the current Frames Per Second of the game.
     * Run this on each pass through the main game loop.
     * @param graphicsContext context to draw in.
     */
    public static void displayFPS(GraphicsContext graphicsContext)
    {
        int fps = (int)(1.0/((System.nanoTime() - lastFrameTime) / A_BILLION));

        graphicsContext.setFont(new Font(16));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRoundRect(-25, 0, 120, 30, 25, 25);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("FPS: " + fps, 10, 20);

        lastFrameTime = System.nanoTime();
    }
}