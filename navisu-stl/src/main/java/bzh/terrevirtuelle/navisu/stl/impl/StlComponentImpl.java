package bzh.terrevirtuelle.navisu.stl.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.stl.StlComponent;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class StlComponentImpl
        implements StlComponent, StlComponentServices, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    GeodesyServices geodesyServices;
    String command;

    @Override
    public void viewSTL(String filename) {
        command = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java -jar stl/STL-viewer.jar " + filename;
        if (OS.isWindows()) {
            command = command.replace("Program Files", "\\\"Program Files\"");
        }
        guiAgentServices.getJobsManager().newJob("Viewing STL objects", (progressHandle) -> {
            try {
                Proc.BUILDER.create()
                        .setCmd(command)
                        .execSh();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        });
    }

    @Override
    public String exportSTL(double latMin, double lonMin, double latScale, double lonScale,
            String kmlFilename, double verticalOffset) {
        String stlFilename = "";
        try {
            String content = new String(Files.readAllBytes(Paths.get(kmlFilename)));
            stlFilename = kmlFilename;
            stlFilename = stlFilename.replace(".kml", "");
            stlFilename = stlFilename.replace("privateData/kml/", "");
            String result = "solid " + stlFilename + "\n";

            String[] contentTab = content.split("</Placemark>");
            for (String s : contentTab) {
                if (s.contains("coordinates")) {
                    s = s.replace("<coordinates>", "é");
                    s = s.replace("</coordinates>", "é");
                    String[] coord = s.split("é");
                    String[] triangleString = coord[1].split("\\s+");
                    result += toFacet(triangleString, latMin, lonMin, latScale, lonScale, verticalOffset);
                }
            }
            result += "endsolid " + stlFilename + "\n";
            java.nio.file.Path path = Paths.get("privateData/stl/" + stlFilename + ".stl");
            Files.write(path, result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return "privateData/stl/" + stlFilename + ".stl";
    }

    @Override
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
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return path != null ? path.toString() : null;
    }

    public String toFacet(String[] triangleString,
            double latMin, double lonMin, double latScale, double lonScale,
            double verticalOffset) {
        String facet = "";
        Vec3d[] vec3d = new Vec3d[3];
        Vec3d normal;
        for (int i = 0; i < 3; i++) {
            vec3d[i] = toVec3d(triangleString[i], latMin, lonMin, latScale, lonScale);
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

    private Vec3d toVec3d(String triangleString, double latMin, double lonMin, double latScale, double lonScale) {
        String[] c = triangleString.split(",");
        double elvScale = (latScale + lonScale) / 2;
        // System.out.println("triangleString : " + triangleString);

        double lon = Double.parseDouble(c[0]);
        double lat = Double.parseDouble(c[1]);
        double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
        double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
        latM *= latScale;
        lonM *= lonScale;

        double elv = Double.parseDouble(c[2]) * elvScale;

        return new Vec3d(lonM, latM, elv);//retour en xyz
    }

    @Override
    public String toFacet(Path path,
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

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }
}
