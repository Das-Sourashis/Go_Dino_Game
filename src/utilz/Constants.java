package utilz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Constants class contains static nested classes with game-related values
 * and utility methods.
 * 
 * Author: Sourashis Das
 */

public class Constants {

	// Inner class defining game-related values
	public static class GameValues {
		public static final int HEIGHT = 700;
		public static final int WIDTH = 1280;
		public static final int ROAD = 610;
		public static final int BAT_MAX_HEIGHT = 300;
		public static final int GROUND = 525;
		public static final int FPS = 150;
		public static final int UPS = 150;
		public static final int RIGHT_START = WIDTH;
		public static final int DINOSAUR_X = 100;
		public static final int DINOSAUR_Y = GROUND;
	}

	// Inner class defining properties of entities
	public static class EntityProperties {
		// Scaling factors
		public static final double BAT_SCALE = 3;
		public static final double DINOSAUR_SCALE = 4;
		public static final double PIG_SCALE = 3;
		public static final double RINO_SCALE = 3;
		public static final double COIN_SCALE = 0.2;
		public static final double LIFE_SCALE = 0.2;

		// Speeds
		public static final double BAT_SPEED = 2;
		public static final double RINO_SPEED = 3;
		public static final double PIG_SPEED = 1.7;
		public static final double COIN_SPEED = 0.8;
		public static final double LIFE_SPEED = 1;

		// Animation ticks
		public static final int BAT_TICK = 5;
		public static final int PIG_TICK = 10;
		public static final int RINO_TICK = 20;
		public static final int COIN_TICK = 5;
		public static final int LIFE_TICK = 2;
		public static final int DINO_TICK = 10;
	}

	// Inner class for getting the maximum score
	public static class GetMaxScore {
		public static int MAX_SCORE = 0;

		static {
			loadMaxScore();
		}

		// Method to load the maximum score from file
		private static void loadMaxScore() {
			String filePath = "asset/score/maxscore.txt";
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(filePath));
				String line = reader.readLine();
				if (line != null) {
					MAX_SCORE = Integer.parseInt(line);
					System.out.println(MAX_SCORE);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Inner class for saving the maximum score
	public static class SaveScore {
		static String filePath = "asset/score/maxscore.txt";;

		// Method to save the highest score to file
		public static void saveHighestScore(int maxvalue) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
				System.out.println(maxvalue);
				writer.write(Integer.toString(maxvalue));
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
