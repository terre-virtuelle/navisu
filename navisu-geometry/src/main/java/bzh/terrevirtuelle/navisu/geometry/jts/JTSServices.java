/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.jts;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 sept 2017
 * @author Serge Morvan
 */
public interface JTSServices
        extends ComponentService {

    List<Coordinate> toListCoordinates(List<Point3DGeo> pts);

    Point3DGeo toPoint3D(Point point);

    List<Point3DGeo> toListPoint3D(Coordinate[] coord);

    Coordinate[] toTabCoordinates(List<Point3DGeo> pts);

    Geometry getConcaveHull(List<Point3DGeo> points, double threshold);

    Geometry getLineString(List<Point3DGeo> points);

    Geometry getPolygon(List<Point3DGeo> points);

    Geometry getBoundaries(Polygon polygon);

    Geometry getPolygonFromPath(Path path);

    Path getPathFromPolygon(Geometry path);

    List<Path> pathsInGeometry(Geometry geom, List<Path> faces);

    boolean contains(Geometry geom, Point3DGeo pt3D);

    List<Point3DGeo> merge(List<Point3DGeo> pts0, List<Point3DGeo> pts1);

    List<Path> createDelaunayToPath(List<Point3DGeo> pts);

    List<Geometry> createDelaunay(List<Point3DGeo> pts);

    List<Path> createDelaunayToPath(Point3DGeo[][] pts);

    List<Path> createDelaunayToPath(Point3DGeo[][] pts, double maxElevation);

    List<Path> createDelaunayToPath(Point3DGeo[][] pts, double maxElevation, boolean depth);

    List<Path> createDelaunayToPath(List<Point3DGeo> pts, double maxElevation);

    List<Path> createDelaunayToPath(List<Point3DGeo> pts, double maxElevation, boolean depth);

    List<Path> createDelaunayWithFilterOnAreaToPath(List<Point3DGeo> pts, double filter);

    List<Path> createDelaunayWithFilterOnLengthToPath(List<Point3DGeo> pts, double filter);

    List<Geometry> createDelaunayWithFilterOnArea(List<Point3DGeo> pts, double filter);

    List<Path> createDelaunayWithFilterToPath(List<Point3DGeo> pts, double filter, double maxElevation);

    Point3DGeo[][] mergePointsToGrid(List<Point3DGeo> points, Point3DGeo[][] grid);

    boolean[][] getPoint3DInit();

    List<Point3DGeo> getBuffer(Geometry geom, double bufferDistance, int capSize);

    List<Point3DGeo> getBuffer(String geom, double bufferDistance, int capSize);
}
