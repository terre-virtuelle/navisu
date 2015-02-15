  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.controller;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS05;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;

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
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);

    protected TShip ship;

    public AisLocatorControllerWithDPAgent(final DpAgentServices dpAgentServices, final TShip ship) {
        this.ship = ship;
        this.dpAgentServices = dpAgentServices;

        subscribe();
    }

    private void subscribe() {

        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS01 data = (AIS01) d;
                double lat = data.getLatitude();
                double lon = data.getLongitude();
                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == ship.getMmsi()) {
                    ship.setLatitude(lat);
                    ship.setLongitude(lon);
                    ship.setCog(data.getCog());
                    ship.setHeading(data.getHeading());
                    ship.setRot(data.getRot());
                    // mise à jour via le DPAgent
                    dpAgentServices.update(ship);
                }
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS02 data = (AIS02) d;
                double lat = data.getLatitude();
                double lon = data.getLongitude();
                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == ship.getMmsi()) {
                    ship.setLatitude(lat);
                    ship.setLongitude(lon);
                    ship.setCog(data.getCog());
                    ship.setHeading(data.getHeading());
                    // mise à jour via le DPAgent
                    dpAgentServices.update(ship);
                }
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS03 data = (AIS03) d;
                double lat = data.getLatitude();
                double lon = data.getLongitude();
                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == ship.getMmsi()) {
                    ship.setLatitude(lat);
                    ship.setLongitude(lon);
                    ship.setCog(data.getCog());
                    ship.setHeading(data.getHeading());
                    // mise à jour via le DPAgent
                    dpAgentServices.update(ship);
                }
            }
        });
        ais5ES.subscribe(new AIS5Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS05 data = (AIS05) d;
                if (data.getMMSI() == ship.getMmsi()) {
                    ship.setName(data.getShipName());
                    ship.setLength(data.getLength());
                    ship.setWidth(data.getWidth());
                    ship.setDraught(data.getDraught());
                    ship.setETA(data.getETA());
                    ship.setDestination(data.getDestination());
                    ship.setType(data.getShipType());
                    // mise à jour via le DPAgent
                    dpAgentServices.update(ship);
                }
            }
        });

    }

    private void update() {

    }
}
