/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.operation.buffer.BufferParameters;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class SLConsExportToSTL {

    protected JTSServices jtsServices;
    protected GeodesyServices geodesyServices;

    protected String stlFilename;

    protected Path path;
    protected Polygon polygon;

    protected double latMin;
    protected double lonMin;

    protected double latScale;
    protected double lonScale;
    protected double verticalOffset;

    public SLConsExportToSTL(JTSServices jtsServices, GeodesyServices geodesyServices,
            String stlFilename,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double verticalOffset) {
        this.jtsServices = jtsServices;
        this.geodesyServices = geodesyServices;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latScale = latScale;
        this.lonScale = lonScale;
        this.verticalOffset = verticalOffset;
        this.stlFilename = stlFilename;
    }

    public void export(List<? extends Geo> objects) {
        GridBox3DExportToSTL gridBox3DExportToSTL = new GridBox3DExportToSTL(geodesyServices);
        List<Path> paths = new ArrayList<>();
        String result = "";
        try {
            for (Geo geo : objects) {
                String geometry = geo.getGeom();
                if (geometry.contains("MULTILINESTRING") || geometry.contains("LINESTRING")) {
                    List<Point3DGeo> points = jtsServices.getBuffer(geometry, 0.00012, BufferParameters.CAP_FLAT);//0.0001

                    Geometry geom = jtsServices.getPolygon(points);
                    if (geom.getArea() < 2.0E-5) {

                        List<Position> positions = new ArrayList<>();
                        points.forEach((p) -> {
                            positions.add(Position.fromDegrees(p.getLatitude(),
                                    p.getLongitude(),
                                    p.getElevation() / 4));//5
                        });

                        List<Position> highPositions = new ArrayList<>();
                        List<Position> lowPositions = new ArrayList<>();
                        if (positions.get(0).getLatitude().getDegrees() == positions.get(positions.size() - 1).getLatitude().getDegrees()) {
                            polygon = new Polygon(positions);
                            highPositions.addAll(positions);
                            positions.forEach((p) -> {
                                lowPositions.add(new Position(p.getLatitude(), p.getLongitude(), 0.0));
                            });

                            for (int i = 0; i < highPositions.size() - 1; i++) {
                                List<Position> pos0 = new ArrayList<>();
                                pos0.add(lowPositions.get(i));
                                pos0.add(highPositions.get(i + 1));
                                pos0.add(highPositions.get(i));
                                pos0.add(lowPositions.get(i));
                                Path path0 = new Path(pos0);
                                paths.add(path0);

                                List<Position> pos1 = new ArrayList<>();
                                pos1.add(lowPositions.get(i));
                                pos1.add(lowPositions.get(i + 1));
                                pos1.add(highPositions.get(i + 1));
                                pos1.add(lowPositions.get(i));
                                Path path1 = new Path(pos1);
                                paths.add(path1);
                            }
                        }
                    }
                }
            }
            result += gridBox3DExportToSTL.exportSTL(paths, latMin, lonMin, latScale, lonScale, verticalOffset);
            Files.write(Paths.get(stlFilename), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        // System.out.println("positions : " + positions);
        /*
        ExtrudedPolygon[] extrudedPolygonTab = new ExtrudedPolygon[extrudedPolygons.size()];
        int i = 0;
        for (ExtrudedPolygon e : extrudedPolygons) {
            extrudedPolygonTab[i++] = e;//Dans une String et Tesselate puis toFacet
           // System.out.println("e.getOuterBoundary() : "+e.getOuterBoundary());
        }
//Inutile pour STL il sufffit de polygon.getOuterBoundary()


       KMLWriter kmlWriter = new KMLWriter("privateData/stl/tmp.kml");
       String result = kmlWriter.write(extrudedPolygonTab, StandardOpenOption.CREATE);
     
       // KMLWriter kmlWriter = new KMLWriter();//With WRITE
       // String result = kmlWriter.write(extrudedPolygonTab, StandardOpenOption.WRITE);// WRITE Write option is only for write into String

        String[] resultTab = result.trim().split("\n");

        List<List<Point3D>> points = new ArrayList<>();
        String[] ptsTab;
        for (String s : resultTab) {
            if (s.contains("<coordinates>")) {
                List<Point3D> coordinates = new ArrayList<>();             
                s = s.replace("<coordinates>", "");
                s = s.replace("</coordinates>", "");
                ptsTab = s.trim().split("\\s+");
                for (String s1 : ptsTab) {
                    String[] tab = s1.split(",");
                    coordinates.add(new Point3D(Double.valueOf(tab[1]), Double.valueOf(tab[0]), Double.valueOf(tab[2])));
                }
                points.add(coordinates);
            }
        }
        List<List<Path>> paths = new ArrayList<>();
        for (List<Point3D> l : points) {
            List<Path> l1 = jtsServices.createDelaunayToPath(l, 0.0);
            layer.addRenderables(l1);
            wwd.redrawNow();
            paths.add(l1);
        }
         */
    }
}
