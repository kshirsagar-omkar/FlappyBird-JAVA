package com.flappybird;

import com.flappybird.model.Bird;
import com.flappybird.model.Pipe;
import com.flappybird.model.GameState;
import com.flappybird.view.GamePanel;
import com.flappybird.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Main entry point for the Flappy Bird game.
 * Initializes the game components such as the bird, pipes, and game panel, and sets up the JFrame for the game.
 * This class is responsible for creating the game window and launching the game.
 *
 * <p>
 * This program is a simple implementation of the classic Flappy Bird game.
 * The player controls the bird's movement using the spacebar to pass through pipes.
 * </p>
 *
 * @author Omkar Kshirsagar
 * @since 2024-12-02
 */
public class App {

    /**
     * The main method to launch the Flappy Bird game.
     * It initializes the game window, sets up the game objects, and starts the game loop.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Set the dimensions of the game board
        int boardWidth = 360;
        int boardHeight = 640;

        // Create and configure the main game window (JFrame)
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false);         // Disable resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close

        // Load the bird image
        Image birdImg = new ImageIcon(App.class.getResource("/resources/flappybird.png")).getImage();

        // Initialize the game components
        Bird bird = new Bird(50, boardHeight / 2, 34, 24, birdImg); // Bird starting position and size
        ArrayList<Pipe> pipes = new ArrayList<>();                 // Empty list of pipes
        GameState gameState = new GameState();                     // Tracks the game's state and score

        // Create the game panel and controller
        GamePanel gamePanel = new GamePanel(bird, pipes, gameState);
        GameController gameController = new GameController(gamePanel, bird, pipes, gameState);

        // Add the game panel to the frame
        frame.add(gamePanel);
        frame.pack();                 // Adjust the frame to fit its components
        gamePanel.requestFocus();     // Give focus to the game panel for key events
        frame.setVisible(true);       // Make the window visible
    }
}
