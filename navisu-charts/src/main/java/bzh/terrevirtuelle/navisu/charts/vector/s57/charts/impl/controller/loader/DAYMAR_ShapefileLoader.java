 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Daymark;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPoint;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class DAYMAR_ShapefileLoader
        extends ShapefileLoader {

    private Daymark object;
    private final List<Daymark> objects;
    private Set<Map.Entry<String, Object>> entries;
    private String label;
    private final String acronym = "DAYMAR";
    private final String marsys;
    private PointPlacemarkAttributes attrs;

    public DAYMAR_ShapefileLoader(String marsys) {
        this.marsys = marsys;
        objects = new ArrayList<>();
    }

    @Override
    protected void addRenderablesForPoints(Shapefile shp, RenderableLayer layer) {

        while (shp.hasNext()) {
            ShapefileRecord record = shp.nextRecord();

            if (!Shapefile.isPointType(record.getShapeType())) {
                continue;
            }
            attrs = this.createPointAttributes(record);
            double[] point = ((ShapefileRecordPoint) record).getPoint();
            layer.addRenderable(this.createPoint(record, point[1], point[0], attrs));
        }
    }

    @Override
    protected PointPlacemarkAttributes createPointAttributes(ShapefileRecord record) {
        PointPlacemarkAttributes normalAttributes = new PointPlacemarkAttributes();
        return normalAttributes;
    }

    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {

        object = new Daymark();
        objects.add(object);

        entries = record.getAttributes().getEntries();
        entries.stream().forEach((Map.Entry<String, Object> e) -> {
            if (e.getKey().equals("RCID")) {
                object.setId((Long) e.getValue());
            }
            if (e.getKey().equals("TOPSHP")) {
                Object obj = e.getValue();
                String shp = "0";
                if (obj != null) {
                    shp = ((Long) e.getValue()).toString();
                }
                object.setShape(shp);
            }

            if (e.getKey().equals("CATSPM")) {
                if (e.getValue() != null) {
                    object.setCategoryOfMark((String) e.getValue());
                } else {
                    object.setCategoryOfMark("0");
                }
            }

            if (e.getKey().equals("OBJNAM")) {
                if (e.getValue() != null) {
                    object.setObjectName((String) e.getValue());
                } else {
                    object.setObjectName(" ");
                }
            }

            if (e.getKey().equals("COLOUR")) {
                if (e.getValue() != null) {
                    object.setColour((String) e.getValue());
                } else {
                    object.setColour("0");
                }
            }
            if (e.getKey().equals("COLPAT")) {
                Object obj = e.getValue();
                String colpat = "0";
                if (obj != null) {
                    colpat = (String) e.getValue();
                }
                object.setColourPattern(colpat);
            }
            if (e.getKey().equals("NATCON")) {
                Object obj = e.getValue();
                String natcon = "0";
                if (obj != null) {
                    natcon = (String) e.getValue();
                }
                object.setNatureOfConstruction(natcon);
            }

        });

        attrs.setImageAddress("img/daymarks_" + marsys + "/" + acronym + "_"
                + object.getShape() + "_"
                + object.getCategoryOfMark() + "_"
                + object.getColour() + "_"
                + object.getColourPattern() + "_"
                + object.getNatureOfConstruction() + "_"
                + marsys
                + ".png");
        attrs.setImageOffset(Offset.BOTTOM_CENTER);
        attrs.setScale(0.7);

        label = acronym + "_"
                + object.getShape() + "_"
                + object.getCategoryOfMark() + "_"
                + object.getColour() + "_"
                + object.getColourPattern() + "_"
                + object.getNatureOfConstruction() + "_"
                + marsys
                + ".png";

        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setValue(AVKey.DISPLAY_NAME, label);
        placemark.setAttributes(attrs);
        return placemark;
    }
}
