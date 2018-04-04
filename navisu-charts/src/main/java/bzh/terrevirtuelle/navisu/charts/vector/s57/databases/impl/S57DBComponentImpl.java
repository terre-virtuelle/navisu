package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;
import bzh.terrevirtuelle.navisu.instruments.transponder.impl.events.TransponderActivateEvent;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.S57DBComponent;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.S57DBComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57DBComponentImpl
        implements S57DBComponent, S57DBComponentServices, InstrumentDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    DatabaseServices databaseServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    @UsedService
    TopologyServices topologyServices;
    @UsedService
    JTSServices jtsServices;
    @UsedService
    ShapefileObjectServices shapefileObjectServices;

    ComponentManager cm;
    ComponentEventSubscribe<TransponderActivateEvent> transponderActivateEvent;

    private final String COMPONENT_KEY_NAME_0 = "ReqDbS57";
    private String componentKeyName;

    protected S57DBComponentController controller;
    protected Layer layer;

    protected static final Logger LOGGER = Logger.getLogger(S57DBComponentImpl.class.getName());
    protected WorldWindow wwd;
    protected String userDirPath = null;
    protected String shpDir;
    protected String dir;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {
        wwd = GeoWorldWindViewImpl.getWW();
    }

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            componentKeyName = cmd[0];
            if (cmd[0].equals(COMPONENT_KEY_NAME_0)) {
                controller = new S57DBComponentController(this, componentKeyName, KeyCode.T, KeyCombination.CONTROL_DOWN,
                        guiAgentServices,
                        layersManagerServices,
                        layerTreeServices,
                        s57ChartComponentServices,
                        databaseServices,
                        instrumentDriverManagerServices,
                        topologyServices,
                        jtsServices,
                        shapefileObjectServices);
                controller.setVisible(true);
            }
        }
    }

    @Override
    public void off() {
        if (controller != null) {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
            guiAgentServices.getRoot().getChildren().remove(controller);
            controller.setVisible(false);
        }
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)) {
            canOpen = true;
        }
        return canOpen;
    }

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {

    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

    @Override
    public List<Buoyage> getBuoyage(String database, String user, String passwd,
            Buoyage buoy, double lat0, double lon0, double lat1, double lon1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
