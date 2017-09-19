/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.BaseLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.SeaLoader;
import gov.nasa.worldwind.geom.Position;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Sep 18, 2017
 */
public class StlPostWriterController
        extends StlController {

    public StlPostWriterController(Path outPathname) {
        super(outPathname);
    }

    

    public void compute() {
        //    writeSea();
        writeBase();
        writeEndOutFile();
    }

    private void writeBase() {
        write(new BaseLoader().compute());
    }

    private void writeEndOutFile() {
        lines = new ArrayList<>();
        String txt = "</Transform>\n"
                + "</Transform>\n"
                + "</Scene>\n"
                + "</X3D> ";
        lines.add(txt);
        try {
            Files.write(outPathname, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlPostWriterController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
