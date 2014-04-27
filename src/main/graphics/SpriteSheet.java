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

import java.awt.image.BufferedImage;

/**
 *
 * @author Patrick Kerr
 */
public final class SpriteSheet {
    
    private final BufferedImage image;
    
    /**
     * Creates a new SpriteSheet with the specified BufferedImage
     * @param image
     */
    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }
    
    /**
     * Used to get the sub images 
     * @param col Column containing the sub image wanted
     * @param row Row containing the sub image wanted
     * @param width Width of the sub images on the sprite sheet
     * @param height Height of the sub images on the sprite sheet
     * @return A BufferedImage of the size specified, from the SpriteSheet at the Column and Row specified.
     */
    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img;
        img = image.getSubimage((col - 1) * width, (row - 1) * height, width, height);
        return img;
    }
}
