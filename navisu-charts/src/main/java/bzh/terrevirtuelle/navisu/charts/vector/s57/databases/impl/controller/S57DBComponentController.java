/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.S57DBComponentImpl;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.BuoyageDbLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.DaymarDbLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.DepareDbLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.DepthContourDbLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDbLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.PontonDbLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.TopmarDbLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view.BuoyageView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view.DaymarView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view.DepareView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view.DephContourView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view.PontonView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWindow;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public class S57DBComponentController
        extends Widget2DController
        implements Initializable {

    protected S57DBComponentImpl component;
    private String componentKeyName;
    protected static final Logger LOGGER = Logger.getLogger(S57DBComponentController.class.getName());
    protected GuiAgentServices guiAgentServices;
    protected LayerTreeServices layerTreeServices;
    protected LayersManagerServices layersManagerServices;
    protected DatabaseServices databaseServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected TopologyServices topologyServices;
    private final String FXML = "s57DBController.fxml";

    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected static final String ALARM_SOUND = "/data/sounds/pling.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    private final String USER = "admin";
    private final String PASSWD = "admin";
    protected Properties properties;
    private static final String NAME = "S57DB";

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected String viewgroupstyle = "configuration.css";

    /* Common controls */
    @FXML
    public Group view;
    @FXML
    public Pane viewPane;
    /*@FXML
    public TabPane toolsTabPane;*/
    @FXML
    public Button quit;
    @FXML
    public Button requestButton;
    @FXML
    public Button helpButton;
    @FXML
    public ChoiceBox<String> databasesCB;
    @FXML
    public ChoiceBox<String> objectsCB;
    @FXML
    public Button latLonButton;
    @FXML
    public Button interactiveButton;
    @FXML
    public Button selectedButton;
    @FXML
    public TextField databaseTF;
    @FXML
    public TextField objectsTF;
    @FXML
    public Label latMinLabel;
    @FXML
    public Label lonMinLabel;
    @FXML
    public Label latMaxLabel;
    @FXML
    public Label lonMaxLabel;

    @FXML
    public TextField hostnameTF;
    @FXML
    public TextField encPortDBTF;

    protected Map<String, String> acronyms;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected MeasureTool measureTool;
    protected final Set<S57Controller> s57Controllers = new HashSet<>();
    protected boolean first = true;
    protected static final String GROUP = "S57 charts";
    protected static final String BUOYAGE_LAYER = "BUOYAGE";
    protected static final String HARBOUR_LAYER = "HARBOUR";
    protected static final String DEPARE_LAYER = "DEPARE";
    protected static final String AREA_LAYER = "AREA";
    protected static final String BUILDING_LAYER = "BUILDING";
    protected static final String EARTH_LAYER = "EARTH";
    protected static final String NAVIGATION_LAYER = "NAVIGATION";
    protected static final String DANGERS_LAYER = "DANGERS";
    protected static final String CBLSUB_LAYER = "CBLSUB";
    protected RenderableLayer buoyageLayer;
    protected RenderableLayer harbourLayer;
    protected RenderableLayer depareLayer;
    protected RenderableLayer areaLayer;
    protected RenderableLayer buildingLayer;
    protected RenderableLayer earthLayer;
    protected RenderableLayer navigationLayer;
    protected RenderableLayer dangersLayer;
    protected RenderableLayer cblsubLayer;
    protected double lat0;
    protected double lon0;
    protected double lat1;
    protected double lon1;
    protected Connection connection;
    protected Map<Pair<Double, Double>, String> topMarkMap = new HashMap<>();
    protected Map<Pair<Double, Double>, String> marsysMap = new HashMap<>();
    protected ObservableList<String> dbCbData = FXCollections.observableArrayList("s57NP1DB", "s57NP2DB", "s57NP3DB", "s57NP4DB", "s57NP5DB", "s57NP6DB");
    /*
    private ObservableList<String> objectsCbData = FXCollections.observableArrayList(
            "ACHARE : AnchorageArea",
            
            "DOCARE : DockArea",
            "DRGARE : DredgedArea",
            "FAIRWY : Fairway",
            "LAKARE : Lake",
            "LIGHTS : Light",
            "LNDMRK : Landmark",
            "MIPARE : MilitaryPracticeArea",
            "NAVLNE : NavigationLine",
            "OBSTRN : NavigationalSystemOfMarks",
            "RESARE : RestrictedArea",
            "SEAARE : SeaAreaNamedWaterArea",
            "SLCONS : ShorelineConstruction",
            "SOUNDG : Sounding",
            "TOPMAR : Topmark",
            "TSSBND : TrafficSeparationSchemeBoundary",
            "UNSARE : UnsurveyedArea",
            "UWTROC : UnderwaterAwashRock",
            "WRECKS : Wreck");
     */
    private ObservableList<String> objectsCbData = FXCollections.observableArrayList(
            "ALL : All S57 objects",
            "BUOYAGE : All buoyage",
            "PONTON : Pontoon",
            "DEPARE : DepthArea",
            "DEPCNT : DepthContour"
    );
    public static final Map<String, String> S57_REQUEST_MAP = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("TOPMAR", "SELECT geom, topshp "
                    + " FROM topmar "
                    + " WHERE geom && ST_MakeEnvelope");
            put("M_NSYS", "SELECT geom, marsys "
                    + " FROM m_nsys "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BCNCAR", "SELECT objnam, geom, bcnshp, colour, colpat, rcid, catcam "
                    + " FROM bcncar "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BCNLAT", "SELECT objnam, geom, bcnshp, colour, colpat, rcid, catlam "
                    + " FROM bcnlat "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BCNISD", "SELECT objnam, geom, bcnshp, colour, colpat, rcid, status"
                    + " FROM bcnisd  "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BCNSAW", "SELECT objnam, geom, bcnshp, colour, colpat, rcid, status"
                    + " FROM bcnsaw "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BCNSPP", "SELECT objnam, geom, bcnshp, colour, colpat, rcid, catspm"
                    + " FROM bcnspp "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BCNISD", "SELECT objnam, geom, bcnshp, colour, colpat, rcid, status"
                    + " FROM bcnisd "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BOYCAR", "SELECT objnam, geom, boyshp, colour, colpat, rcid, catcam"
                    + " FROM boycar "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BOYINB", "SELECT objnam, geom, boyshp, colour, colpat, rcid, status"
                    + " FROM boyinb "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BOYISD", "SELECT objnam, geom, boyshp, colour, colpat, rcid, status"
                    + " FROM boyisd "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BOYLAT", "SELECT objnam, geom, boyshp, colour, colpat, rcid, catlam"
                    + " FROM boylat"
                    + " WHERE geom && ST_MakeEnvelope");
            put("BOYSAW", "SELECT objnam, geom, boyshp, colour, colpat, rcid, status"
                    + " FROM boysaw "
                    + " WHERE geom && ST_MakeEnvelope");
            put("BOYSPP", "SELECT objnam, geom, boyshp, colour, colpat, rcid, catspm"
                    + " FROM boyspp"
                    + " WHERE geom && ST_MakeEnvelope");
            put("DAYMAR", "SELECT objnam, geom, topshp, colour, colpat, rcid, natcon"
                    + " FROM daymar"
                    + " WHERE geom && ST_MakeEnvelope");
            put("MORFAC", "SELECT objnam, geom, boyshp, colour, colpat, rcid, catmor"
                    + " FROM morfac"
                    + " WHERE geom && ST_MakeEnvelope");
            put("PONTON", "SELECT  ST_AsText(geom)"
                    + " FROM ponton"
                    + " WHERE geom && ST_MakeEnvelope");
            put("DEPARE", "SELECT  ST_AsText(geom), drval1, drval2"
                    + " FROM depare"
                    + " WHERE geom && ST_MakeEnvelope");
            put("DEPCNT", "SELECT  ST_AsText(geom), valdco"
                    + " FROM depcnt"
                    + " WHERE geom && ST_MakeEnvelope");
        }
    ;

    });
    
    public S57DBComponentController(S57DBComponentImpl component, String componentKeyName,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            LayersManagerServices layersManagerServices,
            S57ChartComponentServices s57ChartComponentServices,
            DatabaseServices databaseServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            TopologyServices topologyServices) {
        super(keyCode, keyCombination);
        this.componentKeyName = componentKeyName;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        // this.s57ChartComponentServices = s57ChartComponentServices;
        this.layersManagerServices = layersManagerServices;
        this.databaseServices = databaseServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.topologyServices = topologyServices;
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
        guiAgentServices.getRoot().getChildren().add(this);
        buoyageLayer = layersManagerServices.getLayer(GROUP, BUOYAGE_LAYER);
        harbourLayer = layersManagerServices.getLayer(GROUP, HARBOUR_LAYER);
        depareLayer = layersManagerServices.getLayer(GROUP, DEPARE_LAYER);
        areaLayer = layersManagerServices.getLayer(GROUP, AREA_LAYER);
        buildingLayer = layersManagerServices.getLayer(GROUP, BUILDING_LAYER);
        earthLayer = layersManagerServices.getLayer(GROUP, EARTH_LAYER);
        navigationLayer = layersManagerServices.getLayer(GROUP, NAVIGATION_LAYER);
        dangersLayer = layersManagerServices.getLayer(GROUP, DANGERS_LAYER);
        cblsubLayer = layersManagerServices.getLayer(GROUP, CBLSUB_LAYER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databasesCB.setItems(dbCbData);
        databasesCB.getSelectionModel().select("s57NP5DB");
        databaseTF.setText("s57NP5DB");
        databasesCB.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> databaseTF.setText(databasesCB.getValue())
                );
        objectsCB.setItems(objectsCbData);
        // initCheckBox( "properties/objects_1.csv", objectsCB);
        objectsCB.getSelectionModel().select("ALL : All S57 objects");
        objectsTF.setText("ALL");
        objectsCB.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> {
                    String[] v = newValue.split(":");
                    objectsTF.setText(v[0]);
                }
                );

        quit.setOnMouseClicked((MouseEvent event) -> {
            component.off();
        });
        helpButton.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Options");
            alert.setHeaderText("Display S57 objects");
            Text s = new Text(
                    "    Choice database with scale :\n\n"
                    + "    s57NP1DB - Overview 	< 1 : 1 500 000\n"
                    + "    s57NP2DB - General 	1 : 350 000 à 1 : 1 500 000\n"
                    + "    s57NP3DB - Coastal 	1 : 90 000 à 1 : 350 000\n"
                    + "    s57NP4DB - Approach 	1 : 22 000 à 1 : 90 000\n"
                    + "    s57NP5DB - Harbour 	1 : 4 000 à 1 : 22 000\n"
                    + "    s57NP6DB - Berthing 	> 1 : 4 000\n\n"
                    + "    Selection of objects to display");
            s.setWrappingWidth(650);
            alert.getDialogPane().setContent(s);
            alert.show();
        });
        latLonButton.setOnMouseClicked((MouseEvent event) -> {
            Platform.runLater(() -> {
                initSelectedZone();
            });
        });
        interactiveButton.setOnMouseClicked((MouseEvent event) -> {
            measureTool = new MeasureTool(wwd);
            MeasureToolController measureToolController = new MeasureToolController();
            measureTool.setController(measureToolController);
            measureTool.setMeasureShapeType(MeasureTool.SHAPE_SQUARE);
            measureTool.setArmed(true);
        });
        selectedButton.setOnMouseClicked((MouseEvent event) -> {
            if (measureTool != null) {
                List<? extends Position> positions = measureTool.getPositions();
                lat0 = positions.get(0).getLatitude().getDegrees();
                lon0 = positions.get(0).getLongitude().getDegrees();
                lat1 = positions.get(2).getLatitude().getDegrees();
                lon1 = positions.get(2).getLongitude().getDegrees();

                latMinLabel.setText(String.format("%.2f", lat0));
                lonMinLabel.setText(String.format("%.2f", lon0));
                latMaxLabel.setText(String.format("%.2f", lat1));
                lonMaxLabel.setText(String.format("%.2f", lon1));

                measureTool.setArmed(false);
                measureTool.dispose();
            }
        });

        requestButton.setOnMouseClicked((MouseEvent event) -> {
            if (buoyageLayer.getNumRenderables() != 0) {
                buoyageLayer.removeAllRenderables();
            }
            connection = databaseServices.connect(databaseTF.getText(),
                    "localhost", "jdbc:postgresql://", "5432", "org.postgresql.Driver", USER, PASSWD);
            retrieveIn(objectsTF.getText(), lat0, lon0, lat1, lon1);
        });

    }

    private void initSelectedZone() {

        Dialog dialog = new Dialog<>();
        dialog.setTitle("Create Area");
        dialog.setHeaderText("Please enter selected area coordinates.");
        dialog.setResizable(false);

        Label lat0Label = new Label("Northwest point latitude : ");
        Label lon0Label = new Label("Northwest point longitude : ");
        Label lat1Label = new Label("Southeast point latatitude : ");
        Label lon1Label = new Label("Southeast point longitude : ");

        TextField lat0TF = new TextField();
        TextField lat1TF = new TextField();
        TextField lon0TF = new TextField();
        TextField lon1TF = new TextField();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        grid.add(lat0Label, 0, 0);
        grid.add(lat0TF, 1, 0);
        grid.add(lon0Label, 0, 1);
        grid.add(lon0TF, 1, 1);

        grid.add(lat1Label, 0, 2);
        grid.add(lat1TF, 1, 2);
        grid.add(lon1Label, 0, 3);
        grid.add(lon1TF, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            try {
                lat0 = Double.valueOf(lat0TF.getText().trim());
                lon0 = Double.valueOf(lon0TF.getText().trim());
                lat1 = Double.valueOf(lat1TF.getText().trim());
                lon1 = Double.valueOf(lon1TF.getText().trim());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Range latitude : -90° <= Latitude <= 90° \n"
                        + "Range longitude : -180° <= Longitude <= 180°");
                alert.show();
            }
            if (lat0 < -90.0 || lat0 > 90.0 || lat1 < -90.0 || lat1 > 90.0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Range latitude : -90° <= Latitude <= 90°");
                alert.show();
            }
            if (lon0 < -180.0 || lon0 > 180.0 || lon1 < -180.0 || lon1 > 180.0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Range longitude : -180° <= Lonitude <= 180°");
                alert.show();
            }
            event.consume();
            dialog.close();
            latMinLabel.setText(Double.toString(lat0));
            lonMinLabel.setText(Double.toString(lon0));
            latMaxLabel.setText(Double.toString(lat1));
            lonMaxLabel.setText(Double.toString(lon1));
        });
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
    }

    public void retrieveIn(String object, double latMin, double lonMin,
            double latMax, double lonMax) {

        //Define TopMak for all buoyages, default is 0 : no topmark
        TopmarDbLoader topmarDbLoader = new TopmarDbLoader(connection);
        topMarkMap = topmarDbLoader.retrieveIn(latMin, lonMin, latMax, lonMax);

        //Define IALA system for all buoyages, default is 1
        MnsysDbLoader mnsysDbLoader = new MnsysDbLoader(connection);
        marsysMap = mnsysDbLoader.retrieveIn(latMin, lonMin, latMax, lonMax);

        if (object.trim().equals("ALL") || object.trim().equals("BUOYAGE")) {
            //Create a loader for BeaconCardinal, Retrieve all BeaconCardinals in a rectangle latMin, lonMin, latMax, lonMax
            BuoyageDbLoader buoyageDbLoader = new BuoyageDbLoader(connection, "BCNCAR", marsysMap);
            List<Buoyage> buoyages = buoyageDbLoader.retrieveIn(latMin, lonMin, latMax, lonMax);

            //Create a Viewer for Buoyage,Display all buoyages retrieved
            BuoyageView buoyageView = new BuoyageView(topMarkMap, buoyageLayer, "BCNCAR");
            buoyageView.display(buoyages);

            new BuoyageView(topMarkMap, buoyageLayer, "BCNLAT")
                    .display(new BuoyageDbLoader(connection, "BCNLAT", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BCNISD")
                    .display(new BuoyageDbLoader(connection, "BCNISD", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BCNSAW")
                    .display(new BuoyageDbLoader(connection, "BCNSAW", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BCNSPP")
                    .display(new BuoyageDbLoader(connection, "BCNSPP", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BCNISD")
                    .display(new BuoyageDbLoader(connection, "BCNISD", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));

            new BuoyageView(topMarkMap, buoyageLayer, "BOYCAR")
                    .display(new BuoyageDbLoader(connection, "BOYCAR", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BOYLAT")
                    .display(new BuoyageDbLoader(connection, "BOYLAT", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BOYINB")
                    .display(new BuoyageDbLoader(connection, "BOYINB", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BOYISD")
                    .display(new BuoyageDbLoader(connection, "BOYISD", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BOYSAW")
                    .display(new BuoyageDbLoader(connection, "BOYSAW", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new BuoyageView(topMarkMap, buoyageLayer, "BOYSPP")
                    .display(new BuoyageDbLoader(connection, "BOYSPP", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
            new DaymarView(topMarkMap, harbourLayer, "DAYMAR")
                    .display(new DaymarDbLoader(connection, "DAYMAR", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));

            new BuoyageView(topMarkMap, buoyageLayer, "MORFAC")
                    .display(new BuoyageDbLoader(connection, "MORFAC", marsysMap)
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
        }
        if (object.trim().equals("ALL") || object.trim().equals("PONTON")) {
            new PontonView(topologyServices, harbourLayer, "PONTON")
                    .display(new PontonDbLoader(connection, "PONTON")
                            .retrieveIn(latMin, lonMin, latMax, lonMax));
        }
        if (object.trim().equals("ALL") || object.trim().equals("DEPARE")) {
            try {
                new DepareView(topologyServices, harbourLayer, "DEPARE")
                        .display(new DepareDbLoader(connection, "DEPARE")
                                .retrieveIn(latMin, lonMin, latMax, lonMax));
            } catch (SQLException ex) {
                Logger.getLogger(S57DBComponentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (object.trim().equals("ALL") || object.trim().equals("DEPCNT")) {
            guiAgentServices.getJobsManager().newJob("Load contours", (progressHandle) -> {
                try {
                    new DephContourView(topologyServices, harbourLayer, "DEPCNT")
                            .display(new DepthContourDbLoader(connection, "DEPCNT")
                                    .retrieveIn(latMin, lonMin, latMax, lonMax));
                } catch (SQLException ex) {
                    Logger.getLogger(S57DBComponentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

}
