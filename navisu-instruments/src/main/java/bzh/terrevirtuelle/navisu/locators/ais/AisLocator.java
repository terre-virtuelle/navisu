/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import bzh.terrevirtuelle.navisu.core.util.IDGenerator;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.locators.ais.controller.AisLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.locators.ais.controller.AisStationLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.locators.ais.view.AisLayer;
import bzh.terrevirtuelle.navisu.locators.controller.StationProcessor;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.controller.ShipProcessor;
import bzh.terrevirtuelle.navisu.locators.model.TStation;
import bzh.terrevirtuelle.navisu.locators.view.Shape;

import bzh.terrevirtuelle.navisu.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.nmea.model.AIS2;
import bzh.terrevirtuelle.navisu.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.nmea.model.AIS4;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.IconLayer;
import gov.nasa.worldwind.layers.Layer;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.StackPane;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class AisLocator {

    protected GeoLayer<Layer> aisLayer;
    protected GeoLayer<Layer> aisStationLayer;
    protected IconLayer baseStationLayer;
    protected AisLocatorControllerWithDPAgent aisLocatorController;
    protected AisStationLocatorControllerWithDPAgent aisStationLocatorControllerWithDPAgent;
    protected Map<Integer, ShipProcessor> tShipProcessors;
    protected Map<Integer, StationProcessor> tStationsProcessors;
    protected GeoViewServices geoViewServices;
    protected DpAgentServices dpAgentServices;
    protected GuiAgentServices guiAgentServices;
    protected GeoWorldWindView geoView;
    TShip ship;
    TStation station;
    //   Station baseStation;
    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);
    boolean first = true;
    boolean firstBt = true;
    StackPane pane;

    WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public AisLocator(GeoViewServices geoViewServices,
            DpAgentServices dpAgentServices,
            GuiAgentServices guiAgentServices) {

        tShipProcessors = new HashMap<>();
        tStationsProcessors = new HashMap<>();
        this.geoViewServices = geoViewServices;
        this.dpAgentServices = dpAgentServices;
        this.guiAgentServices = guiAgentServices;
  
        pane = guiAgentServices.getRoot();
        
        // creation de la layer
        this.aisLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.aisLayer);

        this.aisStationLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.aisStationLayer);

        wwd.addSelectListener((SelectEvent event) -> {
            if (event.isLeftClick()
                    && event.getTopObject().getClass().getInterfaces()[0].equals(Shape.class)) {
               // System.out.println(((Shape) event.getTopObject()).getShip().getCog());
            }
        });

        subscribe();

    }

    private void subscribe() {
        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS1 ais = (AIS1) data;
                    int mmsi = ais.getMMSI();
                    if (!tShipProcessors.containsKey(mmsi)) {
                        ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                        geoViewServices.registerProcessor(shipProcessor);

                        ship = new TShip(IDGenerator.newIntID(),
                                ais.getMMSI(), ais.getImo(), ais.getShipname(),
                                ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                                ais.getLatitude(), ais.getLongitude(),
                                ais.getWidth(), ais.getLength(), ais.getDraught(),
                                ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                                ais.getETA(), ais.getDestination(), "");
                        dpAgentServices.create(ship);

                        aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);

                        tShipProcessors.put(mmsi, shipProcessor);
                    }
                } catch (Exception e) {
                    System.out.println("e " + e);
                }
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS2 ais = (AIS2) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);

                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    dpAgentServices.create(ship);
                    aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS3 ais = (AIS3) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);

                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    dpAgentServices.create(ship);

                    aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
            }
        });

        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS4 ais = (AIS4) data;
                int mmsi = ais.getMMSI();

                if (!tStationsProcessors.containsKey(mmsi)) {
                    StationProcessor stationProcessor = new StationProcessor(AisLocator.this.aisStationLayer);
                    geoViewServices.registerProcessor(stationProcessor);

                    station = new TStation(IDGenerator.newIntID(),
                            ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(), ais.getDate());

                    dpAgentServices.create(station);

                    aisStationLocatorControllerWithDPAgent = new AisStationLocatorControllerWithDPAgent(dpAgentServices, station);

                    tStationsProcessors.put(mmsi, stationProcessor);

                }

            }
        });
    }
}
