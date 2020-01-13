/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.shapefile.DepareShapefileExport;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import gov.nasa.worldwind.render.markers.BasicMarkerAttributes;
import gov.nasa.worldwind.render.markers.BasicMarkerShape;
import gov.nasa.worldwind.render.markers.MarkerAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date May 2, 2019
 */
public class DepareExportToSTL
        extends DepareShapefileExport {

    protected static final Logger LOGGER = Logger.getLogger(DepareExportToSTL.class.getName());

    protected PathToSTL pathToSTL;
    protected String filename;
    protected JTSServices jtsServices;
    protected DisplayServices displayServices;
    protected TopologyServices topologyServices;
    protected GeodesyServices geodesyServices;

    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected GridBox3D gridBox3D;
    protected List<Path> paths;
    protected String facets;
    protected double latMin;
    protected double lonMin;
    protected double highestElevationBathy;

    //  protected Set<Map.Entry<String, Object>> entries;
    public DepareExportToSTL(GeodesyServices geodesyServices,
            JTSServices jtsServices,
            DisplayServices displayServices,
            TopologyServices topologyServices,
            Shapefile shapefile, GridBox3D gridBox3D, double highestElevationBathy,
            RenderableLayer layer) {
        super(shapefile, layer);
        pathToSTL = new PathToSTL(geodesyServices);
        this.geodesyServices = geodesyServices;
        this.jtsServices = jtsServices;
        this.displayServices = displayServices;
        this.topologyServices = topologyServices;
        this.shapefile = shapefile;
        this.gridBox3D = gridBox3D;
        this.layer = layer;
        this.latMin = gridBox3D.getGrid()[0][0].getLatitude();
        this.lonMin = gridBox3D.getGrid()[0][0].getLongitude();
        this.highestElevationBathy = highestElevationBathy;
    }

    public void export(String filename, List<Polygon> polygonList, double verticalExaggeration,
            double latScale, double lonScale, double verticalOffset) {
        this.filename = filename;
        createPolygon(polygonList, verticalExaggeration, latScale, lonScale, verticalOffset);
    }

    protected void createPolygon(List<Polygon> polygonList, double verticalExaggeration,
            double latScale, double lonScale, double verticalOffset) {
        for (Polygon p : polygonList) {
            double height = (Double) p.getValue("drval1");
            double h = height;
            height = highestElevationBathy - (verticalExaggeration * height);
            Iterable<? extends LatLon> iPostions = p.getOuterBoundary();
            List<Position> posList = new ArrayList<>();
            for (LatLon pp : iPostions) {
                Position p1 = new Position(pp.getLatitude(), pp.getLongitude(), height);
                posList.add(p1);
            }
            paths = extrudePolygon(posList);
            pathToSTL.exportSTL(paths, filename, "depare " + h, latMin, lonMin, latScale, lonScale, verticalOffset);
        }
    }

    private List<Path> extrudePolygon(List<Position> posList) {
        List<Path> pathList;
        List<Position> topList = posList;
        List<Position> bottomList = new ArrayList<>();
        for (int i = 0; i < topList.size(); i++) {
            bottomList.add(new Position(topList.get(i).getLatitude(), topList.get(i).getLongitude(), 0.0));
        }
        pathList = createPaths(topList, bottomList);
        return pathList;
    }

    public List<Path> createPaths(List<Position> topList, List<Position> bottomList) {
        // Sides
        List<Path> resultList = new ArrayList<>();
        for (int j = 0; j < topList.size() - 1; j++) {
            List<Position> tmp0 = new ArrayList<>();
            List<Position> tmp1 = new ArrayList<>();
            tmp0.add(bottomList.get(j));
            tmp0.add(bottomList.get(j + 1));
            tmp0.add(topList.get(j + 1));
            tmp0.add(bottomList.get(j));
            Path path0 = new Path(tmp0);

            tmp1.add(bottomList.get(j));
            tmp1.add(topList.get(j + 1));
            tmp1.add(topList.get(j));
            tmp1.add(bottomList.get(j));
            Path path1 = new Path(tmp1);

            resultList.add(path0);
            resultList.add(path1);
        }
        // Top face
        List<Point3DGeo> pts = new ArrayList<>();
        topList.forEach((p) -> {
            pts.add(new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation()));
        });
        Geometry geometry = jtsServices.getPolygon(pts);
        List<Path> paths_0 = jtsServices.createDelaunayPoly2TriToPath(pts);
        List<Path> innerPaths = jtsServices.pathsInGeometry(geometry, paths_0);

        resultList.addAll(innerPaths);
        return resultList;
    }

    public void exportGround(List<SurfacePolygons> shapes, double distance, double elevation) {
        // List<Geometry> polygonsWKT = createGrounds(shapes);
        /* Display DEBUG */
        //  displayPolygons(polygonsWKT);
        // List<List<Point3DGeo>> points = createPoints(polygonsWKT, distance, elevation);
        List<List<Point3DGeo>> boundaries = createBoundaries(shapes);
        //  List<List<Point3DGeo>> all = mergePoints(boundaries, points);
        //  displayPointList(points);
        List<List<Path>> p = createTIN(boundaries);

        // displayPaths(p, Material.RED);
        List<List<Path>> p2 = createTIN2(p, 8.0);
        List<List<Path>> p3 = createTIN2(p2, 8.0);
       // displayPaths(p3, Material.GREEN);
        displayPolygons(p3, layer, Material.GREEN);
    }

    /* Create triangles from triangles path */
    public List<List<Path>> createTIN2(List<List<Path>> paths, double elevation) {
        List<List<Path>> result = new ArrayList<>();
        List< List<Pair<Double, Double>>> centroids = new ArrayList<>();
        List<Geometry> geometries;
        for (List<Path> pathList : paths) {
            geometries = topologyServices.wwjPathsToJtsGeometry(pathList);
            centroids.add(topologyServices.jtsGetCentroids(geometries));
        }

        for (int i = 0; i < paths.size(); i++) {
            List<Path> tmp = new ArrayList<>();
            for (int j = 0; j < paths.get(i).size(); j++) {
                if (topologyServices.wwjPathToJtsGeometry(paths.get(i).get(j)).getArea() >= 1E-7) {
                    List<Position> positionList = new ArrayList<>();
                    Iterable<? extends Position> vertices = paths.get(i).get(j).getPositions();
                    for (Position pos : vertices) {
                        positionList.add(pos);
                    }
                    double hight = Math.random() * elevation;

                    List<Position> posTab0 = new ArrayList<>();
                    posTab0.add(positionList.get(0));
                    posTab0.add(positionList.get(1));
                    posTab0.add(new Position(Angle.fromDegrees(centroids.get(i).get(j).getX()),
                            Angle.fromDegrees(centroids.get(i).get(j).getY()), hight));
                    posTab0.add(positionList.get(0));
                    tmp.add(new Path(posTab0));

                    List<Position> posTab1 = new ArrayList<>();
                    posTab1.add(positionList.get(1));
                    posTab1.add(positionList.get(2));
                    posTab1.add(new Position(Angle.fromDegrees(centroids.get(i).get(j).getX()),
                            Angle.fromDegrees(centroids.get(i).get(j).getY()), hight));
                    posTab1.add(positionList.get(1));
                    tmp.add(new Path(posTab1));

                    List<Position> posTab2 = new ArrayList<>();
                    posTab2.add(positionList.get(2));
                    posTab2.add(positionList.get(0));
                    posTab2.add(new Position(Angle.fromDegrees(centroids.get(i).get(j).getX()),
                            Angle.fromDegrees(centroids.get(i).get(j).getY()), hight));
                    posTab2.add(positionList.get(2));
                    tmp.add(new Path(posTab2));
                } else {
                    tmp.add(paths.get(i).get(j));
                }
            }
            result.add(tmp);
        }
/*
        for (int i = 0; i < result.size(); i++) {
            for (Path p : result.get(i)) {
                System.out.println(topologyServices.wwjPathToJtsGeometry(p).getArea());
            }
        }
*/
        return result;
    }

    public List<List<Path>> createTIN(List<List<Point3DGeo>> tops) {
        List<List<Path>> result = new ArrayList<>();
        for (List<Point3DGeo> sp : tops) {
            List<Path> tmp = jtsServices.createDelaunayPoly2TriToPath(sp);
            result.add(tmp);
        }
        return result;
    }

    public List<Geometry> createGrounds(List<SurfacePolygons> shapes) {
        List<Geometry> polygonsWKT = new ArrayList<>();
        for (SurfacePolygons sp : shapes) {
            double val = (Double) sp.getValue("drval1");
            if (val == -9) {
                Iterable<? extends Position> pos = sp.getBuffer().getPositions();
                Polygon polygon = new Polygon(pos);
                polygonsWKT.add(topologyServices.wwjPolygonToJtsGeometry(polygon));
            }
        }
        return polygonsWKT;
    }

    public List<List<Point3DGeo>> createBoundaries(List<SurfacePolygons> shapes) {
        List<List<Point3DGeo>> result = new ArrayList<>();
        for (SurfacePolygons sp : shapes) {
            List<Point3DGeo> tmp = new ArrayList<>();
            double val = (Double) sp.getValue("drval1");
            if (val == -9) {
                Iterable<? extends Position> pos = sp.getBuffer().getPositions();
                for (Position p : pos) {
                    tmp.add(new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation()));
                }
                result.add(tmp);
            }

        }
        return result;
    }

    protected List<List<Point3DGeo>> createPoints(List<Geometry> polygonsWKT, double distance, double elevation) {
        List<Geometry> envelopesWKT = new ArrayList<>();
        List<List<Point3DGeo>> result = new ArrayList<>();
        for (Geometry p : polygonsWKT) {
            envelopesWKT.add(p.getEnvelope());
        }
        // displayPolygons(envelopesWKT);
        double distLat = 0.0;
        double distLon = 0.0;
        int line;
        int col;
        int shape = 0;
        for (Geometry g : envelopesWKT) {
            List<Point3DGeo> tmp = new ArrayList<>();
            Coordinate[] coordinates = g.getCoordinates();

            distLat = geodesyServices.getDistanceM(new Position(Angle.fromDegrees(coordinates[0].y), Angle.fromDegrees(coordinates[0].x), 0.0),
                    new Position(Angle.fromDegrees(coordinates[1].y), Angle.fromDegrees(coordinates[1].x), 0.0));
            distLon = geodesyServices.getDistanceM(new Position(Angle.fromDegrees(coordinates[0].y), Angle.fromDegrees(coordinates[0].x), 0.0),
                    new Position(Angle.fromDegrees(coordinates[2].y), Angle.fromDegrees(coordinates[2].x), 0.0));
            System.out.println("distLat : " + distLat + " distLon : " + distLon);
            System.out.println("");
            line = (int) (distLat / distance);
            col = (int) (distLon / distance);
            System.out.println("line : " + line + " col : " + col);
            double lat = coordinates[0].y;
            double lon = coordinates[0].x;
            GeometryFactory geomFactory = new GeometryFactory();
            for (int i = 0; i < line; i++) {
                for (int j = 0; j < col; j++) {
                    Coordinate coord = new Coordinate(lon + j * 0.0000089993 * distance, lat);
                    Point point = geomFactory.createPoint(coord);
                    if (polygonsWKT.get(shape).contains(point)) {
                        tmp.add(new Point3DGeo(lat, lon + j * 0.0000089993 * distance, Math.random() * elevation));
                    }
                }
                lat += 0.0000089993 * distance;
            }
            //  displayPoints(tmp);
            result.add(tmp);
            shape++;
        }
        return result;
    }

    public List<List<Point3DGeo>> mergePoints(List<List<Point3DGeo>> boundaries, List<List<Point3DGeo>> points) {
        List<List<Point3DGeo>> result = new ArrayList<>();
        for (int i = 0; i < boundaries.size(); i++) {
            List<Point3DGeo> tmp = new ArrayList<>();
            tmp.addAll(boundaries.get(i));
            tmp.addAll(points.get(i));
            result.add(tmp);
        }
        return result;
    }

    protected ShapeAttributes createAttributes(Material material) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setDrawInterior(false);
        normAttributes.setDrawOutline(true);
        normAttributes.setInteriorMaterial(material);
        normAttributes.setOutlineMaterial(material);
        return normAttributes;
    }
    private MarkerAttributes[] attrs = new BasicMarkerAttributes[]{
        new BasicMarkerAttributes(Material.YELLOW, BasicMarkerShape.SPHERE, 1d, 1d, 1d),};

    protected void displayPoints(List<Point3DGeo> points) {
        List<PointPlacemark> pointPlacemarks = new ArrayList<>();
        for (Point3DGeo p : points) {
            PointPlacemark pointPlacemark = new PointPlacemark(Position.fromDegrees(p.getLatitude(), p.getLongitude(), p.getElevation()));
            pointPlacemarks.add(pointPlacemark);
        }
        layer.addRenderables(pointPlacemarks);
        wwd.redrawNow();
    }

    protected void displayPointList(List<List<Point3DGeo>> points) {
        for (List<Point3DGeo> list : points) {
            displayPoints(list);
        }
    }

    protected void displayPolygons(List<Geometry> polygonsWKT) {
        List<Polygon> polygons = new ArrayList<>();
        for (Geometry p : polygonsWKT) {
            Polygon polygon = jtsServices.getPolygonFromPolygon(p);
            polygons.add(polygon);

        }
        displayServices.displayPolygons(polygons, layer, Material.RED, 0.05);
    }

    protected void displayPolygons(List<List<Path>> paths, RenderableLayer layer, Material material) {
        for (List<Path> p : paths) {
            displayServices.displayPolygonsFromPaths(p, layer, material);
        }
    }

    protected List<Polygon> paths2Polygons(List<Path> paths) {
        List<Polygon> result = new ArrayList<>();
        for (Path p : paths) {
            result.add(new Polygon(p.getPositions()));
        }
        return result;
    }

    protected List<List<Polygon>> pathsList2PolygonsList(List<List<Path>> paths) {
        List<List<Polygon>> result = new ArrayList<>();
        for (List<Path> p : paths) {
            result.add(paths2Polygons(p));
        }
        return result;
    }

    protected void displayPaths(List<List<Path>> paths, Material material) {
        for (List<Path> p : paths) {
            displayServices.displayPaths(p, layer, material, 1.0, 10);
        }
    }
}
