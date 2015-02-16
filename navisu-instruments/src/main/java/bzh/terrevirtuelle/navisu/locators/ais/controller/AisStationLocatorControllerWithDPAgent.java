  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.controller;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.locators.model.TTransceiver;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.locators.model.TStation;
import gov.nasa.worldwind.avlist.AVKey;

import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class AisStationLocatorControllerWithDPAgent {

    protected static final Logger LOGGER = Logger.getLogger(AisStationLocatorControllerWithDPAgent.class.getName());
    protected DpAgentServices dpAgentServices;
    boolean update = false;
    ComponentManager cm = ComponentManager.componentManager;

    ComponentEventSubscribe<AIS04Event> ais4ES = cm.getComponentEventSubscribe(AIS04Event.class);

    protected TStation station;

    public AisStationLocatorControllerWithDPAgent(final DpAgentServices dpAgentServices, final TStation station) {
        this.station = station;
        this.dpAgentServices = dpAgentServices;

        subscribe();
    }

    private void subscribe() {

        ais4ES.subscribe(new AIS04Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS04 data = (AIS04) d;
                String mmsi = "Unknow";

                double lat = data.getLatitude();
                double lon = data.getLongitude();
                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == station.getMmsi()) {
                    station.setLatitude(lat);
                    station.setLongitude(lon);
                    if (update == false) {
                      //  station.getGStation().getAttributes().setImageAddress("bzh/terrevirtuelle/navisu/locators/view/emetteur_1.png");
                        update = true;

                    } else {
                      //  station.getGStation().getAttributes().setImageAddress("bzh/terrevirtuelle/navisu/locators/view/emetteur_1.png");
                        update = false;
                    }
                    if (station.getMmsi() != 0) {
                        mmsi = Integer.toString(station.getMmsi());
                    }
                    station.getGStation().getShape().setValue(AVKey.DISPLAY_NAME, mmsi);
                    // mise à jour via le DPAgent
                    dpAgentServices.update(station);
                }
            }
        });

    }

    private void update() {

    }
}
