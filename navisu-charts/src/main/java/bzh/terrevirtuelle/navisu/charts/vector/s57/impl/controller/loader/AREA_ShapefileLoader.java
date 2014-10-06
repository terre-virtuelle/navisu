/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.parameters.AREA;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
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
        extends ShapefileLoader
        implements S57ShapeFileLoader {

    private ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;
    private final Color color;
    private final String acronym;

    public AREA_ShapefileLoader(String acronym, Color color) {
        this.color = color;
        this.acronym = acronym;
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
        SurfacePolygons shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        shape.setAttributes(attrs);
        shape.setWindingRule(AVKey.CLOCKWISE);
        shape.setPolygonRingGroups(new int[]{0});
        //  shape.setPolygonRingGroups(new int[]{0});

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(attrs);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(color));
        highlightAttributes.setInteriorOpacity(0.5);
        shape.setHighlightAttributes(highlightAttributes);

        this.record = record;
        entries = record.getAttributes().getEntries();
         System.out.println("entries " + entries);

        Iterable<double[]> coords = record.getCompoundPointBuffer().getCoords();
        int length = 0;
        for (double[] t : coords) {
            length++;
        }
        Coordinate[] coordinates = new Coordinate[length + 1];
        int i = 0;
        for (double[] t : coords) {
            coordinates[i] = new Coordinate(t[0], t[1], 0.0);
            i++;
        }
        coordinates[i] = new Coordinate(coordinates[0].x, coordinates[0].y, 0.0);// Le poly doit être fermé
        Polygon poly = new GeometryFactory().createPolygon(new GeometryFactory().createLinearRing(coordinates), null);
       // System.out.println(poly);
        System.out.println("poly.contains " + poly.contains(new GeometryFactory().createPoint(new Coordinate(-4.5960, 48.3302))));
        entries.stream().forEach((e) -> {
            String label = AREA.ATT.get(acronym) + "\n";
            if (e.getKey().equals("INFORM")) {
                if (e.getValue() != null) {
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
                }
            }
            if (e.getKey().equals("OBJNAM")) {
                if (e.getValue() != null) {
                    shape.setValue(AVKey.DISPLAY_NAME, label.concat((String) e.getValue()));
                }
            }
        });
        layer.addRenderable(shape);
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

}
