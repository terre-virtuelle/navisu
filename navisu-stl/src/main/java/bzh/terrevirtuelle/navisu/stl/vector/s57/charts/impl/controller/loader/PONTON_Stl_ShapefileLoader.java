/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.PONTON_ShapefileLoader;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;

/**
 *
 * @author serge
 * @date Mar 20, 2017
 */
public class PONTON_Stl_ShapefileLoader
        extends PONTON_ShapefileLoader {

    protected String filename;
    protected Polygon polyEnveloppe;

    public PONTON_Stl_ShapefileLoader(String filename, Polygon polyEnveloppe) {
        this.filename = filename;
        this.polyEnveloppe = polyEnveloppe;
       // System.out.println("PONTON_Stl_ShapefileLoader");
    }

    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        this.record = record;
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.RED);
        normalAttributes.setOutlineWidth(2.0);
        return normalAttributes;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        System.out.println("record " + record);
        Color color = Color.BLACK;

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        return normalAttributes;
    }
}
