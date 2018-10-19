/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DaeStlExportToSTL {

    private String content;
    private final String stlFilename;
    private String stlResult = "";
    private Position position0;
    private final GeodesyServices geodesyServices;
    private final double latMin;//Org of thr Grid
    private final double lonMin;
    private final double latScale;//Scale for the tile
    private final double lonScale;
    private final double maxdepth;
    private final double tileSideZ;
    private final double elvScale;

    public DaeStlExportToSTL(GeodesyServices geodesyServices, String stlFilename,
            double latMin, double lonMin, double latScale, double lonScale,
            double maxdepth, double tileSideZ) {

        this.geodesyServices = geodesyServices;
        this.stlFilename = stlFilename;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latScale = latScale;
        this.lonScale = lonScale;
        this.maxdepth = maxdepth;
        this.tileSideZ = tileSideZ;
        elvScale = (latScale + lonScale) / 2;

    }

    public String export(String filename, double lat0, double lon0) {
        position0 = new Position(Angle.fromDegrees(lat0), Angle.fromDegrees(lon0), 0.0);
        String result = "";
        String[] contentTab;
        String[] tmp;
        Position position1;
        Position position2;
        // Transform vertex coordinates in angles
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException ex) {
            Logger.getLogger(DaeStlExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        contentTab = content.split("\n");
        for (String s : contentTab) {
            if (s.contains("vertex")) {
                tmp = s.trim().split("\\s+");
                if (tmp[1].contains("-")) {
                    position1 = geodesyServices.getPosition(position0, 270.0, -Double.valueOf(tmp[1]) / 1000);
                } else {
                    position1 = geodesyServices.getPosition(position0, 90.0, Double.valueOf(tmp[1]) / 1000);
                }
                if (tmp[2].contains("-")) {
                    position2 = geodesyServices.getPosition(position1, 180.0, -Double.valueOf(tmp[2]) / 1000, Double.valueOf(tmp[3]) / 1000);
                } else {
                    position2 = geodesyServices.getPosition(position1, 0.0, Double.valueOf(tmp[2]) / 1000, Double.valueOf(tmp[3]) / 1000);
                }
                double height = position2.getElevation() + maxdepth;
                result += "vertex " + position2.getLongitude().getDegrees() + " " + position2.getLatitude().getDegrees() + " " + height + "\n";
            } else {
                result += s + "\n";
            }
        }
        // write in file if necessary
        /*
        filename = filename.replace(".stl", "");
        filename += "_geo.stl";
        try {
            Files.write(Paths.get(filename), result.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(DaeStlExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
         */
        // Transform vertex angle coordinates in coordinates for one tile
        stlResult += "solid \n";
        String[] resultTab = result.split("\n");
        for (String s : resultTab) {
            if (s.contains("facet") || s.contains("loop")) {
                stlResult += s + "\n";
            } else {
                if (s.contains("vertex")) {
                    String[] c = s.trim().split("\\s+");
                    double lon = Double.parseDouble(c[1]);
                    double lat = Double.parseDouble(c[2]);
                    double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
                    double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
                    latM *= latScale;
                    lonM *= lonScale;
                    double elv = (Double.parseDouble(c[3]) * elvScale) + tileSideZ;
                    stlResult += "vertex " + lonM + " " + latM + " " + elv + "\n";
                }
            }
        }
        stlResult += "endsolid \n";
        /*
        filename = filename.replace("_geo.stl", "");
        filename += "_um3.stl";
        try {
            Files.write(Paths.get(filename), stlResult.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(DaeStlExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
         */
        //Insert dae file in stlFile.stl
        try {
            Files.write(Paths.get(stlFilename), stlResult.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DaeStlExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        return stlFilename;
    }

}
