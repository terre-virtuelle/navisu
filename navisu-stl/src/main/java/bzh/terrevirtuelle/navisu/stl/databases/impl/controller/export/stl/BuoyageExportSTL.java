/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE_INV;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class BuoyageExportSTL {

    protected String stlFilename;
    protected GeodesyServices geodesyServices;
    protected List<Buoyage> buoyages;
    protected String acronym;

    protected double lat;
    protected double lon;
    protected double elv;
    protected double latMin;
    protected double lonMin;
    protected double latScale;
    protected double lonScale;

    public BuoyageExportSTL(GeodesyServices geodesyServices, Point3D[][] gb,
            String stlFilename,
            double latScale, double lonScale) {
        this.stlFilename = stlFilename;
        this.geodesyServices = geodesyServices;
        latMin = gb[0][0].getLatitude();
        lonMin = gb[0][0].getLongitude();
        this.latScale = latScale;
        this.lonScale = lonScale;
    }

    public void export(List<Buoyage> buoyages, double elevation) {
        this.buoyages = buoyages;
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
                double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
                double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
                latM *= latScale;
                lonM *= lonScale;
                if (acronym.equals("BCNCAR") || acronym.equals("BOYCAR")) {
                    buoys = buoys.concat(insertedFile(latM, lonM, elevation, "BCNCAR_" + buoyage.getCategoryOfMark() + ".stl"));
                } else if (acronym.equals("BCNLAT") || acronym.equals("BOYLAT")) {
                    buoys = buoys.concat(insertedFile(latM, lonM, elevation, "BCNLAT_" + buoyage.getCategoryOfMark() + ".stl"));
                } else if (acronym.equals("MORFAC")) {
                    buoys = buoys.concat(insertedFile(latM, lonM, elevation, "MORFAC.stl"));
                } else if (acronym.equals("BCNISD")) {
                    buoys = buoys.concat(insertedFile(latM, lonM, elevation, "danger.stl"));
                } else if (acronym.equals("BOYSPP") || acronym.equals("BCNSPP")) {
                    buoys = buoys.concat(insertedFile(latM, lonM, elevation, "spp.stl"));
                }
            }
            result = buoys.concat(body);
            path = Paths.get(stlFilename);
            Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private String insertedFile(double latitude, double longitude, double altitude, String buoyFilename) {
        String buoyName = "data/stl/" + buoyFilename;
        String buoy = "";
        String result = "";
        try {
            buoy = new String(Files.readAllBytes(Paths.get(buoyName)));
            result = new TransformSTL().transform(buoy, latitude, longitude, altitude);

        } catch (IOException ex) {
            Logger.getLogger(BuoyageExportSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }

}
