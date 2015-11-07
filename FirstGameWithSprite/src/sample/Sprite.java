package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A basic sprite for 2D games.
 * @author Axel Kennedal
 * @version 1.0
 * Created on 2015-11-07.
 */
public class Sprite
{
    // graphics
    private Image image;

    // spatial data
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    Sprite(Image spriteGraphic, double positionX, double positionY, double velocityX, double velocityY)
    {
        image = spriteGraphic;
        width = image.getWidth();
        height = image.getHeight();

        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Calculate new position for sprite.
     * @param time the current time.
     */
    public void tick(double time)
    {
        positionX += velocityX * time;
        positionX += velocityY * time;
    }

    /**
     * Draw the sprite.
     * @param graphicsContext the context in which to draw the sprite.
     */
    public void render(GraphicsContext graphicsContext)
    {
        graphicsContext.drawImage(image, positionX, positionY);
    }

    /**
     * Get the boundaries for the sprite.
     * @return a two-dimensional rectangle with position and size of the sprite.
     */
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    /**
     * Test if this sprite intersects another one. Useful for collision detection.
     * @param otherSprite other sprite to check intersection with.
     * @return true if this sprite intersects the otherSprite, otherwise false.
     */
    public boolean intersects(Sprite otherSprite)
    {
        return otherSprite.getBoundary().intersects(this.getBoundary());
    }
}
