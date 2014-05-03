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

package main.framework.accesories;

import main.framework.*;
import java.awt.Color;
import java.awt.Graphics;
import main.graphics.hudObjects.Symbols;

/**
 * Root class of every weapon
 * needs to be ticked and rendered
 * @author Patrick Kerr
 */
public abstract class Weapon {

    /**
     * Magazine capacity
     */
    protected int maxShots = 0;

    /**
     *  Bullets left
     */
    protected int bullets = 0; 

    /**
     * Magazines left
     */
    protected int mags = 0;

    /**
      * Reload speed
     */
    protected int reloadSpeed = 40; // Default 

    private final int reloadBarLength = 40; // Default reload bar length
    private int reloadTimer = 0; // Reloads until hits 0;
    private float ratio;
    
    /**
     * Must be greater or equals to 0;
     * If greater than 0, is the minimum time between automatic shots (in ticks)
     * If equals 0, is considered like a single fire weapon (Must pull the trigger again to fire)
     */
    protected int cooldown = 0; // (Default) 
    private int cdTimer = 0; // Current cooldown timer status
    private boolean canShoot = true;
    
    /**
     * Represents the entity holding the weapon
     */
    protected final Gunner shooter;
    private final Handler handler;
    
    public Weapon(Gunner shooter, Handler handler) {
        this.handler = handler;
        this.shooter = shooter;
    }
    
    /**
     * Required public method to shoot
     */
    public abstract void shoot();
    
    /**
     * Method to shoot, used by the child only
     * @param projectile Type of GameObject to throw
     * @return True if it was able to shoot, else false;
     */
    protected boolean shoot(GameObject projectile) {
        if (cdTimer < 0) {
            cdTimer = cooldown;
            if (canShoot) {
                canShoot = this.isAuto();
                if (reloadTimer < 0 && bullets-- > 0) {
                    handler.addObjectLast(projectile);
                    return true;
                } else {
                    bullets = 0;
                    SFX.play("/audio/empty_gun.wav");
                }
            }
        }
        return false;
    }
    /**
     * Public method used to reload the weapon
     */
    public void reload() {
        ratio = (float)reloadBarLength / (float)reloadSpeed;
        if (reloadTimer < 0 && bullets < maxShots && mags > 0) {
            reloadTimer = reloadSpeed;
            SFX.play("/audio/reload_mag.wav");
        }
    }
    
    /**
     * Public method used to update the timers
     */
    public void tick() {
        cdTimer--;
        if (reloadTimer == 0) {
            reloadTimer = -1;
            if (mags-- > 0) {
                bullets = maxShots;
            } else {
                mags = 0;
            }
        } else if (reloadTimer > 0) {
            reloadTimer--;
        }
    }
    
    /**
     * Public method used to draw the reload bar over the entity when needed
     * @param g Graphics on which to draw
     */
    public void render (Graphics g) {
        if (reloadTimer >= 0) { // Show Reload Bar
            g.setColor(Color.BLACK);
            g.drawString("Reload", (int)shooter.getX(), (int)shooter.getY());
            if (reloadTimer < (0.1 * reloadSpeed)) {
                g.setColor(Color.GREEN);
            } else if (reloadTimer < 0.25 * reloadSpeed) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.RED);
            }
            g.fillRect((int)shooter.getX(), (int)shooter.getY(), (int)((reloadSpeed - reloadTimer) * ratio), 3);
            g.setColor(Color.BLACK);
            g.drawRect((int)shooter.getX(), (int)shooter.getY(), (int)(reloadSpeed * ratio), 3);
        }
    }
    
    /**
     * @return Number of projectiles left in the magazine
     */
    public int getShotsLeft() {
        return bullets;
    }

    /**
     * @return Number of magazine carried with the weapon
     */
    public int getMagsLeft() {
        return mags;
    }

    /**
     * @return Size of the magazines
     */
    public int getMagSize() {
        return maxShots;
    }

    /**
     * Draws the magazine counter
     * @param g Graphics on which to draw
     * @param x Horizontal position to start drawing
     * @param y Vertical position to start drawing
     */
    public void drawMag(Graphics g, int x, int y) {
        Symbols.drawNumber(g, this.getMagsLeft(), x, y, 32, 32);
    }
    /**
     * Releases the trigger on the weapon (Setting canShoot to true)
     */
    public void releaseTrigger() {
        canShoot = true;
    }

    /**
     * @return True if the weapon is ready to shoot, else false;
     */
    public boolean canShoot() {
        return canShoot;
    }

    /**
     * @return True if the gun is automatic, else false;
     */
    public boolean isAuto() {
        return cooldown != 0;
    }
}
