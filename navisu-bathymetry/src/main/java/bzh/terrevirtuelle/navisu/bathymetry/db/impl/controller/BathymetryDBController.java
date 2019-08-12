/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.BathymetryEventProducerServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.impl.BathymetryDBImpl;
import bzh.terrevirtuelle.navisu.bathymetry.view.impl.controller.DisplayBathymetryController;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3Df;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import org.postgis.PGgeometry;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class BathymetryDBController {

    private static BathymetryDBController INSTANCE = null;
    BathymetryDBImpl component;
    protected static final Logger LOGGER = Logger.getLogger(BathymetryDBController.class.getName());
    protected final String SEP = File.separator;

    DatabaseServices databaseServices;
    GuiAgentServices guiAgentServices;
    BathymetryEventProducerServices bathymetryEventProducerServices;
    TopologyServices topologyServices;

    protected WorldWindow wwd;
    final double LIMIT;
    boolean first = true;
    List<Point3DGeo> points;
    private Connection connection;
    private String dataFileName;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private List<Point3Df> points3df;
    private List<Point3DGeo> points3d = new ArrayList<>();
    protected RenderableLayer layer;
    protected static final String GROUP = "Bathymetry data";
    double longitude;
    double latitude;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;
    double MIN_DEPTH = 0.0;
    double distA;
    double distB;
    double distC;
    double distMin;
    Point_dt pMin;

    double maxElevation = -20.0;
    final double THRESHOLD = 0.0015;
    double tmp;
    Geometry concaveHull;
    protected Charset charset = Charset.forName("UTF-8");

    private BathymetryDBController(BathymetryDBImpl component,
            DatabaseServices databaseServices,
            GuiAgentServices guiAgentServices,
            BathymetryEventProducerServices bathymetryEventProducerServices,
            TopologyServices topologyServices,
            double limit, RenderableLayer layer) {
        this.component = component;
        this.databaseServices = databaseServices;
        this.guiAgentServices = guiAgentServices;
        this.bathymetryEventProducerServices = bathymetryEventProducerServices;
        this.topologyServices = topologyServices;
        this.LIMIT = limit;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();
    }

    public static BathymetryDBController getInstance(BathymetryDBImpl component,
            DatabaseServices databaseServices, GuiAgentServices guiAgentServices,
            BathymetryEventProducerServices bathymetryEventProducerServices,
            TopologyServices topologyServices,
            double limit, RenderableLayer layer) {
        if (INSTANCE == null) {
            INSTANCE = new BathymetryDBController(component,
                    databaseServices, guiAgentServices,
                    bathymetryEventProducerServices, topologyServices,
                    limit, layer);
        }
        return INSTANCE;
    }

    public Connection connect(String dbName, String table, String hostName, String protocol, String port,
            String driverName, String userName, String passwd,
            String dataFileName) {
        this.dataFileName = dataFileName;
        this.connection = databaseServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        System.out.println("Connect : " + dbName + " " + hostName + " " + protocol + " " + port + " " + driverName + " " + userName + " " + passwd);
        String sql = "INSERT INTO " + table + " (coord, elevation) "
                + "VALUES ( ST_SetSRID(ST_MakePoint(?, ?), 4326), ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        //  create(dataFileName);
        createIndex(table);
        return connection;
    }

    public Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd) {
        System.out.println(dbName + " " + hostName + " " + protocol + " " + port + " " + driverName + " " + userName + " " + passwd);
        this.connection = databaseServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        return connection;
    }

    public void insert(String filename, String table) {
        String sql = "INSERT INTO " + table + " (coord, elevation) "
                + "VALUES ( ST_SetSRID(ST_MakePoint(?, ?), 4326), ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        guiAgentServices.getJobsManager().newJob("Insert", (progressHandle) -> {
            points3df = readFromFile(filename);
            insertData(points3df);
            createIndex(table);
        });
    }

    public void insert(String filename, String table, String query) {
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        guiAgentServices.getJobsManager().newJob("Insert", (progressHandle) -> {
            points3df = readFromFile(filename);
            insertData(points3df);
            createIndex(table);
        });
    }

    public void create(String filename, String table) {
        guiAgentServices.getJobsManager().newJob("Create", (progressHandle) -> {
            String query = "DROP TABLE IF EXISTS  " + table + "; "
                    + "CREATE TABLE " + table + "("
                    + "gid SERIAL PRIMARY KEY,"
                    + "coord GEOMETRY(POINT, 4326), " //POINTZ, MULTIPOINTZ, GEOMETRYCOLLECTIONZ
                    + "elevation FLOAT); ";
            try {
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
            work(filename, table);
        });
    }

    public void create(String filename, String table, String query) {
        guiAgentServices.getJobsManager().newJob("Create", (progressHandle) -> {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
            work(filename, table);
        });
    }

    public void execute(String query) {
        guiAgentServices.getJobsManager().newJob("Create table", (progressHandle) -> {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }

        });
    }

    public void insert(String table, List<SolidGeo> solids) {
        String id = "";
        if (table.equals("wall")) {
            id = "roofId";
        } else {
            id = "wallId";
        }
        String sql = "INSERT INTO " + table + " (name, " + id + " , coord, ground, geom) "
                + "VALUES (?, "
                + "?, "
                + "ST_SetSRID(ST_MakePoint(?, ?), 4326), "
                + "ST_GeometryFromText(?, 4326), "
                + "ST_GeometryFromText(?, 4326));";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        insertData(table, solids);

    }

    public void insertData(String table, List<SolidGeo> solids) {
        solids.stream().forEach((s) -> {
            try {
                preparedStatement.setString(1, s.getName());
                preparedStatement.setInt(2, s.getRoofId());
                preparedStatement.setDouble(3, s.getCentroid().getLongitude());
                preparedStatement.setDouble(4, s.getCentroid().getLatitude());
                preparedStatement.setString(5, s.getGround().toString());
                preparedStatement.setString(6, topologyServices.toWKT(s));
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }

        });
    }

    public void work(String filename, String table) {
        if (filename.endsWith(".glz") || filename.endsWith(".xyz")) {
            insertData(table, filename);
            createIndex(table);
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("All files must have .glz extension");
                alert.show();
            });
        }
    }

    public final void insertData(String table, String filename) {
        String sql = "INSERT INTO " + table + " (coord, elevation) "
                + "VALUES ( ST_SetSRID(ST_MakePoint(?, ?), 4326), ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        List<Point3Df> pts = readFromFile(filename);
        insertData(pts);
    }

    public List<Point3Df> readFromFile(String filename) {
        List<Point3Df> tmp = new ArrayList<>();
        try {
            tmp = Files.lines(new File(filename).toPath(), Charset.defaultCharset())
                    .map(line -> line.trim())
                    .map(line -> line.split("\\s+"))
                    .filter(tab -> !tab[0].equals(""))
                    .map(tab -> new Point3Df(Float.parseFloat(tab[0]),
                    Float.parseFloat(tab[1]),
                    Float.parseFloat(tab[2])))
                    .collect(Collectors.toList()
                    );
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return tmp;
    }

    public final void insertData(List<Point3Df> points) {

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

    public final void createIndex(String table) {
        try {
            connection.createStatement().executeUpdate("CREATE INDEX " + table + "index ON " + table + " USING GIST (coord)");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public List<Point3DGeo> retrieveAll() {
        List<Point3DGeo> tmp1 = new ArrayList<>();
        PGgeometry geom;
        double depth;
        try {
            ResultSet r = connection.createStatement().executeQuery("SELECT  coord, elevation FROM bathy");
            while (r.next()) {
                geom = (PGgeometry) r.getObject(1);
                depth = r.getFloat(2);
                if (depth >= MIN_DEPTH) {
                    Point3DGeo pt = new Point3DGeo(geom.getGeometry().getFirstPoint().getX(),
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

    public List<Point3DGeo> retrieveIn(String table, double latMin, double lonMin, double latMax, double lonMax) {
        List<Point3DGeo> tmp1 = new ArrayList<>();
        PGgeometry geom;
        double depth;
        ResultSet r;
        if (connection != null) {
            try {
                r = connection.createStatement().executeQuery(
                        "SELECT *"
                        + " FROM " + table + "  "
                        + "WHERE coord @ ST_MakeEnvelope ("
                        + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax
                        + ", 4326); ");
                while (r.next()) {
                    geom = (PGgeometry) r.getObject(2);
                    depth = r.getFloat(3);
                    if (depth >= MIN_DEPTH) {
                        Point3DGeo pt = new Point3DGeo(geom.getGeometry().getFirstPoint().getX(),
                                geom.getGeometry().getFirstPoint().getY(),
                                depth);
                        tmp1.add(pt);
                    }
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            alert();
        }
        return tmp1;
    }

    public List<Point3DGeo> retrieveIn(Connection connection, String table, double latMin, double lonMin, double latMax, double lonMax) {
        Connection tmp0 = this.connection;
        this.connection = connection;
        List<Point3DGeo> tmp1 = retrieveIn(table, latMin, lonMin, latMax, lonMax);
        this.connection = tmp0;
        return tmp1;
    }

    public List<SolidGeo> retrieveInSolid(Connection connection, String table, double latMin, double lonMin, double latMax, double lonMax) {
        Connection tmp0 = this.connection;
        this.connection = connection;
        List<SolidGeo> tmp1 = retrieveInSolid(table, latMin, lonMin, latMax, lonMax);
        this.connection = tmp0;
        return tmp1;
    }

    /*
     query = "DROP TABLE IF EXISTS roof; \n"
                                        + "CREATE TABLE roof (id SERIAL PRIMARY KEY, "
                                        + "name TEXT, "
                                        + "wallId INTEGER, "
                                        + "coord GEOMETRY(POINT, 4326), "
                                        + "ground GEOMETRY(POLYGON,4326), "
                                        + "geom GEOMETRY(GEOMETRYCOLLECTIONZ,4326));";
     */
    public List<SolidGeo> retrieveInSolid(String table, double latMin, double lonMin, double latMax, double lonMax) {
        List<SolidGeo> result = new ArrayList<>();
        PGgeometry geom;
        PGgeometry centroid;
        PGgeometry ground;
        ResultSet r;
        if (connection != null) {
            try {
                r = connection.createStatement().executeQuery(
                        "SELECT *"
                        + " FROM " + table + "  "
                        + "WHERE coord @ ST_MakeEnvelope ("
                        + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax
                        + ", 4326); ");
                while (r.next()) {
                    geom = (PGgeometry) r.getObject(6);
                    String g = geom.toString();
                    g = g.replace("SRID=4326;", "");
                    SolidGeo solid = topologyServices.getSolidGeofromWKT(g);

                    centroid = (PGgeometry) r.getObject(4);
                    String c = centroid.toString();
                    c = c.replace("SRID=4326;", "");
                    solid.setCentroid(topologyServices.getPoint3DGeoFromWKT(c));

                    ground = (PGgeometry) r.getObject(5);
                    g = ground.toString();
                    g = g.replace("SRID=4326;", "");
                    
                   // System.out.println("retrieve g : " + g);
                    solid.setGround(topologyServices.wktPolygonFromString(g));
                    
                    
                    solid.setId(r.getInt(1));
                    solid.setName(r.getString(2));
                    if (table.equals("wall")) {
                        solid.setRoofId(r.getInt(3));
                    } else {
                        solid.setWallId(r.getInt(3));
                    }
                    result.add(solid);
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            alert();
        }
        return result;
    }

    public final List<Point3DGeo> retrieveAround(double lat, double lon, double limit) {
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
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        bathymetryEventProducerServices.setBathymetry(new DEM(points3d));
        return points3d;
    }

    /**
     * @param orgData a simple grid of point3D, with z =0.0
     * @param nbLat nb of lines
     * @param nbLon nb of columns
     * @param triangles Delaunay tiangulation with elevation value
     * @return the initial grid whith elevation value
     *
     */
    public Point3DGeo[][] mergeData(Point3DGeo[][] orgData, List<Triangle_dt> triangles) {
        /*
        matrice.length     //  2
        matrice[0].length  //  4
        matrice[1].length  //  7
         */
        int nbLat = orgData[0].length;
        int nbLon = orgData[1].length;
        Point3DGeo[][] tmp = new Point3DGeo[nbLat][nbLon];
        //  System.out.println(nbLat+" "+nbLon);
        /* 
        for (int k = 0; k < nbLat; k++) {
            System.arraycopy(orgData[k], 0, tmp[k], 0, nbLon);
            System.out.println("k : "+k);
      }
         */

        for (int i = 0; i < nbLat; i++) {
            for (int j = 0; j < nbLon; j++) {
                //  System.out.println("i : " + i + "j : "+j);
                tmp[i][j] = orgData[i][j];
            }
        }

        for (int k = 0; k < nbLat - 1; k++) {
            for (int l = 0; l < nbLon - 1; l++) {
                //Select one point
                Point3DGeo p = tmp[k][l];
                Point_dt pp = new Point_dt(p.getLatitude(), p.getLongitude(), p.getElevation());
                for (Triangle_dt tt : triangles) {
                    // Research  the nearest point of this triangle
                    if (tt.contains(pp)) {
                        distA = tt.A.distance(pp);
                        distB = tt.B.distance(pp);
                        distC = tt.C.distance(pp);
                        distMin = distA;
                        pMin = tt.A;
                        if (distMin > distB) {
                            distMin = distB;
                            pMin = tt.B;
                        }
                        if (distMin > distC) {
                            distMin = distC;
                            pMin = tt.C;
                        }
                        tmp[k][l].setElevation(pMin.z);
                    }
                }
            }
        }
        return tmp;
    }

    /**
     * @param orgData a simple grid of point3D, with z =0.0
     * @param nbLat nb of lines
     * @param nbLon nb of columns
     * @param triangles Delaunay tiangulation with elevation value
     * @return the initial grid whith elevation value
     *
     */
    public Point3DGeo[][] mergeData(Point3DGeo[][] orgData, List<Triangle_dt> triangles, double depth) {
        int nbLat = orgData[0].length;
        int nbLon = orgData[1].length;
        Point3DGeo[][] tmp = new Point3DGeo[nbLat][nbLon];
        for (int k = 0; k < nbLat; k++) {
            System.arraycopy(orgData[k], 0, tmp[k], 0, nbLon);
        }
        for (int k = 0; k < nbLat - 1; k++) {
            for (int l = 0; l < nbLon - 1; l++) {
                //Select one point
                Point3DGeo p = tmp[k][l];
                Point_dt pp = new Point_dt(p.getLatitude(), p.getLongitude(), p.getElevation());
                for (Triangle_dt tt : triangles) {
                    // Research  the nearest point of this triangle
                    if (tt.contains(pp)) {
                        distA = tt.A.distance(pp);
                        distB = tt.B.distance(pp);
                        distC = tt.C.distance(pp);
                        distMin = distA;
                        pMin = tt.A;
                        if (distMin > distB) {
                            distMin = distB;
                            pMin = tt.B;
                        }
                        if (distMin > distC) {
                            distMin = distC;
                            pMin = tt.C;
                        }
                        tmp[k][l].setElevation(depth);
                    }
                }
            }
        }
        return tmp;
    }

    public Connection getConnection() {
        return connection;
    }

    public void writePointList(List<Point3DGeo> points, Path pathname, boolean latLon) {

        List<String> lines = new ArrayList<>();
        if (points != null) {
            if (latLon == true) {
                points.forEach((p) -> {
                    lines.add(p.getLatitude() + " " + p.getLongitude() + " " + p.getElevation());
                });
            } else {
                points.forEach((p) -> {
                    lines.add(p.getLongitude() + " " + p.getLatitude() + " " + p.getElevation());
                });
            }
            try {
                Files.write(pathname, lines, charset, StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(DisplayBathymetryController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

    private void alert() {
        if (first == true) {
            Platform.runLater(() -> {
                first = false;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Database warning");
                alert.setHeaderText("Database not connected");
                Text s = new Text("    Verify PostGis NaVisuDB connection \n");
                s.setWrappingWidth(650);
                alert.getDialogPane().setContent(s);
                alert.show();
            });
        }
    }

    private void alert(String title, String header, String text) {
        // if (first == true) {
        Platform.runLater(() -> {
            first = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(header);
            Text s = new Text(text);
            s.setWrappingWidth(650);
            alert.getDialogPane().setContent(s);
            alert.show();
        });
        //   }
    }
}
