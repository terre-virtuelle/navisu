/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWindow;
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
public class PontonView {

    PolyGeomView polyView;
    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public PontonView(TopologyServices topologyServices, RenderableLayer layer) {
        this.topologyServices = topologyServices;
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<String> geometries) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setOutlineMaterial(new Material(Color.WHITE));
        normAttributes.setOutlineWidth(3.0);
        normAttributes.setEnableLighting(true);
        for (String s : geometries) {
            if (s.contains("MULTILINESTRING")) {
                polyView = new PolylineView(topologyServices, layer);
                polyView.display(s, normAttributes);
            }
            if (s.contains("MULTIPOLYGON")) {
                System.out.println("s : " + s);
                polyView = new PolygonView(topologyServices, layer);
                polyView.display(s, normAttributes);
            }
        }
        wwd.redrawNow();
    }
}
