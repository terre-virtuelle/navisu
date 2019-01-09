/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.buildings.osmb.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.buildings.osmb.impl.OsmbComponentImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * NaVisu project
 *
 * @date Jan 3, 2019 11:05:54 AM
 * @author Serge Morvan
 */
public class OsmbComponentController
        extends Widget2DController
        implements Initializable {

    protected static final Logger LOGGER = Logger.getLogger(OsmbComponentController.class.getName());
    protected final String NAVISU_HOME = System.getProperty("user.home") + "/.navisu";
    protected final String FXML = "OsmbComponentController.fxml";
    protected final String SEP = File.separator;
    protected static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected String viewgroupstyle = "configuration.css";
    protected static final String GROUP = "On-earth layers";
    protected static final String LAYER = "OSMBuildings";
    protected GuiAgentServices guiAgentServices;
    protected LayersManagerServices layersManagerServices;
    protected RenderableLayer osmbLayer;
    private OsmbComponentImpl component;

    @FXML
    public Group view;
    @FXML
    public Pane viewPane;
    @FXML
    public Button quit;
    @FXML
    public Button requestButton;
    @FXML
    public Button helpButton;

    public OsmbComponentController(OsmbComponentImpl component,
            GuiAgentServices guiAgentServices, LayersManagerServices layersManagerServices) {
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;

        LOGGER.setLevel(Level.INFO);
        FileHandler fh = null;
        try {
            fh = new FileHandler(NAVISU_HOME + "/logs/" + "navisu.log");
        } catch (IOException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        LOGGER.addHandler(fh);

        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
        guiAgentServices.getRoot().getChildren().add(this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);

        osmbLayer = layersManagerServices.getLayer(GROUP, LAYER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quit.setOnMouseClicked((MouseEvent event) -> {
            component.off();
        });
        requestButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Work in progress");
        });
    }

    public List<Renderable> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<Renderable> result = null;
        //osmbLayer ?
        return result;
    }
}
