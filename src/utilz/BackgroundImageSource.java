package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import utilz.Constants.GameValues;

/**
 * BackgroundImageSource handles the loading and management of background images
 * for the game's parallax scrolling effect.
 * 
 * Author: Sourashis Das
 */
public class BackgroundImageSource {
	// List to store details of background images
	public List<ImageDetails> BACKGROUND_IMAGE;

	// Speeds for different background layers
	private double speed1 = 0;
	private double speed2 = 0.3;
	private double speed3 = 0.6;
	private double speed4 = 0.9;
	private double speed5 = 1.2;
	private double speed6 = speed5; // Ground and topmost frame have the same speed

	/**
	 * Constructor initializes the background images with their respective details
	 * such as image source, initial position, speed, and layer continuity.
	 */
	public BackgroundImageSource() {
		// Initialize the list of background images
		BACKGROUND_IMAGE = new ArrayList<>();

		// Add background images with their details to the list
		BACKGROUND_IMAGE.add(new ImageDetails(getImageFromSource("/images/parallax/plx-1.png"), 0, speed1, false));

		// Add 2 images of each moving background to maintain continuity
		// First image spans from 0 to panel width
		// Second image spans from panel width to 2 * panel width
		BACKGROUND_IMAGE.add(new ImageDetails(getImageFromSource("/images/parallax/plx-2.png"), 0, speed2, true)); // true => first image
		BACKGROUND_IMAGE.add(
				new ImageDetails(getImageFromSource("/images/parallax/plx-2.png"), GameValues.WIDTH, speed2, false)); // false => second image
		BACKGROUND_IMAGE.add(new ImageDetails(getImageFromSource("/images/parallax/plx-3.png"), 0, speed3, true));
		BACKGROUND_IMAGE.add(
				new ImageDetails(getImageFromSource("/images/parallax/plx-3.png"), GameValues.WIDTH, speed3, false));
		BACKGROUND_IMAGE.add(new ImageDetails(getImageFromSource("/images/parallax/plx-4.png"), 0, speed4, true));
		BACKGROUND_IMAGE.add(
				new ImageDetails(getImageFromSource("/images/parallax/plx-4.png"), GameValues.WIDTH, speed4, false));
		BACKGROUND_IMAGE.add(new ImageDetails(getImageFromSource("/images/parallax/plx-5.png"), 0, speed5, true));
		BACKGROUND_IMAGE.add(
				new ImageDetails(getImageFromSource("/images/parallax/plx-5.png"), GameValues.WIDTH, speed5, false));
		BACKGROUND_IMAGE.add(new ImageDetails(getImageFromSource("/images/parallax/plx-6.png"), 0, speed6, true));
		BACKGROUND_IMAGE.add(
				new ImageDetails(getImageFromSource("/images/parallax/plx-6.png"), GameValues.WIDTH, speed6, false));
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
	 * Updates the position of each background image to create a parallax scrolling
	 * effect.
	 * 
	 * @param bckgroundImage The list of ImageDetails representing the background
	 *                       images.
	 */
	public void changePicturePosition(List<ImageDetails> bckgroundImage) {
		for (ImageDetails imageDetails : bckgroundImage) {
			if (imageDetails.speed != 0) { // If it is not a still background
				imageDetails.x -= imageDetails.speed; // Move left

				// Check if the image is the first in its layer
				if (imageDetails.isFirst) {
					// If the image has moved out of the frame, reset its position
					if (-imageDetails.x >= GameValues.WIDTH) {
						imageDetails.x = 0;
					}
				} else { // if it is second
					// If the image has completely moved inside the frame, reset its position
					if (imageDetails.x <= 0) {
						imageDetails.x = GameValues.WIDTH;
					}
				}
			}
		}
	}

	/**
	 * A class to hold details about each background image.
	 */
	public class ImageDetails {
		public BufferedImage image; // The image object
		public double x; // The x-coordinate of the image
		public double speed; // The speed of the image for the parallax effect
		public boolean isFirst; // Indicates if it is the first image in its layer

		/**
		 * Constructor to initialize an ImageDetails object.
		 * 
		 * @param image   The BufferedImage object.
		 * @param x       The initial x-coordinate.
		 * @param speed   The speed for the parallax effect.
		 * @param isFirst Indicates if it is the first image in its layer.
		 */
		public ImageDetails(BufferedImage image, double x, double speed, boolean isFirst) {
			this.image = image;
			this.x = x;
			this.speed = speed;
			this.isFirst = isFirst;
		}
	}
}
