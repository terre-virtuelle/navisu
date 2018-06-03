package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import static bzh.terrevirtuelle.navisu.domain.ship.view.ShipType.TYPE;
import bzh.terrevirtuelle.navisu.instruments.common.view.targets.ShipTypeColor;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.AisRadarImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.view.GRShip;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.view.GRShipImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.MenuPanel;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TargetPanel;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TrackPanel;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
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
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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
    public Pane viewpane;
    @FXML
    public Group faisceau;
    @FXML
    public double route = 0.0;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public Slider dimensionSlider;
    @FXML
    public Slider rangeSlider;
    @FXML
    public Slider zoomSlider;
    @FXML
    public Group aisbuttonpanel;
    @FXML
    public Group aisinfopanel;
    @FXML
    public Button buttonsInfos;
    @FXML
    public Text imo;
    @FXML
    public Text type;
    @FXML
    public Text shipname;
    @FXML
    public Text mmsi;
    @FXML
    public Text length;
    @FXML
    public Text draught;
    @FXML
    public Text cog;
    @FXML
    public Text sog;
    @FXML
    public Text destination;
    @FXML
    public Text callSign;
    @FXML
    public Text width;
    @FXML
    public Text latitude;
    @FXML
    public Text longitude;
    @FXML
    public Text country;
    @FXML
    public Text agereport;
    @FXML
    public Button menu;
    @FXML
    public Button mode;
    @FXML
    public Button custom;
    @FXML
    public Button offcenter;
    @FXML
    public Button mode1;
    @FXML
    public Button mode2;
    @FXML
    public Button mode3;
    @FXML
    public Button cancel;
    @FXML
    public Button enter;
    @FXML
    public Button stbytx;
    @FXML
    public MenuButton ebl;
    @FXML
    public MenuButton vrm;
    @FXML
    public MenuButton targetalarm;
    @FXML
    public MenuButton targettype;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected AisServices aisServices;
    protected boolean first = true;
    protected final Rotate rotationTransform = new Rotate(0, 0, 0);
    protected Timeline fiveSecondsWonder;
    protected final int CENTER_X = 500;//425 + 100 suite rajout pane//-25 Serge
    protected final int CENTER_Y = 494;//429 + 65 et agrandissement faisceau
    protected int centerX;
    protected int centerY;
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

    protected AisRadarImpl aisRadar;
    protected TargetPanel aisTargetPanel;
    protected TrackPanel aisTrackPanel;
    protected MenuPanel menuPanel;
    protected GuiAgentServices guiAgentServices;
    protected Map<Integer, String> midMap;
    protected NumberFormat nf = new DecimalFormat("0.###");
    protected SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd-MM");
    protected NumberFormat formatter = new DecimalFormat("#0");
    protected SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    protected String viewgroupstyle = "common.css";
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
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
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        radar.getStylesheets().add(uri);
        viewpane.setOpacity(0.8);
        aisbuttonpanel.setVisible(true);
        Platform.runLater(() -> {
            radar.setOpacity(0.9);
            radar.setScaleX(0.685);
            radar.setScaleY(0.685);
        });
        quit.setOnMouseClicked((MouseEvent event) -> {
            aisRadar.off();
        });
        buttonsInfos.setOnMouseClicked((MouseEvent event) -> {
            aisinfopanel.setVisible(aisbuttonpanel.isVisible());
            aisbuttonpanel.setVisible(!aisinfopanel.isVisible());
        });
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                viewpane.setOpacity(opacitySlider.getValue());
            });
        });
        /*
        dimensionSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                radar.setScaleX(((dimensionSlider.getValue())/100));
                radar.setScaleY(((dimensionSlider.getValue())/100));
            });
        });
         */
        rangeSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                radarScale = rangeSlider.getValue();
                updateTargets();
            });
        });
        zoomSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                // radarScale = zoomSlider.getValue();
                // updateTargets();
            });
        });
        menu.setOnMouseClicked((MouseEvent event) -> {

            menuPanel = new MenuPanel(guiAgentServices, KeyCode.M, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getRoot().getChildren().add(menuPanel);
            menuPanel.setVisible(true);
        });
        stbytx.setOnMouseClicked((MouseEvent event) -> {

            workInProgress(stbytx.getText());
        });
        mode.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(mode.getText());
        });
        custom.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(custom.getText());
        });
        offcenter.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(offcenter.getText());
        });
        mode1.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(mode1.getText());
        });
        mode2.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(mode2.getText());
        });
        mode3.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(mode3.getText());
        });
        cancel.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(cancel.getText());
        });
        enter.setOnMouseClicked((MouseEvent event) -> {
            workInProgress(enter.getText());
        });
        ebl.getItems().get(0).setOnAction((ActionEvent event) -> {
            workInProgress(ebl.getItems().get(0).getText());
        });
        ebl.getItems().get(1).setOnAction((ActionEvent event) -> {
            workInProgress(ebl.getItems().get(1).getText());
        });
        vrm.getItems().get(0).setOnAction((ActionEvent event) -> {
            workInProgress(vrm.getItems().get(0).getText());
        });
        vrm.getItems().get(1).setOnAction((ActionEvent event) -> {
            workInProgress(vrm.getItems().get(1).getText());
        });
        targetalarm.getItems().get(0).setOnAction((ActionEvent event) -> {
            workInProgress(targetalarm.getItems().get(0).getText());
        });
        targetalarm.getItems().get(1).setOnAction((ActionEvent event) -> {
            workInProgress(targetalarm.getItems().get(1).getText());
        });
        targettype.getItems().get(0).setOnAction((ActionEvent event) -> {
            workInProgress(targettype.getItems().get(0).getText());
        });
        targettype.getItems().get(1).setOnAction((ActionEvent event) -> {
            workInProgress(targettype.getItems().get(1).getText());
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
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(AisRadarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // creation de l'objet metier central
        // tests properties set default values
        String mmsiP = properties.getProperty("mmsi");
        if (mmsiP == null || mmsiP.equals("")) {
            mmsiP = "000000000";
        }
        String nameP = properties.getProperty("name");
        if (nameP == null || nameP.equals("")) {
            nameP = "Unknow";
        }
        String latP = properties.getProperty("latitude");
        if (latP == null || latP.equals("")) {
            latP = "48.3649";
        }
        String lonP = properties.getProperty("longitude");
        if (lonP == null || lonP.equals("")) {
            lonP = "-4.4871";
        }
        String cogP = properties.getProperty("cog");
        if (cogP == null || cogP.equals("")) {
            cogP = "0.00";
        }
        String sogP = properties.getProperty("sog");
        if (sogP == null || sogP.equals("")) {
            sogP = "0.00";
        }
        String countryP = properties.getProperty("country");
        if (countryP == null || countryP.equals("")) {
            countryP = "Unknow";
        }
        String widthP = properties.getProperty("width");
        if (widthP == null || widthP.equals("")) {
            widthP = "0.00";
        }
        String lengthP = properties.getProperty("length");
        if (lengthP == null || lengthP.equals("")) {
            lengthP = "0.00";
        }
        String draughtP = properties.getProperty("draught");
        if (draughtP == null || draughtP.equals("")) {
            draughtP = "0.00";
        }
        String shipTypeP = properties.getProperty("shipType");
        if (shipTypeP == null || shipTypeP.equals("")) {
            shipTypeP = "0";
        }
        String navigationalStatusP = properties.getProperty("navigationalStatus");
        if (navigationalStatusP == null || navigationalStatusP.equals("")) {
            navigationalStatusP = "0";
        }
        String callSignP = properties.getProperty("callSign");
        if (callSignP == null || callSignP.equals("")) {
            callSignP = "0";
        }

        ownerShip = ShipBuilder.create()
                .mmsi(new Integer(mmsiP))
                .name(nameP)
                .latitude(new Float(latP))
                .longitude(new Float(lonP))
                .cog(new Float(cogP))
                .sog(new Float(sogP))
                //.heading(new Float(properties.getProperty("heading")))
                .country(countryP)
                .width(new Float(widthP))
                .length(new Float(lengthP))
                .draught(new Float(draughtP))
                .shipType(new Integer(shipTypeP))
                .navigationalStatus(new Integer(navigationalStatusP))
                .callSign(callSignP)
                .target(true).build();
        latOwner = new Float(latP);
        lonOwner = new Float(lonP);
        createTarget(ownerShip);
    }

    public void notifyNmeaMessage(GGA data) {
        latOwner = data.getLatitude();
        lonOwner = data.getLongitude();
        ownerShip.setLatitude(latOwner);
        ownerShip.setLongitude(lonOwner);
        updateTargets();
    }

    public void notifyNmeaMessage(VTG data) {

        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        if (ownerShip.getSog() > 0.1) {
            //  ship.setShapeId(0);
        }
    }

    public void notifyNmeaMessage(RMC data) {
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
                updateAisRadarPanel(grship.getShip());
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
                    circle.setFill(ShipTypeColor.getColor(ship.getShipType()));
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
        nodes.stream().filter((n) -> (n.getId() != null && n.getId().equals(mmsi))).forEachOrdered((n) -> {
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
        });
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
        });

    }

    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            aisTargetPanel.updatePanel(ship, timestamps, midMap);
        });
    }

    protected final void updateAisRadarPanel(Ship ship) {
        Platform.runLater(() -> {

            aisinfopanel.setVisible(true);
            aisbuttonpanel.setVisible(false);
            updateAisPanel(ship, timestamps, midMap);
        });
    }

    public void setTimestamps(Map<Integer, Calendar> timestamps) {
        this.timestamps = timestamps;
    }

    public void setMidMap(Map<Integer, String> midMap) {
        this.midMap = midMap;
    }

    public void updateAisPanel(Ship ship,
            Map<Integer, Calendar> timestamps,
            Map<Integer, String> midMap) {
        setVisible(true);
        if (ship.getName() == null || "".equals(ship.getName())) {
            shipname.setText("Name not yet available");
        } else {
            shipname.setText(ship.getName());
        }
        // Le type est publié tel quel, le dictionnaire n'est pas bien defini dans la spec AIS
        // A revoir
        if (ship.getShipType() != 0) {
            type.setText(TYPE.get(ship.getShipType()));
        } else {
            type.setText("---");
        }
        if (ship.getCallSign() != null) {
            callSign.setText(ship.getCallSign());
        } else {
            callSign.setText("---");
        }
        if (ship.getMMSI() != 0) {
            if (timestamps != null && timestamps.get(ship.getMMSI()) != null) {
                long seconds = Calendar.getInstance().getTimeInMillis()
                        - timestamps.get(ship.getMMSI()).getTimeInMillis();
                agereport.setText("Age report : " + Long.toString(seconds / 1000) + " s");
            }
        } else {
            agereport.setText("Age report : --- s");
        }
        if (ship.getMMSI() != 0) {
            mmsi.setText(Integer.toString(ship.getMMSI()));
        } else {
            mmsi.setText("---");
        }
        if (ship.getIMO() != 0) {
            imo.setText(Integer.toString(ship.getIMO()));
        } else {
            imo.setText("---");
        }
        if (ship.getLength() != 0) {
            length.setText(Float.toString(ship.getLength()) + " m");
        } else {
            length.setText("---");
        }
        if (ship.getWidth() != 0) {
            width.setText(Float.toString(ship.getWidth()) + " m");
        } else {
            width.setText("---");
        }
        if (ship.getDraught() != 0) {
            draught.setText(Float.toString(ship.getDraught()) + " m");
        } else {
            draught.setText("---");
        }
        if (ship.getNavigationalStatus() != 0) {
            //  status.setText(Integer.toString(ship.getNavigationalStatus()));
        } else {
            // status.setText("---");
        }
        if (ship.getSog() != 0) {
            sog.setText(nf.format(ship.getSog()) + " Kn");
        } else {
            sog.setText("---");
        }
        if (ship.getCog() != 0 && ship.getCog() != 511) {
            cog.setText((int) ship.getCog() + " °");
        } else {
            cog.setText("---");
        }
        if (ship.getDestination() != null) {
            destination.setText(ship.getDestination());
        } else {
            destination.setText("---");
        }
        if (ship.getETA() != null) {
            //  eta.setText(dt.format(ship.getETA().getTime()));
        } else {
            //  eta.setText("---");
        }
        if (ship.getLatitude() != 0) {
            latitude.setText(nf.format(ship.getLatitude()) + " °");
        } else {
            latitude.setText("---");
        }
        if (ship.getLongitude() != 0) {
            longitude.setText(nf.format(ship.getLongitude()) + " °");
        } else {
            longitude.setText("---");
        }
        if (ship.getMMSI() != 0) {
            String mmsiStr = Integer.toString(ship.getMMSI());
            String mid = mmsiStr.substring(0, 3);
            if (midMap != null && country != null) {
                country.setText(midMap.get(new Integer(mid)));
            }
        } else {
            country.setText("---");
        }
    }

    private void workInProgress(String text) {
        System.out.println(text + ": WorkInProgress");
    }
}
