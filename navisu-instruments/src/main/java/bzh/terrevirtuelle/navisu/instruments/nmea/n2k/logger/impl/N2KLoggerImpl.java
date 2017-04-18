/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.nmea.n2k.logger.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.impl.controller.GpsLoggerGpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.nmea.n2k.logger.N2KLogger;
import bzh.terrevirtuelle.navisu.instruments.nmea.n2k.logger.N2KLoggerServices;
import bzh.terrevirtuelle.navisu.instruments.nmea.n2k.logger.impl.controller.N2KLoggerController;
import org.capcaval.c3.component.ComponentState;

/**
 * @date 18 avril 2017
 * @author Serge Morvan
 */
public class N2KLoggerImpl
        implements N2KLogger, N2KLoggerServices, InstrumentDriver, ComponentState {

    protected boolean on = false;
    private final String NAME = "N2KLogger";
    private N2KLoggerController n2KLoggerController;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on(String... files) {
        if (on == false) {
            on = true;
            n2KLoggerController = new N2KLoggerController();
            n2KLoggerController.subscribe();
        }
    }

    @Override
    public void off() {
        // Pb dans la lib C3 ? objet non retiré de la liste 
        if (on == true) {
            on = false;
        }
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(NAME);
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
