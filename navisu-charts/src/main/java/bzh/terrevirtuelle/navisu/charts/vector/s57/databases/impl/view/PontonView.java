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
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.WWUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class PontonView {

    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected Path path;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String label;

    public PontonView(TopologyServices topologyServices, RenderableLayer layer, String acronym) {
        this.topologyServices = topologyServices;
        this.acronym = acronym;
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<String> geometries) {
        ShapeAttributes attrs = new BasicShapeAttributes();
        attrs.setOutlineMaterial(new Material(Color.LIGHT_GRAY));
        attrs.setOutlineWidth(2d);

        for (String s : geometries) {
            path = topologyServices.wktMultiLineToWwjPath(s, 5.0);
            path.setAttributes(attrs);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            layer.addRenderable(path);
        }
        wwd.redrawNow();
    }
}
