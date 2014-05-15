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

package main;

import main.graphics.*;
import main.framework.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * The Game class is the main class which runs the game thread;
 * the game loop regulates the speed of the game to tick at the "tps" value specified,
 * the rendering occurs as often as possible, so you should get more Frames/s than Ticks/s
 * This class also listens for keyboard input
 * @author Patrick Kerr
 */
public final class Game extends Canvas implements Runnable {

    /**
     * Game scaling size (Same as block size)
     */
    public static final int scale = 32;
    /**
     * Height of the game frame
     */
    public static int HEIGHT;
    /**
     * Width of the game frame
     */
    public static int WIDTH;
    /**
     * Ticks per second
     */
    public static final double tps = 60.0;
    /**
     * Contains the textures
     */
    protected static Texture tex;
    /**
     * Quantity of frames in the last second
     */
    public int fps;
    /**
     * Quantity of ticks in the last second
     */
    public int ups;
    
    private boolean running = false;
    private static boolean paused = false;
    private Thread thread;
    private GameKeyInput input;
    private Level level;
    
    private static Window gameFrame;
    private static Camera cam;
    private static HUD hud;
    
    private final Random r = new Random();
    private final BufferedImageLoader loader = new BufferedImageLoader();
    private final Handler handler = new Handler();
    
    private final Music backgroundMusic = new Music("/audio/background.wav", -5f);
    
    public static void main(String args[]) {
        gameFrame = new Window(800, 600, "SuperSquirrel Prototype -- ©2014 Patrick Kerr", new Game());
    }
    
    /**
     * @return The textures currently loaded
     */
    public static Texture getTexture() {
        return tex;
    }
    
    /**
     * @return The camera used
     */
    public static Camera getCamera() {
        return cam;
    }
    
    /**
     * Thread starter
     */
    public synchronized void start(){
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private void init() {
        System.out.println("Thread Start!");

        HEIGHT = getHeight();
        WIDTH = getWidth();
        
        tex = new Texture();
        cam = new Camera(0, 0);
        hud = new HUD(this, handler);
        
        level = new Level(loader.loadImage("/level/level1.png"), handler);
        
        input = GameKeyInput.getInstance(); // Creates keyboard listener
        this.addKeyListener(input); // Starts keyboard listener
        
        this.requestFocus();
        
        backgroundMusic.start();
        
        System.out.println("Ready!");
    }
    
    /**
     * Game loop
     * Tick rate and rendering call
     */
    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / tps;
        double d = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        
        while (running) {
            long now = System.nanoTime();
            d += (now - lastTime) / ns;
            lastTime = now;
            while (d >= 1) {
                if (!paused) tick();
                pollKeyboard();
                updates++;
                d--;
            }
            render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = frames;
                ups = updates;
                frames = 0;
                updates = 0;
            }
        }
    }
    
    private void tick() {
        hud.tick();
        handler.tick(input);
        cam.tick(handler.player);
    }
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        /////////// Drawing begins;
        g.setColor(new Color(25, 191, 224));  // background color
        g.fillRect(0, 0, getWidth(), getHeight()); // Fill
        for (int i = 0; i < (level.getWidth() * scale) / (tex.cloud.getWidth() / 2); i++) { // Clouds
            g.drawImage(tex.cloud, (int)-cam.getX()/2 + ((tex.cloud.getWidth()/3) * i), (i % 3) * (i % 4) * 10, tex.cloud.getWidth()/2, tex.cloud.getHeight()/2, null);
        }
        g2d.translate(-cam.getX(), -cam.getY()); // Cam Begins 
        handler.render(g); // Object rendering
        g2d.translate(cam.getX(), cam.getY()); // Cam Ends

        hud.render(g); // Draw HUD
        /////////// Drawing ends;
        
        g.dispose();
        bs.show();
    }
    
    public static boolean isPaused() {
        return paused;
    }
    
    private void togglePause() {
        if (paused) {
            paused = false;
            backgroundMusic.start();
        } else {
            paused = true;
            backgroundMusic.pause();
        }
    }
   
    private void pollKeyboard() {
//        if (input.isKeyDown(KeyEvent.VK_ADD)) backgroundMusic.setGainLevel(backgroundMusic.getGainLevel() + 0.7f);
//        if (input.isKeyDown(KeyEvent.VK_SUBTRACT)) backgroundMusic.setGainLevel(backgroundMusic.getGainLevel() - 0.7f);
        if (input.isKeyReleased()) {
            if (input.isKeyUp(KeyEvent.VK_P) || input.isKeyUp(KeyEvent.VK_PAUSE)) togglePause(); // P and PAUSE = Pause Game
            if (input.isKeyUp(KeyEvent.VK_F1)) { // Help Popup
                Thread t = new Thread() {
                    @Override
                    public void run() {
                    JOptionPane.showMessageDialog(null, "You can use either WASD or the arrows to navigate."
                        + "\n\nW / Up = Jump\nA / Left = Left\nS / Down = Unassigned\n"
                        + "D / Right = Right\n\nHold Shift = Sprint\n\nSpacebar = Shoot"
                        + "\nR / NumPad 0 = Reload"
                        + "\n\n1 / 2 = Select gun"
                        + "\n\nF3 = Display / Hide Performance Overlay"
                        + "\nF12 = Reload the test level", "Controls", JOptionPane.INFORMATION_MESSAGE); // Find a better solution than JOptionPane
                    }
                };
                t.start();
            }
            if (input.isKeyUp(KeyEvent.VK_F3)) { // Toggle FPS & TPS
                hud.toggleDebugInfo();
            }
            if (input.isKeyUp(KeyEvent.VK_F12)) {
                level = new Level(loader.loadImage("/level/level1.png"), handler);
            }
            if (input.isKeyUp(KeyEvent.VK_F11)) {                    
                level = new Level(loader.loadImage("/level/flat.png"), handler);
            }
        }
        input.update();
    }
}