/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.layers.RenderableLayer;
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
    protected double elv;
    protected double latMin;
    protected double lonMin;
    protected double latScale;
    protected double lonScale;

    public LandmarkExportToSTL(GeodesyServices geodesyServices, Point3D[][] gb,
            String stlFilename, double latScale, double lonScale) {
        this.stlFilename = stlFilename;
        this.geodesyServices = geodesyServices;
        latMin = gb[0][0].getLatitude();
        lonMin = gb[0][0].getLongitude();
        this.latScale = latScale;
        this.lonScale = lonScale;
    }

    public void export(List<Landmark> landmarks, double elevation) {
        this.landmarks = landmarks;
        String header;
        String result;
        String landmark = "";
        java.nio.file.Path path = null;

        try {
            String body = new String(Files.readAllBytes(Paths.get(stlFilename)));
            String[] fileSTL = body.split("\n");
            header = fileSTL[0] + "\n";
            body = body.replaceFirst(header, "");
            for (Landmark l : landmarks) {
                lat = l.getLatitude();
                lon = l.getLongitude();

                double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
                double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
                latM *= latScale;
                lonM *= lonScale;
                ElevationModel model = this.wwd.getModel().getGlobe().getElevationModel();
                elevation += model.getElevation(Angle.fromDegreesLatitude(lat), Angle.fromDegreesLongitude(lon));
                elevation *= latScale;
                if (l.getFunction().contains("33")) {
                    landmark = landmark.concat(insertedFile(latM, lonM, elevation, "Phare.stl"));
                } else {
                    landmark = landmark.concat(insertedFile(latM, lonM, elevation, "LNDMRK.stl"));
                }
            }
            result = landmark.concat(body);
            path = Paths.get(stlFilename);
            Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private String insertedFile(double latitude, double longitude, double altitude, String filename) {
        String name = "data/stl/" + filename;
        String obj = "";
        String result = "";
        try {
            obj = new String(Files.readAllBytes(Paths.get(name)));
            result = new TransformSTL().transform(obj, latitude, longitude, altitude);
        } catch (IOException ex) {
            Logger.getLogger(BuoyageExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }

}
