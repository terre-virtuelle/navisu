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
import bzh.terrevirtuelle.navisu.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.nmea.model.AIS135;
import bzh.terrevirtuelle.navisu.nmea.model.AIS2;
import bzh.terrevirtuelle.navisu.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.nmea.model.AIS4;
import bzh.terrevirtuelle.navisu.nmea.model.AIS5;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.ship.Ship;

import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import static jdk.nashorn.internal.objects.NativeJava.type;
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
                AIS1 data = (AIS1) d;
                System.out.println(data);
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

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS2 data = (AIS2) d;
                 System.out.println(data);
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
                AIS3 data = (AIS3) d;
                 System.out.println(data);
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
        /*
         ais4ES.subscribe(new AIS4Event() {

         @Override
         public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
         AIS4 ais = (AIS4) data;
         System.out.println("type : 4");
         int mmsi = ais.getMMSI();
         //   if (ships.containsKey(mmsi)) {
         //       tShip = ships.get(mmsi);
         //shipUpdate(ais);
         //   } else {
         // shipBuild4(ais); // faire un buildDefault pour les stations fixes
         ships.put(mmsi, ship);
         //  }
         }
         });
         */
        ais5ES.subscribe(new AIS5Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS5 data = (AIS5) d;
                 System.out.println(data);
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

    }

    private void update() {

    }
}
