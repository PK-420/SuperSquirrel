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

import main.framework.gameObjects.livingEntities.Player;
import main.framework.gameObjects.*;
import java.awt.image.BufferedImage;
import main.Game;
import main.framework.gameObjects.livingEntities.Skeleton;
import main.framework.gameObjects.livingEntities.Squirrel;

/**
 * This class is used to load a level into an Handler by converting a BufferedImage into objects (pixel by pixel)
 * @author Patrick Kerr
 */
public final class Level {
    private static final int H = 20; // Maximum level Height (bmp)
    private static int WIDTH;
    private static int HEIGHT;
    
    /**
     * Creates a level from a BufferedImage and loads it into the handler
     * @param level BufferedImage to be converted into a level
     * @param handler Handler that will contain all the level objects including the player
     */
    public Level(BufferedImage level, Handler handler) {
        WIDTH = level.getWidth();
        HEIGHT = level.getHeight();
        Player player = handler.player;
        handler.clear();
        load(level, handler); // Objects loading
        if (player != null && handler.player != null) { // keeps old player, only sets new position
            player.x = handler.player.x;
            player.y = handler.player.y;
            handler.player = player;
        }
    }
    
    private void load(BufferedImage image, Handler handler) { //Pixel color to object conversion
//        int size = (Game.HEIGHT / HEIGHT);
        System.out.println("Level: " + WIDTH + "x" + H + " (" + WIDTH + "x" + HEIGHT + " total)");
        for (int yy = 0; yy < H; yy++) {
            for (int xx = 0; xx < WIDTH; xx++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16)& 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if (red == 255 && green == 0 && blue == 255) handler.addPlayer(new Squirrel(xx * Game.scale, yy * Game.scale, handler)); // Player
                
                if (red == 0 && green == 128 && blue == 255) handler.addObject(new Block(xx,yy, ObjectId.Water, -1)); // water top (Anim)
                if (red == 0 && green == 0 && blue == 255) handler.addObject(new Block(xx,yy, ObjectId.Water, 4)); // Water
                if (red == 128 && green == 0 && blue == 255) handler.addObject(new Block(xx,yy, ObjectId.Water, 5)); // Water Bottom
                if (red == 0 && green == 255 & blue == 255) handler.addObject(new Block(xx, yy, ObjectId.Ice, 3));// Ice
                if (red == 64 && green == 64 && blue == 64) handler.addObject(new Block(xx, yy, ObjectId.Stone, 2)); // Stone
                if (red == 128 && green == 64 && blue == 0) handler.addObject(new Block(xx, yy, ObjectId.Dirt, 1)); // Dirt;
                if (red == 0 && green == 255 && blue == 0) handler.addObject(new Block(xx, yy, ObjectId.Grass, 0)); // Grass
                
                if (red == 0 && green == 32 && blue == 0) { // Plant Small
                    handler.addObjectFirst(new Block(xx, yy, ObjectId.Fern, 11));
                }
                if (red == 0 && green == 64 && blue == 0) {
                    handler.addObjectFirst(new Block(xx, yy, ObjectId.Fern, 10)); // Plant Large
                    handler.addObjectFirst(new Block(xx, yy - 1, ObjectId.Fern, 11));
                }
                if (red == 0 && green == 192 && blue == 0) { // Fern Small
                    handler.addObjectFirst(new Block(xx, yy, ObjectId.Fern, 15));
                }
                if (red == 0 && green == 128 && blue == 0) { // Fern Large
                    handler.addObjectFirst(new Block(xx, yy, ObjectId.Fern, 14));
                    handler.addObjectFirst(new Block(xx, yy - 1, ObjectId.Fern, 15));
                }
                if (red == 128 && green == 128 && blue == 128) { // Winter Fern Small
                    handler.addObjectFirst(new Block(xx, yy, ObjectId.Fern, 13));
                }
                if (red == 192 && green == 192 && blue == 192) { // Winter Fern Large
                    handler.addObjectFirst(new Block(xx, yy, ObjectId.Fern, 12));
                    handler.addObjectFirst(new Block(xx, yy - 1, ObjectId.Fern, 13));
                }
                
                if (red == 255 && green == 128 && blue == 0) handler.addObject(new Coin(xx * Game.scale, yy * Game.scale, 7)); // Coin
                
                if (red == 0 && green == 0 && blue == 0) handler.addObjectLast(new Skeleton(xx, yy)); // Mob
                
                ////////////////// To be Added (RGB) /////////////////////
                // 255 0 0 : Fire
                // 150 0 0 : Lava
                // 128 128 0 : Door
                // 192 128 0 : Flag
                // 255 128 0 : Coin
                // ? ? ? : Special "?" Block
                //////////////////////////////////////////////////////////
            }
        }
    }

    /**
     * @return the level width (in blocks)
     */
    public int getWidth() {
        return WIDTH;
    }
    /**
     * @return the level height (in blocks)
     */
    public int getHeight() {
        return HEIGHT;
    }
}
