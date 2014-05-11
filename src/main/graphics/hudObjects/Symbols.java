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
package main.graphics.hudObjects;

import java.awt.Graphics;
import main.graphics.Texture;
import main.Game;

/**
 * This class is used to draw Symbols and numbers for the HUD
 * @author Patrick Kerr
 */
public final class Symbols {
    private final static Texture tex = Game.getTexture();
    /**
     * Draws a symbol on the screen
     * @param g Graphics on which to draw
     * @param number Number to draw
     * @param x Horizontal position of the last digit, extra digits added to the left
     * @param y Vertical position of the symbol
     * @param sizeX Horizontal size
     * @param sizeY Vertical Size
     */
    public static void drawNumber(Graphics g, int number, int x, int y, int sizeX, int sizeY) {
        // Draw the selected symbol resized
        if (number > 9) { // Draw 2 digits
//            g.drawImage(tex.symbol[symbol / 10], x, y + 8, 16, 16, null);
            g.drawImage(tex.symbol[number % 10], x, y, sizeX, sizeY, null);
            drawNumber(g, number / 10, x - sizeX, y, sizeX, sizeY);
        }
        else if (number >= 0) { // Draw 1 digit
            g.drawImage(tex.symbol[number], x, y, sizeX, sizeY, null);
        }
    }

    /**
     * Draws a multiplier symbol ( x ) on the screen
     * @param g Graphics on which to draw
     * @param x Horizontal position of the last digit, extra digits added to the left
     * @param y Vertical position of the symbol
     * @param sizeX Horizontal size
     * @param sizeY Vertical Size
     */
    public static void drawMultiplier(Graphics g, int x, int y, int sizeX, int sizeY) {
        g.drawImage(tex.symbol[11], x, y, sizeX, sizeY, null);
    }
    /**
     * Draws a divider symbol ( / ) on the screen
     * @param g Graphics on which to draw
     * @param x Horizontal position of the last digit, extra digits added to the left
     * @param y Vertical position of the symbol
     * @param sizeX Horizontal size
     * @param sizeY Vertical Size
     */
    public static void drawDivider(Graphics g, int x, int y, int sizeX, int sizeY) {
        g.drawImage(tex.symbol[10], x, y, sizeX, sizeY, null);
    }
}
