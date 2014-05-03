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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Use this class to listen to keyboard inputs
 * @author Patrick Kerr
 */
public final class GameKeyInput implements KeyListener {

    private static GameKeyInput instance = null;
    
    private boolean[] keyDown = new boolean[256];
    private boolean[] keyUp = new boolean[256];
    
    private boolean keyPressed = false;
    private boolean keyReleased = false;
    
    /**
     * Gets or create the instance of a GameKeyInput
     * @return The running instance of a GameKeyInput
     */
    public static final GameKeyInput getInstance() {
        if (instance == null) {
            instance = new GameKeyInput();
        }
        return instance;
    }

    /**
     * Unused
     * @param e KeyEvent caught
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    /**
     * Catches the key pressed and stores it in a boolean array
     * @param e KeyEvent caught
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < 256) {
            keyDown[e.getKeyCode()] = true;
            keyUp[e.getKeyCode()] = false;
            keyPressed = true;
            keyReleased = false;
        }
    }
    /**
     * Catches the key released and stores it in a boolean array
     * @param e KeyEvent caught
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < 256) {
            keyUp[e.getKeyCode()] = true;
            keyDown[e.getKeyCode()] = false;
            keyReleased = true;
            keyPressed = false;
        }
    }

    /**
     * Refreshes the GameKeyInput, clearing the key up states.
     */
    public void update() {
    //clear out the key up states
    keyUp = new boolean[256];
    keyReleased = false;
//    //clear out the key down states
//    keyDown = new boolean[256];
//    keyPressed = false;
//    if( keyCache.length() > 1024 ) {
//            keyCache = "";
//        }
    }

    /**
     * Checks if the specified key is currently pressed
     * @param k Key to be checked
     * @return True if k is down, else false;
     */
    public boolean isKeyDown(int k) {
        return keyDown[k];
    }
    /**
     * Checks if the specified key is currently released
     * @param k Key to be checked
     * @return True if k is up, else false;
     */
    public boolean isKeyUp(int k) {
        return keyUp[k];
    }
    
    /**
     * @return True if any Key was pressed
     */
    public boolean isKeyPressed() {
        return keyPressed;
    }
    /**
     * @return True if any Key was released since the last update();
     */
    public boolean isKeyReleased() {
        return keyReleased;
    }
}
