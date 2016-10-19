/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Obstruction;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Wreck;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceText;
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
public class OBSTRN_ShapefileLoader
        extends LayerShapefileLoader {

    ShapefileRecord record;
    private final List<Wreck> obstructions;
    private Set<Map.Entry<String, Object>> entries;
    private Obstruction obstruction;

    public OBSTRN_ShapefileLoader() {
        obstructions = new ArrayList<>();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        SurfaceText surfaceText = null;
        this.record = record;
        entries = record.getAttributes().getEntries();
        obstruction = new Obstruction();
        obstruction.setLatitude(latDegrees);
        obstruction.setLongitude(lonDegrees);
        entries.stream().forEach((e) -> {
            if (e.getValue() != null) {
                if (e.getKey().equals("CATOBS")) {
                    obstruction.setCategoryOfObstruction(e.getValue().toString());
                }
                if (e.getKey().equals("EXPSOU")) {
                    obstruction.setExpositionOfSounding(e.getValue().toString());
                }
                if (e.getKey().equals("QUASOU")) {
                    obstruction.setQualityOfSoundingMeasurement(e.getValue().toString());
                }
                if (e.getKey().equals("TECSOU")) {
                    obstruction.setTechniqueOfSoundingMeasurement(e.getValue().toString());
                }
                if (e.getKey().equals("VALSOU")) {
                    obstruction.setValueOfSounding(e.getValue().toString());
                }
                if (e.getKey().equals("WATLEV")) {
                    obstruction.setWaterLevelEffect(((Long) e.getValue()).toString());
                }
                if (e.getKey().equals("QUALTY")) {
                    obstruction.setQualityOfSoundingMeasurement(((Long) e.getValue()).toString());
                }
            }
        });
        if (obstruction.getValueOfSounding() != null) {
            surfaceText = new SurfaceText(obstruction.getValueOfSounding(), Position.fromDegrees(latDegrees, lonDegrees, 0));
        } else {
            surfaceText = new SurfaceText("", Position.fromDegrees(latDegrees, lonDegrees, 0));
        }
        surfaceText.setColor(Color.black);
        surfaceText.setTextSize(20.0);
        String tecsou = obstruction.getTechniqueOfSoundingMeasurement();
        String label;

        if (tecsou == null) {
            label = "Obstruction \n"
                    + "Lat : " + new Float(obstruction.getLatitude()).toString() + "\n "
                    + "Lon : " + new Float(obstruction.getLongitude()).toString() + "\n"
                    + "CATWRK : " + obstruction.getQualityOfSoundingMeasurement() + "\n"
                    + "EXPSOU : " + obstruction.getExpositionOfSounding() + "\n"
                    + "QUASOU : " + obstruction.getQualityOfSoundingMeasurement() + "\n"
                    + "VALSOU : " + obstruction.getValueOfSounding() + " m \n"
                    + "WATLEV : " + obstruction.getWaterLevelEffect() + "\n";
        } else {
            label = "Obstruction \n"
                    + "Lat : " + new Float(obstruction.getLatitude()).toString() + "\n "
                    + "Lon : " + new Float(obstruction.getLongitude()).toString() + "\n"
                    + "CATWRK : " + obstruction.getQualityOfSoundingMeasurement() + "\n"
                    + "EXPSOU : " + obstruction.getExpositionOfSounding() + "\n"
                    + "QUASOU : " + obstruction.getQualityOfSoundingMeasurement() + "\n"
                    + "TECSOU : " + tecsou + "\n"
                    + "VALSOU : " + obstruction.getValueOfSounding() + " m \n"
                    + "WATLEV : " + obstruction.getWaterLevelEffect() + "\n";
        }
        surfaceText.setValue(AVKey.DISPLAY_NAME, label);
        return surfaceText;
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(true);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(new Color(81, 161, 5)));
        normalAttributes.setInteriorMaterial(new Material(new Color(81, 161, 5)));
        normalAttributes.setEnableLighting(true);

        return normalAttributes;
    }
}
