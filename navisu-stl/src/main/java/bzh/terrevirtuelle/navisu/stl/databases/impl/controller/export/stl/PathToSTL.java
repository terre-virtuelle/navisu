/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import gov.nasa.worldwind.geom.Position;
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
 * @date May 3, 2019
 */
public class PathToSTL {

    protected GeodesyServices geodesyServices;
    protected String filename;
    String result = null;

    public PathToSTL(GeodesyServices geodesyServices) {
        this.geodesyServices = geodesyServices;
    }

    public String exportSTL(List<Path> paths,
            String filename,
            String solidname,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double verticalOffset) {

        this.filename = filename;
        String[] head = filename.split("/");
        try {
            result = "solid " + solidname + "\n";
            List<Path> gridPaths = paths;
            gridPaths.forEach((p) -> {
                result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
            });
            result += "endsolid " + solidname + "\n";
            java.nio.file.Path path = Paths.get(filename);
            Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(GridBox3DExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return result;
    }

    public String toFacet(Path path,
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

    public String exportSTL(List<Path> paths,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double verticalOffset) {

        paths.forEach((p) -> {
            result += toFacet(p, latMin, lonMin, latScale, lonScale, verticalOffset);
        });
        return result;
    }

    public Vec3d toVec3d(Position position, double latMin, double lonMin, double latScale, double lonScale) {

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

}
