package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Handles overall game logic and rendering.
 * @author Axel Kennedal
 * @version 1.0
 * Created on 2015-11-07.
 */
public class Game
{
    private static Sprite playerSprite;
    private static ArrayList<Sprite> collectibles;
    private static int score = 0;
    private static double acceleration = 0.005;

    /**
     * Perform one "tick" in the game, i.e. move objects, perform collision detection and logic.
     * @param elapsedTime time since the game was started.
     */
    public static void tick(double elapsedTime)
    {
        if (Main.currentlyActiveKeys.contains(KeyCode.LEFT))
        {
            playerSprite.changeVelocityX(-acceleration);
        }
        if (Main.currentlyActiveKeys.contains(KeyCode.RIGHT))
        {
            playerSprite.changeVelocityX(acceleration);
        }
        if (Main.currentlyActiveKeys.contains(KeyCode.UP))
        {
            playerSprite.changeVelocityY(-acceleration);
        }
        if (Main.currentlyActiveKeys.contains(KeyCode.DOWN))
        {
            playerSprite.changeVelocityY(acceleration);
        }
        playerSprite.tick(elapsedTime);

        // collision detection
        Iterator<Sprite> collectiblesIterator = collectibles.iterator();
        while (collectiblesIterator.hasNext())
        {
            Sprite collectible = collectiblesIterator.next();
            if (playerSprite.intersects(collectible))
            {
                collectiblesIterator.remove();
                score += 25;
            }
        }
    }

    /**
     * Render the game graphics.
     * @param graphicsContext
     */
    public static void render(GraphicsContext graphicsContext)
    {
        graphicsContext.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
        playerSprite.render(graphicsContext);

        for (Sprite collectible : collectibles)
        {
            collectible.render(graphicsContext);
        }

        String scoreText = "Cash: " + score + "$";
        graphicsContext.setFill(Color.DARKSEAGREEN);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText(scoreText, 20, 20);
        graphicsContext.strokeText(scoreText, 20, 20);
    }

    /**
     * Needs to be called before using this class, prepares it for use.
     */
    public static void setUp()
    {
        createSprites();
    }

    /**
     * Create the sprites for the game.
     */
    private static void createSprites()
    {
        playerSprite = new Sprite("briefcase.png", 200, 0);

        collectibles = new ArrayList<Sprite>();
        for (int i = 0; i < 15; i++)
        {
            Sprite moneybag = new Sprite("moneybag.png", 350 * Math.random() + 50, 350 * Math.random() + 50);
            collectibles.add(moneybag);
        }
    }
}
