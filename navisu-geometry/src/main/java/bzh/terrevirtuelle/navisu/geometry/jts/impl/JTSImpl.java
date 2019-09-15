/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.jts.impl;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTS;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.topology.impl.TopologyImpl;
import com.vividsolutions.jts.algorithm.ConvexHull;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.operation.buffer.BufferOp;
import com.vividsolutions.jts.triangulate.DelaunayTriangulationBuilder;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.opensphere.geometry.algorithm.ConcaveHull;
import org.poly2tri.Poly2Tri;
import org.poly2tri.geometry.polygon.PolygonPoint;
import org.poly2tri.triangulation.TriangulationPoint;
import org.poly2tri.triangulation.delaunay.DelaunayTriangle;

/**
 *
 * @author serge
 */
public class JTSImpl
        implements JTS, JTSServices, ComponentState {

    @UsedService
    TopologyServices topologyServices;
    @UsedService
    GeodesyServices geodesyServices;
    boolean[][] isInit;

    @Override
    public List<Coordinate> toListCoordinates(List<Point3DGeo> pts) {
        List<Coordinate> coordJTSs = new ArrayList<>();
        pts.stream().forEach((p) -> {
            coordJTSs.add(new Coordinate(p.getLongitude(), p.getLatitude(), p.getElevation()));
        });
        return coordJTSs;
    }

    @Override
    public Coordinate[] toTabCoordinates(List<Point3DGeo> pts) {
        Coordinate[] tmp = new Coordinate[pts.size()];
        for (int i = 0; i < tmp.length; i++) {
            Point3DGeo pt = pts.get(i);
            tmp[i] = new Coordinate(pt.getLongitude(), pt.getLatitude(), pt.getElevation());
        }
        return tmp;
    }

    @Override
    public Geometry getConcaveHull(List<Point3DGeo> points, double threshold) {
        List<Coordinate> coordinatesJTS = toListCoordinates(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        MultiPoint geom = new GeometryFactory().createMultiPoint(coord);
        ConcaveHull ch = new ConcaveHull(geom, threshold);
        Geometry concaveHullTmp = ch.getConcaveHull();

        return concaveHullTmp;
    }

    @Override
    public List<Point3DGeo> toListPoint3D(Coordinate[] coord) {
        List<Point3DGeo> result = new ArrayList<>();
        for (Coordinate c : coord) {
            result.add(new Point3DGeo(c.y, c.x, c.z));
        }
        return result;
    }

    @Override
    public Geometry getConvexHull(Coordinate[] pts, GeometryFactory geomFactory) {
        return new ConvexHull(pts, geomFactory).getConvexHull();
    }

    @Override
    public Geometry getEnveloppe(Coordinate[] pts, GeometryFactory geometryFactory) {
        LinearRing ring = geometryFactory.createLinearRing(pts);
        return ring.getEnvelope();
    }

    @Override
    public Point3DGeo toPoint3D(Point point) {
        Coordinate[] c = point.getCoordinates();
        if (c.length != 0) {
            return new Point3DGeo(c[0].y, c[0].x, c[0].z);
        }
        return null;
    }

    @Override
    public Geometry getLineString(List<Point3DGeo> points) {
        List<Coordinate> coordinatesJTS = toListCoordinates(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        // new GeometryFactory().createPolygon(coord);
        LineString geom = new GeometryFactory().createLineString(coord);
        return geom;
    }

    @Override
    public Geometry getPolygon(List<Point3DGeo> points) {
        List<Coordinate> coordinatesJTS = toListCoordinates(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        Geometry geom = new GeometryFactory().createPolygon(coord);
        return geom;
    }

    @Override
    public Geometry getBoundaries(Polygon polygon) {
        List<LatLon> latLon = new ArrayList<>();
        Iterable<? extends LatLon> bounds = polygon.getOuterBoundary();
        for (LatLon l : bounds) {
            latLon.add(l);
        }
        return null;
    }

    @Override
    public Path getPathFromPolygon(Geometry geometry) {
        Coordinate[] coords = geometry.getCoordinates();
        List<Position> positions = new ArrayList<>();
        for (Coordinate c : coords) {
            positions.add(new Position(Angle.fromDegrees(c.y), Angle.fromDegrees(c.x), 0.0));
        }
        return new Path(positions);
    }

    @Override
    public Polygon getPolygonFromPolygon(Geometry geometry) {
        Coordinate[] coords = geometry.getCoordinates();
        List<Position> positions = new ArrayList<>();
        for (Coordinate c : coords) {
            positions.add(new Position(Angle.fromDegrees(c.y), Angle.fromDegrees(c.x), 100.0));
        }
        return new Polygon(positions);
    }

    @Override
    public Geometry getPolygonFromPath(Path path) {
        List<Point3DGeo> points = new ArrayList<>();
        Iterable< ? extends Position> pos = path.getPositions();
        for (Position po : pos) {
            points.add(new Point3DGeo(po.getLatitude().getDegrees(), po.getLongitude().getDegrees(), po.getAltitude()));
        }
        return getPolygon(points);
    }

    @Override
    public boolean contains(Geometry geom, Point3DGeo pt3D) {
        boolean result;
        Coordinate coord = new Coordinate(pt3D.getLongitude(), pt3D.getLatitude(), 0);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point pt = geometryFactory.createPoint(coord);
        result = geom.contains(pt);
        return result;
    }

    @Override
    public List<Path> pathsInGeometry(Geometry geometry, List<Path> faces) {
        List<Path> result = new ArrayList<>();
        Geometry geom;
        for (Path p : faces) {
            geom = getPolygonFromPath(p);
            try {
                if (geometry.contains(geom)) {
                    result.add(p);

                }
            } catch (Exception e) {

            }
            /*else {
                Geometry intersect = geometry.intersection(geom);
                if (!intersect.isEmpty()) {
                    //  result.add(getPathFromPolygon(intersect));
                    System.out.println("intersect : " + intersect);
                }
            }
             */

        }
        return result;
    }

    @Override
    public List<Point3DGeo> merge(List<Point3DGeo> pts0, List<Point3DGeo> pts1) {
        List<Point3DGeo> tmp = new ArrayList<>();
        Coordinate[] tab0 = toTabCoordinates(pts0);
        Coordinate[] tab1 = toTabCoordinates(pts1);
        GeometryFactory geomFactory = new GeometryFactory();
        MultiPoint multiPoint0 = geomFactory.createMultiPoint(tab0);
        multiPoint0.setSRID(4326);
        MultiPoint multiPoint1 = geomFactory.createMultiPoint(tab1);
        multiPoint1.setSRID(4326);
        Geometry geom = multiPoint0.union(multiPoint1);
        Coordinate[] tab3 = geom.getCoordinates();
        Set<Point3DGeo> set = new HashSet<>();
        for (Coordinate c : tab3) {
            set.add(new Point3DGeo(c.y, c.x, c.z));
        }
        tmp.addAll(set);
        return tmp;
    }

    @Override
    public boolean[][] getPoint3DInit() {
        return isInit;
    }

    @Override
    public Point3DGeo[][] mergePointsToGrid(List<Point3DGeo> points, Point3DGeo[][] grid) {
        int line = grid.length;
        int col = grid[0].length;
        GeometryFactory geometryFactory = new GeometryFactory();
        isInit = new boolean[line][col];
        /*
        New grid
        gridCoord with JTS Point
         */
        Point3DGeo[][] result = new Point3DGeo[line][col];
        Point[][] gridCoord = new Point[line][col];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = new Point3DGeo(grid[i][j].getLatitude(), grid[i][j].getLongitude(), grid[i][j].getElevation());
                gridCoord[i][j] = geometryFactory.createPoint(new Coordinate(grid[i][j].getLongitude(), grid[i][j].getLatitude(), grid[i][j].getElevation()));
            }
        }

        List<Geometry> triangles = createDelaunayWithFilterOnArea(points, 1E-6);

        double dA;
        double dB;
        double dC;
        double min;

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                isInit[i][j] = false;
            }
        }
        for (Geometry t : triangles) {
            for (int i = 0; i < line; i++) {
                for (int j = 0; j < col; j++) {
                    if (t.contains(gridCoord[i][j])) {
                        Coordinate[] coordinates = t.getCoordinates();
                        isInit[i][j] = true;
                        dA = geodesyServices.getDistanceM(coordinates[0].y, coordinates[0].x,
                                gridCoord[i][j].getCoordinate().y, gridCoord[i][j].getCoordinate().x);
                        dB = geodesyServices.getDistanceM(coordinates[1].y, coordinates[1].x,
                                gridCoord[i][j].getCoordinate().y, gridCoord[i][j].getCoordinate().x);
                        dC = geodesyServices.getDistanceM(coordinates[2].y, coordinates[2].x,
                                gridCoord[i][j].getCoordinate().y, gridCoord[i][j].getCoordinate().x);

                        min = Double.MAX_VALUE;
                        if (min > dA) {
                            min = dA;
                            result[i][j].setElevation(coordinates[0].z);
                        }
                        if (min > dB) {
                            min = dB;
                            result[i][j].setElevation(coordinates[1].z);
                        }
                        if (min > dC) {
                            result[i][j].setElevation(coordinates[2].z);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < line; i++) {
            result[i][0] = result[i][1];
            result[i][col - 1] = result[i][col - 2];
        }
        for (int i = 0; i < col; i++) {
            result[0][i] = result[1][i];
            result[line - 1][i] = result[line - 2][i];
        }
        return result;
    }

    /*
    public void testTriangulationOfPolygonWithShortEdge()
{
    final Coordinate c0 = new Coordinate(46.51, -188.20);
    final Coordinate c1 = new Coordinate(4.66, -141.67);
    final Coordinate c2 = new Coordinate(4.66 + 1E-8, -141.67 + 1E-8);
    final Coordinate c3 = new Coordinate(-6.48, -129.26);
    final Coordinate c4 = new Coordinate(0, -200);

    final ArrayList<Segment> segments = new ArrayList<>();
    segments.add(new Segment(c0, c1));
    segments.add(new Segment(c1, c2));
    segments.add(new Segment(c2, c3));
    segments.add(new Segment(c3, c4));
    segments.add(new Segment(c4, c0));

    final ArrayList vertices = new ArrayList<>();
    for (final Segment segment : segments)
    {
        vertices.add(new ConstraintVertex(segment.getStart()));
    }

    final ConformingDelaunayTriangulator cdt = new ConformingDelaunayTriangulator(new ArrayList<>(), 1E-5);
    cdt.setConstraints(segments, vertices);
    cdt.formInitialDelaunay();
    cdt.enforceConstraints();
    final QuadEdgeSubdivision subdivision = cdt.getSubdivision();

    final Collection subdivisionEdges = subdivision.getEdges();

    Assert.assertEquals(subdivisionEdges.size(), 3);
}
     */
    @Override
    public List<Path> createDelaunayToPath(List<Point3DGeo> pts) {

        double elevation = pts.get(0).getElevation();
        List<Path> result = new ArrayList<>();
        List<PolygonPoint> polygonPoints = new ArrayList<>();

        int i = 0;
        for (Point3DGeo p : pts) {
            polygonPoints.add(new PolygonPoint(p.getLatitude(), p.getLongitude(), i++));
        }
        org.poly2tri.geometry.polygon.Polygon polygon = new org.poly2tri.geometry.polygon.Polygon(polygonPoints);
        Poly2Tri.triangulate(polygon);
        // Gather triangles
        List<DelaunayTriangle> triangles1 = polygon.getTriangles();
        Iterator<DelaunayTriangle> it = triangles1.iterator();
        while (it.hasNext()) {
            DelaunayTriangle t = it.next();
            TriangulationPoint[] tpTab = t.points;
            List<Position> positions = new ArrayList<>();
            for (int j = 0; j < tpTab.length; j++) {
                positions.add(new Position(Angle.fromDegrees(tpTab[j].getX()), Angle.fromDegrees(tpTab[j].getY()), elevation));
            }
            positions.add(new Position(Angle.fromDegrees(tpTab[0].getX()), Angle.fromDegrees(tpTab[0].getY()), elevation));
            result.add(new Path(positions));
        }
        return result;
    }

    @Override
    public List<Geometry> createDelaunay(List<Point3DGeo> pts) {
        Coordinate[] coordinateTab = toTabCoordinates(pts);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        return triangles;
    }

    @Override
    public List<Path> createDelaunayToPath(Point3DGeo[][] pts) {
        List<Point3DGeo> data = new ArrayList<>();
        for (int i = 0; i < pts[0].length; i++) {
            for (int j = 0; j < pts[1].length; j++) {
                data.add(pts[i][j]);
            }
        }
        return createDelaunayToPath(data);
    }

    @Override
    public List<Path> createDelaunayToPath(List<Point3DGeo> pts, double maxElevation) {
        List<Point3DGeo> data = new ArrayList<>();
        double height;
        for (Point3DGeo p : pts) {
            if (maxElevation != 0) {
                data.add(new Point3DGeo(p.getLatitude(), p.getLongitude(), maxElevation - p.getElevation()));
            } else {
                data.add(new Point3DGeo(p.getLatitude(), p.getLongitude(), p.getElevation()));
            }
        }
        Coordinate[] coordinateTab = toTabCoordinates(data);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        List<Path> paths = topologyServices.wktPolygonsToWwjPaths(triangles);
        return paths;
    }

    @Override
    public List<Path> createDelaunayToPath(List<Point3DGeo> pts, double maxElevation, boolean depth) {
        List<Point3DGeo> data = new ArrayList<>();
        int height = 1;
        if (depth == false) {
            height = -1;
        }
        for (Point3DGeo p : pts) {
            if (maxElevation != 0) {
                data.add(new Point3DGeo(p.getLatitude(), p.getLongitude(), maxElevation - (height * p.getElevation())));
            } else {
                data.add(new Point3DGeo(p.getLatitude(), p.getLongitude(), height * p.getElevation()));
            }
        }
        Coordinate[] coordinateTab = toTabCoordinates(data);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        List<Path> paths = topologyServices.wktPolygonsToWwjPaths(triangles);
        return paths;
    }

    @Override
    public List<Path> createDelaunayToPath(Point3DGeo[][] pts, double maxElevation) {
        List<Point3DGeo> data = new ArrayList<>();
        for (int i = 0; i < pts.length; i++) {
            for (int j = 0; j < pts[0].length; j++) {
                data.add(pts[i][j]);
            }
        }
        return createDelaunayToPath(data, maxElevation);
    }

    @Override
    public List<Path> createDelaunayToPath(Point3DGeo[][] pts, double maxElevation, boolean depth) {
        List<Point3DGeo> data = new ArrayList<>();
        for (int i = 0; i < pts.length; i++) {
            for (int j = 0; j < pts[0].length; j++) {
                data.add(pts[i][j]);
            }
        }
        return createDelaunayToPath(data, maxElevation, depth);
    }

    @Override
    public List<Path> createDelaunayWithFilterOnAreaToPath(List<Point3DGeo> pts, double filter) {
        Coordinate[] coordinateTab = toTabCoordinates(pts);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        List<Path> paths = topologyServices.wktPolygonsToWwjPathsWithFilterOnArea(triangles, filter);
        return paths;
    }

    @Override
    public List<Geometry> createDelaunayWithFilterOnArea(List<Point3DGeo> pts, double filter) {
        Coordinate[] coordinateTab = toTabCoordinates(pts);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        ArrayList<Geometry> result = new ArrayList<>();
        for (Geometry g : triangles) {
            if (g.getArea() < filter) {
                result.add(g);
            }
        }
        return result;
    }

    public List<Geometry> createDelaunay(Point3DGeo[][] pts) {
        List<Point3DGeo> data = new ArrayList<>();
        for (int i = 0; i < pts[0].length; i++) {
            for (int j = 0; j < pts[1].length; j++) {
                data.add(pts[i][j]);
            }
        }
        Coordinate[] coordinateTab = toTabCoordinates(data);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        ArrayList<Geometry> result = new ArrayList<>();
        triangles.forEach((g) -> {
            result.add(g);
        });
        return result;
    }

    @Override
    public List<Path> createDelaunayWithFilterOnLengthToPath(List<Point3DGeo> pts, double filter) {
        Coordinate[] coordinateTab = toTabCoordinates(pts);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        List<Path> paths = topologyServices.wktPolygonsToWwjPathsWithFilterOnLength(triangles, filter);
        return paths;
    }

    @Override
    public List<Path> createDelaunayWithFilterToPath(List<Point3DGeo> pts, double filter, double maxElevation) {
        List<Point3DGeo> bathy = new ArrayList<>();
        pts.forEach((p) -> {
            bathy.add(new Point3DGeo(p.getLatitude(), p.getLongitude(), maxElevation - p.getElevation()));
        });
        Coordinate[] coordinateTab = toTabCoordinates(bathy);
        MultiPoint points = new GeometryFactory().createMultiPoint(coordinateTab);
        ArrayList<Geometry> triangles = new ArrayList<>();
        DelaunayTriangulationBuilder triator = new DelaunayTriangulationBuilder();
        triator.setSites(points);
        Geometry tris = triator.getTriangles(new GeometryFactory());
        for (int i = 0; i < tris.getNumGeometries(); i++) {
            triangles.add(tris.getGeometryN(i));
        }
        List<Path> paths = topologyServices.wktPolygonsToWwjPathsWithFilterOnArea(triangles, filter);
        return paths;
    }

    @Override
    public List<Point3DGeo> getBuffer(Geometry geom, double bufferDistance, int capSize) {
        List<Point3DGeo> result = new ArrayList<>();
        BufferOp bufferOp = new BufferOp(geom);
        bufferOp.setEndCapStyle(capSize);//CAP_ROUND);
        Geometry offsetBuffer = bufferOp.getResultGeometry(bufferDistance);
        List<Position> offsetPathPositions = new ArrayList<>();
        for (Coordinate c : offsetBuffer.getCoordinates()) {
            offsetPathPositions.add(Position.fromDegrees(c.y, c.x, 100));
        }
        Polygon poly = new Polygon(offsetPathPositions);
        poly.outerBoundary().forEach((p) -> {
            result.add(new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation()));
        });
        return result;
    }

    @Override
    public List<Point3DGeo> getBuffer(String wkt, double bufferDistance, int capSize) {
        List<Point3DGeo> result = new ArrayList<>();
        WKTReader wktReader = new WKTReader();
        Geometry geometry = null;
        Pair<Double, Double> location = null;
        if (wkt != null) {
            try {
                geometry = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(TopologyImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        BufferOp bufferOp = new BufferOp(geometry);
        bufferOp.setEndCapStyle(capSize);//CAP_ROUND);
        Geometry offsetBuffer = bufferOp.getResultGeometry(bufferDistance);
        List<Position> offsetPathPositions = new ArrayList<>();
        for (Coordinate c : offsetBuffer.getCoordinates()) {
            offsetPathPositions.add(Position.fromDegrees(c.y, c.x, 100));
        }
        Polygon poly = new Polygon(offsetPathPositions);
        poly.outerBoundary().forEach((p) -> {
            result.add(new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation()));
        });
        return result;
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
}
