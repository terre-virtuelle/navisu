/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.impl.controller;


import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS01Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS02Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS03Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS05Event;
import bzh.terrevirtuelle.navisu.core.util.IDGenerator;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.view.Shape;
import bzh.terrevirtuelle.navisu.instruments.locators.ais.controller.AisLocatorController;
import bzh.terrevirtuelle.navisu.instruments.locators.ais.controller.AisStationLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.instruments.locators.controller.ShipProcessor;
import bzh.terrevirtuelle.navisu.instruments.locators.controller.StationProcessor;
import bzh.terrevirtuelle.navisu.instruments.locators.model.TShip;
import bzh.terrevirtuelle.navisu.instruments.locators.model.TStation;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
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
public class AisController {

    protected GeoLayer<Layer> aisLayer;
    protected GeoLayer<Layer> aisStationLayer;
    protected AisLocatorControllerWithDPAgent aisLocatorControllerWithDPAgent;
    protected AisStationLocatorControllerWithDPAgent aisStationLocatorControllerWithDPAgent;
    protected AisPanelController aisPanelController;
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
    ComponentEventSubscribe<AIS01Event> ais1ES = cm.getComponentEventSubscribe(AIS01Event.class);
    ComponentEventSubscribe<AIS02Event> ais2ES = cm.getComponentEventSubscribe(AIS02Event.class);
    ComponentEventSubscribe<AIS03Event> ais3ES = cm.getComponentEventSubscribe(AIS03Event.class);
    ComponentEventSubscribe<AIS04Event> ais4ES = cm.getComponentEventSubscribe(AIS04Event.class);
    ComponentEventSubscribe<AIS05Event> ais5ES = cm.getComponentEventSubscribe(AIS05Event.class);
    boolean first = true;
    boolean firstBt = true;

    WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public AisController(GeoViewServices geoViewServices,
            DpAgentServices dpAgentServices,
            GuiAgentServices guiAgentServices) {

        this.geoViewServices = geoViewServices;
        this.dpAgentServices = dpAgentServices;
        this.guiAgentServices = guiAgentServices;
        tShipProcessors = new HashMap<>();
        tStationsProcessors = new HashMap<>();
        timestamps = new HashMap<>();
        this.aisLayer = GeoLayer.factory.newWorldWindGeoLayer(new RenderableLayer());

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

        this.aisStationLayer = GeoLayer.factory.newWorldWindGeoLayer(new RenderableLayer());
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
            Logger.getLogger(AisController.class.getName()).log(Level.SEVERE, null, ex);
        }

        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                Class[] i = o.getClass().getInterfaces();
                if (i.length != 0) {
                    if (i[0].equals(Shape.class)) {
                        updateAisPanel(((Shape) o).getShip());
                    }
                }
            }
        });

        Platform.runLater(() -> {
            aisPanelController = new AisPanelController(KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisPanelController);
            guiAgentServices.getRoot().getChildren().add(aisPanelController); //Par defaut le radar n'est pas visible Ctrl-A
            aisPanelController.scale(1.0);
            aisPanelController.setVisible(false);
        });

        subscribe();
    }

    private void subscribe() {

        ais1ES.subscribe(new AIS01Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS01 ais = (AIS01) data;
                    int mmsi = ais.getMMSI();
                    if (!tShipProcessors.containsKey(mmsi)) {
                        ShipProcessor shipProcessor = new ShipProcessor(AisController.this.aisLayer);
                        geoViewServices.registerProcessor(shipProcessor);
                        ship = new TShip(IDGenerator.newIntID(),
                                ais.getMMSI(),
                                ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                                ais.getLatitude(), ais.getLongitude(),
                                ais.getNavigationalStatus());
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

        ais2ES.subscribe(new AIS02Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS02 ais = (AIS02) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisController.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);
                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getNavigationalStatus());
                    dpAgentServices.create(ship);
                    aisLocatorControllerWithDPAgent = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS03Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                AIS03 ais = (AIS03) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisController.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);
                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(), ais.getNavigationalStatus());
                    dpAgentServices.create(ship);
                    aisLocatorControllerWithDPAgent = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
        ais4ES.subscribe(new AIS04Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                AIS04 ais = (AIS04) data;
                int mmsi = ais.getMMSI();

                if (!tStationsProcessors.containsKey(mmsi)) {
                    StationProcessor stationProcessor = new StationProcessor(AisController.this.aisStationLayer);
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

        ais5ES.subscribe(new AIS05Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                
                AIS05 ais = (AIS05) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisController.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);
                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(),
                            ais.getShipName(), ais.getShipType());
                    dpAgentServices.create(ship);
                    aisLocatorControllerWithDPAgent = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

    }

    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            aisPanelController.updateAisPanel(ship, timestamps, midMap);
        });
    }

    private void clip() {
        aisStationLayer.getDisplayLayer().setEnabled(false);
    }

    private void unClip() {
        aisStationLayer.getDisplayLayer().setEnabled(true);
    }

}
