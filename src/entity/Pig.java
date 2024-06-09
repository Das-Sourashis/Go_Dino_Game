package entity;

import utilz.Constants.EntityProperties;
import utilz.Constants.GameValues;
import utilz.EntityImageSource.CharacterImageDetails;

/**
 * The Pig class represents a pig entity in the game. It extends the
 * Enemy class and provides methods specific to pig behavior.
 * 
 * Author: Sourashis Das
 */
public class Pig extends Enemy {

	/**
	 * Constructs a new Pig entity with the specified image details.
	 * 
	 * @param pig The image details for the pig entity.
	 */
	public Pig(CharacterImageDetails pig) {
		super(pig.images, GameValues.RIGHT_START, GameValues.ROAD - (int) (pig.height * EntityProperties.PIG_SCALE),
				(int) (pig.height * EntityProperties.PIG_SCALE), (int) (pig.width * EntityProperties.PIG_SCALE),
				EntityProperties.PIG_SPEED);
	}

	/**
	 * This method is responsible for updating the pig entity's image state.
	 * It cycles through the images to create animation.
	 */
	@Override
	protected void getNextImage() {
		if ((nextFrameKey--) == 0) {
			// Cycle through the images for animation
			super.imageState = (super.imageState + 1) % enemyImages.size();
			nextFrameKey = EntityProperties.PIG_TICK; // Reset the frame key
		}
	}

}
