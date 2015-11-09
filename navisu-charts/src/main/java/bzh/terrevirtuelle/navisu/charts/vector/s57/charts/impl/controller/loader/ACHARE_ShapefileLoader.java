/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;

/**
 *
 * @author Serge
 */
public class ACHARE_ShapefileLoader extends AREA_ShapefileLoader {

    public ACHARE_ShapefileLoader(String acronym, Color color, double opacity, boolean isDrawInterior) {
        super(acronym, color, opacity, isDrawInterior);
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
/*
        Iterable<? extends LatLon> coords = record.getCompoundPointBuffer().getLocations();
        for (LatLon l : coords) {
          //  System.out.println(l.latitude + " " + l.longitude);
        }
        System.out.println("");
        */
        return super.createPolygonAttributes(record);
    }
}
