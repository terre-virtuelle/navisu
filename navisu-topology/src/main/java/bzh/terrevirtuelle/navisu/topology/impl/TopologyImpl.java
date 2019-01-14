/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.topology.impl;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.topology.Topology;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import com.google.common.collect.Range;
import com.vividsolutions.jts.algorithm.Centroid;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateList;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author Serge Morvan
 * @date Dec 8, 2017
 */
public class TopologyImpl
        implements Topology, TopologyServices, ComponentState {

    String geometry = null;

    @Override
    public void componentStarted() {
        /* Nothing to do here */ }

    @Override
    public void componentStopped() {
        /* Nothing to do here */ }

    @Override
    public void componentInitiated() {
    }

    @Override
    public String wwjPositionsToPolygonWkt(List<? extends Position> positions) {
        geometry = "POLYGON((";
        int l = positions.size();
        for (int i = 0; i < l - 1; i++) {
            geometry += positions.get(i).getLongitude().getDegrees() + " " + positions.get(i).getLatitude().getDegrees() + ",";
        }
        geometry += positions.get(l - 1).getLongitude().getDegrees() + " " + positions.get(l - 1).getLatitude().getDegrees() + "))";
        return geometry;
    }

    @Override
    public String wwjLatLonsToPolygonWkt(List<LatLon> positions) {
        geometry = "POLYGON((";
        int l = positions.size();
        for (int i = 0; i < l - 1; i++) {
            geometry += positions.get(i).getLongitude().getDegrees() + " " + positions.get(i).getLatitude().getDegrees() + ",";
        }
        geometry += positions.get(l - 1).getLongitude().getDegrees() + " " + positions.get(l - 1).getLatitude().getDegrees() + "))";
        return geometry;
    }

    @Override
    public String wwjPositionsToLineWkt(List<? extends Position> positions) {
        if (positions != null && positions.size() > 2) {
            geometry = "LINESTRING(";
            int l = positions.size();
            for (int i = 0; i < l - 1; i++) {
                geometry += positions.get(i).getLongitude().getDegrees() + " " + positions.get(i).getLatitude().getDegrees() + ",";
            }
            geometry += positions.get(l - 1).getLongitude().getDegrees() + " " + positions.get(l - 1).getLatitude().getDegrees() + ")";
            return geometry;
        } else {
            return "";
        }
    }

    @Override
    public MultiPoint wwjPositionsToJtsMultiPoint(Set<Pair<Double, Double>> positions) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Pair<Double, Double> c : positions) {
            coordinates.add(new Coordinate(c.getX(), c.getY()));
        }
        Coordinate coordinates1[] = coordinates.toArray(new Coordinate[coordinates.size()]);
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createMultiPoint(coordinates.toArray(coordinates1));
    }

    @Override
    public String wwjSurfacePolylinesToWkt(List<SurfacePolylines> polylines) {
        if (polylines != null) {
            List<List<LatLon>> listListLatLon = new ArrayList<>();
            polylines.stream().map((s) -> s.getLocations()).map((ll) -> {
                List<LatLon> listLatLon = new ArrayList<>();
                for (LatLon lll : ll) {
                    listLatLon.add(lll);
                }
                return listLatLon;
            }).forEach((listLatLon) -> {
                listListLatLon.add(listLatLon);
            });
            geometry = "MULTILINESTRING(";
            int size0 = listListLatLon.size();
            for (int i = 0; i < size0 - 1; i++) {
                List<LatLon> l = listListLatLon.get(i);
                int size1 = l.size();
                geometry += "(";
                for (int j = 0; j < size1 - 1; j++) {
                    geometry += l.get(j).longitude.degrees + " " + l.get(j).latitude.degrees + ",";
                }
                geometry += l.get(size1 - 1).longitude.degrees + " " + l.get(size1 - 1).latitude.degrees;
                geometry += "),";
            }
            List<LatLon> l = listListLatLon.get(size0 - 1);
            int size1 = l.size();
            geometry += "(";
            for (int j = 0; j < size1 - 1; j++) {
                geometry += l.get(j).longitude.degrees + " " + l.get(j).latitude.degrees + ",";
            }
            geometry += l.get(size1 - 1).longitude.degrees + " " + l.get(size1 - 1).latitude.degrees;
            geometry += "))";
            return geometry;
        } else {
            return null;
        }
    }

    @Override
    public String wwjSurfacePolylinesToWktWithCoalescence(List<SurfacePolylines> polylines) {
        List<LatLon> tmp = new ArrayList<>();
        // Iterable<? extends LatLon> tmp;

        List<LatLon> listLatLon = new ArrayList<>();
        for (SurfacePolylines s : polylines) {
            tmp.clear();

            for (LatLon i : s.getLocations()) {
                tmp.add(i);
            }

            int size = tmp.size();
            if (tmp.get(0) != tmp.get(size - 1)) {
                for (LatLon ll : tmp) {
                    listLatLon.add(ll);
                }
            }
        }
        geometry = "LINESTRING(";
        int size = listLatLon.size();
        for (int j = 0; j < size - 1; j++) {
            geometry += listLatLon.get(j).longitude.degrees + " " + listLatLon.get(j).latitude.degrees + ",";
        }
        geometry += listLatLon.get(size - 1).longitude.degrees + " " + listLatLon.get(size - 1).latitude.degrees;
        geometry += ")";
        return geometry;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<Double, Double> wtkGetCentroid(String wkt) {
        WKTReader wktReader = new WKTReader();
        Geometry geometry = null;
        Pair<Double, Double> location = null;
        if (wkt != null) {
            try {
                geometry = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(TopologyImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Centroid centroid;
            if (geometry != null) {
                centroid = new Centroid(geometry);
                Coordinate coord = centroid.getCentroid();
                location = new Pair(coord.y, coord.x);
            }
        }
        return location;
    }

    @Override
    public Polygon wktPolygonToWwjPolygon(String geometry) {
        List<Position> positions;
        Polygon polygon = null;
        positions = wktPolygonToPositions(geometry);
        if (positions != null) {
            polygon = new Polygon(positions);
        }
        return polygon;
    }

    //MULTILINESTRING((-4.49067 48.37985,-4.49005 48.37938,-4.48888 48.3785))
    @Override
    public Polygon wktMultiLineToWwjPolygon(String geometry) {
        String tmp = geometry.replace("MULTILINESTRING((", "");
        tmp = tmp.replace("))", "");
        String[] posTab0 = tmp.split(",");
        if (posTab0.length > 2) {
            List<Position> positions = new ArrayList<>();
            for (String s : posTab0) {
                String[] posTab1 = s.split("\\s+");
                try {
                    positions.add(new Position(Angle.fromDegrees(Double.valueOf(posTab1[1])),
                            Angle.fromDegrees(Double.valueOf(posTab1[0])), 0));
                } catch (Exception e) {

                }
            }
            Polygon p = null;
            try {
                p = new Polygon(positions);
            } catch (Exception e) {

            }
            return p;
        } else {
            return null;
        }
    }

    @Override
    public Path wktMultiLineToWwjPath(String geometry, double height) {
        String tmp = geometry.replace("MULTILINESTRING((", "");
        tmp = tmp.replace("MULTILINESTRING ((", "");
        tmp = tmp.replace("))", "");
        tmp = tmp.replace(")", "");
        tmp = tmp.replace("(", "");
        //  System.out.println("tmp : " + tmp);
        List<Position> positions = new ArrayList<>();
        if (!tmp.contains("EMPTY")) {
            String[] posTab0 = tmp.split(",");
            for (String s : posTab0) {
                String[] posTab1 = s.trim().split("\\s+");
                if (posTab1.length != 0) {
                    try {
                        positions.add(new Position(Angle.fromDegrees(Double.valueOf(posTab1[1].trim())),
                                Angle.fromDegrees(Double.valueOf(posTab1[0].trim())), height));
                    } catch (NumberFormatException e) {
                        System.out.println("posTab1 : " + posTab1[0] + " " + posTab1[1]);

                    }
                }
            }
        }
        return new Path(positions);
    }

    @Override
    public List<Polygon> wktMultiLineToWwjMultiPolygon(String geometry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LatLon wktMultiPointToWwjLatLon(String geometry) {
        LatLon latLon = null;

        String tmp = geometry.replace("MULTIPOINT(", "");
        tmp = tmp.replace("POINT(", "");
        tmp = tmp.replace(")", "");
        if (!tmp.contains("EMPTY")) {
            String[] posTab0 = tmp.split("\\s+");
            if (posTab0.length != 0) {
                try {
                    latLon = new LatLon(Angle.fromDegrees(Double.valueOf(posTab0[1].trim())),
                            Angle.fromDegrees(Double.valueOf(posTab0[0].trim())));
                } catch (NumberFormatException e) {
                    System.out.println("posTab1 : " + posTab0[0] + " " + posTab0[1]);
                }
            }
        }
        return latLon;
    }

    @Override
    public List<Point3D> wktMultiPointZMToPoint3DList(String geometry) {
        List<Point3D> result = new ArrayList<>();
        if (geometry.contains("MULTIPOINT ZM")) {
            String tmp = geometry.replace("MULTIPOINT ZM (", "");
            tmp = tmp.replace(")", "");
            if (!tmp.contains("EMPTY")) {
                String[] posTab = tmp.split(",");
                if (posTab.length != 0) {
                    for (int i = 0; i < posTab.length; i++) {
                        try {
                            String[] posTab0 = posTab[i].split("\\s+");
                            result.add(new Point3D(Double.valueOf(posTab0[1].trim()), Double.valueOf(posTab0[0].trim()), Double.valueOf(posTab0[2].trim())));
                        } catch (NumberFormatException e) {

                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Point3D wktPointZMToPoint3D(String geometry) {
        Point3D result = null;
        if (geometry.contains("POINT ZM")) {
            String tmp = geometry.replace("POINT ZM (", "");
            tmp = tmp.replace(")", "");
            if (!tmp.contains("EMPTY")) {
                String[] posTab = tmp.split("\\s+");
                if (posTab.length != 0) {
                    try {
                        result = new Point3D(Double.valueOf(posTab[1].trim()), Double.valueOf(posTab[0].trim()), Double.valueOf(posTab[2].trim()));
                    } catch (NumberFormatException e) {

                    }
                }
            }
        }
        return result;
    }

    @Override
    public Path wktLineToWwjPath(String geometry, double height) {
        String tmp = geometry.replace("LINESTRING(", "");
        tmp = tmp.replace("LINESTRING (", "");
        tmp = tmp.replace(")", "");
        tmp = tmp.replace(")", "");
        tmp = tmp.replace("(", "");
        //  System.out.println("tmp : " + tmp);
        List<Position> positions = new ArrayList<>();
        if (!tmp.contains("EMPTY")) {
            String[] posTab0 = tmp.split(",");
            for (String s : posTab0) {
                String[] posTab1 = s.trim().split("\\s+");
                if (posTab1.length != 0) {
                    try {
                        positions.add(new Position(Angle.fromDegrees(Double.valueOf(posTab1[1].trim())),
                                Angle.fromDegrees(Double.valueOf(posTab1[0].trim())), height));
                    } catch (NumberFormatException e) {
                        System.out.println("posTab1 : " + posTab1[0] + " " + posTab1[1]);

                    }
                }
            }
        }
        return new Path(positions);
    }

    @Override
    public List<Polygon> wktMultiPolygonToWwjPolygons(String geometry) {
        List<Polygon> polygons = new ArrayList<>();
        String tmp = geometry.replace("MULTIPOLYGON((", "");
        tmp = tmp.replace("))", "");
        String[] polygonTab = tmp.split(Pattern.quote(")"));
        for (String s : polygonTab) {
            s = s.replace(")", "");
            s = s.replace(",(", "");
            s = s.replace("(", "");

            String[] posTab0 = s.split(",");
            List<Position> positions = new ArrayList<>();
            for (String ss : posTab0) {
                String[] posTab1 = ss.split("\\s+");
                positions.add(new Position(Angle.fromDegrees(Double.valueOf(posTab1[1])),
                        Angle.fromDegrees(Double.valueOf(posTab1[0])), 10.0));
            }
            polygons.add(new Polygon(positions));
        }
        return polygons;
    }

    @Override
    public Geometry wwjPolygonToJtsGeometry(Polygon polygon) {
        Iterable<? extends LatLon> latLon = polygon.getOuterBoundary();
        List<Position> positionList = new ArrayList<>();
        for (LatLon l : latLon) {
            positionList.add(new Position(l, 10.0));
        }
        Coordinate[] coordinates = new Coordinate[positionList.size()];
        for (int i = 0; i < positionList.size(); i++) {
            coordinates[i] = new Coordinate(positionList.get(i).getLongitude().getDegrees(),
                    positionList.get(i).getLatitude().getDegrees());
        }
        return new GeometryFactory().createLineString(coordinates);
    }

    @Override
    public List<Position> wktPolygonToPositions(String geometry) {
        //Pb if multi polygon
        List<Position> positions = null;
        String[] tab0;
        String[] tab1;
        String[] tab2;
        String tmp;
        if (geometry != null && geometry.toUpperCase().contains("POLYGON")) {
            tmp = geometry.replace("POLYGON((", "");
            tmp = tmp.replace("))", "");
            tab0 = tmp.split(",");
            int l = tab0.length;
            positions = new ArrayList<>();
            for (int i = 0; i < l; i++) {
                String[] latLon = tab0[i].trim().split(" ");
                String lat = latLon[1];
                String lon = latLon[0];
                positions.add(new Position(Angle.fromDegrees(Double.parseDouble(lat)),
                        Angle.fromDegrees(Double.parseDouble(lon)), 5));
            }
        }
        return positions;
    }

    @Override
    public Polygon wktPolygonToWwjPolygon(Geometry geometry) {
        Coordinate[] coordinates = geometry.getCoordinates();
        List<Position> positions = new ArrayList<>();
        for (Coordinate c : coordinates) {
            positions.add(new Position(Angle.fromDegrees(c.y), Angle.fromDegrees(c.x), 100.0));
        }
        Polygon polygon = new Polygon(positions);
        return polygon;
    }

    @Override
    public Polygon jtsPolygonToWwjPolygon(Geometry geometry, double height) {
        Coordinate[] coordinates = geometry.getCoordinates();
        List<Position> positions = new ArrayList<>();
        for (Coordinate c : coordinates) {
            positions.add(new Position(Angle.fromDegrees(c.y), Angle.fromDegrees(c.x), height));
        }
        Polygon polygon = new Polygon(positions);
        return polygon;
    }

    @Override
    public String wwjLocationsToWKT(Iterable<? extends LatLon> locations) {
        String[] tab;
        String result = "POLYGON((";
        List<String> locList = new ArrayList<>();
        for (LatLon l : locations) {
            tab = l.toString().split(",");
            if (tab.length == 3) {
                tab[0] = tab[0].replace("(", "");
                tab[0] = tab[0].replace("°", "");
                tab[1] = tab[1].replace("°", "");
                locList.add(tab[1].trim() + " " + tab[0].trim());
            }
        }
        for (int i = 0; i < locList.size() - 1; i++) {
            result += locList.get(i) + ",";
        }
        result += locList.get(locList.size() - 1) + "))";
        return result;
    }

    @Override
    public Geometry filterWwjLatLonsWithGeometryJts(Geometry geometry, List<LatLon> pts) {
        CoordinateList list = new CoordinateList(geometry.getCoordinates());
        list.closeRing();
        GeometryFactory geometryFactory = new GeometryFactory();
        LinearRing ring = geometryFactory.createLinearRing(list.toCoordinateArray());
        com.vividsolutions.jts.geom.Polygon polygonEnv = geometryFactory.createPolygon(ring, null);

        String wkt = wwjLatLonsToPolygonWkt(pts);
        WKTReader wktReader = new WKTReader();
        Geometry geometryFiltered = null;
        Geometry polygon = null;
        if (wkt != null) {
            try {
                polygon = wktReader.read(wkt);

            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(TopologyImpl.class
                        .getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            if (polygonEnv.contains(polygon)) {
                geometryFiltered = polygon;
            } else {
                try {
                    geometryFiltered = polygonEnv.intersection(polygon);
                } catch (Exception e) {

                }
            }
        } else {
            geometryFiltered = null;
        }
        return geometryFiltered;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> clipWKTMultiString(List<String> data, double latMin, double lonMin, double latMax, double lonMax) {
        Range rangeLat = Range.closed(latMin, latMax);
        Range rangeLon = Range.closed(lonMin, lonMax);

        List<String> multiString = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        for (String s : data) {
            tmp.clear();
            s = s.replace("MULTILINESTRING((", "");
            s = s.replace("))", "");
            String[] tab = s.split(",");
            for (String ss : tab) {
                String[] tab1 = ss.trim().split("\\s+");
                if (tab1.length != 0) {
                    if (rangeLat.contains(Double.valueOf(tab1[1]))
                            && rangeLon.contains(Double.valueOf(tab1[0]))) {
                        tmp.add(tab1[0] + " " + tab1[1]);
                    }
                }
            }
            if (!tmp.isEmpty()) {

                String sss = "MULTILINESTRING((";
                for (int i = 0; i < tmp.size() - 1; i++) {
                    sss += tmp.get(i) + ", ";
                }
                sss += tmp.get(tmp.size() - 1);
                sss += "))";
                multiString.add(sss);
            }
        }
        return multiString;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String clipWKTMultiLineString(String data, double latMin, double lonMin, double latMax, double lonMax) {
        Range rangeLat = Range.closed(latMin, latMax);
        Range rangeLon = Range.closed(lonMin, lonMax);

        List<String> tmp = new ArrayList<>();
        String s = data;
        s = s.replace("MULTILINESTRING((", "");
        s = s.replace("))", "");
        String[] tab = s.split(",");
        for (String ss : tab) {
            String[] tab1 = ss.trim().split("\\s+");
            if (tab1.length != 0) {
                if (rangeLat.contains(Double.valueOf(tab1[1]))
                        && rangeLon.contains(Double.valueOf(tab1[0]))) {
                    tmp.add(tab1[0] + " " + tab1[1]);
                }
            }
        }
        String result = "";
        if (!tmp.isEmpty()) {

            result = "MULTILINESTRING((";
            for (int i = 0; i < tmp.size() - 1; i++) {
                result += tmp.get(i) + ", ";
            }
            result += tmp.get(tmp.size() - 1);
            result += "))";
        }
        return result;
    }

    @Override
    public List<Polygon> wktPolygonsToWwjPolygons(List<Geometry> polygons) {

        List<Polygon> result = new ArrayList<>();
        List<Position> positions;
        for (Geometry g : polygons) {
            if (g.getArea() < 3.42E-7) {
                Coordinate[] coordinateTab = g.getCoordinates();
                positions = new ArrayList<>();
                for (int i = 0; i < coordinateTab.length; i++) {
                    positions.add(new Position(Angle.fromDegrees(coordinateTab[i].y),
                            Angle.fromDegrees(coordinateTab[i].x), coordinateTab[i].z + 100));
                }
                result.add(new Polygon(positions));
            }
        }
        return result;
    }

    @Override
    public List<Path> wktPolygonsToWwjPaths(List<Geometry> polygons) {

        List<Path> result = new ArrayList<>();
        List<Position> positions;
        for (Geometry g : polygons) {

            Coordinate[] coordinateTab = g.getCoordinates();
            positions = new ArrayList<>();
            for (int i = 0; i < coordinateTab.length; i++) {
                positions.add(new Position(Angle.fromDegrees(coordinateTab[i].y),
                        Angle.fromDegrees(coordinateTab[i].x), coordinateTab[i].z * 5));
            }
            result.add(new Path(positions));
        }
        return result;
    }

    @Override
    public List<Path> wktPolygonsToWwjPathsWithFilterOnArea(List<Geometry> polygons, double filter) {

        List<Path> result = new ArrayList<>();
        List<Position> positions;
        for (Geometry g : polygons) {
            if (g.getArea() < filter) {
                Coordinate[] coordinateTab = g.getCoordinates();
                positions = new ArrayList<>();
                for (int i = 0; i < coordinateTab.length; i++) {
                    positions.add(new Position(Angle.fromDegrees(coordinateTab[i].y),
                            Angle.fromDegrees(coordinateTab[i].x), coordinateTab[i].z));
                }
                result.add(new Path(positions));
            }
        }
        return result;
    }

    @Override
    public List<Path> wktPolygonsToWwjPathsWithFilterOnLength(List<Geometry> polygons, double filter) {

        List<Path> result = new ArrayList<>();
        List<Position> positions;
        for (Geometry g : polygons) {
            if (g.getLength() < filter) {
                Coordinate[] coordinateTab = g.getCoordinates();
                positions = new ArrayList<>();
                for (int i = 0; i < coordinateTab.length; i++) {
                    positions.add(new Position(Angle.fromDegrees(coordinateTab[i].y),
                            Angle.fromDegrees(coordinateTab[i].x), coordinateTab[i].z));
                }
                result.add(new Path(positions));
            }
        }
        return result;
    }

    @Override
    public List<? extends Geo> clip(List<? extends Geo> objects, double latMin, double lonMin, double latMax, double lonMax) {
        List<Geo> result = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coord = new Coordinate[5];
        coord[0] = new Coordinate(lonMin, latMin, 0);
        coord[1] = new Coordinate(lonMax, latMin, 0);
        coord[2] = new Coordinate(lonMax, latMax, 0);
        coord[3] = new Coordinate(lonMin, latMax, 0);
        coord[4] = new Coordinate(lonMin, latMin, 0);
        com.vividsolutions.jts.geom.Polygon clipper = geometryFactory.createPolygon(coord);
        objects.forEach((g) -> {
            geometry = g.getGeom();

            if (geometry.contains("MULTILINESTRING") && !geometry.contains("EMPTY")) {
                WKTReader wktReader = new WKTReader(geometryFactory);
                try {
                    Geometry geom = wktReader.read(geometry);
                    Geometry geomClipped = geom.intersection(clipper);
                    g.setGeom(geomClipped.toString());
                    result.add(g);
                } catch (ParseException ex) {
                    Logger.getLogger(TopologyImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
        });
        return result;
    }

    @Override
    public List<Point3D> clipPointsZM(List<String> geoms, double latMin, double lonMin, double latMax, double lonMax) {
        List<Point3D> result = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coord = new Coordinate[5];
        coord[0] = new Coordinate(lonMin, latMin, 0);
        coord[1] = new Coordinate(lonMax, latMin, 0);
        coord[2] = new Coordinate(lonMax, latMax, 0);
        coord[3] = new Coordinate(lonMin, latMax, 0);
        coord[4] = new Coordinate(lonMin, latMin, 0);
        com.vividsolutions.jts.geom.Polygon clipper = geometryFactory.createPolygon(coord);
        for (String g : geoms) {
            g = g.replace("POINT ZM (", "");
            g = g.replace(")", "");
            String[] tab = g.trim().split("\\s+");
            Point point = geometryFactory.createPoint(new Coordinate(Double.parseDouble(tab[0]), Double.parseDouble(tab[1])));
            if (clipper.contains(point)) {
                result.add(new Point3D(point.getY(), point.getX(), Double.parseDouble(tab[2])));
            }
        }
        return result;
    }
}
