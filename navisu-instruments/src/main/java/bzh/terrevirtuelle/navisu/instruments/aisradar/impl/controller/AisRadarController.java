package bzh.terrevirtuelle.navisu.instruments.aisradar.impl.controller;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS01Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS02Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS03Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS05Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VTGEvent;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import bzh.terrevirtuelle.navisu.instruments.ais.view.targets.ShipTypeColor;
import bzh.terrevirtuelle.navisu.instruments.aisradar.impl.AisRadarImpl;
import bzh.terrevirtuelle.navisu.instruments.aisradar.impl.view.GRShip;
import bzh.terrevirtuelle.navisu.instruments.aisradar.impl.view.GRShipImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge modifs Dom : variables public
 */
public class AisRadarController
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group radar;
    @FXML
    public ImageView faisceau;
    @FXML
    public double route = 0.0;
    @FXML
    public ImageView quit;

    boolean first = true;
    final Rotate rotationTransform = new Rotate(0, 0, 0);
    protected Timeline fiveSecondsWonder;
    protected final int CENTER_X = 525;//425 + 100 suite rajout pane
    protected final int CENTER_Y = 494;//429 + 65 et agrandissement faisceau
    protected double latOwner = 0.0;
    protected double lonOwner = 0.0;
    protected double posX = 0.0;
    protected double posY = 0.0;
    protected final int RANGE = 5000;
    protected final double RADIUS = 4.;
    protected final double DURATION = .03;
    protected final int MAX = 700;
    protected final int MIN = 100;
    protected Map<Integer, Ship> ships;
    protected Map<Integer, GRShip> outOfRangeShips;
    protected Map<Integer, GRShip> outOfRangeTransceivers;
    protected Map<Integer, BaseStation> transceivers;
    protected Map<Integer, Calendar> timestamps;
    protected Ship ship;
    protected Ship ownerShip;
    protected BaseStation transceiver;
    protected NumberFormat formatter = new DecimalFormat("#0");
    protected SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    protected AisRadarImpl aisRadar;
    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS01Event> ais1ES = cm.getComponentEventSubscribe(AIS01Event.class);
    ComponentEventSubscribe<AIS02Event> ais2ES = cm.getComponentEventSubscribe(AIS02Event.class);
    ComponentEventSubscribe<AIS03Event> ais3ES = cm.getComponentEventSubscribe(AIS03Event.class);
    ComponentEventSubscribe<AIS04Event> ais4ES = cm.getComponentEventSubscribe(AIS04Event.class);
    ComponentEventSubscribe<AIS05Event> ais5ES = cm.getComponentEventSubscribe(AIS05Event.class);
    ComponentEventSubscribe<GGAEvent> ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
    ComponentEventSubscribe<RMCEvent> rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
    ComponentEventSubscribe<VTGEvent> vtgES = cm.getComponentEventSubscribe(VTGEvent.class);

    public AisRadarController(AisRadarImpl aisRadar, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.aisRadar = aisRadar;
        ships = new HashMap<>();
        outOfRangeShips = new HashMap<>();
        outOfRangeTransceivers = new HashMap<>();
        transceivers = new HashMap<>();
        timestamps = new HashMap<>();
        createOwnerShip();
        subscribe();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AisRadar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        quit.setOnMouseClicked((MouseEvent event) -> {
            aisRadar.off();
        });
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
            Logger.getLogger(AisRadarController.class.getName()).log(Level.SEVERE, null, ex);
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
        createTarget(ownerShip, (int) (CENTER_X - (lonOwner - ownerShip.getLongitude()) * RANGE),
                (int) (CENTER_Y + (latOwner - ownerShip.getLatitude()) * RANGE));
    }

    private void subscribe() {
        ais1ES.subscribe(new AIS01Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS01 ais = (AIS01) data;
                    //System.out.println("ais "+ais);
                    int mmsi = ais.getMMSI();
                    if (!ships.containsKey(mmsi)) {
                        ship = ShipBuilder.create()
                                .mmsi(ais.getMMSI())
                                .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                                .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                                .build();
                        ships.put(mmsi, ship);
                        createTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                    } else {
                        ship = ships.get(mmsi);
                        //  System.out.println("AIS01 mmsi "+mmsi +" lat "+ais.getLatitude());
                        ship.setLatitude(ais.getLatitude());
                        ship.setLongitude(ais.getLongitude());
                        ship.setCog(ais.getCog());
                        ship.setSog(ais.getSog());
                        updateTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                    }
                    timestamps.put(mmsi, Calendar.getInstance());
                } catch (Exception e) {
                    // System.out.println("ais1ES.subscribe " + e);
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
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .navigationalStatus(ais.getNavigationalStatus())
                            .build();
                    ships.put(mmsi, ship);
                    createTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                            (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                } else {
                    ship = ships.get(mmsi);
                    // System.out.println("AIS02 mmsi "+mmsi +" lat "+ais.getLatitude());
                    ship.setLatitude(ais.getLatitude());
                    ship.setLongitude(ais.getLongitude());
                    ship.setCog(ais.getCog());
                    ship.setSog(ais.getSog());
                    updateTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                            (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
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
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .build();
                    ships.put(mmsi, ship);
                    createTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                            (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                } else {
                    ship = ships.get(mmsi);
                    //  System.out.println("AIS03 mmsi "+mmsi +" lat "+ais.getLatitude());
                    ship.setLatitude(ais.getLatitude());
                    ship.setLongitude(ais.getLongitude());
                    ship.setCog(ais.getCog());
                    ship.setSog(ais.getSog());
                    updateTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                            (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais4ES.subscribe(new AIS04Event() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS04 ais = (AIS04) data;
                int mmsi = ais.getMMSI();
                /*
                 if (!transceivers.containsKey(mmsi)) {
                 transceiver = new Transceiver(
                 ais.getMMSI(),
                 ais.getLatitude(), ais.getLongitude());

                 transceivers.put(mmsi, transceiver);
                 setTarget(transceiver, (int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                 (int) (CENTER_Y + (latOwner - ais.getLatitude()) * RANGE));
                 } else {
                 transceiver = transceivers.get(mmsi);
                 transceiver.setLatitude(ais.getLatitude());
                 transceiver.setLongitude(ais.getLongitude());
                 updateTarget(transceiver, (int) (CENTER_X - (lonOwner - transceiver.getLongitude()) * RANGE),
                 (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                 }
                 */
                timestamps.put(mmsi, Calendar.getInstance());
            }

        });

        ais5ES.subscribe(new AIS05Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
               // System.out.println("ais "+data);
                AIS05 ais = (AIS05) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                   //  System.out.println("ais "+ais);
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .destination(ais.getDestination())
                            .shipType(ais.getShipType())
                            .name(ais.getName())
                            .build();
                    ships.put(mmsi, ship);
                    createTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                            (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                } else {
                  //   System.out.println("ais "+ais);
                    ship = ships.get(mmsi);
                    ship.setShipType(ais.getShipType());
                    
                    ship.setName(ais.getShipName());
                    ship.setETA(ais.getETA());
                    ship.setDestination(ais.getDestination());
                    updateTarget(ship, (int) (CENTER_X - (lonOwner - ship.getLongitude()) * RANGE),
                            (int) (CENTER_Y + (latOwner - ship.getLatitude()) * RANGE));
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        // souscription aux événements GPS
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

    private void createTarget(Ship ship, int centerX, int centerY) {
        GRShipImpl grship = new GRShipImpl(ship, centerX, centerY, RADIUS);
        grship.setId(Integer.toString(ship.getMMSI()));
        grship.setOnMouseClicked((MouseEvent me) -> {
            if (first) {
                grship.setRadius(RADIUS * 1.5);
                first = false;
            } else {
                grship.setRadius(RADIUS);
                first = true;
            }
            if (me.isAltDown()) {
                System.out.println(grship.getShip().getLatitude() + "  " + grship.getShip().getLongitude());
            }
        });

        if (centerX <= MAX && centerX >= MIN && centerY <= MAX && centerY >= MIN) {
            Platform.runLater(() -> {
                radar.getChildren().add(grship);
                String label = "Name : " + ship.getName() + "\n"
                        + "MMSI : " + ship.getMMSI() + "\n"
                        + "Sog : " + formatter.format(ship.getSog()) + "\n"
                        + "Cog : " + formatter.format(ship.getCog());
                Tooltip t = new Tooltip(label);
                Tooltip.install(grship, t);
            });
        } else {
            outOfRangeShips.put(ship.getMMSI(), grship);
        }
    }

    private void updateTarget(Ship ship, int centerX, int centerY) {
        Integer mmsiI = ship.getMMSI();
        // System.out.println("radar "+ship.getShipType());
        if (centerX <= MAX && centerX >= MIN && centerY <= MAX && centerY >= MIN) {
            Platform.runLater(() -> {

                if (outOfRangeShips.containsKey(mmsiI)) {//Ships In range
                    GRShipImpl grship = (GRShipImpl) outOfRangeShips.get(mmsiI);
                    radar.getChildren().add(grship);
                    outOfRangeShips.remove(mmsiI, grship);
                }
                List<Node> nodes = radar.getChildren();
                String mmsi = Integer.toString(mmsiI);

                nodes.stream().filter((n) -> (n.getId() != null)).filter((n) -> (n.getId().contains(mmsi))).map((n) -> (Circle) n).map((circle) -> {
                    circle.setCenterX(centerX);
                    return circle;
                }).map((circle) -> {
                    circle.setCenterY(centerY);
                    return circle;
                }).map((circle) -> {
                    String label = "";
                    Calendar eta = ship.getETA();
                    String etaFormat = "";
                    if (eta != null) {
                        etaFormat = dateFormatter.format(eta.getTime());
                    }
                    String dest = ship.getDestination();
                    if (dest == null) {
                        dest = "";
                    }
                    label = "Name : " + ship.getName() + "\n"
                            + "MMSI : " + ship.getMMSI() + "\n"
                            + "Sog : " + formatter.format(ship.getSog()) + "\n"
                            + "Cog : " + formatter.format(ship.getCog()) + "\n"
                            + "ETA : " + etaFormat + "\n"
                            + "Dest : " + dest;
                    Tooltip t = new Tooltip(label);
                    Tooltip.install(circle, t);
                    return circle;
                }).forEach((circle) -> {
                    circle.setFill(ShipTypeColor.COLOR.get(ship.getShipType()));
                });
            });
        } else {
            List<Node> nodes = radar.getChildren();//Ship out of range
            String mmsi = Integer.toString(mmsiI);
            nodes.stream().filter((n) -> (n.getId().contains(mmsi))).map((n) -> {
                nodes.remove(n);
                return n;
            }).forEach((n) -> {
                outOfRangeShips.put(ship.getMMSI(), (GRShip) n);
            });
        }
    }

    private void setTarget(BaseStation transceiver, int centerX, int centerY) {
        GRShipImpl grship = new GRShipImpl(ship, centerX, centerY, RADIUS);
        grship.setId(Integer.toString(ship.getMMSI()));
        grship.setOnMouseClicked((MouseEvent me) -> {
            if (first) {
                grship.setRadius(RADIUS * 1.5);
                first = false;
            } else {
                grship.setRadius(RADIUS);
                first = true;
            }
        });

        if (centerX <= MAX && centerX >= MIN && centerY <= MAX && centerY >= MIN) {
            Platform.runLater(() -> {
                radar.getChildren().add(grship);
                String label = "MMSI : " + transceiver.getMMSI();
                Tooltip t = new Tooltip(label);
                Tooltip.install(grship, t);
            });
        } else {
            outOfRangeTransceivers.put(ship.getMMSI(), grship);
        }
    }

    private void updateTarget(BaseStation transceiver, int centerX, int centerY) {

        if (centerX <= MAX && centerX >= MIN && centerY <= MAX && centerY >= MIN) {
            Platform.runLater(() -> {
                List<Node> nodes = radar.getChildren();
                String mmsi = Integer.toString(transceiver.getMMSI());
                nodes.stream().filter((n) -> (n.getId() != null)).filter((n) -> (n.getId().contains(mmsi))).map((n) -> (Circle) n).map((circle) -> {
                    circle.setCenterX(centerX);
                    return circle;
                }).map((circle) -> {
                    circle.setCenterY(centerY);
                    return circle;
                }).forEach((circle) -> {
                    circle.setFill(Color.BLUE);
                });
            });
        }
    }

}
