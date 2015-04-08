 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgent;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.impl.DockManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.impl.GeoViewImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.LayerCheckTreeImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.impl.MenuManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.OptionsManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.widgets.mob.Mob;
import gov.nasa.worldwind.util.StatusBar;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.capcaval.c3.component.annotation.SubComponent;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;

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
    GuiAgentServices guiAgentServices;

    private Scene scene;
    protected Stage stage;
    protected Stage stage1;
    protected List<Stage> stages = new ArrayList<>();
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

        dockManager.init(root, scene, height, width);
        dockManager.makeDock();

        createMOBWidget(scene);

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
        stage.setScene(scene);
        stage.show();
        
        stage1 = new Stage();
        stage1.setOpacity(.0);
        stage1.setHeight(400);
        stage1.setWidth(400);
        stage1.setX(600);
        stage1.setY(200);
        stage1.initStyle(StageStyle.UNDECORATED);
        //stage1.setAlwaysOnTop(true);

    }

    /**
     * MOB - Man Overboard
     */
    private void createMOBWidget(Scene scene) {
        Mob mob = new Mob();
        root.getChildren().add(mob);
        mob.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            boolean first = true;
            MediaPlayer mediaPlayer;

            @Override
            public void handle(MouseEvent event) {
                guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
                    if (first == true) {
                        javafx.scene.media.Media media;
                        String url = System.getProperty("user.dir") + "/data/sounds/alarm10.wav";
                        media = new Media("file:///" + url);
                        mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.setAutoPlay(true);
                        mediaPlayer.setCycleCount(100);
                        first = false;
                    } else {
                        mediaPlayer.dispose();
                        first = true;
                    }
                });
            }
        });

        mob.setTranslateX(400.0);
        mob.setTranslateY(-10.0);
        StackPane.setAlignment(mob, Pos.BOTTOM_CENTER);
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
}
