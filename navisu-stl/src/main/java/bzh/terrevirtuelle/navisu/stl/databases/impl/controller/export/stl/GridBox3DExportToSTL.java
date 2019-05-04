/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
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
public class GridBox3DExportToSTL
        extends PathToSTL {

    protected static final Logger LOGGER = Logger.getLogger(GridBox3DExportToSTL.class.getName());
    protected DisplayServices displayServices; //DEBUG
    protected RenderableLayer layer;           //DEBUG

    private GridBox3D gridBox;
    

    public GridBox3DExportToSTL(GeodesyServices geodesyServices, DisplayServices displayServices, RenderableLayer layer, GridBox3D gridBox) {
        super(geodesyServices);
        this.displayServices = displayServices;
        this.layer = layer;
        this.gridBox = gridBox;
    }

    public GridBox3DExportToSTL(GeodesyServices geodesyServices) {
        super(geodesyServices);
    }
    
    public String exportSTL(String filename, double latScale, double lonScale, double verticalOffset) {
        this.filename = filename;
        double latMin = gridBox.getGrid()[0][0].getLatitude();
        double lonMin = gridBox.getGrid()[0][0].getLongitude();
        // PointPlacemark pointPlacemark=new PointPlacemark(Position.fromDegrees(latMin,lonMin,gridBox.getGrid()[0][0].getElevation()+verticalOffset)); 
        // layer.addRenderable(pointPlacemark);
        String[] head = filename.split("/");
        try {
            result = "solid " + head[head.length - 1] + "\n";
            List<Path> gridPaths = gridBox.getPaths();
            //Path OK
            // displayServices.displayPaths(gridPaths, layer, Material.MAGENTA, verticalOffset);
            gridPaths.forEach((p) -> {

                result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
            });
            result += "endsolid " + head[head.length - 1] + "\n";
            java.nio.file.Path path = Paths.get(filename);
            Files.write(path, result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(GridBox3DExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }

    public String exportLargeSTL(String filename, double latScale, double lonScale, double verticalOffset) {
        this.filename = filename;
        double latMin = gridBox.getGrid()[0][0].getLatitude();
        double lonMin = gridBox.getGrid()[0][0].getLongitude();
        String[] head = filename.split("/");
        result = "solid " + head[head.length - 1] + "\n";
        try {
            java.nio.file.Path path = Paths.get(filename);
            Files.write(path, result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(GridBox3DExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        GridBox3D[] boxes = gridBox.split(3);
        for (GridBox3D gb : boxes) {
            result = "";
            List<Path> gridPaths = gb.getPaths();
            gridPaths.forEach((p) -> {
                result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
            });
            try {
                java.nio.file.Path path = Paths.get(filename);
                Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(GridBox3DExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        result += "endsolid " + filename + "\n";
        try {
            java.nio.file.Path path = Paths.get(filename);
            Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(GridBox3DExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }

    public String exportBaseSTL(String stlFilename, String stlBaseFilename) {
        String header;
        java.nio.file.Path path = null;
        try {
            String base = new String(Files.readAllBytes(Paths.get(stlBaseFilename)));
            String body = new String(Files.readAllBytes(Paths.get(stlFilename)));
            String[] fileSTL = body.split("\n");
            header = fileSTL[0] + "\n";
            body = body.replaceFirst(header, "");
            result = header.concat(base);
            result = result.concat(body);
            path = Paths.get(stlFilename);
            Files.write(path, result.getBytes(), StandardOpenOption.CREATE);

        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class
                    .getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return path != null ? path.toString() : null;
    }

    /**
     * Get the value of gridBox
     *
     * @return the value of gridBox
     */
    public GridBox3D getGridBox() {
        return gridBox;
    }

    /**
     * Set the value of gridBox
     *
     * @param gridBox new value of gridBox
     */
    public void setGridBox(GridBox3D gridBox) {
        this.gridBox = gridBox;
    }

}
