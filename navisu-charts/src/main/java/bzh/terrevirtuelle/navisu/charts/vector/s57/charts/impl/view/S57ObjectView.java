/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
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
public class S57ObjectView {

    PolyGeomView polyView;
    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    ShapeAttributes normAttributes;
    ShapeAttributes highlightAttributes;
    String acronym;

    public S57ObjectView(String acronym, TopologyServices topologyServices, RenderableLayer layer) {
        this.acronym = acronym;
        this.topologyServices = topologyServices;
        this.layer = layer;

        normAttributes = new BasicShapeAttributes();
        normAttributes.setOutlineMaterial(new Material(Color.RED));
        normAttributes.setOutlineWidth(1.0);
        normAttributes.setEnableLighting(true);

        highlightAttributes = new BasicShapeAttributes(normAttributes);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setOutlineMaterial(new Material(Color.BLUE));
        highlightAttributes.setEnableLighting(true);
    }

    public S57ObjectView(String acronym, TopologyServices topologyServices, RenderableLayer layer,
            ShapeAttributes normAttributes, ShapeAttributes highlightAttributes) {
        this.acronym = acronym;
        this.topologyServices = topologyServices;
        this.layer = layer;
        this.normAttributes = normAttributes;
        this.highlightAttributes = highlightAttributes;
    }

    @SuppressWarnings("unchecked")
    public void display(Geo object,
            ShapeAttributes normAttributes, ShapeAttributes highlightAttributes) {
        displayP(object, normAttributes, highlightAttributes);
    }

    @SuppressWarnings("unchecked")
    public void display(Geo object) {
        displayP(object, normAttributes, highlightAttributes);
    }

    @SuppressWarnings("unchecked")
    public void display(List<? extends Geo> objects) {
        objects.forEach((g) -> {
            display(g);
        });
    }

    private void displayP(Geo object, ShapeAttributes normAttributes, ShapeAttributes highlightAttributes) {
        String geometry = object.getGeom();

        if ((geometry.contains("LINESTRING") && !geometry.contains("MULTILINESTRING")) && !geometry.contains("EMPTY")) {
            polyView = new LineView(acronym, topologyServices, layer);
            polyView.display(geometry, normAttributes, highlightAttributes, object.getLabels());
        }

        if (geometry.contains("MULTILINESTRING") && !geometry.contains("EMPTY")) {
            polyView = new PolylineView(acronym, topologyServices, layer);
            polyView.display(geometry, normAttributes, highlightAttributes, object.getLabels());
        }

        if (geometry.contains("POLYGON") && !geometry.contains("MULTIPOLYGON") && !geometry.contains("EMPTY")) {
            polyView = new PolygonView(acronym, topologyServices, layer);
            polyView.display(geometry, normAttributes, highlightAttributes, object.getLabels());
        }
        if (geometry.contains("MULTIPOLYGON") && !geometry.contains("EMPTY")) {
            polyView = new MultiPolygonView(acronym, topologyServices, layer);
            polyView.display(geometry, normAttributes, highlightAttributes, object.getLabels());
        }

    }
}
