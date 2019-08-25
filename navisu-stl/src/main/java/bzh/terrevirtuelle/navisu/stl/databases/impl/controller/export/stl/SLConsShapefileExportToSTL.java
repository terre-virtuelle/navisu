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
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.util.VecBuffer;
import java.util.ArrayList;
import java.util.List;
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
    protected double heightSlCons;
    protected GridBox3D gridBox3D;
    protected List<Path> paths;
    protected String facets;
    protected double latMin;
    protected double lonMin;
    protected double highestElevationBathy;

    public SLConsShapefileExportToSTL(GeodesyServices geodesyServices,
            JTSServices jtsServices,
            DisplayServices displayServices,
            Shapefile shapefile, GridBox3D gridBox3D, double heightSlCons,
            RenderableLayer layer) {
        super(geodesyServices);
        this.jtsServices = jtsServices;
        this.displayServices = displayServices;
        this.shapefile = shapefile;
        this.gridBox3D = gridBox3D;
        this.heightSlCons = heightSlCons;
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
        double height = verticalExaggeration * (highestElevationBathy + heightSlCons);
        paths = extrudePolygon(record, height);
        exportSTL(paths, filename, "slcons " + heightSlCons, latMin, lonMin, latScale, lonScale, verticalOffset);
    }

    private List<Path> extrudePolygon(ShapefileRecord record, double height) {

        List<Path> pathList = new ArrayList<>();
        List<Position> topList = new ArrayList<>();
        // List<Position> topListTmp = new ArrayList<>();
        List<Position> bottomList = new ArrayList<>();
        for (int i = 0; i < record.getNumberOfParts(); i++) {
            VecBuffer buffer = record.getCompoundPointBuffer().subBuffer(i);
            Iterable<? extends Position> pos = buffer.getPositions();
            //inverser la liste
            for (Position p : pos) {
                topList.add(new Position(p.getLatitude(), p.getLongitude(), height));
                bottomList.add(new Position(p.getLatitude(), p.getLongitude(), 0.0));
            }
            //Collections.reverse(topList);
            //Collections.reverse(bottomList);
            pathList = createPaths(topList, bottomList);
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
        List<Point3DGeo> top = new ArrayList<>();
        List<Point3DGeo> bottom = new ArrayList<>();
        topList.forEach((p) -> {
            Point3DGeo tmp0 = new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation());
            top.add(tmp0);
            Point3DGeo tmp1 = new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), 0.0);
            bottom.add(tmp1);
        });
        List<Path> paths_0 = jtsServices.createDelaunayToPath(top);
        resultList.addAll(paths_0);
        paths_0 = jtsServices.createDelaunayToPath(bottom);
        resultList.addAll(paths_0);
        displayServices.displayPolygonsFromPaths(resultList, layer, Material.YELLOW, 0.5);

        return resultList;
    }
}
