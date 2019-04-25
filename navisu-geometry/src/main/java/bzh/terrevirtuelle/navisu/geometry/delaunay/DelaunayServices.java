
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.delaunay;

import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.raster.RasterInfo;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 sept 2017
 * @author Serge Morvan
 */
public interface DelaunayServices
        extends ComponentService {

    List<Triangle_dt> createDelaunay(List<Point3DGeo> points, double elevation);

    List<Triangle_dt> createDelaunay(Point3DGeo[][] points, int nbLat, int nbLon, double elevation);

    List<Triangle_dt> createDelaunay(Point3DGeo[][] points, double elevation);

    Delaunay_Triangulation getTriangulation(List<Point3DGeo> points);

    List<Point3DGeo> toGrid(double latMin, double lonMin, double latMax, double lonMax,
            double y, double x, double elevation);

    Point3DGeo[][] toGridTab(double latMin, double lonMin, double latMax, double lonMax,
            double y, double x, double elevation);

    Point3DGeo[][] toGridTab(List<Point3DGeo> bounds, int lines, int cols);

    Point3DGeo[][] toGridTab(DEM dem);

    Point3DGeo[][] rasterToGridTab(RasterInfo rasterInfo);

    RasterInfo rasterToDemTiff(RasterInfo rasterInfo);


    String gdalInfo(RasterInfo rasterInfo);

    RasterInfo toGridTiff(DEM dem, String name);

    RasterInfo toGridTiff(Point3DGeo[][] dem, String name);

    List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold);

    Point3DGeo[][] mergePointsToGrid(List<Point3DGeo> points, Point3DGeo[][] grid);

    List<Triangle_dt> createDelaunay(List<Point3DGeo> points);
}
