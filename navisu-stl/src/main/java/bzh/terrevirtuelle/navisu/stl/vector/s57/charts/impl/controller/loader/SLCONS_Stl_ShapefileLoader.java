/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SLCONS_ShapefileLoader;
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
public class SLCONS_Stl_ShapefileLoader 
        extends SLCONS_ShapefileLoader{
protected String filename;
protected Polygon polyEnveloppe;

    public SLCONS_Stl_ShapefileLoader(String filename, Polygon polyEnveloppe) {
        this.filename = filename;
        this.polyEnveloppe = polyEnveloppe;
    }
@Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        System.out.println("SLCONS_Stl_ShapefileLoader createPolylineAttributes");
        this.record = record;
        System.out.println("record : "+ record);
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.BLUE);
        normalAttributes.setOutlineWidth(2.0);
        return normalAttributes;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        System.out.println("SLCONS_Stl_ShapefileLoader createPolygonAttributes");
        Color color = Color.BLACK;

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        return normalAttributes;
    }
}
