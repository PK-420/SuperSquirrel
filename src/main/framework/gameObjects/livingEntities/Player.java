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
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import main.framework.accesories.Weapon;
import main.framework.gameObjects.*;

/**
 *
 * @author Patrick Kerr
 */
public abstract class Player extends LivingEntity implements Gunner {
    
    protected Weapon[] gun;
    protected int selectedGun = 0;
    
    protected int facing = 0; // Facing ( - = Left, 0 = Front, + = Right )
    
    protected int coins = 0;
    
    private int protection = 0;
    
    public Player(float x, float y) {
        super(x, y, ObjectId.Player);
    }
    
    @Override
    public void tick(LinkedList<GameObject> lstObj) {
        super.tick();        
        gun[selectedGun].tick();
        for (GameObject tmpObj : lstObj) {
            /* if (tmpObj.getId() == ObjectId.Water) {
                if (getBounds().intersects(tmpObj.getBounds())) { // In water
                    swimming = true;
//                    falling = false;
//                    jumping = false;
                    return;///?!?!
                }
                else { // Not in water
                    falling = true;
                    swimming = false;
                }
            }
            else */
            if (tmpObj.getId() == ObjectId.Dirt ||
                    tmpObj.getId() == ObjectId.Grass ||
                    tmpObj.getId() == ObjectId.Stone ||
                    tmpObj.getId() == ObjectId.Ice) { //Solid Block Collision
                if (getBoundsBottom().intersects(tmpObj.getBounds())) {
                    y = tmpObj.getY() - (sizeY - (sizeY / 4));
                    velY = 0;
                    falling = false;
                    jumping = false;
                    swimming = false;
                    sliding = tmpObj.getId() == ObjectId.Ice;
                    }
                else {
                    falling = true;
                }
                if (getBoundsTop().intersects(tmpObj.getBounds())) {
                    y = (tmpObj.getY() + tmpObj.getSizeY()) - (sizeY / 4);
                    velY = 0;
                }
                if (getBoundsLeft().intersects(tmpObj.getBounds())) {
                    x = tmpObj.getX() + tmpObj.getSizeX();
                }
                if (getBoundsRight().intersects(tmpObj.getBounds())) {
                    x = tmpObj.getX() - sizeX;
                }
            } 
            else if (tmpObj.getId() == ObjectId.Coin) {
                if (tmpObj.getBounds().intersects(getBounds())) {
                    lstObj.remove(tmpObj);
                    coins++;
                }
            }
            else if (tmpObj.getId() == ObjectId.Mob) {
                if (tmpObj.getBounds().intersects(getBounds())) {
                    if (this.isAlive() && protection <= 0) {
                        protection = 15;
                        hp-=20;
                        tmpObj.setVelX(-tmpObj.getVelX());
                        setVelY(-7);
                        lstObj.add(new BloodSplatter(this));
                    }
                }
            }
        }
        if (protection-- < 0) {
            protection = 0;
        }
    }
    
    @Override
    public void render(Graphics g) {
        gun[selectedGun].render(g);
    }
    
    public void processInput(GameKeyInput input) {
        if (this.isAlive()) {
            if (input.isKeyPressed()) {
            }
            if (input.isKeyDown(KeyEvent.VK_A) || input.isKeyDown(KeyEvent.VK_LEFT)) {
                this.setFacing(-1);
                if (Math.abs(this.getVelX()) < 5) {
                    this.setVelX(5);
                }
                this.setSliding(true);
            }
            if (input.isKeyDown(KeyEvent.VK_D) || input.isKeyDown(KeyEvent.VK_RIGHT)) {
                this.setFacing(1);
                if (Math.abs(this.getVelX()) < 5) {
                    this.setVelX(5);
                }
                this.setSliding(true);
            }
            if (input.isKeyDown(KeyEvent.VK_SHIFT)) {
                if (this.getVelX() != 0) {
                    this.setVelX(7);
                }
//        } else if (input.isKeyDown(KeyEvent.VK_CONTROL)) {
//            if (handler.player.getVelX() != 0) handler.player.setVelX(2);
            }
            if ((input.isKeyDown(KeyEvent.VK_W) || input.isKeyDown(KeyEvent.VK_UP)) && // Jump Key
                    !this.isJumping()) { // Currently on ground
                this.setVelY(-10);
                this.setJumping(true);
            }

            if (input.isKeyDown(KeyEvent.VK_SPACE)) {
                if (this.getFacing() != 0) {
                    this.getSelectedGun().shoot();
                }
            }
            // Action on Release
            if (input.isKeyReleased()) {
                if (input.isKeyUp(KeyEvent.VK_R) || input.isKeyUp(KeyEvent.VK_NUMPAD0)) { 
                    this.getSelectedGun().reload();
                }
                if (input.isKeyUp(KeyEvent.VK_1)) { // Select Gun 1
                    this.setSelectedGun(1);
                } else if (input.isKeyUp(KeyEvent.VK_2)) {
                    this.setSelectedGun(2);
                }
                if (input.isKeyUp(KeyEvent.VK_SPACE)) {
                    this.getSelectedGun().releaseTrigger();
                }
            }
        }
    }
    
    public abstract Rectangle getBoundsTop();
    public abstract Rectangle getBoundsBottom();
    public abstract Rectangle getBoundsLeft();
    public abstract Rectangle getBoundsRight();
    
    @Override
    public int getFacing() {
        return facing;
    }
    
    public void setFacing(int facing) {
        this.facing = facing;
    }
    
    public void setVelX(int velX) {
        this.velX = Math.abs(velX) * this.facing;
    }
    
    public int getCoinCount() {
        return coins;
    }
    
    public Weapon getSelectedGun() {
        return gun[selectedGun];
    }
    
    public void setSelectedGun(int gun) {
        if (gun <= this.gun.length && gun > 0) {
            this.selectedGun = gun - 1;
        }
        else {
            this.selectedGun = 0;
        }
    }
}
