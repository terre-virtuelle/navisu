/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.nmea;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 15 oct. 2015
 * @author Serge Morvan
 */
public interface NmeaComponentServices
        extends ComponentService {

    void on(String... files);

    void off();

    public boolean canOpen(String category);

    public InstrumentDriver getDriver();

    public NMEA getHDG();
}
