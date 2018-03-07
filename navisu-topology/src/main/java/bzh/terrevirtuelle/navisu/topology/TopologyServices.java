/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.topology;

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

    String wwjPositionsToPolygonWkt(List<? extends Position> positions);

    String wwjLatLonsToPolygonWkt(List<LatLon> positions);

    String wwjPositionsToLineWkt(List<? extends Position> positions);

    MultiPoint wwjPositionsToJtsMultiPoint(Set<Pair<Double, Double>> positions);

    String wwjSurfacePolylinesToWkt(List<SurfacePolylines> polylines);

    String wwjSurfacePolylinesToWktWithCoalescence(List<SurfacePolylines> polylines);

    Pair<Double, Double> wtkGetCentroid(String wkt);

    Polygon wktPolygonToWwjPolygon(String geometry);

    Polygon wktMultiPolygonToWwjPolygon(String geometry);

    Polygon wktPolygonToWwjPolygon(Geometry geometry);

    Geometry wwjPolygonToJtsGeometry(Polygon polygon);

    List<Position> wktPolygonToPositions(String geometry);

    String wwjLocationsToWKT(Iterable<? extends LatLon> locations);

    Polygon wktMultiLineToWwjPolygon(String geometry);

    Path wktMultiLineToWwjPath(String geometry, double height);

    Geometry filterWwjLatLonsWithGeometryJts(Geometry geometry, List<LatLon> pts);
}
