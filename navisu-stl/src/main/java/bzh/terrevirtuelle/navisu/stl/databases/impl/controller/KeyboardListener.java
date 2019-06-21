/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.stl.databases.impl.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author serge
 * @date Jun 21, 2019
 */
public class KeyboardListener 
        implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
        //== KeyEvent.VK_SHIFT
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
