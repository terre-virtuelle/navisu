/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.common.controller;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VTGEvent;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 19 juin 2015
 * @author Serge Morvan
 */
public abstract class GpsEventsController {

    ComponentManager cm;
    ComponentEventSubscribe<GGAEvent> ggaES;
    ComponentEventSubscribe<RMCEvent> rmcES;
    ComponentEventSubscribe<VTGEvent> vtgES;

    public GpsEventsController() {
        cm = ComponentManager.componentManager;

        ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
        rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
        vtgES = cm.getComponentEventSubscribe(VTGEvent.class);
    }

    public final void subscribe() {
        ggaES.subscribe(new GGAEvent() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                GGA data = (GGA) d;
                notifyNmeaMessage(data);
            }
        });
        vtgES.subscribe(new VTGEvent() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                VTG data = (VTG) d;
                notifyNmeaMessage(data);
            }
        });
        rmcES.subscribe(new RMCEvent() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                RMC data = (RMC) d;
                notifyNmeaMessage(data);
            }
        });
    }

    public final void unsubscribe() {
        ggaES.unsubscribe(new GGAEvent() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
            }
        });
        vtgES.unsubscribe(new VTGEvent() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
            }
        });
        rmcES.unsubscribe(new RMCEvent() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
            }
        });
    }

    protected abstract void notifyNmeaMessage(GGA data);

    protected abstract void notifyNmeaMessage(VTG data);

    protected abstract void notifyNmeaMessage(RMC data);

    public void updateTarget(Ship ship) {

    }
}
