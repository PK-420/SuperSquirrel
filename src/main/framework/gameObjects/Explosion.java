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

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.framework.*;
import main.graphics.Animation;

/**
 * This class represents an explosion effect
 * @author Patrick Kerr
 */
public final class Explosion extends GameObject {

    private final int speed = 1;
    private final Animation explode = new Animation(speed, tex.explosion[3], tex.explosion[4], tex.explosion[5], tex.explosion[6], tex.explosion[7], tex.explosion[8], tex.explosion[9], tex.explosion[10], tex.explosion[11], tex.explosion[12], tex.explosion[13], tex.explosion[14], tex.explosion[15], tex.explosion[14], tex.explosion[13], tex.explosion[12], tex.explosion[11], tex.explosion[10], tex.explosion[9], tex.explosion[8], tex.explosion[7], tex.explosion[6], tex.explosion[5], tex.explosion[4], tex.explosion[3], tex.explosion[2], tex.explosion[1], tex.explosion[0]);
    private GameObject reward = null;
    
    /**
     * Creates a new Explosion effect that needs to be added to the handler list so it can tick and be destroyed once the animation ends
     * @param x Horizontal position of the object on the map
     * @param y Vertical position of the object on the map
     */
    public Explosion(float x, float y) {
        super(x, y, ObjectId.Null);
    }
    /**
     * Creates a new Explosion effect that needs to be added to the handler list so it can tick and be destroyed once the animation ends
     * @param x Horizontal position of the object on the map
     * @param y Vertical position of the object on the map 
     * @param reward Game object to spawn at the end of the explosion
     */
    public Explosion(float x, float y, GameObject reward) {
        super(x, y, ObjectId.Null);
        this.reward = reward;
    }
    
    @Override
    public void tick(LinkedList<GameObject> lstObj) {
        if (explode.runAnimation() == explode.getLength() - 1) {
            lstObj.remove(this);
            if (reward != null) lstObj.add(reward);
        }
    }

    @Override
    public void render(Graphics g) {
        explode.drawAnimation(g, (int)x, (int)y, (int)sizeX, (int)sizeY);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)sizeX, (int)sizeY);
    }
}
