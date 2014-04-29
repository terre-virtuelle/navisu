/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.jojal.widget.radialmenu.RadialMenu;
import bzh.jojal.widget.radialmenu.RadialMenuItem;
import bzh.jojal.widget.radialmenu.RadialMenuRootItem;
import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgent;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.widgets.dock.Dock;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItem;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItemFactory;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.impl.GeoViewImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.LayerCheckTreeImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.impl.MenuManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.OptionsManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.tools.AnimationFactory;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import javafx.animation.Animation;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.capcaval.c3.component.annotation.SubComponent;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.geometry.Insets;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */

public class GuiAgentImpl
        implements GuiAgent, GuiAgentServices {

    private static final Logger LOGGER = Logger.getLogger(GuiAgentImpl.class.getName());

    private static final String NAVISU_LOOK_AND_FEEL_PATH = "css/navisu.css";

    @SubComponent
    OptionsManagerImpl optionsManager;
    @UsedService
    OptionsManagerServices optionsManagerServices;

    @SubComponent
    MenuManagerImpl menu;
    @UsedService
    MenuManagerServices menuServices;

    @SubComponent
    LayerCheckTreeImpl layerTree;
    @UsedService
    LayerTreeServices layerTreeServices;

    @SubComponent
    GeoViewImpl geoView;
    @UsedService
    GeoViewServices geoViewServices;

    protected JobsManager jobsManager;

    protected int width;
    protected int height;
    protected static final String ICON_PATH = "bzh/terrevirtuelle/navisu/app/guiagent/impl/";
    protected Stage stage;
    protected StackPane root;
    static GuiAgentController ctrl = null;
    final ImageView basedock
            = new ImageView(ICON_PATH + "dock.png");
    final Dock dock = new Dock(ICONS);
    final Dock dock0 = new Dock(ICONS0);

    public static final DockItem[] ICONS = new DockItem[]{
        //DockItemFactory.newImageItem("MOB", ICON_PATH + "MOB.png", (e) -> System.out.println("MOB")),
        DockItemFactory.newImageItem("Config", ICON_PATH + "config.png", (e) -> System.out.println("Config")),
        DockItemFactory.newImageItem("User tools", ICON_PATH + "tools.png", (e) -> System.out.println("tools")),
        DockItemFactory.newImageItem("Charts", ICON_PATH + "charts.png", (e) -> System.out.println("Charts")),
        DockItemFactory.newImageItem("Tides", ICON_PATH + "tides.png", (e) -> System.out.println("Tides & streams")),
        DockItemFactory.newImageItem("Meteo", ICON_PATH + "meteo.png", (e) -> System.out.println("Meteo")),
        DockItemFactory.newImageItem("Simulations", ICON_PATH + "flou.png", (e) -> System.out.println("A venir 0")),
        DockItemFactory.newImageItem("Instruments", ICON_PATH + "sounder.png", (e) -> System.out.println("Sounder")),
        DockItemFactory.newImageItem("Engine", ICON_PATH + "vide.png", (e) -> System.out.println("A venir 1")),
        DockItemFactory.newImageItem("Logbook", ICON_PATH + "book.png", (e) -> System.out.println("Diary")),
        DockItemFactory.newImageItem("Maintenance", ICON_PATH + "guide.png", (e) -> System.out.println("Guide")),
        DockItemFactory.newImageItem("Whitebook", ICON_PATH + "whitebook.png", (e) -> System.out.println("White Book")),};
    public static final DockItem[] ICONS0 = new DockItem[]{
        /* Invisible icons just for testing margins */
        //DockItemFactory.newImageItem("", ICON_PATH + "invisible.png", (e) -> System.out.println("")),
        //DockItemFactory.newImageItem("", ICON_PATH + "invisible.png", (e) -> System.out.println("")),
        DockItemFactory.newImageItem("AIS", ICON_PATH + "AISvertical.png", (e) -> System.out.println("AIS")),
        DockItemFactory.newImageItem("GPS", ICON_PATH + "GPSvertical.png", (e) -> System.out.println("GPS")),
        DockItemFactory.newImageItem("Compass", ICON_PATH + "compassvertical.png", (e) -> System.out.println("Compass")),
        DockItemFactory.newImageItem("Sounder", ICON_PATH + "soundervertical.png", (e) -> System.out.println("Sounder")),
        DockItemFactory.newImageItem("Wind", ICON_PATH + "windvertical.png", (e) -> System.out.println("Wind")),
    };

    @Override
    public void showGui(Stage stage, int width, int height) {

        this.width = width;
        this.height = height;

        this.stage = stage;

        this.jobsManager = JobsManager.create();

        final FXMLLoader loader = new FXMLLoader();

        try {
            root = loader.load(GuiAgentImpl.class.getResourceAsStream("GuiAgent.fxml"));
            ctrl = loader.getController();

        } catch (IOException e) {
            LOGGER.severe("Cannot load GuiAgent.fxml !");
            System.exit(0);
        }

        Scene scene = new Scene(root, this.width, this.height, Color.ALICEBLUE);
        this.loadCss(scene);

        // Create Dock Widget
        createDockWidget(scene);

        // Create Radial Widget
        createRadialWidget();

        // Place scene components
        ctrl.leftBorderPane.setCenter(layerTreeServices.getDisplayService().getDisplayable());
        ctrl.centerStackPane.getChildren().add(geoViewServices.getDisplayService().getDisplayable());
        ctrl.statusBorderPane.setRight(jobsManager.getDisplay().getDisplayable());

        // Initialize menu
        this.menuServices.setMenuComponent(ctrl.menuBar);
        this.initializeMenuItems(this.menuServices);

        stage.setTitle("NaVisu");
        stage.setOnCloseRequest(e -> {
            LOGGER.info("Stop Application");
            ComponentManager.componentManager.stopApplication();
            System.exit(0);
        });

       // setFullScreen(true);
        stage.setScene(scene);
      //stage.setMaximized(true);
        stage.show();
    }

    
    /*private void createRadialWidget() {

        //TODO Refactor All of this code
        ImageView zoomInImg = new ImageView(new Image(getClass().getResourceAsStream("zoom-in.png")));
        ImageView zoomOutImg = new ImageView(new Image(getClass().getResourceAsStream("zoom-out.png")));
        

        RadialMenuRootItem rootItem = new RadialMenuRootItem();

        RadialMenuItem zoomInItem = new RadialMenuItem(zoomInImg);
        RadialMenuItem zoomOutItem = new RadialMenuItem(zoomOutImg);

        rootItem.addItem(zoomInItem);
        rootItem.addItem(zoomOutItem);

        RadialMenu radialMenu = new RadialMenu(rootItem);
        radialMenu.setFullMenuAnge(90);  //FIXME setFullMenuAnge -> setFullMenuAngle
        radialMenu.setStartRotationAngle(180);

        root.getChildren().add(radialMenu);
        StackPane.setAlignment(radialMenu, Pos.TOP_RIGHT);

        radialMenu.show(this.width, -5);
    }*/
    private void createRadialWidget() {

        //TODO Refactor All of this code
        ImageView zoomInImg = new ImageView(new Image(getClass().getResourceAsStream("radialmenu/zoom-in.png")));
        ImageView zoomOutImg = new ImageView(new Image(getClass().getResourceAsStream("radialmenu/zoom-out.png")));
        ImageView GPSComImg = new ImageView(new Image(getClass().getResourceAsStream("radialmenu/GPSComPane.png")));
        ImageView GPSprecImg = new ImageView(new Image(getClass().getResourceAsStream("radialmenu/GPSprecision.png")));
        ImageView GPSlocImg = new ImageView(new Image(getClass().getResourceAsStream("radialmenu/GPSlocation.png")));
        RadialMenuRootItem rootItem = new RadialMenuRootItem();

        RadialMenuItem zoomInItem = new RadialMenuItem(zoomInImg);
        RadialMenuItem zoomOutItem = new RadialMenuItem(zoomOutImg);
        RadialMenuItem test1Item = new RadialMenuItem(GPSComImg);
        RadialMenuItem test2Item = new RadialMenuItem(GPSprecImg);
        RadialMenuItem test3Item = new RadialMenuItem(GPSlocImg);

        rootItem.addItem(zoomInItem);
        rootItem.addItem(zoomOutItem);
        rootItem.addItem(test1Item);
        rootItem.addItem(test2Item);
        rootItem.addItem(test3Item);

        RadialMenu radialMenu = new RadialMenu(rootItem);
        radialMenu.setFullMenuAnge(360);  //FIXME setFullMenuAnge -> setFullMenuAngle
        radialMenu.setStartRotationAngle(180);

        root.getChildren().add(radialMenu);
        StackPane.setAlignment(radialMenu, Pos.CENTER);

        radialMenu.show(this.width, -5);
    }

    private void createDockWidget(Scene scene) {
        ImageView MOBImg = new ImageView(new Image(getClass().getResourceAsStream("MOBbouton_2.png")));
        Group groupDock = new Group();
        
        groupDock.getChildren().add(basedock);
        groupDock.getChildren().add(dock);
        groupDock.getChildren().add(MOBImg);
        root.getChildren().add(groupDock);
        MOBImg.setLayoutX(1550.0);
       // MOBImg.setLayoutY(100.0);
        basedock.setLayoutX(450.0);
        basedock.setLayoutY(100.0);
        dock.setLayoutX(525.0);
        dock.setLayoutY(40.0);
        dock.setOrientation(Orientation.HORIZONTAL);
        /* margins if necessity to adjust position of whole group */
        //StackPane.setMargin(groupdock,(new Insets(0, 0, 0, 0)));
        StackPane.setAlignment(groupDock, Pos.BOTTOM_CENTER);
        Animation downAnimation = AnimationFactory.newTranslateAnimation(groupDock, 200, 300);
        Animation upAnimation = AnimationFactory.newTranslateAnimation(groupDock, 200, 0);
        scene.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.DOWN) {
                downAnimation.play();
            }
            if (ke.getCode() == KeyCode.UP) {
                upAnimation.play();
            }
        });
        root.getChildren().add(dock0);
        dock0.setOrientation(Orientation.VERTICAL);
       /* adding margins for vertical dock */
        StackPane.setMargin(dock0,(new Insets(150, 10, 0, 0)));
        StackPane.setAlignment(dock0, Pos.TOP_RIGHT);

    }

    private void loadCss(Scene scene) {
        scene.getStylesheets().add(getClass().getResource(NAVISU_LOOK_AND_FEEL_PATH).toExternalForm());
    }

    protected void initializeMenuItems(final MenuManagerServices menuServices) {

        MenuItem fileMenuItem = new MenuItem(Translator.tr("menu.file.exit"));
        fileMenuItem.setOnAction(e -> {

            ComponentManager.componentManager.stopApplication();
            System.exit(0);
        });
        menuServices.addMenuItem(DefaultMenuEnum.FILE, fileMenuItem);

        MenuItem preferenceMenuItem = new MenuItem(Translator.tr("menu.edit.preferences"));
        preferenceMenuItem.setOnAction(e -> optionsManagerServices.show());

        menuServices.addMenuItem(DefaultMenuEnum.EDIT, preferenceMenuItem);
    }

    @Override
    public JobsManager getJobsManager() {
        return this.jobsManager;
    }

    @Override
    public boolean isFullScreen() {
        return this.stage.isFullScreen();
    }

    @Override
    public void setFullScreen(boolean fullScreen) {
        this.stage.setFullScreen(true);
    }

    @Override
    public StackPane getRoot() {
        return ctrl.centerStackPane;
    }
}
