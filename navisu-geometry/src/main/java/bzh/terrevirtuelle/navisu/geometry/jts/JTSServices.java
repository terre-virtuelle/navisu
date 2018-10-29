/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.jts;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.render.Path;
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
    
    Geometry getPolygon(List<Point3D> points); 

    boolean contains(Geometry geom, Point3D pt3D);

    List<Point3D> merge(List<Point3D> pts0, List<Point3D> pts1);

    List<Path> createDelaunayToPath(List<Point3D> pts);

    List<Geometry> createDelaunay(List<Point3D> pts);

    List<Path> createDelaunayToPath(Point3D[][] pts);

    List<Path> createDelaunayToPath(Point3D[][] pts, double maxElevation);

    List<Path> createDelaunayToPath(List<Point3D> pts, double maxElevation);

    List<Path> createDelaunayWithFilterOnAreaToPath(List<Point3D> pts, double filter);

    List<Path> createDelaunayWithFilterOnLengthToPath(List<Point3D> pts, double filter);

    List<Geometry> createDelaunayWithFilterOnArea(List<Point3D> pts, double filter);

    List<Path> createDelaunayWithFilterToPath(List<Point3D> pts, double filter, double maxElevation);

    Point3D[][] mergePointsToGrid(List<Point3D> points, Point3D[][] grid);

    boolean[][] getPoint3DInit();

    List<Point3D> getBuffer(Geometry geom, double bufferDistance, int capSize);

    List<Point3D> getBuffer(String geom, double bufferDistance, int capSize);
}
