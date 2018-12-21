
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.delaunay;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 sept 2017
 * @author Serge Morvan
 */
public interface DelaunayServices
        extends ComponentService {

    List<Triangle_dt> createDelaunay(List<Point3D> points, double elevation);

    List<Triangle_dt> createDelaunay(Point3D[][] points, int nbLat, int nbLon, double elevation);

    Delaunay_Triangulation getTriangulation(List<Point3D> points);

    List<Point3D> toGrid(double latMin, double lonMin, double latMax, double lonMax,
            double y, double x, double elevation);

    Point3D[][] toGridTab(double latMin, double lonMin, double latMax, double lonMax,
            double y, double x, double elevation);

    Point3D[][] toGridTab(List<Point3D> bounds, double y, double x, double elevation);

    /*
    Point3D[][] toGrid(double orgLat, double orgLon, 
            double latMax, double lonMax,
            double dy, double dx,
            int nbLat, int nbLon, double elevation);
     */
    List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold);

    Point3D[][] mergePointsToGrid(List<Point3D> points, Point3D[][] grid);

    List<Triangle_dt> createDelaunay(List<Point3D> points);
}
