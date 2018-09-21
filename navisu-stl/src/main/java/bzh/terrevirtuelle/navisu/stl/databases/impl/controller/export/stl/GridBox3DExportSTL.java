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
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import java.io.File;
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
public class GridBox3DExportSTL {

    GeodesyServices geodesyServices;
    private GridBox3D gridBox;
    String filename;
    String result;
    String tmp;

    public GridBox3DExportSTL(GeodesyServices geodesyServices, GridBox3D gridBox) {
        this.geodesyServices = geodesyServices;
        this.gridBox = gridBox;
    }

    public void exportSTL(String filename, double latScale, double lonScale, double verticalOffset) {
        this.filename = filename;
        double latMin = gridBox.getGrid()[0][0].getLatitude();
        double lonMin = gridBox.getGrid()[0][0].getLongitude();
        String[] head = filename.split("/");
        try {
            result = "solid " + head[head.length-1] + "\n";
            List<Path> gridPaths = gridBox.getPaths();
            gridPaths.forEach((p) -> {
                result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
            });
            /*
            gridPaths=gridBox.getSidePathsSouth();
            String normal = "0 -1 0 ";
            gridPaths.forEach((p) -> {
                result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
            });
             */
            //    result += "endsolid " + filename + "\n";
            java.nio.file.Path path = Paths.get(filename);
            Files.write(path, result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(GridBox3DExportSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private String toFacet(Path path,
            double latMin, double lonMin, double latScale, double lonScale,
            double verticalOffset) {
        String facet = "";
        Vec3d[] vec3d = new Vec3d[3];
        Vec3d normal;
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
        normal = Vec3d.cross(edge1, edge2).normalize();

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

        return facet;
    }

    private String toFacet(Path path, String normal,
            double latMin, double lonMin, double latScale, double lonScale,
            double verticalOffset) {
        String facet = "";
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
        String result;
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
