/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.gps.plotter;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public interface GpsPlotterWithRouteServices
        extends ComponentService {

    void on();

   

    default void off() {
    }

    boolean isOn();

    boolean canOpen(String category);

    InstrumentDriver getDriver();

}
