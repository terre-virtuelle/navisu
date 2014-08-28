/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.NavigationLine;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolyline;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolylines;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
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
public class NAVLNE_ShapefileLoader
        extends ShapefileLoader
        implements S57ShapeFileLoader {

    private ShapefileRecord record;
    private NavigationLine navigationLine;
    private final List<NavigationLine> navigationLines;
    private Set<Map.Entry<String, Object>> entries;

    public NAVLNE_ShapefileLoader() {
        navigationLines = new ArrayList<>();
    }

    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        this.record = record;
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineStipplePattern((short) 0xAAAA);
        normalAttributes.setOutlineStippleFactor(5);
        normalAttributes.setOutlineMaterial(new Material(new Color(247, 48, 247)));
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

    @Override
    public List<Layer> createLayersFromShapefile(Shapefile shp) {
        while (shp.hasNext()) {
            record = shp.nextRecord();
            // System.out.println("record " + record.getAttributes().getEntries());
        }
        navigationLine = new NavigationLine();
        entries = record.getAttributes().getEntries();
        //  System.out.println("entries " + entries);

        return super.createLayersFromShapefile(shp);
    }
/*
    @Override
    protected void addRenderablesForPolylines(Shapefile shp, RenderableLayer layer) {

        while (shp.hasNext()) {
            ShapefileRecord record = shp.nextRecord();

            if (!Shapefile.isPolylineType(record.getShapeType())) {
                continue;
            }

            ShapeAttributes attrs = this.createPolylineAttributes(record);
            layer.addRenderable(this.createPolyline(record, attrs));
        }

    }

    @Override
    protected Renderable createPolyline(ShapefileRecord record, ShapeAttributes attrs) {
        SurfacePolylines shape = new SurfacePolylines(
                Sector.fromDegrees(((ShapefileRecordPolyline) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        shape.setAttributes(attrs);
        System.out.println("createPolyline");
        return shape;
    }
    */
}
