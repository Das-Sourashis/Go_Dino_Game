package mainPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utilz.Constants.GameValues;

/**
 * The MainPanel class represents the main panel of the game window.
 * It extends JPanel and is responsible for rendering the game and handling user inputs.
 * 
 * Author: Sourashis Das
 */

public class MainPanel extends JPanel {
    public Game game; // Reference to the game instance
    private int height = GameValues.HEIGHT; // Height of the panel
    public int width = GameValues.WIDTH; // Width of the panel
    JLabel mouselLabel; // Label for mouse input

    /**
     * Constructs a new MainPanel with the specified game.
     * 
     * @param game The Game instance associated with this panel.
     */
    MainPanel(Game game) {
        this.game = game;

        // Set panel size
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        // Initialize mouse label and add mouse listener
        mouselLabel = new JLabel(new ImageIcon("asset/images/jump/arrow-125-xxl.png"));
        mouselLabel.addMouseListener(new MouseInputs());

        // Add keyboard input listener
        addKeyListener(new KeyboardInputs());

        setFocusable(true); // Set panel focusable
        requestFocusInWindow(); // Request focus
        setLayout(new BorderLayout()); // Set layout

        // Add status bar to the top of the panel
        add(game.statusBar, BorderLayout.NORTH);

        // Add mouse label to the bottom of the panel
        add(mouselLabel, BorderLayout.SOUTH);
    }
    
    /**
     * Adds the mouse label to the panel.
     */
    public void addLabel() {
        mouselLabel = new JLabel(new ImageIcon("asset/images/jump/arrow-125-xxl.png"));
        mouselLabel.addMouseListener(new MouseInputs());
        add(mouselLabel, BorderLayout.SOUTH);
        revalidate(); // Revalidate the panel
        repaint(); // Repaint the panel
    }

    /**
     * Renders the components of the panel.
     * 
     * @param g The Graphics object used for rendering.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call super method
        // Render background images
        for (int i = 0; i < game.backgroundImageSource.BACKGROUND_IMAGE.size(); i++) {
            g.drawImage(game.backgroundImageSource.BACKGROUND_IMAGE.get(i).image,
                    (int) game.backgroundImageSource.BACKGROUND_IMAGE.get(i).x, 0, width, height, null);
        }
        // Render game entities
        game.drawEntities(g);
    }
}
