/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.ChartS57Controller;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.AREA;
import bzh.terrevirtuelle.navisu.widgets.surveyZone.model.SurveyZone;
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
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class AREA_ShapefileLoader
        extends LayerShapefileLoader
        implements S57ShapeFileLoader {

    protected ShapefileRecord record;
    protected Set<Map.Entry<String, Object>> entries;
    protected final Color color;
    protected final String acronym;
    protected double opacity;
    protected final boolean isDrawInterior;
    protected String objname;
    protected SurfacePolygons shape;

    public AREA_ShapefileLoader(String acronym, Color color, double opacity, boolean isDrawInterior) {
        this.color = color;
        this.acronym = acronym;
        this.opacity = opacity;
        this.isDrawInterior = isDrawInterior;
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawInterior(isDrawInterior);
        normalAttributes.setInteriorOpacity(opacity);
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
        shape = new SurfacePolygons(
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

     //   createValues(shape);
      //  ChartS57Controller.getInstance().getSurveyZoneController().add(new SurveyZone(shape, record));
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
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
                    shape.setValue("OBJNAM", objname);
                }
            }
            if (e.getKey().equals("NATSUR")) {
                if (e.getValue() != null) {
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
                }
            }
            if (e.getKey().equals("STATUS")) {
                if (e.getValue() != null) {
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
                }
            }
        });
        shape.setValue("ACRONYM", acronym);
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }
}
