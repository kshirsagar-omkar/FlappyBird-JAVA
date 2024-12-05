package com.flappybird.model;

import java.awt.Image;

/**
 * Represents the bird in the Flappy Bird game.
 * This class defines the bird's properties such as its position, size, and appearance,
 * and provides a way to instantiate a bird object with the required attributes.
 *
 * <p>
 * The bird is the main character controlled by the player.
 * Its movement is governed by user input and game physics like gravity.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Image birdImage = new ImageIcon("bird.png").getImage();
 *     Bird bird = new Bird(50, 200, 34, 24, birdImage);
 * </pre>
 * </p>
 *
 * @author Omkar
 * @since 2024-12-02
 */
public class Bird {
    /**
     * The x-coordinate of the bird's position on the game screen.
     */
    public int x;

    /**
     * The y-coordinate of the bird's position on the game screen.
     */
    public int y;

    /**
     * The width of the bird's image.
     */
    public int width;

    /**
     * The height of the bird's image.
     */
    public int height;

    /**
     * The image representing the bird.
     */
    public Image img;

    /**
     * Constructs a new Bird with the specified position, size, and image.
     *
     * @param x      The x-coordinate of the bird's initial position.
     * @param y      The y-coordinate of the bird's initial position.
     * @param width  The width of the bird.
     * @param height The height of the bird.
     * @param img    The image representing the bird.
     */
    public Bird(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
}
