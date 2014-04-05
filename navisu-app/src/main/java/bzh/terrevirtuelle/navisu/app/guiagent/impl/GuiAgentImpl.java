/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgent;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.Dock;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.DockItem;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.DockItemFactory;
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
            = new ImageView(ICON_PATH + "dock3.png");
    final Dock dock = new Dock(ICONS);


    public static final DockItem[] ICONS = new DockItem[]{
        DockItemFactory.newImageItem("AIS", ICON_PATH + "AIS.png", (e) -> System.out.println("AIS")),
        DockItemFactory.newImageItem("GPS", ICON_PATH + "GPS.png", (e) -> System.out.println("GPS")),
        DockItemFactory.newImageItem("Compass", ICON_PATH + "compass.png", (e) -> System.out.println("Compass")),
        DockItemFactory.newImageItem("Sounder", ICON_PATH + "sounder.png", (e) -> System.out.println("Sounder"))
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
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void createRadialWidget() {

        ImageView l1i1Img = new ImageView(new Image(getClass().getResourceAsStream("zoom-in.png")));
        ImageView l1i2Img = new ImageView(new Image(getClass().getResourceAsStream("zoom-out.png")));

        RadialMenuRootItem rootItem = new RadialMenuRootItem();

        RadialMenuItem level1Item1 = new RadialMenuItem(l1i1Img);
        RadialMenuItem level1Item2 = new RadialMenuItem(l1i2Img);

        rootItem.addItem(level1Item1);
        rootItem.addItem(level1Item2);

        RadialMenu radialMenu = new RadialMenu(rootItem);
        radialMenu.setFullMenuAnge(90);
        radialMenu.setStartRotationAngle(180);

        root.getChildren().add(radialMenu);
        StackPane.setAlignment(radialMenu, Pos.TOP_RIGHT);

        radialMenu.show(this.width, -5);
    }

    private void createDockWidget(Scene scene) {
        Group groupDock = new Group();
        groupDock.getChildren().add(basedock);
        groupDock.getChildren().add(dock);
        root.getChildren().add(groupDock);
        dock.setLayoutX(85.0);
        dock.setLayoutY(35.0);
        dock.setOrientation(Orientation.HORIZONTAL);
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
