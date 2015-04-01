package bzh.terrevirtuelle.navisu.app.guiagent.dock.impl;

import bzh.terrevirtuelle.navisu.app.drivers.driver.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
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
import javafx.animation.Animation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.capcaval.c3.component.annotation.UsedService;

/*
 * NaVisu
 *
 * @date 23 mars 2015
 * @author Serge Morvan
 */
public class DockManagerImpl
        implements DockManager, DockManagerServices, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    DriverManagerServices driverManagerServices;
    @UsedService
    WebDriverManagerServices webDriverManagerServices;

    protected static final Logger LOGGER = Logger.getLogger(DockManagerImpl.class.getName());

    protected static final String ICON_PATH = "bzh/terrevirtuelle/navisu/app/guiagent/impl/";
    protected final String EMODNET = "http://ows.emodnet-bathymetry.eu/wms";
    protected final String GEBCO = "http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?";
    protected RadialMenu booksRadialMenu;
    protected RadialMenu instrumentsRadialMenu;
    protected RadialMenu meteoRadialMenu;
    protected RadialMenu tidesRadialMenu;
    protected RadialMenu chartsRadialMenu;
    protected RadialMenu toolsRadialMenu;
    boolean firstBooksRadialMenu = false;
    boolean firstInstrumentsRadialMenu = false;
    boolean firstMeteoRadialMenu = false;
    boolean firstToolsRadialMenu = false;
    boolean firstChartsRadialMenu = false;
    boolean firstTidesRadialMenu = false;
    protected ImageView centerImg;
    protected int width;
    protected int height;
    private StackPane root = null;
    private Scene scene = null;
    public final DockItem[] ICONS = new DockItem[]{
        DockItemFactory.newImageItem("user tools", ICON_PATH + "dock_icons/tools.png", (e) -> showToolsMenu()),
        DockItemFactory.newImageItem("charts", ICON_PATH + "dock_icons/charts.png", (e) -> showChartsMenu()),
        DockItemFactory.newImageItem("tides", ICON_PATH + "dock_icons/tides.png", (e) -> showTidesMenu()),
        DockItemFactory.newImageItem("meteo", ICON_PATH + "dock_icons/meteo.png", (e) -> showMeteoMenu()),
        DockItemFactory.newImageItem("instrum.", ICON_PATH + "dock_icons/instruments.png", (e) -> showInstrumentsMenu()),
        DockItemFactory.newImageItem("logbook", ICON_PATH + "dock_icons/book.png", (e) -> showBooksMenu())
    };
    final Dock dock = new Dock(ICONS);

    @Override
    public void componentInitiated() {

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
        //   createMOBWidget(scene);
        createBooksRadialWidget();
        //   createInstrumentsRadialWidget();
        //  createMeteoRadialWidget();
        //   createToolsRadialWidget();
        createChartsRadialWidget();
        //  createTidesRadialWidget();
    }

    private void testMenuItem() {
        System.out.println("testMenuItem");
    }
//--------------CHARTS------------------

    private void createChartsRadialWidget() {
        chartsRadialMenu = RadialMenuBuilder.create()
                .centralImage("chartsradialmenu150.png")
                .createNode(0, "nav.png", 0, "vector.png", 0, "s57.png", (e) -> open("S57", ".000"))
                .createNode(0, "nav.png", 1, "raster.png", 0, "bsbkap.png", (e) -> open("BSB/KAP", ".KAP"))
                .createNode(0, "nav.png", 1, "raster.png", 1, "geotiff.png", (e) -> open("GeoTiff", ".tif"))
                .createNode(1, "bathy.png", 0, "images.png", 0, "emodnet.png", (e) -> openWMS("WMS", EMODNET))
                .createNode(1, "bathy.png", 0, "images.png", 1, "gebco.png", (e) -> openWMS("WMS", GEBCO))
                .createNode(1, "bathy.png", 1, "catalog.png", 1, "shom.png", (e) -> open("Catalog SHOM"))
                .createNode(2, "sediment.png", 0, "vide.png", 0, "vide.png", (e) -> openShp("data/",".shp"))
                .build();

        chartsRadialMenu.setLayoutX((width / 2) - 10);
        chartsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(chartsRadialMenu);
    }

    private void showChartsMenu() {
        firstChartsRadialMenu = firstChartsRadialMenu != true;
        chartsRadialMenu.setVisible(firstChartsRadialMenu);
    }

    //--------------BOOKS------------------
     private void createBooksRadialWidget() {
        booksRadialMenu = RadialMenuBuilder.create()
                .centralImage("booksradialmenu150.png")
                .createNode(0, "logbook.png", 0, "vide.png", 0, "vide.png", (e) -> open("S57", ".000"))
                .createNode(0, "logbook.png", 1, "vide.png", 0, "vide.png", (e) -> open("BSB/KAP", ".KAP"))                
                .createNode(1, "lightsbook.png", 0, "images.png", 0, "emodnet.png", (e) -> openWMS("WMS", EMODNET))                
                .createNode(1, "lightsbook.png", 1, "vide.png", 1, "vide.png", (e) -> open("Catalog SHOM"))                
                .createNode(2, "sailingbook.png", 0, "worldwide_sailing_directions.png", 0, "vide.png", (e) -> openShp("data/",".shp"))
                .createNode(2, "sailingbook.png", 1, "IrelandSouth_sailing_directions.png", 0, "vide.png", (e) -> openShp("data/",".shp"))
                .createNode(2, "sailingbook.png", 2, "UK_Welsh harbours_sailing_directions.png", 0, "vide.png", (e) -> openShp("data/",".shp"))
                .build();

        booksRadialMenu.setLayoutX((width / 2) - 10);
        booksRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(booksRadialMenu);
    }

    private void showBooksMenu() {
        firstBooksRadialMenu = firstBooksRadialMenu != true;
        booksRadialMenu.setVisible(firstBooksRadialMenu);
    }
    
    private void open(String test) {
        System.out.println("Test " + test);
    }

    private void open(String file, String des) {
       // driverManagerServices.open(new FileChooser.ExtensionFilter(file,des));
    }

    private void openShp(String description, String des) {
        driverManagerServices.open(new FileChooser.ExtensionFilter(description, des));
    }

    private void openWMS(String description, String url) {
        webDriverManagerServices.handleOpenFiles(url);
    }

    
    
    //--------------INSTRUMENTS------------------
    private void createInstrumentsRadialWidget() {
        instrumentsRadialMenu = RadialMenuBuilder.create()
                .centralImage("instrumentsradialmenu150.png")
                .build();

        instrumentsRadialMenu.setLayoutX((width / 2) - 40);
        instrumentsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(instrumentsRadialMenu);
    }

    private void showInstrumentsMenu() {
        firstInstrumentsRadialMenu = firstInstrumentsRadialMenu != true;
        instrumentsRadialMenu.setVisible(firstInstrumentsRadialMenu);
    }

    //--------------METEO------------------
    private void createMeteoRadialWidget() {
        meteoRadialMenu = RadialMenuBuilder.create()
                .centralImage("meteoradialmenu150.png")
                .build();

        meteoRadialMenu.setLayoutX((width / 2) - 30);
        meteoRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(meteoRadialMenu);
    }

    private void showMeteoMenu() {
        firstMeteoRadialMenu = firstMeteoRadialMenu != true;
        meteoRadialMenu.setVisible(firstMeteoRadialMenu);
    }

    //--------------TIDES------------------
    private void createTidesRadialWidget() {
        tidesRadialMenu = RadialMenuBuilder.create()
                .centralImage("tidesradialmenu150.png")
                .build();

        tidesRadialMenu.setLayoutX((width / 2) - 10);
        tidesRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(tidesRadialMenu);
    }

    private void showTidesMenu() {
        firstTidesRadialMenu = firstTidesRadialMenu != true;
        tidesRadialMenu.setVisible(firstTidesRadialMenu);
    }

    //--------------TOOLS------------------
    private void createToolsRadialWidget() {
        toolsRadialMenu = RadialMenuBuilder.create()
                .centralImage("toolsradialmenu150.png")
                .build();

        toolsRadialMenu.setLayoutX((width / 2));
        toolsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(toolsRadialMenu);
    }

    private void showToolsMenu() {
        firstToolsRadialMenu = firstToolsRadialMenu != true;
        toolsRadialMenu.setVisible(firstToolsRadialMenu);
    }

    private void createDockWidget(Scene scene) {

        Group groupDock = new Group();
        groupDock.getChildren().add(dock);
        root.getChildren().add(groupDock);
        dock.setLayoutX(475.0);
        dock.setLayoutY(40.0);
        dock.setOrientation(Orientation.HORIZONTAL);
        /* margins if necessity to adjust position of whole group */
        //StackPane.setMargin(groupdock,(new Insets(0, 0, 0, 0)));
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

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
