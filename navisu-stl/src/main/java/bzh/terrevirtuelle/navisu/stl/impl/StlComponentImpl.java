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
    public String exportBaseSTL(String stlFilename, String stlBaseFilename) {
     
        java.nio.file.Path path = null;
        try {
            String result = new String(Files.readAllBytes(Paths.get(stlBaseFilename)));
         
            path = Paths.get(stlFilename);
            Files.write(path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return path != null ? path.toString() : null;
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
