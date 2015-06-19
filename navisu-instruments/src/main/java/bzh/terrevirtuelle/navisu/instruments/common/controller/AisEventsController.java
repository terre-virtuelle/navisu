/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.common.controller;

import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateTargetEvent;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 18 juin 2015
 * @author Serge Morvan
 */
public class AisEventsController {

    ComponentManager cm;
    ComponentEventSubscribe<AisCreateStationEvent> aisCSEvent;
    ComponentEventSubscribe<AisCreateTargetEvent> aisCTEvent;
    ComponentEventSubscribe<AisDeleteStationEvent> aisDSEvent;
    ComponentEventSubscribe<AisDeleteTargetEvent> aisDTEvent;
    ComponentEventSubscribe<AisUpdateStationEvent> aisUSEvent;
    ComponentEventSubscribe<AisUpdateTargetEvent> aisUTEvent;

    public AisEventsController() {
        cm = ComponentManager.componentManager;
        aisCSEvent = cm.getComponentEventSubscribe(AisCreateStationEvent.class);
        aisCTEvent = cm.getComponentEventSubscribe(AisCreateTargetEvent.class);
        aisDSEvent = cm.getComponentEventSubscribe(AisDeleteStationEvent.class);
        aisDTEvent = cm.getComponentEventSubscribe(AisDeleteTargetEvent.class);
        aisUSEvent = cm.getComponentEventSubscribe(AisUpdateStationEvent.class);
        aisUTEvent = cm.getComponentEventSubscribe(AisUpdateTargetEvent.class);
    }

    public void subscribe() {
        aisCTEvent.subscribe((AisCreateTargetEvent) (Ship ship) -> {
            createTarget(ship);
        });
        aisUTEvent.subscribe((AisUpdateTargetEvent) (Ship ship) -> {
            updateTarget(ship);
        });
        aisDTEvent.subscribe((AisDeleteTargetEvent) (Ship ship) -> {
            deleteTarget(ship);
        });
        aisCSEvent.subscribe((AisCreateStationEvent) (BaseStation baseStation) -> {
            createBaseStation(baseStation);
        });
        aisUSEvent.subscribe((AisUpdateStationEvent) (BaseStation baseStation) -> {
            updateBaseStation(baseStation);
        });
        aisDSEvent.subscribe((AisDeleteStationEvent) (BaseStation baseStation) -> {
            deleteBaseStation(baseStation);
        });
    }

    public void unsubscribe() {
        aisCTEvent.unsubscribe((AisCreateTargetEvent) (Ship ship) -> {

        });
        aisUTEvent.unsubscribe((AisUpdateTargetEvent) (Ship ship) -> {

        });
        aisDTEvent.unsubscribe((AisDeleteTargetEvent) (Ship ship) -> {

        });
        aisCSEvent.unsubscribe((AisCreateStationEvent) (BaseStation baseStation) -> {

        });
        aisUSEvent.unsubscribe((AisUpdateStationEvent) (BaseStation baseStation) -> {

        });
        aisDSEvent.unsubscribe((AisDeleteStationEvent) (BaseStation baseStation) -> {

        });
    }

    public void createTarget(Ship ship) {

    }

    public void updateTarget(Ship ship) {

    }

    public void deleteTarget(Ship ship) {

    }

    public void createBaseStation(BaseStation baseStation) {

    }

    public void updateBaseStation(BaseStation baseStation) {

    }

    public void deleteBaseStation(BaseStation baseStation) {

    }
}
