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
import javax.sound.sampled.FloatControl;

/**
 * This class is used for looping music tracks
 * @author Patrick Kerr
 */
public class Music {
    
    private final Clip clip;
    private final FloatControl gainControl;
    private final AudioClipLoader loader = new AudioClipLoader();
    /**
     * Creates a looping Music track
     * @param path Audio file to loop
     */
    public Music(String path) {
        clip = loader.loadSFX(path);
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    
    /**
     * Creates a looping Music track
     * @param path Audio file to loop
     * @param gain Level of audio gain
     */
    public Music(String path, float gain) {
        clip = loader.loadSFX(path);
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(gain);
    }
    
    /**
     * Starts the looping track
     */
    public void start() {
        clip.loop(-1);
    }
    
    /**
     * Pauses the looping track
     */
    public void pause() {
        clip.stop();
    }
    
    /**
     * @param gain Desired gain level for the clip
     */
    public void setGainLevel(float gain) {
        if (gain > gainControl.getMaximum()) {
            gainControl.setValue(gainControl.getMaximum());
        } else if (gain < gainControl.getMinimum()) {
            gainControl.setValue(gainControl.getMinimum());
        } else {
            gainControl.setValue(gain);
        }
    }
    
    /**
     * @return Current gain level of the clip
     */
    public float getGainLevel() {
        return gainControl.getValue();
    }
}
