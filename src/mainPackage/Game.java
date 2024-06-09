/**
 * 
 */
package mainPackage;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import entity.Bat;
import entity.Dinosaur;
import entity.Enemy;
import entity.GoldCoin;
import entity.Life;
import entity.Pig;
import entity.Rino;
import sounds.BackgroundMusic;
import sounds.EventSounds;
import widgets.GameOverMenu;
import widgets.StatusBar;
import utilz.BackgroundImageSource;
import utilz.Constants.GetMaxScore;
import utilz.EntityImageSource;
import utilz.Constants.GameValues;

/**
 * The Game class is responsible for managing the main game loop, handling
 * game state, and coordinating the interaction between game entities and UI components.
 * 
 * Author: Sourashis Das
 */
public class Game implements Runnable {
	private MainPanel gamePanel; // Main game panel
	private MainFrame gameFrame; // Main game frame
	private int FPS_SET = GameValues.FPS; // Frames per second setting
	private int UPS_SET = GameValues.UPS; // Updates per second setting
	private double fps = 0; // Current frame rate
	private double ups = 0; // Current update rate
	private int enemyTime = 0; // Timer for enemy spawning
	BackgroundImageSource backgroundImageSource; // Source for background images
	public EntityImageSource entityImageSource; // Source for entity images
	private Thread thread; // Game loop thread
	public ArrayList<Enemy> enemyArrayList; // List of current enemies and Life and Gold Coins 
	private Dinosaur dinosaur; // Player's Dinosaur character
	StatusBar statusBar; // Status bar UI component
	private int totalScore, highestScore; // Score tracking
	public int totalLife; // Current life count
	private final int maxLife; // Maximum life count
	private List<Timer> timers; // List of timers for game events
	private boolean active = true; // Game active state
	private Random random = new Random(); // Random number generator
	public BackgroundMusic backgroundMusic; // Background music player
//	private EventSounds eventSounds;

	Game() {
		enemyArrayList = new ArrayList<Enemy>(); // Initialize enemy list
		timers = new ArrayList<>(); // Initialize timers list
		maxLife = 5; // Set maximum life
		totalScore = 0; // Initialize score
		totalLife = maxLife; // Set initial life to max
		highestScore = GetMaxScore.MAX_SCORE; // Load previous highest score
		backgroundImageSource = new BackgroundImageSource(); // Load background images
		entityImageSource = new EntityImageSource(); // Load entity images
		backgroundMusic = new BackgroundMusic(); // Initialize background music
		backgroundMusic.start(); // Start background music
		
		dinosaur = new Dinosaur(entityImageSource.DINOSAUR); // Initialize player's dinosaur
		statusBar = new StatusBar(this); // Initialize status bar
		gamePanel = new MainPanel(this); // Initialize game panel
		gameFrame = new MainFrame(gamePanel); // Initialize game frame
		gamePanel.requestFocus(); // Request focus for game panel
		
//		addEnemies(); // for enemy added using timer thread 
		
		// Start the game
		statGame();
		System.out.println(dinosaur);
	}

	/**
	 * Clears the game panel.
	 */
	public void clearGame() {
		gamePanel.removeAll();
		gamePanel.revalidate();
		gamePanel.repaint();
	}
	
	/**
	 * Resets the game state for a new game.
	 */
	public void ResetupGame() {
		active = true;
		totalScore = 0;
		totalLife = maxLife;
		dinosaur = new Dinosaur(entityImageSource.DINOSAUR);
		statusBar = new StatusBar(this);
		gamePanel.add(statusBar, BorderLayout.NORTH);
//		addEnemies(); // for enemy added using timer thread 
		gamePanel.addLabel();
		System.out.println(dinosaur);
	}

	// use timer task to add enemy
	
//	private void addEnemies() {
//		Timer t1 = new Timer();
//		t1.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				if (active) {
//					int randomNum = random.nextInt(19);
//					if (randomNum >= 0 && randomNum <= 3) {
////						System.out.println("bat");
//						enemyArrayList.add(new Bat(entityImageSource.BAT));
//					} else if (randomNum >= 4 && randomNum <= 7) {
////						System.out.println("pig");
//						enemyArrayList.add(new Pig(entityImageSource.PIG));
//					} else if (randomNum >= 8 && randomNum <= 11) {
////						System.out.println("rino");
//						enemyArrayList.add(new Rino(entityImageSource.RINO));
//					} else if (randomNum >= 12 && randomNum <= 16) {
////						System.out.println("coin");
//						enemyArrayList.add(new GoldCoin(entityImageSource.COIN));
//					} else if (randomNum >= 17 && randomNum <= 18) {
////						System.out.println("life");
//						enemyArrayList.add(new Life(entityImageSource.LIFE));
//					}
//				}
//			}
//		}, 0, 2000);
//
//		timers.add(t1);
//
//		Timer t2 = new Timer();
//		t2.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				if (active) {
////					System.out.println("fps : " + fps + ", ups : " + ups);
//					fps = 0;
//					ups = 0;
//				}
//			}
//		}, 1000, 1000);
//
//	}

	
	/**
	 * Starts the game thread.
	 */
	private void statGame() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Main game loop that handles updating and drawing the game state.
	 */
	@Override
	public void run() { // main function of game which will draw and also update
		double timePerUpdate = 1000000000.0 / UPS_SET;
		double timePerFrame = 1000000000.0 / FPS_SET;

		double dFrame = 0;
		double dUpdate = 0;

		long previousTime = System.nanoTime();
		long now;

		while (true) {
			now = System.nanoTime();

			dFrame += (now - previousTime) / timePerFrame;
			dUpdate += (now - previousTime) / timePerUpdate;
//			System.out.println(now-previousTime);
			enemyTime += (now-previousTime);
			previousTime = now;

			if (dFrame >= 1) {
				dFrame--;
				gamePanel.repaint();
				fps++;
			}

			if (dUpdate >= 1 ) {
				dUpdate--;
				update();
				ups++;
			}
			
			// add enemy after each 2 seconds
			if(enemyTime > 2000000000 && active ) {
				enemyTime-=2000000000;
				addEnemy();
			}

			try {
				Thread.sleep(3);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Adds a new enemy to the game .
	 */
	private void addEnemy() {
			int randomNum = random.nextInt(19);
			if (randomNum >= 0 && randomNum <= 3) {
//				System.out.println("bat");
				enemyArrayList.add(new Bat(entityImageSource.BAT));
			} else if (randomNum >= 4 && randomNum <= 7) {
//				System.out.println("pig");
				enemyArrayList.add(new Pig(entityImageSource.PIG));
			} else if (randomNum >= 8 && randomNum <= 11) {
//				System.out.println("rino");
				enemyArrayList.add(new Rino(entityImageSource.RINO));
			} else if (randomNum >= 12 && randomNum <= 16) {
//				System.out.println("coin");
				enemyArrayList.add(new GoldCoin(entityImageSource.COIN));
			} else if (randomNum >= 17 && randomNum <= 18) {
//				System.out.println("life");
				enemyArrayList.add(new Life(entityImageSource.LIFE));
			}
	}


	/**
	 * Updates the game state including background and entity positions.
	 */
	private void update() {
		// image of background
		backgroundImageSource.changePicturePosition(backgroundImageSource.BACKGROUND_IMAGE);

		// draw characters
		for (int i = enemyArrayList.size() - 1; i >= 0; i--) {
			if (enemyArrayList.get(i).x < -150) {
				enemyArrayList.remove(i);// if enemy out of game Panel
			} else {
				enemyArrayList.get(i).updateEnemyPosition();
			}

			// check coalition with Dinosaur
			if (!enemyArrayList.isEmpty() && !enemyArrayList.get(i).collide && active) {
				boolean collition = dinosaur.isColide(enemyArrayList.get(i));
				if (collition) {
					if (enemyArrayList.get(i) instanceof GoldCoin) {
						totalScore++;
						statusBar.updateScore(totalScore);
						enemyArrayList.remove(i);
					} else if (enemyArrayList.get(i) instanceof Life) {
						if (totalLife < maxLife) {
							totalLife++;
							statusBar.updateLife(totalLife);
							enemyArrayList.remove(i);
							continue;
						}
					} else if (dinosaur.state != Dinosaur.State.HIT) {
						EventSounds.playEventSound(EventSounds.HURT);
//						System.out.println(enemyArrayList.get(i));
						totalLife--;
						enemyArrayList.get(i).collide = true;
						statusBar.updateLife(totalLife);
						if (totalLife == 0) {
							endGame();
							return;
						}
						dinosaur.state = Dinosaur.State.HIT;
						dinosaur.imageState = 0;
						dinosaur.nextFrameTick = 1;
					}
				}
			}
		}
		if (dinosaur != null)
			dinosaur.updateDino();
	}

	/**
	 * Ends the game and displays the game over menu.
	 */
	private void endGame() {
		active = false;

		for (Timer timer : timers) {
			timer.cancel();
		}

		enemyArrayList.clear();

		dinosaur = null;

		if (totalScore > highestScore) {
			highestScore = totalScore;
		}
		gamePanel.add(new GameOverMenu(this), BorderLayout.CENTER);
		gameFrame.revalidate();
		gamePanel.repaint();
//		System.out.println("hello 3");
	}
	
	/**
	 * Draws all game entities on the given Graphics context.
	 * 
	 * @param g The Graphics context to draw on.
	 */
	public void drawEntities(Graphics g) {
		for (int i = 0; i < enemyArrayList.size(); i++) {
			enemyArrayList.get(i).drawEnemy(g);
		}
		if (dinosaur != null)
			dinosaur.drawDino(g);
	}

	/**
	 * @return the totalScore
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * @return the highestScore
	 */
	public int getHighestScore() {
		if (totalScore > highestScore) {
			highestScore = totalScore;
		}
		return highestScore;
	}

}
