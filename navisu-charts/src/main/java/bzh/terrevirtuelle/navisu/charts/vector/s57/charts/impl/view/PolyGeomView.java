/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Map;

/**
 *
 * @author serge
 */
public interface PolyGeomView {

    default void display(String geometries, 
            ShapeAttributes attrs, ShapeAttributes hattrs,
            Map<String,String> labels){}
}
