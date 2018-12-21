/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.stl.charts.impl.loader.dem.DemSrtmElevationLoader;
import bzh.terrevirtuelle.navisu.util.io.IO;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author serge
 */
public class DaeExportToSTL {

    private final GeodesyServices geodesyServices;
    private final GuiAgentServices guiAgentServices;
    protected JTSServices jtsServices;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String CACHE_FILE_NAME = System.getProperty("user.home") + "/.navisu/caches/caches.properties";
    protected String OBJECT_DIR;
    protected Properties cacheProperties = new Properties();

    public DaeExportToSTL(GeodesyServices geodesyServices, GuiAgentServices guiAgentServices, JTSServices jtsServices) {
        this.guiAgentServices = guiAgentServices;
        this.geodesyServices = geodesyServices;//  elvScale = (latScale + lonScale) / 2;
        this.jtsServices = jtsServices;

        InputStream input = null;
        try {
            input = new FileInputStream(CACHE_FILE_NAME);
            cacheProperties.load(input);
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(DaeExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        OBJECT_DIR = cacheProperties.getProperty("OBJECT_DIR");
        if (OBJECT_DIR == null) {
            OBJECT_DIR = "data/stl/kmz";
        }
    }


    public void loadKmzAndSaveStlWgs84() {
        File file = IO.fileChooser(guiAgentServices.getStage(), "data/stl", "Georeferenced STL files (*.stl)", "*.STL", "*.stl");
        Point3D point = parseDocKmlFile(file);
        // daeLocationObjectMap.put(new Point3D(point.getLatitude(), point.getLongitude(), point.getElevation()), file.getAbsolutePath());
        toGeographicWGS84CoordAndSave(file, point);
    }

    public void toGeographicWGS84CoordAndSave(File file, Point3D point) {
        guiAgentServices.getJobsManager().newJob("Load KMZ objects", new Job() {

            @Override
            public void run(ProgressHandle progressHandle) {
                String result = "";
                String daeWgs84Filename = file.getName();
                daeWgs84Filename = daeWgs84Filename.replace(".stl", "Wgs84.stl");

                String content = null;
                // Transform vertex coordinates in angles
                try {
                    content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                } catch (IOException ex) {
                    Logger.getLogger(DaeExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
                Position position0 = new Position(Angle.fromDegrees(point.getLatitude()), Angle.fromDegrees(point.getLongitude()), point.getElevation());
                try {
                    content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                } catch (IOException ex) {
                    Logger.getLogger(DaeExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
                String[] contentTab;
                if (content != null) {
                    contentTab = content.split("\n");
                    for (String s : contentTab) {
                        if (s.contains("solid") && !s.contains("endsolid")) {
                            s = "";
                            result += "solid " + daeWgs84Filename + " Origin :  " + point.getLatitude() + " " + point.getLongitude() + " " + point.getElevation();
                        }
                        if (s.contains("endsolid")) {
                            s = "";
                            result += "endsolid " + daeWgs84Filename;
                        }
                        if (s.contains("vertex")) {
                            Position position1;
                            Position position2;

                            String[] tmp = s.trim().split("\\s+");
                            if (tmp[1].contains("-")) {
                                position1 = geodesyServices.getPosition(position0, 270.0, (-Double.valueOf(tmp[1])), Double.valueOf(tmp[3]));
                            } else {
                                position1 = geodesyServices.getPosition(position0, 90.0, (Double.valueOf(tmp[1])), Double.valueOf(tmp[3]));
                            }
                            if (tmp[2].contains("-")) {
                                position2 = geodesyServices.getPosition(position1, 180.0, (-Double.valueOf(tmp[2])), Double.valueOf(tmp[3]));
                            } else {
                                position2 = geodesyServices.getPosition(position1, 0.0, (Double.valueOf(tmp[2])), Double.valueOf(tmp[3]));
                            }
                            double height = position2.getElevation() + +point.getElevation();
                            result += "vertex " + position2.getLongitude().getDegrees() + " " + position2.getLatitude().getDegrees() + " " + height + "\n";
                        } else {
                            result += s + "\n";
                        }
                    }
                    try {
                        if (new File(file.getParent() + "/" + daeWgs84Filename).exists()) {
                            Files.delete(Paths.get(file.getParent() + "/" + daeWgs84Filename));
                        }
                        Files.write(Paths.get(file.getParent() + "/" + daeWgs84Filename), result.getBytes(), StandardOpenOption.CREATE);
                    } catch (IOException ex) {
                        Logger.getLogger(DaeExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                    }
                }
            }
        });
    }

    
    private Point3D parseDocKmlFile(File file) {
        Point3D point = null;
        try {
            String latString = "";
            String lonString = "";
            double latitude = 0.0;
            double longitude = 0.0;
            String latLonString = new String(Files.readAllBytes(Paths.get(file.getParent() + "/doc.kml")));

            String[] latLonTab = latLonString.split("\n");
            boolean match = false;
            for (int i = 0; i < latLonTab.length; i++) {
                if (latLonTab[i].contains("<Model>")) {
                    while (!latLonTab[i].contains("<latitude>")) {
                        i++;
                    }
                    latString = latLonTab[i];
                    lonString = latLonTab[i + 1];
                    match = true;
                }
            }
            if (match == true) {
                latString = latString.replace("<latitude>", "");
                lonString = lonString.replace("<longitude>", "");
                latString = latString.replace("</latitude>", "");
                lonString = lonString.replace("</longitude>", "");
                latitude = Double.parseDouble(latString);
                longitude = Double.parseDouble(lonString);
            }
            point = new DemSrtmElevationLoader(geodesyServices).getElevation(latitude, longitude);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Le répertoire : \n "
                    + file.getParent()
                    + "\n du fichier : " + file.getName()
                    + "  à charger, doit aussi comporter un fichier doc.kml "
                    + "\n avec les coordonnées géographiques du repère.");
            alert.show();
        }
        return point;
    }
}
