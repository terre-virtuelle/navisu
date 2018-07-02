package bzh.terrevirtuelle.navisu.stl.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.stl.StlComponent;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
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

    @Override
    public void viewSTL(String filename) {
        System.out.println("System : " + System.getProperty("user.dir"));
        String path = "stl";
        String command
                = "cd " + path + " \n"
                + System.getProperty("java.home") + "/" + "bin" + "/" + "java -jar STL-viewer.jar " + filename;
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
    public String exportSTL(double latMin, double lonMin, double latScale, double lonScale, String kmlFilename) {
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
                    result += toFacet(triangleString, latMin, lonMin, latScale, lonScale);
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

    private String toFacet(String[] triangleString, double latMin, double lonMin, double latScale, double lonScale) {
        String facet = "";
        Vec3d[] vec3d = new Vec3d[3];
        Vec3d normal;
        for (int i = 0; i < 3; i++) {
            vec3d[i] = toVec3d(triangleString[i], latMin, lonMin, latScale, lonScale);
        }
        Vec3d edge1 = vec3d[1].sub(vec3d[2]);
        Vec3d edge2 = vec3d[2].sub(vec3d[0]);
        normal = Vec3d.cross(edge1, edge2).normalize();

        facet = "facet normal ";
        facet += normal.x + " " + normal.y + " " + normal.z + " \n";
        facet += "outer loop \n";
        facet += "vertex " + vec3d[0].x + " " + vec3d[0].y + " " + vec3d[0].z + " \n";
        facet += "vertex " + vec3d[1].x + " " + vec3d[1].y + " " + vec3d[1].z + " \n";
        facet += "vertex " + vec3d[2].x + " " + vec3d[2].y + " " + vec3d[2].z + " \n";
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
        latM /= latScale;
        lonM /= lonScale;

        double elv = Double.parseDouble(c[2]) * elvScale;

        return new Vec3d(lonM / 1000, latM / 1000, elv);//retour en xyz
    }
}
