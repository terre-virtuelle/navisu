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
import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3Df;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.utils.NaVisuToJTS;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPoint;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceSquare;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.stage.Stage;
import org.opensphere.geometry.algorithm.ConcaveHull;
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
    final String LIMIT;

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
            String limit, RenderableLayer layer) {
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
            String limit, RenderableLayer layer) {
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
        // stage = guiAgentServices.getStage();
        // stage.setOpacity(.75);
        // testDisplay();
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
        List<Point3D> tmp = new ArrayList<>();
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
                    tmp.add(pt);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        // });
        return tmp;
    }

    public List<Point3D> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<Point3D> tmp = new ArrayList<>();
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
                    tmp.add(pt);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return tmp;
    }

    public final List<Point3D> retrieveAround(double lat, double lon) {
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
                    + "LIMIT " + LIMIT);
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
/*
    public void displayAllSounding() {

        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {

            // points3d = retrieveAll();
            points3d = retrieveIn(MIN_LAT, MIN_LON, MAX_LAT, MAX_LON);
            System.out.println("points3df : " + points3d.size());

        } // plusieurs jobs
                ,
                (progressHandle) -> {
                    // displaySounding(points3d);
                    testDisplay(points3d);
                }
        );
    }

    public void displaySounding(double lat, double lon, double depth) {

        BasicShapeAttributes basicShapeAttributes = new BasicShapeAttributes();
        basicShapeAttributes.setOutlineOpacity(0.0);
        basicShapeAttributes.setInteriorMaterial(new Material(SHOM_LOW_BATHYMETRY_CLUT.getColor(depth)));
        SurfaceSquare surface
                = new SurfaceSquare(new LatLon(Angle.fromDegrees(lat), Angle.fromDegrees(lon)), 100);
        surface.setAttributes(basicShapeAttributes);
        String label = "Lat : " + nf4.format(lat) + "°\n"
                + "Lon : " + nf4.format(lon) + "°\n"
                + "Depth : " + nf1.format(depth) + "m";
        surface.setValue(AVKey.DISPLAY_NAME, label);
        layer.addRenderable(surface);
    }

    public void displaySounding(List<Point3D> points) {
        points.stream().forEach((pt) -> {
            displaySounding(pt.getLat(),
                    pt.getLon(),
                    pt.getElevation());
        });
    }
*/
    /*
    public void testDisplay(List<Point3D> points) {

        //Rechercher le max de bathy, z = max - elevation
        maxElevation = 0.0;
        points.stream().filter((pt) -> (maxElevation < pt.getElevation())).forEach((pt) -> {
            maxElevation = pt.getElevation();
        });
        //  System.out.println("maxElevation : " + maxElevation);
        //  System.out.println("points : " + points.size());
        Delaunay_Triangulation dt = new Delaunay_Triangulation();

        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), maxElevation - pt.getElevation()));
        });
        // System.out.println("dt.avgEdgeLength() " + dt.avgEdgeLength());

        ArrayList<Triangle_dt> triangles = dt.get_triangles();
        //Suppression des grands triangles
        List<Triangle_dt> triangles1 = filterLargeEdges(dt.get_triangles());

        //  List<Coordinate> coordinatesJTS = NaVisuToJTS.toJTS(points);
        concaveHull = getConcaveHull(points, THRESHOLD);

    //    Set<Position> seaPts=WwjGeodesy.toGrid(MIN_LAT,MIN_LON,MAX_LAT,MAX_LON,100.0,100.0);
            
        
        Coordinate[] concaveHullCoordinates = concaveHull.getCoordinates();
        ArrayList<Position> pathPositions1 = new ArrayList<>();
        for (Coordinate concaveHullCoordinate : concaveHullCoordinates) {
            pathPositions1.add(Position.fromDegrees(concaveHullCoordinate.y,
                    concaveHullCoordinate.x,
                    (maxElevation - concaveHullCoordinate.z) * 10));
        }
        layer.addRenderable(createPath(pathPositions1, Material.RED));

        ArrayList<Position> pathPositions0 = new ArrayList<>();
        pathPositions0.add(Position.fromDegrees(MIN_LAT, MIN_LON, 100));
        pathPositions0.add(Position.fromDegrees(MAX_LAT, MIN_LON, 100));
        pathPositions0.add(Position.fromDegrees(MAX_LAT, MAX_LON, 100));
        pathPositions0.add(Position.fromDegrees(MIN_LAT, MAX_LON, 100));
        pathPositions0.add(Position.fromDegrees(MIN_LAT, MIN_LON, 100));
        layer.addRenderable(createPath(pathPositions0, Material.BLUE));

        ArrayList<Position> pathPositions2 = new ArrayList<>();
        pathPositions2.add(Position.fromDegrees(MIN_LAT, MIN_LON, maxElevation * 10));
        pathPositions2.add(Position.fromDegrees(MAX_LAT, MIN_LON, maxElevation * 10));
        pathPositions2.add(Position.fromDegrees(MAX_LAT, MAX_LON, maxElevation * 10));
        pathPositions2.add(Position.fromDegrees(MIN_LAT, MAX_LON, maxElevation * 10));
        pathPositions2.add(Position.fromDegrees(MIN_LAT, MIN_LON, maxElevation * 10));
        layer.addRenderable(createPath(pathPositions2, Material.GREEN));

        triangles1.stream()
                .filter((t) -> (t.A != null && t.B != null && t.C != null)).map((t) -> {
            ArrayList<Position> pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * 10)));
            pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, (t.B.z * 10)));
            pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, (t.C.z * 10)));
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * 10)));
            Path p = new Path(pathPositions);
            double z = maxElevation - t.B.z;
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setOutlineOpacity(1.0);
            attrs.setOutlineWidth(1d);
            attrs.setOutlineMaterial(Material.WHITE);
            p.setAttributes(attrs);
            p.setValue(AVKey.DISPLAY_NAME, (int) (maxElevation - t.A.z) + ", "
                    + (int) (maxElevation - t.B.z) + ", "
                    + (int) (maxElevation - t.C.z));
            return p;
        }
        ).map(
                (p) -> {
                    layer.addRenderable(p);
                    return p;
                }
        ).forEachOrdered(
                (_item) -> {
                    i++;
                }
        );
    }
*/
    /*
    public List<Triangle_dt> filterLargeEdges(ArrayList<Triangle_dt> triangles) {
        List<Triangle_dt> tmp1 = new ArrayList<>();
        triangles.stream().filter((t) -> (t.getBoundingBox().getWidth() < THRESHOLD)).forEach((t) -> {
            tmp1.add(t);

        });
        return tmp1;
    }

    public Geometry getConcaveHull(List<Point3D> points, double threshold) {
        List<Coordinate> coordinatesJTS = NaVisuToJTS.toJTS(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        MultiPoint geom = new GeometryFactory().createMultiPoint(coord);
        ConcaveHull ch = new ConcaveHull(geom, threshold);
        Geometry concaveHullTmp = ch.getConcaveHull();

        return concaveHullTmp;
    }

    public Geometry getLineString(List<Point3D> points) {
        List<Coordinate> coordinatesJTS = NaVisuToJTS.toJTS(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        LineString geom = new GeometryFactory().createLineString(coord);
        return geom;
    }
*/
    

    public Connection getConnection() {
        return connection;
    }
/*
    public Path createPath(ArrayList<Position> pathPositions, Material material) {
        Path p = new Path(pathPositions);
        ShapeAttributes attrs0 = new BasicShapeAttributes();
        attrs0.setOutlineOpacity(1.0);
        attrs0.setOutlineWidth(1d);
        attrs0.setOutlineMaterial(material);
        p.setAttributes(attrs0);
        return p;
    }
*/
}
