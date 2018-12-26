/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgent;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.impl.DockManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.impl.GeoViewImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.LayerCheckTreeImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.impl.MenuManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.server.impl.ServerOptionsComponentImpl;
import bzh.terrevirtuelle.navisu.widgets.alarms.Alarm;
import bzh.terrevirtuelle.navisu.widgets.alarms.Mob;
import gov.nasa.worldwind.util.StatusBar;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.capcaval.c3.component.annotation.SubComponent;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import bzh.terrevirtuelle.navisu.app.guiagent.options.server.ServerOptionsComponentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.geom.Position;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class GuiAgentImpl
        implements GuiAgent, GuiAgentServices {

    private static final Logger LOGGER = Logger.getLogger(GuiAgentImpl.class.getName());

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    //private static final String NAVISU_LOOK_AND_FEEL_PATH = "css/navisu.css";
    private final View viewWW;
    private final WorldWindow wwd;

    @SubComponent
    ServerOptionsComponentImpl optionsManager;
    @UsedService
    ServerOptionsComponentServices optionsManagerServices;

    @SubComponent
    MenuManagerImpl menu;
    @UsedService
    MenuManagerServices menuServices;

    @SubComponent
    DockManagerImpl dockManager;
    @SubComponent
    LayerCheckTreeImpl layerTree;
    @SubComponent
    GeoViewImpl geoView;

    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;

    private Scene scene;
    protected Stage stage;
    protected Stage stage1;
    protected StackPane root;
    protected static GuiAgentController ctrl = null;
    protected JobsManager jobsManager;
    protected StatusBar statusBar;
    protected int width;
    protected int height;
    protected static final String TITLE = "NaVisu";
    protected static final String ICON_PATH = "bzh/terrevirtuelle/navisu/app/guiagent/impl/";
    protected static final String ALARM_SOUND = "/data/sounds/alarm10.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected static final String GUI_AGENT_FXML = "GuiAgent.fxml";
    protected static final String STATUS_INFO = "status-text";
    protected static final String SBPSTYLE = "-fx-background-color: #999999;";
    protected boolean first = true;
    protected InstrumentDriver driver = null;//Utilise par le MOB
    protected Position pos;
    protected Text label;
    protected String modstyle;

    public GuiAgentImpl() {
        this.wwd = GeoWorldWindViewImpl.getWW();
        this.viewWW = wwd.getView();
    }

    @Override
    public void showGui(Stage stage, int width, int height) {
        this.width = width;
        this.height = height;
        this.stage = stage;

        /**
         * ********************************************
         * double screenwidth =
         * java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
         * //System.out.println(" ********!!!!!!!!! largeur écran : " +
         * screenwidth); double screenheight =
         * java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
         * //System.out.println(" ********!!!!!!!!! hauteur écran : " +
         * screenheight);
         *
         * double percentx = 1; double percenty = 1; width = (int) (screenwidth
         * * percentx); //System.out.println(" !!!!!!******* largeur fenêtre : "
         * + width); height = (int) (screenheight * percenty);
         * //System.out.println(" !!!!!!******* hauteur fenêtre : " + height);
         */
        stage.setResizable(true);
        //stage.setFullScreen(true);
        label = new Text();

        label.getStyleClass()
                .add(STATUS_INFO);
        //label.setFill(Color.WHITESMOKE);        
        label.setLayoutY(16);

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

        dockManager.init(root, scene, height, width);

        dockManager.makeDock();

        createMOBWidget(scene);

        ctrl.leftBorderPane.setCenter(layerTreeServices.getDisplayService().getDisplayable());
        ctrl.centerStackPane.getChildren()
                .add(geoViewServices.getDisplayService().getDisplayable());
        ctrl.statusBorderPane.setRight(jobsManager.getDisplay().getDisplayable());
        //ctrl.statusBorderPane.setStyle(SBPSTYLE);
        ctrl.statusBorderPane.getChildren()
                .add(label);

        stage.setTitle(TITLE);

        stage.setScene(scene);

        stage.show();

        wwd.addPositionListener(
                (PositionEvent event) -> {
                    pos = wwd.getView().getCurrentEyePosition();
                    double a = pos.getAltitude();
                    String aText;
                    if (a <= 1000) {
                        aText = (int) (pos.getAltitude()) + " m";
                    } else {
                        aText = (int) (pos.getAltitude() / 1000) + " Km";
                    }
                    Platform.runLater(() -> {
                        label.setText(
                                " Altitude : " + aText
                                + " Latitude : " + pos.getLatitude().toFormattedDMSString()
                                + "  (" + pos.getLatitude().toDecimalDegreesString(4) + ")"
                                + " Longitude : " + pos.getLongitude().toFormattedDMSString()
                                + "  (" + pos.getLongitude().toDecimalDegreesString(4) + ")");
                    });
                    wwd.redrawNow();
                }
        );

// Deuxieme stage pour le sonar, pour qu'il reste au dessus, bug sur l'api ?
        stage1 = new Stage();

        stage1.setOpacity(0.0);
        stage1.setHeight(400);
        stage1.setWidth(400);
        stage1.setX(600);
        stage1.setY(200);
        stage1.initStyle(StageStyle.UNDECORATED);
    }

    /**
     * MOB - Man Overboard
     */
    private void createMOBWidget(Scene scene) {
        Alarm mob = new Mob();
        root.getChildren().add(mob);
        mob.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (first == true) {
                driver = instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "100");
                first = false;
            } else {
                driver.off();
                first = true;
            }
        });

        mob.setTranslateX(1350);//1250
        mob.setTranslateY(40.0);
        mob.setScale(.75);
        StackPane.setAlignment(mob, Pos.BOTTOM_CENTER);
        Group groupDock = dockManager.getGroupDock();
        groupDock.getChildren().add(mob);
        groupDock.setTranslateX(30);
    }

    private void loadCss(Scene scene) {
        //scene.getStylesheets().add(getClass().getResource(CSS_STYLE_PATH).toExternalForm());
        //String uri = Paths.get(System.getProperty("user.dir") + "\\css\\navisu.css").toUri().toString();
        modstyle = "navisu.css";
        String uri = CSS_STYLE_PATH + modstyle;
        scene.getStylesheets().add(uri);
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
        return root;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public Stage getStage() {
        return stage1;
    }

    @Override
    public MenuBar getMenuBar() {
        return ctrl.getMenuBar();
    }

    @Override
    public BorderPane getLeftBorderPane() {
        return ctrl.getLeftBorderPane();
    }

    @Override
    public StackPane getCenterStackPane() {
        return ctrl.getCenterStackPane();
    }

    @Override
    public BorderPane getStatusBorderPane() {
        return ctrl.getStatusBorderPane();
    }

}
