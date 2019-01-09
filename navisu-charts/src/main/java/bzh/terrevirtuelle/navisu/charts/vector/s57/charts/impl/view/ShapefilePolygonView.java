/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.awt.Color;

/**
 *
 * @author serge
 */
public abstract class ShapefilePolygonView
        extends ShapefileView {

    protected SurfacePolygons shape;
    protected double height;
    protected ShapeAttributes capAttrs = new BasicShapeAttributes();
    protected ShapeAttributes sideAttrs = new BasicShapeAttributes();
    double val2;
    double val1;

    public ShapefilePolygonView() {

    }

    protected void createPolygon(RenderableLayer layer, ShapefileRecord record,
            boolean isHeight, double magnify, double maxHeight) {
        this.record = record;
      //  System.out.println("isHeight : " + isHeight);
        if (isHeight == true) {
            if (record.getAttributes() != null) {
                entries = record.getAttributes().getEntries();
                entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                    if (e.getKey().equalsIgnoreCase("drval1")) {
                        height = magnify * (maxHeight - (Double) e.getValue());
                        extrudePolygon(layer, record, height);
                    }
                });
            }
        } else {
            if (record.getAttributes() != null) {
                entries = record.getAttributes().getEntries();
                entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                    if (e.getKey().equalsIgnoreCase("drval1")) {
                        val1 = (Double) e.getValue();
                    }
                   
                    color = SHOM_LOW_BATHYMETRY_CLUT.getColor(val1);
                    if (e.getKey().equalsIgnoreCase("drval2")) {
                        val2 = (Double) e.getValue();
                    }
                });
            }
            surface(layer, record);
            shape.setValue("drval1", val1);
            shape.setValue("drval2", val2);
            shape.setValue(AVKey.DISPLAY_NAME,
                    "[" + Double.toString(val1) + ", " + Double.toString(val2) + "]");
            setPolygonAttributes(color);
            
        }
    }

    private void extrudePolygon(RenderableLayer layer, ShapefileRecord record, double height) {
        if (height != 0) {  // create extruded polygons
            ExtrudedPolygon ep = new ExtrudedPolygon(height);
            setExtrudedPolygonAttributes(ep, color);
            layer.addRenderable(ep);
            for (int i = 0; i < record.getNumberOfParts(); i++) {
                VecBuffer buffer = record.getCompoundPointBuffer().subBuffer(i);
                if (WWMath.computeWindingOrderOfLocations(buffer.getLocations()).equals(AVKey.CLOCKWISE)) {
                    if (!ep.getOuterBoundary().iterator().hasNext()) {
                        ep.setOuterBoundary(buffer.getLocations());
                    } else {
                        ep = new ExtrudedPolygon();
                        ep.setOuterBoundary(record.getCompoundPointBuffer().getLocations());
                        ep.setValue(AVKey.DISPLAY_NAME, "[" + Double.toString(height) + "]");
                        layer.addRenderable(ep);
                    }
                } else {
                    ep.addInnerBoundary(buffer.getLocations());
                }
            }
        } else {
            shape = new SurfacePolygons(
                    Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                    record.getCompoundPointBuffer());
            shape.setWindingRule(AVKey.CLOCKWISE);
            shape.setPolygonRingGroups(new int[]{0});
        }
    }

    private void surface(RenderableLayer layer, ShapefileRecord record) {
        shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        shape.setWindingRule(AVKey.CLOCKWISE);
        shape.setPolygonRingGroups(new int[]{0});
        layer.addRenderable(shape);
    }

    protected void setPolygonAttributes(Color col) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setDrawInterior(true);
        normAttributes.setInteriorMaterial(new Material(col));
        normAttributes.setDrawOutline(true);
        normAttributes.setOutlineMaterial(new Material(Color.BLACK));
        //   normAttributes.setEnableLighting(true);
        shape.setAttributes(normAttributes);

        //  shape.setHighlightAttributes(highlightAttributes);
    }

    protected void setExtrudedPolygonAttributes(ExtrudedPolygon ep, Color color) {
        capAttrs.setDrawOutline(true);
        capAttrs.setDrawInterior(true);
        capAttrs.setOutlineMaterial(Material.BLUE);
        capAttrs.setInteriorMaterial(new Material(color));
        //  capAttrs.setEnableLighting(true);
        ep.setCapAttributes(capAttrs);

        sideAttrs.setOutlineWidth(3);
        sideAttrs.setDrawOutline(true);
        sideAttrs.setDrawInterior(true);
        sideAttrs.setOutlineMaterial(Material.BLUE);
        sideAttrs.setInteriorMaterial(new Material(color));
        //  sideAttrs.setEnableLighting(true);
        ep.setSideAttributes(sideAttrs);
    }
}
