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
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.awt.Color;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class DEPARE_ShapefileLoader
        extends ShapefileLoader 
        implements S57ShapeFileLoader{

    ShapefileRecord record;

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        this.record = record;
        Float val1 = new Float(record.getAttributes().getValue("DRVAL1").toString());
        Float val2 = new Float(record.getAttributes().getValue("DRVAL2").toString());

        Color color = new Color(255, 0, 0);

        if (val1 == -9.0 && val2 <= 0.0) {
          //  color = new Color(131, 178, 149);
            color = new Color(151, 199, 0);
        }
        
        if (val1 >= 0.0 && val2 <= 12.0) {
            color = new Color(91, 175, 247);
        }
        if (val1 >= 0.0 && val2 <= 8.0) {
            color = new Color(31, 175, 247);
        }
        if (val1 >= 0.0 && val2 <= 3.0) {
            color = new Color(33, 255, 242);
        }
        if (val1 == 5.0 && val2 <= 10.0) {
            color = new Color(159, 215, 247);
        }
        if (val1 >= 5.0 && val2 <= 20.0) {
            color = new Color(159, 215, 247);
        }
       /*
        if ( val1 > 0.0 && val2 <= 2.0) {
            color = new Color(31, 175, 247);
        }
        if (val2 <= 3.5) {
            color = new Color(87, 199, 247);
        }
        if (val2 <= 5.5) {
            color = new Color(135, 207, 247);
        }
        if ( val2 < 10.0) {
            color = new Color(159, 215, 247);
        }
                */
        if (val1 == 10.0 && val2 <= 20.0) {
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
        if (val2 >=100.0 ) {
            color = new Color(247, 247, 247);
        }
        
       
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(false);
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }
    
}
