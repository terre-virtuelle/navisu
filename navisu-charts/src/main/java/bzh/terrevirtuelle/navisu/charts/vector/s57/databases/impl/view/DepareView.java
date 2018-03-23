/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.ndf.view.NFD_COLOUR;
import bzh.terrevirtuelle.navisu.shapefiles.impl.controller.loader.SingleAREA_ShapefileLoader;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import java.awt.Color;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DepareView {

    protected RenderableLayer layer;
    protected Polygon polygon;
    protected String label;
    protected double val1;
    protected double val2;
    protected Color color;

    private ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;
    private SurfacePolygons shape;

    public DepareView(RenderableLayer layer) {
        this.layer = layer;
    }

    public void display(Shapefile shp) {

        while (shp.hasNext()) {
            try {
                record = shp.nextRecord();
                if (record != null) {
                    if (record.getAttributes() != null) {
                        entries = record.getAttributes().getEntries();
                        entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                            if (e.getKey().equalsIgnoreCase("drval1")) {
                                val1 = (Double)e.getValue();
                            }
                            if (e.getKey().equalsIgnoreCase("drval2")) {
                                val2 = (Double)e.getValue();
                            }
                            color = defineColor(val1, val2);
                        });
                    }
                    if (!Shapefile.isPolygonType(record.getShapeType())) {
                        continue;
                    }

                    createPolygon(record);
                    setPolygonAttributes(shape, color);
                    
                    layer.addRenderable(shape);
                    label = "";
                }
            } catch (Exception ex) {
                Logger.getLogger(SingleAREA_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

    protected void createPolygon(ShapefileRecord record) {
        this.record = record;
        shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        shape.setWindingRule(AVKey.CLOCKWISE);
        shape.setPolygonRingGroups(new int[]{0});
    }

    protected void setPolygonAttributes(SurfacePolygons shape, Color color) {

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(true);
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(Color.BLACK));
        normalAttributes.setEnableLighting(true);
        shape.setAttributes(normalAttributes);

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(Color.WHITE));
        highlightAttributes.setInteriorOpacity(.5);
        highlightAttributes.setEnableLighting(true);
        shape.setHighlightAttributes(highlightAttributes);
    }

    private Color defineColor(double val1, double val2) {
        color = new Color(159, 215, 247);

        if (val1 >= -14.0 && val2 <= 0.0) {
            color = new Color(151, 199, 0);
            // color = new Color(87, 137, 108);
        }
        if (val1 >= 0.0 && val2 <= 12.0) {
            color = new Color(91, 175, 247);
        }
        if (val1 >= 0.0 && val2 <= 8.0) {
            //color = new Color(31, 175, 247);
            color = new Color(115, 182, 239);
        }
        if (val1 >= 0.0 && val2 <= 3.0) {
            color = new Color(31, 175, 247);
        }
        if (val1 == 5.0 && val2 <= 10.0) {
            color = new Color(159, 215, 247);
        }
        if (val1 >= 5.0 && val2 <= 25.0) {//20.0
            color = new Color(159, 215, 247);
        }
        if (val1 == 10.0 && val2 <= 20.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 10.0 && val2 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 20.0 && val2 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 15.0 && val2 <= 50.0) {
            color = new Color(129, 195, 226);
        }
        if (val1 == 30.0 && val2 <= 50.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 50.0 && val2 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 20.0 && val2 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val2 >= 100.0) {
            color = new Color(247, 247, 247);
        }
        // pour une mer bleue, en mode nav
        /* 
         if (val1 >= -20.0 && val2 <= 5000.0) {
         color = new Color(10, 38, 51);
         }
         */
        return color;

    }
}
