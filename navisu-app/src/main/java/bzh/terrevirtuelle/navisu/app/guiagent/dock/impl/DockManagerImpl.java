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
    private final String DB_NAME = "Bathy";
    private final String USER_NAME = "Serge";
    private final String PASSWD = "lithops";
    protected static final String ICON_PATH = "bzh/terrevirtuelle/navisu/app/guiagent/impl/";
    protected final String EMODNET = "http://ows.emodnet-bathymetry.eu/wms";
    protected final String GEBCO = "http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?";
    protected RadialMenu booksRadialMenu;
    protected RadialMenu instrumentsRadialMenu;
    protected RadialMenu meteoRadialMenu;
    protected RadialMenu tidesRadialMenu;
    protected RadialMenu chartsRadialMenu;
    protected RadialMenu toolsRadialMenu;
    protected RadialMenu navigationRadialMenu;
    protected RadialMenu systemRadialMenu;
    protected RadialMenu tracksRadialMenu;
    protected ImageView centerImg;
    protected int width;
    protected int height;
    private StackPane root = null;
    private Scene scene = null;
    private List<RadialMenu> radialMenus;
    private Map<String, InstrumentDriver> instrumentDrivers;
    private InstrumentDriver instrumentDriver;
    private ZoneDriver zone;

    public final DockItem[] ICONS = new DockItem[]{
        DockItemFactory.newImageItem("tracks", ICON_PATH + "dock_icons/tracks.png",
        (e) -> {
            tracksRadialMenu.setVisible(!tracksRadialMenu.isVisible());
        }),
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
        DockItemFactory.newImageItem("instruments", ICON_PATH + "dock_icons/instruments.png",
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
        createSystemRadialWidget();
        createTracksRadialWidget();
    }

    private void createDockWidget(Scene scene) {

        Group groupDock = new Group();
        groupDock.getChildren().add(dock);
        root.getChildren().add(groupDock);
        dock.setLayoutX(475.0);
        dock.setLayoutY(40.0);
        dock.setOrientation(Orientation.HORIZONTAL);
        StackPane.setAlignment(groupDock, Pos.BOTTOM_CENTER);
        Animation downAnimation = AnimationFactory.newTranslateAnimation(groupDock, 200, 300, true);
        Animation upAnimation = AnimationFactory.newTranslateAnimation(groupDock, 200, 0, true);
        scene.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.DOWN) {
                downAnimation.play();
            }
            if (ke.getCode() == KeyCode.UP) {
                upAnimation.play();
            }
        });
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
                .createNode(2, "sailingbook.png", 2, "UK_Welsh harbours_sailing_directions.png", 0, "vide.png", (e) -> open())
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
                .createNode(0, "nav.png", 0, "vector.png", 0, "s57.png", (e) -> open("S57", ".000"))
                .createNode(0, "nav.png", 1, "raster.png", 0, "bsbkap.png", (e) -> open("BSB/KAP", ".KAP", ".kap"))
                .createNode(0, "nav.png", 1, "raster.png", 1, "geotiff.png", (e) -> open("GeoTiff", ".tif", ".TIF", ".tiff"))
                .createNode(1, "bathy.png", 0, "images.png", 0, "emodnet.png", (e) -> openWMS("WMS", EMODNET))
                .createNode(1, "bathy.png", 0, "images.png", 1, "gebco.png", (e) -> openWMS("WMS", GEBCO))
                .createNode(1, "bathy.png", 1, "data.png", 1, "dbshomon.png", (e) -> openDB(DB_NAME, HOST_NAME, JDBC_PROTOCOL, PORT, DRIVER_NAME, USER_NAME, PASSWD))
                .createNode(1, "bathy.png", 1, "data.png", 2, "dbshomoff.png", (e) -> closeDB(DB_NAME))
                .createNode(2, "sediment.png", 0, "data.png", 0, "shom.png", (e) -> open("sedimentology", ".shp"))
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
                .createNode(0, "navigation.png", 1, "gps.png", 2, "gpsLogOn.png", (e) -> open("GpsLogger"))
                .createNode(0, "navigation.png", 1, "gps.png", 3, "gpsLogOff.png", (e) -> close("GpsLogger"))
                .createNode(0, "navigation.png", 2, "compass.png", 0, "compass.png", (e) -> open("Compass"))
                .createNode(0, "navigation.png", 3, "bathy.png", 0, "sonarOn.png", (e) -> open("Sonar"))
                .build();

        instrumentsRadialMenu.setLayoutX((width / 2) - 40);
        instrumentsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(instrumentsRadialMenu);
        radialMenus.add(instrumentsRadialMenu);
    }

    //--------------METEO------------------
    private void createMeteoRadialWidget() {
        meteoRadialMenu = RadialMenuBuilder.create()
                .centralImage("meteoradialmenu150.png")
                .createNode(0, "file.png", 0, "vector.png", 0, "grib.png",
                        (e) -> open("Grib", ".grb", ".Z", ".zip", ".gzip", "gz", ".bz2"))
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
                .createNode(0, "currents.png", 0, "catalog.png", 0, "shom.png", (e) -> open("Currents", ".shp", ".SHP"))
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
                .createNode(0, "system.png", 1, "devices.png", 2, "aisConf.png", (e) -> open())
                .createNode(0, "system.png", 1, "devices.png", 5, "gpsConf.png", (e) -> open())
                .createNode(1, "data.png", 0, "files.png", 0, "shapefile.png", (e) -> open("SHP", ".shp"))
                .createNode(1, "data.png", 0, "files.png", 1, "kml.png", (e) -> open("KML", ".kml", ".kmz", ".KMZ"))
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
                .createNode(0, "system.png", 0, "files.png", 1, "fileReadOn.png", (e) -> open("NMEA", ".nmea", ".n2k", ".ais"))
                .createNode(0, "system.png", 0, "files.png", 2, "fileReadOff.png", (e) -> open())
                .build();
        systemRadialMenu.setLayoutX((width / 2));
        systemRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(systemRadialMenu);
        radialMenus.add(systemRadialMenu);
    }

    //--------------Tracks------------------

    private void createTracksRadialWidget() {
        tracksRadialMenu = RadialMenuBuilder.create()
                .centralImage("tracksradialmenu150.png")
                //.createNode(0, "sector.png", 0, "sector.png", 0, "sectorOn.png", (e) -> open("GpsTrackSector"))
                //.createNode(0, "sector.png", 0, "sector.png", 1, "newSector.png", (e) -> newSector())
                .createNode(1, "shape.png", 0, "shape.png", 0, "polyShapeOn.png", (e) -> polyShapeOn())
                .createNode(1, "shape.png", 0, "shape.png", 1, "ellipseShapeOn.png", (e) -> ellipseShapeOn())
                .createNode(1, "shape.png", 0, "shape.png", 2, "circleShapeOn.png", (e) -> circleShapeOn())
                .createNode(1, "shape.png", 0, "shape.png", 3, "quadShapeOn.png", (e) -> quadShapeOn())
                .createNode(1, "shape.png", 0, "shape.png", 4, "freeHandOn.png", (e) -> freeHandOn())   
                .createNode(2, "polyControl.png", 0, "polyControl.png", 0, "load.png", (e) -> loadPolygons())
                .createNode(2, "polyControl.png", 0, "polyControl.png", 1, "saveAll.png", (e) -> saveAllPolygons())
                .createNode(2, "polyControl.png", 0, "polyControl.png", 2, "savePolygon.png", (e) -> savePolygon())
                .createNode(2, "polyControl.png", 0, "polyControl.png", 3, "drawerOn.png", (e) -> drawerOn())
                .createNode(2, "polyControl.png", 0, "polyControl.png", 4, "polygonOn.png", (e) -> open("GpsTrackPolygon"))              
                .createNode(0, "createRule.png", 1, "createRule.png", 0, "createRule.png", (e) -> createRule())
                .createNode(0, "createRule.png", 1, "createRule.png", 1, "associateRule.png", (e) -> activateRule()) 
                .createNode(3, "cpa.png", 1, "custom.png", 0, "activateCpaZone.png", (e) -> activateCpaZone())
                .createNode(3, "cpa.png", 1, "custom.png", 1, "createCpaZone.png", (e) -> createCpaZone())
                .createNode(3, "cpa.png", 0, "classic.png", 0, "createCpaZone1000.png", (e) -> createCpaZone1000())
                .createNode(3, "cpa.png", 0, "classic.png", 1, "createCpaZone500.png", (e) -> createCpaZone500())
                .createNode(4, "drawPath.png", 1, "drawPath.png", 0, "createPath.png", (e) -> createPath())
                .createNode(4, "drawPath.png", 1, "drawPath.png", 1, "activatePath.png", (e) -> activatePath())
                .createNode(4, "drawPath.png", 1, "drawPath.png", 2, "save.png", (e) -> savePath())
                .createNode(4, "drawPath.png", 1, "drawPath.png", 3, "load.png", (e) -> loadPath())
                .build();

        tracksRadialMenu.setLayoutX((width / 2));
        tracksRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(tracksRadialMenu);
        radialMenus.add(tracksRadialMenu);
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

    private void open(String description, String... des) {
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

    private void newSector() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackSector");
        if (zone != null) {
            zone.newSector();
        } else {
            System.out.println("ça plante");
        }
    }

    private void drawerOn() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.drawerOn();
        } else {
            System.out.println("ça plante");
        }
    }

    private void savePolygon() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.savePolygon();
        } else {
            System.out.println("ça plante");
        }
    }

    private void saveAllPolygons() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.saveAllPolygons();
        } else {
            System.out.println("ça plante");
        }
    }

    private void loadPolygons() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.loadPolygons();
        } else {
            System.out.println("ça plante");
        }
    }

    private void polyShapeOn() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.polyShapeOn();
        } else {
            System.out.println("ça plante");
        }
    }

    private void ellipseShapeOn() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.ellipseShapeOn();
        } else {
            System.out.println("ça plante");
        }
    }

    private void circleShapeOn() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.circleShapeOn();
        } else {
            System.out.println("ça plante");
        }
    }

    private void quadShapeOn() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.quadShapeOn();
        } else {
            System.out.println("ça plante");
        }
    }

    private void freeHandOn() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.freeHandOn();
        } else {
            System.out.println("ça plante");
        }
    }

    private void createCpaZone() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.createCpaZone();
        } else {
            System.out.println("ça plante");
        }
    }

    private void createPath() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.createPath();
        } else {
            System.out.println("ça plante");
        }
    }

    private void activatePath() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.activatePath();
        } else {
            System.out.println("ça plante");
        }
    }

    private void savePath() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.savePath();
        } else {
            System.out.println("ça plante");
        }
    }

    private void loadPath() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.loadPath();
        } else {
            System.out.println("ça plante");
        }
    }

    private void createCpaZone500() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.createCpaZone(500);
        } else {
            System.out.println("ça plante");
        }
    }

    private void createCpaZone1000() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.createCpaZone(1000);
        } else {
            System.out.println("ça plante");
        }
    }

    private void activateCpaZone() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.activateCpaZone();
        } else {
            System.out.println("ça plante");
        }
    }
    
    private void createRule() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.createRule();
        } else {
            System.out.println("ça plante");
        }
    }
    
    private void activateRule() {
        zone = (ZoneDriver) instrumentDrivers.get("GpsTrackPolygon");
        if (zone != null) {
            zone.activateRule();
        } else {
            System.out.println("ça plante");
        }
    }

    private void openWMS(String description, String url) {
        webDriverManagerServices.handleOpenFiles(url);
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

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
