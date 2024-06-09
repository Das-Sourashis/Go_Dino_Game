package entity;

import java.awt.Rectangle;

/**
 * The Entity class represents an abstract entity in the game.
 * It extends Rectangle and provides common properties and methods for entities.
 * 
 * Author: Sourashis Das
 */

public abstract class Entity extends Rectangle {
    protected int height, width; // Height and width of the entity
    public int imageState; // State of sprite
    public double x; // X-coordinate of the entity
    protected double y, speed; // Y-coordinate and speed of the entity

    /**
     * Constructs a new Entity with the specified coordinates, height, and width.
     * 
     * @param x      The initial x-coordinate of the entity.
     * @param y      The initial y-coordinate of the entity.
     * @param height The height of the entity.
     * @param width  The width of the entity.
     */
    public Entity(double x, double y, int height, int width) {
        super((int) x, (int) y, width, height); // Initialize Rectangle
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.imageState = 0; // Default image state
    }

    /**
     * Checks if this entity collides with another entity.
     * 
     * @param rect The other entity to check collision with.
     * @return true if this entity collides with the other entity, false otherwise.
     */
    public boolean isColide(Entity rect) {
        return this.intersects(rect); // Use Rectangle's intersects method to check collision
    }

    /**
     * Updates the position of the entity's rectangle.
     */
    protected void updateRectengal() {
        this.setLocation((int) x, (int) y); // Update the location of the rectangle
    }
}
