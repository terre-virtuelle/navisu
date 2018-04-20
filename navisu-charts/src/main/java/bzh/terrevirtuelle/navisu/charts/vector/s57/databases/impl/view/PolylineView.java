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
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Map;

/**
 *
 * @author serge
 */
public class PolylineView
        implements PolyGeomView {

    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected Path path;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    String label = "";
    String tmp = "";

    public PolylineView(TopologyServices topologyServices, RenderableLayer layer) {
        this.topologyServices = topologyServices;
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void display(String geometry,
            ShapeAttributes attrs, ShapeAttributes hattrs,
            Map<String, String> labels) {
        path = topologyServices.wktMultiLineToWwjPath(geometry, 1.0);
        path.setAttributes(attrs);
        path.setHighlightAttributes(hattrs);
        path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);

        if (labels != null) {
            labels.keySet().forEach((key) -> {
                tmp = String.format("%-30s", key + ": " + labels.get(key));
                label += tmp + "\n";
            });
            path.setValue(AVKey.DISPLAY_NAME, label);
        }
        //Le formatage n'est pas respecté dans le DISPLAY_NAME
        layer.addRenderable(path);
    }
}
