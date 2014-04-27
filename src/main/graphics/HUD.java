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

package main.graphics;

import main.graphics.hudObjects.*;
import java.awt.Color;
import java.awt.Graphics;
import main.framework.Handler;
import main.graphics.hudObjects.StatusBar.Align;
import main.Game;

/**
 *
 * @author Patrick Kerr
 */
public final class HUD {
    
    private boolean showDebug = false;
    
    private final Game game;
    private final Handler handler;
    
    public HUD(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }
    
    public void tick() {
        CoinCounter.tick();
    }
    
    public void render(Graphics g) {
        if (handler.player != null) { // Player HUD
            CoinCounter.draw(g, handler.player, Game.WIDTH - 10, 10, 30);
            handler.player.getSelectedGun().drawMag(g);
            StatusBar.draw(g, handler.player, 5, 5, 2, Align.Left);
            //StatusBar.draw(g, handler.player, handler.player, Game.WIDTH/2, 5, 2);
        }
        if (showDebug) { // Performance Overlay
            showGameDebug(g, 0, 0);
            showPlayerDebug(g, 0, 50);
            g.setColor(Color.WHITE);
            g.fillRect(5, Game.HEIGHT - 15, 275, 10);
            g.setColor(Color.BLACK);
            g.drawString(handler.player.getSelectedGun().toString(), 10, Game.HEIGHT - 5);
        }
    }
    
    public void toggleDebugInfo() {
        showDebug = !showDebug;
    }
    
    private void showGameDebug(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 112, 40);
        g.setColor(Color.BLACK);
        g.drawString("=== GAME INFO ===", x, y += 10);
        g.drawString("Objects : " + String.valueOf(handler.mapObjects.size()), x, y += 10);
        if (game.fps > game.ups + 5) {
            g.setColor(Color.GREEN);
        } else if (Math.abs(game.ups - game.fps) < 5) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }
        g.drawString("Frames/S : " + String.valueOf(game.fps), x, y += 10);
        if (game.ups == Game.tps) {
            g.setColor(Color.GREEN);
        } else if (Math.abs(game.ups - Game.tps) < 5) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }
        g.drawString("Ticks/S : " + String.valueOf(game.ups), x, y += 10);
    }
    
    private void showPlayerDebug(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 125, 120);
        g.setColor(Color.BLACK);
        g.drawString("=== PLAYER INFO ===", x, y += 10);
        g.drawString("Dragging : " + handler.player.isDragging(), x, y += 10);
        g.drawString("Swimming : " + handler.player.isSwimming(), x, y += 10);
        g.drawString("Falling : " + handler.player.isFalling(), x, y += 10);
        g.drawString("Jumping : " + handler.player.isJumping(), x, y += 10);
        g.drawString("X : " + handler.player.getX(), x, y += 10);
        g.drawString("Y : " + handler.player.getY(), x, y += 10);
        g.drawString("Facing : " + handler.player.getFacing(), x, y += 10);
        g.drawString("Velocity X : " + handler.player.getVelX(), x, y += 10);
        g.drawString("Velocity Y : " + handler.player.getVelY(), x, y += 10);
        g.drawString("HP : " + handler.player.getHP() + "/" + handler.player.getMaxHP(), x, y += 10);
        g.drawString("Air : " + handler.player.getAir() + "/" + handler.player.getMaxAir(), x, y += 10);
    }
}
