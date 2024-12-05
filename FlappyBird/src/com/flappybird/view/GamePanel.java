package com.flappybird.view;

import com.flappybird.model.Bird;
import com.flappybird.model.Pipe;
import com.flappybird.model.GameState;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * Represents the game panel responsible for rendering all visual elements of the game.
 * The GamePanel class handles the drawing of the bird, pipes, background, and score
 * based on the current game state.
 *
 * <p>
 * This class extends {@link JPanel} and serves as the primary display component for the game.
 * It also loads the necessary resources like images for the background, bird, and pipes.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Bird bird = new Bird(50, 200, 34, 24, birdImage);
 *     List&lt;Pipe&gt; pipes = new ArrayList<>();
 *     GameState gameState = new GameState();
 *     GamePanel gamePanel = new GamePanel(bird, pipes, gameState);
 * </pre>
 * </p>
 *
 * @author
 * Omkar Kshirsagar
 * @since 2024-12-02
 */
public class GamePanel extends JPanel {
    private int boardWidth = 360;
    private int boardHeight = 640;

    /**
     * The background image of the game.
     */
    public Image backgroundImg;

    /**
     * The image representing the bird.
     */
    public Image birdImg;

    /**
     * The image representing the top pipe.
     */
    public Image topPipeImg;

    /**
     * The image representing the bottom pipe.
     */
    public Image bottomPipeImg;

    private Bird bird;
    private List<Pipe> pipes;
    private GameState gameState;

    /**
     * Constructs a new GamePanel with the specified bird, pipes, and game state.
     * Loads the images required for rendering the game elements.
     *
     * @param bird The bird object representing the player.
     * @param pipes The list of pipe objects representing obstacles.
     * @param gameState The current state of the game.
     */
    public GamePanel(Bird bird, List<Pipe> pipes, GameState gameState) {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.bird = bird;
        this.pipes = pipes;
        this.gameState = gameState;

        // Load images
        backgroundImg = new ImageIcon(getClass().getResource("/resources/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/resources/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/resources/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/resources/bottompipe.png")).getImage();
    }

    /**
     * Paints all the game components including the background, bird, pipes, and score.
     * This method is automatically called when the panel is repainted.
     *
     * @param g The {@link Graphics} object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Draws the game elements such as the background, bird, pipes, and score.
     *
     * @param g The {@link Graphics} object used for drawing.
     */
    private void draw(Graphics g) {
        // Background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, this);

        // Bird
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, this);

        // Pipes
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, this);
        }

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameState.gameOver) {
            g.drawString("Game Over: " + (int) gameState.score, 10, 35);
        } else {
            g.drawString(String.valueOf((int) gameState.score), 10, 35);
        }
    }

    /**
     * Retrieves the image for the top pipe obstacle.
     *
     * @return The {@link Image} representing the top pipe.
     */
    public Image getTopPipeImg() {
        return topPipeImg;
    }

    /**
     * Retrieves the image for the bottom pipe obstacle.
     *
     * @return The {@link Image} representing the bottom pipe.
     */
    public Image getBottomPipeImg() {
        return bottomPipeImg;
    }
}
