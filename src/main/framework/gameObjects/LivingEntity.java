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

import main.framework.GameObject;
import main.framework.ObjectId;
import main.graphics.Animation;

/**
 *
 * @author Patrick Kerr
 */
public abstract class LivingEntity extends GameObject {

    /**
     * Object's health limit (Maximum)
     */
    protected final float maxHp = 100.0f; // Default Max Object Health
    /**
     * Object's health
     */
    protected float hp = maxHp;
    
    protected boolean jumping = false;
    protected boolean falling = false;
    protected boolean dragging = true;
    protected boolean swimming = false;
    private final float maxAir = 100f;
    protected float air = maxAir;
    
    protected Animation avatar;
    
    /**
     * Required parameters to create any LivingEntity
     * @param x Horizontal spawn position
     * @param y Vertical spawn position
     * @param id Type of object
     */
    public LivingEntity(float x, float y, ObjectId id) {
        super(x, y, id);
    }
    
    @Override
    public void tick() {
        super.tick();
        if (jumping || falling) {
            velY += gravity;
            if (velY > tVel) {
                velY = tVel;
            }
        }
        if (dragging) {
            velX *= drag;
        } else {
            if (jumping) { // air friction
                velX *= (1 - drag);
            } else {
                velX *= (1 - (drag / 2));
            }
        }
        if ((velX < 1 && velX >= 0) || (velX > -1 && velX <= 0)){
            velX = 0;
        }
        if (swimming) {
            if (air < 0) {
                hp -= 0.7;
            } else {
                air-=0.1;
            }
        } else {
            if (air < maxAir) {
                air += 0.025;
            } else {
                air = maxAir;
            }
        }
    }
    
    /**
     * @return the HP limit of the entity
     */
    public final float getMaxHP() {
        return maxHp;
    }
    
    /**
     * Sets the HP of the entity
     * @param hp Desired Health Points
     */
    public void setHP(float hp) {
        this.hp = hp;
    }
    
    /**
     * Gets the HP of the entity
     * @return Health points of the entity
     */
    public float getHP() {
        return hp;
    }

    /**
     * Wounds the entity
     * @param hp Health Points to Remove
     */
    public void wound(float hp) {
        this.hp -= hp;
    }

    /**
     * Heals the entity
     * @param hp Health Points to add
     */
    public void heal(float hp) {
        this.hp += hp;
        if (this.hp > maxHp){
            this.hp = maxHp;
        }
    }

    /**
     * Checks if the entity is still alive
     * @return True if alive, else false;
     */
    public Boolean isAlive() {
        return (hp > 0);
    }
    
    /**
     * Checks if the entity is affected by drag
     * @return  True if entity is affected by drag, else False;
     */
    public boolean isDragging() {
        return dragging;
    }

    /**
     * Sets the sliding state of the entity
     * @param dragging True = entity is affected by drag, else False;
     */
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    /**
     * Checks if the entity is swimming
     * @return True if swimming, else false;
     */
    public boolean isSwimming() {
        return swimming;
    }

    /**
     * Sets the swimming state of the entity
     * @param swimming True = Swimming, else False;
     */
    public void setSwimming(boolean swimming) {
        this.swimming = swimming;
    } 

    /**
     * Sets the jumping state of the entity
     * @param jumping True = Jumping, else False;
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    /**
     * Checks if the entity is jumping
     * @return True if jumping, else false;
     */
    public boolean isJumping() {
        return jumping;
    }
    /**
     * Checks if the entity is falling
     * @return True if object is currently falling, else False;
     */
    public boolean isFalling() {
        return falling;
    }
    
    /**
     * Sets the falling state of the entity
     * @param falling True = Falling, else False;
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public float getAir() {
        return air;
    }
    
    public float getMaxAir() {
        return maxAir;
    }
    
    public Animation getAvatar() {
        return avatar;
    }
}
