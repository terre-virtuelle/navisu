/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.sedimentology.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.ndf.view.NFD_COLOUR;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class NFD_ShapefileLoader
        extends ShapefileLoader {

    private ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;
    private Color color = Color.RED;
    private double opacity;
    private String objname;
    private SurfacePolygons shape;

    public NFD_ShapefileLoader() {

    }

    @Override
    protected void addRenderablesForPolygons(Shapefile shp, List<Layer> layers) {
        RenderableLayer layer = new RenderableLayer();
        layers.add(layer);

        int recordNumber = 0;
        while (shp.hasNext()) {
            try {
                ShapefileRecord record = shp.nextRecord();
                entries = record.getAttributes().getEntries();
                for (Entry e : entries) {
                    if (e.getKey().equals("TYPEVALE")) {
                        color = NFD_COLOUR.ATT.get(((String) e.getValue()).trim());
                    }
                }
                recordNumber = record.getRecordNumber();

                if (!Shapefile.isPolygonType(record.getShapeType())) {
                    continue;
                }

                ShapeAttributes attrs = this.createPolygonAttributes(color);
                this.createPolygon(record, attrs, layer);

                if (layer.getNumRenderables() > this.numPolygonsPerLayer) {
                    layer = new RenderableLayer();
                    layer.setEnabled(false);
                    layers.add(layer);
                }
            } catch (Exception e) {
                // Logging.logger().warning(Logging.getMessage("SHP.ExceptionAttemptingToConvertShapefileRecord",
                //        recordNumber, e));
                // continue with the remaining records
            }
        }
    }

    @Override
    protected void createPolygon(ShapefileRecord record, ShapeAttributes attrs, RenderableLayer layer) {
        this.record = record;
        entries = record.getAttributes().getEntries();
        shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());

        shape.setAttributes(attrs);
        shape.setWindingRule(AVKey.CLOCKWISE);
        shape.setPolygonRingGroups(new int[]{0});

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(attrs);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(Color.WHITE));
        highlightAttributes.setInteriorOpacity(.5);
        highlightAttributes.setEnableLighting(true);
        shape.setHighlightAttributes(highlightAttributes);
        shape.setValue(AVKey.DISPLAY_NAME, color.toString());
       // shape.setHighlightAttributes(null);

        createValues(shape);
        //  ChartS57Controller.getInstance().getSurveyZoneController().add(new SurveyZone(shape, record));
        layer.addRenderable(shape);
    }

    protected ShapeAttributes createPolygonAttributes(Color color) {

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(true);
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(color));
        normalAttributes.setEnableLighting(true);
        return normalAttributes;
    }

    protected void createValues(SurfacePolygons shape) {
        entries = record.getAttributes().getEntries();
        /*
         entries.stream().forEach((e) -> {
         String label = AREA.ATT.get(acronym) + "\n";
         if (e.getKey().equals("INFORM")) {
         if (e.getValue() != null) {
         shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
         }
         }
         if (e.getKey().equals("OBJNAM")) {
         if (e.getValue() != null) {
         objname = (String)e.getValue();
         shape.setValue(AVKey.DISPLAY_NAME, label.concat((String)e.getValue()));
         shape.setValue("OBJNAM", objname);
         }
         }
         if (e.getKey().equals("NATSUR")) {
         if (e.getValue() != null) {
         shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
         }
         }
         });
         shape.setValue("ACRONYM", acronym);
         */
    }
}
