/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.awt.Color;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author serge
 */
public abstract class PolygonView
        extends ShapeFileView {

    protected Polygon polygon;
    protected String label;
    protected Color color;
    protected ShapefileRecord record;
    protected Set<Map.Entry<String, Object>> entries;
    protected SurfacePolygons shape;
    protected double height;
    protected ShapeAttributes capAttrs = new BasicShapeAttributes();
    protected ShapeAttributes sideAttrs = new BasicShapeAttributes();
    

    public PolygonView() {

    }

    protected void createPolygon(RenderableLayer layer, ShapefileRecord record,
            boolean isHeight, double magnify, double maxHeight, boolean simp) {
        this.record = record;
        if (isHeight == true) {
            if (record.getAttributes() != null) {
                entries = record.getAttributes().getEntries();
                entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                    if (e.getKey().equalsIgnoreCase("drval2")) {
                        height = magnify * (maxHeight - (Double) e.getValue());
                    }
                });
            }

            if (height != 0) // create extruded polygons
            {
                ExtrudedPolygon ep = new ExtrudedPolygon(height);
                setExtrudedPolygonAttributes(ep);

                layer.addRenderable(ep);

                for (int i = 0; i < record.getNumberOfParts(); i++) {

                    VecBuffer buffer = record.getCompoundPointBuffer().subBuffer(i);
                    if (WWMath.computeWindingOrderOfLocations(buffer.getLocations()).equals(AVKey.CLOCKWISE)) {
                        if (!ep.getOuterBoundary().iterator().hasNext()) // has no outer boundary yet
                        {
                            ep.setOuterBoundary(buffer.getLocations());
                        } else {
                            ep = new ExtrudedPolygon();
                            //  ep.setAttributes(attrs);
                            ep.setOuterBoundary(record.getCompoundPointBuffer().getLocations());
                            layer.addRenderable(ep);
                        }
                    } else {
                        ep.addInnerBoundary(buffer.getLocations());
                    }
                }
            }
        } else {
            shape = new SurfacePolygons(
                    Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                    record.getCompoundPointBuffer());
            shape.setWindingRule(AVKey.CLOCKWISE);
            shape.setPolygonRingGroups(new int[]{0});
            layer.addRenderable(shape);
        }
    }

    protected abstract void setPolygonAttributes(SurfacePolygons shape, Color color);

    protected  void setExtrudedPolygonAttributes(ExtrudedPolygon ep){}

}
