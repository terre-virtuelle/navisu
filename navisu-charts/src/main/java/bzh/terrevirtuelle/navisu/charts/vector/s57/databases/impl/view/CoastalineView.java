/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author serge
 */
public class CoastalineView
        extends ShapefilePolylineView {

    PolyGeomView polyView;
    protected TopologyServices topologyServices;
    protected RenderableLayer layer;

    public CoastalineView(TopologyServices topologyServices, RenderableLayer layer) {
        this.topologyServices = topologyServices;
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<String> geometries) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setOutlineMaterial(new Material(Color.WHITE));
        normAttributes.setEnableLighting(true);
       //Clip 
        for (String s : geometries) {
            polyView = new PolylineView(topologyServices, layer);
            polyView.display(s, normAttributes);
        }
    }

}
