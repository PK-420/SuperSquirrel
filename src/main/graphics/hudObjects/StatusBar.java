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

package main.graphics.hudObjects;

import java.awt.Graphics;
import main.framework.gameObjects.LivingEntity;
import main.graphics.Texture;
import main.Game;

/**
 *
 * @author Patrick Kerr
 */
public final class StatusBar {
    private final static Texture tex = Game.getTexture();
    
    public enum Align{Left, Right}

    public static void draw(Graphics g, LivingEntity e, int x, int y, int size, Align a) {
        switch (a) {
            case Left :
                drawLeftToRight(g, e, x, y, size);
                break;
            case Right :
                drawRightToLeft(g, e, x, y, size);
                break;
        }
    }
    
    public static void draw(Graphics g, LivingEntity e1, LivingEntity e2, int x, int y, int size) {
        // Entity 2
        drawLeftToRight(g, e2, x - (15 * size), y, size);
        // Entity 1
        drawRightToLeft(g, e1, x + (15 * size), y, size);
    }
    
    private static void drawLeftToRight(Graphics g, LivingEntity e, int x, int y, int size) {
        float ratio = tex.bar[0].getWidth() / (float)e.getMaxHP();
        g.drawImage(tex.frame[0], x, y, tex.frame[0].getWidth() * size, tex.frame[0].getHeight() * size,null);
        int color;
        if (e.getHP() > e.getMaxHP() * 0.40) {
            color = 2;
        } else if (e.getHP() > e.getMaxHP() * 0.25) {
            color = 3;
        } else if (e.getHP() > e.getMaxHP() * 0.10) {
            color = 4;
        } else {
            color = 5;
        }
        if (e.getHP() > 0) g.drawImage(tex.bar[color], x + (43 * size), y + 10 * size, (int)(ratio * e.getHP()) * size, tex.bar[2].getHeight() * size, null);
        ratio = tex.bar[0].getWidth() / (float)e.getMaxAir();
        
        if (e.getAir() > 0) g.drawImage(tex.bar[0], x + (43 * size), y + 16 * size, (int)(ratio * e.getAir()) * size, tex.bar[2].getHeight() * size, null);
        
        g.drawImage(tex.frame[1], x, y, tex.frame[1].getWidth() * size, tex.frame[1].getHeight() * size,null);
        g.drawImage(tex.frame[2], x, y, tex.frame[2].getWidth() * size, tex.frame[2].getHeight() * size, null);

        e.getAvatar().drawAnimation(g, x, y, 32 * size, 32 * size);
    }
    
    private static void drawRightToLeft(Graphics g, LivingEntity e, int x, int y, int size) {
        float ratio = tex.bar[0].getWidth() / (float)e.getMaxHP();
        int sizeX = tex.frame[0].getWidth() * size;
        g.drawImage(tex.frame[3], x - sizeX, y, sizeX, tex.frame[0].getHeight() * size, null);
        int color;
        if (e.getHP() > e.getMaxHP() * 0.40) {
            color = 2;
        } else if (e.getHP() > e.getMaxHP() * 0.25) {
            color = 3;
        } else if (e.getHP() > e.getMaxHP() * 0.10) {
            color = 4;
        } else {
            color = 5;
        }
        int barX = x - sizeX + (28 * size); 
        if (e.getHP() > 0) g.drawImage(tex.bar[color], barX + (int)(ratio * (e.getMaxHP() - e.getHP())) * size, y + 10 * size, (int)(ratio * e.getHP()) * size, tex.bar[2].getHeight() * size, null);
        ratio = tex.bar[0].getWidth() / (float)e.getMaxAir();
        
        if (e.getAir() > 0) g.drawImage(tex.bar[0], barX + (int)(ratio * (e.getMaxAir() - e.getAir())) * size, y + 16 * size, (int)(ratio * e.getAir()) * size, tex.bar[2].getHeight() * size, null);
        
        g.drawImage(tex.frame[4], x - sizeX, y, sizeX, tex.frame[1].getHeight() * size,null);
        g.drawImage(tex.frame[5], x - sizeX, y, sizeX, tex.frame[2].getHeight() * size, null);
        
        e.getAvatar().drawAnimation(g, x - (30 * size), y, 32 * size, 32 * size);
    }
}
