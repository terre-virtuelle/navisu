/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.BathymetryEventProducerServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.impl.BathymetryDBImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Bathymetry;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3Df;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.stage.Stage;
import org.postgis.PGgeometry;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class BathymetryDBController {

    private static BathymetryDBController INSTANCE = null;
    BathymetryDBImpl component;
    protected static final Logger LOGGER = Logger.getLogger(BathymetryDBController.class.getName());
    DatabaseServices databaseServices;
    GuiAgentServices guiAgentServices;
    BathymetryEventProducerServices bathymetryEventProducerServices;
    protected WorldWindow wwd;
    final double LIMIT;

    List<Point3D> points;
    private Connection connection;
    private String dataFileName;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private List<Point3Df> points3df;
    private List<Point3D> points3d;
    protected RenderableLayer layer;
    protected static final String GROUP = "Bathymetry data";
    double longitude;
    double latitude;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;
    Stage stage;
    double MIN_DEPTH = 0.0;

    double MIN_LAT = 48.25;
    double MIN_LON = -4.55;
    double MAX_LAT = 48.45;
    double MAX_LON = -4.245;

    /*
    //Ouessant
    double MIN_LAT = 48.4;
    double MIN_LON = -5.20;
    double MAX_LAT = 48.50;
    double MAX_LON = -5;
     */
    double maxElevation = -20.0;
    final double THRESHOLD = 0.0015;
    double tmp;
    Geometry concaveHull;

    private BathymetryDBController(BathymetryDBImpl component,
            DatabaseServices databaseServices, GuiAgentServices guiAgentServices,
            BathymetryEventProducerServices bathymetryEventProducerServices,
            double limit, RenderableLayer layer) {
        this.component = component;
        this.databaseServices = databaseServices;
        this.guiAgentServices = guiAgentServices;
        this.bathymetryEventProducerServices = bathymetryEventProducerServices;
        this.LIMIT = limit;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();

        wwd.addPositionListener((PositionEvent event) -> {
            Position pos = event.getPosition();
            try {
                if (pos != null && connection != null && !connection.isClosed() && pos.getAltitude() < 20.0) {
                    //  points = bathymetryDBImpl.retrieve(pos.getLatitude().getDegrees(), pos.getLongitude().getDegrees());
                }
            } catch (SQLException ex) {
                Logger.getLogger(BathymetryDBController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        });
    }

    public static BathymetryDBController getInstance(BathymetryDBImpl component,
            DatabaseServices databaseServices, GuiAgentServices guiAgentServices,
            BathymetryEventProducerServices bathymetryEventProducerServices,
            double limit, RenderableLayer layer) {
        if (INSTANCE == null) {
            INSTANCE = new BathymetryDBController(component,
                    databaseServices, guiAgentServices, bathymetryEventProducerServices,
                    limit, layer);
        }
        return INSTANCE;
    }

    public Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd,
            String dataFileName) {
        this.dataFileName = dataFileName;
        this.connection = databaseServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        System.out.println(dbName + " " + hostName + " " + protocol + " " + port + " " + driverName + " " + userName + " " + passwd);
        String sql = "INSERT INTO " + "bathy" + " (coord, elevation) "
                + "VALUES ( ST_SetSRID(ST_MakePoint(?, ?), 4326), ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        //  create(dataFileName);
        createIndex();
        return connection;
    }

    public Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd) {
        System.out.println(dbName + " " + hostName + " " + protocol + " " + port + " " + driverName + " " + userName + " " + passwd);
        this.connection = databaseServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        return connection;
    }

    public void create(String filename) {
        String sql = "INSERT INTO " + "bathy" + " (coord, elevation) "
                + "VALUES ( ST_SetSRID(ST_MakePoint(?, ?), 4326), ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        guiAgentServices.getJobsManager().newJob("create", (progressHandle) -> {
            String query = "DROP TABLE IF EXISTS  bathy; "
                    + "CREATE TABLE bathy("
                    + "gid SERIAL PRIMARY KEY,"
                    + "coord GEOMETRY(POINT, 4326), "
                    + "elevation FLOAT); ";
            try {
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }

            points3df = readFromFile(filename);
            insert(points3df);
            // createIndex();
        });
    }

    public List<Point3Df> readFromFile(String filename) {
        List<Point3Df> tmp = new ArrayList<>();
        try {
            tmp = Files.lines(new File(filename).toPath())
                    .map(line -> line.trim())
                    // .map(line -> line.split("\t"))
                    .map(line -> line.split(" "))
                    .map(tab -> new Point3Df(Float.parseFloat(tab[0]),
                            Float.parseFloat(tab[1]),
                            Float.parseFloat(tab[2])))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return tmp;
    }

    public final void insert(List<Point3Df> points) {
        points.stream().forEach((p) -> {
            try {
                preparedStatement.setDouble(1, p.getLon());
                preparedStatement.setDouble(2, p.getLat());
                preparedStatement.setDouble(3, p.getElevation());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        });
    }

    public final void createIndex() {
        guiAgentServices.getJobsManager().newJob("createIndex", (progressHandle) -> {
            try {
                connection.createStatement().executeUpdate("CREATE INDEX bathyindex ON bathy USING GIST (coord)");
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        });
    }

    public List<Point3D> retrieveAll() {
        List<Point3D> tmp1 = new ArrayList<>();
        //  guiAgentServices.getJobsManager().newJob("retrieveAll", (progressHandle) -> {
        PGgeometry geom;
        double depth;
        try {
            ResultSet r = connection.createStatement().executeQuery("SELECT  coord, elevation FROM bathy");
            while (r.next()) {
                geom = (PGgeometry) r.getObject(1);
                depth = r.getFloat(2);
                if (depth >= MIN_DEPTH) {
                    Point3D pt = new Point3D(geom.getGeometry().getFirstPoint().getX(),
                            geom.getGeometry().getFirstPoint().getY(),
                            depth);
                    tmp1.add(pt);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        // });
        return tmp1;
    }

    public List<Point3D> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<Point3D> tmp1 = new ArrayList<>();
        PGgeometry geom;
        double depth;
        ResultSet r;

        try {
            r = connection.createStatement().executeQuery(
                    "SELECT *"
                    + "FROM bathy "
                    + "WHERE coord @ ST_MakeEnvelope ("
                    + latMin + ", " + lonMin + ", "
                    + latMax + ", " + lonMax
                    + ", 4326) ");

            while (r.next()) {
                geom = (PGgeometry) r.getObject(2);
                depth = r.getFloat(3);
                if (depth > MIN_DEPTH) {
                    Point3D pt = new Point3D(geom.getGeometry().getFirstPoint().getX(),
                            geom.getGeometry().getFirstPoint().getY(),
                            depth);
                    tmp1.add(pt);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return tmp1;
    }

    public final List<Point3D> retrieveAround(double lat, double lon, double limit) {
        PGgeometry geom;
        ResultSet r0;
        ResultSet r1;
        points3d.clear();
        layer.removeAllRenderables();
        try {
            r0 = connection.createStatement().executeQuery(
                    "SELECT coord,elevation "
                    + "FROM bathy "
                    + "ORDER BY coord <-> ST_SetSRID("
                    + "ST_MakePoint(" + Double.toString(lon) + ", " + Double.toString(lat) + "), 4326) "
                    + "LIMIT " + limit);
            while (r0.next()) {
                geom = (PGgeometry) r0.getObject(1);
                longitude = geom.getGeometry().getFirstPoint().getX();
                latitude = geom.getGeometry().getFirstPoint().getY();
                r1 = connection.createStatement().executeQuery(
                        "SELECT ST_DISTANCE("
                        + "ST_SetSRID(ST_MakePoint(" + longitude
                        + ", " + latitude + "), 4326)::geography,"
                        + "ST_SetSRID(ST_MakePoint(" + Double.toString(lon)
                        + ", " + Double.toString(lat) + "), 4326)::geography"
                        + ");");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        bathymetryEventProducerServices.setBathymetry(new Bathymetry(points3d));
        return points3d;
    }

    public Connection getConnection() {
        return connection;
    }

}
