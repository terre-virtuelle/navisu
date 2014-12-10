/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.magnetic.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.ShapeAttributes;


/**
 *
 * @author Serge
 */
public class MagneticShapefileLoader
        extends ShapefileLoader {

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        if (record != null) {
           // System.out.println("createPolylineAttributes " + record.getAttributes().getEntries());
        }
        return randomAttrs.nextPolylineAttributes();
    }
}
