/*
 * The MIT License
 *
 * Copyright 2014 Patrick.
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

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import main.framework.GameObject;
import main.framework.ObjectId;
import main.graphics.Animation;

/**
 *
 * @author Patrick
 */
public class BloodSplatter extends GameObject {

    private final int r = new Random().nextInt(6);
    private final Animation splatter = new Animation(7, tex.splatter[r]);
    private final GameObject host;
    
    public BloodSplatter(GameObject host) {
        super(host.getX(), host.getY(), ObjectId.Null);
        this.host = host;
    }

    @Override
    public void tick(LinkedList<GameObject> lstObj) {
        if (splatter.runAnimation() == splatter.getLength() - 1) {
            lstObj.remove(this);
        }
    }

    @Override
    public void render(Graphics g) {
        splatter.drawAnimation(g, (int)host.getX(), (int)host.getY(), (int)sizeX, (int)sizeY);
    }

    @Override
    public Rectangle getBounds() {
            return new Rectangle((int)x,(int)y,(int)sizeX,(int)sizeY);
    }
}
