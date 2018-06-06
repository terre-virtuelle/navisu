package bzh.terrevirtuelle.navisu.dem.db.impl;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import gov.nasa.worldwind.WorldWindow;
import org.capcaval.c3.component.ComponentState;

import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3Df;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.dem.db.DemDBComponentServices;
import bzh.terrevirtuelle.navisu.dem.db.DemDBComponent;
import bzh.terrevirtuelle.navisu.dem.db.impl.controller.DemDBComponentController;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class DemDBComponentImpl
        implements DemDBComponent, DemDBComponentServices, DatabaseDriver, ComponentState {

    protected final String COMPONENT_KEY_NAME_0 = "DbElevation";
    protected String componentKeyName;
    protected static final Logger LOGGER = Logger.getLogger(DemDBComponentImpl.class.getName());
    protected WorldWindow wwd;

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    DatabaseServices databaseServices;

    protected final String NAME = "Bathy";
    protected final String LAYER_NAME = "BathyShom";
    protected final double LIMIT = 100.0;
    protected String dataFileName;
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected Statement statement;
    protected List<Point3D> points3d;

    protected RenderableLayer layer;
    protected static final String GROUP = "Bathymetry data";
    double longitude;
    double latitude;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;
    DemDBComponentController controller;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {
        points3d = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        layer = layersManagerServices.getLayer(GROUP, LAYER_NAME);
        //   layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
        controller = DemDBComponentController.getInstance(this,
                databaseServices, guiAgentServices,
                LIMIT, layer);

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd,
            String dataFileName) {
        this.dataFileName = dataFileName;
        this.connection = controller.connect(dbName, hostName, protocol, port, driverName, userName, passwd, dataFileName);
        return connection;
    }

    @Override
    public Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd) {
        this.connection = controller.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        return connection;
    }

    @Override
    public void create(String filename) {
        controller.create(filename);
    }

    @Override
    public List<Point3Df> readFromFile(String filename) {
        return controller.readFromFile(filename);
    }

    @Override
    public void insert(List<Point3Df> points) {
        controller.insert(points);
    }

    @Override
    public void createIndex() {
        controller.createIndex();
    }

    @Override
    public List<Point3D> retrieveAll() {
        return controller.retrieveAll();
    }

    @Override
    public List<Point3D> retrieveAround(double lat, double lon) {
        return controller.retrieveAround(lat, lon, LIMIT);
    }

    @Override
    public List<Point3D> retrieveAround(double lat, double lon, double limit) {
        return controller.retrieveAround(lat, lon, limit);
    }

    @Override
    public List<Point3D> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        return controller.retrieveIn(latMin, lonMin, latMax, lonMax);
    }

    @Override
    public List<Point3D> retrieveIn(Connection connection, double latMin, double lonMin, double latMax, double lonMax) {
        return controller.retrieveIn(connection, latMin, lonMin, latMax, lonMax);
    }

    @Override
    public Point3D[][] mergeData(Point3D[][] orgData, List<Triangle_dt> triangles) {
        return controller.mergeData(orgData, triangles);
    }

    @Override
    public Point3D[][] mergeData(Point3D[][] orgData, List<Triangle_dt> triangles, double depth) {
        return controller.mergeData(orgData, triangles, depth);
    }

    @Override
    public void close() {
        databaseServices.close();
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean canOpen(String dbName) {
        return dbName.equalsIgnoreCase(NAME);
    }

    @Override
    public DatabaseDriver getDriver() {
        return this;
    }

    @Override
    public void writePointList(List<Point3D> points, Path pathname, boolean latLon) {
        controller.writePointList(points, pathname, latLon);
    }

}
