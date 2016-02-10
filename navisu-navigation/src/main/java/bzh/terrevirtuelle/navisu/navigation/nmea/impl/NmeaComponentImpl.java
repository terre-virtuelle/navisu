/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.nmea.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.HDGEvent;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.navigation.nmea.NmeaComponent;
import bzh.terrevirtuelle.navisu.navigation.nmea.NmeaComponentServices;
import bzh.terrevirtuelle.navisu.navigation.nmea.impl.controller.NmeaComponentController;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 15 octobre 2015
 * @author Serge Morvan
 */
public class NmeaComponentImpl
        implements NmeaComponent, NmeaComponentServices, InstrumentDriver, ComponentState {

    ComponentManager cm;
    ComponentEventSubscribe<HDGEvent> hdgEvent;
    
    private final String KEY_NAME = "Nmea";
    private NmeaComponentController controller;

    @Override
    public void componentInitiated() {
        cm = ComponentManager.componentManager;
        hdgEvent = cm.getComponentEventSubscribe(HDGEvent.class);

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(KEY_NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void on(String... files) {
     //   controller.subscribe();
    }

    @Override
    public void off() {
     //   controller.unsubscribe();
    }

    @Override
    public NMEA getHDG() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     

}
