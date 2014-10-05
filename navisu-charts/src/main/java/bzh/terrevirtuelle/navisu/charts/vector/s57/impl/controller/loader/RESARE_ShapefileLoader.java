/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class RESARE_ShapefileLoader
        extends ShapefileLoader
        implements S57ShapeFileLoader {

    ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        this.record = record;
        entries = record.getAttributes().getEntries();
        System.out.println("entries PolygonTemplate : " + entries);
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(new Color(197,69,195)));
        normalAttributes.setOutlineStipplePattern((short) 0xAAAA);
        normalAttributes.setOutlineStippleFactor(5);
      //  normalAttributes.setOutlineWidth(2d);
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

}
