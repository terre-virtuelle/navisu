/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
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
public class GridBox3DExportToSTL {

    protected static final Logger LOGGER = Logger.getLogger(GridBox3DExportToSTL.class.getName());
    protected GeodesyServices geodesyServices;
    protected DisplayServices displayServices; //DEBUG
    protected RenderableLayer layer;           //DEBUG

    private GridBox3D gridBox;
    String filename;
    String result = "";
    String tmp;

    public GridBox3DExportToSTL(GeodesyServices geodesyServices,
            DisplayServices displayServices, RenderableLayer layer,
            GridBox3D gridBox) {
        this.geodesyServices = geodesyServices;
        this.displayServices = displayServices;
        this.layer = layer;
        this.gridBox = gridBox;
    }

    public GridBox3DExportToSTL() {
    }

    public GridBox3DExportToSTL(GeodesyServices geodesyServices) {
        this.geodesyServices = geodesyServices;
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

    public String exportSTL(List<Path> paths,
            String filename,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double verticalOffset) {

        this.filename = filename;
        String[] head = filename.split("/");
        try {
            result = "solid " + head[head.length - 1] + "\n";
            List<Path> gridPaths = paths;
            gridPaths.forEach((p) -> {
                result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
            });
            result += "endsolid " + filename + "\n";
            java.nio.file.Path path = Paths.get(filename);
            Files.write(path, result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(GridBox3DExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }

    public String exportSTL(List<Path> paths,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double verticalOffset) {

        paths.forEach((p) -> {
            result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
        });
        return result;
    }

    private String toFacet(Path path,
            double latMin, double lonMin, double latScale, double lonScale,
            double verticalOffset) {
        String facet;
        Vec3d[] vec3d = new Vec3d[3];
        Vec3d normal = null;
        int i = 0;
        Iterable<? extends Position> positions = path.getPositions();
        for (Position pp : positions) {
            if (i < 3) {
                vec3d[i] = toVec3d(pp, latMin, lonMin, latScale, lonScale);
            }
            i++;
        }
        Vec3d edge1 = vec3d[1].sub(vec3d[2]);
        Vec3d edge2 = vec3d[2].sub(vec3d[0]);
       // System.out.println("edge : " + edge1 + " " + edge2);
        normal = Vec3d.cross(edge1, edge2);

        double z0 = vec3d[0].z + verticalOffset;
        double z1 = vec3d[1].z + verticalOffset;
        double z2 = vec3d[2].z + verticalOffset;

        facet = "facet normal ";
        facet += normal.x + " " + normal.y + " " + normal.z + " \n";
        facet += "outer loop \n";
        facet += "vertex " + vec3d[0].x + " " + vec3d[0].y + " " + z0 + " \n";
        facet += "vertex " + vec3d[1].x + " " + vec3d[1].y + " " + z1 + " \n";
        facet += "vertex " + vec3d[2].x + " " + vec3d[2].y + " " + z2 + " \n";
        facet += "endloop \n";
        facet += "endfacet \n";
        if (facet.contains("NaN")) {
            System.out.println("facet.contains(\"NaN\")" + edge1 + " " + edge2);
            facet = "";
        }
        return facet;
    }

    private String toFacet(Path path, String normal,
            double latMin, double lonMin, double latScale, double lonScale,
            double verticalOffset) {
        String facet;
        Vec3d[] vec3d = new Vec3d[3];

        int i = 0;
        Iterable<? extends Position> positions = path.getPositions();
        for (Position pp : positions) {
            if (i < 3) {
                vec3d[i] = toVec3d(pp, latMin, lonMin, latScale, lonScale);
            }
            i++;
        }

        double z0 = vec3d[0].z + verticalOffset;
        double z1 = vec3d[1].z + verticalOffset;
        double z2 = vec3d[2].z + verticalOffset;

        facet = "facet normal ";
        facet += normal + " \n";
        facet += "outer loop \n";
        facet += "vertex " + vec3d[0].x + " " + vec3d[0].y + " " + z0 + " \n";
        facet += "vertex " + vec3d[1].x + " " + vec3d[1].y + " " + z1 + " \n";
        facet += "vertex " + vec3d[2].x + " " + vec3d[2].y + " " + z2 + " \n";
        facet += "endloop \n";
        facet += "endfacet \n";

        return facet;
    }

    private Vec3d toVec3d(Position position, double latMin, double lonMin, double latScale, double lonScale) {

        double elvScale = (latScale + lonScale) / 2;

        double lon = position.getLongitude().getDegrees();
        double lat = position.getLatitude().getDegrees();
        double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
        double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
        latM *= latScale;
        lonM *= lonScale;

        double elv = position.getElevation() * elvScale;

        return new Vec3d(lonM, latM, elv);//retour en xyz
    }

    public String exportBaseSTL(String stlFilename, String stlBaseFilename) {
        String header;
        // String result;
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
