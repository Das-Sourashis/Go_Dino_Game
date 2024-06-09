package sounds;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The EventSounds class manages the playback of event sounds in the game, such as jumping and getting hurt.
 * It loads the sound files for each event and provides methods to play them.
 * 
 * Author: Sourashis Das
 */
public class EventSounds {
    public static final int JUMP = 0;
    public static final int HURT = 1;

    private static int noOfEventAudio = 2;
    private static int event;

    private static ArrayList<Clip> eventClips = new ArrayList<>();
    private static String[] eventClipNames = { "asset/audio/jump14.wav", "asset/audio/Hurt7.wav" };

    /**
     * Static block to load the sound files.
     */
    static {
        getFiles();
    }

    /**
     * Loads the sound files for each event.
     */
    private static void getFiles() {
        for (int i = 0; i < noOfEventAudio; i++) {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(eventClipNames[i]));
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                eventClips.add(clip);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Plays the event sound corresponding to the given event type.
     * 
     * @param event The type of event sound to play (JUMP or HURT)
     */
    public static void playEventSound(int event) {
        new Thread(() -> playSound(event)).start();
    }

    /**
     * Plays the specified event sound.
     * 
     * @param event The type of event sound to play (JUMP or HURT)
     */
    private static void playSound(int event) {
        switch (event) {
            case JUMP:
                eventClips.get(JUMP).start();
                eventClips.get(JUMP).setMicrosecondPosition(0);
                break;

            case HURT:
                eventClips.get(HURT).start();
                eventClips.get(HURT).setMicrosecondPosition(0);
                break;

            default:
                break;
        }
    }
}
