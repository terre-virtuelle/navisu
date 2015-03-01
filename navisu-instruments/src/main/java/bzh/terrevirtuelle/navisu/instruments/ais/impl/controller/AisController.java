/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.impl.controller;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS01Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS02Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS03Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS05Event;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import bzh.terrevirtuelle.navisu.instruments.ais.view.GShip;
import bzh.terrevirtuelle.navisu.instruments.ais.view.Shape;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
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

    protected RenderableLayer aisLayer;
    protected RenderableLayer aisStationLayer;
    protected static final String GROUP = "Devices";

    protected AisPanelController aisPanelController;
    protected Ship ship;
    protected BaseStation station;
    protected Map<Integer, Ship> ships;
    protected Map<Integer, GShip> gShips;
    protected Map<Integer, BaseStation> stations;
    protected Map<Integer, Calendar> timestamps;
    protected Map<Integer, String> midMap;
    protected GeoViewServices geoViewServices;//seront injectes
    protected DpAgentServices dpAgentServices;
    protected GuiAgentServices guiAgentServices;
    protected LayerTreeServices layerTreeServices;
    // protected GeoWorldWindView geoView;
    protected final String MID_MAP = "data/ais/mmsi.txt";
    NumberFormat nf = new DecimalFormat("0.###");
    SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd-MM");

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
            LayerTreeServices layerTreeServices,
            DpAgentServices dpAgentServices,
            GuiAgentServices guiAgentServices) {

        this.geoViewServices = geoViewServices;
        this.dpAgentServices = dpAgentServices;
        this.guiAgentServices = guiAgentServices;
        this.layerTreeServices = layerTreeServices;

        ships = new HashMap<>();
        gShips = new HashMap<>();
        stations = new HashMap<>();
        midMap = new HashMap<>();
        timestamps = new HashMap<>();

        layerTreeServices.createGroup(GROUP);
        geoViewServices.getLayerManager().createGroup(GROUP);

        addLayers();
        loadMaritimeIdentificationDigits();
        addListeners();
        addPanelController();
        subscribe();
    }

    private void addLayers() {
        this.aisLayer = new RenderableLayer();
        aisLayer.setName("AIS");
        geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));
        layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));//TODO
    }

    private void loadMaritimeIdentificationDigits() {
        String[] midEntries;
        try (BufferedReader br = new BufferedReader(new FileReader(MID_MAP))) {
            String s;
            while ((s = br.readLine()) != null) {
                if (!s.isEmpty()) {
                    midEntries = s.split(",");
                    midMap.put(new Integer(midEntries[0].trim()), midEntries[1].trim());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addListeners() {
        /*
         wwd.addPositionListener((PositionEvent event) -> {
         float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
         if (altitude >= 30000) {
         clip();
         } else {
         unClip();
         }
         });
         */
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
    }

    private void addPanelController() {
        Platform.runLater(() -> {
            aisPanelController = new AisPanelController(KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisPanelController);
            guiAgentServices.getRoot().getChildren().add(aisPanelController); //Par defaut le radar n'est pas visible Ctrl-A
            aisPanelController.scale(1.0);
            aisPanelController.setVisible(false);
        });
    }

    private void subscribe() {

        ais1ES.subscribe(new AIS01Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS01 ais = (AIS01) data;
                    int mmsi = ais.getMMSI();
                    if (!ships.containsKey(mmsi)) {
                        ship = ShipBuilder.create()
                                .mmsi(ais.getMMSI())
                                .heading(ais.getHeading()).cog(ais.getCog())
                                .sog(ais.getSog()).rot(ais.getRot())
                                .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                                .navigationalStatus(ais.getNavigationalStatus())
                                .build();
                        ships.put(mmsi, ship);
                        createTarget(ship);
                    } else {
                        ship = ships.get(mmsi);
                        ship.setHeading(ais.getHeading());
                        ship.setCog(ais.getCog());
                        ship.setSog(ais.getSog());
                        ship.setRot(ais.getRot());
                        ship.setLatitude(ais.getLatitude());
                        ship.setLongitude(ais.getLongitude());
                        ship.setNavigationalStatus(ais.getNavigationalStatus());
                        updateTarget(ship);
                    }
                    timestamps.put(mmsi, Calendar.getInstance());
                    // TODO controle de la cible morte
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
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .heading(ais.getHeading()).cog(ais.getCog())
                            .sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .navigationalStatus(ais.getNavigationalStatus())
                            .build();
                    ships.put(mmsi, ship);
                    createTarget(ship);
                } else {
                    ship = ships.get(mmsi);
                    ship.setHeading(ais.getHeading());
                    ship.setCog(ais.getCog());
                    ship.setSog(ais.getSog());
                    ship.setRot(ais.getRot());
                    ship.setLatitude(ais.getLatitude());
                    ship.setLongitude(ais.getLongitude());
                    ship.setNavigationalStatus(ais.getNavigationalStatus());
                    updateTarget(ship);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS03Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                AIS03 ais = (AIS03) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .heading(ais.getHeading()).cog(ais.getCog())
                            .sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .navigationalStatus(ais.getNavigationalStatus())
                            .build();
                    ships.put(mmsi, ship);
                    createTarget(ship);
                } else {
                    ship = ships.get(mmsi);
                    ship.setHeading(ais.getHeading());
                    ship.setCog(ais.getCog());
                    ship.setSog(ais.getSog());
                    ship.setRot(ais.getRot());
                    ship.setLatitude(ais.getLatitude());
                    ship.setLongitude(ais.getLongitude());
                    ship.setNavigationalStatus(ais.getNavigationalStatus());
                    updateTarget(ship);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
        ais4ES.subscribe(new AIS04Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                AIS04 ais = (AIS04) data;
                int mmsi = ais.getMMSI();

                if (!stations.containsKey(mmsi)) {
                    station = new BaseStation(
                            ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getDate(),
                            ais.getTypeOfElectronicPositionFixingDevice());
                    stations.put(mmsi, station);
                    createTarget(station);
                } else {
                    station = stations.get(mmsi);
                    station.setDate(ais.getDate());
                    station.setEpfd(ais.getTypeOfElectronicPositionFixingDevice());
                    updateTarget(station);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais5ES.subscribe(new AIS05Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS05 ais = (AIS05) data;
                System.out.println(ais);
                int mmsi = ais.getMMSI();
                String mmsiStr = Integer.toString(mmsi);
                String mid = mmsiStr.substring(0, 3);
                if (!ships.containsKey(mmsi)) {

                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .callSign(ais.getCallSign())
                            .country(midMap.get(new Integer(mid)))
                            .destination(ais.getDestination())
                          //  .draught(ais.getDraught())
                            // .eta(ais.getETA())
                            .imo(ais.getIMO())
                            //   .length(ais.getLength())
                            // .name(ais.getName())
                           // .shipType(ais.getShipType())
                            // .width(ais.getWidth())
                            .build();
                    System.out.println("ship 05 create" + ship);
                  //  System.out.println("ais.getWidth() "+ ais.getWidth());
                    //  System.out.println(ais);
                    ships.put(mmsi, ship);

                    createTarget(ship);
                } else {
                    System.out.println("update ais.getShipType() " + ais.getShipType());
                    ship = ships.get(mmsi);
                    ship.setCallSign(ais.getCallSign());
                  //  ship.setCountry(midMap.get(new Integer(mid)));
                    ship.setDestination(ais.getDestination());
                  //  ship.setDraught(ais.getDraught());
                    //   ship.setETA(ais.getETA());
                    ship.setIMO(ais.getIMO());
                    //    ship.setLength(ais.getLength());
                    ship.setName(ais.getName());
                    ship.setShipType(ais.getShipType());
                    //   ship.setWidth(ais.getWidth());
                    System.out.println("ship 05 update " + ship);
                    updateTarget(ship);
                }

                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

    }

    private void createTarget(Ship target) {
        GShip gShip = new GShip(target);
        gShips.put(target.getMMSI(), gShip);
        if (target.getLatitude() != 0.0 && target.getLongitude() != 0.0) {
            Renderable[] renderables = gShip.getRenderables();
            for (Renderable r : renderables) {
                aisLayer.addRenderable(r);
            }
            wwd.redrawNow();
        }
    }

    private void updateTarget(Ship target) {
        GShip gShip = gShips.get(target.getMMSI());
        gShip.update(target);
        wwd.redrawNow();
    }

    private void deleteTarget(Ship target) {

    }

    private void createTarget(BaseStation target) {

    }

    private void updateTarget(BaseStation target) {

    }

    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            aisPanelController.updateAisPanel(ship, timestamps, midMap);
        });
    }

    private void clip() {
        aisStationLayer.setEnabled(false);
    }

    private void unClip() {
        aisStationLayer.setEnabled(true);
    }

}
