/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE_INV;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class BuoyageExportSTL {

    String stlFilename;
    GeodesyServices geodesyServices;
    protected List<Buoyage> buoyages;
    protected String acronym;

    protected double lat;
    protected double lon;
    protected double elv;

    protected double realLatMin;
    protected double realLonMin;

    protected double latRangeMetric;
    protected double lonRangeMetric;
    protected double latScale;
    protected double lonScale;
    protected double tileSideY;
    protected double tileSideX;

    public BuoyageExportSTL(GeodesyServices geodesyServices, Point3D[][] grid, String stlFilename) {
        this.geodesyServices = geodesyServices;
        this.stlFilename = stlFilename;
        double realLatMin = grid[0][0].getLatitude();
        double realLonMin = grid[0][0].getLongitude();
        double realLatMax = grid[grid[0].length - 1][0].getLatitude();
        double realLonMax = grid[1][grid[1].length - 1].getLongitude();
        latRangeMetric = geodesyServices.getDistanceM(realLatMin, realLonMin, realLatMax, realLonMin);
        lonRangeMetric = geodesyServices.getDistanceM(realLatMin, realLonMin, realLatMin, realLonMax);
        latScale = tileSideY / latRangeMetric;
        lonScale = tileSideX / lonRangeMetric;
    }

    public void export(List<Buoyage> buoyages, double elevation) {
        String header;
        String result;
        String buoys = "";
        java.nio.file.Path path = null;
        try {
            String body = new String(Files.readAllBytes(Paths.get(stlFilename)));
            String[] fileSTL = body.split("\n");
            header = fileSTL[0] + "\n";
            body = body.replaceFirst(header, "");
            for (Buoyage buoyage : buoyages) {
                acronym = BUOYAGE_INV.ATT.get(buoyage.getClass().getSimpleName());
                lat = buoyage.getLatitude();
                lon = buoyage.getLongitude();
                if (acronym.equals("BCNCAR") || acronym.equals("BOYCAR")) {
                    buoys = buoys.concat(insertedFile(lat, lon, elevation, "BCNCAR_" + buoyage.getCategoryOfMark() + ".stl"));
                } else if (acronym.equals("BCNLAT") || acronym.equals("BOYLAT")) {
                    buoys = buoys.concat(insertedFile(lat, lon, elevation, "BCNLAT_" + buoyage.getCategoryOfMark() + ".stl"));
                }
            }
            System.out.println("buoys : " + buoys);
            //    result = buoys.concat(body);
            //    result = header + result;
            //     path = Paths.get(stlFilename);
            //    Files.write(path, result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private String insertedFile(double latitude, double longitude, double altitude, String buoyFilename) {
        String buoyName = "data/stl/" + buoyFilename;
        String buoy = "";
        try {
            buoy = new String(Files.readAllBytes(Paths.get(buoyName)));
            /*
            outer loop
      vertex 0.000000e+00 -4.461072e+00 5.000000e+00
      vertex -3.000000e+00 -6.193123e+00 5.000000e+00
      vertex 3.000000e+00 -6.193123e+00 5.000000e+00
    endloop
             */
        } catch (IOException ex) {
            Logger.getLogger(BuoyageExportSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return buoy;
    }

}
