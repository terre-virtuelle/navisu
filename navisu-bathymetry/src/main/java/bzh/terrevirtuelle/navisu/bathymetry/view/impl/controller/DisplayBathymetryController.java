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
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;

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
    protected DisplayServices displayServices;
    protected WorldWindow wwd;
    protected RenderableLayer layer;
    protected DisplayBathymetryImpl component;
    protected String LIMIT = "100";
    protected static double maxElevation = -20.0;
    protected final double THRESHOLD = 0.0015;
    protected Geometry concaveHull;
    protected double MIN_DEPTH = 0.0;
    protected double MIN_LAT = 48.255496978759766;
    protected double MIN_LON = -4.549251079559326;
    protected double MAX_LAT = 48.45;
    protected double MAX_LON = -4.245;
    double distA;
    double distB;
    double distC;
    double distMin;
    Point_dt pMin;
    int NB_LAT = 220;
    int NB_LON = 220;
    protected List<Point3D> points3d;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;
    private final static Ellipsoid REFERENCE = Ellipsoid.WGS84;//default

    private DisplayBathymetryController(DisplayBathymetryImpl component,
            BathymetryDBServices bathymetryDBServices,
            GuiAgentServices guiAgentServices,
            DisplayServices displayServices,
            String limit, RenderableLayer layer) {
        this.component = component;
        this.bathymetryDBServices = bathymetryDBServices;
        this.guiAgentServices = guiAgentServices;
        this.displayServices = displayServices;
        this.LIMIT = limit;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();
    }

    public static DisplayBathymetryController getInstance(DisplayBathymetryImpl component,
            BathymetryDBServices bathymetryDBServices, GuiAgentServices guiAgentServices,
            DisplayServices displayServices,
            String limit, RenderableLayer layer) {
        if (INSTANCE == null) {
            INSTANCE = new DisplayBathymetryController(component,
                    bathymetryDBServices, guiAgentServices, displayServices,
                    limit, layer);
        }
        return INSTANCE;
    }

    public void displayAllSounding() {

        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {
            points3d = bathymetryDBServices.retrieveIn(MIN_LAT, MIN_LON, MAX_LAT, MAX_LON);
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

        } // plusieurs jobs
                ,
                 (progressHandle) -> {
                    //Rechercher le max de bathy, z = max - elevation, pour le positionnement
                    maxElevation = 0.0;
                    points3d.stream().filter((pt) -> (maxElevation < pt.getElevation())).forEach((pt) -> {
                        maxElevation = pt.getElevation();
                    });
                    //Display plane 0m over sea
                    displayServices.displayPlane(MIN_LAT, MIN_LON, MAX_LAT, MAX_LON, 100, Material.BLUE, layer);
                    //Display plane maxElevation*10 over sea
                    displayServices.displayPlane(MIN_LAT, MIN_LON, MAX_LAT, MAX_LON, maxElevation * 10, Material.GREEN, layer);

                    //Create Delaunay triangulation with bathymetry data
                    List<Triangle_dt> triangles = createDelaunay(points3d, maxElevation);
                    //Suppress large edges
                    List<Triangle_dt> triangles1 = filterLargeEdges(triangles, THRESHOLD);
                    displayServices.displayDelaunay(triangles1, maxElevation , 10.0, Material.GREEN, layer);

                    //Create concaveHull from points with bathy information
                    concaveHull = createConcaveHull(points3d, THRESHOLD);
                    displayServices.displayConcaveHull(concaveHull, maxElevation, 10.0, Material.RED, layer);

                    //Create a grid of points for triangulate sea level plane 
                    Point3D[][] seaPlane = toGrid(MIN_LAT, MIN_LON, 100.0, 100.0, NB_LAT, NB_LON);
                    //Modifie the fid whith bathyletry data
                    seaPlane = mergeData(seaPlane, NB_LAT, NB_LON, triangles1);
                    List<Triangle_dt> triangles2 = createDelaunay(seaPlane, NB_LAT, NB_LON,0.0);
                    
                    displayServices.displayDelaunay(triangles2, maxElevation , 10.0, Material.YELLOW, layer);
                    wwd.redrawNow();
                });

    }

    /**
     * @param orgData a simple grid of point3D, with z =0.0
     * @param nbLat nb of lines
     * @param nbLon nb of columns
     * @param triangles Delaunay tiangulation with elevation value
     * @return the initial grid whith elevation value
     *
     */
    public Point3D[][] mergeData(Point3D[][] orgData,
            int nbLat, int nbLon,
            List<Triangle_dt> triangles) {
        Point3D[][] tmp = new Point3D[nbLat][nbLon];
        for (int k = 0; k < nbLat; k++) {
            System.arraycopy(orgData[k], 0, tmp[k], 0, nbLon);
        }
        for (int k = 0; k < nbLat - 1; k++) {
            for (int l = 0; l < nbLon - 1; l++) {
                //Select one point
                Point3D p = tmp[k][l];
                Point_dt pp = new Point_dt(p.getLat(), p.getLon(), p.getElevation());
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

    public ArrayList<Triangle_dt> createDelaunay(List<Point3D> points, double elevation) {

        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), elevation - pt.getElevation()));
        });
        return dt.get_triangles();
    }

    public List<Triangle_dt> createDelaunay(Point3D[][] points, int nbLat, int nbLon, double elevation) {
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

    public Geometry createConcaveHull(List<Point3D> points3d, double threshold) {
        return NaVisuToJTS.getConcaveHull(points3d, threshold);
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

    public double getDistanceM(Position posA, Position posB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates wpA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates wpB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getEllipsoidalDistance();
    }

    public List<Point3D> toGrid(double latMin, double lonMin,
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

    public Position getPosition(Position posA, double bearing, double distance) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE,
                locA, bearing, distance, endBearing);

        Position p = new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), 0);
        return p;
    }

    
    public void displaySounding(double lat, double lon, double depth, RenderableLayer l) {
        BasicShapeAttributes basicShapeAttributes = new BasicShapeAttributes();
        basicShapeAttributes.setOutlineOpacity(1.0);
        SurfaceSquare surface
                = new SurfaceSquare(new LatLon(Angle.fromDegrees(lat), Angle.fromDegrees(lon)), depth);
        surface.setAttributes(basicShapeAttributes);
        String label = "Lat : " + nf4.format(lat) + "°\n"
                + "Lon : " + nf4.format(lon) + "°\n"
                + "Depth : " + nf1.format(depth) + "m";
        surface.setValue(AVKey.DISPLAY_NAME, label);

        l.addRenderable(surface);
    }

    public void displaySounding(List<Point3D> points, RenderableLayer l) {
        points.stream().forEach((pt) -> {
            displaySounding(pt.getLat(),
                    pt.getLon(),
                    pt.getElevation(), l);
        });
    }

}
