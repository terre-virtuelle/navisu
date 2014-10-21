/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.ChartS57Controller;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.parameters.AREA;
import bzh.terrevirtuelle.navisu.topology.model.SurveyZone;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
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
public class AREA_ShapefileLoader
        extends ShapefileLoader
        implements S57ShapeFileLoader {

    private ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;
    private final Color color;
    private final String acronym;
    private double opacity;
    private String objname;

    public AREA_ShapefileLoader(String acronym, Color color, double opacity) {
        this.color = color;
        this.acronym = acronym;
        this.opacity = opacity;
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(color));
        normalAttributes.setOutlineStipplePattern((short) 0xAAAA);
        normalAttributes.setOutlineStippleFactor(5);
        normalAttributes.setEnableLighting(true);
        return normalAttributes;
    }

    @Override
    protected void createPolygon(ShapefileRecord record, ShapeAttributes attrs, RenderableLayer layer) {
        this.record = record;
        SurfacePolygons shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());

        shape.setAttributes(attrs);
        shape.setWindingRule(AVKey.CLOCKWISE);
        shape.setPolygonRingGroups(new int[]{0});

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(attrs);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(color));
        highlightAttributes.setInteriorOpacity(opacity);
        //  shape.setHighlightAttributes(highlightAttributes);
        shape.setHighlightAttributes(null);

        createValues(shape);
        createSurveyZone(record);
        layer.addRenderable(shape);
    }

    protected void createValues(SurfacePolygons shape) {
        entries = record.getAttributes().getEntries();
        entries.stream().forEach((e) -> {
            String label = AREA.ATT.get(acronym) + "\n";
            if (e.getKey().equals("INFORM")) {
                if (e.getValue() != null) {
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
                }
            }
            if (e.getKey().equals("OBJNAM")) {
                if (e.getValue() != null) {
                    objname = (String) e.getValue();
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat(objname));
                }
            }
            if (e.getKey().equals("NATSUR")) {
                if (e.getValue() != null) {
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
                }
            }
        });
    }

    protected void createSurveyZone(ShapefileRecord record) {
        List<double[]> vertices = new ArrayList<>();
        Iterable<double[]> coords = record.getCompoundPointBuffer().getCoords();
        for (double[] c : coords) {
            vertices.add(c);
        }
        // SurveyZone surveyZone = new SurveyZone(vertices);
        ChartS57Controller.getInstance().getSurveyZoneController().add(new SurveyZone(acronym, objname, vertices));
        // System.out.println("poly.contains ? " + surveyZone.contains(48.3302, -4.5960));
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

}
