 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class BRIDGE_ShapefileLoader
        extends LayerShapefileLoader {

    public BRIDGE_ShapefileLoader() {
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(true);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setInteriorMaterial(new Material(new Color(139, 142, 133)));
        normalAttributes.setOutlineMaterial(new Material(new Color(139, 142, 133)));
        return normalAttributes;

    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(new Color(139, 142, 133)));
        normalAttributes.setOutlineWidth(3.0);
        return normalAttributes;
    }
}
