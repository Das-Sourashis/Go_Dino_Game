package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The Enemy class represents an abstract enemy entity in the game.
 * It extends the Entity class and provides common properties and methods for enemies.
 * 
 * Author: Sourashis Das
 */
 public abstract class Enemy extends Entity {
    protected List<BufferedImage> enemyImages; // List of images for the enemy
    protected int nextFrameKey = 0; // Index for next image frame
    public boolean collide; // Indicates if the enemy has collided with another entity to prevent collision again

    /**
     * Constructs a new Enemy with the specified image list, coordinates, dimensions, and speed.
     * 
     * @param img    The list of images for the enemy.
     * @param x      The initial x-coordinate of the enemy.
     * @param y      The initial y-coordinate of the enemy.
     * @param height The height of the enemy.
     * @param width  The width of the enemy.
     * @param speed  The speed of the enemy.
     */
    public Enemy(List<BufferedImage> img, double x, double y, int height, int width, double speed) {
        super(x, y, height, width); // Call superclass constructor
        this.enemyImages = img;
        super.speed = speed; // Set the speed of the enemy
        collide = false; // Initialize collision state
    }

    /**
     * Draws the enemy on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void drawEnemy(Graphics g) {
        g.drawImage(enemyImages.get(super.imageState), (int) super.x, (int) super.y, super.width, super.height, null);
        // Draw the enemy's current image
        // Draw the rectangle around the enemy (commented out)
        // g.drawRect((int)super.x, (int)super.y, super.width, super.height);
    }

    /**
     * Updates the position of the enemy and selects the next image frame.
     */
    public void updateEnemyPosition() {
        super.x -= speed; // Move the enemy horizontally
        getNextImage(); // Update the enemy's image
        updateRectengal(); // Update the position of the enemy's rectangle
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Gets the next image frame for the enemy.
     */
    protected abstract void getNextImage();
}
