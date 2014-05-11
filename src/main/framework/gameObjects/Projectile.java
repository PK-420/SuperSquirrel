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

import java.awt.Color;
import main.framework.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

/**
 * This class is the parent class of any projectile that can be fired
 * @author Patrick Kerr
 */
public abstract class Projectile extends GameObject {
    
    /**
     * Creates a new projectile
     * @param host Gunner entity that will fire the projectile
     */
    public Projectile(Gunner host) {
        super(host.getX(), host.getY() + 15, ObjectId.Bullet);
        this.sizeX = this.sizeY = 16;
        velX = 10 * host.getFacing();
    }

    @Override
    public void tick(LinkedList<GameObject> mapObj) {
        super.tick();
//        velY += gravity; // Gravity acceleration
        if (x > -Game.getCamera().getX() + Game.WIDTH || x < -Game.getCamera().getX()) { 
            mapObj.remove(this);
        }
    }

    @Override
    public void render(Graphics g) {
////////// Collision Box (Call super.render(); in child object)
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)sizeX, (int)sizeY);
    }
}
