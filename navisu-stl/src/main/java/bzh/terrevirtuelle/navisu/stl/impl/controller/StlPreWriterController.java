/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.controller;

import bzh.terrevirtuelle.navisu.stl.impl.controller.loader.RefLoader;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Sep 18, 2017
 */
public class StlPreWriterController
        extends StlController {

    String title;

    public StlPreWriterController(Path outPathname, String title,
            int tilesCount, int index,
            List<? extends Position> positions,
            double tileSideX, double tileSideY,
            double spaceX, double spaceY,
            double bottom,
            double magnification,
            double offset) {
        super(outPathname,
                tilesCount, index,
                positions,
                tileSideX, tileSideY,
                spaceX, spaceY,
                bottom,
                magnification,
                offset);
        this.title = title;
    }

    public void compute() {
        writeInitOutFile();
        //  writeRef(outPathname.toString(), positions, tileSideX, tileSideY);// repere XYZ
        writePositionOrientation();
    }

    private void writeInitOutFile() {
        String txt;
        lines = new ArrayList<>();
        txt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"
                + "<!DOCTYPE X3D PUBLIC \"ISO//Web3D//DTD X3D 3.0//EN\" "
                + "\"http://www.web3d.org/specifications/x3d-3.0.dtd\">\n"
                + "<X3D profile='Immersive' version='3.0'  "
                + "xmlns:xsd='http://www.w3.org/2001/XMLSchema-instance'"
                + " xsd:noNamespaceSchemaLocation =' "
                + "http://www.web3d.org/specifications/x3d-3.0.xsd '> \n"
                + "<head>\n"
                + "<meta name='Title' content='" + title + "'/> \n"
                + "<meta name='TilesCount' content='" + tilesCount + "'/>\n"
                + "<meta name='Index' content='" + index + "'/>\n"
                + "<meta name='Author' content='" + System.getProperty("user.name") + "'/>\n"
                + "<meta name='Created' content='" + new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss").format(new Date()) + "'/>\n"
                + "<meta name='Generator' content='NaVisu'/>\n"
                + "<meta name='Site' content='http://www.navisu.org/'/>\n"
                + "<meta name='Github' content='https://github.com/terre-virtuelle/navisu'/>\n"
                + "<meta name='Pos0' content='Lat=" + positions.get(0).getLatitude()
                + " Lon=" + positions.get(0).getLongitude() + "'/>\n"
                + "<meta name='Pos1' content='Lat=" + positions.get(1).getLatitude()
                + " Lon=" + positions.get(1).getLongitude() + "'/>\n"
                + "<meta name='Pos2' content='Lat=" + positions.get(2).getLatitude()
                + " Lon=" + positions.get(2).getLongitude() + "'/>\n"
                + "<meta name='Pos3' content='Lat=" + positions.get(3).getLatitude()
                + " Lon=" + positions.get(3).getLongitude() + "'/>\n"
                + "</head>\n"
                + "<Scene>\n";
        lines.add(txt);
        try {
            Files.write(outPathname,
                    lines,
                    charset,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(StlPreWriterController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void writeRef(String outFilename, List<? extends Position> positions,
            double tileSideX, double tileSideY) {
        RefLoader l = new RefLoader(positions, tileSideX, tileSideY);
        write(l.compute());
    }

    private void writePositionOrientation() {
        lines = new ArrayList<>();
        String txt = "<Transform rotation='0 1 0 1.57058' "
                + "translation='200.0 0.0 200.0' "
                + " scale='1.000900 1.000900 1.000900'> \n"
                + "<Viewpoint  position='-100.0 500.0 100'  "
                + "orientation='1 0 0 -1.57'  "
                + "fieldOfView='.5'/>\n"
                + "<Transform rotation='0 1 0 -3.14'>\n";

        lines.add(txt);
        try {
            Files.write(outPathname, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlPreWriterController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
