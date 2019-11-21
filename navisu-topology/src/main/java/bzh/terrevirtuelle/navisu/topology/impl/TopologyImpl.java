/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.topology.impl;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.geometry.FaceGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.topology.Topology;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import com.google.common.collect.Range;
import com.vividsolutions.jts.algorithm.Centroid;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateList;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.operation.linemerge.LineSequencer;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public Path wktPolygonToWwjPath(String geometry) {
        List<Position> positions;
        Path path = null;
        positions = wktPolygonToPositions(geometry);
        System.out.println("0 : " + positions.get(0) + "end : " + positions.get(positions.size() - 1));
        path = new Path(positions);
        return path;
    }

    @Override
    public List<Path> wktLinestringToWwjPaths(Collection lines) {
        List<Path> result = new ArrayList<>();
        String lineString = lines.toString();
        lineString = lineString.replace("[", "");
        lineString = lineString.replace("]", "");
        lineString = lineString.replace("),", "");
        lineString = lineString.replace("\\(", "");
        lineString = lineString.trim();
        System.out.println("lineString : " + lineString);
        String[] tab = lineString.split("LINESTRING");
        System.out.println("tab : " + tab.length);
        for (String s : tab) {

            //s = s.trim();
            s = s.replace("(", "");
            s = s.trim();
            s = s.replace(")", "");
            //  System.out.println("s : " + s);

            String[] tmp = s.split(",");
            if (tmp.length > 1) {
                // System.out.println("tmp : " + tmp.length);
                String f = tmp[0].trim();
                String[] ff = f.split("\\s+");
                String l = tmp[1].trim();
                String[] ll = l.split("\\s+");

                result.add(new Path(
                        new Position(Angle.fromDegrees(Double.valueOf(ff[1].trim())),
                                Angle.fromDegrees(Double.valueOf(ff[0].trim())),
                                0.0),
                        new Position(Angle.fromDegrees(Double.valueOf(ll[1].trim())),
                                Angle.fromDegrees(Double.valueOf(ll[0].trim())),
                                0.0)));

            }
        }
        System.out.println("result : " + result.size());
        return result;
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
                } catch (NumberFormatException e) {

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
    public List<Point3DGeo> wktMultiPointZMToPoint3DList(String geometry) {
        List<Point3DGeo> result = new ArrayList<>();
        if (geometry.contains("MULTIPOINT ZM")) {
            String tmp = geometry.replace("MULTIPOINT ZM (", "");
            tmp = tmp.replace(")", "");
            if (!tmp.contains("EMPTY")) {
                String[] posTab = tmp.split(",");
                if (posTab.length != 0) {
                    for (int i = 0; i < posTab.length; i++) {
                        try {
                            String[] posTab0 = posTab[i].split("\\s+");
                            result.add(new Point3DGeo(Double.valueOf(posTab0[1].trim()), Double.valueOf(posTab0[0].trim()), Double.valueOf(posTab0[2].trim())));
                        } catch (NumberFormatException e) {

                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Point3DGeo> wktPolygonToPoint3DList(String geometry) {
        List<Point3DGeo> result = new ArrayList<>();
        if (geometry.contains("POLYGON")) {
            String tmp = geometry.replace("POLYGON((", "");
            tmp = tmp.replace("))", "");
            if (!tmp.contains("EMPTY")) {
                String[] posTab = tmp.split(",");
                if (posTab.length != 0) {
                    for (int i = 0; i < posTab.length; i++) {
                        try {
                            String[] posTab0 = posTab[i].split("\\s+");
                            result.add(new Point3DGeo(Double.valueOf(posTab0[1].trim()), Double.valueOf(posTab0[0].trim()), Double.valueOf(posTab0[2].trim())));
                        } catch (NumberFormatException e) {

                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Point3DGeo wktPointZMToPoint3D(String geometry) {
        Point3DGeo result = null;
        if (geometry.contains("POINT ZM")) {
            String tmp = geometry.replace("POINT ZM (", "");
            tmp = tmp.replace(")", "");
            if (!tmp.contains("EMPTY")) {
                String[] posTab = tmp.split("\\s+");
                if (posTab.length != 0) {
                    try {
                        result = new Point3DGeo(Double.valueOf(posTab[1].trim()), Double.valueOf(posTab[0].trim()), Double.valueOf(posTab[2].trim()));
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

    /*
    GEOMETRYCOLLECTION (
       POLYGON ((-4.50236274708401 48.37778085361472, -4.50236274708401 48.37778085361472)), 
       POLYGON ((-4.501922 48.377567, -4.501922 48.377567, -4.501636 48.37753))
    )
     */
    @Override
    public List<Path> wktGeometryCollectionToWwjPaths(Geometry polygons) {
        List<Path> result = new ArrayList<>();
        String geomCollection = polygons.toString();
        geomCollection = geomCollection.replace("GEOMETRYCOLLECTION (", "");
        geomCollection = geomCollection.replace(")))", "))");
        String[] tab = geomCollection.split(",");
        for (String s : tab) {
            result.add(wktPolygonToWwjPath(s));
        }
        return result;
    }

    @Override
    public Geometry wwjPolygonToJtsGeometry(Polygon polygon) {
        Iterable<? extends LatLon> latLon = polygon.getOuterBoundary();
        List<Position> positionList = new ArrayList<>();
        for (LatLon l : latLon) {
            positionList.add(new Position(l, 20.0));
        }
        Coordinate[] coordinates = new Coordinate[positionList.size()];
        for (int i = 0; i < positionList.size(); i++) {
            coordinates[i] = new Coordinate(positionList.get(i).getLongitude().getDegrees(),
                    positionList.get(i).getLatitude().getDegrees());
        }
        return new GeometryFactory().createPolygon(coordinates);
    }

    @Override
    public LineString wwjPositions2JtsGeometry(List<Position> positionList) {
        Coordinate[] coordinates = new Coordinate[positionList.size()];
        for (int i = 0; i < positionList.size(); i++) {
            coordinates[i] = new Coordinate(positionList.get(i).getLongitude().getDegrees(),
                    positionList.get(i).getLatitude().getDegrees());
        }
        return new GeometryFactory().createLineString(coordinates);
    }

    @Override
    public List<Geometry> within(Geometry geom, List<Geometry> geoms) {
        List<Geometry> result = new ArrayList<>();
        for (Geometry g : geoms) {
            if (g.within(geom)) {
                result.add(g);
            }
        }
        return result;
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
    public Position wktMultiPointToPosition(String geometry) {
        Position result = null;
        if (!geometry.contains("EMPTY")) {
        //MULTIPOINT(-4.478 48.37314)
            String tmp = geometry;
            tmp = tmp.replace("MULTIPOINT(", "");
            tmp = tmp.replace(")", "");
            String[] tab = tmp.trim().split(" ");
            double lat = Double.parseDouble(tab[1]);
            double lon = Double.parseDouble(tab[0]);
            result = new Position(Angle.fromDegrees(lat), Angle.fromDegrees(lon), 0.0);
        }
        return result;
    }

    @Override
    public Polygon wktPolygonToWwjPolygon(Geometry geometry, double height) {

        Coordinate[] coordinates = geometry.getCoordinates();
        List<Position> positions = new ArrayList<>();
        for (Coordinate c : coordinates) {
            positions.add(new Position(Angle.fromDegrees(c.y), Angle.fromDegrees(c.x), height));
        }
        Polygon polygon = new Polygon(positions);
        return polygon;
    }

    @Override
    public Polygon wktPolygonToWwjPolygon(Geometry geometry) {
        return wktPolygonToWwjPolygon(geometry, 0.0);
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
    public List<Path> jtsLineString2Path(List<Geometry> geoms) {
        LineString line;
        List<Geometry> filter = new ArrayList<>();
        for (int i = 0; i < geoms.size(); i++) {
            if (!((LineString) geoms.get(i)).isClosed() && geoms.get(i) != null) {
                filter.add(geoms.get(i));
            }
        }
        Geometry[] geomTab = new Geometry[filter.size()];
        for (int i = 0; i < filter.size(); i++) {
            geomTab[i] = filter.get(i);
        }
        GeometryFactory geometryFactory = new GeometryFactory();
        GeometryCollection lineStringCollection = geometryFactory.createGeometryCollection(geomTab);
        LineSequencer lineSequencer = new LineSequencer();
        lineSequencer.add(lineStringCollection);
        Geometry geom = lineSequencer.getSequencedLineStrings();

        List<Path> result = new ArrayList<>();
        if (geom != null) {
            List<Position> positions = new ArrayList<>();
            Coordinate[] coords = geom.getCoordinates();
            for (Coordinate c : coords) {
                positions.add(new Position(Angle.fromDegrees(c.y), Angle.fromDegrees(c.x), 0));
            }
            Path path = new Path(positions);
            result.add(path);
        }
        return result;
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

    /*
    POLYGON((-4.525918529669304 48.36554080812927,-
     */
    @Override
    public String toWKT(List<Point3DGeo> o) {
        String result = "POLYGONZ((";
        for (int i = 0; i < o.size() - 1; i++) {
            result += o.get(i).getLongitude() + " " + o.get(i).getLatitude() + " " + o.get(i).getElevation() + ", ";
        }
        result += o.get(o.size() - 1).getLongitude() + " " + o.get(o.size() - 1).getLatitude() + " " + o.get(o.size() - 1).getElevation() + "))";
        return result;
    }

    /*
    POINTZ(13.21 47.21 0.21)
     */
    @Override
    public String toWKT(Point3DGeo o) {
        String result = null;
        if (o != null) {
            result = "POINTZ(" + o.getLongitude() + " " + o.getLatitude() + " " + o.getElevation() + ")";
        }
        return result;
    }

    @Override
    public String toWKT2D(Point3DGeo o) {
        String result = null;
        if (o != null) {
            result = "POINT(" + o.getLongitude() + " " + o.getLatitude() + ")";
        }
        return result;
    }

    /*
    POINT(15.21 57.58 0.31)
     */
    @Override
    public Point3DGeo getPoint3DGeoFromWKT(String o) {
        String tmp = o;
        Point3DGeo result;
        tmp = tmp.replace("POINT(", "");
        tmp = tmp.replace(")", "");
        String[] coord = tmp.split("\\s+");

        double lon = Double.parseDouble(coord[0]);
        double lat = Double.parseDouble(coord[1]);
        double elv = 0.0;
        if (coord.length == 3) {
            elv = Double.parseDouble(coord[2]);
        }
        return new Point3DGeo(lat, lon, elv);
    }

    /*
    MULTIPOINTZ(15.21 57.58 0.31, 15.81 57.12 0.33)
     */
    @Override
    public String toWKT(FaceGeo o) {
        String result = "MULTIPOINTZ(";
        List<Point3DGeo> vertices = o.getVertices();
        int size = vertices.size();
        for (int i = 0; i < size - 1; i++) {
            result += vertices.get(i).getLongitude() + " " + vertices.get(i).getLatitude() + " " + vertices.get(i).getElevation() + ", ";
        }
        result += vertices.get(size - 1).getLongitude() + " " + vertices.get(size - 1).getLatitude() + " " + vertices.get(size - 1).getElevation() + ")";
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toWKT(SolidGeo o) {

        String faces = "GEOMETRYCOLLECTIONZ(";
        List<FaceGeo> faceList = new ArrayList<>(o.getFaces());
        for (int i = 0; i < faceList.size() - 1; i++) {
            String multipoints = "MULTIPOINTZ(";
            List<Point3DGeo> vertices = faceList.get(i).getVertices();
            for (int j = 0; j < vertices.size() - 1; j++) {
                multipoints += vertices.get(j).getLongitude() + " " + vertices.get(j).getLatitude() + " " + vertices.get(j).getElevation() + ",";
            }
            multipoints += vertices.get(vertices.size() - 1).getLongitude() + " " + vertices.get(vertices.size() - 1).getLatitude() + " " + vertices.get(vertices.size() - 1).getElevation() + "), ";
            faces += multipoints;
        }
        faces += "MULTIPOINTZ(";
        List<Point3DGeo> vertices = faceList.get(faceList.size() - 1).getVertices();
        for (int j = 0; j < vertices.size() - 1; j++) {
            faces += vertices.get(j).getLongitude() + " " + vertices.get(j).getLatitude() + " " + vertices.get(j).getElevation() + ", ";
        }
        faces += vertices.get(vertices.size() - 1).getLongitude() + " " + vertices.get(vertices.size() - 1).getLatitude() + " " + vertices.get(vertices.size() - 1).getElevation() + ")";
        faces += ")";
        return faces;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String facesToWKT(List<FaceGeo> faceList) {
        String faces = "GEOMETRYCOLLECTIONZ(";

        for (int i = 0; i < faceList.size() - 1; i++) {
            String multipoints = "MULTIPOINTZ(";
            List<Point3DGeo> vertices = faceList.get(i).getVertices();
            for (int j = 0; j < vertices.size() - 1; j++) {
                multipoints += vertices.get(j).getLongitude() + " " + vertices.get(j).getLatitude() + " " + vertices.get(j).getElevation() + ",";
            }
            multipoints += vertices.get(vertices.size() - 1).getLongitude() + " " + vertices.get(vertices.size() - 1).getLatitude() + " " + vertices.get(vertices.size() - 1).getElevation() + "), ";
            faces += multipoints;
        }
        faces += "MULTIPOINTZ(";
        List<Point3DGeo> vertices = faceList.get(faceList.size() - 1).getVertices();
        for (int j = 0; j < vertices.size() - 1; j++) {
            faces += vertices.get(j).getLongitude() + " " + vertices.get(j).getLatitude() + " " + vertices.get(j).getElevation() + ", ";
        }
        faces += vertices.get(vertices.size() - 1).getLongitude() + " " + vertices.get(vertices.size() - 1).getLatitude() + " " + vertices.get(vertices.size() - 1).getElevation() + ")";
        faces += ")";
        return faces;
    }

    @Override
    public boolean overlaps(List<Point3DGeo> a, List<Point3DGeo> b) {
        String aWKT = toWKT(a);
        String bWKT = toWKT(b);
        Geometry aGeom = wktPolygonZFromString(aWKT);
        Geometry bGeom = wktPolygonZFromString(bWKT);
        return aGeom.overlaps(bGeom);
    }

    @Override
    public boolean within(Point3DGeo a, Geometry b) {
        Geometry aGeom = wktFromPoint(a);
        return aGeom.within(b);
    }

    @Override
    public Geometry wktPolygonZFromString(String geometry) {
        String tmp = geometry;
        tmp = tmp.replace("POLYGONZ((", "");
        tmp = tmp.replace("))", "");
        String[] tab = tmp.split(",");
        List<Coordinate> coordinates = new ArrayList<>();
        for (String p : tab) {
            String[] coord = p.trim().split("\\s+");
            double x = Double.parseDouble(coord[0]);
            double y = Double.parseDouble(coord[1]);
            coordinates.add(new Coordinate(x, y));
        }
        Coordinate[] coordTab = new Coordinate[coordinates.size()];
        coordTab = coordinates.toArray(coordTab);
        GeometryFactory geometryFactory = new GeometryFactory();
        com.vividsolutions.jts.geom.Polygon polygon = geometryFactory.createPolygon(coordTab);
        return polygon;
    }

    /*
    POINT(15.21 57.58 0.31)
     */
    @Override
    public Geometry wktFromPoint(Point3DGeo point) {
        Coordinate coord = new Coordinate(point.getLongitude(), point.getLatitude(), 0);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point pt = geometryFactory.createPoint(coord);
        return pt;
    }

    /*
    POLYGON ((-4.52951275353941 48.36533020505645, -4.529559569163751 48.36526236948451,-4.529549212092845 48.365242650170885, -4.52951275353941 48.36533020505645))
     */
    @Override
    public Geometry wktPolygonFromString(String geometry) {
        // System.out.println("geometry : " + geometry);
        String tmp = geometry;
        tmp = tmp.replace("POLYGON((", "");
        tmp = tmp.replace("))", "");
        String[] tab = tmp.split(",");
        List<Coordinate> coordinates = new ArrayList<>();
        for (String p : tab) {
            String[] coord = p.split("\\s+");
            double x = Double.parseDouble(coord[0]);
            double y = Double.parseDouble(coord[1]);
            coordinates.add(new Coordinate(x, y));
        }
        Coordinate[] coordTab = new Coordinate[coordinates.size()];
        coordTab = coordinates.toArray(coordTab);
        GeometryFactory geometryFactory = new GeometryFactory();
        com.vividsolutions.jts.geom.Polygon polygon = geometryFactory.createPolygon(coordTab);
        return polygon;
    }

    /*
    MULTIPOINTZ(15.21 57.58 0.31, 15.81 57.12 0.33)
     */
    @Override
    public FaceGeo getFaceGeofromMulitipointWKT(String o) {
        List<Point3DGeo> pts = new ArrayList<>();
        String tmp = o;
        tmp = tmp.replace("MULTIPOINT(", "");
        tmp = tmp.replace(")", "");
        String[] tab = tmp.split(",");
        for (String p : tab) {
            String[] coord = p.split("\\s+");
            double lon = Double.parseDouble(coord[0]);
            double lat = Double.parseDouble(coord[1]);
            double elv = Double.parseDouble(coord[2]);
            pts.add(new Point3DGeo(lat, lon, elv));
        }
        FaceGeo f = new FaceGeo(pts);
        return f;
    }

    /*
    GEOMETRYCOLLECTIONZ(
         MULTIPOINTZ(15.21 57.58 0.31, 15.81 57.12 0.33),
         MULTIPOINTZ(15.21 57.58 0.31, 15.81 57.12 0.33)
    ) 
     */
    @SuppressWarnings("unchecked")
    @Override
    public SolidGeo getSolidGeofromWKT(String o) {
        //  System.out.println("o : "+ o);
        List<FaceGeo> faces = new ArrayList<>();
        String tmp = o;
        tmp = tmp.replace("GEOMETRYCOLLECTION(", "");
        tmp = tmp.replace("))", ")");
        tmp = tmp.replace("),", ")),");
        String[] tab = tmp.split("\\),");
        for (String f : tab) {
            faces.add(getFaceGeofromMulitipointWKT(f));
        }
        return new SolidGeo(faces);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FaceGeo> getFaceGeofromGeometryCollectionMulitipointWKT(String o) {
        List<FaceGeo> faces = new ArrayList<>();
        String tmp = o;
        tmp = tmp.replace("GEOMETRYCOLLECTION(", "");
        tmp = tmp.replace("))", ")");
        tmp = tmp.replace("),", ")),");
        String[] tab = tmp.split("\\),");
        for (String f : tab) {
            faces.add(getFaceGeofromMulitipointWKT(f));
        }
        return faces;
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
                        Angle.fromDegrees(coordinateTab[i].x), coordinateTab[i].z));//z*5
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
    public List<Point3DGeo> clipPointsZM(List<String> geoms, double latMin, double lonMin, double latMax, double lonMax) {
        List<Point3DGeo> result = new ArrayList<>();
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
                result.add(new Point3DGeo(point.getY(), point.getX(), Double.parseDouble(tab[2])));
            }
        }
        return result;
    }
}
