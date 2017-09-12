/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.delaunay;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.Geodesy;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import gov.nasa.worldwind.geom.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author serge
 * @date Sep 12, 2017
 */
public class Delaunay {

    public static ArrayList<Triangle_dt> createDelaunay(List<Point3D> points, double elevation) {

        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), elevation - pt.getElevation()));
        });
        return dt.get_triangles();
    }

    public static List<Triangle_dt> createDelaunay(Point3D[][] points, int nbLat, int nbLon, double elevation) {
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

    public static Delaunay_Triangulation getTriangulation(List<Point3D> points) {
        Delaunay_Triangulation dt = new Delaunay_Triangulation();

        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLat(), pt.getLon(), pt.getElevation()));
        });
        return dt;
    }
    public static List<Point3D> toGrid(double latMin, double lonMin,
            double latMax, double lonMax,
            double y, double x,
            double elevation) {

        double latRange = Geodesy.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMax, lonMin));
        double lonRange = Geodesy.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMin, lonMax));

        int nbLat = (int) (latRange / y);
        int nbLon = (int) (lonRange / x);

        Set<Position> tmp = new HashSet<>();
        for (int i = 0; i < nbLat; i++) {
            Position p = Geodesy.getPosition(Position.fromDegrees(latMin, lonMin), 0.0, i * 100);
            for (int j = 0; j < nbLon; j++) {
                tmp.add(Geodesy.getPosition(p, 90.0, j * 100));
            }
        }
        List<Point3D> l = new ArrayList<>();
        tmp.stream().forEach((p) -> {
            l.add(new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), elevation));
        });

        return l;
    }

    public static Point3D[][] toGrid(double orgLat, double orgLon,
            double dy, double dx,
            int nbLat, int nbLon,
            double elevation) {

        Point3D[][] tab = new Point3D[nbLat][nbLon];
        for (int v = 0; v < nbLat; v++) {
            Position p = Geodesy.getPosition(Position.fromDegrees(orgLat, orgLon), 0.0, v * dy);
            for (int u = 0; u < nbLon; u++) {
                Position pp = Geodesy.getPosition(p, 90.0, u * dx);
                tab[v][u] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), elevation);
            }
        }

        return tab;
    }
}
