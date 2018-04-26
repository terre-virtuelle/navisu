/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.List;
import java.util.Map;

/**
 *
 * @author serge
 */
public class MultiPolygonView
        implements PolyGeomView {

    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected String acronym;
    protected Polygon polygon;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String label = "";
    String tmp = "";

    public MultiPolygonView(String acronym, TopologyServices topologyServices, RenderableLayer layer) {
        this.topologyServices = topologyServices;
        this.layer = layer;
        this.acronym = acronym;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void display(String geometries, ShapeAttributes attrs, ShapeAttributes hattrs,
            Map<String, String> labels) {
        List<Polygon> polygons = topologyServices.wktMultiPolygonToWwjPolygons(geometries);
        for (Polygon p : polygons) {
            p.setAttributes(attrs);
            p.setHighlightAttributes(hattrs);
            p.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            if (labels != null) {
                if (labels.get(acronym) != null) {
                    label = labels.get(acronym).toUpperCase() + "\n";
                }
                labels.keySet().forEach((key) -> {
                    tmp = labels.get(key);
                    if (tmp != null && !key.equals(acronym)) {
                        if (!key.equals("")) {
                            label += key + " : " + tmp + "\n";
                        } else {
                            label += tmp + "\n";
                        }
                    }
                });
                p.setValue(AVKey.DISPLAY_NAME, label);
            }
        }
        layer.addRenderables(polygons);
    }
}
