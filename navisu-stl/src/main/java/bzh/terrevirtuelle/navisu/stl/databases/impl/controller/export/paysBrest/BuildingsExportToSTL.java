/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.paysBrest;

import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Aug 3, 2019
 */
public class BuildingsExportToSTL {

    private BathymetryDBServices bathymetryDBServices;
    private GeodesyServices geodesyServices;
    private Connection buildingsConnection;
    private double latMin;
    private double lonMin;
    private double latMax;
    private double lonMax;

    public BuildingsExportToSTL(BathymetryDBServices bathymetryDBServices, GeodesyServices geodesyServices) {
        this.bathymetryDBServices = bathymetryDBServices;
        this.geodesyServices = geodesyServices;
    }

    @SuppressWarnings("unchecked")
    public List<SolidGeo> export(Connection buildingsConnection, Point3DGeo[][] g, String filename, double latScale, double lonScale, double tileSideZ, double maxdepth) {
        this.buildingsConnection = buildingsConnection;
        double elvScale = (latScale + lonScale) / 2;
        latMin = g[0][0].getLatitude();
        lonMin = g[0][0].getLongitude();
        latMax = g[g.length - 1][g[0].length - 1].getLatitude();
        lonMax = g[g.length - 1][g[0].length - 1].getLongitude();
        
        List<SolidGeo> solids = new ArrayList<>();

        List<String> contents = null;
        List<SolidGeo> walls = bathymetryDBServices.retrieveInSolid(buildingsConnection, "wall", latMin, lonMin, latMax, lonMax);
        List<SolidGeo> roofs = bathymetryDBServices.retrieveInSolid(buildingsConnection, "roof", latMin, lonMin, latMax, lonMax);
        
        /*
        walls.forEach((s) -> {
            System.out.println(s.getCentroid());
        });
        System.out.println("");
        
        roofs.forEach((s) -> {
            System.out.println(s.getCentroid());
        });
        */
        
        solids.addAll(walls);
        solids.addAll(roofs);

        // bathymetryDBServices.re
        /*
            String content = null;
            // Transform vertex coordinates in angles
            for (String ss : contents) {
                try {
                    content = new String(Files.readAllBytes(Paths.get(ss)));
                } catch (IOException ex) {
                    Logger.getLogger(BuildingsExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
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
                            Logger.getLogger(BuildingsExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                        }
                    }
                }
            }
         */
        // }
        return solids;
    }
}
