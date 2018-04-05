/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthContour;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class DephContourView
        extends PolygonView {
    
    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected Path path;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String label;
    protected float val;
    protected Color color;
    
    public DephContourView(TopologyServices topologyServices, RenderableLayer layer, String acronym) {
        this.topologyServices = topologyServices;
        this.acronym = acronym;
        this.layer = layer;
        //  GeometryClipper GeometryClipper;
    }
    
    @SuppressWarnings("unchecked")
    public void display(List<DepthContour> depthContours) throws SQLException {
        List<Path> paths = new ArrayList<>();
        for (DepthContour d : depthContours) {
            String s = d.getGeom();
            s = s.replace("LINESTRING(", "");
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
            path = new Path(positions);
            color = defineColor(Double.parseDouble(d.getValueOfDepthContour()));
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setOutlineMaterial(new Material(color));
            path.setAttributes(attrs);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            paths.add(path);
        }
        layer.addRenderables(paths);
        wwd.redrawNow();
    }
    
    private Color defineColor(double val1) {
        color = new Color(159, 215, 247);
        
        if (val1 == -9.0 && val1 <= 0.0) {
            color = new Color(151, 199, 0);
        }
        if (val1 >= -14.0 && val1 <= 0.0) {
            color = new Color(151, 199, 0);
        }
        if (val1 >= 0.0 && val1 <= 12.0) {
            color = new Color(91, 175, 247);
        }
        if (val1 >= 0.0 && val1 <= 8.0) {
            //color = new Color(31, 175, 247);
            color = new Color(115, 182, 239);
        }
        if (val1 >= 0.0 && val1 <= 3.0) {
            color = new Color(31, 175, 247);
        }
        if (val1 == 5.0 && val1 <= 10.0) {
            color = new Color(159, 215, 247);
        }
        if (val1 >= 5.0 && val1 <= 25.0) {//20.0
            color = new Color(159, 215, 247);
        }
        if (val1 == 10.0 && val1 <= 20.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 10.0 && val1 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 20.0 && val1 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 15.0 && val1 <= 50.0) {
            color = new Color(129, 195, 226);
        }
        if (val1 == 30.0 && val1 <= 50.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 50.0 && val1 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 20.0 && val1 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 100.0) {
            color = new Color(247, 247, 247);
        }
        // pour une mer bleue, en mode nav
        /* 
         if (val >= -20.0 && val2 <= 5000.0) {
         color = new Color(10, 38, 51);
         }
         */
        return color;
        
    }
}
