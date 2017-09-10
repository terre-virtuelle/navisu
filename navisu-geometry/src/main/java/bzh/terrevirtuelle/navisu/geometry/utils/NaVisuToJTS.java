/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.utils;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opensphere.geometry.algorithm.ConcaveHull;

/**
 *
 * @author serge
 */
public class NaVisuToJTS {

    public static List<Coordinate> toListCoordinates(List<Point3D> pts) {
        List<Coordinate> coordJTSs = new ArrayList<>();
        pts.stream().forEach((p) -> {
            coordJTSs.add(new Coordinate(p.getLon(), p.getLat(), p.getElevation()));
        });
        return coordJTSs;
    }

    public static Coordinate[] toTabCoordinates(List<Point3D> pts) {
        Coordinate[] tmp = new Coordinate[pts.size()];
        for (int i = 0; i < tmp.length; i++) {
            Point3D pt = pts.get(i);
            tmp[i] = new Coordinate(pt.getLon(), pt.getLat(), pt.getElevation());
        }
        return tmp;
    }
    public static Geometry getConcaveHull(List<Point3D> points, double threshold) {
        List<Coordinate> coordinatesJTS = NaVisuToJTS.toListCoordinates(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        MultiPoint geom = new GeometryFactory().createMultiPoint(coord);
        ConcaveHull ch = new ConcaveHull(geom, threshold);
        Geometry concaveHullTmp = ch.getConcaveHull();

        return concaveHullTmp;
    }

    public static Geometry getLineString(List<Point3D> points) {
        List<Coordinate> coordinatesJTS = NaVisuToJTS.toListCoordinates(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        LineString geom = new GeometryFactory().createLineString(coord);
        return geom;
    }
    public static boolean contains(Geometry geom, Point3D pt3D) {
        boolean result;
        Coordinate coord = new Coordinate(pt3D.getLon(), pt3D.getLat(), 100);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point pt = geometryFactory.createPoint(coord);
        result = !geom.contains(pt);
        return result;
    }
    public static List<Point3D> merge(List<Point3D> pts0, List<Point3D> pts1) {

        System.out.println("pts0 : " + pts0.size());
        System.out.println("pts1 : " + pts1.size());
        List<Point3D> tmp = new ArrayList<>();
        Coordinate[] tab0 = NaVisuToJTS.toTabCoordinates(pts0);
        Coordinate[] tab1 = NaVisuToJTS.toTabCoordinates(pts1);
        GeometryFactory geomFactory = new GeometryFactory();
        MultiPoint multiPoint0 = geomFactory.createMultiPoint(tab0);
        multiPoint0.setSRID(4326);
        MultiPoint multiPoint1 = geomFactory.createMultiPoint(tab1);
        multiPoint1.setSRID(4326);
        Geometry geom = multiPoint0.union(multiPoint1);
        Coordinate[] tab3 = geom.getCoordinates();
        Set<Point3D> set= new HashSet<>();
        for(Coordinate c : tab3){ 
            set.add(new Point3D(c.y, c.x,c.z));
        }
        tmp.addAll(set);
        return tmp;
    }
}
