package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A basic sprite for 2D games.
 * @author Axel Kennedal
 * @version 1.35
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

    /**
     * Change the horizontal velocity.
     * @param velocityDifference how much to change the velocity with.
     */
    public void changeVelocityX(double velocityDifference)
    {
        velocityX += velocityDifference;
    }

    /**
     * Change the vertical velocity.
     * @param velocityDifference how much to change the velocity with.
     */
    public void changeVelocityY(double velocityDifference)
    {
        velocityY += velocityDifference;
    }

    /**
     * Change the horizontal and vertical velocity.
     * @param velocityDifferenceX how much to change the horizontal velocity with.
     * @param velocityDifferenceY how much to change the vertical velocity with.
     */
    public void changeVelocity(double velocityDifferenceX, double velocityDifferenceY)
    {
        changeVelocityX(velocityDifferenceX);
        changeVelocityY(velocityDifferenceY);
    }

    /**
     * Main constructor.
     * @param spriteGraphicName the name of the image to use a graphic for this Sprite.
     *                          Image must be placed in the same folder as Main.java or
     *                          a subfolder of the parent folder of Main.java.
     * @param positionX horizontal position.
     * @param positionY vertical position.
     * @param velocityX horizontal speed.
     * @param velocityY vertical speed.
     */
    Sprite(String spriteGraphicName, double positionX, double positionY, double velocityX, double velocityY)
    {
        image = new Image(getResource(spriteGraphicName));
        width = image.getWidth();
        height = image.getHeight();

        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Create a new sprite with a specified location.
     * @param spriteGraphicName
     * @param positionX
     * @param positionY
     */
    Sprite(String spriteGraphicName, double positionX, double positionY)
    {
        this(spriteGraphicName, positionX, positionY, 0, 0);
    }

    /**
     * Create a new sprite with spatial data unspecified.
     * @param spriteGraphicName the name of the image to use a graphic for this Sprite.
     *                          Image must be placed in the same folder as Main.java or
     *                          a subfolder of the parent folder of Main.java.
     */
    Sprite(String spriteGraphicName)
    {
        this(spriteGraphicName, 0, 0, 0, 0);
    }

    /**
     * Calculate new position for sprite.
     * @param time the current time.
     */
    public void tick(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
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

    /**
     * Set 2D-position of this Sprite.
     * @param positionX new horizontal position.
     * @param positionY new vertical position.
     */
    public void setPosition(double positionX, double positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Return the URL of the resource for the specified filename.
     * @param filename the name of the file to get the URL for.
     * @return a string representation of the URL for the resource.
     */
    private static String getResource(String filename)
    {
        return Main.class.getResource(filename).toString();
    }
}
