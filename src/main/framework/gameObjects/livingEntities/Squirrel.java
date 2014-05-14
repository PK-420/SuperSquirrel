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
import main.framework.accesories.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.graphics.Animation;

/**
 * This class represents the main character of the game
 * @author Patrick Kerr
 */
public final class Squirrel extends Player {
   
    private final Animation enter = new Animation(15, tex.squirrel[41], tex.squirrel[42], tex.squirrel[43]);
    private final Animation leave = new Animation(15,tex.squirrel[44], tex.squirrel[45], tex.squirrel[46]);
    private final Animation walkRight = new Animation(7, tex.squirrel[2], tex.squirrel[3], tex.squirrel[4]);
    private final Animation walkLeft = new Animation(7, tex.squirrel[6], tex.squirrel[7], tex.squirrel[8]);
    private final Animation sneakRight = new Animation(20, tex.squirrel[9], tex.squirrel[10]);
    private final Animation sneakLeft = new Animation(20, tex.squirrel[19], tex.squirrel[20]);
    private final Animation eatRight = new Animation(10, tex.squirrel[11], tex.squirrel[12], tex.squirrel[13], tex.squirrel[14], tex.squirrel[15], tex.squirrel[16], tex.squirrel[17], tex.squirrel[18]);
    private final Animation eatLeft = new Animation(10, tex.squirrel[21], tex.squirrel[22], tex.squirrel[23], tex.squirrel[24], tex.squirrel[25], tex.squirrel[26], tex.squirrel[27], tex.squirrel[28]);
    private final Animation eat = new Animation(10, tex.squirrel[31], tex.squirrel[32], tex.squirrel[33], tex.squirrel[34], tex.squirrel[35], tex.squirrel[36], tex.squirrel[37], tex.squirrel[38]);
    private final Animation dead = new Animation(10, tex.squirrel[39], tex.squirrel[40]);
        
    /**
     * Creates the squirrel
     * @param x Horizontal position to spawn the Player
     * @param y Vertical position to spawn the Player
     * @param handler
     */
    public Squirrel(float x, float y, Handler handler) {
        super(x, y);
        gun.add(new Pistol(this, handler));
        gun.add(new Bazooka(this, handler));
        avatar = eat;
        sizeX = 45; 
        sizeY = 45;
    }
    
    @Override
    public void tick(LinkedList<GameObject> mapObj) {
        super.tick(mapObj);
        if (isAlive()) {
            avatar = eat;
        } else {
            avatar = dead;
        }
        dead.runAnimation();
        enter.runAnimation();
        leave.runAnimation();
        walkRight.runAnimation();
        walkLeft.runAnimation();
        sneakRight.runAnimation();
        sneakLeft.runAnimation();
        eatRight.runAnimation();
        eatLeft.runAnimation();
        eat.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
//        g.fillRect((int)x, (int)y, (int)sizeX, (int)sizeY);
        if (protection % 5 == 0) {
            if (isAlive()) {
                walkLeft.setSpeed(13 - (int) Math.abs(velX));
                walkRight.setSpeed(13 - (int) Math.abs(velX));
                if (velX != 0) { // Got Velocity
                    if (jumping) { // is Jumping
                        if (facing > 0) {
                            g.drawImage(tex.squirrel[2], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        } else if (facing < 0) {
                            g.drawImage(tex.squirrel[6], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        } else {
                            g.drawImage(tex.squirrel[0], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        }
                    } else {
                        if (facing > 0) {
                            walkRight.drawAnimation(g, (int) x, (int) y, (int) sizeX, (int) sizeY);
                        } else if (facing < 0) {
                            walkLeft.drawAnimation(g, (int) x, (int) y, (int) sizeX, (int) sizeY);
                        } else {
                            g.drawImage(tex.squirrel[0], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        }
                    }
                } else { // Stopped
                    if (jumping) { // is Jumping
                        if (facing > 0) {
                            g.drawImage(tex.squirrel[18], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        } else if (facing < 0) {
                            g.drawImage(tex.squirrel[28], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        } else {
                            g.drawImage(tex.squirrel[0], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        }
                    } else {
                        if (facing > 0) {
                            g.drawImage(tex.squirrel[1], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        } else if (facing < 0) {
                            g.drawImage(tex.squirrel[5], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        } else {
                            // eat.drawAnimation(g, (int) x, (int) y, (int) sizeX, (int) sizeY); 
                            g.drawImage(tex.squirrel[0], (int) x, (int) y, (int) sizeX, (int) sizeY, null);
                        }
                    }
                }
            } else {
                dead.drawAnimation(g, (int) x, (int) y, (int) sizeX, (int) sizeY);
            }
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
        return new Rectangle((int)(x + (sizeY/10)), (int)(y+(sizeY/4)), (int)(8*(sizeX/10)), (int)(sizeY/2));
    }
    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x + (sizeX/4)), (int)(y+(sizeY/4)), (int)sizeX/2, (int)sizeY/2);
    }
    @Override
    public Rectangle getBoundsBottom() {
        return new Rectangle((int)(x + (sizeX/4)), (int)(y+(sizeY/2)), (int)sizeX/2, (int)(sizeY/4));
    }
    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)(y + (sizeY/3)), (int)sizeX/5, (int)sizeY/3);
    }
    @Override
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x + (4 * (sizeX/5))), (int)(y + (sizeY/3)), (int)sizeX/5, (int)sizeY/3);
    }
}