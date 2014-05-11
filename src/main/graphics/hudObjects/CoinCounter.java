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

import main.graphics.*;
import java.awt.Graphics;
import main.framework.gameObjects.livingEntities.Player;
import main.Game;

/**
 * This class is used to draw a coin counter for the HUD
 * @author Patrick Kerr
 */
public final class CoinCounter {
    private final static Texture tex = Game.getTexture();
    private final static Animation coin = new Animation(5, tex.coin[0], tex.coin[1], tex.coin[2], tex.coin[3], tex.coin[4], tex.coin[5], tex.coin[6], tex.coin[7]);

    /**
     * Draws the coin counter on the screen
     * @param g Graphics on which to draw
     * @param player Player to get the coin count from
     * @param x Horizontal position of the counter
     * @param y Vertical position of the counter
     * @param size Size of the counter
     */
    public static void draw(Graphics g, Player player, int x, int y, int size) {
        coin.drawAnimation(g, x - size, y, size, size);
        Symbols.drawMultiplier(g, (int)(x - size * 1.8), y, size, size);
        Symbols.drawNumber(g, player.getCoinCount(), (int)(x - size * 2.6), y, size, size);
    }
    
    /**
     * Tick method for the coin to spin
     */
    public static void tick() {
        coin.runAnimation();
    }
}
