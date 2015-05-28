package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import bzh.terrevirtuelle.navisu.instruments.ais.view.targets.ShipTypeColor;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.AisRadarImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.view.GRShip;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.view.GRShipImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.common.view.TargetPanel;
import bzh.terrevirtuelle.navisu.instruments.common.view.TrackPanel;
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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

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
    @FXML
    public Slider opacitySlider;
    @FXML
    public Slider dimensionSlider;
    @FXML
    public Slider scaleSlider;
    @FXML
    public Group aisbuttonpanel;
    @FXML
    public Group aisinfopanel;

    AisServices aisServices;
    boolean first = true;
    final Rotate rotationTransform = new Rotate(0, 0, 0);
    protected Timeline fiveSecondsWonder;
    protected final int CENTER_X = 500;//425 + 100 suite rajout pane//-25 Serge
    protected final int CENTER_Y = 494;//429 + 65 et agrandissement faisceau

    int centerX;
    int centerY;
    protected double latOwner = 0.0;
    protected double lonOwner = 0.0;
    protected double posX = 0.0;
    protected double posY = 0.0;
    protected double radarScale = 1000.0;//MAX=1/3490
    protected final double RADIUS = 4.;
    protected final int RADIUS_LIMIT = 350;
    protected final double DURATION = .03;
    protected final int MAX = 840;
    protected final int MIN = 50;
    protected Map<Integer, GRShip> ships;
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
    protected TargetPanel aisTargetPanel;
    protected TrackPanel aisTrackPanel;

    protected GuiAgentServices guiAgentServices;
    protected Map<Integer, String> midMap;

    public AisRadarController(AisRadarImpl aisRadar) {
        this(aisRadar, null, null);
    }

    public AisRadarController(AisRadarImpl aisRadar, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.aisRadar = aisRadar;
        guiAgentServices = aisRadar.getGuiAgentServices();
        ships = new HashMap<>();
        outOfRangeShips = new HashMap<>();
        outOfRangeTransceivers = new HashMap<>();
        createOwnerShip();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AisRadar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        aisinfopanel.setVisible(false);
        aisbuttonpanel.setVisible(true);
        radar.setOpacity(0.6);
        quit.setOnMouseClicked((MouseEvent event) -> {
            aisRadar.off();
        });
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                radar.setOpacity(opacitySlider.getValue());
            });
        });
        dimensionSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                radar.setScaleX(dimensionSlider.getValue());
                radar.setScaleY(dimensionSlider.getValue());
            });
        });
        scaleSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                radarScale = scaleSlider.getValue();
                updateTargets();
            });
        });
        GhostLine ghostLine = new GhostLine(CENTER_X, CENTER_Y, 1, RADIUS_LIMIT);
        getChildren().add(ghostLine);

        radar.setOnMouseClicked(press -> {
            if (press.getButton() == MouseButton.PRIMARY) {
                ghostLine.show(press.getX(), press.getY());
            } else {
                ghostLine.clear();
            }
        });

        addPanelController();
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
        // creation de l'objet metier central
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
        createTarget(ownerShip);
    }

    public void notifyNmeaMessageChanged(GGA data) {
        latOwner = data.getLatitude();
        lonOwner = data.getLongitude();
        ownerShip.setLatitude(latOwner);
        ownerShip.setLongitude(lonOwner);
        updateTargets();

    }

    public void notifyNmeaMessageChanged(VTG data) {

        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        if (ownerShip.getSog() > 0.1) {
            //  ship.setShapeId(0);
        }
    }

    public void notifyNmeaMessageChanged(RMC data) {
        latOwner = data.getLatitude();
        lonOwner = data.getLongitude();
        ownerShip.setLatitude(latOwner);
        ownerShip.setLongitude(lonOwner);
        ownerShip.setLongitude(data.getLongitude());
        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        if (ownerShip.getSog() > 0.1) {
            //  ship.setShapeId(0);
        }
        updateTargets();

    }

    private void schedule() {
        fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(DURATION), (ActionEvent event) -> {
            //faisceau.setPivotX(50.0);
            //faisceau.setPivotY(50.0);
            faisceau.setRotate(route);
            route++;
            route %= 360;
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    public void createTarget(Ship ship) {
        centerX = (int) (CENTER_X - (lonOwner - ship.getLongitude()) * radarScale);
        centerY = (int) (CENTER_Y + (latOwner - ship.getLatitude()) * radarScale);
        GRShipImpl grship = new GRShipImpl(ship, centerX, centerY, RADIUS);
        grship.setId(Integer.toString(ship.getMMSI()));
        grship.setOnMouseClicked((MouseEvent me) -> {
            if (first) {
                grship.setRadius(RADIUS * 1.5);
                updateAisPanel(grship.getShip());
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
                ships.put(ship.getMMSI(), grship);
                radar.getChildren().add(grship);
                String dest = ship.getDestination();
                if (dest == null) {
                    dest = "";
                }
                String name = ship.getName();
                if (name == null) {
                    name = "Not yet available";
                }
                String label = "Name : " + name + "\n"
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

    public void updateTarget(Ship ship) {
        centerX = (int) (CENTER_X - (lonOwner - ship.getLongitude()) * radarScale);
        centerY = (int) (CENTER_Y + (latOwner - ship.getLatitude()) * radarScale);
        Integer mmsiI = ship.getMMSI();
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
                    String name = ship.getName();
                    if (name == null) {
                        name = "Not yet available";
                    }
                    label = "Name : " + name + "\n"
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
            if (nodes != null) {
                String mmsi = Integer.toString(mmsiI);
                nodes.stream().filter((n) -> (n.getId().contains(mmsi))).map((n) -> {
                    nodes.remove(n);
                    return n;
                }).forEach((n) -> {
                    outOfRangeShips.put(ship.getMMSI(), (GRShip) n);
                });
            }
        }
    }

    private void updateTargets() {
        Platform.runLater(() -> {
            Set<Integer> s = ships.keySet();
            s.stream().forEach((i) -> {
                //updateTarget(ships.get(i).getShip());
                maj(ships.get(i).getShip());
            });
        });
    }

    private void maj(Ship target) {
        List<Node> nodes = radar.getChildren();
        String mmsi = Integer.toString(target.getMMSI());
        for (Node n : nodes) {
            if (n.getId() != null && n.getId().equals(mmsi)) {
                int cX = (int) (CENTER_X - (lonOwner - target.getLongitude()) * radarScale);
                int cY = (int) (CENTER_Y + (latOwner - target.getLatitude()) * radarScale);
                if (cX <= MAX && cX >= MIN && cY <= MAX && cY >= MIN) {
                    Platform.runLater(() -> {

                        if (outOfRangeShips.containsKey(target.getMMSI())) {//Ships In range
                            GRShipImpl grship = (GRShipImpl) outOfRangeShips.get(target.getMMSI());
                            radar.getChildren().add(grship);
                            outOfRangeShips.remove(mmsi, grship);
                        }
                        ((Circle) n).setCenterX(cX);
                        ((Circle) n).setCenterY(cY);
                    });
                }
            }
        }
    }

    public void setTarget(BaseStation transceiver) {
        centerX = (int) (CENTER_X - (lonOwner - transceiver.getLongitude()) * radarScale);
        centerY = (int) (CENTER_Y + (latOwner - transceiver.getLatitude()) * radarScale);
        GRShipImpl grship = new GRShipImpl(ship, centerX, centerY, RADIUS);
        grship.setId(Integer.toString(ship.getMMSI()));
        grship.setOnMouseClicked((MouseEvent me) -> {
            if (first) {
                grship.setRadius(RADIUS * 1.5);
                // updateAisPanel(grship.getShip());
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

    public void updateTarget(BaseStation transceiver) {
        centerX = (int) (CENTER_X - (lonOwner - transceiver.getLongitude()) * radarScale);
        centerY = (int) (CENTER_Y + (latOwner - transceiver.getLatitude()) * radarScale);
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

    private void addPanelController() {
        Platform.runLater(() -> {
            aisTargetPanel = new TargetPanel(guiAgentServices, KeyCode.B, KeyCombination.CONTROL_DOWN);
            aisTargetPanel.setTranslateX(100);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisTargetPanel);
            guiAgentServices.getRoot().getChildren().add(aisTargetPanel); //Par defaut le radar n'est pas visible Ctrl-A
            aisTargetPanel.setScale(1.0);
            aisTargetPanel.setVisible(false);
            
            aisTrackPanel = new TrackPanel(guiAgentServices, KeyCode.T, KeyCombination.CONTROL_DOWN);
            aisTrackPanel.setTranslateX(150);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisTrackPanel);
            guiAgentServices.getRoot().getChildren().add(aisTrackPanel);
            aisTrackPanel.setScale(1.0);
            aisTrackPanel.setVisible(true);
        });
    }

    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            aisTargetPanel.updateAisPanel(ship, timestamps, midMap);
        });
    }

    public void setTimestamps(Map<Integer, Calendar> timestamps) {
        this.timestamps = timestamps;
    }

    public void setMidMap(Map<Integer, String> midMap) {
        this.midMap = midMap;
    }
}
