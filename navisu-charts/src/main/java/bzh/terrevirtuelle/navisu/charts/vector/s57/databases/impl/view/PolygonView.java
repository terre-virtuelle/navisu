/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author serge
 */
public class PolygonView {

    protected Polygon polygon;
    protected String label;
    protected Color color;
    protected ShapefileRecord record;
    protected Set<Map.Entry<String, Object>> entries;
    protected SurfacePolygons shape;
    protected double height;
    protected ShapeAttributes capAttrs = new BasicShapeAttributes();
    protected ShapeAttributes sideAttrs = new BasicShapeAttributes();
    protected List<PointPlacemark> pointPlacemarks;
    PointPlacemark pointPlacemark;
    protected Set<LatLon> latLonSet;

    public PolygonView() {

        capAttrs.setDrawOutline(true);
        capAttrs.setDrawInterior(true);
        capAttrs.setOutlineMaterial(Material.BLUE);
        capAttrs.setInteriorMaterial(Material.CYAN);
        capAttrs.setEnableLighting(true);

        sideAttrs.setOutlineWidth(3);
        sideAttrs.setDrawOutline(true);
        sideAttrs.setDrawInterior(true);
        sideAttrs.setOutlineMaterial(Material.BLUE);
        sideAttrs.setInteriorMaterial(Material.BLUE);
        sideAttrs.setEnableLighting(true);

        pointPlacemarks = new ArrayList<>();
        latLonSet = new HashSet<>();
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
                ep.setCapAttributes(capAttrs);
                ep.setSideAttributes(sideAttrs);
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
        } else // create surface polygons
        {
            
            shape = new SurfacePolygons(
                    Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                    record.getCompoundPointBuffer());
            shape.setWindingRule(AVKey.CLOCKWISE);
            shape.setPolygonRingGroups(new int[]{0});
            layer.addRenderable(shape);
            if (simp == true) {
                height=1;
                if (record.getAttributes() != null) {
                    entries = record.getAttributes().getEntries();
                    entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                        if (e.getKey().equalsIgnoreCase("drval1")) {
                            height = (Double) e.getValue();
                        }
                    });
                }
                if (height < 0) {
                    if (record.getAttributes() != null) {
                        entries = record.getAttributes().getEntries();
                        entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                            Iterable<? extends LatLon> locations = shape.getLocations();
                            for (LatLon ll : locations) {
                                latLonSet.add(ll);
                            }
                        });
                    }
                }
            }
        }
    }

    public double getHeight() {
        return height;
    }

    public Set<LatLon> getLatLonSet() {
        return latLonSet;
    }

    public List<PointPlacemark> getPointPlacemarks() {
        return pointPlacemarks;
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

    protected Color defineColor(double val1, double val2) {
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
