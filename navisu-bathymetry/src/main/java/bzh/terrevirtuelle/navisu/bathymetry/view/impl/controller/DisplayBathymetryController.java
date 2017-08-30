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
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
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
import java.util.List;
import java.util.logging.Logger;
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

    public List<Triangle_dt> filterLargeEdges(ArrayList<Triangle_dt> triangles) {
        List<Triangle_dt> tmp1 = new ArrayList<>();
        triangles.stream().filter((t) -> (t.getBoundingBox().getWidth() < THRESHOLD)).forEach((t) -> {
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
}
