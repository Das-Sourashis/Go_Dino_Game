package entity;

import utilz.Constants.EntityProperties;
import utilz.EntityImageSource.CharacterImageDetails;

/**
 * The Life class represents a life entity in the game. It extends the
 * Enemy class and provides methods specific to life entity behavior.
 * 
 * Author: Sourashis Das
 */
public class Life extends Enemy {
	
	/**
	 * Constructs a new Life entity with the specified image details.
	 * 
	 * @param life The image details for the life entity.
	 */
	public Life(CharacterImageDetails life) {
		// Initialize the life entity with the provided image details and default position
		// The life entity starts at the right edge of the screen, 300 pixels from the top,
		// with a scaled height and width based on the provided image details,
		// and moves with a constant speed defined in the entity properties.
		super(life.images, 1280, 300, (int) (life.height * EntityProperties.LIFE_SCALE),
				(int) (life.width * EntityProperties.LIFE_SCALE), EntityProperties.LIFE_SPEED);
	}

	/**
	 * This method is responsible for updating the life entity's image state.
	 * Since the life entity does not have an animation, this method is left empty.
	 */
	@Override
	protected void getNextImage() {
		if ((nextFrameKey--) == 0) {
			super.imageState = (super.imageState + 1) % enemyImages.size(); // Cycle through the images
			nextFrameKey = EntityProperties.LIFE_TICK; // Reset the frame key
		}
	}
}
