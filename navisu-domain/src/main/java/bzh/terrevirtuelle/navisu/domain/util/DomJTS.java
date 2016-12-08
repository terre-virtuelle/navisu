/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.util;

import com.vividsolutions.jts.algorithm.Centroid;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DomJTS {

    @SuppressWarnings("unchecked")
    public static Pair<Double, Double> getCentroid(String wkt) {

        WKTReader wktReader = new WKTReader();
        Geometry geometry = null;
        Pair<Double, Double> location = null;
        if (wkt != null) {
            try {
                geometry = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(DomJTS.class.getName()).log(Level.SEVERE, null, ex);
            }
            Centroid centroidArea;
            if (geometry != null) {
                centroidArea = new Centroid(geometry);
                Coordinate coord = centroidArea.getCentroid();
                location = new Pair(coord.y, coord.x);
            }
        }
        return location;
    }
}
