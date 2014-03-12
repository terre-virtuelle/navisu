/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import bzh.terrevirtuelle.navisu.core.util.IDGenerator;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.ais.controller.AisLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.locators.ais.view.AisLayer;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.view.CShipProcessor;
import bzh.terrevirtuelle.navisu.locators.view.ShipProcessor;
import bzh.terrevirtuelle.navisu.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import gov.nasa.worldwind.layers.Layer;
import java.util.HashMap;
import java.util.Map;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class AisLocator {

    protected GeoLayer<Layer> aisLayer;
    protected AisLocatorControllerWithDPAgent aisLocatorController;
    protected Map<Integer, ShipProcessor> tShipProcessors;
    protected GeoViewServices geoViewServices;
    protected DpAgentServices dpAgentServices;

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);
    boolean first = true;

    public AisLocator(GeoViewServices geoViewServices, DpAgentServices dpAgentServices) {

        tShipProcessors = new HashMap<>();
        this.geoViewServices = geoViewServices;
        this.dpAgentServices = dpAgentServices;

        // creation de la layer
        this.aisLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.aisLayer);

        subscribe();
    }

    private void subscribe() {
        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS1 ais = (AIS1) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {

                    TShip ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    dpAgentServices.create(ship);
                    
                    aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    ShipProcessor shipProcessor = new CShipProcessor(AisLocator.this.aisLayer, ship);
                    geoViewServices.registerProcessor(shipProcessor);

                    tShipProcessors.put(mmsi, shipProcessor);
                }
            }
        });
        /*
         ais2ES.subscribe(new AIS2Event() {

         @Override
         public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
         AIS2 ais = (AIS2) data;
         System.out.println("type : 2");
         int mmsi = ais.getMMSI();
         if (ships.containsKey(mmsi)) {
         ship = ships.get(mmsi);
         shipUpdate(ais);
         } else {
         shipBuild(ais, 2);
         ships.put(mmsi, ship);
         }
         }
         });
         ais3ES.subscribe(new AIS3Event() {

         @Override
         public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
         AIS3 ais = (AIS3) data;
         System.out.println("type : 3");
         int mmsi = ais.getMMSI();
         if (ships.containsKey(mmsi)) {
         ship = ships.get(mmsi);
         shipUpdate(ais);
         } else {
         shipBuild(ais, 3);
         ships.put(mmsi, ship);
         }
         }
         });
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
         ais5ES.subscribe(new AIS5Event() {

         @Override
         public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
         AIS5 ais = (AIS5) data;
         int mmsi = ais.getMMSI();
         if (ships.containsKey(mmsi)) {
         ship = ships.get(mmsi);
         //   shipUpdate5(ais);
         }
         }
         });

         }
         */
    }
}
