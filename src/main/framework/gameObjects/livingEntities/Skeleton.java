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

package main.framework.gameObjects.livingEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;
import main.framework.*;
import main.framework.gameObjects.LivingEntity;
import main.graphics.Animation;

/**
 *
 * @author Patrick Kerr
 */
public final class Skeleton extends LivingEntity {

    private final Animation walkLeft = new Animation (10, tex.skeleton[1], tex.skeleton[2], tex.skeleton[3]);
    private final Animation walkRight = new Animation (10, tex.skeleton[4], tex.skeleton[5], tex.skeleton[6]);
    
    public Skeleton(float x, float y) {
        super(x * Game.scale, y * Game.scale, ObjectId.Mob);
        hp = 25;
        sizeX = sizeY = 32;
        drag = 1;
        velX = 3;
    }

    @Override
    public void tick(LinkedList<GameObject> lstObj) {
        super.tick();
        if (this.isAlive()) {
            velX = Math.signum(velX) * 3;
            walkLeft.runAnimation();
            walkRight.runAnimation();
            for (GameObject tmpObj : lstObj) {
                if (this != tmpObj) {
                    if (getBounds().intersects(tmpObj.getBounds())) {
                        if (tmpObj.getId() == ObjectId.Dirt ||
                                tmpObj.getId() == ObjectId.Grass ||
                                tmpObj.getId() == ObjectId.Stone ||
                                tmpObj.getId() == ObjectId.Ice || 
                                tmpObj.getId() == ObjectId.Mob) {
                            velX = -velX;
                        }   
                    }
                }
            }
        } else {
            lstObj.remove(this);
        }
    }

    @Override
    public void render(Graphics g) {
        if (velX != 0) {
            if (velX > 0) {
                walkRight.drawAnimation(g, (int)x, (int)y, (int)sizeX, (int)sizeY);
            } else {
                walkLeft.drawAnimation(g, (int)x, (int)y, (int)sizeX, (int)sizeY);
            }
        } else {
            g.drawImage(tex.skeleton[0], (int)x, (int)y, (int)sizeX, (int)sizeY, null);
        }
    //////////Collision Boxes
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.yellow);
//        g2d.draw(getBoundsTop());
//        g2d.draw(getBoundsBottom());
//        g2d.draw(getBoundsLeft());
//        g2d.draw(getBoundsRight());
//        g.setColor(Color.red);
//        g2d.draw(getBounds());
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)(y + sizeY/3), (int)sizeX, (int)(2*(sizeY/3)));
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x + sizeX/4), (int)(y + sizeY/3), (int)sizeX/2, (int)sizeY/3);
    }
    
    public Rectangle getBoundsBottom() {
        return new Rectangle((int)(x + sizeX/4), (int)(y + (sizeY/3) * 2), (int)sizeX/2, (int)sizeY/3);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)(y + sizeY/3), (int)sizeX/5, (int)(2*(sizeY/3)));
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x + 4 * (sizeX/5)), (int)(y + sizeY/3), (int)sizeX/5, (int)(2*(sizeY/3)));
    }
    
    
}
