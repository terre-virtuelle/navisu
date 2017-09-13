/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.jts;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 sept 2017
 * @author Serge Morvan
 */
public interface JTSServices
        extends ComponentService {

    List<Coordinate> toListCoordinates(List<Point3D> pts);

    Coordinate[] toTabCoordinates(List<Point3D> pts);

    Geometry getConcaveHull(List<Point3D> points, double threshold);

    Geometry getLineString(List<Point3D> points);

    boolean contains(Geometry geom, Point3D pt3D);

    List<Point3D> merge(List<Point3D> pts0, List<Point3D> pts1);

}
