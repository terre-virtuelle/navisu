package bzh.terrevirtuelle.navisu.stl.databases.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.LambertServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.stl.databases.StlDBComponent;
import bzh.terrevirtuelle.navisu.stl.databases.StlDBComponentServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.StlDBComponentController;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import bzh.terrevirtuelle.navisu.dem.db.DemDBServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class StlDBComponentImpl
        implements StlDBComponent, StlDBComponentServices, InstrumentDriver, ComponentState {

    @UsedService
    BathymetryDBServices bathymetryDBServices;
    @UsedService
    DatabaseServices databaseServices;
    @UsedService
    DelaunayServices delaunayServices;
    @UsedService
    DemDBServices demComponentServices;
    @UsedService
    DisplayServices displayServices;
    @UsedService
    GeodesyServices geodesyServices;
    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    @UsedService
    JTSServices jtsServices;
    @UsedService
    LambertServices lambertServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    Pro4JServices pro4JServices;
    @UsedService
    ShapefileObjectServices shapefileObjectServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    StlComponentServices stlComponentServices;
    @UsedService
    TopologyServices topologyServices;
    @UsedService
    KmlComponentServices kmlComponentServices;
    @UsedService
    ObjComponentServices objComponentServices;

    protected final String COMPONENT_KEY_NAME_0 = "StlDbS57";
    protected String componentKeyName;
    protected StlDBComponentController controller;
    protected Layer layer;
    protected static final Logger LOGGER = Logger.getLogger(StlDBComponentImpl.class.getName());
    protected WorldWindow wwd;
    protected String userDirPath = null;
    protected String shpDir;
    protected String dir;
    protected Map<Pair<Double, Double>, String> topMarkMap = new HashMap<>();
    protected String marsys;

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
                controller = new StlDBComponentController(this, KeyCode.T, KeyCombination.CONTROL_DOWN,
                        guiAgentServices,
                        layersManagerServices,
                        s57ChartComponentServices,
                        databaseServices,
                        delaunayServices,
                        demComponentServices,
                        displayServices,
                        bathymetryDBServices,
                        instrumentDriverManagerServices,
                        topologyServices,
                        shapefileObjectServices,
                        geodesyServices,
                        jtsServices,
                        stlComponentServices,
                        kmlComponentServices,
                        objComponentServices,
                        pro4JServices);
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
    public Connection getConnnection(String database, String user, String passwd) {
        return controller.getConnection();
    }

}
