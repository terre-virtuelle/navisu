 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.gps.controller;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VTGEvent;
import bzh.terrevirtuelle.navisu.core.util.IDGenerator;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.nmea.model.GGA;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.nmea.model.RMC;
import bzh.terrevirtuelle.navisu.nmea.model.VTG;
import gov.nasa.worldwind.WorldWindow;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public class GpsLocatorControllerWithDPAgent {

    protected static final Logger LOGGER = Logger.getLogger(GpsLocatorControllerWithDPAgent.class.getName());
    private final Properties properties;
    protected DpAgentServices dpAgentServices;

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<GGAEvent> ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
    ComponentEventSubscribe<RMCEvent> rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
    ComponentEventSubscribe<VTGEvent> vtgES = cm.getComponentEventSubscribe(VTGEvent.class);
    WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected TShip ship;

    public GpsLocatorControllerWithDPAgent(final DpAgentServices dpAgentServices) {
        this.dpAgentServices = dpAgentServices;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/domain.properties"));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        // creation du TObject (l'objet metier)
        ship = new TShip(IDGenerator.newIntID(),
                new Integer(properties.getProperty("mmsi")),
                properties.getProperty("name"),
                properties.getProperty("country"),
                new Float(properties.getProperty("width")),
                new Float(properties.getProperty("length")),
                new Float(properties.getProperty("draught")),
                new Integer(properties.getProperty("shipType")),
                new Integer(properties.getProperty("navigationalStatus")),
                new Integer(properties.getProperty("electronicPositionDevice")),
                properties.getProperty("callSign"));
        ship.setShapeId(0);
        // insertion dans le DPAgent
        dpAgentServices.create(ship);

        

        subscribe();
    }

    private void subscribe() {
        // souscription aux événements
        ggaES.subscribe(new GGAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                GGA data = (GGA) d;
                ship.setLatitude(data.getLatitude());
                ship.setLongitude(data.getLongitude());
              
                // mise à jour via le DPAgent
                dpAgentServices.update(ship);
            }
        });
        vtgES.subscribe(new VTGEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                VTG data = (VTG) d;
                ship.setCog(data.getCog());
                ship.setSog(data.getSog());
                if (ship.getSog() > 0.1) {
                    ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                dpAgentServices.update(ship);
            }
        });
        rmcES.subscribe(new RMCEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                RMC data = (RMC) d;
                ship.setLatitude(data.getLatitude());
                ship.setLongitude(data.getLongitude());
                ship.setCog(data.getCog());
                ship.setSog(data.getSog());
                if (ship.getSog() > 0.1) {
                    ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                dpAgentServices.update(ship);
            }
        });
    }
}
