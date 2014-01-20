/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widget;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GSAEvent;
import bzh.terrevirtuelle.navisu.nmea.model.GGA;
import bzh.terrevirtuelle.navisu.nmea.model.GSA;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class Text {

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<GGAEvent> ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
    ComponentEventSubscribe<GSAEvent> gsaES = cm.getComponentEventSubscribe(GSAEvent.class);

    public Text() {

        ggaES.subscribe(new GGAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                System.out.println("gga " + ((GGA) data).getLatitude());
            }
        });
        gsaES.subscribe(new GSAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                System.out.println("gsa " + ((GSA) data).getNumPRNOfSatelliteUsed());
            }
        });
    }
}
