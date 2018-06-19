/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.jts.impl;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.jts.JTS;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.triangulate.DelaunayTriangulationBuilder;
import gov.nasa.worldwind.render.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.opensphere.geometry.algorithm.ConcaveHull;

/**
 *
 * @author serge
 */
public class JTSImpl
        implements JTS, JTSServices, ComponentState {

    @UsedService
    TopologyServices topologyServices;

    @Override
    public List<Coordinate> toListCoordinates(List<Point3D> pts) {
        List<Coordinate> coordJTSs = new ArrayList<>();
        pts.stream().forEach((p) -> {
            coordJTSs.add(new Coordinate(p.getLongitude(), p.getLatitude(), p.getElevation()));
        });
        return coordJTSs;
    }

    @Override
    public Coordinate[] toTabCoordinates(List<Point3D> pts) {
        Coordinate[] tmp = new Coordinate[pts.size()];
        for (int i = 0; i < tmp.length; i++) {
            Point3D pt = pts.get(i);
            tmp[i] = new Coordinate(pt.getLongitude(), pt.getLatitude(), pt.getElevation());
        }
        return tmp;
    }

    @Override
    public Geometry getConcaveHull(List<Point3D> points, double threshold) {
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
    public Geometry getLineString(List<Point3D> points) {
        List<Coordinate> coordinatesJTS = toListCoordinates(points);
        Coordinate[] coord = new Coordinate[coordinatesJTS.size()];
        for (int c = 0; c < coord.length; c++) {
            coord[c] = coordinatesJTS.get(c);
        }
        LineString geom = new GeometryFactory().createLineString(coord);
        return geom;
    }

    @Override
    public boolean contains(Geometry geom, Point3D pt3D) {
        boolean result;
        Coordinate coord = new Coordinate(pt3D.getLongitude(), pt3D.getLatitude(), 100);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point pt = geometryFactory.createPoint(coord);
        result = !geom.contains(pt);
        return result;
    }

    @Override
    public List<Point3D> merge(List<Point3D> pts0, List<Point3D> pts1) {
        List<Point3D> tmp = new ArrayList<>();
        Coordinate[] tab0 = toTabCoordinates(pts0);
        Coordinate[] tab1 = toTabCoordinates(pts1);
        GeometryFactory geomFactory = new GeometryFactory();
        MultiPoint multiPoint0 = geomFactory.createMultiPoint(tab0);
        multiPoint0.setSRID(4326);
        MultiPoint multiPoint1 = geomFactory.createMultiPoint(tab1);
        multiPoint1.setSRID(4326);
        Geometry geom = multiPoint0.union(multiPoint1);
        Coordinate[] tab3 = geom.getCoordinates();
        Set<Point3D> set = new HashSet<>();
        for (Coordinate c : tab3) {
            set.add(new Point3D(c.y, c.x, c.z));
        }
        tmp.addAll(set);
        return tmp;
    }

    @Override
    public List<Point3D> pointsToGrid(List<Point3D> points, Point3D[][] grid) {
        List<Point3D> tmp = new ArrayList<>();

        return tmp;
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

    @Override
    public List<Path> createDelaunay(List<Point3D> pts) {
        Coordinate[] coordinateTab = toTabCoordinates(pts);
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
    public List<Path> createDelaunay(List<Point3D> pts, double maxElevation) {
        List<Point3D> bathy = new ArrayList<>();
        for (Point3D p : pts) {
            bathy.add(new Point3D(p.getLatitude(), p.getLongitude(), maxElevation - p.getElevation()));
        }
        Coordinate[] coordinateTab = toTabCoordinates(bathy);
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
    public List<Path> createDelaunayWithFilterOnArea(List<Point3D> pts, double filter) {
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
    public List<Path> createDelaunayWithFilterOnLength(List<Point3D> pts, double filter) {
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
    public List<Path> createDelaunayWithFilter(List<Point3D> pts, double filter, double maxElevation) {
        List<Point3D> bathy = new ArrayList<>();
        pts.forEach((p) -> {
            bathy.add(new Point3D(p.getLatitude(), p.getLongitude(), maxElevation - p.getElevation()));
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

}
