package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Dinosaur;

/**
 * The KeyboardInputs class implements KeyListener to handle keyboard input in the game.
 * 
 * Author: Sourashis Das
 */

public class KeyboardInputs implements KeyListener {

    /**
     * Invoked when a key has been typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed for key typing events
    }

    /**
     * Invoked when a key has been pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Respond to specific key presses
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                Dinosaur.isJump = true; // Set the dinosaur to jump when the up arrow key is pressed
                break;
            default:
                break;
        }
    }

    /**
     * Invoked when a key has been released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // No action needed for key release events
    }
}
