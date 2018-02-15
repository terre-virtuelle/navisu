/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.S57DBComponentImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public class S57DBComponentController
        extends Widget2DController
        implements Initializable {

    protected S57DBComponentImpl component;
    protected LayersManagerServices layersManagerServices;
    protected GuiAgentServices guiAgentServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected LayerTreeServices layerTreeServices;
    protected static final String ALARM_SOUND = "/data/sounds/pling.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    // Pour récupérer le .css
    protected final String FXML = "configurationStlController.fxml";
    //protected String VIEW_GROUP_STYLE = "configuration.css";
    protected String VIEW_GROUP_STYLE = "common.css";
    protected static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
   
    protected static S57DBComponentController INSTANCE = null;

    protected Map<String, String> acronyms;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String marsys;
    protected final String NAME = "";
    protected final String GROUP = "";
    protected final Set<S57Controller> s57Controllers = new HashSet<>();
    protected boolean first = true;
    protected RenderableLayer layer;
    

    public S57DBComponentController(S57DBComponentImpl component,
            GuiAgentServices guiAgentServices,
            LayerTreeServices layerTreeServices,
            LayersManagerServices layersManagerServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            String GROUP,
            String NAME,
            WorldWindow wwd) {
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.layerTreeServices = layerTreeServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        
        this.wwd = wwd;
        layer = layersManagerServices.getLayer(GROUP, NAME);
        initAcronymsMap();
    }

    private void initAcronymsMap() {
        acronyms = new HashMap<>();
        String tmp;
        String[] tmpTab;
        try {
            BufferedReader input = new BufferedReader(
                    new FileReader("properties/s57AcronymClasses.table"));
            while ((tmp = input.readLine()) != null) {
                tmpTab = tmp.split(",");
                acronyms.put(tmpTab[0], tmpTab[1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(S57DBComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void init(String path) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
