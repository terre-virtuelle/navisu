/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.EXPSOU;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.QUASOU;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Sounding;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceText;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class SOUNDG_ShapefileLoader
        extends LayerShapefileLoader {

    ShapefileRecord record;
    private final List<Sounding> soundings;
    private Set<Map.Entry<String, Object>> entries;
    private Sounding sounding;

    public SOUNDG_ShapefileLoader() {
        soundings = new ArrayList<>();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        SurfaceText surfaceText;
        this.record = record;
        entries = record.getAttributes().getEntries();
        sounding = new Sounding();
        sounding.setLat(latDegrees);
        sounding.setLon(lonDegrees);
        entries.stream().forEach((e) -> {
            if (e.getValue() != null) {
                if (e.getKey().equals("DEPTH")) {
                    sounding.setDepth((double)e.getValue());
                }
                if (e.getKey().equals("EXPSOU")) {
                    sounding.setExpositionOfSounding(e.getValue().toString());
                }
                if (e.getKey().equals("QUASOU")) {
                    sounding.setQualityOfSoundingMeasurement(e.getValue().toString());
                }
            }
        }
        );
        surfaceText = new SurfaceText(Double.toString(sounding.getDepth()), Position.fromDegrees(latDegrees, lonDegrees, 0));
        surfaceText.setColor(Color.black);
        surfaceText.setTextSize(25.0);
        String label;
       
            label = String.format("%-29s\n"
                    + "%-29s"
                    + "%-29s"
                    + "%-29s"
                    + "%-29s"
                    + "%-29s",
                    "SOUNDING",
                    "Lat : " + sounding.getLat() + "\n",
                    "Lon : " + sounding.getLon() + "\n",
                    "Depth : " + sounding.getDepth() +"\n",
                    "EXPSOU : " + EXPSOU.ATT.get(sounding.getExpositionOfSounding()) + "\n",
                    "QUASOU : " + QUASOU.ATT.get(sounding.getQualityOfSoundingMeasurement()) + "\n"
            );
           
        
        surfaceText.setValue(AVKey.DISPLAY_NAME, label);

        return surfaceText;
    }

    public List<Sounding> getSounding() {
        return soundings;
    }

}
