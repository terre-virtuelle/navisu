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
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.BuoyageDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.TopmarDBLoader;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.geom.Position;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
    GeodesyServices geodesyServices;
    @UsedService
    JTSServices jtsServices;
    @UsedService
    DelaunayServices delaunayServices;
    @UsedService
    ShapefileObjectServices shapefileObjectServices;
    @UsedService
    DisplayServices displayServices;

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
    protected Map<Pair<Double, Double>, String> topMarkMap = new HashMap<>();
    protected String marsys ;

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
                        shapefileObjectServices,
                        displayServices,
                        delaunayServices);
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
    public Connection getConnnection(String database, String user, String passwd) {
        return controller.getConnection();
    }

    @Override
    public List<Buoyage> retrieveBuoyagesIn(Connection connection, double latMin, double lonMin, double latMax, double lonMax) {
        //Define TopMak for all buoyages, default is 0 : no topmark
        TopmarDBLoader topmarDbLoader = new TopmarDBLoader(connection);
        topMarkMap = topmarDbLoader.retrieveIn(latMin, lonMin, latMax, lonMax);

        //Define IALA system for all buoyages, default is 1
        MnsysDBLoader mnsysDbLoader = new MnsysDBLoader(connection);
        marsys = mnsysDbLoader.retrieveIn(latMin, lonMin, latMax, lonMax);
        
        List<Buoyage> buoyages = new ArrayList<>();
        Set<String> buoyageKeys = BUOYAGE.ATT.keySet();
        for (String b : buoyageKeys) {
            buoyages.addAll(new BuoyageDBLoader(topologyServices, connection, b, topMarkMap, marsys)
                    .retrieveObjectsIn(latMin, lonMin, latMax, lonMax));
        }
        return buoyages;
    }

    @Override
    public List<Buoyage> retrieveBuoyagesIn(Connection connection, double lat, double lon, double side) {
        double r = side / 2;
        Position position = geodesyServices.getPosition(lat, lon, 90.0, r);
        position = geodesyServices.getPosition(position, 0, r);
        double latMax = position.getLatitude().getDegrees();
        double lonMax = position.getLongitude().getDegrees();
        position = geodesyServices.getPosition(lat, lon, 270.0, r);
        position = geodesyServices.getPosition(position, 180, r);
        double latMin = position.getLatitude().getDegrees();
        double lonMin = position.getLongitude().getDegrees();
        return retrieveBuoyagesIn(connection, latMin, lonMin, latMax, lonMax);
    }
   
}
