/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.logger.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.GpsLogger;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.GpsLoggerServices;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.impl.controller.GpsLoggerGpsEventsController;
import org.capcaval.c3.component.ComponentState;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class GpsLoggerImpl
        implements GpsLogger, GpsLoggerServices, InstrumentDriver, ComponentState {

    protected boolean on = false;
    private final String NAME = "GpsLogger";
    private GpsEventsController gpsEventsController;

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
            gpsEventsController = new GpsLoggerGpsEventsController();
            gpsEventsController.subscribe();
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
