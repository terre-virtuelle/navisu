/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Wreck;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceIcon;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
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
public class OBSTRN_ShapefileLoader
        extends ShapefileLoader {

    ShapefileRecord record;
    private final List<Wreck> wrecks;
    private Set<Map.Entry<String, Object>> entries;
    private Wreck wreck;
    String imgStr = "img/wrecks/Wreck.png";

    public OBSTRN_ShapefileLoader() {
        wrecks = new ArrayList<>();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        SurfaceIcon surfaceIcon = null;
        this.record = record;
        entries = record.getAttributes().getEntries();
     //   System.out.println("entries : " + entries);
        wreck = new Wreck();
        entries.stream().forEach((e) -> {
            if (e.getValue() != null) {
                if (e.getKey().equals("CATWRK")) {
                    String str = e.getValue().toString();
                    if (str != null) {
                        wreck.setCategoryOfWreck(str);
                        if (str.equals("1")) {
                            imgStr = "img/wrecks/Wreck_Depth_NonDangerous.png";
                        } else {
                            if (str.equals("2") || str.equals("3")) {
                                imgStr = "img/wrecks/Wreck_Depth_Dangerous.png";
                            } else {
                                if (str.equals("4") || str.equals("5")) {
                                    imgStr = "img/wrecks/Wreck.png";
                                }
                            }
                        }
                    }
                } else {
                    if (e.getKey().equals("WATLEV")) {
                        String str = e.getValue().toString();
                        if ((str.equals("3") || str.equals("5")&&wreck.getCategoryOfWreck().contains("2"))) {
                            imgStr = "img/wrecks/ISODGR51.png";
                            wreck.setWaterLevelEffect(((Long) e.getValue()).toString());
                        }
                    }

                }
            }

        });
        try {
            surfaceIcon = new SurfaceIcon(ImageIO.read(new File(imgStr)), new LatLon(LatLon.fromDegrees(latDegrees, lonDegrees)));
            surfaceIcon.setMaxSize(100.0);
        } catch (IOException ex) {
            Logger.getLogger(OBSTRN_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("WRECKS " + record.getAttributes().getEntries());
        return surfaceIcon;
    }

    public List<Wreck> getWrecks() {
        return wrecks;
    }

}
