package widgets;

import mainPackage.Game;
import utilz.Constants.SaveScore;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The GameOverMenu class represents the game over menu displayed when the game ends.
 * It provides options to restart the game or exit the application.
 * 
 * Author: Sourashis Das
 */


public class GameOverMenu extends JPanel {
    Game game;
    DinoPanel dinoPanel;

    /**
     * Constructs a new GameOverMenu with the specified game instance.
     * 
     * @param game The game instance associated with the game over menu.
     */
    public GameOverMenu(Game game) {
        this.game = game;
        setLayout(new GridBagLayout());
        setOpaque(true);

        // Create a blurred background effect
        setBackground(new Color(0, 0, 0, 100));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        container.setBackground(new Color(0, 0, 0, 150));
        container.setOpaque(true);
        container.setAlignmentX(Component.CENTER_ALIGNMENT);

        // DinoPanel
        dinoPanel = new DinoPanel(game.entityImageSource.DINODANCEC.images);
        dinoPanel.setPreferredSize(new Dimension(dinoPanel.getWidth(), dinoPanel.getHeight()+10)); // Adjust size as needed
        dinoPanel.setOpaque(true);

        // Game Over Label
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Score Label
        JLabel scoreLabel = new JLabel("Your Score : " + game.getTotalScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Highest Score Label
        JLabel maxscoreLabel = new JLabel("Highest Score : " + game.getHighestScore());
        maxscoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        maxscoreLabel.setForeground(Color.WHITE);
        maxscoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Restart Button
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 30));
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setBackground(Color.BLUE);
        restartButton.setForeground(Color.WHITE);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the game and restart
                game.clearGame();
                game.ResetupGame();
            }
        });

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setBackground(Color.GREEN);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the highest score and exit the application
                SaveScore.saveHighestScore(game.getHighestScore());
                game.backgroundMusic.stop();
                dinoPanel.Stop();
                System.exit(0);
            }
        });

        // Add components to the container
        container.add(dinoPanel);
        container.add(Box.createVerticalStrut(10)); // Separation
        container.add(gameOverLabel);
        container.add(Box.createVerticalStrut(10));
        container.add(scoreLabel);
        container.add(Box.createVerticalStrut(10));
        container.add(maxscoreLabel);
        container.add(Box.createVerticalStrut(10));
        container.add(restartButton);
        container.add(Box.createVerticalStrut(10));
        container.add(exitButton);

        // Add container to the panel
        add(container);
    }
}
