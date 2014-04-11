/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.loggers.impl;

import bzh.terrevirtuelle.navisu.loggers.Logger;
import bzh.terrevirtuelle.navisu.loggers.LoggerServices;
import bzh.terrevirtuelle.navisu.loggers.controller.LoggerController;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author Serge
 */
public class LoggerImpl
        implements Logger, LoggerServices, ComponentState {

    LoggerController loggerController;

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
    public void createPrinter() {
        loggerController = new LoggerController();
    }

    @Override
    public void createPrinter(NMEA nmea) {
        loggerController = new LoggerController(nmea);
          }

}
