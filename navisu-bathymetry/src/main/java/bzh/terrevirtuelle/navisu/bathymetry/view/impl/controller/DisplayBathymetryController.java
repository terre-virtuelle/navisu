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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

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
    protected static double maxElevation = -20.0;
    protected final double THRESHOLD = 0.008;//0.1;//0.008;//0.0015
    protected Geometry concaveHull;
    protected double MIN_DEPTH = 0.0;
    protected double MIN_LAT = 48.255496978759766;//48.25549;
    protected double MIN_LON = -4.549251079559326;
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
            double latM = 90.0;
            double lonM = 0.0;
            for (Point3D p : points3d) {
                if (latM >= p.getLat()) {
                    latM = p.getLat();
                }
                if (lonM >= p.getLon()) {
                    lonM = p.getLon();
                }
            }
            System.out.println("latM : " + latM + "lonM : " + lonM);
            System.out.println("compare " + Double.compare(latM, MIN_LAT));
        } // plusieurs jobs
                ,
                 (progressHandle) -> {

                    List<Triangle_dt> triangles = createDelaunay(points3d);

                    //Display plane 50m over sea
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
                    //   displayDelaunay(triangles1, Material.GREEN, 0.0);

                    //Create concaveHull from points with bathy information
                    concaveHull = NaVisuToJTS.getConcaveHull(points3d, THRESHOLD);
                    displayConcaveHull(concaveHull);

                    //Create a grid of points for triangulate sea level plane 
                    Point3D[][] seaPlane = toGrid(MIN_LAT, MIN_LON, 100.0, 100.0, 220, 220);
                    seaPlane = mergeData(seaPlane, 220, 220, triangles1);
                    /*
                    for (int k = 0; k < 220 - 1; k++) {
                        for (int l = 0; l < 220 - 1; l++) {
                            Point3D p = seaPlane[k][l];
                            Point_dt pp = new Point_dt(p.getLat(), p.getLon(), p.getElevation());
                            for (Triangle_dt tt : triangles1) {
                                if (tt.contains(pp)) {
                                    // displayTriangle(tt, Material.RED, maxElevation * 10);
                                    double el = (tt.A.z + tt.B.z + tt.C.z) / 3.0;
                                    seaPlane[k][l].setElevation(el);
                                }
                            }
                        }
                    }
                     */
                    List<Triangle_dt> triangles2 = createDelaunay(seaPlane, 220, 220);
                    displayDelaunay(triangles2, Material.WHITE, maxElevation * 10);
                    wwd.redrawNow();
                });

    }

    public Point3D[][] mergeData(Point3D[][] orgData, int lat, int lon, List<Triangle_dt> triangles) {
        Point3D[][] tmp = new Point3D[lat][lon];
        for (int k = 0; k < lat; k++) {
            System.arraycopy(orgData[k], 0, tmp[k], 0, lon);
        }
        for (int k = 0;
                k < lat - 1; k++) {
            for (int l = 0; l < lon - 1; l++) {
                Point3D p = tmp[k][l];
                Point_dt pp = new Point_dt(p.getLat(), p.getLon(), p.getElevation());
                double max =0.0;
                for (Triangle_dt tt : triangles) {
                    if (tt.contains(pp)) {
                      //  displayTriangle(tt, Material.RED, maxElevation * 10);
                      
                      if(max < tt.A.z){
                          max = tt.A.z;
                      }
                      if(max < tt.B.z){
                          max = tt.B.z;
                      }
                      if(max < tt.B.z){
                          max = tt.B.z;
                      }
                        //double el = (tt.A.z + tt.B.z + tt.C.z) / 3.0;
                        tmp[k][l].setElevation(max);
                    }
                }
            }
        }
        return tmp;
    }

    public void displaySounding(double lat, double lon, double depth) {
        //  System.out.println(lat +" "+ lon +" "+ depth);
        BasicShapeAttributes basicShapeAttributes = new BasicShapeAttributes();
        basicShapeAttributes.setOutlineOpacity(1.0);
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
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), maxElevation - pt.getElevation()));
        });
        return dt.get_triangles();
    }

    public List<Triangle_dt> createDelaunay(Point3D[][] points, int nbLat, int nbLon) {
        List<Triangle_dt> triangles = new ArrayList<>();
        for (int k = 0; k < nbLat - 1; k++) {
            for (int l = 0; l < nbLon - 1; l++) {
                Point3D pt0 = points[k][l];
                Point3D pt1 = points[k + 1][l];
                Point3D pt2 = points[k][l + 1];
                Point3D pt3 = points[k + 1][l + 1];
                triangles.add(new Triangle_dt(
                        new Point_dt(pt0.getLat(), pt0.getLon(), pt0.getElevation()),
                        new Point_dt(pt1.getLat(), pt1.getLon(), pt1.getElevation()),
                        new Point_dt(pt3.getLat(), pt3.getLon(), pt3.getElevation())));
                triangles.add(new Triangle_dt(
                        new Point_dt(pt0.getLat(), pt0.getLon(), pt0.getElevation()),
                        new Point_dt(pt3.getLat(), pt3.getLon(), pt3.getElevation()),
                        new Point_dt(pt2.getLat(), pt2.getLon(), pt2.getElevation())));
            }

        }

        return triangles;
    }

    public void displayTriangle(Triangle_dt t, Material material, double high) {
        if (t.A != null && t.B != null && t.C != null) {
            ArrayList<Position> pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * 10) + high));
            pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, (t.B.z * 10) + high));
            pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, (t.C.z * 10) + high));
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * 10) + high));
            Path p = new Path(pathPositions);
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setInteriorMaterial(material);
            attrs.setDrawInterior(true);
            attrs.setOutlineOpacity(1.0);
            attrs.setOutlineWidth(0.6);
            attrs.setOutlineMaterial(material);
            p.setAttributes(attrs);
            p.setValue(AVKey.DISPLAY_NAME, i + " : " + (int) (maxElevation - t.A.z) + ", "
                    + (int) (maxElevation - t.B.z) + ", "
                    + (int) (maxElevation - t.C.z));
            layer.addRenderable(p);
        }
    }

    public void displayDelaunay(List<Triangle_dt> triangles, Material material, double high) {
        i = 0;
        triangles.stream()
                .filter((t) -> (t.A != null && t.B != null && t.C != null)).map((t) -> {
            ArrayList<Position> pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * 10) + high));
            pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, (t.B.z * 10) + high));
            pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, (t.C.z * 10) + high));
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * 10) + high));
            Path p = new Path(pathPositions);
            //   double z = maxElevation - t.B.z;
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setOutlineOpacity(1.0);
            attrs.setOutlineWidth(0.5);
            attrs.setOutlineMaterial(material);
            p.setAttributes(attrs);
            p.setValue(AVKey.DISPLAY_NAME, i + " : " + (int) (maxElevation - t.A.z) + ", "
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
        wwd.redrawNow();
    }

    public void displayConcaveHull(Geometry concaveHull) {
        Coordinate[] concaveHullCoordinates = concaveHull.getCoordinates();
        ArrayList<Position> pathPositions1 = new ArrayList<>();
        for (Coordinate concaveHullCoordinate : concaveHullCoordinates) {
            pathPositions1.add(Position.fromDegrees(concaveHullCoordinate.y,
                    concaveHullCoordinate.x,
                    (maxElevation - concaveHullCoordinate.z) * 10));//*10

        }
        layer.addRenderable(createPath(pathPositions1, Material.RED));
        wwd.redrawNow();
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

    public Path createPath(List<Position> pathPositions, Material material) {
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
            l.add(new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), maxElevation));
        });

        return l;
    }

    public Point3D[][] toGrid(double orgLat, double orgLon,
            double dy, double dx,
            int nbLat, int nbLon) {

        Point3D[][] tab = new Point3D[nbLat][nbLon];
        for (int v = 0; v < nbLat; v++) {
            Position p = getPosition(Position.fromDegrees(orgLat, orgLon), 0.0, v * dy);
            for (int u = 0; u < nbLon; u++) {
                Position pp = getPosition(p, 90.0, u * dx);
                tab[v][u] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), maxElevation);
            }
        }

        return tab;
    }

    public static Position getPosition(Position posA, double bearing, double distance) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE,
                locA, bearing, distance, endBearing);

        Position p = new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), 0);
        return p;
    }

}
