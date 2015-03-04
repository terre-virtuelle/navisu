/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.logger.impl;

import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
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
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class AisLoggerImpl
        implements AisLogger, AisLoggerServices, ComponentState {

    ComponentManager cm;
    ComponentEventSubscribe<AisCreateStationEvent> aisCSEvent;
    ComponentEventSubscribe<AisCreateTargetEvent> aisCTEvent;
    ComponentEventSubscribe<AisDeleteStationEvent> aisDSEvent;
    ComponentEventSubscribe<AisDeleteTargetEvent> aisDTEvent;
    ComponentEventSubscribe<AisUpdateStationEvent> aisUSEvent;
    ComponentEventSubscribe<AisUpdateTargetEvent> aisUTEvent;

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
        
        aisCTEvent.subscribe((AisCreateTargetEvent) (Ship updatedDate) -> {
            System.out.println(updatedDate);
        });
        aisUTEvent.subscribe((AisUpdateTargetEvent) (Ship updatedDate) -> {
            System.out.println(updatedDate);
        });
        aisDTEvent.subscribe((AisDeleteTargetEvent) (Ship updatedDate) -> {
            System.out.println(updatedDate);
        });
        
       
         aisCSEvent.subscribe((AisCreateStationEvent) (BaseStation updatedDate) -> {
            System.out.println(updatedDate);
        });
         aisUSEvent.subscribe((AisUpdateStationEvent) (BaseStation updatedDate) -> {
            System.out.println(updatedDate);
        });
         aisDSEvent.subscribe((AisDeleteStationEvent) (BaseStation updatedDate) -> {
            System.out.println(updatedDate);
        });       
    }

    @Override
    public void off() {
       
        aisCTEvent.unsubscribe((AisCreateTargetEvent) (Ship updatedDate) -> {
            System.out.println(updatedDate);
        });
        aisUTEvent.unsubscribe((AisUpdateTargetEvent) (Ship updatedDate) -> {
            System.out.println(updatedDate);
        });
        aisDTEvent.unsubscribe((AisDeleteTargetEvent) (Ship updatedDate) -> {
            System.out.println(updatedDate);
        });
       
        aisCSEvent.unsubscribe((AisCreateStationEvent) (BaseStation updatedDate) -> {
            System.out.println(updatedDate);
        });
         aisUSEvent.unsubscribe((AisUpdateStationEvent) (BaseStation updatedDate) -> {
            System.out.println(updatedDate);
        });
        aisDSEvent.unsubscribe((AisDeleteStationEvent) (BaseStation updatedDate) -> {
            System.out.println(updatedDate);
        });
    }
}
