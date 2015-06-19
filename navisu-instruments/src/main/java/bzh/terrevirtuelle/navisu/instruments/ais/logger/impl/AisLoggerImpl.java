/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.logger.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.logger.AisLogger;
import bzh.terrevirtuelle.navisu.instruments.ais.logger.AisLoggerServices;
import bzh.terrevirtuelle.navisu.instruments.ais.logger.impl.controller.AisLoggerAisEventsController;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class AisLoggerImpl
        implements AisLogger, InstrumentDriver, AisLoggerServices, ComponentState {

    @UsedService
    AisServices aisServices;

    protected boolean on = false;
    private final String NAME = "AisLogger";
    private AisEventsController aisEventsController;

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
        if (!aisServices.isOn()) {
            aisServices.on();
        }
        if (on == false) {
            on = false;
           aisEventsController = new AisLoggerAisEventsController(); 
           aisEventsController.subscribe();
        }
    }

    @Override
    public void off() {

        // Pb dans la lib C3 ? objet non retiré de la liste 
        if (on == true) {
            on = false;
           aisEventsController.unsubscribe(); 
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
