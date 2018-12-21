/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.util.io.IO;
import gov.nasa.worldwind.WorldWindow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author serge
 */
public class MeshExportToSTL {

    private final GeodesyServices geodesyServices;
    private final GuiAgentServices guiAgentServices;
    protected JTSServices jtsServices;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String CACHE_FILE_NAME = System.getProperty("user.home") + "/.navisu/caches/caches.properties";
    protected String OBJECT_DIR;
    protected Properties cacheProperties = new Properties();
    private double latMin;
    private double lonMin;
    private final Map<Point3D, List<String>> meshObjectsLocationMap;

    public MeshExportToSTL(GeodesyServices geodesyServices, GuiAgentServices guiAgentServices, JTSServices jtsServices) {
        this.guiAgentServices = guiAgentServices;
        this.geodesyServices = geodesyServices;//  elvScale = (latScale + lonScale) / 2;
        this.jtsServices = jtsServices;
        this.meshObjectsLocationMap = new HashMap<>();

        InputStream input = null;
        try {
            input = new FileInputStream(CACHE_FILE_NAME);
            cacheProperties.load(input);
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(MeshExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        OBJECT_DIR = cacheProperties.getProperty("OBJECT_DIR");
        if (OBJECT_DIR == null) {
            OBJECT_DIR = "data/stl/kmz";
        }
    }

    @SuppressWarnings("unchecked")
    public List<Point3D> loadMesh() {
        double latitude;
        double longitude;
        double elevation;
        List<Point3D> boundList = new ArrayList<>();
        List<File> files = IO.multipleFileChooser(guiAgentServices.getStage(), OBJECT_DIR, "Georeferenced STL files (*.stl)", "*.STL", "*.stl");
        if (files != null) {
            OBJECT_DIR = files.get(0).getParent();
            OutputStream output = null;
            Properties properties = new Properties();
            try {
                output = new FileOutputStream(CACHE_FILE_NAME);
                properties.setProperty("OBJECT_DIR", OBJECT_DIR);
                properties.store(output, null);
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(MeshExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }

            for (File file : files) {

                String content = null;
                try {
                    content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                } catch (IOException ex) {
                    Logger.getLogger(MeshExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
                if (content != null) {
                    /* File STL with Geographics coordinates
                       Update stl files map
                     solid 65_103_terrain.obj  Origin :  48.374021741300346 -4.530830948390423 0.0 
                     */
                    if (content.contains("Origin")) {
                        String[] contentTab = content.split("\n");
                        for (String s : contentTab) {
                            if (s.contains("solid") && !s.contains("endsolid")) {
                                try {
                                    String[] tmp = s.trim().split("\\s+");
                                    latitude = Double.parseDouble(tmp[4]);
                                    longitude = Double.parseDouble(tmp[5]);
                                    elevation = Double.parseDouble(tmp[6]);
                                    Point3D key = new Point3D(latitude, longitude, elevation);
                                    if (!meshObjectsLocationMap.containsKey(key)) {
                                        meshObjectsLocationMap.put(key, new ArrayList<>());
                                    }
                                    meshObjectsLocationMap.get(key).add(file.getAbsolutePath());
                                    boundList.add(key);
                                } catch (NumberFormatException e) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setHeaderText("Bad format file STL");
                                    alert.show();
                                }
                            }
                        }
                    }
                    
                    if (content.contains("Bounds")) {
                        String[] contentTab = content.split("\n");
                        for (String s : contentTab) {
                            if (s.contains("solid") && !s.contains("endsolid")) {
                                try {
                                    
                                    String[] tmp0 = s.trim().split("\\[");
                                    tmp0[1] = tmp0[1].replace("]", "");
                                    
                                    String[] tmp1 = tmp0[1].trim().split(",");
                                    String[] tmp2 = tmp1[0].trim().split("\\s+");
                                   
                                    latitude = Double.parseDouble(tmp2[0]);
                                    longitude = Double.parseDouble(tmp2[1]);
                                    elevation = Double.parseDouble(tmp2[2]);
                                    
                                    Point3D key = new Point3D(latitude, longitude, elevation);
                                    if (!meshObjectsLocationMap.containsKey(key)) {
                                        meshObjectsLocationMap.put(key, new ArrayList<>());
                                    }
                                    meshObjectsLocationMap.get(key).add(file.getAbsolutePath());
                                  
                                    for (int i = 0; i < 5; i++) {
                                        String[] tmp3 = tmp1[i].trim().split("\\s+");
                                        boundList.add(new Point3D(Double.parseDouble(tmp3[0]),Double.parseDouble(tmp3[1]),Double.parseDouble(tmp3[2])));
                                    }

                                } catch (NumberFormatException e) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setHeaderText("Bad format file STL");
                                    alert.show();
                                }
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Bad format file STL. \n"
                                + "Use item : Import/export 3D object from STL/KMZ \n"
                                + "in Tools menu, \n"
                                + "to transform metric coordinates\n"
                                + "in geographic coordinates");
                        alert.show();
                    }
                }
            }
        }
        return boundList;
    }

    @SuppressWarnings("unchecked")
    public void export(Point3D[][] g, String filename, double latScale, double lonScale, double tileSideZ, double maxdepth) {
        double elvScale = (latScale + lonScale) / 2;
        latMin = g[0][0].getLatitude();
        lonMin = g[0][0].getLongitude();
        List<Point3D> bounds = new ArrayList<>();
        bounds.add(g[0][0]);
        bounds.add(g[0][g[1].length - 1]);
        bounds.add(g[g[0].length - 1][g[1].length - 1]);
        bounds.add(g[g[0].length - 1][0]);
        bounds.add(g[0][0]);
        List<Point3D> locInBound = new ArrayList<>();
        Set<Point3D> locations = meshObjectsLocationMap.keySet();
        //DEBUG for mesh import
       // locations.stream().filter((loc) -> (jtsServices.contains(jtsServices.getPolygon(bounds), loc))).forEachOrdered((loc) -> {
      //      locInBound.add(loc);
       // });

       for(Point3D loc : locations){
           locInBound.add(loc);
       }
       
       
        for (Point3D p : locInBound) {
            List<String> contents = meshObjectsLocationMap.get(p);
            System.out.println("p : "+p);
            String content = null;
            // Transform vertex coordinates in angles
            for (String ss : contents) {
                try {
                    content = new String(Files.readAllBytes(Paths.get(ss)));
                } catch (IOException ex) {
                    Logger.getLogger(MeshExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
                if (content != null) {
                    if (content.contains("Origin") || content.contains("Bounds")) {

                        // Transform vertex angle coordinates in coordinates for one tile
                        String stlResult = "";
                        String[] resultTab = content.split("\n");
                        for (String s : resultTab) {
                            if (s.contains("facet") || s.contains("loop") || s.contains("solid")) {
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

                                    double elv = Double.parseDouble(c[3]) * elvScale + tileSideZ;
                                    stlResult += "vertex " + lonM + " " + latM + " " + elv + "\n";
                                }
                            }
                        }

                        //Insert mœesh file in stlFile.stl
                        try {
                            Files.write(Paths.get(filename), stlResult.getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException ex) {
                            Logger.getLogger(MeshExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                        }
                    }
                }
            }
        }
    }
}
