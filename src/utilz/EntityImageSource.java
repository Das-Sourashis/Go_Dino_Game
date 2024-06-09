package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * The EntityImageSource class is responsible for loading and managing images
 * for various game entities from sprite sheets.
 * 
 * Author: Sourashis Das
 */
public class EntityImageSource {

	// CharacterImageDetails for different game entities
	public CharacterImageDetails PIG;
	public CharacterImageDetails BAT;
	public CharacterImageDetails RINO;
	public CharacterImageDetails DINOSAUR;
	public CharacterImageDetails LIFE;
	public CharacterImageDetails COIN;
	public CharacterImageDetails STATUSHEART;
	public CharacterImageDetails DINODANCEC;

	/**
	 * Constructor initializes the images for various game entities by loading them
	 * from their respective file paths and splitting them into frames.
	 */
	public EntityImageSource() {
		PIG = new CharacterImageDetails(getImageFromSource("/images/AngryPig/Walk (36x30).png"), 16);
		BAT = new CharacterImageDetails(getImageFromSource("/images/Bat/Flying (46x30).png"), 7);
		RINO = new CharacterImageDetails(getImageFromSource("/images/Rino/Run (52x34).png"), 6);
		DINOSAUR = new CharacterImageDetails(getImageFromSource("/images/dinosaur/dino.png"), 24);
		LIFE = new CharacterImageDetails(getImageFromSource("/images/coinAndLife/life.png"), 45);
		COIN = new CharacterImageDetails(getImageFromSource("/images/coinAndLife/coin.png"), 6);
		STATUSHEART = new CharacterImageDetails(getImageFromSource("/images/coinAndLife/status_heart.png"), 2);
		DINODANCEC = new CharacterImageDetails(getImageFromSource("/images/dinosaur/dinoDance.png"), 12);
	}

	/**
	 * Loads an image from the given URL.
	 * 
	 * @param url The URL of the image resource.
	 * @return The loaded BufferedImage object.
	 */
	private BufferedImage getImageFromSource(String url) {
		BufferedImage img = null;
		InputStream iis = getClass().getResourceAsStream(url);
		if (iis == null) {
			System.err.println("Image not found!");
		} else {
			try {
				img = ImageIO.read(iis);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (iis != null) {
						iis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return img;
	}

	/**
	 * The CharacterImageDetails class holds details and frames for an entity's
	 * animation extracted from a sprite sheet.
	 */
	public class CharacterImageDetails {
		public ArrayList<BufferedImage> images; // List of frames for the character animation
		public int height; // Height of each frame
		public int width; // Width of each frame

		/**
		 * Constructor to initialize a CharacterImageDetails object by splitting the
		 * sprite sheet into individual frames.
		 * 
		 * @param image The BufferedImage object representing the sprite sheet.
		 * @param count The number of frames in the sprite sheet.
		 */
		public CharacterImageDetails(BufferedImage image, int count) {
			this.images = new ArrayList<>();
			this.height = image.getHeight();
			this.width = image.getWidth() / count;

			// Split the sprite sheet into individual frames and add to the list
			for (int i = 0; i < count; i++) {
				images.add(image.getSubimage(width * i, 0, width, height));
			}
		}
	}
}
