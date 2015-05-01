/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.view;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Serge
 */
public interface GRShip 
extends EventHandler<KeyEvent>{

    @Override
    default void handle(KeyEvent event) {
    }
}
