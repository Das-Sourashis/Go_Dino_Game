package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import entity.Dinosaur;

/**
 * The MouseInputs class implements MouseListener to handle mouse input in the game.
 * 
 * Author: Sourashis Das
 */


public class MouseInputs implements MouseListener {

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if the left mouse button is clicked
        if (e.getButton() == MouseEvent.BUTTON1) {
            Dinosaur.isJump = true; // Set the dinosaur to jump when the left mouse button is clicked
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // No action needed for mouse press events
    }

    /**
     * Invoked when a mouse button has been released on a component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // No action needed for mouse release events
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // No action needed for mouse enter events
    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // No action needed for mouse exit events
    }
}
