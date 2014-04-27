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
import java.util.LinkedList;
import main.framework.gameObjects.livingEntities.Player;

/**
 *
 * @author Patrick Kerr
 */
public final class Handler {

    /**
     * LinkedList containing all loaded GameObjects
     */
    public LinkedList<GameObject> mapObjects = new LinkedList<>();
    
    /**
     * The main character
     */
    public Player player = null;

    /**
     * Updating all GameObjects and player
     * @param input Keyboard Input to process
     */
    public void tick(GameKeyInput input) {
        if (player != null) {
            player.move(input);
            player.tick(mapObjects);
            if (player.isDisposable()) {
                player = null;
            }
        }
        
        for (GameObject tmpObj : mapObjects) {
            if (tmpObj.isDisposable()) { // deletes unused objects?!
                removeObject(tmpObj);
                return;
            }
            tmpObj.tick(mapObjects);
        }
    }

    /**
     * Renders all GameObjects and player
     * @param g Graphics on which to draw
     */
    public void render(Graphics g) {
        for (GameObject tempObj : mapObjects) {
            tempObj.render(g);
        }
        if (player != null) {
            player.render(g);
        }
    }

    /**
     * Adds a new GameObject to the handler list
     * @param mapObj GameObject to add
     */
    public void addObject(GameObject mapObj) {
        this.mapObjects.add(mapObj);
    }
    
    /**
     * Adds a new GameObject to the beginning of the handler list
     * @param mapObj GameObject to add
     */
    public void addObjectFirst(GameObject mapObj) {
        this.mapObjects.addFirst(mapObj);
    }
    
    /**
     * Adds a new GameObject to the end of the handler list
     * @param mapObj GameObject to add
     */
    public void addObjectLast(GameObject mapObj) {
        this.mapObjects.addLast(mapObj);
    }

    /**
     * Removes a specified object from the handler list
     * @param mapObj GameObject to remove
     */
    public void removeObject(GameObject mapObj) {
        this.mapObjects.remove(mapObj);
    }
    
    /**
     * Adds the player to the handler
     * @param mapObj GameObject to add
     */
    public void addPlayer(Player mapObj) {
        this.player = mapObj;
    }

    /**
     * Removes the player
     */
    public void removePlayer() {
        this.player = null;
    }

    public void clear() {
        this.mapObjects.clear();
        this.player = null;
    }
}
