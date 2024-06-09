package widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mainPackage.Game;
import utilz.Constants.GetMaxScore;
import utilz.Constants.SaveScore;

/**
 * The StatusBar class represents the status bar in the game interface.
 * It displays the player's score, highest score, and remaining lives.
 * 
 * Author: Sourashis Das
 */


public class StatusBar extends JPanel {
    ArrayList<BufferedImage> imgBufferedImage;
    JLabel scoreLabel;
    JPanel lifePanel;
    private int totalLife;

    /**
     * Constructs a new StatusBar with the specified game instance.
     * 
     * @param game The game instance to associate with the status bar.
     */
    public StatusBar(Game game) {
        imgBufferedImage = game.entityImageSource.STATUSHEART.images;
        this.totalLife = game.totalLife;
        setLayout(new BorderLayout());
        setOpaque(false);

        // Score Label
        scoreLabel = new JLabel("Your Score: " + 0);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Dubai Medium", Font.BOLD, 30));
        
        // High Score Label
        JLabel highScoreLabel = new JLabel("Highest Score: " + GetMaxScore.MAX_SCORE);
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setFont(new Font("Consolas", Font.BOLD, 20));

        // Score Panel
        JPanel scorePanel = new JPanel(new GridLayout(2, 1));
        scorePanel.setOpaque(false);
        scorePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0)); // 100px padding to the left
        scorePanel.add(scoreLabel);
        scorePanel.add(highScoreLabel);
        
        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.GREEN);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the highest score and exit the game
                SaveScore.saveHighestScore(game.getHighestScore());
                game.backgroundMusic.stop(); // Stop background music
                System.exit(0); // Exit the game
            }
        });

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(exitButton);

        // Life Panel
        lifePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        lifePanel.setOpaque(false);
        for (int i = 0; i < totalLife; i++) {
            JLabel heartLabel = new JLabel(new ImageIcon(imgBufferedImage.get(0)));
            lifePanel.add(heartLabel);
        }

        // Add components to the status bar
        add(buttonPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.WEST);
        add(lifePanel, BorderLayout.EAST);
    }

    /**
     * Updates the life display on the status bar.
     * 
     * @param life The number of lives remaining.
     */
    public void updateLife(int life) {
        lifePanel.removeAll();

        for (int i = 0; i < totalLife; i++) {
            BufferedImage icon = i < life ? imgBufferedImage.get(0) : imgBufferedImage.get(1);
            JLabel heartLabel = new JLabel(new ImageIcon(icon));
            lifePanel.add(heartLabel);
        }

        lifePanel.revalidate();
        lifePanel.repaint();
    }

    /**
     * Updates the score display on the status bar.
     * 
     * @param score The player's current score.
     */
    public void updateScore(int score) {
        scoreLabel.setText("Your Score: " + score);
        scoreLabel.revalidate();
        scoreLabel.repaint();
    }
}
