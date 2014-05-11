/*
 * The MIT License
 *
 * Copyright 2014 Patrick Kerr.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package main.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This class is in charge of animating images from sprite sheets
 * @author Patrick Kerr
 */
public final class Animation {
    
    private int speed;
    private final int frames;
    
    private int index = 0;
    private int count = 0;
    
    private final BufferedImage[] images;
    private BufferedImage currentImg;
    
    /**
     * Creates a new animation
     * @param speed Animation speed, More = Slower; Fastest = 1;
     * @param args As many BufferedImages as needed for the animation
     */
    public Animation(int speed, BufferedImage... args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        System.arraycopy(args, 0, images, 0, args.length);
        frames = args.length;
        currentImg = images[count];
    }
    
    /**
     * Runs the animation (Preferably during the tick method)
     * @return The current frame index
     */
    public int runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
            return count;
        }
        return -1;
    }
    
    private void nextFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i) currentImg = images[i];
        }
        count++;
        if (count >= frames) count = 0;
    }
    
    /**
     * Draws the animation (Preferably during the render method)
     * @param g Graphics on which to draw the animation
     * @param x Horizontal position of the animation
     * @param y Vertical position of the animation
     */
    public void drawAnimation (Graphics g, int x, int y) {
        g.drawImage(currentImg, x, y, null);
    }
    
    /**
     * Draws a scaled version of the animation (Preferably during the render method)
     * @param g Graphics on which to draw the animation
     * @param x Horizontal position of the animation
     * @param y Vertical position of the animation
     * @param scaleX Desired horizontal size
     * @param scaleY Desired vertical size
     */
    public void drawAnimation (Graphics g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }

    /**
     * Changes the speed of the animation
     * @param speed Desired speed, More = Slower; Fastest = 1;
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * @return The length of the animation (in frames)
     */
    public int getLength() {
        return frames;
    }
}
