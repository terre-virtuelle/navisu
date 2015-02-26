  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.controller;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS01Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS02Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS03Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS05Event;
import bzh.terrevirtuelle.navisu.locators.model.TShip;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05;

import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class AisLocatorControllerWithDPAgent {

    protected static final Logger LOGGER = Logger.getLogger(AisLocatorControllerWithDPAgent.class.getName());
    protected DpAgentServices dpAgentServices;

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS01Event> ais1ES = cm.getComponentEventSubscribe(AIS01Event.class);
    ComponentEventSubscribe<AIS02Event> ais2ES = cm.getComponentEventSubscribe(AIS02Event.class);
    ComponentEventSubscribe<AIS03Event> ais3ES = cm.getComponentEventSubscribe(AIS03Event.class);
    ComponentEventSubscribe<AIS04Event> ais4ES = cm.getComponentEventSubscribe(AIS04Event.class);
    ComponentEventSubscribe<AIS05Event> ais5ES = cm.getComponentEventSubscribe(AIS05Event.class);

    protected TShip ship;

    public AisLocatorControllerWithDPAgent(final DpAgentServices dpAgentServices, final TShip ship) {
        this.ship = ship;
        this.dpAgentServices = dpAgentServices;

        subscribe();
    }

    private void subscribe() {

        ais1ES.subscribe(new AIS01Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS01 data = (AIS01) d;
                double lat = data.getLatitude();
                double lon = data.getLongitude();

                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == ship.getMMSI()) {
                    ship.setLatitude(lat);
                    ship.setLongitude(lon);
                    ship.setCog(data.getCog());
                    ship.setHeading(data.getHeading());
                    ship.setRot(data.getRot());
                    dpAgentServices.update(ship);
                }
            }
        });

        ais2ES.subscribe(new AIS02Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                AIS02 data = (AIS02) d;
                double lat = data.getLatitude();
                double lon = data.getLongitude();
                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == ship.getMMSI()) {
                    ship.setLatitude(lat);
                    ship.setLongitude(lon);
                    ship.setCog(data.getCog());
                    ship.setHeading(data.getHeading());
                }
            }
        });

        ais3ES.subscribe(new AIS03Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS03 data = (AIS03) d;
                double lat = data.getLatitude();
                double lon = data.getLongitude();
                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == ship.getMMSI()) {
                    ship.setLatitude(lat);
                    ship.setLongitude(lon);
                    ship.setCog(data.getCog());
                    ship.setHeading(data.getHeading());
                    dpAgentServices.update(ship);
                }
            }
        });
        
        ais5ES.subscribe(new AIS05Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                AIS05 data = (AIS05) d;

                if (data.getMMSI() == ship.getMMSI()) {
                    ship.setName(data.getShipName());
                    ship.setLength(data.getLength());
                    ship.setWidth(data.getWidth());
                    ship.setDraught(data.getDraught());
                    ship.setETA(data.getETA());
                    ship.setDestination(data.getDestination());
                    ship.setShipType(data.getShipType());
                    System.out.println("AisLocatorControllerWithDPAgent " + data.getShipType());
                    dpAgentServices.update(ship);
               }
            }
        });

    }

    private void update() {

    }
}
