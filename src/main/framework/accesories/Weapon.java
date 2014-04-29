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
 *
 * @author Patrick Kerr
 */
public abstract class Weapon {
    protected int maxShots = 0; // Mag capacity
    protected int bullets = 0; // Bullets left
    protected int mags = 0; // Mags left
    protected int reloadSpeed = 40; // Default reload speed
    protected int reloadBarLength = 40; // Default reload bar length
    private int reloadTimer = 0; // Reloads until hits 0;
    private float ratio;
    
    protected int cooldown = 10; // (Default) Min. time between shots (in ticks)
    private int cdTimer = 0; // Current cooldown timer status
    
    protected final Gunner shooter;
    private final Handler handler;
    
    public Weapon(Gunner shooter, Handler handler) {
        this.handler = handler;
        this.shooter = shooter;
    }
    
    public abstract void shoot();
    
    protected void shoot(GameObject projectile) {
        if (cdTimer < 0) {
            cdTimer = cooldown;
            if (reloadTimer < 0 && bullets-- > 0) {
                handler.addObjectLast(projectile);
    //            if (bullets == 0) {
    //                reload(40); // AutoReload
    //            }
            } else {
                bullets = 0;
            }
        }
        
    }
    public void reload() {
        ratio = (float)reloadBarLength / (float)reloadSpeed;
        if (reloadTimer < 0 && bullets < maxShots && mags > 0) {
            reloadTimer = reloadSpeed;
        }
    }
    
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
    
    public int getShotsLeft() {
        return bullets;
    }
    public int getMagsLeft() {
        return mags;
    }
    public int getMagSize() {
        return maxShots;
    }
    public void drawMag(Graphics g, int x, int y) {
        Symbols.drawNumber(g, this.getMagsLeft(), x, y, 32, 32);
    }
}
