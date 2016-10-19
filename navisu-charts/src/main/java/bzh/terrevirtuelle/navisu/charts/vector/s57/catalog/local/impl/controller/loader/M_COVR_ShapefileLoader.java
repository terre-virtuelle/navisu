/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.S57ShapeFileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.ShapefileLoader;
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
public class M_COVR_ShapefileLoader
        extends ShapefileLoader
        implements S57ShapeFileLoader {

    private ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        this.record = record;
        entries = record.getAttributes().getEntries();
        entries.stream().forEach((e) -> {
           // System.out.println(e);
        });

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(Color.RED));
        normalAttributes.setOutlineMaterial(new Material(Color.RED));
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        return normalAttributes;
    }

    @Override
    protected void createPolygon(ShapefileRecord record, ShapeAttributes attrs, RenderableLayer layer) {
        this.record = record;
        entries = record.getAttributes().getEntries();

        entries.stream().forEach((e) -> {
            SurfacePolygons shape;
            if (e.getKey().contains("CATCOV")) {
                if (e.getValue().toString().contains("1")) {
                    shape = new SurfacePolygons(
                            Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                            record.getCompoundPointBuffer());
                    shape.setAttributes(attrs);
                    shape.setWindingRule(AVKey.CLOCKWISE);
                    shape.setPolygonRingGroups(new int[]{0});
                    layer.addRenderable(shape);
                }
            }
            /*
            if (e.getKey().contains("SORIND")) {
                String[] val = e.getValue().toString().split(",");
                if (shape != null) {
                    shape.setValue("Numer", val[val.length - 1]);
                }
            }
                    */
        });
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

}
