
package mainPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * The MainFrame class represents the main frame of the game window.
 * It extends JFrame and is responsible for setting up the game window.
 * 
 * Author: Sourashis Das
 */
public class MainFrame extends JFrame {
    /**
     * Constructs a new MainFrame with the specified game panel.
     * 
     * @param gamepanel The JPanel representing the game panel.
     */
    MainFrame(JPanel gamepanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Close operation
        setTitle("Go Dino Game"); // Set window title
        add(gamepanel); // Add game panel to the frame
        pack(); // Pack components
        setLocation(100, 50); // Set location
        setResizable(false); // Disable resizing
        setVisible(true); // Set visibility
    }
}
