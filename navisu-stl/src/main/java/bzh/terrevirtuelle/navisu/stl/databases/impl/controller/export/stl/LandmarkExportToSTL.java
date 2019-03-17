/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.charts.impl.loader.dem.DemSrtmElevationLoader;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import gov.nasa.worldwind.WorldWindow;
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
public class LandmarkExportToSTL {

    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    protected String stlFilename;
    protected GeodesyServices geodesyServices;
    protected List<Landmark> landmarks;
    protected String acronym;

    protected double lat;
    protected double lon;
    protected double latMin;
    protected double lonMin;
    protected double latScale;
    protected double lonScale;
    protected double elvScale;

    public LandmarkExportToSTL(GeodesyServices geodesyServices, Point3DGeo[][] gb,
            String stlFilename, double latScale, double lonScale) {
        this.stlFilename = stlFilename;
        this.geodesyServices = geodesyServices;
        latMin = gb[0][0].getLatitude();
        lonMin = gb[0][0].getLongitude();
        this.latScale = latScale;
        this.lonScale = lonScale;
        elvScale = (latScale + lonScale) / 2.0;
    }

    public void export(List<Landmark> landmarks, double maxDepth, double tileSideZ) {
        this.landmarks = landmarks;;
        String result="";
        double elevation;
        java.nio.file.Path path = null;
        try {
            for (Landmark l : landmarks) {
                lat = l.getLatitude();
                lon = l.getLongitude();
                elevation = new DemSrtmElevationLoader(geodesyServices).getElevation(lat, lon).getElevation();
                double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
                double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
                latM *= latScale;
                lonM *= lonScale;
                elevation += maxDepth;
                elevation *= elvScale;
                elevation += tileSideZ;
                if (l.getFunction().contains("33")) {
                    result = result.concat(insertedFile(latM, lonM, elevation, "PHARE.stl"));
                } else {
                    result = result.concat(insertedFile(latM, lonM, elevation, "LNDMRK.stl"));
                }
            }
            path = Paths.get(stlFilename);
            Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private String insertedFile(double latitude, double longitude, double elevation, String filename) {
        String name = "data/stl/" + filename;
        String obj = "";
        String result = "";
        try {
            obj = new String(Files.readAllBytes(Paths.get(name)));
            result = new TransformSTL().transform(obj, latitude, longitude, elevation);
        } catch (IOException ex) {
            Logger.getLogger(BuoyageExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }

}
