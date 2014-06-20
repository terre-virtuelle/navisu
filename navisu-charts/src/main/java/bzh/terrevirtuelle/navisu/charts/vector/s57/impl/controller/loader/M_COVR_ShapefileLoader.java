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
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.awt.Color;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class M_COVR_ShapefileLoader
        extends ShapefileLoader 
        implements S57ShapeFileLoader{

    ShapefileRecord record;

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        this.record = record;
        
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(Color.RED));
        normalAttributes.setOutlineMaterial(new Material(Color.RED));
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }
    
}
