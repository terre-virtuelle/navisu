/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db.impl;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.BathymetryEventProducerServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDB;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.impl.controller.BathymetryDBController;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3Df;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.stage.Stage;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class BathymetryDBImpl
        implements BathymetryDB, BathymetryDBServices, DatabaseDriver, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(BathymetryDBImpl.class.getName());
    final String NAME = "Bathy";
    final String LAYER_NAME = "BathyShom";
    final String LIMIT = "100";
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
    @UsedService
    BathymetryEventProducerServices bathymetryEventProducerServices;
    private String dataFileName;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private List<Point3Df> points3df;
    private List<Point3D> points3d;
    protected WorldWindow wwd;
    protected RenderableLayer layer;
    protected static final String GROUP = "Bathymetry data";
    double longitude;
    double latitude;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;
    BathymetryDBController bathymetryDBController;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {
        points3df = new ArrayList<>();
        points3d = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        layer = layersManagerServices.getLayer(GROUP, LAYER_NAME);
        //   layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
        bathymetryDBController = BathymetryDBController.getInstance(this,
                databaseServices, guiAgentServices, bathymetryEventProducerServices,
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
        this.connection = bathymetryDBController.connect(dbName, hostName, protocol, port, driverName, userName, passwd, dataFileName);
        return connection;
    }

    @Override
    public Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd) {
        this.connection = bathymetryDBController.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        return connection;
    }

    @Override
    public void create(String filename) {
        bathymetryDBController.create(filename);
    }

    @Override
    public List<Point3Df> readFromFile(String filename) {
        return bathymetryDBController.readFromFile(filename);
    }

    @Override
    public void insert(List<Point3Df> points) {
        bathymetryDBController.insert(points);
    }

    @Override
    public void createIndex() {
        bathymetryDBController.createIndex();
    }

    @Override
    public List<Point3D> retrieveAll() {
        return bathymetryDBController.retrieveAll();
    }

    @Override
    public List<Point3D> retrieveAround(double lat, double lon) {
        return bathymetryDBController.retrieveAround(lat, lon);
    }

    @Override
    public List<Point3D> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        return bathymetryDBController.retrieveIn(latMin, lonMin, latMax, lonMax);
    }

    @Override
    public void displaySounding(List<Point3D> points) {
        bathymetryDBController.displaySounding(points);
    }

    @Override
    public void displayAllSounding() {
        bathymetryDBController.displayAllSounding();
    }

    @Override
    public List<Point3D> retrieveBoundaries(List<Point3D> pts) {
        return bathymetryDBController.retrieveBoundaries(pts);
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

}
