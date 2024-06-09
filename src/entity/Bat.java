package entity;

import java.util.Random;

import utilz.Constants.EntityProperties;
import utilz.Constants.GameValues;
import utilz.EntityImageSource.CharacterImageDetails;

/**
 * The Bat class represents a bat entity in the game.
 * It extends the Enemy class and provides methods specific to bat behavior.
 * 
 * Author: Sourashis Das
 */

public class Bat extends Enemy {
    static Random random = new Random();
    static int steps = 50;

    /**
     * Constructs a new Bat with the specified image details.
     * 
     * @param bat The image details for the bat entity.
     */
    public Bat(CharacterImageDetails bat) {
        super(bat.images, GameValues.RIGHT_START,
                GameValues.BAT_MAX_HEIGHT + 50 * random.nextInt((int) Math.ceil((GameValues.GROUND - GameValues.BAT_MAX_HEIGHT) / steps)),
                (int) (bat.height * EntityProperties.BAT_SPEED), (int) (bat.width * EntityProperties.BAT_SCALE), EntityProperties.BAT_SPEED);
    }

    /**
     * Gets the next image frame for the bat.
     * This method is responsible for updating the bat's image state.
     */
    @Override
    protected void getNextImage() {
        if ((nextFrameKey--) == 0) {
            super.imageState = (super.imageState + 1) % enemyImages.size(); // Cycle through the images
            nextFrameKey = EntityProperties.BAT_TICK; // Reset the frame key
        }
    }
}
