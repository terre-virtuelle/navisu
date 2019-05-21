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
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date May 2, 2019
 */
public class SLConsShapefileExportToSTL
        extends PathToSTL {

    protected static final Logger LOGGER = Logger.getLogger(SLConsShapefileExportToSTL.class.getName());

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

    public SLConsShapefileExportToSTL(GeodesyServices geodesyServices,
            JTSServices jtsServices,
            DisplayServices displayServices,
            Shapefile shapefile, GridBox3D gridBox3D,
            RenderableLayer layer) {
        super(geodesyServices);
        this.jtsServices = jtsServices;
        this.displayServices = displayServices;
        this.shapefile = shapefile;
        this.gridBox3D = gridBox3D;
        this.layer = layer;
        this.latMin = gridBox3D.getGrid()[0][0].getLatitude();
        this.lonMin = gridBox3D.getGrid()[0][0].getLongitude();
        this.highestElevationBathy = gridBox3D.getHighestElevationBathy();
    }

    public void export(String filename,
            double verticalExaggeration,
            double latScale, double lonScale, double verticalOffset) {
        result = "";
        this.filename = filename;
        while (shapefile.hasNext()) {
            try {
                ShapefileRecord record = shapefile.nextRecord();
                createPolygon(record, verticalExaggeration, latScale, lonScale, verticalOffset);
            } catch (Exception ex) {
                Logger.getLogger(SLConsShapefileExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

    }

    protected void createPolygon(ShapefileRecord record, double verticalExaggeration,
            double latScale, double lonScale, double verticalOffset) {

        if (record.getAttributes() != null) {
            entries = record.getAttributes().getEntries();
            entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                if (e.getKey().equalsIgnoreCase("drval1")) {
                    //Case e=-9.0 m ?
                    double h = (Double) e.getValue();
                    if (h < 0.0) {
                        h = 0.0;
                    }
                    double height = verticalExaggeration * (highestElevationBathy - h);
                    paths = extrudePolygon(record, height);
                    exportSTL(paths, filename, "depare " + e.getValue(), latMin, lonMin, latScale, lonScale, verticalOffset);//verticalOffset

                }
            });
        }
    }

    private List<Path> extrudePolygon(ShapefileRecord record, double height) {

        List<Path> pathList = new ArrayList<>();
        for (int i = 0; i < record.getNumberOfParts(); i++) {
            VecBuffer buffer = record.getCompoundPointBuffer().subBuffer(i);
            if (WWMath.computeWindingOrderOfLocations(buffer.getLocations()).equals(AVKey.CLOCKWISE)) {
                Iterable<? extends Position> pos = buffer.getPositions();
                List<Position> cwTopList = new ArrayList<>();
                for (Position p : pos) {
                    cwTopList.add(new Position(p.getLatitude(), p.getLongitude(), height));
                }
                //Create counter clockwise list
                List<Position> topList = new ArrayList<>();
                List<Position> bottomList = new ArrayList<>();
                for (int j = cwTopList.size() - 1; j >= 0; j--) {
                    topList.add(cwTopList.get(j));
                    bottomList.add(new Position(cwTopList.get(j).getLatitude(), cwTopList.get(j).getLongitude(), 0.0));
                }
                pathList = createPaths(topList, bottomList);
            }
        }
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
        List<Path> paths_0 = jtsServices.createDelaunayToPath(pts);
        List<Path> innerPaths = jtsServices.pathsInGeometry(geometry, paths_0);

        resultList.addAll(innerPaths);
        return resultList;
    }
}
