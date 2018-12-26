package bzh.terrevirtuelle.navisu.app.guiagent.dock.impl;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.driver.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.ZoneDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.DockManager;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.DockManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.tools.AnimationFactory;

import org.capcaval.c3.component.ComponentState;

import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.widgets.dock.Dock;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItem;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItemFactory;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenu;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenuBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.Animation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import org.capcaval.c3.component.annotation.UsedService;

/*
 * NaVisu
 *
 * @date 23 mars 2015
 * @author Serge Morvan
 */
public class DockManagerImpl<TrackTool>
        implements DockManager, DockManagerServices, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    DriverManagerServices driverManagerServices;
    @UsedService
    WebDriverManagerServices webDriverManagerServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    @UsedService
    DatabaseDriverManagerServices databaseDriverManagerServices;

    protected static final Logger LOGGER = Logger.getLogger(DockManagerImpl.class.getName());
    private final String HOST_NAME = "localhost";
    private final String PORT = "5432";
    private final String DRIVER_NAME = "org.postgresql.Driver";
    private final String JDBC_PROTOCOL = "jdbc:postgresql://";
    private final String DB_NAME = "BathyShomDB";
    private final String USER_NAME = "admin";
    private final String PASSWD = "admin";
    protected static final String ICON_PATH = "bzh/terrevirtuelle/navisu/app/guiagent/impl/";
    protected final String EMODNET = "http://ows.emodnet-bathymetry.eu/wms";
    protected final String GEBCO = "http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?";

    protected RadialMenu stlRadialMenu;
    protected RadialMenu booksRadialMenu;
    protected RadialMenu instrumentsRadialMenu;
    protected RadialMenu MediasRadialMenu;
    protected RadialMenu meteoRadialMenu;
    protected RadialMenu tidesRadialMenu;
    protected RadialMenu chartsRadialMenu;
    protected RadialMenu toolsRadialMenu;
    protected RadialMenu navigationRadialMenu;
    protected RadialMenu systemRadialMenu;
    protected RadialMenu tracksRadialMenu;
    protected RadialMenu shomRadialMenu;
    protected RadialMenu osmBRadialMenu;

    protected ImageView centerImg;
    protected int width;
    protected int height;
    private StackPane root = null;
    private Scene scene = null;
    private List<RadialMenu> radialMenus;
    private Map<String, InstrumentDriver> instrumentDrivers;
    private InstrumentDriver instrumentDriver;
    private ZoneDriver zone;
    private Group groupDock;

    public final DockItem[] ICONS = new DockItem[]{
        /* 
        DockItemFactory.newImageItem("tracks", ICON_PATH + "dock_icons/tracks.png",
        (e) -> {
            tracksRadialMenu.setVisible(!tracksRadialMenu.isVisible());
        }),
         */
        DockItemFactory.newImageItem("system I/O", ICON_PATH + "dock_icons/system.png",
        (e) -> {
            systemRadialMenu.setVisible(!systemRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("user tools", ICON_PATH + "dock_icons/tools.png",
        (e) -> {
            toolsRadialMenu.setVisible(!toolsRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("charts", ICON_PATH + "dock_icons/charts.png",
        (e) -> {
            chartsRadialMenu.setVisible(!chartsRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("tides", ICON_PATH + "dock_icons/tides.png",
        (e) -> {
            tidesRadialMenu.setVisible(!tidesRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("meteo", ICON_PATH + "dock_icons/meteo.png",
        (e) -> {
            meteoRadialMenu.setVisible(!meteoRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("instrum", ICON_PATH + "dock_icons/instruments.png",
        (e) -> {
            instrumentsRadialMenu.setVisible(!instrumentsRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("navigation", ICON_PATH + "dock_icons/navigation.png",
        (e) -> {
            navigationRadialMenu.setVisible(!navigationRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("logbook", ICON_PATH + "dock_icons/book.png",
        (e) -> {
            booksRadialMenu.setVisible(!booksRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("STL", ICON_PATH + "dock_icons/stl.png",
        (e) -> {
            stlRadialMenu.setVisible(!stlRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("medias", ICON_PATH + "dock_icons/medias.png",
        (e) -> {
            MediasRadialMenu.setVisible(!MediasRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("shom", ICON_PATH + "dock_icons/shom.png",
        (e) -> {
            shomRadialMenu.setVisible(!shomRadialMenu.isVisible());
        }),
        DockItemFactory.newImageItem("osmb", ICON_PATH + "dock_icons/osmB.png",
        (e) -> {
            osmBRadialMenu.setVisible(!osmBRadialMenu.isVisible());
        })
    };
    final Dock dock = new Dock(ICONS);

    @Override
    public void componentInitiated() {
        radialMenus = new ArrayList<>();
        instrumentDrivers = new HashMap<>();
    }

    @Override
    public void init(StackPane root, Scene scene, int height, int width) {
        this.scene = scene;
        this.root = root;
        this.height = height;
        this.width = width;
    }

    @Override
    public void makeDock() {
        createDockWidget(scene);
        createBooksRadialWidget();
        createChartsRadialWidget();
        createInstrumentsRadialWidget();
        createMeteoRadialWidget();
        createTidesRadialWidget();
        createToolsRadialWidget();
        createNavigationRadialWidget();
        createOsmBWidget();
        createSystemRadialWidget();
        createStlWidget();
        createShomWidget();
        createMediasRadialWidget();
    }

    private void createDockWidget(Scene scene) {

        groupDock = new Group();
        groupDock.getChildren().add(dock);
        root.getChildren().add(groupDock);
        //dock.setLayoutX(475.0);
        dock.setLayoutX(425.0);
        dock.setLayoutY(40.0);
        dock.setOrientation(Orientation.HORIZONTAL);
        StackPane.setAlignment(groupDock, Pos.BOTTOM_CENTER);
        Animation downAnimation = AnimationFactory.newTranslateAnimation(groupDock, 200, 300, true);
        Animation upAnimation = AnimationFactory.newTranslateAnimation(groupDock, 200, 0, true);
        scene.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.D) {
                downAnimation.play();
            }
            if (ke.getCode() == KeyCode.U) {
                upAnimation.play();
            }
        });
    }
    //--------------OSMB------------------

    private void createOsmBWidget() {
        osmBRadialMenu = RadialMenuBuilder.create()
                .centralImage("chantier.png")
                .createNode(0, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(1, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(2, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> open())
                .build();
        osmBRadialMenu.setLayoutX((width / 2) - 10);
        osmBRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(osmBRadialMenu);
        radialMenus.add(osmBRadialMenu);
    }
//--------------STL------------------

    private void createStlWidget() {
        stlRadialMenu = RadialMenuBuilder.create()
                .centralImage("STLradialmenu.png")
                .createNode(0, "dem.png", 0, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(1, "bathy.png", 1, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(2, "charts.png", 1, "files.png", 0, "vide.png", (e) -> open())
                .createNode(2, "charts.png", 2, "db.png", 0, "scales.png", (e) -> open("StlDbS57"))
                .build();
        stlRadialMenu.setLayoutX((width / 2) - 10);
        stlRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(stlRadialMenu);
        radialMenus.add(stlRadialMenu);
    }
//--------------SHOM------------------

    private void createShomWidget() {
        shomRadialMenu = RadialMenuBuilder.create()
                .centralImage("shom_2.png")
                .createNode(0, "bathy.png", 0, "read.png", 0, "dalles.png", (e) -> open())
                .createNode(0, "bathy.png", 0, "read.png", 1, "mnt.png", (e) -> open())
                .createNode(1, "sediment.png", 0, "read.png", 0, "vide.png", (e) -> open())
                .createNode(2, "litto3D.png", 0, "read.png", 0, "vide.png", (e) -> open())
                .createNode(3, "marees.png", 0, "read.png", 0, "vide.png", (e) -> open())
                .createNode(4, "aires.png", 0, "read.png", 0, "vide.png", (e) -> open())
                .createNode(4, "epaves.png", 0, "read.png", 0, "vide.png", (e) -> open())
                .build();
        shomRadialMenu.setLayoutX((width / 2) - 10);
        shomRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(shomRadialMenu);
        radialMenus.add(shomRadialMenu);
    }

    //--------------BOOKS------------------
    private void createBooksRadialWidget() {
        booksRadialMenu = RadialMenuBuilder.create()
                .centralImage("booksradialmenu150.png")
                .createNode(0, "logbook.png", 0, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(0, "logbook.png", 1, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(1, "lightsbook.png", 0, "images.png", 0, "emodnet.png", (e) -> open())
                .createNode(1, "lightsbook.png", 1, "vide.png", 1, "vide.png", (e) -> open())
                .createNode(2, "sailingbook.png", 0, "worldwide_sailing_directions.png", 0, "vide.png", (e) -> open())
                .createNode(2, "sailingbook.png", 1, "IrelandSouth_sailing_directions.png", 0, "vide.png", (e) -> open())
                .createNode(2, "sailingbook.png", 2, "shom.png", 0, "shomviewer.png", (e) -> open("SailingDirectionsViewer"))
                .createNode(2, "sailingbook.png", 2, "shom.png", 0, "shomeditor.png", (e) -> open("SailingDirectionsEditor"))
                .build();

        booksRadialMenu.setLayoutX((width / 2) - 10);
        booksRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(booksRadialMenu);
        radialMenus.add(booksRadialMenu);
    }

    //--------------CHARTS------------------
    private void createChartsRadialWidget() {
        chartsRadialMenu = RadialMenuBuilder.create()
                .centralImage("chartsradialmenu150.png")
                .createNode(0, "nav.png", 0, "vector.png", 0, "s57charts.png", (e) -> open("S57", ".000"))
                .createNode(0, "nav.png", 0, "vector.png", 1, "s57db.png", (e) -> open("ReqDbS57"))
                .createNode(0, "nav.png", 1, "raster.png", 0, "bsbkap.png", (e) -> open("BSB/KAP", ".KAP", ".kap"))
                .createNode(0, "nav.png", 1, "raster.png", 1, "geotiff.png", (e) -> open("GeoTiff", ".tif", ".TIF", ".tiff"))
                // .createNode(0, "nav.png", 2, "vector3D.png", 2, "stl.png", (e) -> open("S57Stl", ".000",".001",".002"))
                .createNode(1, "bathy.png", 0, "images.png", 0, "emodnet.png", (e) -> openWMS("WMS", EMODNET))
                .createNode(1, "bathy.png", 0, "images.png", 1, "gebco.png", (e) -> openWMS("WMS", GEBCO))
                .createNode(1, "bathy.png", 1, "data.png", 1, "dbshomon.png", (e) -> openDB(DB_NAME, HOST_NAME, JDBC_PROTOCOL, PORT, DRIVER_NAME, USER_NAME, PASSWD))
                .createNode(1, "bathy.png", 1, "data.png", 2, "dbshomoff.png", (e) -> closeDB(DB_NAME))
                .createNode(1, "bathy.png", 1, "data.png", 3, "gebco.png", (e) -> open("Bathy", ".grb", ".Z", ".zip", ".gzip", "gz", ".bz2", ".nc", ".grib2"))
                //.createNode(1, "bathy.png", 1, "data.png", 4, "bathySounds.png", (e) -> open("BathySounds"))
                .createNode(2, "sediment.png", 0, "data.png", 0, "shom.png", (e) -> open("sedimentology", ".shp", ".SHP"))
                .createNode(3, "magnetism.png", 0, "data.png", 0, "noaa.png", (e) -> open("Magnetic", ".shp", ".SHP"))
                .build();

        chartsRadialMenu.setLayoutX((width / 2) - 10);
        chartsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(chartsRadialMenu);
        radialMenus.add(chartsRadialMenu);
    }

    //--------------INSTRUMENTS------------------
    private void createInstrumentsRadialWidget() {
        instrumentsRadialMenu = RadialMenuBuilder.create()
                .centralImage("instrumentsradialmenu150.png")
                .createNode(0, "navigation.png", 0, "ais.png", 0, "aisRadarOn.png", (e) -> open("AisRadar"))
                .createNode(0, "navigation.png", 0, "ais.png", 1, "aisRadarOff.png", (e) -> close("AisRadar"))
                .createNode(0, "navigation.png", 0, "ais.png", 0, "aisPlotOn.png", (e) -> open("AisPlotter"))
                .createNode(0, "navigation.png", 0, "ais.png", 1, "aisPlotOff.png", (e) -> close("AisPlotter"))
                .createNode(0, "navigation.png", 0, "ais.png", 2, "aisLogOn.png", (e) -> open("AisLogger"))
                .createNode(0, "navigation.png", 0, "ais.png", 3, "aisLogOff.png", (e) -> close("AisLogger"))
                .createNode(0, "navigation.png", 1, "gps.png", 0, "gpsPlotOn.png", (e) -> open("GpsPlotter"))
                .createNode(0, "navigation.png", 1, "gps.png", 1, "gpsPlotOff.png", (e) -> close("GpsPlotter"))
                .createNode(0, "navigation.png", 1, "gps.png", 0, "gpsPlotWithRouteOn.png", (e) -> open("GpsPlotterWithRoute"))
                .createNode(0, "navigation.png", 1, "gps.png", 1, "gpsPlotWithRouteOff.png", (e) -> close("GpsPlotterWithRoute"))
                .createNode(0, "navigation.png", 1, "gps.png", 2, "gpsTrackOn.png", (e) -> open("GpsTrack"))
                .createNode(0, "navigation.png", 1, "gps.png", 3, "gpsTrackOff.png", (e) -> close("GpsTrack"))
                .createNode(0, "navigation.png", 1, "gps.png", 4, "gpsLogOn.png", (e) -> open("GpsLogger"))
                .createNode(0, "navigation.png", 1, "gps.png", 5, "gpsLogOff.png", (e) -> close("GpsLogger"))
                .createNode(0, "navigation.png", 2, "compass.png", 0, "compass.png", (e) -> open("Compass"))
                .createNode(0, "navigation.png", 3, "bathy.png", 0, "sonarOn.png", (e) -> open("Sonar"))
                .createNode(0, "navigation.png", 4, "time.png", 1, "clocks.png", (e) -> open("Clocks"))
                .build();

        instrumentsRadialMenu.setLayoutX((width / 2) - 40);
        instrumentsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(instrumentsRadialMenu);
        radialMenus.add(instrumentsRadialMenu);
    }

    //--------------MEDIAS------------------
    private void createMediasRadialWidget() {
        MediasRadialMenu = RadialMenuBuilder.create()
                .centralImage("Mediasradialmenu150.png")
                .createNode(0, "photos.png", 0, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(0, "photos.png", 1, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(1, "sounds.png", 0, "samples.png", 0, "vide.png", (e) -> open())
                .createNode(1, "sounds.png", 1, "captures.png", 1, "bathySounds.png", (e) -> open("BathySounds"))
                .createNode(2, "videos.png", 0, "vide.png", 0, "vide.png", (e) -> open())
                .createNode(2, "videos.png", 1, "vide.png", 0, "vide.png", (e) -> open())
                .build();
        MediasRadialMenu.setLayoutX((width / 2) - 10);
        MediasRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(MediasRadialMenu);
        radialMenus.add(MediasRadialMenu);
    }

    //--------------METEO------------------
    private void createMeteoRadialWidget() {
        meteoRadialMenu = RadialMenuBuilder.create()
                .centralImage("meteoradialmenu150.png")
                .createNode(0, "files.png", 0, "grib.png", 0, "wind.png",
                        (e) -> open("Wind", ".grb", ".Z", ".zip", ".gzip", "gz", ".bz2", ".nc", ".grib2", ".grb2"))
                .createNode(0, "files.png", 0, "grib.png", 0, "pressure.png",
                        (e) -> open("Pressure", ".grb", ".Z", ".zip", ".gzip", "gz", ".bz2", ".nc", ".grib2", ".grb2"))
                .createNode(0, "files.png", 1, "model.png", 0, "dump.png",
                        (e) -> open("NetCdfInfo", ".grb", ".Z", ".zip", ".gzip", "gz", ".bz2", ".nc", ".grib2", ".grb2"))
                .createNode(1, "sites.png", 0, "local.png", 0, "darkSky.png", (e) -> open("DarkSky"))
                .createNode(1, "sites.png", 1, "northA.png", 0, "metoffice.png", (e) -> open("MetOffice"))
                .build();
        meteoRadialMenu.setLayoutX((width / 2) - 30);
        meteoRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(meteoRadialMenu);
        radialMenus.add(meteoRadialMenu);
    }
    //--------------NAVIGATION------------------

    private void createNavigationRadialWidget() {
        navigationRadialMenu = RadialMenuBuilder.create()
                .centralImage("navigationradialmenu150.png")
                .createNode(0, "navigation.png", 0, "tracks.png", 0, "gpx.png", (e) -> open("Gpx", ".gpx", ".GPX"))
                .createNode(0, "navigation.png", 0, "tracks.png", 1, "kml.png", (e) -> open("Kml", ".kml", ".KML", ".kmz", ".KMZ"))
                .createNode(0, "navigation.png", 1, "routes.png", 2, "measuretools.png", (e) -> open("MeasureTools"))
                .createNode(0, "navigation.png", 1, "routes.png", 1, "routeeditor.png", (e) -> open("RouteEditor"))
                .createNode(0, "navigation.png", 1, "routes.png", 2, "routedataeditor.png", (e) -> open("RouteDataEditor"))
                .createNode(0, "navigation.png", 1, "routes.png", 3, "routephotoeditor.png", (e) -> open("RoutePhotoEditor"))
                .build();

        navigationRadialMenu.setLayoutX((width / 2) - 30);
        navigationRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(navigationRadialMenu);
        radialMenus.add(navigationRadialMenu);
    }

    //--------------TIDES------------------
    private void createTidesRadialWidget() {
        tidesRadialMenu = RadialMenuBuilder.create()
                .centralImage("tidesradialmenu150.png")
                .createNode(0, "currents.png", 0, "model.png", 0, "grib.png", (e) -> open("Currents", ".grb", ".Z", ".zip", ".gzip", "gz", ".bz2", ".nc", ".grib2"))
                .createNode(1, "waves.png", 0, "model.png", 0, "grib.png", (e) -> open("Waves", ".grb", ".Z", ".zip", ".gzip", "gz", ".bz2", ".nc", ".grib2"))
                .createNode(2, "tide.png", 0, "model.png", 0, "grib.png", (e) -> open("Tide"))
                .build();

        tidesRadialMenu.setLayoutX((width / 2) - 10);
        tidesRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(tidesRadialMenu);
        radialMenus.add(tidesRadialMenu);
    }

    //--------------TOOLS------------------
    private void createToolsRadialWidget() {
        toolsRadialMenu = RadialMenuBuilder.create()
                .centralImage("toolsradialmenu150.png")
                .createNode(0, "devices.png", 1, "server.png", 2, "options.png", (e) -> open("ServerOptions"))
                .createNode(0, "devices.png", 2, "leapMotion.png", 2, "leapMotionOn.png", (e) -> open("LeapMotion"))
                .createNode(0, "devices.png", 2, "leapMotion.png", 3, "leapMotionOff.png", (e) -> close("LeapMotion"))
                .createNode(1, "config.png", 1, "app.png", 1, "options.png", (e) -> open("Configuration"))//"ServerOptions"
                .createNode(2, "earth.png", 0, "models.png", 0, "noElevation.png", (e) -> open("noElevation"))
                .createNode(2, "earth.png", 0, "models.png", 1, "elevation.png", (e) -> open("Elevation"))
                .createNode(2, "earth.png", 0, "models.png", 2, "noBathy.png", (e) -> open("noBathymetry"))
                .createNode(2, "earth.png", 0, "models.png", 3, "bathy.png", (e) -> open("Bathymetry"))
                .createNode(1, "config.png", 0, "components.png", 4, "architecture.png", (e) -> open("Architecture"))
                .createNode(3, "db.png", 0, "spatiales.png", 0, "s57.png", (e) -> open("DbS57"))
                .createNode(3, "db.png", 0, "spatiales.png", 1, "bathy.png", (e) -> open("DbBathy"))
                .createNode(3, "db.png", 0, "spatiales.png", 2, "elevations.png", (e) -> open("DbElevation"))
                .createNode(3, "db.png", 0, "spatiales.png", 3, "aires.png", (e) -> open())
                .build();
        toolsRadialMenu.setLayoutX((width / 2));
        toolsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(toolsRadialMenu);
        radialMenus.add(toolsRadialMenu);
    }

    //--------------System------------------
    private void createSystemRadialWidget() {
        systemRadialMenu = RadialMenuBuilder.create()
                .centralImage("systemradialmenu150.png")
                .createNode(0, "simu.png", 0, "files.png", 1, "nmeaOn.png", (e) -> open("NMEA", ".nmea", ".n2k", ".ais", ".txt"))
                .createNode(0, "simu.png", 0, "files.png", 2, "nmeaOff.png", (e) -> open())
                .createNode(1, "data.png", 0, "files.png", 0, "shapefile.png", (e) -> open("SHP", ".shp", ".SHP"))
                .createNode(1, "data.png", 0, "files.png", 1, "kml.png", (e) -> open("KML", ".kml", ".kmz", ".KMZ"))
                .createNode(1, "data.png", 0, "files.png", 2, "collada.png", (e) -> open("DAE", ".dae", ".DAE"))
                .createNode(1, "data.png", 0, "files.png", 3, "wms.png", (e) -> openWMS("WMS_CATALOG", ""))
                //   .createNode(0, "system.png", 1, "camera.png", 1, "cameraOff.png", (e) -> close("Camera"))
                //   .createNode(0, "system.png", 1, "camera.png", 2, "cameraOn.png", (e) -> open("Camera"))
                .build();
        systemRadialMenu.setLayoutX((width / 2));
        systemRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(systemRadialMenu);
        radialMenus.add(systemRadialMenu);
    }

    private void open() {
        System.out.println("Work in progress");
        clear();
    }

    private void open(String keyName) {
        instrumentDriver = instrumentDriverManagerServices.open(keyName);
        instrumentDrivers.put(keyName, instrumentDriver);
        clear();
    }

    @Override
    public void open(String description, String... des) {
        String[] tab = new String[des.length];
        int i = 0;
        for (String s : des) {
            tab[i] = "*" + s;
            i++;
        }
        driverManagerServices.open(description, tab);
        clear();
    }

    private void close(String keyName) {
        instrumentDriver = instrumentDrivers.get(keyName);
        if (instrumentDriver != null) {
            instrumentDriver.off();
        }
    }

    private void openWMS(String description, String url) {
        webDriverManagerServices.handleOpenFiles(description, url);
        clear();
    }

    private void openDB(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd) {
        databaseDriverManagerServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        clear();
    }

    private void closeDB(String dbName) {
        databaseDriverManagerServices.close(dbName);
        clear();
    }

    private void clear() {
        radialMenus.stream().forEach((r) -> {
            r.setVisible(false);
        });
    }

    public Group getGroupDock() {
        return groupDock;
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
