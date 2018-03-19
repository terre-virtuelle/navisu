/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.ndf.view.NFD_COLOUR;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthAreaWithHoles;
import bzh.terrevirtuelle.navisu.shapefiles.impl.controller.loader.ShapefileLoader;
import bzh.terrevirtuelle.navisu.shapefiles.impl.controller.loader.SingleAREA_ShapefileLoader;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
//import gov.nasa.worldwind.render.airspaces.Polygon;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DepareView {
    //extends ShapefileLoader {

    protected TopologyServices topologyServices;
    protected RenderableLayer layer;
    protected Polygon polygon;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String label;
    protected float val1;
    protected float val2;
    protected Color color;

    private ShapefileRecord record;
    private Set<Map.Entry<String, Object>> entries;
    private SurfacePolygons shape;

    public DepareView(TopologyServices topologyServices, RenderableLayer layer, String acronym) {
        this.topologyServices = topologyServices;
        this.acronym = acronym;
        this.layer = layer;

    }

    public void display(Shapefile shp) {

        while (shp.hasNext()) {
            try {
                record = shp.nextRecord();
                if (record != null) {
                    if (record.getAttributes() != null) {
                        entries = record.getAttributes().getEntries();
                        entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                            if (e.getKey().equals("TYPEVALE")) {
                                color = NFD_COLOUR.ATT.get(((String) e.getValue()).trim());
                            } else {
                                color = new Color((int) (Math.random() * 255),
                                        (int) (Math.random() * 255), (int) (Math.random() * 255));
                            }
                        });
                    }
                    if (!Shapefile.isPolygonType(record.getShapeType())) {
                        continue;
                    }

                    createPolygon(record);
                    setPolygonAttributes(shape, color);
                    layer.addRenderable(shape);
                    label = "";
                }
            } catch (Exception ex) {
                Logger.getLogger(SingleAREA_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

    protected void createPolygon(ShapefileRecord record) {
        this.record = record;
        shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        shape.setWindingRule(AVKey.CLOCKWISE);
        shape.setPolygonRingGroups(new int[]{0});
    }

    protected void setPolygonAttributes(SurfacePolygons shape, Color color) {

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(true);
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(Color.BLACK));
        normalAttributes.setEnableLighting(true);
        shape.setAttributes(normalAttributes);

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(Color.WHITE));
        highlightAttributes.setInteriorOpacity(.5);
        highlightAttributes.setEnableLighting(true);
        shape.setHighlightAttributes(highlightAttributes);
    }
/*
    @SuppressWarnings("unchecked")
    public void display(List<DepthAreaWithHoles> depthAreas) throws SQLException {
        int count = 0;
        List<Position> buffer = new ArrayList();
        for (DepthAreaWithHoles d : depthAreas) {
            //if (count++ < 30) {
            //area
            int l;
            List<Position> positions = new ArrayList<>();

            String s = d.getGeom();
            s = s.replace("(", "");
            s = s.replace(")", "");
            String[] tab0 = s.split(",");
            l = tab0.length;

            for (int j = 0; j < l; j++) {
                String[] latLon = tab0[j].trim().split(" ");
                String lat = latLon[1];
                String lon = latLon[0];
                positions.add(new Position(Angle.fromDegrees(Double.parseDouble(lat)),
                        Angle.fromDegrees(Double.parseDouble(lon)),
                        5));
            }

            polygon = new Polygon(positions);

            color = defineColor(Double.parseDouble(d.getDepthRangeValue2()),
                    Double.parseDouble(d.getDepthRangeValue2()));

            String label0 = (int) Double.parseDouble(d.getDepthRangeValue1())
                    + ", "
                    + (int) Double.parseDouble(d.getDepthRangeValue2());

            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setDrawInterior(true);
            attrs.setDrawOutline(true);
            attrs.setOutlineMaterial(Material.BLACK);
            attrs.setInteriorMaterial(new Material(color));
            attrs.setEnableLighting(true);
            //   polygon.setAltitudes(300 - Double.parseDouble(d.getDepthRangeValue1()) * 10,
            //           300 - Double.parseDouble(d.getDepthRangeValue2()) * 10);
            polygon.setAttributes(attrs);
            //  polygon.setValue(AVKey.DISPLAY_NAME, label0);
            polygon.setValue(AVKey.DISPLAY_NAME, count++);
            polygon.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);

            layer.addRenderable(polygon);

        }
        System.out.println("count : " + count);
        wwd.redrawNow();
    }
*/
    private Color defineColor(double val1, double val2) {
        color = new Color(159, 215, 247);

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
