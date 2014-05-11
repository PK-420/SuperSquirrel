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

import java.awt.image.BufferedImage;
import main.framework.BufferedImageLoader;

/**
 * This class is used to load all the textures into memory
 * @author Patrick Kerr
 */
public final class Texture {
    
    private final SpriteSheet blockSheet, squirrelSheet, coinSheet, 
            numberSheet, healthFrameSheet, healthBarSheet, explosionSheet, 
            skeletonSheet, splatterSheet;
    
    /**
     * Numbers 0-9, / and x
     */
    public BufferedImage[] symbol = new BufferedImage[12];

    /**
     * Block textures
     */
    public BufferedImage[] block = new BufferedImage[20];

    /**
     * Player textures
     */
    public BufferedImage[] squirrel = new BufferedImage[54]; 

    /**
     * Skeleton mob textures
     */
    public BufferedImage[] skeleton = new BufferedImage[7];
    /**
     * Spinning Coin Textures
     */
    public BufferedImage[] coin = new BufferedImage[8];
    
    /**
     * Explosion Textures
     */
    public BufferedImage[] explosion = new BufferedImage[16];

    /**
     * Blood Splatter Textures
     */
    public BufferedImage[] splatter = new BufferedImage[6];
    
    /**
     * Health bar Frame Textures
     */
    public BufferedImage[] frame = new BufferedImage[6];

    /**
     * Health bar Bar Textures
     */
    public BufferedImage[] bar = new BufferedImage[6];
    
    /**
     * Bullet texture
     */
    public BufferedImage bullet = null;
    /**
     * Bullet Count HUD
     */
    public BufferedImage bulletSlot = null;
    /**
     * Background Cloud
     */
    public BufferedImage cloud = null;
    
    /**
     * Creates all the textures needed for the game
     */
    public Texture() {
        BufferedImageLoader loader = new BufferedImageLoader();
        cloud = loader.loadImage("/img/cloud2.png");
        bullet = loader.loadImage("/img/acorn.png");
        bulletSlot = loader.loadImage("/img/acornSlot.png");
        blockSheet = new SpriteSheet(loader.loadImage("/sprite/sprite_blocks.png"));
        squirrelSheet = new SpriteSheet(loader.loadImage("/sprite/sprite_player.png"));
        coinSheet = new SpriteSheet(loader.loadImage("/sprite/sprite_coin.png"));
        numberSheet = new SpriteSheet(loader.loadImage("/sprite/sprite_numbers.png"));
        explosionSheet = new SpriteSheet(loader.loadImage("/sprite/sprite_explosion.png"));
        healthFrameSheet = new SpriteSheet(loader.loadImage("/healthbar/frame.png"));
        healthBarSheet = new SpriteSheet(loader.loadImage("/healthbar/bars.png"));
        skeletonSheet = new SpriteSheet(loader.loadImage("/sprite/sprite_skeleton.png"));
        splatterSheet = new SpriteSheet(loader.loadImage("/sprite/sprite_blood.png"));
        getTextures();
    }
    
    private void getTextures() {   // Texture Loading
// Blood splatter frames
        splatter[0] = splatterSheet.grabImage(1, 1, 100, 100);
        splatter[1] = splatterSheet.grabImage(1, 2, 100, 100);
        splatter[2] = splatterSheet.grabImage(2, 2, 100, 100);
        splatter[3] = splatterSheet.grabImage(1, 3, 100, 100);
        splatter[4] = splatterSheet.grabImage(2, 3, 100, 100);
        splatter[5] = splatterSheet.grabImage(1, 4, 100, 100);

// Skeleton Frames (Mob)
        skeleton[0] = skeletonSheet.grabImage(2, 1, 32, 32); // Idle
        skeleton[1] = skeletonSheet.grabImage(1, 2, 32, 32);
        skeleton[2] = skeletonSheet.grabImage(2, 2, 32, 32);
        skeleton[3] = skeletonSheet.grabImage(3, 2, 32, 32);
        skeleton[4] = skeletonSheet.grabImage(1, 3, 32, 32);
        skeleton[5] = skeletonSheet.grabImage(2, 3, 32, 32);
        skeleton[6] = skeletonSheet.grabImage(3, 3, 32, 32);
        
// Explosion Frames
        explosion[0] = explosionSheet.grabImage(4, 4, 64, 64);
        explosion[1] = explosionSheet.grabImage(3, 4, 64, 64);
        explosion[2] = explosionSheet.grabImage(2, 4, 64, 64);
        explosion[3] = explosionSheet.grabImage(1, 4, 64, 64);
        explosion[4] = explosionSheet.grabImage(4, 3, 64, 64);
        explosion[5] = explosionSheet.grabImage(3, 3, 64, 64);
        explosion[6] = explosionSheet.grabImage(2, 3, 64, 64);
        explosion[7] = explosionSheet.grabImage(1, 3, 64, 64);
        explosion[8] = explosionSheet.grabImage(4, 2, 64, 64);
        explosion[9] = explosionSheet.grabImage(3, 2, 64, 64);
        explosion[10] = explosionSheet.grabImage(2, 2, 64, 64);
        explosion[11] = explosionSheet.grabImage(1, 2, 64, 64);
        explosion[12] = explosionSheet.grabImage(4, 1, 64, 64);
        explosion[13] = explosionSheet.grabImage(3, 1, 64, 64);
        explosion[14] = explosionSheet.grabImage(2, 1, 64, 64);
        explosion[15] = explosionSheet.grabImage(1, 1, 64, 64);

// Health Bars & Frame
        // Left Frame
        frame[0] = healthFrameSheet.grabImage(1, 1, 115, 30); // Background
        frame[1] = healthFrameSheet.grabImage(1, 2, 115, 30); // Green/Yellow Bar
        frame[2] = healthFrameSheet.grabImage(1, 3, 115, 30); // ForeGround
        
        // Right Frame
        frame[3] = healthFrameSheet.grabImage(2, 1, 115, 30); // Background
        frame[4] = healthFrameSheet.grabImage(2, 2, 115, 30); // Green/Yellow Bar
        frame[5] = healthFrameSheet.grabImage(2, 3, 115, 30); // ForeGround
        
        bar[0] = healthBarSheet.grabImage(1, 1, 56, 4); // Blue
        bar[1] = healthBarSheet.grabImage(1, 2, 56, 4); // Cyan
        bar[2] = healthBarSheet.grabImage(1, 3, 56, 4); // Green
        bar[3] = healthBarSheet.grabImage(1, 4, 56, 4); // Yellow
        bar[4] = healthBarSheet.grabImage(1, 5, 56, 4); // Orange
        bar[5] = healthBarSheet.grabImage(1, 6, 56, 4); // Red

// Numbers 0-9
        symbol[0] = numberSheet.grabImage(1, 1, 90, 90);
        symbol[1] = numberSheet.grabImage(2, 1, 90, 90);
        symbol[2] = numberSheet.grabImage(3, 1, 90, 90);
        symbol[3] = numberSheet.grabImage(4, 1, 90, 90);
        symbol[4] = numberSheet.grabImage(5, 1, 90, 90);
        symbol[5] = numberSheet.grabImage(6, 1, 90, 90);
        symbol[6] = numberSheet.grabImage(7, 1, 90, 90);
        symbol[7] = numberSheet.grabImage(8, 1, 90, 90);
        symbol[8] = numberSheet.grabImage(9, 1, 90, 90);
        symbol[9] = numberSheet.grabImage(10, 1, 90, 90);
// Signs / & x
        symbol[10] = numberSheet.grabImage(11, 1, 90, 90);
        symbol[11] = numberSheet.grabImage(12, 1, 90, 90);
// Spinning Coin Textures        
        coin[0] = coinSheet.grabImage(1, 1, 32, 32);
        coin[1] = coinSheet.grabImage(2, 1, 32, 32);
        coin[2] = coinSheet.grabImage(3, 1, 32, 32);
        coin[3] = coinSheet.grabImage(4, 1, 32, 32);
        coin[4] = coinSheet.grabImage(5, 1, 32, 32);
        coin[5] = coinSheet.grabImage(6, 1, 32, 32);
        coin[6] = coinSheet.grabImage(7, 1, 32, 32);
        coin[7] = coinSheet.grabImage(8, 1, 32, 32);
// Block Textures
        block[0] = blockSheet.grabImage(1, 1, 32, 32); // Grass Block;
        block[1] = blockSheet.grabImage(1, 2, 32, 32); // Dirt Block;
        block[2] = blockSheet.grabImage(2, 2, 32, 32); // Stone Block; 
        block[3] = blockSheet.grabImage(2, 1, 32, 32); // Ice Block;
        block[4] = blockSheet.grabImage(3, 3, 32, 32); // Water
        block[5] = blockSheet.grabImage(3, 4, 32, 32); // Water Bottom    
        
// Water Surface textures for animation
        block[16] = blockSheet.grabImage(1, 3, 32, 32);
        block[17] = blockSheet.grabImage(2, 3, 32, 32);
        block[18] = blockSheet.grabImage(1, 4, 32, 32);
        block[19] = blockSheet.grabImage(2, 4, 32, 32);
        
// Tall grass textures
        block[10] = blockSheet.grabImage(4, 4, 32, 32); // Bottom
        block[11] = blockSheet.grabImage(4, 3, 32, 32); // Top
        
        block[12] = blockSheet.grabImage(4, 2, 32, 32); // Bottom // Winter
        block[13] = blockSheet.grabImage(4, 1, 32, 32); // Top    // Gray
        
        block[14] = blockSheet.grabImage(3, 2, 32, 32); // Bottom
        block[15] = blockSheet.grabImage(3, 1, 32, 32); // Top
        
// Player Textures
        squirrel[0] = squirrelSheet.grabImage(5, 7, 32, 32); // still Front
        
        // Animation Going Right
        squirrel[1] = squirrelSheet.grabImage(5, 5, 32, 32); // still Right
        squirrel[2] = squirrelSheet.grabImage(1, 5, 32, 32);
        squirrel[3] = squirrelSheet.grabImage(2, 5, 32, 32);
        squirrel[4] = squirrelSheet.grabImage(3, 5, 32, 32);
        // Animation Going Left
        squirrel[5] = squirrelSheet.grabImage(5, 6, 32, 32); // still Left
        squirrel[6] = squirrelSheet.grabImage(1, 6, 32, 32);
        squirrel[7] = squirrelSheet.grabImage(2, 6, 32, 32);
        squirrel[8] = squirrelSheet.grabImage(3, 6, 32, 32);
        
        // Animation sneak right
        squirrel[9] = squirrelSheet.grabImage(7, 5, 32, 32);
        squirrel[10] = squirrelSheet.grabImage(8, 5, 32, 32);
        
        // Animation eat Right
        squirrel[11] = squirrelSheet.grabImage(1, 1, 32, 32);
        squirrel[12] = squirrelSheet.grabImage(2, 1, 32, 32);
        squirrel[13] = squirrelSheet.grabImage(3, 1, 32, 32);
        squirrel[14] = squirrelSheet.grabImage(4, 1, 32, 32);
        squirrel[15] = squirrelSheet.grabImage(5, 1, 32, 32);
        squirrel[16] = squirrelSheet.grabImage(6, 1, 32, 32);
        squirrel[17] = squirrelSheet.grabImage(7, 1, 32, 32);
        squirrel[18] = squirrelSheet.grabImage(8, 1, 32, 32);
        
        // Animation sneak left
        squirrel[19] = squirrelSheet.grabImage(7, 6, 32, 32);
        squirrel[20] = squirrelSheet.grabImage(8, 6, 32, 32);
        
        // Animation eat Left
        squirrel[21] = squirrelSheet.grabImage(1, 2, 32, 32);
        squirrel[22] = squirrelSheet.grabImage(2, 2, 32, 32);
        squirrel[23] = squirrelSheet.grabImage(3, 2, 32, 32);
        squirrel[24] = squirrelSheet.grabImage(4, 2, 32, 32);
        squirrel[25] = squirrelSheet.grabImage(5, 2, 32, 32);
        squirrel[26] = squirrelSheet.grabImage(6, 2, 32, 32);
        squirrel[27] = squirrelSheet.grabImage(7, 2, 32, 32);
        squirrel[28] = squirrelSheet.grabImage(8, 2, 32, 32);
        
        // Animation eat Front
        squirrel[31] = squirrelSheet.grabImage(1, 3, 32, 32);
        squirrel[32] = squirrelSheet.grabImage(2, 3, 32, 32);
        squirrel[33] = squirrelSheet.grabImage(3, 3, 32, 32);
        squirrel[34] = squirrelSheet.grabImage(4, 3, 32, 32);
        squirrel[35] = squirrelSheet.grabImage(5, 3, 32, 32);
        squirrel[36] = squirrelSheet.grabImage(6, 3, 32, 32);
        squirrel[37] = squirrelSheet.grabImage(7, 3, 32, 32);
        squirrel[38] = squirrelSheet.grabImage(8, 3, 32, 32);
        
        // Animation Death
        squirrel[39] = squirrelSheet.grabImage(7, 7, 32, 32);
        squirrel[40] = squirrelSheet.grabImage(8, 7, 32, 32);
        
        // Animation Enter World...
        squirrel[41] = squirrelSheet.grabImage(1, 7, 32, 32);
        squirrel[42] = squirrelSheet.grabImage(2, 7, 32, 32);
        squirrel[43] = squirrelSheet.grabImage(3, 7, 32, 32);

        // Animation Exit World...
        squirrel[44] = squirrelSheet.grabImage(1, 8, 32, 32);
        squirrel[45] = squirrelSheet.grabImage(2, 8, 32, 32);
        squirrel[46] = squirrelSheet.grabImage(3, 8, 32, 32);
    }
}
