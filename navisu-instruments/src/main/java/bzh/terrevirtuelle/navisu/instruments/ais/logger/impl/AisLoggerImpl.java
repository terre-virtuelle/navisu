/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.logger.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisCreateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisDeleteStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisDeleteTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisUpdateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisUpdateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.logger.AisLogger;
import bzh.terrevirtuelle.navisu.instruments.ais.logger.AisLoggerServices;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class AisLoggerImpl
        implements AisLogger, AisLoggerServices, InstrumentDriver, ComponentState {

    @UsedService
    AisServices aisServices;

    ComponentManager cm;
    ComponentEventSubscribe<AisCreateStationEvent> aisCSEvent;
    ComponentEventSubscribe<AisCreateTargetEvent> aisCTEvent;
    ComponentEventSubscribe<AisDeleteStationEvent> aisDSEvent;
    ComponentEventSubscribe<AisDeleteTargetEvent> aisDTEvent;
    ComponentEventSubscribe<AisUpdateStationEvent> aisUSEvent;
    ComponentEventSubscribe<AisUpdateTargetEvent> aisUTEvent;
    protected boolean on = false;
    private final String NAME = "AisLogger";

    @Override
    public void componentInitiated() {
        cm = ComponentManager.componentManager;
        aisCSEvent = cm.getComponentEventSubscribe(AisCreateStationEvent.class);
        aisCTEvent = cm.getComponentEventSubscribe(AisCreateTargetEvent.class);
        aisDSEvent = cm.getComponentEventSubscribe(AisDeleteStationEvent.class);
        aisDTEvent = cm.getComponentEventSubscribe(AisDeleteTargetEvent.class);
        aisUSEvent = cm.getComponentEventSubscribe(AisUpdateStationEvent.class);
        aisUTEvent = cm.getComponentEventSubscribe(AisUpdateTargetEvent.class);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on() {
        if (!aisServices.isOn()) {
            aisServices.on();
        }
        if (on == false) {
            on = true;
            aisCTEvent.subscribe((AisCreateTargetEvent) (Ship updatedDate) -> {
                if (on) {
                    System.out.println(updatedDate);
                }
            });
            aisUTEvent.subscribe((AisUpdateTargetEvent) (Ship updatedDate) -> {
                if (on) {
                    System.out.println(updatedDate);
                }
            });
            aisDTEvent.subscribe((AisDeleteTargetEvent) (Ship updatedDate) -> {
                if (on) {
                    System.out.println(updatedDate);
                }
            });

            aisCSEvent.subscribe((AisCreateStationEvent) (BaseStation updatedDate) -> {
                if (on) {
                    System.out.println(updatedDate);
                }
            });
            aisUSEvent.subscribe((AisUpdateStationEvent) (BaseStation updatedDate) -> {
                if (on) {
                    System.out.println(updatedDate);
                }
            });
            aisDSEvent.subscribe((AisDeleteStationEvent) (BaseStation updatedDate) -> {
                if (on) {
                    System.out.println(updatedDate);
                }
            });
        }
    }

    @Override
    public void off() {
        // Pb dans la lib C3 ? objet non retiré de la liste 
        if (on == true) {
            on = false;
            aisCTEvent.unsubscribe((AisCreateTargetEvent) (Ship updatedDate) -> {
                //System.out.println("unsubscribe");
            });
            aisUTEvent.unsubscribe((AisUpdateTargetEvent) (Ship updatedDate) -> {
                // System.out.println("unsubscribe");
            });
            aisDTEvent.unsubscribe((AisDeleteTargetEvent) (Ship updatedDate) -> {
                //System.out.println("unsubscribe");
            });
            aisCSEvent.unsubscribe((AisCreateStationEvent) (BaseStation updatedDate) -> {
                //System.out.println("unsubscribe");
            });
            aisUSEvent.unsubscribe((AisUpdateStationEvent) (BaseStation updatedDate) -> {
                // System.out.println("unsubscribe");
            });
            aisDSEvent.unsubscribe((AisDeleteStationEvent) (BaseStation updatedDate) -> {
                // System.out.println("unsubscribe");
            });
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
