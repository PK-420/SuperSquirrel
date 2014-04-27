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

import main.framework.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.framework.gameObjects.LivingEntity;

/**
 *
 * @author Patrick Kerr
 */
public abstract class Enemy extends LivingEntity {
    
    private int facing = 0; // Facing ( - = Left, 0 = Front, + = Right
    private final int tVel = 300; // Terminal Velocity
    
    private boolean jumping;
    
    public Enemy(float x, float y) {
        super(x, y, ObjectId.Player);
        sizeX = 32;
        sizeY = 64;
    }
    
    @Override
    public void tick(LinkedList<GameObject> mapObj) {
        x += velX;
        y += velY;
        
        if (jumping || falling) { // Gravity acceleration
            velY += gravity; 
            if (velY > tVel) {
                velY = tVel;
            }
        }
        
        for (GameObject tmpObj : mapObj) {
            if (tmpObj.getId() == ObjectId.Dirt ||
                    tmpObj.getId() == ObjectId.Grass ||
                    tmpObj.getId() == ObjectId.Stone ||
                    tmpObj.getId() == ObjectId.Ice) {
                if (getBoundsTop().intersects(tmpObj.getBounds())) {
                    y = (tmpObj.getY() + tmpObj.getSizeY());
                    velY = 0;
                }
                if (getBounds().intersects(tmpObj.getBounds())) {
                    y = tmpObj.getY() - sizeY;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
                else {
                    falling = true;
                }
                if (getBoundsLeft().intersects(tmpObj.getBounds()))  x = tmpObj.getX() + tmpObj.getSizeX();
                if (getBoundsRight().intersects(tmpObj.getBounds())) x = tmpObj.getX() - sizeX;
            }
        }       
// .runAnimation
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, (int)sizeX, (int)sizeY);

        // animation draw
        
        // Collision boxes
        g.setColor(Color.yellow);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x + (sizeX/4)), (int)y+(int)(sizeY/2), (int)sizeX/2, (int)sizeY/2);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x + (sizeX/4)), (int)y, (int)sizeX/2, (int)sizeY/2);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)sizeX/5, (int)sizeY - 10);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x + (4 * (sizeX/5))), (int)y + 5, (int)sizeX/5, (int)sizeY - 10);
    }
    public void setFacing(int facing) {
        this.facing = facing;
    }  
}