/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.view.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.view.impl.DisplayBathymetryImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.utils.NaVisuToJTS;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceSquare;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.opensphere.geometry.algorithm.ConcaveHull;

/**
 *
 * @author serge
 */
public class DisplayBathymetryController {

    private static DisplayBathymetryController INSTANCE = null;
    protected static final Logger LOGGER = Logger.getLogger(DisplayBathymetryController.class.getName());
    protected BathymetryDBServices bathymetryDBServices;
    protected GuiAgentServices guiAgentServices;
    protected WorldWindow wwd;
    protected RenderableLayer layer;
    protected DisplayBathymetryImpl component;
    protected String LIMIT = "100";
    protected double maxElevation = -20.0;
    protected final double THRESHOLD = 0.0015;
    protected Geometry concaveHull;
    protected double MIN_DEPTH = 0.0;
    protected double MIN_LAT = 48.25;
    protected double MIN_LON = -4.55;
    protected double MAX_LAT = 48.45;
    protected double MAX_LON = -4.245;
    protected List<Point3D> points3d;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;
    private final static Ellipsoid REFERENCE = Ellipsoid.WGS84;//default
    private final static double KM_TO_METER = 1000;

    private DisplayBathymetryController(DisplayBathymetryImpl component,
            BathymetryDBServices bathymetryDBServices, GuiAgentServices guiAgentServices,
            String limit, RenderableLayer layer) {
        this.component = component;
        this.bathymetryDBServices = bathymetryDBServices;
        this.guiAgentServices = guiAgentServices;
        this.LIMIT = limit;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();
    }

    public static DisplayBathymetryController getInstance(DisplayBathymetryImpl component,
            BathymetryDBServices bathymetryDBServices, GuiAgentServices guiAgentServices,
            String limit, RenderableLayer layer) {
        if (INSTANCE == null) {
            INSTANCE = new DisplayBathymetryController(component,
                    bathymetryDBServices, guiAgentServices,
                    limit, layer);
        }
        return INSTANCE;
    }

    public void displayAllSounding() {

        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {

            // points3d = retrieveAll();
            points3d = bathymetryDBServices.retrieveIn(MIN_LAT, MIN_LON, MAX_LAT, MAX_LON);
            System.out.println("points3df : " + points3d.size());

        } // plusieurs jobs
                ,
                (progressHandle) -> {

                    List<Triangle_dt> triangles = createDelaunay(points3d);

                    //Display plane 100m over sea
                    ArrayList<Position> pathPositions0 = new ArrayList<>();
                    pathPositions0.add(Position.fromDegrees(MIN_LAT, MIN_LON, 100));
                    pathPositions0.add(Position.fromDegrees(MAX_LAT, MIN_LON, 100));
                    pathPositions0.add(Position.fromDegrees(MAX_LAT, MAX_LON, 100));
                    pathPositions0.add(Position.fromDegrees(MIN_LAT, MAX_LON, 100));
                    pathPositions0.add(Position.fromDegrees(MIN_LAT, MIN_LON, 100));
                    layer.addRenderable(createPath(pathPositions0, Material.BLUE));

                    //Display plane maxElevation*10 over sea
                    ArrayList<Position> pathPositions2 = new ArrayList<>();
                    pathPositions2.add(Position.fromDegrees(MIN_LAT, MIN_LON, maxElevation * 10));
                    pathPositions2.add(Position.fromDegrees(MAX_LAT, MIN_LON, maxElevation * 10));
                    pathPositions2.add(Position.fromDegrees(MAX_LAT, MAX_LON, maxElevation * 10));
                    pathPositions2.add(Position.fromDegrees(MIN_LAT, MAX_LON, maxElevation * 10));
                    pathPositions2.add(Position.fromDegrees(MIN_LAT, MIN_LON, maxElevation * 10));
                    layer.addRenderable(createPath(pathPositions2, Material.GREEN));

                    //Suppress large edges
                    List<Triangle_dt> triangles1 = filterLargeEdges(triangles, 0.0015);
                      displayDelaunay(triangles1);

                    //Create concaveHull from points with bathy information
                    concaveHull = getConcaveHull(points3d, THRESHOLD);
                    displayConcaveHull(concaveHull);

                    //Create a grid of points for triangulate elevation level plane and bathy
                    List<Point3D> seaPlane = toGrid(MIN_LAT, MIN_LON, MAX_LAT, MAX_LON, 100.0, 100.0);
                   // List<Triangle_dt> triangles2 = createDelaunay(seaPts);
                   // displayDelaunay(triangles2);
                    // displaySounding(seaPts);
                    List<Point3D> seaPts=new ArrayList<>();
                    for(Point3D p : seaPlane){
                        if(contains(concaveHull, p)==true){
                            seaPts.add(p);
                        }
                    }
                    
                    List<Triangle_dt> triangles2 = createDelaunay(seaPts);
                    List<Triangle_dt> triangles3 = filterLargeEdges(triangles2, 0.001);
                    displayDelaunay(triangles3);
                }
        );
    }

    public void displaySounding(double lat, double lon, double depth) {
        //  System.out.println(lat +" "+ lon +" "+ depth);
        BasicShapeAttributes basicShapeAttributes = new BasicShapeAttributes();
        basicShapeAttributes.setOutlineOpacity(1.0);
        // basicShapeAttributes.setInteriorMaterial(new Material(SHOM_LOW_BATHYMETRY_CLUT.getColor(depth)));
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

    public ArrayList<Triangle_dt> createDelaunay(List<Point3D> points) {

        //Rechercher le max de bathy, z = max - elevation
        maxElevation = 0.0;
        points.stream().filter((pt) -> (maxElevation < pt.getElevation())).forEach((pt) -> {
            maxElevation = pt.getElevation();
        });
        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), 100));//maxElevation - pt.getElevation()));
        });
        return dt.get_triangles();
    }

    public void displayDelaunay(List<Triangle_dt> triangles) {
        triangles.stream()
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

    public void displayConcaveHull(Geometry concaveHull) {
        Coordinate[] concaveHullCoordinates = concaveHull.getCoordinates();
        ArrayList<Position> pathPositions1 = new ArrayList<>();
        for (Coordinate concaveHullCoordinate : concaveHullCoordinates) {
            pathPositions1.add(Position.fromDegrees(concaveHullCoordinate.y,
                    concaveHullCoordinate.x,
                    (maxElevation - concaveHullCoordinate.z) * 10));
        }
        layer.addRenderable(createPath(pathPositions1, Material.RED));
    }

    public List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold) {
        List<Triangle_dt> tmp1 = new ArrayList<>();
        triangles.stream().filter((t) -> (t.getBoundingBox().getWidth() < threshold)).forEach((t) -> {
            tmp1.add(t);

        });
        return tmp1;
    }

    public Delaunay_Triangulation getTriangulation(List<Point3D> points) {
        Delaunay_Triangulation dt = new Delaunay_Triangulation();

        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), pt.getElevation()));
        });
        return dt;
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

    public Path createPath(ArrayList<Position> pathPositions, Material material) {
        Path p = new Path(pathPositions);
        ShapeAttributes attrs0 = new BasicShapeAttributes();
        attrs0.setOutlineOpacity(1.0);
        attrs0.setOutlineWidth(1d);
        attrs0.setOutlineMaterial(material);
        p.setAttributes(attrs0);
        return p;
    }

    public static double getDistanceM(Position posA, Position posB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates wpA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates wpB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getEllipsoidalDistance();
    }

    public static List<Point3D> toGrid(double latMin, double lonMin,
            double latMax, double lonMax,
            double y, double x) {

        double latRange = getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMax, lonMin));
        double lonRange = getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMin, lonMax));

        int nbLat = (int) (latRange / y);
        int nbLon = (int) (lonRange / x);

        Set<Position> tmp = new HashSet<>();
        for (int i = 0; i < nbLat; i++) {
            Position p = getPosition(Position.fromDegrees(latMin, lonMin), 0.0, i * 100);
            for (int j = 0; j < nbLon; j++) {
                tmp.add(getPosition(p, 90.0, j * 100));
            }
        }
        List<Point3D> l = new ArrayList<>();
        tmp.stream().forEach((p) -> {
            l.add(new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), 100));
        });

        return l;
    }

    public static Position getPosition(Position posA, double bearing, double distance) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE,
                locA, bearing, distance, endBearing);

        Position p = new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), 100);
        return p;
    }

    public boolean contains(Geometry geom, Point3D pt3D) {
        boolean result = false;
        Coordinate coord = new Coordinate(pt3D.getLon(), pt3D.getLat(), 100);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point pt = geometryFactory.createPoint(coord);
        result = !geom.contains(pt);
        return result;
    }
}
