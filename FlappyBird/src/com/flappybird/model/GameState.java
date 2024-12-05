package com.flappybird.model;

/**
 * Represents the current state of the Flappy Bird game.
 * The GameState class tracks the game's progress, including whether the game is over
 * and the player's current score. It also provides functionality to reset the game
 * and update the score when the player progresses.
 *
 * <p>
 * This class serves as a centralized place to manage the game's status and is updated
 * by the game controller as the game progresses.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     GameState gameState = new GameState();
 *     gameState.incrementScore();
 *     System.out.println(gameState.score); // Outputs: 0.5
 * </pre>
 * </p>
 *
 * @author
 * Omkar Kshirsagar
 * @since 2024-12-02
 */
public class GameState {
    /**
     * A flag indicating whether the game is over.
     * If {@code true}, the game has ended; otherwise, it is still in progress.
     */
    public boolean gameOver;

    /**
     * The player's current score in the game.
     * Initially set to 0 and incremented as the player progresses.
     */
    public double score;

    /**
     * Constructs a new GameState with the initial values.
     * The game starts with {@code gameOver} set to {@code false} and the score set to 0.
     */
    public GameState() {
        this.gameOver = false;
        this.score = 0;
    }

    /**
     * Resets the game state to its initial values.
     * <p>
     * This method is called to restart the game, setting {@code gameOver} to {@code false}
     * and resetting the score to 0.
     * </p>
     */
    public void reset() {
        this.gameOver = false;
        this.score = 0;
    }

    /**
     * Increments the player's score by 0.5 points.
     * <p>
     * This method is called whenever the player successfully passes a pair of pipes.
     * </p>
     */
    public void incrementScore() {
        this.score += 0.5;
    }
}
