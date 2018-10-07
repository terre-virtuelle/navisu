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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DaeStlExportSTL {

    private String content;
    private String filename;
    private final Position position0;
    private final GeodesyServices geodesyServices;

    public DaeStlExportSTL(GeodesyServices geodesyServices, String filename, double lat0, double lon0) {
        this.filename = filename;

        this.geodesyServices = geodesyServices;
        position0 = new Position(Angle.fromDegrees(lat0), Angle.fromDegrees(lon0), 0.0);
    }

    public String export() {
        String result = "";
        String[] contentTab;
        String[] tmp;
        Position position1;
        Position position2;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException ex) {
            Logger.getLogger(DaeStlExportSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        contentTab = content.split("\n");
        for (String s : contentTab) {
            if (s.contains("vertex")) {
                tmp = s.trim().split("\\s+");
                position1 = geodesyServices.getPosition(position0, 90.0, Double.valueOf(tmp[1]));
                position2 = geodesyServices.getPosition(position1, 0.0, Double.valueOf(tmp[2]), Double.valueOf(tmp[3]));
                result += "vertex " + position2.getLongitude().getDegrees() + " " + position2.getLatitude().getDegrees() + " " + position2.getElevation() + "\n";
            } else {
                result += s + "\n";
            }
        }
        filename = filename.replace(".stl", "");
        filename +=  "_geo.stl";
        System.out.println("filename : " + filename);
        try {
            Files.write(Paths.get(filename), result.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(DaeStlExportSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return filename;
    }

    /**
     * Get the value of filename
     *
     * @return the value of filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Set the value of filename
     *
     * @param filename new value of filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Get the value of content
     *
     * @return the value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the value of content
     *
     * @param content new value of content
     */
    public void setContent(String content) {
        this.content = content;
    }

}
