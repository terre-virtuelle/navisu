/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.view;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 15 aout 2017
 * @author Serge Morvan
 */
public interface DisplayBathymetryServices
        extends ComponentService {

    InstrumentDriver getDriver();

    Point3D[][] mergeData(Point3D[][] orgData, int nbLat, int nbLon, List<Triangle_dt> triangles);

    ArrayList<Triangle_dt> createDelaunay(List<Point3D> points, double elevation);

    Delaunay_Triangulation getTriangulation(List<Point3D> points);

    List<Triangle_dt> createDelaunay(Point3D[][] points, int nbLat, int nbLon,double elevation);

    Geometry createConcaveHull(List<Point3D> points3d, double threshold);

    List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold);

    double getDistanceM(Position posA, Position posB);

    List<Point3D> toGrid(double latMin, double lonMin, double latMax, double lonMax, double y, double x);

    Point3D[][] toGrid(double orgLat, double orgLon, double dy, double dx, int nbLat, int nbLon);

    Position getPosition(Position posA, double bearing, double distance);

    void displaySounding(double lat, double lon, double depth, RenderableLayer l);

    void displaySounding(List<Point3D> points, RenderableLayer l);

    public void displayAllSounding();
    
}
