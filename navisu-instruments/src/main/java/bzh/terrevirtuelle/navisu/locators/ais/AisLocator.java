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
import bzh.terrevirtuelle.navisu.locators.view.Shape;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.locators.ais.controller.AisLocatorController;
import bzh.terrevirtuelle.navisu.locators.model.TStation;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.Layer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class AisLocator {

    protected GeoLayer<Layer> aisLayer;
    protected GeoLayer<Layer> aisStationLayer;
  //  protected RenderableLayer baloonLayer;
    protected AisLocatorControllerWithDPAgent aisLocatorControllerWithDPAgent;
    protected AisStationLocatorControllerWithDPAgent aisStationLocatorControllerWithDPAgent;
    protected AisLocatorController aisLocatorController;
    protected Map<Integer, ShipProcessor> tShipProcessors;
    protected Map<Integer, Calendar> timestamps;
    protected Map<Integer, StationProcessor> tStationsProcessors;
    protected Map<Integer, String> midMap;
    protected GeoViewServices geoViewServices;
    protected DpAgentServices dpAgentServices;
    protected GuiAgentServices guiAgentServices;
    protected GeoWorldWindView geoView;
    protected final String MID_MAP = "data/ais/mmsi.txt";
    NumberFormat nf = new DecimalFormat("0.###");
    SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd-MM");
    TShip ship;
    TStation station;
    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);
    boolean first = true;
    boolean firstBt = true;
    // StackPane pane;
   // Position balloonPosition;
    //  AbstractBrowserBalloon balloon;
    //GlobeBalloon balloon;
    //BalloonAttributes attrs;

    WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public AisLocator(GeoViewServices geoViewServices,
            DpAgentServices dpAgentServices,
            GuiAgentServices guiAgentServices) {

        this.geoViewServices = geoViewServices;
        this.dpAgentServices = dpAgentServices;
        this.guiAgentServices = guiAgentServices;
        tShipProcessors = new HashMap<>();
        tStationsProcessors = new HashMap<>();
        timestamps = new HashMap<>();
        this.aisLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());

        aisLayer.setName("AIS Layer");
        geoViewServices.getLayerManager().insertGeoLayer(this.aisLayer);

        wwd.addPositionListener((PositionEvent event) -> {
            float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
            if (altitude >= 30000) {
                clip();
            } else {
                unClip();
            }
        });

        this.aisStationLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());
        aisStationLayer.setName("AIS_Station_Layer");
        geoViewServices.getLayerManager().insertGeoLayer(this.aisStationLayer);
    
        midMap = new HashMap<>();
        String[] midEntries;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(MID_MAP))) {
                String s;
                while ((s = br.readLine()) != null) {
                    if (!s.isEmpty()) {
                        midEntries = s.split(",");
                        midMap.put(new Integer(midEntries[0].trim()), midEntries[1].trim());
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AisLocator.class.getName()).log(Level.SEVERE, null, ex);
        }

        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                Class[] i = o.getClass().getInterfaces();
                if (i.length != 0) {
                    if (i[0].equals(Shape.class)) {
                        makeBrowserBalloon(((Shape) o).getShip());
                    }
                }
            }
        });   
        
       
        Platform.runLater(() -> {
            aisLocatorController = new AisLocatorController( KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisLocatorController);
            guiAgentServices.getRoot().getChildren().add(aisLocatorController); //Par defaut le radar n'est pas visible Ctrl-A
            aisLocatorController.scale(1.0);
            aisLocatorController.setVisible(false);
        });
        
        subscribe();
    }

    private void subscribe() {
        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS01 ais = (AIS01) data;
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

                        aisLocatorControllerWithDPAgent = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);

                        tShipProcessors.put(mmsi, shipProcessor);
                    }
                    timestamps.put(mmsi, Calendar.getInstance());
                } catch (Exception e) {
                    System.out.println("ais1ES.subscribe " + e);
                }
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS02 ais = (AIS02) data;
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
                    aisLocatorControllerWithDPAgent = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS03 ais = (AIS03) data;
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

                    aisLocatorControllerWithDPAgent = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS04 ais = (AIS04) data;
                int mmsi = ais.getMMSI();

                if (!tStationsProcessors.containsKey(mmsi)) {
                    StationProcessor stationProcessor = new StationProcessor(AisLocator.this.aisStationLayer);
                    geoViewServices.registerProcessor(stationProcessor);

                    station = new TStation(IDGenerator.newIntID(),
                            ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(), ais.getDate());

                    dpAgentServices.create(station);

                    aisStationLocatorControllerWithDPAgent = new AisStationLocatorControllerWithDPAgent(dpAgentServices, station);
                    /*
                     wwd.addPositionListener((PositionEvent event) -> {
                     System.out.println((int) wwd.getView().getCurrentEyePosition().getElevation());
                     });
                     */
                    tStationsProcessors.put(mmsi, stationProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
    }

    protected final void makeBrowserBalloon(TShip ship) {
        Platform.runLater(() -> {
            aisLocatorController.updateAisPanel(ship, timestamps, midMap);
        });
    }

    private void clip() {
        aisStationLayer.getDisplayLayer().setEnabled(false);
    }

    private void unClip() {
        aisStationLayer.getDisplayLayer().setEnabled(true);
    }

}
