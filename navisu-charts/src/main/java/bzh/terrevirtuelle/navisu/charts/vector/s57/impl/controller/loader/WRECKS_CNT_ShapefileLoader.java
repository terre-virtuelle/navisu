/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceIcon;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class WRECKS_CNT_ShapefileLoader
        extends ShapefileLoader {

    ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;

    SurfaceIcon surfaceIcon = null;
    final String IMG_STR = "img/wrecks/danger02.png";
    String imageName = IMG_STR;

    public WRECKS_CNT_ShapefileLoader() {
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        try {

            this.record = record;
            entries = record.getAttributes().getEntries();
            entries.stream().forEach((e) -> {
                if (e.getValue() != null) {
                  //  if (e.getKey().equals("CATWRK")) {
                   //     if (e.getValue().toString().equals("2")) {
                   //         imageName = "img/wrecks/danger01.png";
                   //     }
                  //  }
                }
            });

            surfaceIcon = new SurfaceIcon(ImageIO.read(new File(imageName)), new LatLon(LatLon.fromDegrees(latDegrees, lonDegrees)));
        } catch (IOException ex) {
            Logger.getLogger(WRECKS_CNT_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        surfaceIcon.setMaxSize(100.0);
        surfaceIcon.setMinSize(40.0);
        return surfaceIcon;
    }
}
