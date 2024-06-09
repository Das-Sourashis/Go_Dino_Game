package widgets;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * The DinoPanel class represents a panel displaying a Dancing dinosaur animation.
 * It extends JPanel and implements Runnable to support animation.
 * 
 * Author: Sourashis Das
 */

public class DinoPanel extends JPanel implements Runnable {
    private List<BufferedImage> sprites;
    private List<Integer> xPositions;
    private int yPosition, width, height, scale, imageState;
    private int dx = 4; // Separation
    Thread thread;
    private boolean running = true;

    /**
     * Constructs a new DinoPanel with the specified list of dinosaur sprites.
     * 
     * @param img The list of dinosaur sprites.
     */
    public DinoPanel(List<BufferedImage> img) {
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 10));
        imageState = 0;
        scale = 2;
        xPositions = new ArrayList<>();
        yPosition = 5; // Initial y position for all sprites

        sprites = img;
        height = sprites.get(0).getHeight() * scale;
        width = sprites.get(0).getWidth() * scale;

        for (int i = 0; i < 6; i++) { // number of Dancing dinosaur
            xPositions.add(i * (width + dx));
        }

        thread = new Thread(this);
        thread.start();
    }

    /**
     * @return the width of the panel
     */
    public int getWidth() {
        return width * xPositions.size();
    }

    /**
     * @return the height of the panel
     */
    public int getHeight() {
        return height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 10));
        g.fillRect(0, 0, getHeight(), getWidth());

        for (int i = 0; i < xPositions.size(); i++) {
            g.drawImage(sprites.get(imageState), xPositions.get(i), yPosition, height, width, null);
        }
    }

    @Override
    public void run() {
        while (running) {
            imageState = (imageState + 1) % sprites.size();
            repaint();
            try {
                thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the animation thread.
     */
    public void Stop() {
        running = false;
    }
}
