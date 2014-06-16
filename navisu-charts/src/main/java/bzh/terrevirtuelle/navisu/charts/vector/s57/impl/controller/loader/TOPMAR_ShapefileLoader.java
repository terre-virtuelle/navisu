/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Topmark;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class TOPMAR_ShapefileLoader
        extends ShapefileLoader {

    private final List<Topmark> dataList;

    private Set<Map.Entry<String, Object>> entries;
    private Topmark data;

    public TOPMAR_ShapefileLoader() {
        dataList = new ArrayList<>();
    }

    @Override
    protected PointPlacemarkAttributes createPointAttributes(ShapefileRecord record) {
        PointPlacemarkAttributes normalAttributes = new PointPlacemarkAttributes();
        return normalAttributes;
    }

    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {

        entries = record.getAttributes().getEntries();
        data = new Topmark();

        dataList.add(data);
        data.setLat(latDegrees);
        data.setLon(lonDegrees);
        entries.stream().forEach((e) -> {
            if (e.getKey().equals("RCID")) {
                data.setId((Long) e.getValue());
            } else {
                if (e.getKey().equals("COLOUR")) {
                    data.setColour((String) e.getValue());
                } else {
                    if (e.getKey().equals("COLPAT")) {
                        data.setColourPattern((String) e.getValue());
                    } 
                }
            }
        });

        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setAttributes(attrs);

        return placemark;
    }

    public List<Topmark> getTopmarks() {
        return dataList;
    }

}
