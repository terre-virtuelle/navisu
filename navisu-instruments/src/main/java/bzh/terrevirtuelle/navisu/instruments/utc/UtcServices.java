/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this clock file, choose Tools | Templates
 * and open the clock in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.utc;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public interface UtcServices
        extends ComponentService {

    void on(String ... files);

    default void off() {
    }

    boolean canOpen(String category);

    InstrumentDriver getDriver();
}
