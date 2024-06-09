package entity;

import utilz.Constants.EntityProperties;
import utilz.Constants.GameValues;
import utilz.EntityImageSource.CharacterImageDetails;

/**
 * The GoldCoin class represents a gold coin entity in the game. It extends the
 * Enemy class and provides methods specific to gold coin behavior.
 * 
 * Author: Sourashis Das
 */
public class GoldCoin extends Enemy {
	/**
	 * Constructs a new GoldCoin with the specified image details.
	 * 
	 * @param coin The image details for the gold coin entity.
	 */
	public GoldCoin(CharacterImageDetails coin) {
		super(coin.images, GameValues.RIGHT_START, 400, (int) (coin.height * EntityProperties.COIN_SCALE),
				(int) (coin.width * EntityProperties.COIN_SCALE), EntityProperties.COIN_SPEED);
	}

	/**
	 * Gets the next image frame for the gold coin. This method is responsible for
	 * updating the gold coin's image state.
	 */
	@Override
	protected void getNextImage() {
		if ((nextFrameKey--) == 0) {
			super.imageState = (super.imageState + 1) % enemyImages.size(); // Cycle through the images
			nextFrameKey = EntityProperties.COIN_TICK; // Reset the frame key
		}
	}
}
