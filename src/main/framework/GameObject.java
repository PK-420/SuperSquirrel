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

package main.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.graphics.Texture;
import main.Game;

/**
 *
 * @author Patrick Kerr
 */
public abstract class GameObject {
    
    /**
     * Contains the game textures
     */
    protected final static Texture tex = Game.getTexture();
    /**
     * Horizontal object size (in pixels)
     */
    protected float sizeX = Game.scale; // Default
    /**
     * Vertical object size (in pixels)
     */
    protected float sizeY = Game.scale; // Size
    /**
     * Horizontal position of the object (in pixels)
     */
    protected float x;
    /**
     * Vertical position of the object (in pixels)
     */
    protected float y;
    /**
     * Horizontal velocity of the object (in pixels / tick)
     */
    protected float velX = 0;
    /**
     * Vertical velocity of the object (in pixels / tick)
     */
    protected float velY = 0;
    /**
     * Gravity pull
     */
    protected final float gravity = 0.5f;
    /**
     * Terminal velocity
     */
    protected final int tVel = 300;
    /**
     * Side friction
     */
    protected final float drag = 0.025f;
    /**
     * If true, Object will be destroyed in next handler tick
     */
    protected boolean disposable = false;
    
    private final ObjectId id;
    
    /**
     * Required parameters to create any GameObject
     * @param x Horizontal spawn position
     * @param y Vertical spawn position
     * @param id Type of object
     */
    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;  
    }
    
    /**
     * Required method and parameter to update any GameObject
     * @param lstObj LinkedList of all GameObjects to check for collision
     */
    public abstract void tick(LinkedList<GameObject> lstObj);

    /**
     * Default Object tick
     * Movement according to velocity
     */
    protected void tick() {
        x += velX;
        y += velY;
    }
    
    /**
     * Required method and parameter to render any GameObject
     * @param g Graphics on which to draw
     */
    public abstract void render(Graphics g);
    
    /**
     * Used to get the main collision box of any object
     * @return A rectangle surrounding the GameObject
     */
    public abstract Rectangle getBounds();
    
    /**
     * @return Horizontal position of the object (in pixels)
     */
    public float getX(){
        return x;
    }

    /**
     * @return Vertical position of the object (in pixels)
     */
    public float getY(){
        return y;
    }

    /**
     * Sets the horizontal position of the object
     * @param x Desired horizontal position of the object (in pixels)
     */
    public void setX (float x) {
        this.x = x;
    }

    /**
     * Sets the vertical position of the object
     * @param y Desired vertical position of the object (in pixels)
     */
    public void setY (float y) {
        this.y = y;
    }
    
    /**
     * @return Horizontal velocity of the object (in pixels / tick)
     */
    public float getVelX(){
        return velX;
    }

    /**
     * @return Vertical velocity of the object (in pixels / tick)
     */
    public float getVelY(){
        return velY;
    }

    /**
     * Changes the horizontal velocity of the object
     * @param velX Desired horizontal velocity of the object (in pixels / tick)
     */
    public void setVelX (float velX) {
        this.velX = velX;
    }

    /**
     * Changes the vertical velocity of the object
     * @param velY Desired vertical velocity of the object (in pixels / tick)
     */
    public void setVelY (float velY) {
        this.velY = velY;
    }
    
    /**
     * @return The Id of the object
     */
    public ObjectId getId() {
        return this.id;
    }
    
    /**
     * @return Horizontal object size (in pixels)
     */
    public float getSizeX() {
        return sizeX;
    }

    /**
     * @return Vertical object size (in pixels)
     */
    public float getSizeY() {
        return sizeY;
    }
    /**
     * @return True if the object is no longer needed, else false
     */
    public boolean isDisposable() {
        return disposable;
    }

    /**
     * Flags the object to be destroyed
     * @param disposable True = Can be destroyed, else false
     */
    public void setDisposable(boolean disposable) {
        this.disposable = disposable;
    }
}
