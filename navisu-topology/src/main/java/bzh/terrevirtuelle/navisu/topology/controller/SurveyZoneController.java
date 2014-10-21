/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.topology.controller;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS2;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.topology.model.SurveyZone;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge Morvan
 * @date 7 oct. 2014 NaVisu project
 */
public class SurveyZoneController {

    protected static final Logger LOGGER = Logger.getLogger(SurveyZoneController.class.getName());

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);

    private final List<SurveyZone> zones;

    public SurveyZoneController() {
        zones = new ArrayList<>();
        subscribe();
    }

    private void subscribe() {

        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS1 data = (AIS1) d;
                contains(data.getLatitude(), data.getLongitude());
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS2 data = (AIS2) d;
                contains(data.getLatitude(), data.getLongitude());
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS3 data = (AIS3) d;
                contains(data.getLatitude(), data.getLongitude());
            }
        });
    }

    public void add(SurveyZone surveyZone) {
        zones.add(surveyZone);
    }

    private void contains(double lat, double lon) {
        zones.stream().filter((s) -> (s.contains(lat, lon))).forEach((_item) -> {
            System.out.println(_item.getAcronym() + " " + _item.getObjname());
        });
    }
}
