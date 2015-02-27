/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.loggers;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge
 */
public interface LoggerServices
        extends ComponentService {

    public void createPrinter();
    public void createPrinter(NMEA nmea);
}
