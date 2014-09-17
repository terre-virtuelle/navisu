/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.UnderwaterAwashRock;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceIcon;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
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
 * @date 17  juin 2014 NaVisu project
 */
public class UWTROC_ShapefileLoader
        extends ShapefileLoader {

    ShapefileRecord record;
    private final List<UnderwaterAwashRock> underwaterAwashRocks;
    private Set<Map.Entry<String, Object>> entries;
    private UnderwaterAwashRock underwaterAwashRock;
    // String imgStr = "img/wrecks/Wreck_Depth_Dangerous.png";
    String imgStr = "img/wrecks/Rock_Awash.png";

    public UWTROC_ShapefileLoader() {
        underwaterAwashRocks = new ArrayList<>();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        SurfaceIcon surfaceIcon = null;
        this.record = record;
        entries = record.getAttributes().getEntries();
     //   System.out.println("entries : " + entries);
        underwaterAwashRock = new UnderwaterAwashRock();
        entries.stream().forEach((e) -> {
            if (e.getValue() != null) {
                if (e.getKey().equals("NATSUR")) {
                    String str = e.getValue().toString();
                    if (str != null) {
                        underwaterAwashRock.setNatureOfSurface(str);
                        if (str.equals("9")) {
                            imgStr = "img/wrecks/Rock_Awash.png";
                        } 
                    }
                } 
                /*else {
                    if (e.getKey().equals("WATLEV")) {
                        String str = e.getValue().toString();
                        if ((str.equals("3") || str.equals("5")&&wreck.getCategoryOfWreck().contains("2"))) {
                            imgStr = "img/wrecks/ISODGR51.png";
                            wreck.setWaterLevelEffect(((Long) e.getValue()).toString());
                        }
                    }
                }
                        */
            }

        });
        try {
            surfaceIcon = new SurfaceIcon(ImageIO.read(new File(imgStr)), new LatLon(LatLon.fromDegrees(latDegrees, lonDegrees)));
            surfaceIcon.setMaxSize(100.0);
        } catch (IOException ex) {
            Logger.getLogger(UWTROC_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return surfaceIcon;
    }

    public List<UnderwaterAwashRock> getUnderwaterAwashRocks() {
        return underwaterAwashRocks;
    }

}
