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
import java.util.ArrayList;
import java.util.List;
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
}
