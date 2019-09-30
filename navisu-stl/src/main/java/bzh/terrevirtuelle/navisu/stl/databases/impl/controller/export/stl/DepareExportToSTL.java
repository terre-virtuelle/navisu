/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.shapefile.DepareShapefileExport;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    protected RenderableLayer layer;
    protected Shapefile shapefile;
    protected GridBox3D gridBox3D;
    protected List<Path> paths;
    protected String facets;
    protected double latMin;
    protected double lonMin;
    protected double highestElevationBathy;

    protected Set<Map.Entry<String, Object>> entries;

    public DepareExportToSTL(GeodesyServices geodesyServices,
            JTSServices jtsServices,
            DisplayServices displayServices,
            Shapefile shapefile, GridBox3D gridBox3D, double highestElevationBathy,
            RenderableLayer layer) {
        super(shapefile, layer);
        pathToSTL = new PathToSTL(geodesyServices);
        this.jtsServices = jtsServices;
        this.displayServices = displayServices;
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
        for (int i=0; i < topList.size(); i++) {
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
}
