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

import javax.sound.sampled.Clip;

/**
 *
 * @author Patrick Kerr
 */
public final class SFX {
    
    private static Clip clip;
    private static final AudioClipLoader loader = new AudioClipLoader();
    
    /**
     * Plays the specified sound once
     * @param path
     */
    public static void play(String path) {
        clip = loader.loadSFX(path);
        clip.start();
    }
    
    /**
     * Plays the specified sound for x times
     * @param path Path to the sound clip
     * @param x Numbers of time to loop (x must be greater than 0)
     */
    public static void play(String path, int x) {
        if (x > 0) {
            clip = loader.loadSFX(path);
            clip.loop(x - 1);
        }
    }
}
