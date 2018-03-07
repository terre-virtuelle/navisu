/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthArea;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class DepareView {

    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected Polygon polygon;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String label;
    protected float val1;
    protected float val2;
    protected Color color;

    public DepareView(TopologyServices topologyServices, RenderableLayer layer, String acronym) {
        this.topologyServices = topologyServices;
        this.acronym = acronym;
        this.layer = layer;
      //  GeometryClipper GeometryClipper;
    }

    @SuppressWarnings("unchecked")
    public void display(List<DepthArea> depthAreas) throws SQLException {

        for (DepthArea d : depthAreas) {
            String s = d.getGeom();
            s = s.replace("(", "");
            s = s.replace(")", "");
            String[] tab0 = s.split(",");
            int l = tab0.length;
            List<Position> positions = new ArrayList<>();
            for (int j = 0; j < l; j++) {
                String[] latLon = tab0[j].trim().split(" ");
                String lat = latLon[1];
                String lon = latLon[0];
                positions.add(new Position(Angle.fromDegrees(Double.parseDouble(lat)),
                        Angle.fromDegrees(Double.parseDouble(lon)), 5));
            }
            polygon = new Polygon(positions);
            color = defineColor(Double.parseDouble(d.getDepthRangeValue1()),
                    Double.parseDouble(d.getDepthRangeValue2()));
            //  System.out.println("color : " + color);
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setDrawInterior(true);
            attrs.setDrawOutline(false);
            attrs.setInteriorMaterial(new Material(color));
            polygon.setAttributes(attrs);
            polygon.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            layer.addRenderable(polygon);
        }
        wwd.redrawNow();
    }

    private Color defineColor(double val1, double val2) {
        //  System.out.println("val1 : " + val1 + " val2 : " + val2);
        color = new Color(159, 215, 247);

        if (val1 == -9.0 && val2 <= 0.0) {
            color = new Color(151, 199, 0);
        }
        if (val1 >= -14.0 && val2 <= 0.0) {
            color = new Color(151, 199, 0);
            // color = new Color(87, 137, 108);
        }
        if (val1 >= 0.0 && val2 <= 12.0) {
            color = new Color(91, 175, 247);
        }
        if (val1 >= 0.0 && val2 <= 8.0) {
            //color = new Color(31, 175, 247);
            color = new Color(115, 182, 239);
        }
        if (val1 >= 0.0 && val2 <= 3.0) {
            color = new Color(31, 175, 247);
        }
        if (val1 == 5.0 && val2 <= 10.0) {
            color = new Color(159, 215, 247);
        }
        if (val1 >= 5.0 && val2 <= 25.0) {//20.0
            color = new Color(159, 215, 247);
        }
        if (val1 == 10.0 && val2 <= 20.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 10.0 && val2 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 20.0 && val2 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 15.0 && val2 <= 50.0) {
            color = new Color(129, 195, 226);
        }
        if (val1 == 30.0 && val2 <= 50.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 50.0 && val2 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 20.0 && val2 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val2 >= 100.0) {
            color = new Color(247, 247, 247);
        }
        // pour une mer bleue, en mode nav
        /* 
         if (val1 >= -20.0 && val2 <= 5000.0) {
         color = new Color(10, 38, 51);
         }
         */
        return color;

    }
}
