/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.clock.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.clock.impl.ClockImpl;
import bzh.terrevirtuelle.navisu.instruments.common.controller.InstrumentController;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class ClockController
        extends InstrumentController
        implements Initializable {

    private final String FXML = "clock.fxml";
    protected ClockImpl instrument;


    public ClockController(ClockImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        load(FXML);
    }

}
