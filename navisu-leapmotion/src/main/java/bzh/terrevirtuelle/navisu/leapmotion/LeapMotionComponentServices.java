/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.leapmotion;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge Nov 16, 2016
 */
public interface LeapMotionComponentServices
        extends ComponentService {

    void on(String... files);

    default void off() {
    }

    boolean canOpen(String category);

    InstrumentDriver getDriver();

}
