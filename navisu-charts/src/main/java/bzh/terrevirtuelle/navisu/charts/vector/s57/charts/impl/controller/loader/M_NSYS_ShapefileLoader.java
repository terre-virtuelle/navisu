/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Map;
import java.util.Set;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class M_NSYS_ShapefileLoader
        extends ShapefileLoader
        implements S57ShapeFileLoader {

    private ShapefileRecord record;
    private String marsys;
    private Set<Map.Entry<String, Object>> entries;
    private Boolean first = true;

    public M_NSYS_ShapefileLoader() {
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        this.record = record;
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        entries = record.getAttributes().getEntries();
        entries.stream().forEach((e) -> {
            if (e.getKey().equals("MARSYS")) {
                if (first == true) {
                    marsys = ((Long) e.getValue()).toString();
                    if (marsys == null || marsys.equals("9") || marsys.equals("10")) {
                        marsys = "0";
                    }
                    first = false;
                }
            }
        });
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

    public String getMarsys() {
        return marsys;
    }
}
