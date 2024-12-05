package com.flappybird.model;

import java.awt.Image;

/**
 * Represents a pipe obstacle in the Flappy Bird game.
 * The Pipe class defines the position, size, and appearance of the pipe,
 * as well as a flag to track if the bird has successfully passed it.
 *
 * <p>
 * Pipes are a core obstacle in the game, and the player needs to navigate the bird
 * between the gaps formed by the top and bottom pipes. Each pipe has its own dimensions
 * and image representation.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Image pipeImage = new ImageIcon("pipe.png").getImage();
 *     Pipe pipe = new Pipe(300, 200, 64, 512, pipeImage);
 * </pre>
 * </p>
 *
 * @author
 * Omkar Kshirsagar
 * @since 2024-12-02
 */
public class Pipe {
    /**
     * The x-coordinate of the pipe's position on the game screen.
     */
    public int x;

    /**
     * The y-coordinate of the pipe's position on the game screen.
     */
    public int y;

    /**
     * The width of the pipe.
     */
    public int width;

    /**
     * The height of the pipe.
     */
    public int height;

    /**
     * The image representing the pipe.
     */
    public Image img;

    /**
     * A flag to indicate if the bird has passed this pipe.
     * This is used to update the game score.
     */
    public boolean passed;

    /**
     * Constructs a new Pipe with the specified position, size, and image.
     *
     * @param x      The x-coordinate of the pipe's initial position.
     * @param y      The y-coordinate of the pipe's initial position.
     * @param width  The width of the pipe.
     * @param height The height of the pipe.
     * @param img    The image representing the pipe.
     */
    public Pipe(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.passed = false;
    }
}
