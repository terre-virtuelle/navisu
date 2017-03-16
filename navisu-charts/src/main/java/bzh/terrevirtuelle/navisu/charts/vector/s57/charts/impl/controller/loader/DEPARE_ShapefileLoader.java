/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class DEPARE_ShapefileLoader
        extends LayerShapefileLoader
        implements S57ShapeFileLoader {

    protected ShapefileRecord record;
    protected float val1;
    protected float val2;
    protected Color color;
    protected ShapeAttributes normalAttributes;

    public DEPARE_ShapefileLoader() {
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
       // System.out.println("DEPARE_ShapefileLoader createPolygonAttributes");
        this.record = record;
        val1 = new Float(record.getAttributes().getValue("DRVAL1").toString());
        val2 = new Float(record.getAttributes().getValue("DRVAL2").toString());

        color = new Color(159, 215, 247);

        if (val1 == -9.0 && val2 <= 0.0) {
            color = new Color(151, 199, 0);
        }
        if (val1 >= -14.0 && val2 <= 0.0) {
            // color = new Color(151, 199, 0);
            color = new Color(87, 137, 108);
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

        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(false);
        //  normalAttributes.setImageSource(getClass().getResourceAsStream("img/mer.jpg"));
        return normalAttributes;
    }

    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        this.record = record;
        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.BLACK);
        normalAttributes.setOutlineWidth(2.0);
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

    
}
