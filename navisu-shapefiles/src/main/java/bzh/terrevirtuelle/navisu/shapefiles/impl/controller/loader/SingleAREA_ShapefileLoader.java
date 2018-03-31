/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.shapefiles.impl.controller.loader;

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
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class SingleAREA_ShapefileLoader
        extends ShapefileLoader {

    private ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;
    private Color color = Color.RED;
    private double opacity;
    private String objname;
    private SurfacePolygons shape;
    private String label = "";
    private List<List<String>> dbList;
    private Map<String, Integer> keys;
    int i = 0;
    private Shapefile shp;

    public SingleAREA_ShapefileLoader(List<List<String>> dbList, Map<String, Integer> keys) {
        this.dbList = dbList;
        this.keys = keys;
    }

    public SingleAREA_ShapefileLoader() {
    }

    @Override
    protected void addRenderablesForPolygons(Shapefile shp, List<Layer> layers) {
        RenderableLayer layer = new RenderableLayer();
        this.shp = shp;
        layers.add(layer);
        while (shp.hasNext()) {
            try {
                record = shp.nextRecord();
                if (record != null) {
                    if (record.getAttributes() != null) {
                        entries = record.getAttributes().getEntries();
                        entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                            if (e.getKey().equals("TYPEVALE")) {
                                color = NFD_COLOUR.ATT.get(((String) e.getValue()).trim());
                            } else {
                                color = new Color((int) (Math.random() * 255),
                                        (int) (Math.random() * 255), (int) (Math.random() * 255));
                            }
                            // System.out.println("e.getKey() : " + e.getKey());
                        });
                    }
                    if (!Shapefile.isPolygonType(record.getShapeType())) {
                        continue;
                    }
                    color = new Color((int) (Math.random() * 255),
                            (int) (Math.random() * 255), (int) (Math.random() * 255));
                    ShapeAttributes attrs = this.createPolygonAttributes(color);
                    this.createPolygon(record, attrs, layer);

                    label = "";
                }
            } catch (Exception ex) {
                Logger.getLogger(SingleAREA_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

    @Override
    protected void createPolygon(ShapefileRecord record,
            ShapeAttributes attrs,
            RenderableLayer layer) {
        this.record = record;
        shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());

        shape.setAttributes(attrs);
        //  System.out.println("attrs : " + shape.getAttributes());
        shape.setWindingRule(AVKey.CLOCKWISE);
        shape.setPolygonRingGroups(new int[]{0});

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(attrs);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(Color.WHITE));
        highlightAttributes.setInteriorOpacity(.5);
        highlightAttributes.setEnableLighting(true);

        shape.setHighlightAttributes(highlightAttributes);

        layer.addRenderable(shape);
        i++;
    }

    public List<SurfacePolygons> createPolygons(Shapefile shp, RenderableLayer layer) {
        return null;
    }

    protected ShapeAttributes createPolygonAttributes(Color color) {

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(true);
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(Color.BLACK));
        normalAttributes.setEnableLighting(true);
        return normalAttributes;
    }

    protected void createValues(SurfacePolygons shape) {
        entries = record.getAttributes().getEntries();
    }

    public Shapefile getShapefile() {
        return shp;
    }

}
