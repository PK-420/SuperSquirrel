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

import main.framework.GameObject;
import main.Game;

/**
 * This class is used to move the view smoothly, following the player
 * @author Patrick Kerr
 */
public final class Camera {
    private float x, y;
    
    /**
     * Creates the scrolling camera Object
     * @param x Starting horizontal position
     * @param y Starting vertical position
     */
    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Moves the camera according to player's position
     * @param player Player on which to keep focus
     */
    public void tick(GameObject player) {
//         x = -player.getX() + Game.WIDTH / 2;
        if (player != null) {
            x += ((player.getX() - (Game.WIDTH / 2)) - x) * 0.05; // Tweening effect
        }
    }

    /**
     * @return The current horizontal position of the camera
     */
    public float getX() {
        return x;
    }
    
    /**
     * @return The current vertical position of the camera
     */
    public float getY() {
        return y;
    }
}
