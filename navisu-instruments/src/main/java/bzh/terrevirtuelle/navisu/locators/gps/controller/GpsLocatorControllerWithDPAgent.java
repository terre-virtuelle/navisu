 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.gps.controller;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GSVEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VTGEvent;
import bzh.terrevirtuelle.navisu.core.util.IDGenerator;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.locators.model.OShip;
import bzh.terrevirtuelle.navisu.nmea.model.GGA;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.nmea.model.VTG;
import gov.nasa.worldwind.layers.Layer;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public class GpsLocatorControllerWithDPAgent {

    protected static final Logger LOGGER = Logger.getLogger(GpsLocatorControllerWithDPAgent.class.getName());

    protected LayerManager<Layer> layerManager;

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<GGAEvent> ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
    ComponentEventSubscribe<GSVEvent> gsvES = cm.getComponentEventSubscribe(GSVEvent.class);
    ComponentEventSubscribe<RMCEvent> rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
    ComponentEventSubscribe<VTGEvent> vtgES = cm.getComponentEventSubscribe(VTGEvent.class);

    protected OShip ship;

    public GpsLocatorControllerWithDPAgent(final DpAgentServices dpAgentServices) {

        // creation du TObject (l'objet metier)
        ship = new OShip(IDGenerator.newIntID());
        // insertion dans le DPAgent
        dpAgentServices.create(ship);

        ggaES.subscribe(new GGAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                GGA gga = (GGA) data;
                ship.setLatitude(gga.getLatitude());
                ship.setLongitude(gga.getLongitude());

                // mise à jour via le DPAgent
                dpAgentServices.update(ship);
            }
        });
        vtgES.subscribe(new VTGEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                VTG vtg = (VTG) data;
                ship.setCog(vtg.getCog());
                // mise à jour via la DPAgent
                dpAgentServices.update(ship);
            }
        });
    }

}
