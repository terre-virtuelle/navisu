/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Wrecks;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class WrecksExportSTL {

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
    protected double elevationScale;

    public WrecksExportSTL(GeodesyServices geodesyServices, Point3DGeo[][] gb,
            String stlFilename,
            double latScale, double lonScale) {
        this.stlFilename = stlFilename;
        this.geodesyServices = geodesyServices;
        latMin = gb[0][0].getLatitude();
        lonMin = gb[0][0].getLongitude();
        this.latScale = latScale;
        this.lonScale = lonScale;
        elevationScale = (latScale + lonScale) / 2.0;
    }

    public void export(List<Wrecks> objects, double elevation, double verticalOffet) {
        String result = "";
        elevation *= elevationScale;
        elevation += verticalOffet;
        try {
            for (Wrecks o : objects) {
                acronym = "WRECKS";
                lat = o.getLatitude();
                lon = o.getLongitude();
                double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
                double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
                latM *= latScale;
                lonM *= lonScale;
                result = result.concat(insertedFile(latM, lonM, elevation, "WRECKS.stl"));
            }
            Path path = Paths.get(stlFilename);
            Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public String insertedFile(double latitude, double longitude, double elevation, String objectFilename) {
        String objectName = "data/stl/" + objectFilename;
        String buoy = "";
        String result = "";
        try {
            buoy = new String(Files.readAllBytes(Paths.get(objectName)));
            result = new TransformSTL().transform(buoy, latitude, longitude, elevation);
        } catch (IOException ex) {
            Logger.getLogger(WrecksExportSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }
}
