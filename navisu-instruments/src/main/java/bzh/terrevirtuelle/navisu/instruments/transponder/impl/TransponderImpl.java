
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.transponder.impl;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VTGEvent;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.transponder.Transponder;
import bzh.terrevirtuelle.navisu.instruments.transponder.TransponderServices;
import bzh.terrevirtuelle.navisu.instruments.transponder.impl.events.TransponderUpdateTargetEvent;

import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.ProducedEvent;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author serge
 */
public class TransponderImpl
        implements Transponder, TransponderServices, ComponentState {

    @ProducedEvent
    protected TransponderUpdateTargetEvent transponderUpdateTargetEvent;

    protected boolean on;
    protected Ship ship;

    ComponentManager cm;
    ComponentEventSubscribe<GGAEvent> ggaES;
    ComponentEventSubscribe<RMCEvent> rmcES;
    ComponentEventSubscribe<VTGEvent> vtgES;

    public TransponderImpl() {
    }

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
            subscribe();
        }
        on = true;
    }

    @Override
    public void off() {
        if (on == true) {
            unsubscribe();
        }
        on = false;
    }

    public void subscribe() {
        if (ship == null) {
            ship = new Ship();
            ship.setGpsTarget(on);
        }
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

    public void unsubscribe() {
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

    public void notifyNmeaMessage(GGA data) {
        //update ship
        ship.setLatitude(data.getLatitude());
        ship.setLongitude(data.getLongitude());
        transponderUpdateTargetEvent(ship);
    }

    public void notifyNmeaMessage(VTG data) {
        //update ship
        ship.setCog(data.getCog());
        ship.setSog(data.getSog());
        transponderUpdateTargetEvent(ship);
    }

    public void notifyNmeaMessage(RMC data) {
        //update ship
        ship.setCog(data.getCog());
        ship.setSog(data.getSog());
        ship.setLatitude(data.getLatitude());
        ship.setLongitude(data.getLongitude());
        transponderUpdateTargetEvent(ship);
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public void transponderUpdateTargetEvent(Ship ship) {
        transponderUpdateTargetEvent.notifyTransponderMessageChanged(ship);
    }

    @Override
    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
