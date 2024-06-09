package sounds;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * The BackgroundMusic class manages the background music in the game.
 * It loads a sound file and continuously plays it in a loop.
 * This class implements the Runnable interface to allow the music to play in a separate thread.
 * It provides methods to control the playback, such as starting, stopping, and checking if the music is currently playing.
 * 
 * Author: Sourashis Das
 */
public class BackgroundMusic implements Runnable {
    private Clip clip;
    private File soundFile;
    private volatile boolean play;
    private Thread thread;

    /**
     * Constructs a new BackgroundMusic object.
     */
    public BackgroundMusic() {
        play = true;
        readFile();
    }

    /**
     * Reads the sound file.
     */
    private void readFile() {
        try {
            soundFile = new File("asset/audio/8BitPlatformerLoop.wav");
            if (!soundFile.exists()) {
                System.out.println("Sound file not found: " + soundFile.getAbsolutePath());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the music is currently playing.
     * 
     * @return true if music is playing, false otherwise
     */
    public boolean isPlaying() {
        return play;
    }

    /**
     * Sets the play state of the music.
     * 
     * @param play true to play the music, false to stop
     */
    public void setPlay(boolean play) {
        this.play = play;
        if (!play) {
            stopBackgroundMusic();
        }
    }

    /**
     * Plays the background music.
     */
    public void playBackgroundMusic() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
            ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f); // change volume
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the background music.
     */
    public void stopBackgroundMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    /**
     * Starts the background music thread.
     */
    public void start() {
        if (thread == null || !thread.isAlive()) {
            play = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Stops the background music thread.
     */
    public void stop() {
        setPlay(false);
        if (thread != null && thread.isAlive()) {
            thread.interrupt(); 
        }
    }

    @Override
    public void run() {
        while (play) {
            playBackgroundMusic();
            try {
                Thread.sleep(Long.MAX_VALUE); // Keep the thread alive
            } catch (InterruptedException e) {
                // Thread.currentThread().interrupt(); // Restore interrupted status
            }
            stopBackgroundMusic();
        }
    }
}
