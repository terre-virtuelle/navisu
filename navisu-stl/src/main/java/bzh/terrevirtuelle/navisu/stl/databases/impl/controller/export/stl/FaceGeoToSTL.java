/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.geometry.FaceGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
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
public class FaceGeoToSTL {

    protected GeodesyServices geodesyServices;
    protected String filename;
    protected String result = null;

    public FaceGeoToSTL(GeodesyServices geodesyServices) {
        this.geodesyServices = geodesyServices;
    }

    public String exportSTL(List<FaceGeo> faces,
            String solidname,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double tileSideZ, double maxdepth) {
        result = "solid " + solidname + "\n";
        faces.forEach((f) -> {
            result += toFacet(f, latMin, lonMin, latScale, lonScale, tileSideZ, maxdepth);
        });
        result += "endsolid " + solidname + "\n";
        return result;
    }

    public void write(String filename, String content) {
        try {
            java.nio.file.Path path = Paths.get(filename);
            Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(GridBox3DExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public String toFacet(FaceGeo face,
            double latMin, double lonMin, double latScale, double lonScale,
            double tileSideZ, double verticalOffset) {
        String facet;
        Vec3d[] vec3d = new Vec3d[3];
        Vec3d normal;
        int i = 0;
        List<Point3DGeo> positions = face.getVertices();
        for (Point3DGeo pp : positions) {
            if (i < 3) {
                vec3d[i] = toVec3d(pp, latMin, lonMin, latScale, lonScale, verticalOffset);
            }
            i++;
        }
        Vec3d edge1 = vec3d[1].sub(vec3d[2]);
        Vec3d edge2 = vec3d[2].sub(vec3d[0]);
        normal = Vec3d.cross(edge1, edge2);

        double z0 = vec3d[0].z + tileSideZ;
        double z1 = vec3d[1].z + tileSideZ;
        double z2 = vec3d[2].z + tileSideZ;
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

    public Vec3d toVec3d(Point3DGeo position,
            double latMin, double lonMin,
            double latScale, double lonScale, double verticalOffset) {

        double elvScale = (latScale + lonScale) / 2;

        double lon = position.getLongitude();
        double lat = position.getLatitude();
        double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
        double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
        latM *= latScale;
        lonM *= lonScale;
        double elv = (position.getElevation() + verticalOffset) * elvScale;

        return new Vec3d(lonM, latM, elv);//retour en xyz
    }
}
