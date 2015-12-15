/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.util;

import com.vividsolutions.jts.algorithm.CentroidArea;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class JTS {
    
    public static Pair<Double, Double> getCentroid(String wkt) {
      
        WKTReader wktReader = new WKTReader();
        Geometry geometry = null;
        Pair<Double, Double> location = null;
        if (wkt != null) {   
            try {
                geometry = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(JTS.class.getName()).log(Level.SEVERE, null, ex);
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
}
