/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DepareView
        extends PolygonView {

    protected double val1;
    protected double val2;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public DepareView(RenderableLayer layer) {
        super(layer);
    }

    public void display(Shapefile shp) {
        ShapeAttributes capAttrs = new BasicShapeAttributes();
        capAttrs.setDrawOutline(true);
        capAttrs.setDrawInterior(true);
        capAttrs.setOutlineMaterial(Material.BLUE);
        capAttrs.setInteriorMaterial(Material.CYAN);
        capAttrs.setEnableLighting(true);

        ShapeAttributes sideAttrs = new BasicShapeAttributes();
        sideAttrs.setOutlineWidth(3);
        sideAttrs.setDrawOutline(true);
        sideAttrs.setDrawInterior(true);
        sideAttrs.setOutlineMaterial(Material.BLUE);
        sideAttrs.setInteriorMaterial(Material.BLUE);
        sideAttrs.setEnableLighting(true);
        int altitudeMode = WorldWind.CONSTANT;
        while (shp.hasNext()) {
            try {
                record = shp.nextRecord();
                if (record != null) {
                    if (record.getAttributes() != null) {
                        entries = record.getAttributes().getEntries();
                        entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                            if (e.getKey().equalsIgnoreCase("drval1")) {
                                val1 = (Double) e.getValue();
                            }
                            if (e.getKey().equalsIgnoreCase("drval2")) {
                                val2 = (Double) e.getValue();
                            }
                            color = defineColor(val1, val2);
                        });
                    }
                    if (!Shapefile.isPolygonType(record.getShapeType())) {
                        continue;
                    }

                    createPolygon(record);
                    setPolygonAttributes(shape, color);
                    /*
                    List<ExtrudedPolygon> polyList = new ArrayList<>();
                    ExtrudedPolygon pgon;
                    for (int i = 0; i < shape.getBuffer().size(); i++) {
                        for (int j = 0; j < shape.getBuffer().subBuffer(i).getSize(); j++) {

                            pgon = new ExtrudedPolygon(shape.getBuffer().subBuffer(i).getPositions(), 1200-val2*50);
                            pgon.setAltitudeMode(altitudeMode);
                            pgon.setEnableSides(true);
                            pgon.setEnableCap(true);
                            pgon.setCapAttributes(capAttrs);
                            pgon.setSideAttributes(sideAttrs);
                            polyList.add(pgon);
                        }
                    }
                    layer.addRenderables(polyList);
*/
                      layer.addRenderable(shape);
                    label = "";
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        wwd.redrawNow();
    }
}
