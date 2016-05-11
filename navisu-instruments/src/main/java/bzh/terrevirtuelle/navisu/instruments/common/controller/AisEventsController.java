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
public abstract class AisEventsController {

    ComponentManager cm;

    ComponentEventSubscribe<AisCreateTargetEvent> aisCTEvent;
    ComponentEventSubscribe<AisUpdateTargetEvent> aisUTEvent;
    ComponentEventSubscribe<AisDeleteTargetEvent> aisDTEvent;

    ComponentEventSubscribe<AisCreateStationEvent> aisCSEvent;
    ComponentEventSubscribe<AisUpdateStationEvent> aisUSEvent;
    ComponentEventSubscribe<AisDeleteStationEvent> aisDSEvent;

    public AisEventsController() {
        cm = ComponentManager.componentManager;
        aisCTEvent = cm.getComponentEventSubscribe(AisCreateTargetEvent.class);
        aisUTEvent = cm.getComponentEventSubscribe(AisUpdateTargetEvent.class);
        aisDTEvent = cm.getComponentEventSubscribe(AisDeleteTargetEvent.class);

        aisCSEvent = cm.getComponentEventSubscribe(AisCreateStationEvent.class);
        aisUSEvent = cm.getComponentEventSubscribe(AisUpdateStationEvent.class);
        aisDSEvent = cm.getComponentEventSubscribe(AisDeleteStationEvent.class);
    }

    public void subscribe() {
        aisCTEvent.subscribe((AisCreateTargetEvent) this::createTarget);
        aisUTEvent.subscribe((AisUpdateTargetEvent) this::updateTarget);
        aisDTEvent.subscribe((AisDeleteTargetEvent) this::deleteTarget);

        aisCSEvent.subscribe((AisCreateStationEvent) this::createBaseStation);
        aisUSEvent.subscribe((AisUpdateStationEvent) this::updateBaseStation);
        aisDSEvent.subscribe((AisDeleteStationEvent) this::deleteBaseStation);
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

    public abstract void createTarget(Ship ship);

    public abstract void updateTarget(Ship ship);

    public void deleteTarget(Ship ship) {
    }

    public void createBaseStation(BaseStation baseStation) {
    }

    public void updateBaseStation(BaseStation baseStation) {
    }

    public void deleteBaseStation(BaseStation baseStation) {
    }
}
