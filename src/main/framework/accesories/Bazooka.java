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
import java.awt.Graphics;
import main.framework.gameObjects.projectiles.Rocket;
import main.graphics.Texture;
import main.Game;

/**
 * This class represents a Rocket Launcher object
 * @author Patrick Kerr
 */
public final class Bazooka extends Weapon {

    private final Texture tex = Game.getTexture();

    /**
     * Creates a rocket launcher object (Weapon)
     * @param shooter Entity that will use this weapon
     * @param handler Handler to which to add the bullets fired
     */
    public Bazooka(Gunner shooter, Handler handler) {
        super(shooter, handler);
        maxShots = 1;
        mags = 5;
        reloadSpeed = 120;
        reload();
    }

    @Override
    public void shoot() {
        if (super.shoot(new Rocket(shooter))) {
            SFX.play("/audio/shot_rocket.wav");
        }
    }

    @Override
    public void drawMag(Graphics g, int x, int y) {
        super.drawMag(g, x, y);
        for (int i = this.getMagSize(); i > 0; i--) { // Show Mags
            if (i <= this.getShotsLeft()) {
                g.drawImage(tex.bullet, (i * 20) + x + 10, y, null);
            } else {
                g.drawImage(tex.bulletSlot, (i * 20) + x + 10, y, null);
            }
        }
    }
}