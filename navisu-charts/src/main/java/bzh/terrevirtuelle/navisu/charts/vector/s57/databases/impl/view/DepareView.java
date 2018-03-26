/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.shapefiles.impl.controller.loader.SingleAREA_ShapefileLoader;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.RenderableLayer;
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

                    layer.addRenderable(shape);
                     label = "";
                   //  wwd.redrawNow();
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
         wwd.redrawNow();
    }
}
