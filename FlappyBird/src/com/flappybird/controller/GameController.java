package com.flappybird.controller;

import com.flappybird.model.Bird;
import com.flappybird.model.Pipe;
import com.flappybird.model.GameState;
import com.flappybird.view.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

/**
 * Controls the game logic, user inputs, and game state updates for the Flappy Bird game.
 * The GameController manages bird movement, pipe generation, collision detection, and scoring.
 *
 * <p>
 * This class uses {@link Timer} to handle the game loop and pipe generation,
 * ensuring smooth gameplay mechanics.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     GameController controller = new GameController(gamePanel, bird, pipes, gameState);
 * </pre>
 * </p>
 *
 * @author
 * Omkar Kshirsagar
 * @since 2024-12-02
 */
public class GameController implements ActionListener, KeyListener {
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private GameState gameState;
    private GamePanel gamePanel;
    private Timer gameLoop;
    private Timer pipeTimer;
    private int velocityY = 0;
    private int gravity = 1;
    private Random random = new Random();

    /**
     * Constructs a GameController to manage the Flappy Bird game's logic and inputs.
     *
     * <p>
     * Initializes the game loop timer (60 FPS) and pipe generation timer (every 1.5 seconds).
     * Attaches the KeyListener to the game panel for user input handling.
     * </p>
     *
     * @param gamePanel The {@link GamePanel} object displaying the game visuals.
     * @param bird The {@link Bird} object controlled by the player.
     * @param pipes The {@link ArrayList} of {@link Pipe} objects acting as obstacles.
     * @param gameState The {@link GameState} representing the current game state.
     */
    public GameController(GamePanel gamePanel, Bird bird, ArrayList<Pipe> pipes, GameState gameState) {
        this.gamePanel = gamePanel;
        this.bird = bird;
        this.pipes = pipes;
        this.gameState = gameState;

        this.gamePanel.addKeyListener(this);
        this.gamePanel.setFocusable(true);

        // Game loop timer (60 FPS)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Pipe placement timer (every 1.5 seconds)
        pipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipeTimer.start();
    }

    /**
     * Adds new pipes to the game at random heights.
     * This method is called every 1.5 seconds by the pipeTimer.
     */
    private void placePipes() {
        int pipeHeight = 512;
        int randomPipeY = -128 - random.nextInt(pipeHeight / 2);
        int openingSpace = gamePanel.getHeight() / 4;

        // Create and add top and bottom pipes
        Pipe topPipe = new Pipe(gamePanel.getWidth(), randomPipeY, 64, pipeHeight, gamePanel.getTopPipeImg());
        Pipe bottomPipe = new Pipe(gamePanel.getWidth(), randomPipeY + pipeHeight + openingSpace, 64, pipeHeight, gamePanel.getBottomPipeImg());

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    /**
     * Updates the game mechanics such as bird and pipe movement, collision detection, and scoring.
     * This method is called repeatedly by the game loop timer.
     */
    private void move() {
        // Update bird's vertical position
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0); // Prevent bird from going above the screen

        // Update pipes and check for collisions or scoring
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x -= 4; // Move pipes to the left

            if (bird.x > pipe.x + pipe.width && !pipe.passed) {
                gameState.incrementScore(); // Increment score when bird passes a pipe
                pipe.passed = true;
            }

            if (checkCollision(bird, pipe)) {
                gameState.gameOver = true; // End game on collision
            }
        }

        // End game if the bird falls below the screen
        if (bird.y > gamePanel.getHeight()) {
            gameState.gameOver = true;
        }
    }

    /**
     * Checks whether the bird has collided with a pipe.
     *
     * @param bird The {@link Bird} object.
     * @param pipe The {@link Pipe} object.
     * @return {@code true} if the bird has collided with the pipe, {@code false} otherwise.
     */
    private boolean checkCollision(Bird bird, Pipe pipe) {
        return bird.x < pipe.x + pipe.width &&
                bird.x + bird.width > pipe.x &&
                bird.y < pipe.y + pipe.height &&
                bird.y + bird.height > pipe.y;
    }

    /**
     * Handles game loop actions such as moving game objects and repainting the screen.
     * Stops the game if the game over condition is met.
     *
     * @param e The {@link ActionEvent} object triggered by the game loop timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        gamePanel.repaint(); // Update the game panel
        if (gameState.gameOver) {
            pipeTimer.stop();  // Stop pipe generation
            gameLoop.stop();   // Stop game loop
        }
    }

    /**
     * Handles key press events.
     *
     * <ul>
     * <li>Pressing the spacebar makes the bird jump.</li>
     * <li>Restarts the game if the game is over.</li>
     * </ul>
     *
     * @param e The {@link KeyEvent} object representing the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;  // Bird jump

            if (gameState.gameOver) {
                // Reset game state
                bird.y = gamePanel.getHeight() / 2;
                velocityY = 0;
                pipes.clear();
                gameState.reset();
                gameLoop.start();   // Restart game loop
                pipeTimer.start();  // Restart pipe generation
            }
        }
    }

    /**
     * Unused method from the {@link KeyListener} interface.
     *
     * @param e The {@link KeyEvent} object representing the key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Unused method from the {@link KeyListener} interface.
     *
     * @param e The {@link KeyEvent} object representing the key released event.
     */
    @Override
    public void keyReleased(KeyEvent e) {}
}
