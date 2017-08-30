/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.utils;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import com.vividsolutions.jts.geom.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class NaVisuToJTS {

    public static List<Coordinate> toJTS(List<Point3D> pts) {
        List<Coordinate> coordJTSs = new ArrayList<>();
        pts.stream().forEach((p) -> {
            coordJTSs.add(new Coordinate(p.getLon(), p.getLat(), p.getElevation()));
        });
        return coordJTSs;
    }
}
