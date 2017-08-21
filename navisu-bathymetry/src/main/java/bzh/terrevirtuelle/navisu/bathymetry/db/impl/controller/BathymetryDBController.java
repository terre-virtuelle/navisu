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
import bzh.terrevirtuelle.navisu.widgets.sonar.sonar3D.Points3D;
import bzh.terrevirtuelle.navisu.widgets.sonar.sonar3D.TriangleMeshes;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    double minLat = 90.0;
    double maxLat = 0.0;
    double minLon = 0.0;
    double maxLon = -90.0;
    double minElevation = 10000.0;
    double maxElevation = -20.0;
    double tmp;

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
                if (depth >= 0.0) {
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
                    + lonMin + ", " + latMin + ", "
                    + lonMax + ", " + latMax
                    + ", 4326) ");

            while (r.next()) {
                geom = (PGgeometry) r.getObject(2);
                depth = r.getFloat(3);
                if (depth >= 0.0) {
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

    public List<Point3D> retrieveBoundaries(List<Point3D> pts) {
        List<Point3D> tmp = new ArrayList<>();
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
                /*
                while (r1.next()) {
                    if ((Double) r1.getObject(1) < 900.0) {
                        points3d.add(new Point3D(latitude, longitude, r0.getDouble(2)));
                        PointPlacemark pointPlacemark = displaySounding(latitude, longitude, r0.getDouble(2));
                        layer.addRenderable(pointPlacemark);
                    }
                }
                 */
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        bathymetryEventProducerServices.setBathymetry(new Bathymetry(points3d));
        return points3d;
    }

    public void displayAllSounding() {

        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {

            // points3d = retrieveAll();
            points3d = retrieveIn(-4.550, 48.25, -4.3, 48.70);
            System.out.println("points3df : " + points3d.size());
        } // plusieurs jobs
                , (progressHandle) -> {
                    // displaySounding(points3d);
                   // sonarSounding(points3d);
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

    public void testDisplay(List<Point3D> points) {
        /*
        ShapeAttributes attrs = new BasicShapeAttributes();
        attrs.setOutlineOpacity(1.0);
        attrs.setOutlineWidth(1d);
        int a = latDimension * lonDimension;
        final Delaunay_Triangulation dt = new Delaunay_Triangulation();
        int l = 0;
        
            for (int h = 0; h < latDimension; h += 10) {
                for (int w = 0; w < lonDimension; w += 10) {
                    if ((!Double.isNaN(values[h + l + w]))) {
                        dt.insertPoint(new Point_dt(latTab[h], lonTab[w], values[l + h + w]));
                        //writer.write(String.valueOf(values[l + h + w]) + " ");
                    } else {
                        dt.insertPoint(new Point_dt(latTab[h], lonTab[w], average));
                    }
                }
                l += lonDimension;
            }
        Path triangle;
        int i = 0;
        double h = 0;
        System.out.println("dt.get_triangles() : " + dt.get_triangles().size());
        for (Triangle_dt t : dt.get_triangles()) {
            if (t.A != null && t.B != null && t.C != null) {
                
                h += t.B.z;
                Path path;//Polygon
                ArrayList<Position> pathPositions = new ArrayList<>();
                pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, 300000+(-average + t.A.z) * 200));
                pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, 300000+(-average + t.B.z) * 200));
                pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, 300000+(-average + t.C.z) * 200));
                pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, 300000+(-average + t.A.z) * 200));
                path = new Path(pathPositions);
                path.setAttributes(attrs);
                path.setValue(AVKey.DISPLAY_NAME, (int)t.B.z);
                layer.addRenderable(path);
                i++;
            }
        };
         */

        //Rechercher le max de bathy, z = max - elevation
        maxElevation = 0.0;
        points.stream().filter((pt) -> (maxElevation < pt.getElevation())).forEach((pt) -> {
            maxElevation = pt.getElevation();
        });
        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        for (Point3D pt : points) {
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), maxElevation - pt.getElevation()));
        }
        ShapeAttributes attrs = new BasicShapeAttributes();
        attrs.setOutlineOpacity(1.0);
        attrs.setOutlineWidth(1d);
        attrs.setOutlineMaterial(Material.WHITE);
        Path triangle;
        int i = 0;
        //  double h = 0;
        System.out.println("dt.get_triangles() : " + dt.get_triangles().size());
        for (Triangle_dt t : dt.get_triangles()) {
            if (t.A != null && t.B != null && t.C != null) {

                Path path;//Polygon
                ArrayList<Position> pathPositions = new ArrayList<>();
                pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, t.A.z * 10));
                pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, t.B.z * 10));
                pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, t.C.z * 10));
                pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, t.A.z * 10));
                path = new Path(pathPositions);
                path.setAttributes(attrs);
                path.setValue(AVKey.DISPLAY_NAME, (int) t.B.z);
                layer.addRenderable(path);
                i++;
            }
        };
    }

    public void sonarSounding(List<Point3D> points) {

        points.stream().map((p) -> {
            tmp = p.getLat();
            if (tmp < minLat) {
                minLat = tmp;
            }
            if (tmp > maxLat) {
                maxLat = tmp;
            }
            tmp = p.getLon();
            if (tmp < minLon) {
                minLon = tmp;
            }
            if (tmp > maxLon) {
                maxLon = tmp;
            }
            tmp = p.getElevation();
            return tmp;
        }).map((tmp) -> {
            if (tmp < minElevation) {
                minElevation = tmp;
            }
            return tmp;
        }).filter((tmp) -> (tmp > maxElevation)).forEach((tmp) -> {
            maxElevation = tmp;
        });
        // System.out.println(minLat + " " + maxLat + " " + minLon + " " + maxLon + " " + minElevation + " " + maxElevation);
        List<javafx.geometry.Point3D> list = new ArrayList<>();
        for (Point3D p : points) {
            list.add(new javafx.geometry.Point3D(p.getLat(), p.getLon(), p.getElevation()));
        }
        Platform.runLater(() -> {
            stage = new Stage();
            // stage.setOpacity(.75);
            stage.setHeight(400);
            stage.setWidth(800);
            stage.setX(600);
            stage.setY(200);
            //  System.out.println(list +"\n"+minLat+" "+maxLat+" "+minLon+" "+maxLon);
            //  new TriangleMeshes(stage);

            new Points3D(stage, list,
                    minLat, maxLat,
                    minLon, maxLon,
                    minElevation, maxElevation);

        });
    }

    public Connection getConnection() {
        return connection;
    }
}
