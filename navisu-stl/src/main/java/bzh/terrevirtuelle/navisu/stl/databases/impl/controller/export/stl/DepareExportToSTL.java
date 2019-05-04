/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.DepareView;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ExtrudedPolygon;
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
public class DepareExportToSTL
        extends PathToSTL {

    protected static final Logger LOGGER = Logger.getLogger(DepareExportToSTL.class.getName());

    protected Shapefile shapefile;
    protected GridBox3D gridBox3D;
    protected List<Path> paths;
    protected String facets;
    protected double latMin;
    protected double lonMin;
    protected double highestElevationBathy;

    protected Set<Map.Entry<String, Object>> entries;

    public DepareExportToSTL(GeodesyServices geodesyServices, Shapefile shapefile, GridBox3D gridBox3D) {
        super(geodesyServices);
        this.geodesyServices = geodesyServices;
        this.shapefile = shapefile;
        this.gridBox3D = gridBox3D;
        this.latMin = gridBox3D.getGrid()[0][0].getLatitude();
        this.lonMin = gridBox3D.getGrid()[0][0].getLongitude();
        this.highestElevationBathy = gridBox3D.getHighestElevationBathy();
    }

    public void export(String filename,
            double verticalExaggeration,
            double latScale, double lonScale) {
        result = "";
        this.filename = filename;
      //  System.out.println("shapefile "+shapefile);
        while (shapefile.hasNext()) {
            try {
                ShapefileRecord record = shapefile.nextRecord();
              //  System.out.println("record : " +record);
                createPolygon(record, verticalExaggeration, latScale, lonScale);
            } catch (Exception ex) {
                Logger.getLogger(DepareExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

    }

    protected void createPolygon(ShapefileRecord record, double verticalExaggeration, double latScale, double lonScale) {
/*
        String exportSTL(List<Path> paths,
            String filename,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double verticalOffset) 
        */
        if (record.getAttributes() != null) {
            entries = record.getAttributes().getEntries();
            entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
              //  System.out.println("e : " + e.getKey());
                if (e.getKey().equalsIgnoreCase("drval1")) {
                    double height = verticalExaggeration * (highestElevationBathy - (Double) e.getValue());
                    paths = extrudePolygon(record, height);
                   exportSTL(paths, filename, latMin, lonMin, latScale, lonScale, 10.0);
               
                }
            });
        }
    }

    private List<Path> extrudePolygon(ShapefileRecord record, double height) {
        List<Position> tmp0 = new ArrayList<>();
        List<Position> tmp1 = new ArrayList<>();
        List<Path> paths = new ArrayList<>();
        for (int i = 0; i < record.getNumberOfParts(); i++) {
            VecBuffer buffer = record.getCompoundPointBuffer().subBuffer(i);
            if (WWMath.computeWindingOrderOfLocations(buffer.getLocations()).equals(AVKey.CLOCKWISE)) {
                Iterable<? extends Position> pos = buffer.getPositions();
                List<Position> topList = new ArrayList<>();
                List<Position> bottomList = new ArrayList<>();
                for (Position p : pos) {
                    topList.add(new Position(p.getLatitude(), p.getLongitude(), height));
                    bottomList.add(new Position(p.getLatitude(), p.getLongitude(), 0.0));
                }
                for (int j = 0; j < topList.size() - 1; j++) {
                    tmp0.clear();
                    tmp0.add(bottomList.get(j));
                    tmp0.add(bottomList.get(j + 1));
                    tmp0.add(topList.get(j + 1));
                    tmp0.add(bottomList.get(j));
                    Path path0 = new Path(tmp0);
                    tmp1.clear();
                    tmp1.add(bottomList.get(j));
                    tmp1.add(topList.get(j + 1));
                    tmp1.add(topList.get(j));
                    tmp1.add(bottomList.get(j));
                    Path path1 = new Path(tmp1);

                    paths.add(path0);
                    paths.add(path1);
                }

            }
        }
        return paths;
    }
}
