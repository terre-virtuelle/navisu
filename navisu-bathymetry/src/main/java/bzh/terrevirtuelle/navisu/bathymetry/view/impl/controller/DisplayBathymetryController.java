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
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;

import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.SurfaceSquare;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Logger;
import org.gavaghan.geodesy.Ellipsoid;

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
    protected DelaunayServices delaunayServices;
    protected JTSServices jtsServices;
    protected WorldWindow wwd;
    protected RenderableLayer layer;
    protected DisplayBathymetryImpl component;
    protected String limit = "100";
    protected double maxElevation;
    protected final double THRESHOLD = 0.0015;
    protected Geometry concaveHull;
    protected double MIN_DEPTH = 0.0;
    protected NumberFormat formatter = new DecimalFormat("#0.00");
    double distA;
    double distB;
    double distC;
    double distMin;
    Point_dt pMin;

    protected List<Point3D> points3d;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;
    private final static Ellipsoid REFERENCE = Ellipsoid.WGS84;//default

    private DisplayBathymetryController(DisplayBathymetryImpl component,
            BathymetryDBServices bathymetryDBServices,
            GuiAgentServices guiAgentServices,
            DisplayServices displayServices,
            DelaunayServices delaunayServices,
            JTSServices jtsServices,
            RenderableLayer layer) {
        this.component = component;
        this.bathymetryDBServices = bathymetryDBServices;
        this.guiAgentServices = guiAgentServices;
        this.displayServices = displayServices;
        this.delaunayServices = delaunayServices;
        this.jtsServices = jtsServices;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();
    }

    public static DisplayBathymetryController getInstance(DisplayBathymetryImpl component,
            BathymetryDBServices bathymetryDBServices, GuiAgentServices guiAgentServices,
            DisplayServices displayServices,
            DelaunayServices delaunayServices,
            JTSServices jtsServices,
            RenderableLayer layer) {
        if (INSTANCE == null) {
            INSTANCE = new DisplayBathymetryController(component,
                    bathymetryDBServices, guiAgentServices,
                    displayServices, delaunayServices,
                    jtsServices,
                    layer);
        }
        return INSTANCE;
    }

    public void displayAllSounding(double minLat, double minLon, double maxLat, double maxLon, int nbLat, int nbLon) {

        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {
            points3d = bathymetryDBServices.retrieveIn(minLat, minLon, maxLat, maxLon);
            double latM = 90.0;
            double lonM = 0.0;
            for (Point3D p : points3d) {
                if (latM >= p.getLatitude()) {
                    latM = p.getLatitude();
                }
                if (lonM >= p.getLongitude()) {
                    lonM = p.getLongitude();
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
                    System.out.println("maxElevation : " + maxElevation);

                    //Display plane 0m over sea
                    // displayServices.displayPlane(minLat, minLon, maxLat, maxLon, 100, Material.BLUE, layer);
                    //Display plane maxElevation*10 over sea
                    //  displayServices.displayPlane(minLat, minLon, maxLat, maxLon, maxElevation * 10, Material.GREEN, layer);
                    //Create Delaunay triangulation with bathymetry data
                    List<Triangle_dt> triangles = delaunayServices.createDelaunay(points3d, maxElevation);
                    //Suppress large edges
                    List<Triangle_dt> triangles1 = delaunayServices.filterLargeEdges(triangles, THRESHOLD);
                    displayServices.displayDelaunay(triangles1, maxElevation, 10.0, Material.GREEN, layer);

                    //Create concaveHull from points with bathy information
                    //  concaveHull = jtsServices.getConcaveHull(points3d, THRESHOLD);
                    // displayServices.displayConcaveHull(concaveHull, maxElevation, 10.0, Material.RED, layer);
                    //Create a grid of points for triangulate sea level plane 
                    // Point3D[][] seaPlane = delaunayServices.toGrid(minLat, minLon, 100.0, 100.0, nbLat, nbLon, maxElevation);
                    //Modifie the z whith bathyletry data
                    //    seaPlane = bathymetryDBServices.mergeData(seaPlane, nbLat, nbLon, triangles1);
                    //   List<Triangle_dt> triangles2 = delaunayServices.createDelaunay(seaPlane, nbLat, nbLon, 0.0);
                    //     displayServices.displayDelaunay(triangles2, maxElevation, 10.0, Material.YELLOW, layer);
                    wwd.redrawNow();
                });

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
            displaySounding(pt.getLatitude(),
                    pt.getLongitude(),
                    pt.getElevation(), l);
        });

    }

    public void displayDelaunaySounding(List<Point3D> points, RenderableLayer layer, double maxElevation) {
        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {
            //Create Delaunay triangulation with bathymetry data
            List<Triangle_dt> triangles = delaunayServices.createDelaunay(points, maxElevation);
            //Suppress large edges
            List<Triangle_dt> triangles1 = delaunayServices.filterLargeEdges(triangles, THRESHOLD);
            displayServices.displayDelaunay(triangles1, maxElevation, 10.0, Material.GREEN, layer);
            wwd.redrawNow();
        });
    }

}
