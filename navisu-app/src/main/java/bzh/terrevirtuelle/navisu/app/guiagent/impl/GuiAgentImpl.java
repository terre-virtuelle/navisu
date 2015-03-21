 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgent;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
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
import bzh.terrevirtuelle.navisu.widgets.dock.Dock;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItem;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItemFactory;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenu;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenuBuilder;

import gov.nasa.worldwind.util.StatusBar;
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
    @UsedService
    GuiAgentServices guiAgentServices;

    private Scene scene;
    protected Stage stage;
    protected StackPane root;
    protected static GuiAgentController ctrl = null;
    protected JobsManager jobsManager;
    protected StatusBar statusBar;
    protected int width;
    protected int height;
    protected static final String TITLE = "NaVisu";
    protected static final String ICON_PATH = "bzh/terrevirtuelle/navisu/app/guiagent/impl/";
    protected static final String GUI_AGENT_FXML = "GuiAgent.fxml";
    protected final ImageView mobOffImg = new ImageView(ICON_PATH + "MOB_Off.png");
    protected final ImageView mobOnImg = new ImageView(ICON_PATH + "MOB_On.png");

    boolean firstInstruments = true;
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
    public void showGui(Stage stage, int width, int height) {
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.jobsManager = JobsManager.create();
        final FXMLLoader loader = new FXMLLoader();
        try {
            root = loader.load(GuiAgentImpl.class.getResourceAsStream(GUI_AGENT_FXML));
            ctrl = loader.getController();
        } catch (IOException e) {
            LOGGER.severe("Cannot load " + GUI_AGENT_FXML + " !");
            System.exit(0);
        }

        scene = new Scene(root, this.width, this.height, Color.ALICEBLUE);
        this.loadCss(scene);

        createDockWidget(scene);
        createMOBWidget(scene);

        createBooksRadialWidget();
        createInstrumentsRadialWidget();
        createMeteoRadialWidget();
        createToolsRadialWidget();
        createChartsRadialWidget();
        createTidesRadialWidget();

        // Place scene components
        ctrl.leftBorderPane.setCenter(layerTreeServices.getDisplayService().getDisplayable());
        ctrl.centerStackPane.getChildren().add(geoViewServices.getDisplayService().getDisplayable());
        ctrl.statusBorderPane.setRight(jobsManager.getDisplay().getDisplayable());

        // Initialize menu
        this.menuServices.setMenuComponent(ctrl.menuBar);
        this.initializeMenuItems(this.menuServices);

        stage.setTitle(TITLE);
        stage.setOnCloseRequest(e -> {
            LOGGER.info("Stop Application");
            ComponentManager.componentManager.stopApplication();
            System.exit(0);
        });

        // Test avant les Displays
        //------------ HUD widgets ---------------------------------------------       
        //------------------- Speedo - hide with Ctrl-S -----------------
        /*
         WidgetController widgetController1 = new WidgetController(KeyCode.H, KeyCombination.CONTROL_DOWN);
         HUD_3_2_1_Controller hud_3 = new HUD_3_2_1_Controller();
         guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, widgetController1);
         widgetController1.add(hud_3);
         root.getChildren().add(hud_3);
         // hud_3.schedule();
         */
        //--------------------Speedo_end---------------
        stage.setScene(scene);
        //   stage.setMaximized(true);
        stage.show();

    }
// ------------------------ HUD widgets end ------------------------------

    private void testMenuItem() {
        System.out.println("testMenuItem");
    }

    //--------------BOOKS------------------
    private void createBooksRadialWidget() {
        booksRadialMenu = RadialMenuBuilder.create()
                //  .innerRadius(30).outerRadius(60).length(360).gap(2)
                .centralImage("centreradialmenu60.png")
                .stageItem(0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 1, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 2, "vide.png", (e) -> testMenuItem())
                .stageItem(1, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(2, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(3, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .build();

        booksRadialMenu.setLayoutX((width / 2) - 50);
        booksRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(booksRadialMenu);
    }

    private void showBooksMenu() {
        firstBooksRadialMenu = firstBooksRadialMenu != true;
        booksRadialMenu.setVisible(firstBooksRadialMenu);
    }

    //--------------INSTRUMENTS------------------
    private void createInstrumentsRadialWidget() {
        instrumentsRadialMenu = RadialMenuBuilder.create()
                .centralImage("instrumentsradialmenu150.png")
                .stageItem(0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 1, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 2, "vide.png", (e) -> testMenuItem())
                .stageItem(1, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(2, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(3, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
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
                .stageItem(0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 1, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 2, "vide.png", (e) -> testMenuItem())
                .stageItem(1, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(2, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(3, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
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
                .stageItem(0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 1, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 2, "vide.png", (e) -> testMenuItem())
                .stageItem(1, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(2, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(3, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .build();

        tidesRadialMenu.setLayoutX((width / 2) - 10);
        tidesRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(tidesRadialMenu);
    }

    private void showTidesMenu() {
        firstTidesRadialMenu = firstTidesRadialMenu != true;
        tidesRadialMenu.setVisible(firstTidesRadialMenu);
    }

    //--------------CHARTS------------------
    private void createChartsRadialWidget() {
        chartsRadialMenu = RadialMenuBuilder.create()
                .centralImage("chartsradialmenu150.png")
                .stageItem(0, "vectorielle.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 1, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 2, "vide.png", (e) -> testMenuItem())
                .stageItem(1, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(2, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(3, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .build();

        chartsRadialMenu.setLayoutX((width / 2) - 10);
        chartsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(chartsRadialMenu);
    }

    private void showChartsMenu() {
        firstChartsRadialMenu = firstChartsRadialMenu != true;
        chartsRadialMenu.setVisible(firstChartsRadialMenu);
    }

    //--------------TOOLS------------------
    private void createToolsRadialWidget() {
        toolsRadialMenu = RadialMenuBuilder.create()
                .centralImage("toolsradialmenu150.png")
                .stageItem(0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 1, "vide.png", (e) -> testMenuItem())
                .stageItem(0, 2, "vide.png", (e) -> testMenuItem())
                .stageItem(1, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(2, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .stageItem(3, "vide.png", 0, "vide.png", 0, "vide.png", (e) -> testMenuItem())
                .build();

        toolsRadialMenu.setLayoutX((width / 2));
        toolsRadialMenu.setLayoutY(height / 2);
        root.getChildren().add(toolsRadialMenu);
    }

    private void showToolsMenu() {
        firstToolsRadialMenu = firstToolsRadialMenu != true;
        toolsRadialMenu.setVisible(firstToolsRadialMenu);
    }

    /**
     * *******************************************
     * MOB - Man Over Board **************************************************
     */
    private void createMOBWidget(Scene scene) {

        Group mob = new Group();
        mob.getChildren().add(mobOffImg);
        //  mob.getChildren().add(mobOnImg);
        root.getChildren().add(mob);
        mob.setTranslateX(600.0);
        mob.setTranslateY(-80.0);
        //  mob.setVisible(true);
        // mob.getChildren().add(swingNode);
        StackPane.setAlignment(mob, Pos.BOTTOM_CENTER);
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
        // return ctrl.centerStackPane;
        return root;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
