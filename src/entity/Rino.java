package entity;

import utilz.Constants.EntityProperties;
import utilz.Constants.GameValues;
import utilz.EntityImageSource.CharacterImageDetails;

/**
 * The Rino class represents a rhinoceros entity in the game. It extends the
 * Enemy class and provides methods specific to rhinoceros behavior.
 * 
 * Author: Sourashis Das
 */
public class Rino extends Enemy {

	/**
	 * Constructs a new Rino entity with the specified image details.
	 * 
	 * @param rino The image details for the rhinoceros entity.
	 */
	public Rino(CharacterImageDetails rino) {
		super(rino.images, GameValues.RIGHT_START, GameValues.ROAD - (int) (rino.height * EntityProperties.RINO_SCALE),
				(int) (rino.height * EntityProperties.RINO_SCALE), (int) (rino.width * EntityProperties.RINO_SCALE),
				EntityProperties.RINO_SPEED);
	}

	/**
	 * This method is responsible for updating the rhinoceros entity's image state.
	 * It cycles through the images to create animation.
	 */
	@Override
	protected void getNextImage() {
		if ((nextFrameKey--) == 0) {
			// Cycle through the images for animation
			super.imageState = (super.imageState + 1) % enemyImages.size();
			nextFrameKey = EntityProperties.RINO_TICK; // Reset the frame key
		}
	}

}
