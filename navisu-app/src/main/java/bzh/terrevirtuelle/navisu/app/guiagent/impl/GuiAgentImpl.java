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
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenuContainer;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenuItem;

import gov.nasa.worldwind.util.StatusBar;
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
import javafx.embed.swing.SwingNode;

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

    protected JobsManager jobsManager;
    protected StatusBar statusBar;
    protected int width;
    protected int height;
    protected static final String ICON_PATH = "bzh/terrevirtuelle/navisu/app/guiagent/impl/";
    protected Stage stage;
    protected StackPane root;
    static GuiAgentController ctrl = null;
    protected final ImageView basedock = new ImageView(ICON_PATH + "dock.png");
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
    SwingNode swingNode;
    protected final ImageView mobOffImg
            = new ImageView(ICON_PATH + "MOB_Off.png");
    protected final ImageView mobOnImg
            = new ImageView(ICON_PATH + "MOB_On.png");

    public final DockItem[] ICONS = new DockItem[]{
        DockItemFactory.newImageItem("user tools", ICON_PATH + "tools.png", (e) -> showToolsMenu()),
        DockItemFactory.newImageItem("charts", ICON_PATH + "charts.png", (e) -> showChartsMenu()),
        DockItemFactory.newImageItem("tides", ICON_PATH + "tides.png", (e) -> showTidesMenu()),
        DockItemFactory.newImageItem("meteo", ICON_PATH + "meteo.png", (e) -> showMeteoMenu()),
        DockItemFactory.newImageItem("instrum.", ICON_PATH + "instruments.png", (e) -> showInstrumentsMenu()),
        DockItemFactory.newImageItem("logbook", ICON_PATH + "book.png", (e) -> showBooksMenu()), //DockItemFactory.newImageItem("MOB", ICON_PATH + "MOB.png", (e) -> System.out.println("MOB")),
    };
    public final DockItem[] ICONS0 = new DockItem[]{
        /* Invisible icons just for testing margins */
        //DockItemFactory.newImageItem("", ICON_PATH + "invisible.png", (e) -> System.out.println("")),
        //DockItemFactory.newImageItem("", ICON_PATH + "invisible.png", (e) -> System.out.println("")),
        DockItemFactory.newImageItem("AIS", ICON_PATH + "AISvertical.png", (e) -> System.out.println("AIS")),
        DockItemFactory.newImageItem("GPS", ICON_PATH + "GPSvertical.png", (e) -> showInstrumentsMenu()),
        DockItemFactory.newImageItem("Compass", ICON_PATH + "compassvertical.png", (e) -> System.out.println("Compass")),
        DockItemFactory.newImageItem("Sounder", ICON_PATH + "soundervertical.png", (e) -> System.out.println("Sounder")),
        DockItemFactory.newImageItem("Wind", ICON_PATH + "windvertical.png", (e) -> System.out.println("Wind")),};
    final Dock dock = new Dock(ICONS);
    private Scene scene;

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

        scene = new Scene(root, this.width, this.height, Color.ALICEBLUE);

        this.loadCss(scene);

        // Create Dock Widget
        createDockWidget(scene);

        // Create MOB as Widget
        createMOBWidget(scene);

        // Create Books Radial Widget
        createBooksRadialWidget();

        // Create Radial Widget (for instruments)
        createRadialWidget();

        // Create Meteo Radial Widget
        createMeteoRadialWidget();

        // Create Tools Radial Widget
        createToolsRadialWidget();

        // Create Charts Radial Widget
        createChartsRadialWidget();

// Create Tides Radial Widget
        createTidesRadialWidget();

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

        // Test avant les Displays
        //------------ HUD widgets ---------------------------------------------       
        /*WidgetController widgetController1 = new WidgetController(KeyCode.H, KeyCombination.CONTROL_DOWN);
        HUD_3_2_1_Controller hud_3 = new HUD_3_2_1_Controller();
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, widgetController1);
        widgetController1.add(hud_3);
        root.getChildren().add(hud_3);
        */
        // hud_3.schedule();
        /*WidgetController widgetController2 = new WidgetController(KeyCode.R, KeyCombination.CONTROL_DOWN);
        Radar_Controller radar_1 = new Radar_Controller();
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, widgetController2);
        widgetController2.add(radar_1);
        root.getChildren().add(radar_1);
        radar_1.schedule();
        */
        // Test appel a HTML5-Javascript
      //CloudMenu cloudMenu = new CloudMenu();
      // root.getChildren().add(cloudMenu);
        // setFullScreen(true);
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();

    }
// ------------------------ HUD widgets end ------------------------------
    /*
     private void showInstruments() {
     if (firstInstruments == true) {
     root.getChildren().add(dock0);
     dock0.setOrientation(Orientation.VERTICAL);

     StackPane.setMargin(dock0, (new Insets(150, 10, 0, 0)));
     StackPane.setAlignment(dock0, Pos.TOP_RIGHT);
     firstInstruments = false;
     }
     }*/

    private void showBooksMenu() {
        //System.out.println("showBooksMenu");
        firstBooksRadialMenu = firstBooksRadialMenu != true;
        booksRadialMenu.setVisible(firstBooksRadialMenu);
    }

    private void showInstrumentsMenu() {
        // System.out.println("showInstrumentsMenu");
        firstInstrumentsRadialMenu = firstInstrumentsRadialMenu != true;
        instrumentsRadialMenu.setVisible(firstInstrumentsRadialMenu);
        // centerImg.setVisible(firstInstrumentsRadialMenu);
    }

    private void showMeteoMenu() {
        // System.out.println("showMeteoMenu");
        firstMeteoRadialMenu = firstMeteoRadialMenu != true;
        meteoRadialMenu.setVisible(firstMeteoRadialMenu);
    }

    private void showTidesMenu() {
        // System.out.println("showTidesMenu");
        firstTidesRadialMenu = firstTidesRadialMenu != true;
        tidesRadialMenu.setVisible(firstTidesRadialMenu);
    }

    private void showChartsMenu() {
        // System.out.println("showChartsMenu");
        firstChartsRadialMenu = firstChartsRadialMenu != true;
        chartsRadialMenu.setVisible(firstChartsRadialMenu);
    }

    private void showToolsMenu() {
        //  System.out.println("showToolsMenu");
        firstToolsRadialMenu = firstToolsRadialMenu != true;
        toolsRadialMenu.setVisible(firstToolsRadialMenu);
    }

    //------------------ Option center
    //--------------BOOKS------------------
    private void createBooksRadialWidget() {

        centerImg = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/centreradialmenu60.png")));

        //First Stage Item 1
        RadialMenuContainer stage1Item1 = new RadialMenuContainer();
        ImageView stage1Item1Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/videvert.png")));
        stage1Item1.setImage(stage1Item1Img);

        //First Stage Item 2
        RadialMenuContainer stage1Item2 = new RadialMenuContainer();
        ImageView stage1Item2Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/videbleu.png")));
        stage1Item2.setImage(stage1Item2Img);

        //First Stage Item 3
        RadialMenuContainer stage1Item3 = new RadialMenuContainer();
        ImageView stage1Item3Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/viderouge.png")));
        stage1Item3.setImage(stage1Item3Img);

        //First Stage Item 4
        RadialMenuContainer stage1Item4 = new RadialMenuContainer();
        ImageView stage1Item4Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/videorange.png")));
        stage1Item4.setImage(stage1Item4Img);

//---------------------------------------------------
        //Stage 2 item 1
        RadialMenuItem stageTwoItem1 = new RadialMenuItem();
        ImageView stageTwoItem1Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/videbleu.png")));
        stageTwoItem1.setImage(stageTwoItem1Img);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem2 = new RadialMenuItem();
        ImageView stageTwoItem2Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/viderouge.png")));
        stageTwoItem2.setImage(stageTwoItem2Img);

        //Stage 2 item 3
        RadialMenuItem stageTwoItem3 = new RadialMenuItem();
        ImageView stageTwoItem3Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/videmagenta.png")));
        stageTwoItem2.setImage(stageTwoItem3Img);

        //Stage 2 item 4
        RadialMenuItem stageTwoItem4 = new RadialMenuItem();
        ImageView stageTwoItem4Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/videvert.png")));
        stageTwoItem2.setImage(stageTwoItem4Img);

        //Stage 2 item 5
        RadialMenuItem stageTwoItem5 = new RadialMenuItem();
        ImageView stageTwoItem5Img = new ImageView(new Image(getClass().getResourceAsStream("booksradialmenu/videorange.png")));
        stageTwoItem5.setImage(stageTwoItem5Img);

//---------------Stage2 to Stage1 ------------------------       
        stage1Item1.addItem(stageTwoItem1);
        stage1Item1.addItem(stageTwoItem2);
        stage1Item1.addItem(stageTwoItem3);
        stage1Item1.addItem(stageTwoItem4);
        stage1Item1.addItem(stageTwoItem5);

        booksRadialMenu = new RadialMenu(30, 60, 360, 2);

        booksRadialMenu.addRootItem(stage1Item1);
        booksRadialMenu.addRootItem(stage1Item2);
        booksRadialMenu.addRootItem(stage1Item3);
        booksRadialMenu.addRootItem(stage1Item4);

        //Use setManaged(false) to not let the parent's layout resize our component !
        booksRadialMenu.setManaged(false);

        root.getChildren().add(booksRadialMenu);
        booksRadialMenu.getChildren().add(centerImg);
        centerImg.setLayoutX(-centerImg.getImage().getWidth() / 2);
        centerImg.setLayoutY(-centerImg.getImage().getHeight() / 2);
        booksRadialMenu.setLayoutX(width / 2);
        booksRadialMenu.setLayoutY(height / 2);
        booksRadialMenu.setVisible(false);
    }

    //--------------INSTRUMENTS------------------
    private void createRadialWidget() {

        centerImg = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/centreradialmenu150.png")));

        //First Stage Item 1
        RadialMenuContainer zoomInItem = new RadialMenuContainer();
        ImageView sounderImg = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/sounderradialombre.png")));
        zoomInItem.setImage(sounderImg);

        //First Stage Item 2
        RadialMenuContainer zoomOutItem = new RadialMenuContainer();
        ImageView windImg = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/windradialombre.png")));
        zoomOutItem.setImage(windImg);

        //First Stage Item 3
        RadialMenuContainer test1Item = new RadialMenuContainer();
        ImageView compassImg = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/compassradialombre.png")));
        test1Item.setImage(compassImg);

        //First Stage Item 4
        RadialMenuContainer test2Item = new RadialMenuContainer();
        ImageView gpsImg = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/GPSradialombre.png")));
        test2Item.setImage(gpsImg);

        //First Stage Item 5
        RadialMenuContainer test3Item = new RadialMenuContainer();
        ImageView aisImg = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/AISradialombre.png")));
        test3Item.setImage(aisImg);

        //---------------------------------------------------
        //Stage 2 item 1
        RadialMenuItem stageTwoItem1 = new RadialMenuItem();
        ImageView gpsImg2 = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/vide.png")));
        stageTwoItem1.setImage(gpsImg2);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem2 = new RadialMenuItem();
        ImageView compassImg2 = new ImageView(new Image(getClass().getResourceAsStream("instrumentsradialmenu/vide.png")));
        stageTwoItem2.setImage(compassImg2);

        zoomInItem.addItem(stageTwoItem1);
        zoomInItem.addItem(stageTwoItem2);

        instrumentsRadialMenu = new RadialMenu(70, 130, 360, 5);

        instrumentsRadialMenu.addRootItem(zoomInItem);
        instrumentsRadialMenu.addRootItem(zoomOutItem);
        instrumentsRadialMenu.addRootItem(test1Item);
        instrumentsRadialMenu.addRootItem(test2Item);
        instrumentsRadialMenu.addRootItem(test3Item);

        //Use setManaged(false) to not let the parent's layout resize our component !
        instrumentsRadialMenu.setManaged(false);

        root.getChildren().add(instrumentsRadialMenu);
        instrumentsRadialMenu.getChildren().add(centerImg);
        centerImg.setLayoutX(-centerImg.getImage().getWidth() / 2);
        centerImg.setLayoutY(-centerImg.getImage().getHeight() / 2);
        instrumentsRadialMenu.setLayoutX(width / 2);
        instrumentsRadialMenu.setLayoutY(height / 2);
        instrumentsRadialMenu.setVisible(false);
    }

    //--------------METEO------------------
    private void createMeteoRadialWidget() {

        centerImg = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/centreradialmenu150.png")));

        //First Stage Item 1
        RadialMenuContainer zoomInItem = new RadialMenuContainer();
        ImageView sounderImg = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/vide.png")));
        zoomInItem.setImage(sounderImg);

        //First Stage Item 2
        RadialMenuContainer zoomOutItem = new RadialMenuContainer();
        ImageView windImg = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/vide.png")));
        zoomOutItem.setImage(windImg);

        //First Stage Item 3
        RadialMenuContainer test1Item = new RadialMenuContainer();
        ImageView compassImg = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/vide.png")));
        test1Item.setImage(compassImg);

        //First Stage Item 4
        RadialMenuContainer test2Item = new RadialMenuContainer();
        ImageView gpsImg = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/vide.png")));
        test2Item.setImage(gpsImg);

        //First Stage Item 5
        RadialMenuContainer test3Item = new RadialMenuContainer();
        ImageView aisImg = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/vide.png")));
        test3Item.setImage(aisImg);

        //---------------------------------------------------
        //Stage 2 item 1
        RadialMenuItem stageTwoItem1 = new RadialMenuItem();
        ImageView gpsImg2 = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/vide.png")));
        stageTwoItem1.setImage(gpsImg2);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem2 = new RadialMenuItem();
        ImageView compassImg2 = new ImageView(new Image(getClass().getResourceAsStream("meteoradialmenu/vide.png")));
        stageTwoItem2.setImage(compassImg2);

        test1Item.addItem(stageTwoItem1);
        test2Item.addItem(stageTwoItem2);

        meteoRadialMenu = new RadialMenu(70, 130, 360, 5);

        //meteoRadialMenu.addRootItem(zoomInItem);
        //meteoRadialMenu.addRootItem(zoomOutItem);
        meteoRadialMenu.addRootItem(test1Item);
        meteoRadialMenu.addRootItem(test2Item);
        meteoRadialMenu.addRootItem(test3Item);

        //Use setManaged(false) to not let the parent's layout resize our component !
        meteoRadialMenu.setManaged(false);

        root.getChildren().add(meteoRadialMenu);
        meteoRadialMenu.getChildren().add(centerImg);
        centerImg.setLayoutX(-centerImg.getImage().getWidth() / 2);
        centerImg.setLayoutY(-centerImg.getImage().getHeight() / 2);
        meteoRadialMenu.setLayoutX(width / 2);
        meteoRadialMenu.setLayoutY(height / 2);
        meteoRadialMenu.setVisible(false);
    }

    //--------------TIDES------------------
    private void createTidesRadialWidget() {

        centerImg = new ImageView(new Image(getClass().getResourceAsStream("tidesradialmenu/centreradialmenu150.png")));

        //First Stage Item 1
        RadialMenuContainer zoomInItem = new RadialMenuContainer();
        ImageView zoominImg = new ImageView(new Image(getClass().getResourceAsStream("tidesradialmenu/videvert.png")));
        zoomInItem.setImage(zoominImg);

        //First Stage Item 2
        RadialMenuContainer zoomOutItem = new RadialMenuContainer();
        ImageView zoomoutImg = new ImageView(new Image(getClass().getResourceAsStream("tidesradialmenu/vide.png")));
        zoomOutItem.setImage(zoomoutImg);
//---------------------------------------------------
        //Stage 2 item 1
        RadialMenuItem stageTwoItem1 = new RadialMenuItem();
        ImageView gpsImg2 = new ImageView(new Image(getClass().getResourceAsStream("tidesradialmenu/videbleu.png")));
        stageTwoItem1.setImage(gpsImg2);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem2 = new RadialMenuItem();
        ImageView compassImg2 = new ImageView(new Image(getClass().getResourceAsStream("tidesradialmenu/videvert.png")));
        stageTwoItem2.setImage(compassImg2);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem3 = new RadialMenuItem();
        ImageView compassImg3 = new ImageView(new Image(getClass().getResourceAsStream("tidesradialmenu/vide.png")));
        stageTwoItem2.setImage(compassImg3);

        zoomInItem.addItem(stageTwoItem1);
        zoomInItem.addItem(stageTwoItem2);
        zoomInItem.addItem(stageTwoItem3);

        tidesRadialMenu = new RadialMenu(70, 130, 360, 5);

        tidesRadialMenu.addRootItem(zoomInItem);
        tidesRadialMenu.addRootItem(zoomOutItem);

        //Use setManaged(false) to not let the parent's layout resize our component !
        tidesRadialMenu.setManaged(false);

        root.getChildren().add(tidesRadialMenu);
        tidesRadialMenu.getChildren().add(centerImg);
        centerImg.setLayoutX(-centerImg.getImage().getWidth() / 2);
        centerImg.setLayoutY(-centerImg.getImage().getHeight() / 2);
        tidesRadialMenu.setLayoutX(width / 2);
        tidesRadialMenu.setLayoutY(height / 2);
        tidesRadialMenu.setVisible(false);
    }

    //--------------CHARTS------------------
    private void createChartsRadialWidget() {

        centerImg = new ImageView(new Image(getClass().getResourceAsStream("chartsradialmenu/centreradialmenu150.png")));

        //First Stage Item 1
        RadialMenuContainer rootItem1 = new RadialMenuContainer();
        ImageView rootItem1Img = new ImageView(new Image(getClass().getResourceAsStream("chartsradialmenu/configradial.png")));
        rootItem1.setImage(rootItem1Img);

        //First Stage Item 2
        RadialMenuContainer rootItem2 = new RadialMenuContainer();
        ImageView rootItem2Img = new ImageView(new Image(getClass().getResourceAsStream("chartsradialmenu/toolsradial.png")));
        rootItem2.setImage(rootItem2Img);
//---------------------------------------------------
        //Stage 2 item 1
        RadialMenuItem stageTwoItem1 = new RadialMenuItem();
        ImageView stageTwoImg1 = new ImageView(new Image(getClass().getResourceAsStream("chartsradialmenu/vide.png")));
        stageTwoItem1.setImage(stageTwoImg1);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem2 = new RadialMenuItem();
        ImageView stageTwoImg2 = new ImageView(new Image(getClass().getResourceAsStream("chartsradialmenu/videbleu.png")));
        stageTwoItem2.setImage(stageTwoImg2);

        //Stage 2 item 3
        RadialMenuItem stageTwoItem3 = new RadialMenuItem();
        ImageView stageTwoImg3 = new ImageView(new Image(getClass().getResourceAsStream("chartsradialmenu/videvert.png")));
        stageTwoItem2.setImage(stageTwoImg3);

        rootItem1.addItem(stageTwoItem1);
        rootItem1.addItem(stageTwoItem2);
        rootItem1.addItem(stageTwoItem3);

        chartsRadialMenu = new RadialMenu(70, 130, 360, 5);

        chartsRadialMenu.addRootItem(rootItem1);
        chartsRadialMenu.addRootItem(rootItem2);

        //Use setManaged(false) to not let the parent's layout resize our component !
        chartsRadialMenu.setManaged(false);

        root.getChildren().add(chartsRadialMenu);
        chartsRadialMenu.getChildren().add(centerImg);
        centerImg.setLayoutX(-centerImg.getImage().getWidth() / 2);
        centerImg.setLayoutY(-centerImg.getImage().getHeight() / 2);
        chartsRadialMenu.setLayoutX(width / 2);
        chartsRadialMenu.setLayoutY(height / 2);
        chartsRadialMenu.setVisible(false);
    }

    //--------------TOOLS------------------
    private void createToolsRadialWidget() {

        centerImg = new ImageView(new Image(getClass().getResourceAsStream("toolsradialmenu/centreradialmenu150.png")));

        //First Stage Item 1
        RadialMenuContainer zoomInItem = new RadialMenuContainer();
        ImageView zoominImg = new ImageView(new Image(getClass().getResourceAsStream("toolsradialmenu/configradial.png")));
        zoomInItem.setImage(zoominImg);

        //First Stage Item 2
        RadialMenuContainer zoomOutItem = new RadialMenuContainer();
        ImageView zoomoutImg = new ImageView(new Image(getClass().getResourceAsStream("toolsradialmenu/toolsradial.png")));
        zoomOutItem.setImage(zoomoutImg);
//---------------------------------------------------
        //Stage 2 item 1
        RadialMenuItem stageTwoItem1 = new RadialMenuItem();
        ImageView gpsImg2 = new ImageView(new Image(getClass().getResourceAsStream("toolsradialmenu/videbleu.png")));
        stageTwoItem1.setImage(gpsImg2);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem2 = new RadialMenuItem();
        ImageView compassImg2 = new ImageView(new Image(getClass().getResourceAsStream("toolsradialmenu/videvert.png")));
        stageTwoItem2.setImage(compassImg2);

        //Stage 2 item 2
        RadialMenuItem stageTwoItem3 = new RadialMenuItem();
        ImageView compassImg3 = new ImageView(new Image(getClass().getResourceAsStream("toolsradialmenu/vide.png")));
        stageTwoItem2.setImage(compassImg3);

        zoomInItem.addItem(stageTwoItem1);
        zoomInItem.addItem(stageTwoItem2);
        zoomInItem.addItem(stageTwoItem3);

        toolsRadialMenu = new RadialMenu(70, 130, 360, 5);

        toolsRadialMenu.addRootItem(zoomInItem);
        toolsRadialMenu.addRootItem(zoomOutItem);

        //Use setManaged(false) to not let the parent's layout resize our component !
        toolsRadialMenu.setManaged(false);

        root.getChildren().add(toolsRadialMenu);
        toolsRadialMenu.getChildren().add(centerImg);
        centerImg.setLayoutX(-centerImg.getImage().getWidth() / 2);
        centerImg.setLayoutY(-centerImg.getImage().getHeight() / 2);
        toolsRadialMenu.setLayoutX(width / 2);
        toolsRadialMenu.setLayoutY(height / 2);
        toolsRadialMenu.setVisible(false);
    }

    /**
     * *******************************************
     * MOB - Man Off Board **************************************************
     */
    private void createMOBWidget(Scene scene) {

        Group MOBdock = new Group();
        MOBdock.getChildren().add(mobOffImg);
        //MOBdock.getChildren().add(mobOnImg);
        root.getChildren().add(MOBdock);
        MOBdock.setTranslateX(600.0);
        MOBdock.setTranslateY(-50.0);
        //  MOBdock.getChildren().add(swingNode);
        StackPane.setAlignment(MOBdock, Pos.BOTTOM_CENTER);
    }

    private void createDockWidget(Scene scene) {

        Group groupDock = new Group();

        groupDock.getChildren().add(basedock);
        groupDock.getChildren().add(dock);
        root.getChildren().add(groupDock);
        basedock.setLayoutX(450.0);
        basedock.setLayoutY(100.0);
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

        /*
         root.getChildren().add(dock0);
         dock0.setOrientation(Orientation.VERTICAL);
   
         StackPane.setMargin(dock0, (new Insets(150, 10, 0, 0)));
         StackPane.setAlignment(dock0, Pos.TOP_RIGHT);
         */
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

    @Override
    public Scene getScene() {
        return scene;
    }

}
