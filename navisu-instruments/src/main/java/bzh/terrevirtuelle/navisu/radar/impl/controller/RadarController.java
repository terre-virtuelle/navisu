/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.radar.impl.controller;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VTGEvent;
import bzh.terrevirtuelle.navisu.domain.devices.Transceiver;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS2;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS4;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS5;
import bzh.terrevirtuelle.navisu.domain.nmea.model.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.ShipBuilder;
import bzh.terrevirtuelle.navisu.widgets.Widget2DController;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge modifs Dom : variables public
 */
public class RadarController
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group radar;
    @FXML
    public ImageView faisceau;
    @FXML
    public double route = 0.0;
 
    boolean first = true;
    final Rotate rotationTransform = new Rotate(0, 0, 0);
    protected Timeline fiveSecondsWonder;
    protected final int CENTER_X = 425;//425
    protected final int CENTER_Y = 429;//429
    protected double latOwner = 0.0;
    protected double lonOwner = 0.0;
    protected double posX = 0.0;
    protected double posY = 0.0;
    protected final int RANGE = 5000;
    protected final double RADIUS = 3.;
    protected final double DURATION = .03;
    protected Map<Integer, Ship> ships;
    protected Map<Integer, Transceiver> transceivers;
    protected Map<Integer, Calendar> timestamps;
    protected Ship ship;
    protected Ship ownerShip;
    protected Transceiver transceiver;
    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);
    ComponentEventSubscribe<GGAEvent> ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
    ComponentEventSubscribe<RMCEvent> rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
    ComponentEventSubscribe<VTGEvent> vtgES = cm.getComponentEventSubscribe(VTGEvent.class);

    public RadarController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        ships = new HashMap<>();
        transceivers = new HashMap<>();
        timestamps = new HashMap<>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Radar-fullscreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        createOwnerShip();
        subscribe();
        setTarget(CENTER_X, CENTER_Y, RADIUS, "#ff0000");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void start() {
        schedule();
    }

    @Override
    public void stop() {
        fiveSecondsWonder.stop();
    }

    private void createOwnerShip() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/domain.properties"));
        } catch (IOException ex) {
            Logger.getLogger(RadarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // creation de l'objet metier
        ownerShip = ShipBuilder.create()
                .mmsi(new Integer(properties.getProperty("mmsi")))
                .name(properties.getProperty("name"))
                .latitude(new Float(properties.getProperty("latitude")))
                .longitude(new Float(properties.getProperty("longitude")))
                .cog(new Float(properties.getProperty("cog")))
                .sog(new Float(properties.getProperty("sog")))
                .heading(new Float(properties.getProperty("heading")))
                .country(properties.getProperty("country"))
                .width(new Float(properties.getProperty("width")))
                .length(new Float(properties.getProperty("length")))
                .draught(new Float(properties.getProperty("draught")))
                .shipType(new Integer(properties.getProperty("shipType")))
                .navigationalStatus(new Integer(properties.getProperty("navigationalStatus")))
                .electronicPositionDevice(new Integer(properties.getProperty("electronicPositionDevice")))
                .callSign(properties.getProperty("callSign")).build();
        latOwner = new Float(properties.getProperty("latitude"));
        lonOwner = new Float(properties.getProperty("longitude"));
        System.out.println("ownerShip " + ownerShip);
    }

    private void subscribe() {
        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS1 ais = (AIS1) data;
                    int mmsi = ais.getMMSI();
                    if (!ships.containsKey(mmsi)) {
                        ship = ShipBuilder.create()
                                .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                                .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                                .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                                .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                                .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                                .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                                .eta(ais.getETA()).destination(ais.getDestination())
                                .build();
                        ships.put(mmsi, ship);
                        setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y  +(latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                    }
                    timestamps.put(mmsi, Calendar.getInstance());
                } catch (Exception e) {
                    // System.out.println("ais1ES.subscribe " + e);
                }
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS2 ais = (AIS2) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                            .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                            .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                            .eta(ais.getETA()).destination(ais.getDestination()).build();
                    ships.put(mmsi, ship);
                     setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y +(latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS3 ais = (AIS3) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                            .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                            .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                            .eta(ais.getETA()).destination(ais.getDestination()).build();
                    ships.put(mmsi, ship);
                     setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS4 ais = (AIS4) data;
                int mmsi = ais.getMMSI();

                if (!transceivers.containsKey(mmsi)) {
                    transceiver = new Transceiver(ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(), ais.getDate());
                    transceivers.put(mmsi, transceiver);
                }
                timestamps.put(mmsi, Calendar.getInstance());
                 setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#0000ff");
            }
            
        });
        ais5ES.subscribe(new AIS5Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS5 ais = (AIS5) data;
                int mmsi = ais.getMMSI();

                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                            .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                            .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                            .eta(ais.getETA()).destination(ais.getDestination()).build();
                    ships.put(mmsi, ship);
                     setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
        // souscription aux événements
        ggaES.subscribe(new GGAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                GGA data = (GGA) d;
                ownerShip.setLatitude(data.getLatitude());
                ownerShip.setLongitude(data.getLongitude());

                // mise à jour via le DPAgent
                // dpAgentServices.update(ship);
            }
        });
        vtgES.subscribe(new VTGEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                VTG data = (VTG) d;
                ownerShip.setCog(data.getCog());
                ownerShip.setSog(data.getSog());
                if (ownerShip.getSog() > 0.1) {
                    //   ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                // dpAgentServices.update(ship);
            }
        });
        rmcES.subscribe(new RMCEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                RMC data = (RMC) d;
                ownerShip.setLatitude(data.getLatitude());
                ownerShip.setLongitude(data.getLongitude());
                ownerShip.setCog(data.getCog());
                ownerShip.setSog(data.getSog());
                if (ownerShip.getSog() > 0.1) {
                    //  ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                // dpAgentServices.update(ship);
            }
        });
    }

    private void schedule() {
        fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(DURATION), (ActionEvent event) -> {
            faisceau.setRotate(route);
            route++;
            route %= 360;
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    private void setTarget(int centerX, int centerY, double radius, String color) {
        if (centerX <= 600 && centerX >= 100 && centerY <= 600 && centerY >= 100) {
            Circle circle = new Circle();
            circle.setCenterX(centerX);
            circle.setCenterY(centerY);
            circle.setRadius(radius);
            circle.setFill(Paint.valueOf(color));

            Platform.runLater(() -> {
                radar.getChildren().add(circle);
            });

            circle.setOnMouseClicked((MouseEvent me) -> {

                if (first) {
                    circle.setRadius(radius * 1.5);
                    first = false;
                } else {
                    circle.setRadius(radius);
                    first = true;
                }
            });
        }
    }
}
