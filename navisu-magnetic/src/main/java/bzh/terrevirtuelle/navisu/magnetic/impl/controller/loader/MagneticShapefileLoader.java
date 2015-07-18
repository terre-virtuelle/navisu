/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.magnetic.impl.controller.loader;

import bzh.terrevirtuelle.navisu.core.util.shapefile.Template_ShapefileLoader;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge
 */
public class MagneticShapefileLoader
        extends Template_ShapefileLoader {

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        // System.out.println("MagneticShapefileLoader");
        if (record != null) {
            Set<Entry<String, Object>> entries = record.getShapeFile().getEntries();
            for (Entry<String, Object> e : entries) {
                System.out.println(e.getKey() + " " + e.getValue());
            }
          //  System.out.println("getPointBuffer "+record.getShapeFile().getPointBuffer());
            //   System.out.println("getValues "+record.getAttributes().getValues());
            //   System.out.println("getNumberOfRecords "+record.getShapeFile().getNumberOfRecords());
            //   System.out.println("createPolylineAttributes " + record.getAttributes().getEntries());
        }
        return randomAttrs.nextPolylineAttributes();
    }
}
