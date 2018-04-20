/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author serge
 */
public class PolygonView
        implements PolyGeomView {

    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected Polygon path;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String label;

    public PolygonView(TopologyServices topologyServices, RenderableLayer layer) {
        this.topologyServices = topologyServices;
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
 //   @Override
    public void display(String geometries, ShapeAttributes attrs) {
            path = topologyServices.wktMultiPolygonToWwjPolygon(geometries);
            path.setAttributes(attrs);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            layer.addRenderable(path);
    }
}
