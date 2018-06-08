/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.delaunay.impl;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.delaunay.Delaunay;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import gov.nasa.worldwind.geom.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author serge
 * @date Sep 12, 2017
 */
public class DelaunayImpl
        implements Delaunay, DelaunayServices, ComponentState {

    @UsedService
    GeodesyServices geodesyServices;

    /**
     *
     * @param points
     * @param elevation
     * @return
     */
    @Override
    public List<Triangle_dt> createDelaunay(List<Point3D> points, double elevation) {

        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLatitude(), pt.getLongitude(), elevation - pt.getElevation()));
        });
        return dt.get_triangles();
    }

    @Override
    public List<Triangle_dt> createDelaunay(Point3D[][] points, int nbLat, int nbLon, double elevation) {
        List<Triangle_dt> triangles = new ArrayList<>();
        for (int k = 0; k < nbLat - 1; k++) {
            for (int l = 0; l < nbLon - 1; l++) {
              //  System.out.println("k : "+k+" l : "+l);
                Point3D pt0 = points[k][l];
                Point3D pt1 = points[k + 1][l];
                Point3D pt2 = points[k][l + 1];
                Point3D pt3 = points[k + 1][l + 1];
                triangles.add(new Triangle_dt(
                        new Point_dt(pt0.getLatitude(), pt0.getLongitude(), pt0.getElevation()),
                        new Point_dt(pt1.getLatitude(), pt1.getLongitude(), pt1.getElevation()),
                        new Point_dt(pt3.getLatitude(), pt3.getLongitude(), pt3.getElevation())));
                triangles.add(new Triangle_dt(
                        new Point_dt(pt0.getLatitude(), pt0.getLongitude(), pt0.getElevation()),
                        new Point_dt(pt3.getLatitude(), pt3.getLongitude(), pt3.getElevation()),
                        new Point_dt(pt2.getLatitude(), pt2.getLongitude(), pt2.getElevation())));
            }
        }
        return triangles;
    }

    @Override
    public Delaunay_Triangulation getTriangulation(List<Point3D> points) {
        Delaunay_Triangulation dt = new Delaunay_Triangulation();

        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLatitude(), pt.getLongitude(), pt.getElevation()));
        });
        return dt;
    }

    @Override
    public List<Point3D> toGrid(double latMin, double lonMin,
            double latMax, double lonMax,
            double y, double x,
            double elevation) {

        double latRange = geodesyServices.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMax, lonMin));
        double lonRange = geodesyServices.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMin, lonMax));

        int nbLat = (int) (latRange / y);
        int nbLon = (int) (lonRange / x);

        Set<Position> tmp = new HashSet<>();
        for (int i = 0; i < nbLat; i++) {
            Position p = geodesyServices.getPosition(Position.fromDegrees(latMin, lonMin), 0.0, i * 100);
            for (int j = 0; j < nbLon; j++) {
                tmp.add(geodesyServices.getPosition(p, 90.0, j * 100));
            }
        }
        List<Point3D> l = new ArrayList<>();
        tmp.stream().forEach((p) -> {
            l.add(new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), elevation));
        });

        return l;
    }

    /*
    @Override
    public Point3D[][] toGridTab(double latMin, double lonMin, double latMax, double lonMax, double y, double x, double elevation) {
        double latRange = geodesyServices.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMax, lonMin));
        double lonRange = geodesyServices.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMin, lonMax));

        int nbLat = (int) (latRange / y);
        int nbLon = (int) (lonRange / x);
        
        return toGrid(latMin, lonMin, latMax, lonMax, y, x, nbLat, nbLon, elevation);

    }

    @Override
    public Point3D[][] toGrid(double orgLat, double orgLon,
            double latMax, double lonMax,
            double dy, double dx,
            int nbLat, int nbLon,
            double elevation) {
//Pb d'arrondis

        Point3D[][] tab = new Point3D[nbLat][nbLon];
        for (int v = 0; v < nbLat; v++) {
            Position p = geodesyServices.getPosition(Position.fromDegrees(orgLat, orgLon), 0.0, v * dy);

            for (int u = 0; u < nbLon; u++) {
                Position pp = geodesyServices.getPosition(p, 90.0, u * dx);
                tab[v][u] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), elevation);
                
                //tab[v][u] = new Point3D(orgLat+(v*dy), orgLon+(u*dx), elevation);
            }
            // Position pp = geodesyServices.getPosition(p, 90.0, lonMax);
            // tab[v][nbLon - 1] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), elevation);
        }

        // Position p = geodesyServices.getPosition(Position.fromDegrees(orgLat, orgLon), 0.0, latMax);
        // tab[v][nbLon - 1] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), elevation);
        return tab;
    }
     */
    @Override
    public Point3D[][] toGridTab(double latMin, double lonMin, double latMax, double lonMax,
            double y, double x, double elevation) {
        Position p = geodesyServices.getPosition(Position.fromDegrees(latMin, lonMin), 0.0, y);
        double latInc = latMin - p.getLatitude().getDegrees();
        latInc = Math.abs(latInc);
        double lat = latMin;
        p = geodesyServices.getPosition(Position.fromDegrees(latMin, lonMin), 90.0, x);
        double lonInc = lonMin - p.getLongitude().getDegrees();
        lonInc = Math.abs(lonInc);
        double lon = lonMin;
        List<List<Point3D>> ptsList = new ArrayList<>();
        while (lat < latMax) {
            List<Point3D> l = new ArrayList<>();
            ptsList.add(l);
            while (lon < lonMax) {
                l.add(new Point3D(lat, lon, elevation));
                lon += lonInc;
            }
            l.add(new Point3D(lat, lonMax, elevation));//last column
            lon = lonMin;
            lat += latInc;
        }
        List<Point3D> l = new ArrayList<>();
        ptsList.add(l);
        lat = latMax;
        for (int i = 0; i < ptsList.get(0).size(); i++) {
            l.add(new Point3D(lat, ptsList.get(0).get(i).getLongitude(), elevation));//last line
        }

        int latCount = ptsList.size();
        int lonCount = ptsList.get(0).size();
        Point3D[][] ptsTab = new Point3D[latCount][lonCount];
        for (int i = 0; i < latCount; i++) {
            for (int j = 0; j < lonCount; j++) {
                ptsTab[i][j] = ptsList.get(i).get(j);
            }
        }
        return ptsTab;
    }

    @Override
    public Point3D[][] toGrid(double orgLat, double orgLon,
            double latMax, double lonMax,
            double dy, double dx,
            int nbLat, int nbLon,
            double elevation) {
//Pb d'arrondis

        Point3D[][] tab = new Point3D[nbLat][nbLon];
        for (int v = 0; v < nbLat; v++) {
            Position p = geodesyServices.getPosition(Position.fromDegrees(orgLat, orgLon), 0.0, v * dy);

            for (int u = 0; u < nbLon; u++) {
                Position pp = geodesyServices.getPosition(p, 90.0, u * dx);
                tab[v][u] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), elevation);

                //tab[v][u] = new Point3D(orgLat+(v*dy), orgLon+(u*dx), elevation);
            }
            // Position pp = geodesyServices.getPosition(p, 90.0, lonMax);
            // tab[v][nbLon - 1] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), elevation);
        }

        // Position p = geodesyServices.getPosition(Position.fromDegrees(orgLat, orgLon), 0.0, latMax);
        // tab[v][nbLon - 1] = new Point3D(pp.getLatitude().getDegrees(), pp.getLongitude().getDegrees(), elevation);
        return tab;
    }

    @Override
    public List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold) {
        List<Triangle_dt> tmp1 = new ArrayList<>();
        triangles.stream().filter((t) -> (t.getBoundingBox().getWidth() < threshold)).forEach((t) -> {
            //  System.out.println("t.getBoundingBox().getWidth() : " +t.getBoundingBox().getWidth());
            tmp1.add(t);
        });
        return tmp1;
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
