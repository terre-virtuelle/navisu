/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.LambertServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.BuoyageDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.CoastlineDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.DepareDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.DepthContourDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.PontoonDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.ShorelineConstructionDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.TopmarDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.BuoyageView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.DepareView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.LandmarkView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.LightView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.S57ObjectView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.SlConsView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.AnchorageAreaDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.DockAreaDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.DredgedAreaDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.LandmarkDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.LightDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.NavigationLineDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.RestrictedAreaDBLoader;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthContour;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Light;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.StlDBComponentImpl;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.bathy.BathyLoader;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.WorldWindow;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.dem.DemDbLoader;
import bzh.terrevirtuelle.navisu.dem.db.DemDBServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
import bzh.terrevirtuelle.navisu.stl.charts.impl.loader.dem.DemSrtmElevationLoader;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.kml.BuoyageExportKML;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.kml.GridBox3DExportKML;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.BuoyageExportToSTL;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.DaeExportToSTL;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.GridBox3DExportToSTL;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.LandmarkExportToSTL;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.MeshExportToSTL;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.ObjExportToSTL;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.S57ObjectsExportToSTL;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.SLConsExportToSTL;

import com.google.common.collect.ImmutableMap;
import gov.nasa.worldwind.avlist.AVKey;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.FileHandler;
import org.apache.commons.io.FileUtils;

/**
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public class StlDBComponentController
        extends Widget2DController
        implements Initializable {

    protected StlDBComponentImpl component;

    protected BathymetryDBServices bathymetryDBServices;
    protected DatabaseServices databaseServices;
    protected DelaunayServices delaunayServices;
    protected DemDBServices demDBServices;
    protected DisplayServices displayServices;
    protected GeodesyServices geodesyServices;
    protected GuiAgentServices guiAgentServices;
    protected JTSServices jtsServices;
    protected LambertServices lambertServices;
    protected LayersManagerServices layersManagerServices;
    protected KmlComponentServices kmlComponentServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected ObjComponentServices objComponentServices;
    protected Pro4JServices pro4JServices;
    protected ShapefileObjectServices shapefileObjectServices;
    protected StlComponentServices stlComponentServices;
    protected TopologyServices topologyServices;

    protected static final Logger LOGGER = Logger.getLogger(StlDBComponentController.class.getName());
    private final String NAVISU_HOME = System.getProperty("user.home") + "/.navisu";

    private final String FXML = "stlDBController.fxml";
    protected final String SEP = File.separator;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected String CACHE_FILE_NAME = System.getProperty("user.home") + "/.navisu/caches/caches.properties";
    protected static final String ALARM_SOUND = "/data/sounds/pling.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected static final String DEFAULT_KML_PATH = "privateData/kml/";
    protected static final String DEFAULT_STL_PATH = "privateData/stl/";
    protected static final String DEFAULT_ASC_PATH = "privateData/asc/";
    private final String HOST = "localhost";
    private final String PROTOCOL = "jdbc:postgresql://";
    private final String PORT = "5432";
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "admin";
    private final String PASSWD = "admin";

    private final String S57_DEFAULT_DATABASE_1 = "s57NP1DB";
    private final String S57_DEFAULT_DATABASE_2 = "s57NP2DB";
    private final String S57_DEFAULT_DATABASE_3 = "s57NP53B";
    private final String S57_DEFAULT_DATABASE_4 = "s57NP4DB";
    private final String S57_DEFAULT_DATABASE_5 = "s57NP5DB";
    private final String S57_DEFAULT_DATABASE_6 = "s57NP6DB";

    protected Properties configProperties = new Properties();
    protected Properties cacheProperties = new Properties();

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected String viewgroupstyle = "configuration.css";
    protected Map<String, String> acronyms;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected MeasureTool measureTool;
    protected final Set<S57Controller> s57Controllers = new HashSet<>();
    protected boolean first = true;
    protected static final String GROUP_0 = "S57 charts";
    protected static final String BATHYMETRY_LAYER = "S57StlBathy";
    protected static final String S57_LAYER = "S57Stl";
    protected static final String LIGHTS_LAYER = "LIGHTS";
    protected static final String SELECTED_LAYER = "TargetCmd";

    protected String LAT_MIN;
    protected String LON_MIN;
    protected String LAT_MAX;
    protected String LON_MAX;

    protected static final double RETRIEVE_OFFSET = 0.002;

    protected RenderableLayer bathymetryLayer;
    protected RenderableLayer s57Layer;
    protected RenderableLayer selectLayer;
    protected RenderableLayer lightsLayer;
    protected ShapeAttributes normalAttributes;
    protected ShapeAttributes highlightAttributes;

    protected double DEFAULT_SIDE = 180.0;
    protected double DEFAULT_BASE_HEIGHT = 4.0;
    protected double tileSideX = DEFAULT_SIDE;
    protected double tileSideY = DEFAULT_SIDE;
    protected double tileSideZ = DEFAULT_BASE_HEIGHT;
    protected double DEFAULT_GRID = 100.0;
    protected double gridX = DEFAULT_GRID;
    protected double gridY = DEFAULT_GRID;
    protected double lat0 = 520;
    protected double lon0;
    protected double lat1;
    protected double lon1;
    protected double latRange;
    protected double lonRange;
    protected double latRangeMetric;
    protected double lonRangeMetric;
    protected double latScale;
    protected double lonScale;
    protected double globalScale;
    protected List<String> stlFileNames;
    protected List<String> kmlFileNames;
    protected List<Polygon> selectedPolygons = new ArrayList<>();

    protected int tileCount = 1;
    protected double DEFAULT_EXAGGERATION = 10.0;
    protected double verticalExaggeration = DEFAULT_EXAGGERATION;
    protected double simplifyFactor;
    protected S57ObjectView s57Viewer;
    protected S57ObjectsExportToSTL s57ObjectsExport;
    protected List<? extends Geo> objects = new ArrayList<>();
    protected List<DepthContour> depthContours = new ArrayList<>();
    protected List<Buoyage> buoyages = new ArrayList<>();
    protected List<Landmark> landmarks = new ArrayList<>();
    protected Connection s57Connection;
    protected Connection bathyConnection;
    protected Connection elevationConnection;
    protected DEM bathymetry;
    protected DEM elevation;
    protected double maxDepth;
    protected Map<Pair<Double, Double>, String> topMarkMap = new HashMap<>();
    protected String marsys;
    protected List<String> selectedObjects = new ArrayList<>();
    protected String isoValuesUlhysses = "";
    protected String sep = File.separator;
    protected List<Polygon> tiles;
    protected List<Point3D[][]> grids = null;
    /* Common controls */
    @FXML
    public Group view;
    @FXML
    public Pane viewPane;
    @FXML
    public Button quit;
    @FXML
    public Button requestButton;
    @FXML
    public Button helpButton;
    @FXML
    public ChoiceBox<String> s57DatabasesCB;
    @FXML
    public ChoiceBox<String> bathyDatabasesCB;
    @FXML
    public Button latLonButton;
    @FXML
    public Button interactiveButton;
    @FXML
    public Button selectedButton;
    @FXML
    public TextField s57DatabaseTF;
    @FXML
    public TextField bathyDatabaseTF;
    @FXML
    public TextField objectsTF;
    @FXML
    public TextField scaleDaeTF;
    @FXML
    public TextField depthMagnificationTF;
    @FXML
    public TextField simplifyTF;
    @FXML
    public Label latMinLabel;
    @FXML
    public Label lonMinLabel;
    @FXML
    public Label latMaxLabel;
    @FXML
    public Label lonMaxLabel;
    @FXML
    public ComboBox<String> objectsCB;
    @FXML
    public TextField outFileTF;
    @FXML
    public TextField hostnameTF;
    @FXML
    public TextField tilesCountTF;
    @FXML
    public TextField rangeLatTF;
    @FXML
    public TextField rangeLonTF;
    @FXML
    public TextField scaleTF;
    @FXML
    public TextField tileSideXTF;
    @FXML
    public TextField tileSideYTF;
    @FXML
    public TextField tileSideZTF;
    @FXML
    public TextField gridSideXTF;
    @FXML
    public TextField gridSideYTF;
    @FXML
    public TextField encPortDBTF;
    @FXML
    public TextField ulhyssesTF;
    @FXML
    public RadioButton bathyRB;
    @FXML
    public RadioButton noBathyRB;
    @FXML
    public RadioButton depareRB;
    @FXML
    public RadioButton depareUlhyssesRB;
    @FXML
    public CheckBox simplifyCB;
    @FXML
    public ChoiceBox<String> tilesCountCB;
    @FXML
    public GridPane paneGP;
    @FXML
    public TextField elevationDatabaseTF;
    @FXML
    public ChoiceBox<String> elevationDatabasesCB;
    @FXML
    public RadioButton srtmRB;
    @FXML
    public RadioButton elevationRB;
    @FXML
    public RadioButton noAltiRB;
    @FXML
    public CheckBox wwjPreviewCB;
    @FXML
    public CheckBox stlPreviewCB;
    @FXML
    public CheckBox generateStlCB;
    @FXML
    public CheckBox generateKmlCB;
    @FXML
    public RadioButton solidRB;
    @FXML
    public RadioButton wireframeRB;
    /*--------------------------------------Checkboxes ----*/
    @FXML
    public CheckBox autoBoundCB;
    @FXML
    public CheckBox allCB;
    @FXML
    public CheckBox achareCB;
    @FXML
    public CheckBox buoyageCB;
    @FXML
    public CheckBox coalneCB;
    @FXML
    public CheckBox depcntCB;
    @FXML
    public CheckBox docareCB;
    @FXML
    public CheckBox drgareCB;
    @FXML
    public CheckBox lightsCB;
    @FXML
    public CheckBox lndmrkCB;
    @FXML
    public CheckBox navlneCB;
    @FXML
    public CheckBox slconsCB;
    @FXML
    public CheckBox pontonCB;
    @FXML
    public RadioButton terrainRB;
    @FXML
    public CheckBox resareCB;
    @FXML
    public CheckBox baseCB;
    @FXML
    public Button meshStlObjectButton;
    @FXML
    public Button daeButton;
    @FXML
    public Button objButton;

    /*-----------------------------Tools------*/
    @FXML
    public TextField objXOffsetTF;
    @FXML
    public TextField objYOffsetTF;

    int k = 0;
    int i = 0;

    int j = 0;
    String resultStlFilename = null;
    boolean solid;

    final ToggleGroup bathyGroup = new ToggleGroup();
    final ToggleGroup wsGroup = new ToggleGroup();
    final ToggleGroup altiGroup = new ToggleGroup();
    final ToggleGroup terrainGroup = new ToggleGroup();

    protected ObservableList<String> s57DbCbData
            = FXCollections.observableArrayList(S57_DEFAULT_DATABASE_1, S57_DEFAULT_DATABASE_2, S57_DEFAULT_DATABASE_3, S57_DEFAULT_DATABASE_4, S57_DEFAULT_DATABASE_5, S57_DEFAULT_DATABASE_6);
    protected ObservableList<String> bathyDbCbData = FXCollections.observableArrayList("BathyShomDB");
    protected ObservableList<String> elevationDbCbData = FXCollections.observableArrayList("AltiV2_2-0_75mIgnDB");
    protected ObservableList<String> tilesCbData = FXCollections.observableArrayList("1x1", "2x2", "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10");
    protected Map<String, CheckBox> s57SelectionMap;

    protected StlGuiController stlGuiController;
    protected DaeExportToSTL daeExportToSTL;
    protected ObjExportToSTL objExportToSTL;
    protected MeshExportToSTL meshExportToSTL;

    // protected boolean isDaeObject = false;
    // protected boolean isObjObject = false;
    protected List<Point3D> boundList;

    protected long startTime;

    public StlDBComponentController(StlDBComponentImpl component,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            LayersManagerServices layersManagerServices,
            S57ChartComponentServices s57ChartComponentServices,
            DatabaseServices databaseServices,
            DelaunayServices delaunayServices,
            DemDBServices demServices,
            DisplayServices displayServices,
            BathymetryDBServices bathymetryDBServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            TopologyServices topologyServices,
            ShapefileObjectServices shapefileObjectServices,
            GeodesyServices geodesyServices,
            JTSServices jtsServices,
            StlComponentServices stlComponentServices,
            KmlComponentServices kmlComponentServices,
            ObjComponentServices objComponentServices,
            Pro4JServices pro4JServices) {
        super(keyCode, keyCombination);

        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.databaseServices = databaseServices;
        this.delaunayServices = delaunayServices;
        this.demDBServices = demServices;
        this.displayServices = displayServices;
        this.bathymetryDBServices = bathymetryDBServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.topologyServices = topologyServices;
        this.jtsServices = jtsServices;
        this.shapefileObjectServices = shapefileObjectServices;
        this.geodesyServices = geodesyServices;
        this.stlComponentServices = stlComponentServices;
        this.kmlComponentServices = kmlComponentServices;
        this.objComponentServices = objComponentServices;
        this.pro4JServices = pro4JServices;

        this.daeExportToSTL = new DaeExportToSTL(geodesyServices, guiAgentServices, jtsServices);
        this.objExportToSTL = new ObjExportToSTL(geodesyServices, guiAgentServices, jtsServices,
                objComponentServices, pro4JServices, displayServices, instrumentDriverManagerServices);
        this.meshExportToSTL = new MeshExportToSTL(geodesyServices, guiAgentServices, jtsServices);

        LOGGER.setLevel(Level.INFO);
        FileHandler fh = null;
        try {
            fh = new FileHandler(NAVISU_HOME + "/logs/" + "navisu.log");
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(StlDBComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOGGER.addHandler(fh);

        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
        guiAgentServices.getRoot().getChildren().add(this);

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

        s57Layer = layersManagerServices.getLayer(GROUP_0, S57_LAYER);
        bathymetryLayer = layersManagerServices.getLayer(GROUP_0, BATHYMETRY_LAYER);
        lightsLayer = layersManagerServices.getLayer(GROUP_0, LIGHTS_LAYER);
        selectLayer = layersManagerServices.getLayer(GROUP_0, SELECTED_LAYER);
        try {
            configProperties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

        InputStream input = null;
        try {
            input = new FileInputStream(CACHE_FILE_NAME);
            cacheProperties.load(input);
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(StlDBComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        LAT_MIN = cacheProperties.getProperty("LAT_MIN");
        LON_MIN = cacheProperties.getProperty("LON_MIN");
        LAT_MAX = cacheProperties.getProperty("LAT_MAX");
        LON_MAX = cacheProperties.getProperty("LON_MAX");
        //Mat values :  48.21N -4.61     48.42N -4.30
        kmlFileNames = new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources
    ) {
        makeAttributes();

        // Init s57 selection Panel
        s57SelectionMap = ImmutableMap.<String, CheckBox>builder()
                .put("ACHARE", achareCB)
                .put("BUOYAGE", buoyageCB)
                .put("COALNE", coalneCB)
                .put("DEPCNT", depcntCB)
                .put("DOCARE", docareCB)
                .put("DRGARE", drgareCB)
                .put("LIGHTS", lightsCB)
                .put("LNDMRK", lndmrkCB)
                .put("NAVLNE", navlneCB)
                .put("SLCONS", slconsCB)
                .put("PONTON", pontonCB)
                .put("RESARE", resareCB)
                .build();
        stlGuiController = new StlGuiController(geodesyServices, s57SelectionMap, allCB, selectedObjects, objectsTF);
        selectedObjects.clear();
        objectsTF.clear();
        stlGuiController.initS57Gui();
        allCB.setOnAction((ActionEvent event) -> {
            stlGuiController.initS57Gui("ALL");
        });

        s57DatabasesCB.setItems(s57DbCbData);
        s57DatabasesCB.getSelectionModel().select(S57_DEFAULT_DATABASE_5);
        s57DatabaseTF.setText(S57_DEFAULT_DATABASE_5);
        s57DatabasesCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> s57DatabaseTF.setText(s57DatabasesCB.getValue())
                );
        bathyDatabasesCB.setItems(bathyDbCbData);
        bathyDatabasesCB.getSelectionModel().select("BathyShomDB");
        bathyDatabaseTF.setText("BathyShomDB");
        bathyDatabasesCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> bathyDatabaseTF.setText(bathyDatabasesCB.getValue())
                );

        elevationDatabasesCB.setItems(elevationDbCbData);
        elevationDatabasesCB.getSelectionModel().select("AltiV2_2-0_75mIgnDB");
        elevationDatabaseTF.setText("AltiV2_2-0_75mIgnDB");
        elevationDatabasesCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> elevationDatabaseTF.setText(elevationDatabasesCB.getValue())
                );
        tilesCountCB.setItems(tilesCbData);
        tilesCountCB.getSelectionModel().select("1x1");
        tilesCountTF.setText("1");
        tilesCountCB.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> {
                    tileCount = Integer.parseInt(tilesCountCB.getValue().split("x")[0]);
                    tilesCountTF.setText(Integer.toString(tileCount * tileCount));
                    selectedPolygons.addAll(stlGuiController.createAndDisplayTiles(s57Layer, Material.RED, 100, lat0, lon0, lat1, lon1, tileCount, tileCount));
                });
        gridSideXTF.setText(Double.toString(DEFAULT_GRID));
        gridSideXTF.setOnAction((ActionEvent event) -> {
            try {
                gridX = Double.parseDouble(gridSideXTF.getText());
                gridSideXTF.setText(Double.toString(gridX));
                gridSideXTF.setText(Double.toString(gridX));
            } catch (NumberFormatException e) {
                gridX = DEFAULT_SIDE;
                gridSideXTF.setText(Double.toString(gridX));
                gridX = DEFAULT_SIDE;
                gridSideXTF.setText(Double.toString(gridX));
            }
        });
        gridSideYTF.setText(Double.toString(DEFAULT_GRID));
        gridSideYTF.setOnAction((ActionEvent event) -> {
            try {
                gridY = Double.parseDouble(gridSideYTF.getText());
                gridSideYTF.setText(Double.toString(gridY));
                gridSideYTF.setText(Double.toString(gridY));
            } catch (NumberFormatException e) {
                gridY = DEFAULT_SIDE;
                gridSideYTF.setText(Double.toString(gridY));
                gridY = DEFAULT_SIDE;
                gridSideYTF.setText(Double.toString(gridY));
            }
        });
        tileSideXTF.setText(Double.toString(DEFAULT_SIDE));
        tileSideXTF.setOnAction((ActionEvent event) -> {
            try {
                tileSideX = Double.parseDouble(tileSideXTF.getText());
                tileSideXTF.setText(Double.toString(tileSideX));
                tileSideY = tileSideX;
                tileSideYTF.setText(Double.toString(tileSideX));
            } catch (NumberFormatException e) {
                tileSideX = DEFAULT_SIDE;
                tileSideXTF.setText(Double.toString(tileSideX));
                tileSideY = DEFAULT_SIDE;
                tileSideYTF.setText(Double.toString(tileSideY));
            }
        });
        tileSideYTF.setText(Double.toString(DEFAULT_SIDE));
        tileSideYTF.setOnAction((ActionEvent event) -> {

            try {
                tileSideY = Double.parseDouble(tileSideYTF.getText());
                tileSideYTF.setText(Double.toString(tileSideY));
                tileSideX = tileSideY;
                tileSideXTF.setText(Double.toString(tileSideY));
            } catch (NumberFormatException e) {
                tileSideY = DEFAULT_SIDE;
                tileSideYTF.setText(Double.toString(tileSideY));
                tileSideX = DEFAULT_SIDE;
                tileSideXTF.setText(Double.toString(tileSideX));
            }
        });
        tileSideZTF.setText(Double.toString(DEFAULT_BASE_HEIGHT));
        tileSideZTF.setOnAction((ActionEvent event) -> {
            try {
                tileSideZ = Double.parseDouble(tileSideZTF.getText());
                tileSideZTF.setText(Double.toString(tileSideZ));
            } catch (NumberFormatException e) {
                tileSideZ = DEFAULT_BASE_HEIGHT;
                tileSideZTF.setText(Double.toString(tileSideZ));
            }
        });
        tileSideZ -= 0.1;
        simplifyTF.setOnAction((ActionEvent event) -> {
            try {
                double tmp = Double.valueOf(simplifyTF.getText());
                simplifyFactor = tmp / 100000;
            } catch (NumberFormatException e) {
                simplifyFactor = 0.0001;
                simplifyTF.setText(Double.toString(simplifyFactor * 100000));
            }
        });
        depthMagnificationTF.setText(Double.toString(DEFAULT_EXAGGERATION));
        depthMagnificationTF.setOnAction((ActionEvent event) -> {
            try {
                verticalExaggeration = Double.valueOf(depthMagnificationTF.getText());
            } catch (NumberFormatException e) {
                verticalExaggeration = DEFAULT_EXAGGERATION;
                depthMagnificationTF.setText(Double.toString(verticalExaggeration));
            }
        });

        outFileTF.setText("out");

        scaleDaeTF.setText("1");

        //  noBathyRB.setToggleGroup(bathyGroup);
        depareRB.setToggleGroup(bathyGroup);
        depareUlhyssesRB.setToggleGroup(bathyGroup);

        noAltiRB.setToggleGroup(terrainGroup);
        terrainRB.setToggleGroup(terrainGroup);

        srtmRB.setToggleGroup(altiGroup);
        elevationRB.setToggleGroup(altiGroup);

        solidRB.setToggleGroup(wsGroup);
        wireframeRB.setToggleGroup(wsGroup);

        wireframeRB.setOnAction((ActionEvent event) -> {
            solid = false;
        });
        solidRB.setOnAction((ActionEvent event) -> {
            solid = true;
        });
        isoValuesUlhysses = ulhyssesTF.getText();
        ulhyssesTF.setOnAction((ActionEvent event) -> {
            isoValuesUlhysses = ulhyssesTF.getText();
            //check
        });

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
            measureTool.setPathType(AVKey.GREAT_CIRCLE);
            measureTool.setArmed(true);
        });

        selectedButton.setOnMouseClicked((MouseEvent event) -> {

            if (measureTool != null) {
                List<? extends Position> positions = measureTool.getPositions();
                if (!positions.isEmpty()) {
                    lat0 = positions.get(0).getLatitude().getDegrees();
                    lon0 = positions.get(0).getLongitude().getDegrees();
                    lat1 = positions.get(2).getLatitude().getDegrees();
                    lon1 = positions.get(2).getLongitude().getDegrees();

                    latMinLabel.setText(String.format("%.2f", lat0));
                    lonMinLabel.setText(String.format("%.2f", lon0));
                    latMaxLabel.setText(String.format("%.2f", lat1));
                    lonMaxLabel.setText(String.format("%.2f", lon1));;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Sélectionnez une zone d'acquisition");
                    alert.show();
                }
                measureTool.setArmed(false);
                measureTool.dispose();
                selectedPolygons.addAll(stlGuiController.createAndDisplayTiles(s57Layer, Material.RED, 100, lat0, lon0, lat1, lon1, tileCount, tileCount));
            }

        });

        meshStlObjectButton.setOnMouseClicked((MouseEvent event) -> {
            boundList = meshExportToSTL.loadMesh();
            wwd.redrawNow();
            displayServices.displayPoints3DAsPath(boundList, 150, s57Layer, Material.YELLOW);
            /*else {
                    PointPlacemark pp = new PointPlacemark(Position.fromDegrees(bounds.getX().getLatitude(), bounds.getX().getLongitude(), 100));
                    s57Layer.addRenderable(pp);
                    wwd.redrawNow();
                }
             */

        });
        daeButton.setOnMouseClicked((MouseEvent event) -> {
            daeExportToSTL.loadKmzAndSaveStlWgs84();
        });
        objButton.setOnMouseClicked((MouseEvent event) -> {
            boundList = objExportToSTL.loadObj(s57Layer,
                    Double.valueOf(objXOffsetTF.getText()), Double.valueOf(objYOffsetTF.getText()),
                    terrainRB.isSelected());
            if (boundList != null) {
                displayServices.displayPoints3DAsPath(boundList, 150, s57Layer, Material.YELLOW);
            }
        });
        requestButton.setOnMouseClicked((MouseEvent event) -> {

            s57Connection = databaseServices.connect(s57DatabaseTF.getText(),
                    "localhost", "jdbc:postgresql://", "5432", "org.postgresql.Driver", USER, PASSWD);
            if (lat0 != 0 && lon0 != 0 && lat1 != 0 && lon1 != 0) {
                stlGuiController.initTile(tileSideX, tileSideY, tileSideZ, tileCount);
                scaleTF.setText(stlGuiController.initScale(lat0, lon0, lat1, lon1));
                retrieveIn(objectsTF.getText(), lat0, lon0, lat1, lon1);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Lat and Lon fields must be filled \n"
                        + "Range latitude : -90° <= Latitude <= 90° \n"
                        + "Range longitude : -180° <= Longitude <= 180°");
                alert.show();
            }
        });

    }

    @SuppressWarnings("unchecked")
    public void retrieveIn(String object, double latMin, double lonMin, double latMax, double lonMax) {

        guiAgentServices.getJobsManager().newJob("Load S57 objects", new Job() {

            @Override
            public void run(ProgressHandle progressHandle) {
                startTime = System.currentTimeMillis();
                gridX = Double.parseDouble(gridSideXTF.getText());
                gridY = Double.parseDouble(gridSideYTF.getText());
                LOGGER.info("Début traitement : ");
                //Define TopMak for all buoyages, default is 0 : no topmark
                TopmarDBLoader topmarDbLoader = new TopmarDBLoader(s57Connection);
                topMarkMap = topmarDbLoader.retrieveIn(latMin, lonMin, latMax, lonMax);

                //Define IALA system for all buoyages, default is 1
                MnsysDBLoader mnsysDbLoader = new MnsysDBLoader(s57Connection);
                marsys = mnsysDbLoader.retrieveIn(latMin, lonMin, latMax, lonMax);

                // DEBUG
                // autoBoundCB.setSelected(true);
                stlPreviewCB.setSelected(false);
                if (autoBoundCB.isSelected()) {
                    stlGuiController.displayGuiGridBM(s57Layer);
                }

                //BATHY, ELEVATION AND TILES
                if (elevationRB.isSelected() && noBathyRB.isSelected()) {
                    grids = createElevationTab(lat0, lon0, lat1, lon1);
                }
                if (bathyRB.isSelected() && !elevationRB.isSelected()) {
                    grids = createBathymetryTab(lat0, lon0, lat1, lon1);
                    // displayServices.displayGridAsTriangles(grids.get(0), s57Layer, Material.GREEN, verticalExaggeration);
                }
                if (!noAltiRB.isSelected()) {
                    if (elevationRB.isSelected() && bathyRB.isSelected()) {
                        grids = createBathymetryAndElevationTab(lat0, lon0, lat1, lon1);

                    }
                    if (srtmRB.isSelected() && noBathyRB.isSelected()) {
                        grids = createSrtmElevationTab(lat0, lon0, lat1, lon1, gridX);//~1m
                    }
                    if (srtmRB.isSelected() && depareRB.isSelected()) {
                        //createElevationAndDepare(latMin, lonMin, latMax, lonMax);
                    }
                } else {
                    Point3D[][] grid = null;
                    if (autoBoundCB.isSelected()) {
                        // stlGuiController.displayGuiGridBM(s57Layer);
                    } else {
                        grid = delaunayServices.toGridTab(latMin, lonMin, latMax, lonMax, gridY, gridX, maxDepth);
                    }
                    grids = createGrids(grid, tileCount);
                }
                k = 0;
                if (grids != null) {
                    List<GridBox3D> gridBoxes = new ArrayList<>();
                    for (Point3D[][] g : grids) {
                        scaleCompute(g);
                        GridBox3D gb = new GridBox3D(g, verticalExaggeration);
                        gridBoxes.add(gb);
                        i = k / tileCount + 1;
                        j = k % tileCount + 1;
                        //  String filename = DEFAULT_ASC_PATH + outFileTF.getText() + "_" + i + "," + j + ".asc";
                        //  displayServices.exportASC(filename, g);
                    }

                    if (wwjPreviewCB.isSelected()) {
                        gridBoxes.forEach((gb) -> {
                            LOGGER.info("In affichage gridBoxes");
                            // System.out.println("In affichage gridBoxes " + gb);
                            // System.out.println("verticalExaggeration : "+verticalExaggeration);
                            // displayServices.displayGridAsTriangles(gb.getGrid(), s57Layer, Material.GREEN, verticalExaggeration);
                            wwd.redrawNow();
                        });
                    }
                    if (generateKmlCB.isSelected()) {
                        k = 0;
                        gridBoxes.forEach((gb) -> {
                            LOGGER.info("In export GridBox3D en KML");
                            i = k / tileCount + 1;
                            j = k % tileCount + 1;
                            String filename = DEFAULT_KML_PATH + outFileTF.getText() + "_" + i + "," + j + ".kml";
                            kmlFileNames.add(filename);
                            new GridBox3DExportKML(gb).exportWKML(filename, solid);
                            LOGGER.info("Out export GridBox3D en KML");
                            k++;
                        });

                    }
                    if (gridBoxes.size() == 1) {
                        //special treatment for large area
                        LOGGER.info("In export GridBox3D en STL");
                        String filename = DEFAULT_STL_PATH + outFileTF.getText() + "_" + 1 + "," + 1 + ".stl";
                        GridBox3D gb = gridBoxes.get(0);
                        // System.out.println("gb : " + gb);
                        //DEBUG for mesh import
                        // new GridBox3DExportToSTL(geodesyServices, gb).exportSTL(filename, latScale, lonScale, tileSideZ);
                        LOGGER.info("In export exportBaseSTL en STL");
                        if (baseCB.isSelected()) {
                            // stlComponentServices.exportBaseSTL(filename, "data/stl/base/base" + i + "-" + j + ".stl");
                            // stlComponentServices.exportBaseSTL(filename, "data/stl/base/baseNew.stl");
                            // stlComponentServices.exportRotateBaseSTL("data/stl/base/baseNewRotated.stl", "data/stl/base/baseNew.stl", 5.42);
                            stlComponentServices.exportBaseSTL(filename, "data/stl/base/baseNewRotated.stl");
                            LOGGER.info("Out export exportBaseSTL en STL");
                        }
                        LOGGER.info("Out export GridBox3D en STL");
                    } else {
                        k = 0;
                        gridBoxes.forEach((gb) -> {
                            LOGGER.info("In export GridBox3D en STL");
                            i = k / tileCount + 1;
                            j = k % tileCount + 1;
                            String filename = DEFAULT_STL_PATH + outFileTF.getText() + "_" + i + "," + j + ".stl";
                            new GridBox3DExportToSTL(geodesyServices, gb).exportSTL(filename, latScale, lonScale, tileSideZ);
                            LOGGER.info("In export exportBaseSTL en STL");
                            if (baseCB.isSelected()) {
                                // stlComponentServices.exportBaseSTL(filename, "data/stl/base/base" + i + "-" + j + ".stl");
                                stlComponentServices.exportBaseSTL(filename, "data/stl/base/baseNew.stl");
                                LOGGER.info("Out export exportBaseSTL en STL");
                            }
                            k++;
                            LOGGER.info("Out export GridBox3D en STL");
                        });
                    }

                    //DEPARE
                    if (selectedObjects.contains("ALL") || depareRB.isSelected()) {
                        createElevationAndDepare(latMin, lonMin, latMax, lonMax);
                    }
                    if (selectedObjects.contains("ALL") || depareUlhyssesRB.isSelected()) {
                        createElevationAndUlhyssesDepare(latMin, lonMin, latMax, lonMax);
                    }
                    k = 0;
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("BUOYAGE")) {
                        Set<String> buoyageKeySet = BUOYAGE.ATT.keySet();
                        for (Point3D[][] g : grids) {
                            LOGGER.info("In export BUOYAGE en STL");
                            buoyages.clear();
                            for (String b : buoyageKeySet) {
                                buoyages.addAll(new BuoyageDBLoader(topologyServices, s57Connection, b, topMarkMap, marsys)
                                        .retrieveObjectsIn(g[0][0].getLatitude(),
                                                g[0][0].getLongitude(),
                                                g[g[0].length - 1][g[0].length - 1].getLatitude(),
                                                g[g[0].length - 1][g[0].length - 1].getLongitude()));
                            }
                            new BuoyageView(s57Layer).display(buoyages, 1.0);
                            if (generateKmlCB.isSelected()) {
                                File src = new File(System.getProperty("user.dir") + SEP + "data" + SEP + "collada" + SEP + "buoys");
                                File dest = new File(System.getProperty("user.dir") + SEP + "privateData" + SEP + "kml" + SEP + "buoys");
                                try {
                                    FileUtils.copyDirectory(src, dest);
                                } catch (IOException ex) {
                                    Logger.getLogger(StlDBComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                                }
                                new BuoyageExportKML(kmlFileNames.get(k)).export(buoyages, maxDepth + tileSideZ);
                            }
                            i = k / tileCount + 1;
                            j = k % tileCount + 1;
                            String filename = DEFAULT_STL_PATH + outFileTF.getText() + "_" + i + "," + j + ".stl";
                            scaleCompute(g);
                            BuoyageExportToSTL buoyageExportSTL = new BuoyageExportToSTL(geodesyServices, g, filename, latScale, lonScale);
                            buoyageExportSTL.export(buoyages, maxDepth + tileSideZ);
                            k++;
                        }

                    }
                    k = 0;
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("LNDMRK")) {
                        landmarks.clear();
                        for (Point3D[][] g : grids) {
                            LOGGER.info("In export LNDMRK en STL");
                            landmarks.clear();
                            landmarks.addAll(new LandmarkDBLoader(topologyServices, s57Connection, marsys)
                                    .retrieveObjectsIn(g[0][0].getLatitude(),
                                            g[0][0].getLongitude(),
                                            g[g[0].length - 1][g[0].length - 1].getLatitude(),
                                            g[g[0].length - 1][g[0].length - 1].getLongitude()));

                            new LandmarkView(s57Layer).display(landmarks);
                            i = k / tileCount + 1;
                            j = k % tileCount + 1;
                            String filename = DEFAULT_STL_PATH + outFileTF.getText() + "_" + i + "," + j + ".stl";
                            scaleCompute(g);
                            new LandmarkExportToSTL(geodesyServices, g, filename, latScale, lonScale)
                                    .export(landmarks, maxDepth, tileSideZ);
                            k++;
                        }

                    }
                    // DAE
                    k = 0;
                    if (boundList != null && !boundList.isEmpty()) {

                        System.out.println("boundList : " + boundList);
                        System.out.println(geodesyServices.getAzimuth(boundList.get(0).getLatitude(), boundList.get(0).getLongitude(), boundList.get(1).getLatitude(), boundList.get(1).getLongitude()));
                        System.out.println(geodesyServices.getAzimuth(boundList.get(1).getLatitude(), boundList.get(1).getLongitude(), boundList.get(2).getLatitude(), boundList.get(2).getLongitude()));
                        System.out.println(geodesyServices.getAzimuth(boundList.get(2).getLatitude(), boundList.get(2).getLongitude(), boundList.get(3).getLatitude(), boundList.get(3).getLongitude()));
                        System.out.println(geodesyServices.getAzimuth(boundList.get(3).getLatitude(), boundList.get(3).getLongitude(), boundList.get(4).getLatitude(), boundList.get(4).getLongitude()));
                        // System.out.println("grids : "+ grids);
                        for (Point3D[][] g : grids) {
                            System.out.println("g : " + g);
                            objects.clear();
                            i = k / tileCount + 1;
                            j = k % tileCount + 1;
                            String filename = DEFAULT_STL_PATH + outFileTF.getText() + "_" + i + "," + j + ".stl";
                            scaleCompute(g);
                            meshExportToSTL.export(g, filename, latScale, lonScale, tileSideZ, maxDepth);
                            k++;
                        }
                    }
                    k = 0;
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("SLCONS")) {
                        for (Point3D[][] g : grids) {
                            LOGGER.info("In export SLCONS en STL");
                            objects.clear();
                            i = k / tileCount + 1;
                            j = k % tileCount + 1;
                            Pair pair = new Pair(i, j);

                            double latitudeMin = g[0][0].getLatitude();
                            double longitudeMin = g[0][0].getLongitude();
                            double latitudeMax = g[g[0].length - 1][g[0].length - 1].getLatitude();
                            double longitudeMax = g[g[0].length - 1][g[0].length - 1].getLongitude();

                            String filename = DEFAULT_STL_PATH + outFileTF.getText() + "_" + i + "," + j + ".stl";
                            scaleCompute(g);
                            objects = new ShorelineConstructionDBLoader(s57Connection)
                                    .retrieveObjectsIn(latitudeMin, longitudeMin, latitudeMax, longitudeMax);
                            List<? extends Geo> clippedObjects = topologyServices.clip(objects, latitudeMin, longitudeMin, latitudeMax, longitudeMax);

                            SlConsView slConsView = new SlConsView(jtsServices, geodesyServices, s57Layer);
                            slConsView.display(clippedObjects);

                            SLConsExportToSTL slConsExportToSTL = new SLConsExportToSTL(jtsServices, geodesyServices,
                                    filename,
                                    latitudeMin, longitudeMin,
                                    latScale, lonScale,
                                    maxDepth + tileSideZ);
                            slConsExportToSTL.export(clippedObjects);

                            //TODO export KML
                            filename = DEFAULT_KML_PATH + outFileTF.getText() + "_" + i + "," + j + ".kml";
                            //    new SlConsExportKML(topologyServices).export(filename, StandardOpenOption.APPEND, objects, 50.0);
                            k++;
                        }

                    }
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("PONTON")) {
                        objects = new PontoonDBLoader(s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        new S57ObjectView("PONTON", topologyServices, s57Layer).display(objects);
                        objects.forEach((g) -> {
                            s57ObjectsExport = new S57ObjectsExportToSTL(topologyServices, stlComponentServices, jtsServices,
                                    lat0, lon0, latScale, lonScale, tileSideZ);
                            s57ObjectsExport.export(g);
                        });
                    }

                    if (selectedObjects.contains("ALL") || selectedObjects.contains("ACHARE")) {
                        objects = new AnchorageAreaDBLoader(topologyServices, s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        s57Viewer = new S57ObjectView("ACHARE", topologyServices, s57Layer);
                        objects.forEach((g) -> {
                            s57Viewer.display(g);
                        });

                    }
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("COALNE")) {
                        objects = new CoastlineDBLoader(s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        s57Viewer = new S57ObjectView("COALNE", topologyServices, s57Layer);
                        objects.forEach((g) -> {
                            s57Viewer.display(g);
                        });
                    }
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("DEPCNT")) {
                        objects = new DepthContourDBLoader(s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        s57Viewer = new S57ObjectView("DEPCNT", topologyServices, bathymetryLayer);
                        objects.forEach((g) -> {
                            s57Viewer.display(g);

                        });
                    }
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("DOCARE")) {
                        objects = new DockAreaDBLoader(s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        s57Viewer = new S57ObjectView("DOCARE", topologyServices, bathymetryLayer);
                        objects.forEach((g) -> {
                            s57Viewer.display(g);
                        });
                    }
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("DRGARE")) {
                        objects = new DredgedAreaDBLoader(s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        s57Viewer = new S57ObjectView("DRGARE", topologyServices, bathymetryLayer);
                        objects.forEach((g) -> {
                            s57Viewer.display(g);
                        });
                    }

                    if (selectedObjects.contains("ALL") || selectedObjects.contains("LIGHTS")) {

                        List<Light> lights = new LightDBLoader(topologyServices, s57Connection, marsys)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        new LightView(lightsLayer).display(lights);
                    }
                    if (selectedObjects.contains("ALL") || selectedObjects.contains("NAVLNE")) {
                        objects = new NavigationLineDBLoader(s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        s57Viewer = new S57ObjectView("NAVLNE", topologyServices, s57Layer);
                        objects.forEach((g) -> {
                            s57Viewer.display(g);
                        });
                    }

                    if (selectedObjects.contains("ALL") || selectedObjects.contains("RESARE")) {
                        objects = new RestrictedAreaDBLoader(s57Connection)
                                .retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
                        s57Viewer = new S57ObjectView("RESARE", topologyServices, s57Layer);
                        objects.forEach((g) -> {
                            normalAttributes.setOutlineMaterial(new Material(new Color(197, 69, 195)));
                            s57Viewer.display(g, normalAttributes, highlightAttributes);
                        });
                    }
                    k = 0;
                    if (stlPreviewCB.isSelected()) {
                        for (Point3D[][] g : grids) {
                            i = k / tileCount + 1;
                            j = k % tileCount + 1;
                            String filename = outFileTF.getText() + "_" + i + "," + j + ".stl";
                            filename = System.getProperty("user.dir") + File.separator + "privateData" + File.separator + "stl" + File.separator + filename;
                            try {
                                Thread.sleep(1000);
                                stlComponentServices.viewSTL(filename);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(StlDBComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                            }
                            k++;
                        }
                    }
                    instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                    selectLayer.removeAllRenderables();
                    wwd.redrawNow();
                }
                LOGGER.info("Out export in STL");
            }

        });

    }

    /*
    Create a list of points on a regular grid.
     */
    private List<Point3D[][]> createElevationTab(double latMin, double lonMin, double latMax, double lonMax) {
        elevationConnection = databaseServices.connect(elevationDatabaseTF.getText(), HOST, PROTOCOL, PORT, DRIVER, USER, PASSWD);
        DEM dem = new DemDbLoader(elevationConnection, demDBServices).retrieveIn(latMin - RETRIEVE_OFFSET,
                lonMin - RETRIEVE_OFFSET, latMax + RETRIEVE_OFFSET, lonMax + RETRIEVE_OFFSET);
        maxDepth = 0.0;
        return createGrids(dem, latMin, lonMin, latMax, lonMax);
    }

    private List<Point3D[][]> createSrtmElevationTab(double latMin, double lonMin,
            double latMax, double lonMax, double range) {
        LOGGER.info("In createSrtmElevationTab in STL");
        Point3D[][] points = new DemSrtmElevationLoader(geodesyServices).getElevations(latMin, lonMin, latMax, lonMax, range);
        maxDepth = 0.0;
        LOGGER.info("Out createSrtmElevationTab in STL");

        List<Point3D[][]> gds = createGrids(points, tileCount);

        return gds;
    }

    /*
    Create a list of points on a regular grid.
    These grids are translate vertically with the max of depth
     */
    private List<Point3D[][]> createBathymetryTab(double latMin, double lonMin, double latMax, double lonMax) {
        bathyConnection = databaseServices.connect(bathyDatabaseTF.getText(), HOST, PROTOCOL, PORT, DRIVER, USER, PASSWD);
        DEM dem = new BathyLoader(bathyConnection, bathymetryDBServices).retrieveIn(latMin - RETRIEVE_OFFSET,
                lonMin - RETRIEVE_OFFSET, latMax + RETRIEVE_OFFSET, lonMax + RETRIEVE_OFFSET);
       // System.out.println("dem : "+dem.getGrid().size());
        maxDepth = dem.getMaxElevation();
        // Offset from maxDepth
        System.out.println("maxDepth : " + maxDepth);
        
        List<Triangle_dt> tri = delaunayServices.createDelaunay(dem.getGrid());
        List<Triangle_dt> tri2 = delaunayServices.filterLargeEdges(tri, 0.001);
        displayServices.displayDelaunay(tri2, maxDepth, 10, Material.WHITE, s57Layer);
        //  displayServices.displayPoints3DAsTriangles(dem.getGrid(), s57Layer, Material.MAGENTA);
        return createGrids(dem, latMin, lonMin, latMax, lonMax);
    }

    /*
    Create a list of points on a regular grid.
    These grids are translate vertically with the max of depth
     */
    private List<Point3D[][]> createBathymetryAndElevationTab(double latMin, double lonMin, double latMax, double lonMax) {

        bathyConnection = databaseServices.connect(bathyDatabaseTF.getText(), HOST, PROTOCOL, PORT, DRIVER, USER, PASSWD);
        DEM bathy = new BathyLoader(bathyConnection, bathymetryDBServices).retrieveIn(latMin - RETRIEVE_OFFSET,
                lonMin - RETRIEVE_OFFSET, latMax + RETRIEVE_OFFSET, lonMax + RETRIEVE_OFFSET);
        maxDepth = bathy.getMaxElevation();
        // Offset from maxDepth
        List<Point3D> bathyElevations = new ArrayList<>();
        bathy.getGrid().forEach((p) -> {
            bathyElevations.add(new Point3D(p.getLatitude(), p.getLongitude(), maxDepth - p.getElevation()));
        });

        elevationConnection = databaseServices.connect(elevationDatabaseTF.getText(), HOST, PROTOCOL, PORT, DRIVER, USER, PASSWD);
        DEM alti = new DemDbLoader(elevationConnection, demDBServices).retrieveIn(latMin - 0.1, lonMin - 0.1, latMax + 0.1, lonMax + 0.1);
        // Offset from maxDepth
        alti.getGrid().forEach((p) -> {
            bathyElevations.add(new Point3D(p.getLatitude(), p.getLongitude(), maxDepth + p.getElevation()));
        });

        // Merge all alti and bathy points
        Point3D[][] grid = delaunayServices.toGridTab(latMin, lonMin, latMax, lonMax, gridY, gridX, maxDepth);

        grid = jtsServices.mergePointsToGrid(bathyElevations, grid);

        List<Point3D[][]> grids = createGrids(grid, tileCount);
        return grids;
    }

    private List<Point3D[][]> createGrids(DEM dem, double latMin, double lonMin, double latMax, double lonMax) {
        /*
        // Si export vers ASC
        Position orig = new Position(Angle.fromDegrees(latMin), Angle.fromDegrees(lonMin), 0);
        Position p0 = geodesyServices.getPosition(orig, 90, gridX);
        Position p1 = geodesyServices.getPosition(orig, 0, gridY);

        Double y = Math.abs(Math.abs(orig.getLatitude().getDegrees()) - Math.abs(p1.getLatitude().getDegrees()));
        double x = Math.abs(Math.abs(orig.getLongitude().getDegrees()) - Math.abs(p0.getLongitude().getDegrees()));
        double yy = orig.getLatitude().getDegrees() + y;
        double xx = orig.getLongitude().getDegrees() - y;

        gridY = geodesyServices.getDistanceM(orig.getLatitude().getDegrees(), orig.getLongitude().getDegrees(),
                yy, orig.getLongitude().getDegrees());
        gridX = geodesyServices.getDistanceM(orig.getLatitude().getDegrees(), orig.getLongitude().getDegrees(),
                orig.getLatitude().getDegrees(), xx);
         */

        Point3D[][] grid = delaunayServices.toGridTab(latMin, lonMin, latMax, lonMax, gridY, gridX, maxDepth);
        grid = jtsServices.mergePointsToGrid(dem.getGrid(), grid);
        List<Point3D[][]> grids = createGrids(grid, tileCount);
        return grids;
    }

    private List<Point3D[][]> createGrids(Point3D[][] grid, int tileCount) {
        LOGGER.info("In createGrids in STL");
        List<Point3D[][]> grids = new ArrayList<>();
        if (tileCount == 1) {
            grids.add(grid);
        } else {
            int bound = grid[0].length / tileCount;
            if (grid[0].length <= tileCount * bound) {
                Point3D[][] tmpTab = Point3D.suppress(grid, grid[0].length - 1);
                grid = Point3D.copy(tmpTab);
                bound = grid[0].length / tileCount;
            }
            for (int l = 0; l < tileCount; l++) {
                for (int c = 0; c < tileCount; c++) {
                    Point3D[][] realGrid = new Point3D[bound + 1][bound + 1];
                    for (int i = 0; i < bound + 1; i++) {
                        for (int j = 0; j < bound + 1; j++) {
                            realGrid[i][j] = new Point3D(grid[i + l * bound][j + c * bound].getLatitude(),
                                    grid[i + l * bound][j + c * bound].getLongitude(),
                                    grid[i + l * bound][j + c * bound].getElevation());
                        }
                    }
                    grids.add(realGrid);
                }
            }
        }
        LOGGER.info("Out createGrids in STL");
        return grids;
    }

    private void scaleCompute(Point3D[][] grid) {
        double realLatMin = grid[0][0].getLatitude();
        double realLonMin = grid[0][0].getLongitude();

        double realLatMax = grid[grid[0].length - 1][0].getLatitude();
        double realLonMax = grid[1][grid[1].length - 1].getLongitude();
        latRangeMetric = geodesyServices.getDistanceM(realLatMin, realLonMin, realLatMax, realLonMin);
        lonRangeMetric = geodesyServices.getDistanceM(realLatMin, realLonMin, realLatMin, realLonMax);
        latScale = tileSideY / latRangeMetric;
        lonScale = tileSideX / lonRangeMetric;
        Platform.runLater(() -> {
            rangeLatTF.setText(Integer.toString((int) latRangeMetric));
            rangeLonTF.setText(Integer.toString((int) lonRangeMetric));
        });

    }

    private Point3D[][] createGridFromDelaunayBathymetry(DEM bathymetry,
            double latMin, double lonMin, double latMax, double lonMax, double elevation) {
        List<Triangle_dt> triangles = delaunayServices.createDelaunay(bathymetry.getGrid(), Math.round(bathymetry.getMaxElevation()));
        Point3D[][] pts = delaunayServices.toGridTab(latMin, lonMin, latMax, lonMax, 100, 100, Math.round(bathymetry.getMaxElevation()));

        displayServices.displayDelaunay(triangles, initX, verticalExaggeration, Material.YELLOW, s57Layer);

        return null;
    }

    private void makeAttributes() {
        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.RED);
        normalAttributes.setOutlineOpacity(0.5);
        normalAttributes.setOutlineWidth(1);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setOutlineMaterial(new Material(Color.WHITE));
    }

    private void initSelectedZone() {
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Create Area");
        dialog.setHeaderText("Please enter selected area coordinates.");
        dialog.setResizable(false);

        Label lat0Label = new Label("Southwest point latitude : ");
        Label lon0Label = new Label("Southwest point longitude : ");
        Label lat1Label = new Label("Northeast point latitude : ");
        Label lon1Label = new Label("Northeast point longitude : ");

        TextField lat0TF = new TextField();
        TextField lat1TF = new TextField();
        TextField lon0TF = new TextField();
        TextField lon1TF = new TextField();

        //Default values
        lat0TF.setText(LAT_MIN);
        lat1TF.setText(LAT_MAX);
        lon0TF.setText(LON_MIN);
        lon1TF.setText(LON_MAX);

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
                alert.setHeaderText("Range longitude : -180° <= Longitude <= 180°");
                alert.show();
            }
            if (lon1 < lon0 || lat1 < lat0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Right inputs are LatMin, LonMin & LatMax, LonMax");
                alert.show();
            }

            double latRan = geodesyServices.getDistanceM(lat0, lon0, lat1, lon0);
            double lonRan = geodesyServices.getDistanceM(lat0, lon0, lat0, lon1);
            if (latRan > lonRan) {
                Position position = geodesyServices.getPosition(lat1, lon0, 90.0, latRan);
                lon1 = position.getLongitude().getDegrees();
            } else {
                Position position = geodesyServices.getPosition(lat0, lon1, 0.0, lonRan);
                lat1 = position.getLatitude().getDegrees();
            }
            event.consume();
            dialog.close();
            latMinLabel.setText(Double.toString(lat0));
            lonMinLabel.setText(Double.toString(lon0));
            latMaxLabel.setText(Double.toString(lat1));
            lonMaxLabel.setText(Double.toString(lon1));
            LAT_MIN = Double.toString(lat0);
            LON_MIN = Double.toString(lon0);
            LAT_MAX = Double.toString(lat1);
            LON_MAX = Double.toString(lon1);
            OutputStream output = null;
            Properties properties = new Properties();
            try {
                output = new FileOutputStream(CACHE_FILE_NAME);
                properties.setProperty("LAT_MIN", LAT_MIN);
                properties.setProperty("LON_MIN", LON_MIN);
                properties.setProperty("LAT_MAX", LAT_MAX);
                properties.setProperty("LON_MAX", LON_MAX);
                properties.store(output, null);
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(StlDBComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            selectedPolygons.addAll(stlGuiController.createAndDisplayTiles(s57Layer, Material.RED, 100, lat0, lon0, lat1, lon1, tileCount, tileCount));
        });

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
    }

    public Connection getConnection() {
        return s57Connection;
    }

    private String startCmd(String command) {
        String cmd = null;
        if (OS.isWindows()) {
            cmd = "gdal\\win\\" + command;
        } else if (OS.isLinux()) {
            cmd = configProperties.getProperty("gdalPath") + "/" + command;
        } else {
            System.out.println("OS not found");
        }
        return cmd;
    }

    private void createElevationAndDepare(double latMin, double lonMin, double latMax, double lonMax) {
        // bathymetry = createBathymetry(latMin, lonMin, latMax, lonMax);
        Point3D[][] ptsTab = createGridFromDelaunayBathymetry(bathymetry, latMin, lonMin, latMax, lonMax, 0.0);
        displayServices.displayGrid(ptsTab, s57Layer, Material.GREEN, verticalExaggeration);

        Shapefile shapefile = new DepareDBLoader(databaseServices, s57DatabaseTF.getText(), USER, PASSWD)
                .retrieveIn(latMin, lonMin, latMax, lonMax);

        new DepareView(s57Layer, s57Layer, s57Layer,
                simplifyFactor,
                Math.round(bathymetry.getMaxElevation()), verticalExaggeration,
                true, true)
                .display(shapefile);
    }

    public Point3D[][] resample(String filename, Point3D[][] grid) {
        //  System.out.println("grid : " + grid[0][0] + " " + grid[grid[0].length - 1][grid[1].length - 1]);
        displayServices.exportASC(filename, grid);

        String tmpTif = filename.replace(".asc", ".tif");
        String tmpAsc = filename.replace(".asc", "1.asc");
        String command = startCmd("gdalwarp");
        command += " -overwrite"
                + " -s_srs EPSG:4326 -t_srs EPSG:4326 "
                + " -r cubicspline "
                + " -ts 300 300 "
                + " -te " + grid[0][0].getLongitude() + " "
                + grid[0][0].getLatitude() + " "
                + grid[grid[0].length - 1][grid[1].length - 1].getLongitude() + " "
                + grid[grid[0].length - 1][grid[1].length - 1].getLatitude() + " "
                + filename + " "
                + tmpTif;
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

        command = startCmd("gdal_translate");
        command += " -of AAIGrid "
                + tmpTif + " "
                + tmpAsc;
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return displayServices.importASC(tmpAsc);
        // return displayServices.importASC(path.toString());

    }

    private void createElevationAndUlhyssesDepare(double latMin, double lonMin, double latMax, double lonMax) {
        //  bathymetry = createBathymetryTab(latMin, lonMin, latMax, lonMax);
        Point3D[][] pts = createGridFromDelaunayBathymetry(bathymetry, latMin, lonMin, latMax, lonMax, 0.0);
        displayServices.displayGrid(pts, s57Layer, Material.GREEN, verticalExaggeration);

        List<Point3D> points = bathymetry.getGrid();
        bathymetryDBServices.writePointList(points, Paths.get(System.getProperty("user.dir") + "/privateData/ulhysses", "bathy.csv"), false);

        String ulhyssesPath = "" + sep + "opt" + sep + "ULHYSSES" + sep + "app";
        String command
                = "cd " + ulhyssesPath + " \n"
                + System.getProperty("java.home") + sep + "bin" + sep + "java "
                + "-Dlog4j.configuration=file:" + ulhyssesPath + "" + sep + "conf-tools" + sep + "toolsLog4j.properties "
                + "-Xmx14g -Xms1024m -jar " + ulhyssesPath + "" + sep + "" + "ULHYSSES.jar "
                + "--outputDirectory=" + System.getProperty("user.dir") + "" + sep + "privateData" + sep + "ulhysses "
                + "--inputFile=" + System.getProperty("user.dir") + "" + sep + "privateData" + sep + "ulhysses" + sep + "bathy.csv "
                + "--compilationScale=1000 --fileType=0 --isoValues='" + isoValuesUlhysses + "' "
                + "--codeAgency=4G --baseName=0001";
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

        new File("data" + sep + "shp").mkdir();
        new File("data" + sep + "shp" + sep + "shp_0").mkdir();
        String cmd0 = startCmd("ogr2ogr");
        String cmd;
        String sep = File.separator;
        try {
            java.nio.file.Path tmp = Paths.get(new File(System.getProperty("user.dir") + sep + "privateData" + sep + "ulhysses" + sep + "bathy" + sep + "ENC" + sep + "4G600010.000").toString());
            cmd = cmd0 + " -skipfailures -overwrite " + "data" + sep + "shp" + sep + "shp_0" + " " + tmp.toString();
            Proc.BUILDER.create()
                    .setCmd(cmd)
                    .execSh();
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        Shapefile shp = new Shapefile(new File("data" + sep + "shp" + sep + "shp_0" + sep + "DEPARE.shp"));

        new DepareView(s57Layer, s57Layer, s57Layer,
                simplifyFactor,
                Math.round(bathymetry.getMaxElevation()), verticalExaggeration,
                true, true)
                .display(shp);
    }

}
