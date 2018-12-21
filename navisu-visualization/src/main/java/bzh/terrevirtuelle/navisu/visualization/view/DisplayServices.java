/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.visualization.view;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import java.util.List;
import java.util.Map;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 15 aout 2017
 * @author Serge Morvan
 */
public interface DisplayServices
        extends ComponentService {

    void displayPoints3D(List<Point3D> points, RenderableLayer layer);

    void displayPoints3DAsPath(List<Point3D> points, RenderableLayer layer);

    void displayPoints3DAsPath(List<Point3D> points, double height, RenderableLayer layer, Material material);

    void displayPoints3DAsTriangles(List<Point3D> points, RenderableLayer layer, Material material);

    void displayPaths(List<Path> paths, RenderableLayer layer, Material material, double verticalExaggeration);

    void displayPaths(GridBox3D gridBox3D, RenderableLayer layer, Material material);

    void displayPaths(List<Path> points, RenderableLayer layer, Material material, double verticalExaggeration, double verticalOffset);

    void displayPolygons(List<Polygon> points, RenderableLayer layer, Material material, double verticalExaggeration);

    void displayPolygonsFromPaths(List<Path> paths, RenderableLayer layer, Material material, double verticalExaggeration);

    void displayPolygons(List<Polygon> points, RenderableLayer layer, Material material, double verticalExaggeration, double verticalOffset);

    void displayGrid(List<List<Point3D>> latLons, RenderableLayer layer, Material material);

    void displayGrid(Point3D[][] latLons, RenderableLayer layer, Material material, double verticalExaggeration);

    List<Path> displayGridAsTriangles(Point3D[][] latLons, RenderableLayer layer, Material material, double verticalExaggeration);

    List<List<Path>> displayGridAsTriangles(List<Point3D[][]> latLons, RenderableLayer layer, double verticalExaggeration);

    void displayGrid(GridBox3D gridBox3D, RenderableLayer layer, Material material, double verticalExaggeration);

    void displayGridAsPolygon(Point3D[][] latLons, RenderableLayer layer, Material material, double verticalExaggeration);

    void displayPlane(double minLat, double minLon, double maxLat, double maxLon, double height,
            Material material, RenderableLayer l);

    void displayTriangle(Triangle_dt t, double height, double verticalExaggeration,
            Material material, RenderableLayer l);

    void displayDelaunay(List<Triangle_dt> triangles,
            double height, double verticalExaggeration,
            Material material, RenderableLayer l);

    void displayConcaveHull(Geometry concaveHull,
            double height, double verticalExaggeration,
            Material material, RenderableLayer l);

    Path createPath(List<Position> pathPositions, Material material);

    List<Path> createPaths(Point3D[][] latLons, double verticalExaggeration);

    List<List<Path>> createPaths(List<Point3D[][]> latLons, double verticalExaggeration);

    List<List<Path>> createPaths(GridBox3D box3D, double verticalExaggeration);

    List<Polygon> createPolygons(List<Path> paths);

    Map<Double, Material> createCLUT(String fileName);

    void exportWKML(String outputFilename, List<Path> paths);

    void exportWKML(List<Path> paths);

    void exportKML(String outputFilename, List<Path> paths);

    void exportKML(List<Path> paths);

    void exportWKMLPolygons(String outputFilename, List<Polygon> polygons);

    void exportWKMLPolygons(List<Polygon> polygons);

    String mergeKML(String inputFilename, String outputFilename);

    void exportASC(String outputFilename, Point3D[][] pts);

    Point3D[][] importASC(String outputFilename);
}
