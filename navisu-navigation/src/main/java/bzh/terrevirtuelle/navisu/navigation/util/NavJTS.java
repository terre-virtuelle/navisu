/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.util;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import com.vividsolutions.jts.algorithm.CentroidArea;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class NavJTS {

    public static String positionsToWkt(List<Position> positions) {
        String geometry = "POLYGON((";
        int l = positions.size();
        for (int i = 0; i < l - 1; i++) {
            geometry += positions.get(i).getLongitude().getDegrees() + " " + positions.get(i).getLatitude().getDegrees() + ",";
        }
        geometry += positions.get(l - 1).getLongitude().getDegrees() + " " + positions.get(l - 1).getLatitude().getDegrees() + "))";
        return geometry;
    }

    public static Pair<Double, Double> getCentroid(String wkt) {
        WKTReader wktReader = new WKTReader();
        Geometry geometry = null;
        Pair<Double, Double> location = null;
        if (wkt != null) {
            try {
                geometry = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(NavJTS.class.getName()).log(Level.SEVERE, null, ex);
            }
            CentroidArea centroid = new CentroidArea();
            if (geometry != null) {
                centroid.add(geometry);
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
