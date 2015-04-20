/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.logger.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VTGEvent;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.GpsLogger;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.GpsLoggerServices;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class GpsLoggerImpl
        implements GpsLogger, GpsLoggerServices, InstrumentDriver, ComponentState {

    ComponentManager cm;
    ComponentEventSubscribe<GGAEvent> ggaES;
    ComponentEventSubscribe<RMCEvent> rmcES;
    ComponentEventSubscribe<VTGEvent> vtgES;

    protected boolean on = false;
    private final String NAME = "GpsLogger";

    @Override
    public void componentInitiated() {
        cm = ComponentManager.componentManager;
        ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
        rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
        vtgES = cm.getComponentEventSubscribe(VTGEvent.class);

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on() {

        if (on == false) {
            on = true;
            // souscription aux événements GPS
            ggaES.subscribe(new GGAEvent() {

                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                    GGA data = (GGA) d;
                    if (on) {
                        System.out.println(data);
                    }
                }
            });

            vtgES.subscribe(new VTGEvent() {

                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    VTG data = (VTG) d;
                    if (on) {
                        System.out.println(data);
                    }
                }
            });
            rmcES.subscribe(new RMCEvent() {

                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    RMC data = (RMC) d;
                    if (on) {
                        System.out.println(data);
                    }
                }
            });

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
