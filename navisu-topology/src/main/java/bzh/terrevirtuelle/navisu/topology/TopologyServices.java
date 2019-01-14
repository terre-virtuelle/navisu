/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.topology;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPoint;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.util.List;
import java.util.Set;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge
 * @date Dec 8, 2017
 */
public interface TopologyServices
        extends ComponentService {

    /**
     *
     * @param positions
     * @return
     */
    String wwjPositionsToPolygonWkt(List<? extends Position> positions);

    /**
     *
     * @param positions
     * @return
     */
    String wwjLatLonsToPolygonWkt(List<LatLon> positions);

    /**
     *
     * @param positions
     * @return
     */
    String wwjPositionsToLineWkt(List<? extends Position> positions);

    /**
     *
     * @param positions
     * @return
     */
    MultiPoint wwjPositionsToJtsMultiPoint(Set<Pair<Double, Double>> positions);

    /**
     *
     * @param polylines
     * @return
     */
    String wwjSurfacePolylinesToWkt(List<SurfacePolylines> polylines);

    /**
     *
     * @param polylines
     * @return
     */
    String wwjSurfacePolylinesToWktWithCoalescence(List<SurfacePolylines> polylines);

    /**
     *
     * @param polygon
     * @return
     */
    Geometry wwjPolygonToJtsGeometry(Polygon polygon);

    /**
     *
     * @param locations
     * @return
     */
    String wwjLocationsToWKT(Iterable<? extends LatLon> locations);

    /**
     *
     * @param wkt
     * @return
     */
    Pair<Double, Double> wtkGetCentroid(String wkt);

    /**
     *
     * @param geometry
     * @return
     */
    Polygon wktPolygonToWwjPolygon(String geometry);

    LatLon wktMultiPointToWwjLatLon(String geometry);

    List<Point3D> wktMultiPointZMToPoint3DList(String geometry);

    Point3D wktPointZMToPoint3D(String geometry);

    /**
     *
     * @param geometry
     * @param height
     * @return
     */
    Polygon jtsPolygonToWwjPolygon(Geometry geometry, double height);

    /**
     *
     * @param geometry
     * @return
     */
    List<Polygon> wktMultiPolygonToWwjPolygons(String geometry);

    /**
     *
     * @param geometry
     * @return
     */
    Polygon wktPolygonToWwjPolygon(Geometry geometry);

    /**
     *
     * @param geometry
     * @return
     */
    List<Position> wktPolygonToPositions(String geometry);

    /**
     *
     * @param geometry
     * @return
     */
    Polygon wktMultiLineToWwjPolygon(String geometry);

    /**
     *
     * @param geometry
     * @return
     */
    List<Polygon> wktMultiLineToWwjMultiPolygon(String geometry);

    List<Polygon> wktPolygonsToWwjPolygons(List<Geometry> polygons);

    List<Path> wktPolygonsToWwjPaths(List<Geometry> polygons);

    /*
    Filter on area of each polygon
     */
    List<Path> wktPolygonsToWwjPathsWithFilterOnArea(List<Geometry> polygons, double filter);

    List<Path> wktPolygonsToWwjPathsWithFilterOnLength(List<Geometry> polygons, double filter);

    /**
     *
     * @param geometry
     * @param height
     * @return
     */
    Path wktMultiLineToWwjPath(String geometry, double height);

    /**
     *
     * @param geometry
     * @param height
     * @return
     */
    Path wktLineToWwjPath(String geometry, double height);

    /**
     *
     * @param geometry
     * @param pts
     * @return
     */
    Geometry filterWwjLatLonsWithGeometryJts(Geometry geometry, List<LatLon> pts);

    /**
     * Clip a list of WKT Multiline with a rectangle
     *
     * @param data
     * @param latMin
     * @param lonMin
     * @param latMax
     * @param lonMax
     * @return List of WKT Multiline
     */
    List<String> clipWKTMultiString(List<String> data, double latMin, double lonMin,
            double latMax, double lonMax);

    String clipWKTMultiLineString(String data, double latMin, double lonMin,
            double latMax, double lonMax);

    List<? extends Geo> clip(List<? extends Geo> geos, double latMin, double lonMin, double latMax, double lonMax);
    
    List<Point3D> clipPointsZM(List<String> geoms, double latMin, double lonMin, double latMax, double lonMax);

}
