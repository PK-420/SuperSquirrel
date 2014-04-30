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

package main.framework.gameObjects;

import main.framework.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.graphics.Animation;

/**
 *
 * @author Patrick Kerr
 */
public final class Coin extends GameObject {

    private final Animation spin;
    
    public Coin(float x, float y, int spinSpeed) {
        super(x, y, ObjectId.Coin);
        spin = new Animation(spinSpeed, tex.coin[0], tex.coin[1], tex.coin[2], tex.coin[3], tex.coin[4], tex.coin[5], tex.coin[6], tex.coin[7]);
    }

    @Override
    public void tick(LinkedList<GameObject> lstObj) {
        spin.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        spin.drawAnimation(g, (int)x, (int)y);
        //////////////   Collision Box
//        g.setColor(Color.BLUE);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)sizeX, (int)sizeY);
    }
    
}
