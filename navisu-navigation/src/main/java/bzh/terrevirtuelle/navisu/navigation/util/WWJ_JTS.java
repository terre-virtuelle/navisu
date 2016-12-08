/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.util;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import com.vividsolutions.jts.algorithm.Centroid;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class WWJ_JTS {

    public static String toPolygonWkt(List<? extends Position> positions) {
        String geometry = "POLYGON((";
        int l = positions.size();
        for (int i = 0; i < l - 1; i++) {
            geometry += positions.get(i).getLongitude().getDegrees() + " " + positions.get(i).getLatitude().getDegrees() + ",";
        }
        geometry += positions.get(l - 1).getLongitude().getDegrees() + " " + positions.get(l - 1).getLatitude().getDegrees() + "))";
        return geometry;
    }

    public static String toLineStringWkt(List<? extends Position> positions) {
        if (positions != null && positions.size() > 2) {
            String geometry = "LINESTRING(";
            int l = positions.size();
            for (int i = 0; i < l - 1; i++) {
                geometry += positions.get(i).getLongitude().getDegrees() + " " + positions.get(i).getLatitude().getDegrees() + ",";
            }
            geometry += positions.get(l - 1).getLongitude().getDegrees() + " " + positions.get(l - 1).getLatitude().getDegrees() + ")";
            return geometry;
        } else {
            return "";
        }
    }

    public static MultiPoint toMultiPoint(Set<Pair<Double, Double>> positions) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Pair<Double, Double> c : positions) {
            coordinates.add(new Coordinate(c.getX(), c.getY()));
        }
        Coordinate coordinates1[] = coordinates.toArray(new Coordinate[coordinates.size()]);
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createMultiPoint(coordinates.toArray(coordinates1));
    }

    public static String surfacePolylinesToWkt(List<SurfacePolylines> polylines) {
        //MULTILINESTRING((3 4,10 50,20 25),(-5 -8,-10 -8,-15 -4))
        if (polylines != null) {
            List<List<LatLon>> listListLatLon = new ArrayList<>();
            polylines.stream().map((s) -> s.getLocations()).map((ll) -> {
                List<LatLon> listLatLon = new ArrayList<>();
                for (LatLon lll : ll) {
                    listLatLon.add(lll);
                }
                return listLatLon;
            }).forEach((listLatLon) -> {
                listListLatLon.add(listLatLon);
            });
            String geometry = "MULTILINESTRING(";
            int size0 = listListLatLon.size();
            for (int i = 0; i < size0 - 1; i++) {
                List<LatLon> l = listListLatLon.get(i);
                int size1 = l.size();
                geometry += "(";
                for (int j = 0; j < size1 - 1; j++) {
                    geometry += l.get(j).longitude.degrees + " " + l.get(j).latitude.degrees + ",";
                }
                geometry += l.get(size1 - 1).longitude.degrees + " " + l.get(size1 - 1).latitude.degrees;
                geometry += "),";
            }
            List<LatLon> l = listListLatLon.get(size0 - 1);
            int size1 = l.size();
            geometry += "(";
            for (int j = 0; j < size1 - 1; j++) {
                geometry += l.get(j).longitude.degrees + " " + l.get(j).latitude.degrees + ",";
            }
            geometry += l.get(size1 - 1).longitude.degrees + " " + l.get(size1 - 1).latitude.degrees;
            geometry += "))";
            return geometry;
        } else {
            return null;
        }
    }

    public static String surfacePolylinesToWktWithCoalescence(List<SurfacePolylines> polylines) {
        List<LatLon> tmp = new ArrayList<>();
        // Iterable<? extends LatLon> tmp;

        List<LatLon> listLatLon = new ArrayList<>();
        for (SurfacePolylines s : polylines) {
            tmp.clear();

            for (LatLon i : s.getLocations()) {
                tmp.add(i);
            }

            int size = tmp.size();
            if (tmp.get(0) != tmp.get(size - 1)) {
                for (LatLon ll : tmp) {
                    listLatLon.add(ll);
                }
            }
        }
        String geometry = "LINESTRING(";
        int size = listLatLon.size();
        for (int j = 0; j < size - 1; j++) {
            geometry += listLatLon.get(j).longitude.degrees + " " + listLatLon.get(j).latitude.degrees + ",";
        }
        geometry += listLatLon.get(size - 1).longitude.degrees + " " + listLatLon.get(size - 1).latitude.degrees;
        geometry += ")";
        return geometry;
    }

    @SuppressWarnings("unchecked")
    public static Pair<Double, Double> getCentroid(String wkt) {
        WKTReader wktReader = new WKTReader();
        Geometry geometry = null;
        Pair<Double, Double> location = null;
        if (wkt != null) {
            try {
                geometry = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(WWJ_JTS.class.getName()).log(Level.SEVERE, null, ex);
            }
            Centroid centroid;
            if (geometry != null) {
                centroid = new Centroid(geometry);
                Coordinate coord = centroid.getCentroid();
                location = new Pair(coord.y, coord.x);
            }
        }
        return location;
    }

    //<geometry>POLYGON((-4.78192 48.24402-4.78192 48.24402))</geometry>
    public static Polygon wktPolygonToPolygon(String geometry) {
        List<Position> positions;
        Polygon polygon = null;
        positions = wktPolygonToPositionList(geometry);
        if (positions != null) {
            polygon = new Polygon(positions);
        }
        return polygon;
    }

    public static List<Position> wktPolygonToPositionList(String geometry) {
        List<Position> positions = null;
        String[] tab0;
        String[] tab1;
        String[] tab2;
        if (geometry != null && geometry.toUpperCase().contains("POLYGON")) {
            tab0 = geometry.split("\\(\\(");
            if (tab0.length > 1) {
                positions = new ArrayList<>();
                tab1 = tab0[1].split("\\)\\)");
                tab2 = tab1[0].split(",");
                int l = tab2.length;
                for (int i = 0; i < l; i++) {
                    positions.add(Position.fromDegrees(
                            Double.parseDouble(tab2[i].split(" ")[1]),
                            Double.parseDouble(tab2[i].split(" ")[0]),
                            5));
                }
            }
        }
        return positions;
    }
}
