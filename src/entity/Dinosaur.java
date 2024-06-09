package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import sounds.EventSounds;
import utilz.Constants.EntityProperties;
import utilz.Constants.GameValues;
import utilz.EntityImageSource.CharacterImageDetails;

/**
 * The Dinosaur class represents the main character of the game. It extends the
 * Entity class and provides methods specific to the dinosaur's behavior.
 * 
 * Author: Sourashis Das
 */
public class Dinosaur extends Entity {

	public enum State {
		RUN, HIT, JUMP
	};

	public int nextFrameTick;
	private int runCount, Hitcount, jumpCount, jumpSteps, maxJumpCount, nextJumpTick;
	private List<BufferedImage> RUNList, HITList;
	public State state = State.RUN;
	public static boolean isJump = true;

	/**
	 * Constructs a new Dinosaur with the specified image details.
	 * 
	 * @param dinosaur The image details for the dinosaur character.
	 */
	public Dinosaur(CharacterImageDetails dinosaur) {
		super(GameValues.DINOSAUR_X, GameValues.ROAD - (int) (dinosaur.height * EntityProperties.DINOSAUR_SCALE),
				(int) (dinosaur.height * EntityProperties.DINOSAUR_SCALE),
				(int) (dinosaur.width * EntityProperties.DINOSAUR_SCALE));
		HITList = new ArrayList<BufferedImage>();

		runCount = 6;
		Hitcount = (2 * 3);
		jumpCount = 0;
		jumpSteps = 35; // change of y position in jump
		maxJumpCount = 8; // 8 steps up and 8 steps down for jump
		nextFrameTick = 0;
		nextJumpTick = 1;
		RUNList = dinosaur.images.subList(4, 10);
		List<BufferedImage> temp = dinosaur.images.subList(14, 17);
		for (int i = 0; i < 2; i++) { // 2 for 2 times repetition
			for (int j = 0; j < 3; j++) {
				HITList.add(temp.get(j));
			}
		}
	}

	/**
	 * Draws the dinosaur character on the screen based on its current state.
	 * 
	 * @param g The graphics object used for drawing.
	 */
	public void drawDino(Graphics g) {
		if (state == State.RUN) {
			g.drawImage(RUNList.get(super.imageState), (int) super.x, (int) super.y, super.width, super.height, null);
		} else if (state == State.HIT) {
			g.drawImage(HITList.get(super.imageState), (int) super.x, (int) super.y, super.width, super.height, null);
		}
//		g.drawRect( (int) super.x, (int) super.y, super.width, super.height);
	}

	/**
	 * Updates the state and position of the dinosaur character.
	 */
	public void updateDino() {
		jumpDecision();
		nextState();
	}

	/**
	 * Determines whether the dinosaur should jump and updates its position
	 * accordingly.
	 */
	private void jumpDecision() {
		if (isJump) {
			if (jumpCount == 3) { // play jump sound
				EventSounds.playEventSound(EventSounds.JUMP);
			}
			if (0 == nextJumpTick--) { // up
				if (jumpCount < maxJumpCount) {
					super.y -= jumpSteps;
				} else if (jumpCount < 2 * maxJumpCount) { // down fall
					super.y += jumpSteps;
				} else {
					isJump = false;
					jumpCount = 0;
					nextJumpTick = 1;
					return;
				}
				super.updateRectengal();
				jumpCount++;
				nextJumpTick = EntityProperties.DINO_TICK;
			}
		}
	}

	/**
	 * Updates the dinosaur's state to the next frame of animation.
	 */
	private void nextState() {
		if (0 == (nextFrameTick--)) {
			if (state == State.RUN) {
				super.imageState = (super.imageState + 1) % runCount;
			} else if (state == State.HIT) {
				super.imageState++;
				if (super.imageState == this.Hitcount) {
					state = State.RUN;
					super.imageState = 0;
				}
			}
			nextFrameTick = EntityProperties.DINO_TICK;
		}
	}

}
