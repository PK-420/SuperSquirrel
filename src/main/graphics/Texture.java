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
 *
 * @author Patrick Kerr
 */
public final class Texture {
    
    private final SpriteSheet bs, ss, cs, ns, hfs, hbs;
    
    public BufferedImage[] symbol = new BufferedImage[12]; // Numbers 0-9, / & x
    public BufferedImage[] block = new BufferedImage[20]; // Block textures
    public BufferedImage[] squirrel = new BufferedImage[54]; // Player textures
    public BufferedImage[] coin = new BufferedImage[8]; // Spinning Coin Textures
    
    public BufferedImage[] frame = new BufferedImage[6];
    public BufferedImage[] bar = new BufferedImage[6];
    
    public BufferedImage acorn = null; // Bullet Texture
    public BufferedImage acornSlot = null; // Bullet Count HUD
    public BufferedImage cloud = null; // Background Cloud
    
    public Texture() {
        BufferedImageLoader loader = new BufferedImageLoader();
        cloud = loader.loadImage("/img/cloud2.png");
        acorn = loader.loadImage("/img/acorn.png");
        acornSlot = loader.loadImage("/img/acornSlot.png");
        bs = new SpriteSheet(loader.loadImage("/sprite/sprite_blocks.png"));
        ss = new SpriteSheet(loader.loadImage("/sprite/sprite_player.png"));
        cs = new SpriteSheet(loader.loadImage("/sprite/sprite_coin.png"));
        ns = new SpriteSheet(loader.loadImage("/sprite/sprite_numbers.png"));
        hfs = new SpriteSheet(loader.loadImage("/healthbar/frame.png"));
        hbs = new SpriteSheet(loader.loadImage("/healthbar/bars.png"));
        getTextures();
    }
    
    private void getTextures() {   // Texture Loading
// Health Bars & Frame
        // Left Frame
        frame[0] = hfs.grabImage(1, 1, 115, 30); // Background
        frame[1] = hfs.grabImage(1, 2, 115, 30); // Green/Yellow Bar
        frame[2] = hfs.grabImage(1, 3, 115, 30); // ForeGround
        
        // Right Frame
        frame[3] = hfs.grabImage(2, 1, 115, 30); // Background
        frame[4] = hfs.grabImage(2, 2, 115, 30); // Green/Yellow Bar
        frame[5] = hfs.grabImage(2, 3, 115, 30); // ForeGround
        
        bar[0] = hbs.grabImage(1, 1, 56, 4); // Blue
        bar[1] = hbs.grabImage(1, 2, 56, 4); // Cyan
        bar[2] = hbs.grabImage(1, 3, 56, 4); // Green
        bar[3] = hbs.grabImage(1, 4, 56, 4); // Yellow
        bar[4] = hbs.grabImage(1, 5, 56, 4); // Orange
        bar[5] = hbs.grabImage(1, 6, 56, 4); // Red

// Numbers 0-9
        symbol[0] = ns.grabImage(1, 1, 90, 90);
        symbol[1] = ns.grabImage(2, 1, 90, 90);
        symbol[2] = ns.grabImage(3, 1, 90, 90);
        symbol[3] = ns.grabImage(4, 1, 90, 90);
        symbol[4] = ns.grabImage(5, 1, 90, 90);
        symbol[5] = ns.grabImage(6, 1, 90, 90);
        symbol[6] = ns.grabImage(7, 1, 90, 90);
        symbol[7] = ns.grabImage(8, 1, 90, 90);
        symbol[8] = ns.grabImage(9, 1, 90, 90);
        symbol[9] = ns.grabImage(10, 1, 90, 90);
// Signs / & x
        symbol[10] = ns.grabImage(11, 1, 90, 90);
        symbol[11] = ns.grabImage(12, 1, 90, 90);
// Spinning Coin Textures        
        coin[0] = cs.grabImage(1, 1, 32, 32);
        coin[1] = cs.grabImage(2, 1, 32, 32);
        coin[2] = cs.grabImage(3, 1, 32, 32);
        coin[3] = cs.grabImage(4, 1, 32, 32);
        coin[4] = cs.grabImage(5, 1, 32, 32);
        coin[5] = cs.grabImage(6, 1, 32, 32);
        coin[6] = cs.grabImage(7, 1, 32, 32);
        coin[7] = cs.grabImage(8, 1, 32, 32);
// Block Textures
        block[0] = bs.grabImage(1, 1, 32, 32); // Grass Block;
        block[1] = bs.grabImage(1, 2, 32, 32); // Dirt Block;
        block[2] = bs.grabImage(2, 2, 32, 32); // Stone Block; 
        block[3] = bs.grabImage(2, 1, 32, 32); // Ice Block;
        block[4] = bs.grabImage(3, 3, 32, 32); // Water
        block[5] = bs.grabImage(3, 4, 32, 32); // Water Bottom    
        
// Water Surface textures for animation
        block[16] = bs.grabImage(1, 3, 32, 32);
        block[17] = bs.grabImage(2, 3, 32, 32);
        block[18] = bs.grabImage(1, 4, 32, 32);
        block[19] = bs.grabImage(2, 4, 32, 32);
        
// Tall grass textures
        block[10] = bs.grabImage(4, 4, 32, 32); // Bottom
        block[11] = bs.grabImage(4, 3, 32, 32); // Top
        
        block[12] = bs.grabImage(4, 2, 32, 32); // Bottom // Winter
        block[13] = bs.grabImage(4, 1, 32, 32); // Top    // Gray
        
        block[14] = bs.grabImage(3, 2, 32, 32); // Bottom
        block[15] = bs.grabImage(3, 1, 32, 32); // Top
        
// Player Textures
        squirrel[0] = ss.grabImage(5, 7, 32, 32); // still Front
        
        // Animation Going Right
        squirrel[1] = ss.grabImage(5, 5, 32, 32); // still Right
        squirrel[2] = ss.grabImage(1, 5, 32, 32);
        squirrel[3] = ss.grabImage(2, 5, 32, 32);
        squirrel[4] = ss.grabImage(3, 5, 32, 32);
        // Animation Going Left
        squirrel[5] = ss.grabImage(5, 6, 32, 32); // still Left
        squirrel[6] = ss.grabImage(1, 6, 32, 32);
        squirrel[7] = ss.grabImage(2, 6, 32, 32);
        squirrel[8] = ss.grabImage(3, 6, 32, 32);
        
        // Animation sneak right
        squirrel[9] = ss.grabImage(7, 5, 32, 32);
        squirrel[10] = ss.grabImage(8, 5, 32, 32);
        
        // Animation eat Right
        squirrel[11] = ss.grabImage(1, 1, 32, 32);
        squirrel[12] = ss.grabImage(2, 1, 32, 32);
        squirrel[13] = ss.grabImage(3, 1, 32, 32);
        squirrel[14] = ss.grabImage(4, 1, 32, 32);
        squirrel[15] = ss.grabImage(5, 1, 32, 32);
        squirrel[16] = ss.grabImage(6, 1, 32, 32);
        squirrel[17] = ss.grabImage(7, 1, 32, 32);
        squirrel[18] = ss.grabImage(8, 1, 32, 32);
        
        // Animation sneak left
        squirrel[19] = ss.grabImage(7, 6, 32, 32);
        squirrel[20] = ss.grabImage(8, 6, 32, 32);
        
        // Animation eat Left
        squirrel[21] = ss.grabImage(1, 2, 32, 32);
        squirrel[22] = ss.grabImage(2, 2, 32, 32);
        squirrel[23] = ss.grabImage(3, 2, 32, 32);
        squirrel[24] = ss.grabImage(4, 2, 32, 32);
        squirrel[25] = ss.grabImage(5, 2, 32, 32);
        squirrel[26] = ss.grabImage(6, 2, 32, 32);
        squirrel[27] = ss.grabImage(7, 2, 32, 32);
        squirrel[28] = ss.grabImage(8, 2, 32, 32);
        
        // Animation eat Front
        squirrel[31] = ss.grabImage(1, 3, 32, 32);
        squirrel[32] = ss.grabImage(2, 3, 32, 32);
        squirrel[33] = ss.grabImage(3, 3, 32, 32);
        squirrel[34] = ss.grabImage(4, 3, 32, 32);
        squirrel[35] = ss.grabImage(5, 3, 32, 32);
        squirrel[36] = ss.grabImage(6, 3, 32, 32);
        squirrel[37] = ss.grabImage(7, 3, 32, 32);
        squirrel[38] = ss.grabImage(8, 3, 32, 32);
        
        // Animation Death
        squirrel[39] = ss.grabImage(7, 7, 32, 32);
        squirrel[40] = ss.grabImage(8, 7, 32, 32);
        
        // Animation Enter World...
        squirrel[41] = ss.grabImage(1, 7, 32, 32);
        squirrel[42] = ss.grabImage(2, 7, 32, 32);
        squirrel[43] = ss.grabImage(3, 7, 32, 32);

        // Animation Exit World...
        squirrel[44] = ss.grabImage(1, 8, 32, 32);
        squirrel[45] = ss.grabImage(2, 8, 32, 32);
        squirrel[46] = ss.grabImage(3, 8, 32, 32);
    }
}
