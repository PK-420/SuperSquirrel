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

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Patrick Kerr
 */
public final class Window {
    
    /**
     * Creates a new Game Window
     * @param w Desired Window Height
     * @param h Desired Window Width
     * @param title Title to display in TaskBar
     * @param game Game canvas to add to frame
     */
    public Window(int w, int h, String title, Game game) {
        game.setPreferredSize(new Dimension(w, h));
        game.setMaximumSize(new Dimension(w, h));
        game.setMinimumSize(new Dimension(w, h));
        
        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        JOptionPane.showMessageDialog(game, "Welcome to my platformer game prototype\nwhere the only goal is to have fun!\n\nPress F1 at anytime to get help :)");
        game.start();
    }
}
