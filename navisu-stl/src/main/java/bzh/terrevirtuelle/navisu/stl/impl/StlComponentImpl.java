package bzh.terrevirtuelle.navisu.stl.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.stl.StlComponent;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.MeshExportToSTL;
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
    public void exportBaseSTL(String stlFilename, String stlBaseFilename) {
        try {
            String result = new String(Files.readAllBytes(Paths.get(stlBaseFilename)));
            Files.write(Paths.get(stlFilename), result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public void exportRotateBaseSTL(String outFilename, String inFilename, double rot) {
        String stlResult = "";
        String content = null;
        double angle = Math.toRadians(rot);
        try {
            content = new String(Files.readAllBytes(Paths.get(inFilename)));
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        if (content != null) {
            // Transform vertex coordinates by rotation
            String[] resultTab = content.split("\n");
            for (String s : resultTab) {
                if (s.contains("facet") || s.contains("loop") || s.contains("solid")) {
                    stlResult += s + "\n";
                } else {
                    if (s.contains("vertex")) {
                        String[] c = s.trim().split("\\s+");
                        double lon = Double.parseDouble(c[1]);
                        double lat = Double.parseDouble(c[2]);
                        double elv = Double.parseDouble(c[3]);

                        double x = lon * Math.cos(angle) - lat * Math.sin(angle);
                        double y = lon * Math.sin(angle) + lat * Math.cos(angle);

                        stlResult += "vertex " + x + " " + y + " " + elv + "\n";
                    }
                }
            }

            //Insert base file in stlFile.stl
            try {
                System.out.println("write base");
                Files.write(Paths.get(outFilename), stlResult.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(MeshExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }

        }
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
