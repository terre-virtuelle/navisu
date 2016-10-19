/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Wreck;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceIcon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        extends LayerShapefileLoader {

    ShapefileRecord record;
    private final List<Wreck> objects;
    private Set<Map.Entry<String, Object>> entries;

    SurfaceIcon surfaceIcon = null;
    final String IMG_STR = "img/wrecks/danger02.png";
    String imageName = IMG_STR;
    String catwrek = " ";
    String quasou = " ";
    String watlev = " ";

    public WRECKS_CNT_ShapefileLoader() {
        objects = new ArrayList<>();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        try {
            this.record = record;
            entries = record.getAttributes().getEntries();
            imageName = IMG_STR;
            catwrek = "";
            quasou = "";
            watlev = "";
            entries.stream().forEach((e) -> {
                if (e.getValue() != null) {
                    if (e.getKey().contains("CATWRK")) {
                        if (e.getValue().toString().contains("2")) {
                            catwrek = "2";
                        }
                    }
                    if (e.getKey().contains("QUASOU")) {
                        if (e.getValue().toString().contains("2")) {
                            quasou = "2";
                        }
                    }
                    if (e.getKey().contains("WATLEV")) {
                        if (e.getValue().toString().contains("3")) {
                            watlev = "3";
                        }
                    }
                }
                if (watlev.contains("3") && catwrek.contains("2") && quasou.contains("2")) {
                    imageName = "img/wrecks/danger01.png";
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
