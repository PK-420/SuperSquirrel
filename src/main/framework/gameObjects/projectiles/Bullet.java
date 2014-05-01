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

package main.framework.gameObjects.projectiles;

import main.framework.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.framework.gameObjects.*;

/**
 *
 * @author Patrick Kerr
 */
public final class Bullet extends Projectile {
    
    private final Gunner host;

    public Bullet(float x, float y, Gunner host) {
        super(x, y, host);
        this.sizeX = this.sizeY = 16;
        this.host = host;
        velX = 10 * host.getFacing();
    }

    @Override
    public void tick(LinkedList<GameObject> lstObj) {
        super.tick(lstObj);
        for (GameObject tmpObj : lstObj) {
            if (getBounds().intersects(tmpObj.getBounds())) {
                if (tmpObj.getId() == ObjectId.Mob) { // Hits a mob
                    // Check if instance of... instead?
                    lstObj.add(new BloodSplatter(tmpObj));
                    LivingEntity ent = (LivingEntity) tmpObj;
                    ent.wound(4.20f);
                    lstObj.remove(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.bullet, (int)x, (int)y, (int)sizeX, (int)sizeY, null);
//        super.render(g); // Shows collision box
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)sizeX, (int)sizeY);
    }
}
