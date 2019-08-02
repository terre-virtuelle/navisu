/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.tools.impl.controller;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.LambertServices;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.GeoTiffChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.tools.impl.ToolsComponentImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import bzh.terrevirtuelle.navisu.dem.db.DemDBServices;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.geo.raster.RasterServices;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.paysBrest.ObjPaysbrestLoader;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author serge
 * @date May 21, 2016
 *
 */
public class ToolsComponentController
        extends Widget2DController
        implements Initializable {

    protected static final String SEP = File.separator;
    private final ToolsComponentImpl component;
    protected static final Logger LOGGER = Logger.getLogger(ToolsComponentController.class.getName());

    protected GuiAgentServices guiAgentServices;
    protected S57ChartComponentServices s57ChartComponentServices;
    protected GeoTiffChartServices geoTiffChartServices;
    protected DatabaseServices databaseServices;
    protected BathymetryDBServices bathymetryDBServices;
    protected DemDBServices demDBComponentServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected LambertServices lambertServices;
    protected RasterServices rasterServices;

    protected GeodesyServices geodesyServices;
    protected JTSServices jtsServices;
    protected Pro4JServices pro4JServices;
    protected ObjComponentServices objComponentServices;
    protected DisplayServices displayServices;
    protected LayersManagerServices layersManagerServices;
    protected TopologyServices topologyServices;

    private final String FXML = "toolsController.fxml";

    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected static final String ALARM_SOUND = SEP + "data" + SEP + "sounds" + SEP + "pling.wav";

    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected static final String USER_DIR = System.getProperty("user.dir");

    private final String USER = "admin";
    private final String PASSWD = "admin";
    protected Properties properties;

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + SEP + "css" + SEP).toUri().toString();
    protected String viewgroupstyle = "configuration.css";

    /* Common controls */
    @FXML
    public Group view;
    @FXML
    public Pane viewPane;
    @FXML
    public TabPane databaseTabPane;
    @FXML
    public Button quit;

    @FXML
    public Button helpButton;

    /* ENC controls */
    @FXML
    public Tab s57Tab;
    @FXML
    public TextField s57TF;
    @FXML
    public TextField psqlTF;
    @FXML
    public TextField gdalTF;
    @FXML
    public TextField ulhyssesTF;
    @FXML
    public TextField countryTF;
    @FXML
    public TextField catalogTF;
    @FXML
    public TextField s57DatabaseTF;
    @FXML
    public Button s57Button;
    @FXML
    public Button psqlButton;
    @FXML
    public Button gdalButton;
    @FXML
    public Button ulhyssesButton;
    @FXML
    public ChoiceBox<String> catalogCB;
    @FXML
    public ChoiceBox<String> countryCB;
    @FXML
    public Button createS57Button;
    @FXML
    public Button insertS57Button;

    protected String s57Path;
    protected String psqlPath;
    protected String gdalPath;
    protected String ulhyssesPath;
    protected String countryPath;
    protected String s7DataBaseName;
    protected String beaconsDatabaseName;

    /* Bathy controls */
    @FXML
    public Tab bathyTab;
    @FXML
    public TextField psqlTF1;
    @FXML
    public Button psqlButton1;
    @FXML
    public ChoiceBox<String> bathyDbCB;
    @FXML
    public TextField bathyDataTF;
    @FXML
    public Button bathyDataButton;
    @FXML
    public TextField bathyDatabaseNameTF;
    @FXML
    public Button createBathyButton;
    @FXML
    public Button insertBathyButton;

    protected String bathyData;

    /* Elevations controls */
    @FXML
    public Tab elevationsTab;
    @FXML
    public TextField psqlTF11;
    @FXML
    public Button psqlButton11;
    @FXML
    public TextField elevationDataTF;
    @FXML
    public TextField elevationDataDirTF;
    @FXML
    public Button elevationDataButton;
    @FXML
    public Button elevationDataDirButton;
    @FXML
    public TextField elevationDatabaseNameTF;
    @FXML
    public Button createElevationButton;
    @FXML
    public Button insertElevationButton;
    @FXML
    public ChoiceBox<String> elevationDbCB;
    @FXML
    public CheckBox tiff2XyzCB;
    @FXML
    public CheckBox lambert2Wgs84CB;
    @FXML
    public CheckBox geotifPreviewCB;
    @FXML
    public RadioButton dem30mRB;
    @FXML
    public RadioButton dem5mRB;
    @FXML
    public RadioButton dem1mRB;

    /* Beacons controls */
    @FXML
    public Tab beaconsTab;
    @FXML
    public TextField psqlTF12;
    @FXML
    public Button psqlButton12;
    @FXML
    public ChoiceBox<String> beaconsDbCB;
    @FXML
    public TextField beaconsDataTF;
    @FXML
    public Button beaconsDataButton;
    @FXML
    public TextField beaconsDatabaseNameTF;
    @FXML
    public Button createBeaconsButton;
    @FXML
    public Button insertBeaconsButton;

    /*Buildings controls */
    @FXML
    public Tab buildingsTab;
    @FXML
    public ChoiceBox<String> buildingsDbCB;
    @FXML
    public TextField buildingsDataDirTF;
    @FXML
    public Button buildingsDataButton;
    @FXML
    public TextField buildingsDatabaseNameTF;
    @FXML
    public Button createBuildingsButton;
    @FXML
    public Button insertBuildingsButton;
    @FXML
    public RadioButton previewBuildingsRB;

    protected String buildingsData;
    protected String elevationData;
    protected boolean isDataDir = false;

    private ObservableList<String> catalogCbData = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
    private ObservableList<String> countryCbData = FXCollections.observableArrayList("FR", "ALL", "CA", "DE", "KR", "NO", "PE",
            "PH", "PT", "RU", "TR", "US", "ZA");
    private ObservableList<String> dbCbElevationData = FXCollections.observableArrayList("Choice DB", "IGN75m", "SRTM30m",
            "BrestMetropole5mDB", "BrestMetropole1mDB", "Finistere5mDB",
            "Litto3D5m", "Litto3D1m", "TestAltiDB");
    private ObservableList<String> dbCbBathyData = FXCollections.observableArrayList("Choice DB", "BathyShomDB", "TestDB");
    private ObservableList<String> dbCbBeaconsData = FXCollections.observableArrayList("Choice DB", "BalisageMaritimeDB");
    private ObservableList<String> dbCbBuildingsData = FXCollections.observableArrayList("Choice DB", "BuildingsPaysbrestDB");

    final ToggleGroup mntGroup = new ToggleGroup();

    protected FileChooser fileChooser;

    private final String COMPONENT_KEY_NAME_0 = "DbS57";
    private final String COMPONENT_KEY_NAME_1 = "DbBathy";
    private final String COMPONENT_KEY_NAME_2 = "DbElevation";
    private final String COMPONENT_KEY_NAME_3 = "DbBeacons";
    private final String COMPONENT_KEY_NAME_4 = "DbBuildings";
    private final String ENC_CATALOG_HOME = "data/charts/vector/s57/catalog/";
    private final String BATHY_DB_NAME_0 = "BathyShomDB";
    private final String BATHY_DB_NAME_1 = "TestDB";
    private final String ELEVATION_DB_NAME_0 = "AltiV2_2-0_75mIgnDB";
    private final String ELEVATION_DB_NAME_1 = "SRTM30mDB";
    private final String ELEVATION_DB_NAME_2 = "TestAltiDB";
    private final String ELEVATION_DB_NAME_3 = "Litto3D5mDB";
    private final String ELEVATION_DB_NAME_4 = "Litto3D1mDB";
    private final String ELEVATION_DB_NAME_5 = "BrestMetropole5mDB";
    private final String ELEVATION_DB_NAME_6 = "BrestMetropole1mDB";
    private final String ELEVATION_DB_NAME_7 = "Finistere5mDB";
    private final String BUILDINGS_DB_NAME_0 = "BuildingsPaysBrestDB";
    private final String GROUP_0 = "S57 charts";
    protected static final String S57_LAYER = "S57";

    private final String ELEVATION_DB_ORG_DIR = "privateData" + SEP + "elevation";
    private final String BEACONS_DB_NAME_0 = "BalisageMaritimeDB";
    private String componentKeyName;
    private List<File> selectedFiles;
    protected String mnt = "MNT5m";
    protected boolean isCreate = false;
    protected RenderableLayer s57Layer;
    boolean isTable0Created = false;
    boolean isTable1Created = false;

    /**
     *
     * @param component
     * @param componentKeyName
     * @param keyCode
     * @param keyCombination
     * @param guiAgentServices
     * @param s57ChartComponentServices
     * @param geoTiffChartServices
     * @param databaseServices
     * @param bathymetryDBServices
     * @param demDBComponentServices
     * @param instrumentDriverManagerServices
     * @param lambertServices
     * @param rasterServices
     * @param geodesyServices
     * @param jtsServices
     * @param pro4JServices
     * @param objComponentServices
     * @param displayServices
     * @param layersManagerServices
     */
    @SuppressWarnings("unchecked")
    public ToolsComponentController(ToolsComponentImpl component, String componentKeyName,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            S57ChartComponentServices s57ChartComponentServices,
            GeoTiffChartServices geoTiffChartServices,
            DatabaseServices databaseServices,
            BathymetryDBServices bathymetryDBServices,
            DemDBServices demDBComponentServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            LambertServices lambertServices,
            RasterServices rasterServices,
            GeodesyServices geodesyServices,
            JTSServices jtsServices,
            Pro4JServices pro4JServices,
            ObjComponentServices objComponentServices,
            DisplayServices displayServices,
            LayersManagerServices layersManagerServices,
            TopologyServices topologyServices
    ) {
        super(keyCode, keyCombination);
        this.componentKeyName = componentKeyName;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ToolsComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.s57ChartComponentServices = s57ChartComponentServices;
        this.geoTiffChartServices = geoTiffChartServices;
        this.databaseServices = databaseServices;
        this.bathymetryDBServices = bathymetryDBServices;
        this.demDBComponentServices = demDBComponentServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.lambertServices = lambertServices;
        this.rasterServices = rasterServices;
        this.geodesyServices = geodesyServices;
        this.jtsServices = jtsServices;
        this.pro4JServices = pro4JServices;
        this.objComponentServices = objComponentServices;
        this.displayServices = displayServices;
        this.layersManagerServices = layersManagerServices;
        this.topologyServices = topologyServices;

        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
        guiAgentServices.getRoot().getChildren().add(this);

        s57Layer = layersManagerServices.getLayer(GROUP_0, S57_LAYER);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* Init s57Tab and databaseTab 
           Values are store in CONFIG_FILE_NAME 
           properties file
         */
        switch (componentKeyName) {
            case COMPONENT_KEY_NAME_0:
                databaseTabPane.getSelectionModel().select(s57Tab);
                break;
            case COMPONENT_KEY_NAME_1:
                databaseTabPane.getSelectionModel().select(bathyTab);
                break;
            case COMPONENT_KEY_NAME_2:
                databaseTabPane.getSelectionModel().select(elevationsTab);
                break;
            case COMPONENT_KEY_NAME_3:
                databaseTabPane.getSelectionModel().select(beaconsTab);
                break;
            case COMPONENT_KEY_NAME_4:
                databaseTabPane.getSelectionModel().select(buildingsTab);
                break;
        }

        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(ToolsComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        String p = properties.getProperty("s57ChartsDir");
        if (p != null) {
            s57TF.setText(p);
        }
        p = properties.getProperty("psqlPath");
        if (p != null) {
            psqlTF.setText(p);
            psqlTF1.setText(p);
            psqlTF11.setText(p);
        }
        p = properties.getProperty("gdalPath");
        if (p != null) {
            gdalTF.setText(p);
        }
        p = properties.getProperty("ulhyssesPath");
        if (p != null) {
            ulhyssesTF.setText(p);
        }
        p = properties.getProperty("bathyData");
        if (p != null) {
            bathyDataTF.setText(p);
        }
        try {
            properties.store(new FileOutputStream(CONFIG_FILE_NAME), null);
        } catch (IOException ex) {
            Logger.getLogger(ToolsComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        /* ENC controls */
        catalogCB.setItems(catalogCbData);
        catalogCB.getSelectionModel().select("5");
        catalogTF.setText("ENC_NP5.kml");
        s57DatabaseTF.setText("s57NP5DB");
        s57DatabaseTF.setEditable(false);

        catalogCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable,
                        String oldValue, String newValue) -> {
                    catalogTF.setText("ENC_NP" + newValue + ".kml");
                    s7DataBaseName = "s57NP" + newValue + "DB";
                    s57DatabaseTF.setText(s7DataBaseName);
                });

        countryCB.setItems(countryCbData);
        countryCB.getSelectionModel().select("FR");
        countryTF.setText("FR");
        countryCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable,
                        String oldValue, String newValue) -> {
                    countryTF.setText(newValue);
                });

        beaconsDbCB.setItems(dbCbBeaconsData);
        beaconsDbCB.getSelectionModel().select("Choice DB");
        beaconsDbCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable,
                        String oldValue, String newValue) -> {
                    beaconsDatabaseName = newValue;
                    beaconsDatabaseNameTF.setText(beaconsDatabaseName);
                });
        createBeaconsButton.setOnMouseClicked((MouseEvent event) -> {
            if (beaconsTab.isSelected()) {
                psqlPath = psqlTF.getText();
                gdalPath = gdalTF.getText();
            }
            guiAgentServices.getJobsManager().newJob("Load DB : " + beaconsDatabaseName, (progressHandle) -> {
                String shpDir = beaconsDataTF.getText();
                System.out.println("shpDir : " + shpDir);
                String sqlDir = databaseServices.shapeFileToSql(psqlPath, shpDir, "4326");
                databaseServices.sqlToSpatialDB(beaconsDatabaseName, USER, PASSWD, sqlDir, psqlTF.getText() + "/psql");
                instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
            });
        });

        quit.setOnMouseClicked((MouseEvent event) -> {
            component.off();
        });

        createS57Button.setOnMouseClicked((MouseEvent event) -> {
            if (s57Tab.isSelected()) {
                s57Path = s57TF.getText();
                psqlPath = psqlTF.getText();
                gdalPath = gdalTF.getText();
                ulhyssesPath = ulhyssesTF.getText();
            }
            s7DataBaseName = s57DatabaseTF.getText();

            guiAgentServices.getJobsManager().newJob("Load DB : " + s7DataBaseName, (progressHandle) -> {
                String shpDir = s57ChartComponentServices.s57FromCatalogToShapeFile(s57TF.getText(),
                        ENC_CATALOG_HOME + catalogTF.getText(),
                        countryTF.getText(),
                        "4326");
                String sqlDir = databaseServices.shapeFileToSql(psqlPath, shpDir, "4326");
                databaseServices.sqlToSpatialDB(s7DataBaseName, USER, PASSWD, sqlDir, psqlTF.getText() + "/psql");
                instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
            });

        });

        /* Bathy controls */
        bathyDatabaseNameTF.setText(BATHY_DB_NAME_0);
        bathyDbCB.setItems(dbCbBathyData);
        bathyDbCB.getSelectionModel().select("Choice DB");
        bathyDbCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable,
                        String oldValue, String newValue) -> {
                    if (newValue.equals(BATHY_DB_NAME_0)) {
                        bathyDatabaseNameTF.setText(BATHY_DB_NAME_0);
                        bathyDbCB.getSelectionModel().select("Choice DB");
                    } else {
                        if (newValue.equals(BATHY_DB_NAME_1)) {
                            bathyDatabaseNameTF.setText(BATHY_DB_NAME_1);
                            bathyDbCB.getSelectionModel().select("Choice DB");
                        }
                    }
                });
        createBathyButton.setOnMouseClicked((MouseEvent event) -> {
            String bathyDBName = bathyDatabaseNameTF.getText();

            bathyData = bathyDataTF.getText();
            guiAgentServices.getJobsManager().newJob("Load DB : " + bathyDBName, (progressHandle) -> {
                bathymetryDBServices.connect(bathyDBName, "localhost", "jdbc:postgresql://",
                        "5432", "org.postgresql.Driver", "admin", "admin");
                bathymetryDBServices.create(bathyData, "bathy");
            });
        });
        insertBathyButton.setOnMouseClicked((MouseEvent event) -> {
            String bathyDBName = bathyDatabaseNameTF.getText();

            bathyData = bathyDataTF.getText();
            guiAgentServices.getJobsManager().newJob("Load DB : " + bathyDBName, (progressHandle) -> {
                bathymetryDBServices.connect(bathyDBName, "localhost", "jdbc:postgresql://",
                        "5432", "org.postgresql.Driver", "admin", "admin");
                bathymetryDBServices.insert(bathyData, "bathy");
            });
        });
        /* Elevations controls */
        dem30mRB.setToggleGroup(mntGroup);
        dem5mRB.setToggleGroup(mntGroup);
        dem1mRB.setToggleGroup(mntGroup);

        elevationDbCB.setItems(dbCbElevationData);
        elevationDbCB.getSelectionModel().select("Choice DB");
        elevationDbCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable,
                        String oldValue, String newValue) -> {
                    if (newValue.equals("IGN75m")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_0);
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                    if (newValue.equals("SRTM30m")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_1);
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                    if (newValue.equals("Litto3D5m")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_3);
                        if (dem1mRB.isSelected()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("5m Checkbox is selected, are you sure the DB is OK ? ");
                            alert.show();
                        }
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                    if (newValue.equals("Litto3D1m")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_4);
                        if (dem5mRB.isSelected()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("5m Checkbox is selected, are you sure the DB is OK ? ");
                            alert.show();
                        }
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                    if (newValue.equals("BrestMetropole5mDB")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_5);
                        if (dem1mRB.isSelected()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("1m Checkbox is selected, are you sure the DB is OK ? ");
                            alert.show();
                        }
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                    if (newValue.equals("BrestMetropole1mDB")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_6);
                        if (dem5mRB.isSelected()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("5m Checkbox is selected, are you sure the DB is OK ? ");
                            alert.show();
                        }
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                    if (newValue.equals("Finistere5mDB")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_7);
                        if (dem1mRB.isSelected()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("1m Checkbox is selected, are you sure the DB is OK ? ");
                            alert.show();
                        }
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                    if (newValue.equals("TestAltiDB")) {
                        elevationDatabaseNameTF.setText(ELEVATION_DB_NAME_2);
                        elevationDbCB.getSelectionModel().select("Choice DB");
                    }
                });

        createElevationButton.setOnMouseClicked((MouseEvent event) -> {
            if (dem5mRB.isSelected()) {
                mnt = "MNT5m";
            }
            if (dem1mRB.isSelected()) {
                mnt = "MNT1m";
            }
            String elevationDBName = elevationDatabaseNameTF.getText();
            if (elevationDBName != null) {
                bathymetryDBServices.connect(elevationDBName, "localhost", "jdbc:postgresql://",
                        "5432", "org.postgresql.Driver", "admin", "admin");
                guiAgentServices.getJobsManager().newJob("Load DB : " + elevationDBName, (progressHandle) -> {
                    if (!elevationDataTF.getText().trim().equals("")) {
                        if (lambert2Wgs84CB.isSelected() || tiff2XyzCB.isSelected()) {
                            String fileName = prepareCreateOrInsertFile(elevationDataTF.getText());
                            bathymetryDBServices.create(ELEVATION_DB_ORG_DIR + SEP + fileName, "elevation");
                        } else {
                            bathymetryDBServices.create(elevationDataTF.getText(), "elevation");
                        }
                    } else {
                        if (!elevationDataDirTF.getText().trim().equals("")) {
                            if (lambert2Wgs84CB.isSelected() || tiff2XyzCB.isSelected()) {
                                try {
                                    Path directory = Paths.get(elevationDataDirTF.getText());
                                    Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                                        @Override
                                        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                                            if ((path.toString().endsWith("asc")) //&& path.toString().contains(mnt))
                                                    || (path.toString().endsWith("tif") && path.toString().contains("1arc"))) {// || tiff2XyzCB.isSelected()
                                                String fileName = prepareCreateOrInsertFile(path.toString());
                                                if (isCreate == false) {
                                                    isCreate = true;
                                                    bathymetryDBServices.create(ELEVATION_DB_ORG_DIR + SEP + fileName, "elevation");
                                                } else {
                                                    bathymetryDBServices.insert(ELEVATION_DB_ORG_DIR + SEP + fileName, "elevation");
                                                }
                                            }

                                            return FileVisitResult.CONTINUE;
                                        }
                                    });
                                } catch (IOException ex) {
                                    //Nothing if dir don't exist
                                }
                            } else {
                                try {
                                    Path directory = Paths.get(elevationDataDirTF.getText());
                                    System.out.println("directory : " + directory);
                                    Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                                        @Override
                                        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                                            if (path.toString().endsWith("asc")) {
                                                bathymetryDBServices.create(path.toString(), "elevation");
                                            }
                                            return FileVisitResult.CONTINUE;
                                        }
                                    });
                                } catch (IOException ex) {
                                    //Nothing if dir don't exist
                                }
                            }
                        }
                    }
                });
            }
        });

        insertElevationButton.setOnMouseClicked((MouseEvent event) -> {
            String elevationDBName = elevationDatabaseNameTF.getText();
            if (elevationDBName != null) {
                bathymetryDBServices.connect(elevationDBName, "localhost", "jdbc:postgresql://",
                        "5432", "org.postgresql.Driver", "admin", "admin");
                guiAgentServices.getJobsManager().newJob("Load DB : " + elevationDBName, (progressHandle) -> {
                    if (!elevationDataTF.getText().trim().equals("")) {
                        if (lambert2Wgs84CB.isSelected() || tiff2XyzCB.isSelected()) {
                            String fileName = prepareCreateOrInsertFile(elevationDataTF.getText());
                            bathymetryDBServices.insert(ELEVATION_DB_ORG_DIR + SEP + fileName, "elevation");
                        } else {
                            bathymetryDBServices.insert(elevationDataTF.getText(), "elevation");
                        }
                    } else {
                        if (!elevationDataDirTF.getText().trim().equals("")) {
                            if (lambert2Wgs84CB.isSelected() || tiff2XyzCB.isSelected()) {
                                try {
                                    Path directory = Paths.get(elevationDataDirTF.getText());
                                    Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                                        @Override
                                        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                                            if (path.toString().endsWith("asc") && (path.toString().contains(mnt))) {
                                                String fileName = prepareCreateOrInsertFile(path.toString());
                                                bathymetryDBServices.insert(ELEVATION_DB_ORG_DIR + SEP + fileName, "elevation");
                                            }
                                            return FileVisitResult.CONTINUE;
                                        }
                                    });
                                } catch (IOException ex) {
                                    //Nothing if dir don't exist
                                }
                            } else {
                                try {
                                    Path directory = Paths.get(elevationDataDirTF.getText());
                                    Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                                        @Override
                                        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                                            if (path.toString().endsWith("asc")) {
                                                bathymetryDBServices.insert(path.toString(), "elevation");
                                            }
                                            return FileVisitResult.CONTINUE;
                                        }
                                    });
                                } catch (IOException ex) {
                                    //Nothing if dir don't exist
                                }
                            }
                        }
                    }
                });
            }
        });
        /* Buildings controls */

        buildingsDatabaseNameTF.setText(BUILDINGS_DB_NAME_0);
        buildingsDbCB.setItems(dbCbBuildingsData);
        buildingsDbCB.getSelectionModel().select("Choice DB");
        buildingsDbCB.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable,
                        String oldValue, String newValue) -> {
                    if (newValue.equals(BUILDINGS_DB_NAME_0)) {
                        buildingsDatabaseNameTF.setText(BUILDINGS_DB_NAME_0);
                        buildingsDbCB.getSelectionModel().select("Choice DB");
                    }
                });
        createBuildingsButton.setOnMouseClicked((MouseEvent event) -> {
            loadingBuildings(buildingsDataDirTF.getText(), buildingsDatabaseNameTF.getText());
        });
        insertBuildingsButton.setOnMouseClicked((MouseEvent event) -> {
            String buildingsDBName = buildingsDatabaseNameTF.getText();

        });

        helpButton.setOnMouseClicked((MouseEvent event) -> {
            if (s57Tab.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Options");
                alert.setHeaderText("Données utilisateur");
                Text s = new Text("    S57ChartsDir : chemin de la racine des cartes S57 \n");
                s.setWrappingWidth(650);
                alert.getDialogPane().setContent(s);
                alert.show();
            } else {
                if (bathyTab.isSelected()) {
                } else {
                    if (elevationsTab.isSelected()) {

                    }
                }
            }
        });
        s57Button.setOnMouseClicked((MouseEvent event) -> {
            openDir(s57TF);
        });
        beaconsDataButton.setOnMouseClicked((MouseEvent event) -> {
            openDir(beaconsDataTF);
        });
        psqlButton.setOnMouseClicked((MouseEvent event) -> {
            openDir(psqlTF);
        });
        psqlButton1.setOnMouseClicked((MouseEvent event) -> {
            openDir(psqlTF1);
        });
        psqlButton11.setOnMouseClicked((MouseEvent event) -> {
            openDir(psqlTF11);
        });
        gdalButton.setOnMouseClicked((MouseEvent event) -> {
            openDir(gdalTF);
        });
        ulhyssesButton.setOnMouseClicked((MouseEvent event) -> {
            openDir(ulhyssesTF);
        });
        bathyDataButton.setOnMouseClicked((MouseEvent event) -> {
            openFile(bathyDataTF);
        });
        elevationDataButton.setOnMouseClicked((MouseEvent event) -> {
            openFile(elevationDataTF);
        });
        elevationDataDirButton.setOnMouseClicked((MouseEvent event) -> {
            isDataDir = true;
            openDir(elevationDataDirTF);
        });
        buildingsDataButton.setOnMouseClicked((MouseEvent event) -> {
            openDir(buildingsDataDirTF);
        });
    }

    private String prepareCreateOrInsertFile(String in) {
        String result = in;
        String tiffFile;
        if (lambert2Wgs84CB.isSelected() && !tiff2XyzCB.isSelected()) {
            tiffFile = rasterServices.translateAscLambert93ToTif(in, USER_DIR + SEP + ELEVATION_DB_ORG_DIR);//EPSG d'origine 2154
            tiffFile = rasterServices.warpTifLambert93ToTifWGS84(USER_DIR + SEP + ELEVATION_DB_ORG_DIR + SEP + tiffFile, ELEVATION_DB_ORG_DIR);//EPSG 4326
            System.out.println("tiffFile : " + tiffFile);
            if (geotifPreviewCB.isSelected()) {
                geoTiffChartServices.openChart(ELEVATION_DB_ORG_DIR + SEP + tiffFile);
            }
            result = rasterServices.translateTif2XYZ(ELEVATION_DB_ORG_DIR + SEP + tiffFile, ELEVATION_DB_ORG_DIR);
        }
        if (!lambert2Wgs84CB.isSelected() && tiff2XyzCB.isSelected()) {
            if (geotifPreviewCB.isSelected()) {
                geoTiffChartServices.openChart(in);
            }
            result = rasterServices.translateTif2XYZ(in, ELEVATION_DB_ORG_DIR);
        }
        return result;
    }

    private List<SolidGeo> loadingBuildings(String buildingsDataDir, String buildingsDBName) {
        List<SolidGeo> result = null;
        guiAgentServices.getJobsManager().newJob("Load DB : " + buildingsDBName, (ProgressHandle progressHandle) -> {
            ObjPaysbrestLoader objPaysbrestLoader = new ObjPaysbrestLoader(geodesyServices, guiAgentServices,
                    instrumentDriverManagerServices, jtsServices, pro4JServices, objComponentServices);
            System.out.println("objPaysbrestLoader : " + objPaysbrestLoader);
            bathymetryDBServices.connect(buildingsDBName, "localhost", "jdbc:postgresql://",
                    "5432", "org.postgresql.Driver", "admin", "admin");

            try {
                Path directory = Paths.get(buildingsDataDir);
                System.out.println("directory " + directory);
                Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                        String table;

                        if (path.toString().endsWith("obj")
                                && (path.toString().contains("FACADES_TEXTURE") || path.toString().contains("TOITURES_TEXTURE"))) {
                            if (path.toString().contains("FACADES")) {
                                table = "wall";
                            } else {
                                table = "roof";
                            }
                            LOGGER.log(Level.INFO, path.getFileName().toString());
                            List<SolidGeo> solidWgs84List = objPaysbrestLoader.loadObj(path, 145168, 6836820);

                            if (previewBuildingsRB.isSelected()) {
                                Material[] materials = {Material.GREEN, Material.BLUE, Material.YELLOW, Material.PINK,
                                    Material.CYAN, Material.MAGENTA, Material.ORANGE, Material.RED};
                                int color = 0;
                                for (SolidGeo solid : solidWgs84List) {
                                    displayServices.displaySolidGeoAsPolygon(solid, 0.0, s57Layer, materials[color++ % 8]);
                                    //   System.out.println(topologyServices.toWKT(solid));
                                }
                            }
                            String query;
                            if (!isTable0Created) {
                                isTable0Created = true;
                                query = "DROP TABLE IF EXISTS wall; \n"
                                        + "CREATE TABLE wall (id SERIAL PRIMARY KEY, "
                                        + "name TEXT, "
                                        + "coord GEOMETRY(POINT, 4326), "
                                        + "geom geometry(GEOMETRYCOLLECTIONZ,4326));";
                                bathymetryDBServices.execute(query);
                            }
                            if (!isTable1Created) {
                                isTable1Created = true;
                                query = "DROP TABLE IF EXISTS roof; \n"
                                        + "CREATE TABLE roof (id SERIAL PRIMARY KEY, "
                                        + "name TEXT, "
                                        + "coord GEOMETRY(POINT, 4326), "
                                        + "geom GEOMETRY(GEOMETRYCOLLECTIONZ,4326));";
                                bathymetryDBServices.execute(query);
                            }
                            System.out.println("solidWgs84List : " + path.getFileName().toString() + " " + solidWgs84List.size());
                            bathymetryDBServices.insert( table,  solidWgs84List);
                        
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException ex) {
                //Nothing if dir don't exist
            }

        });
        return result;
    }

    public void openFile(TextField tf) {
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle(tr("popup.fileChooser.open"));
        String userInitialDirectory = System.getProperty("user.home");
        this.fileChooser.setInitialDirectory(new File(userInitialDirectory));
        File selectedFile = this.fileChooser.showOpenDialog(null);
        if (selectedFile == null) {
            tf.setText("No file selected");
        } else {
            tf.setText(selectedFile.getAbsolutePath());
        }
    }

    public void openDir(TextField tf) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(tr("popup.fileChooser.open"));
        String userInitialDirectory = System.getProperty("user.home");
        directoryChooser.setInitialDirectory(new File(userInitialDirectory));
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory == null) {
            tf.setText("No Directory selected");
        } else {
            tf.setText(selectedDirectory.getAbsolutePath());
        }
    }
}
