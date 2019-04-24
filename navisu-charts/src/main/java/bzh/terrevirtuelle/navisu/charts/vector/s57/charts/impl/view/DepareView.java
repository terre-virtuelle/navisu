/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polygon;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DepareView
        extends ShapefilePolygonView {

    protected double val1;
    protected double val2;
    protected RenderableLayer layer;
    protected RenderableLayer simpleDeparelayer;
    protected RenderableLayer depare3DLayer;
    protected List<Polygon> polygons = new ArrayList<>();
    double simplify = 0.001;
    boolean isSimplify = false;
    double magnify;
    double maxHeight;
    boolean isCreateElevation;
    protected String sep = File.separator;

    public DepareView(RenderableLayer layer, RenderableLayer simpleDeparelayer, RenderableLayer depare3DLayer,
            double simplify,
            double maxHeight, double magnify,
            boolean isSimplify, boolean isCreateElevation) {
        this.layer = layer;
        this.simpleDeparelayer = simpleDeparelayer;
        this.depare3DLayer = depare3DLayer;
        this.maxHeight = maxHeight;
        this.simplify = simplify;
        this.isSimplify = isSimplify;
        this.magnify = magnify;
        this.isCreateElevation = isCreateElevation;
        new File("cmd").mkdir();
    }

    public void display(Shapefile shp) {
        while (shp.hasNext()) {
            try {
                //Create classical chart
                record = shp.nextRecord();
                createSurfacePolygons(record, layer, isCreateElevation, false);
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

        wwd.redrawNow();

    }

    protected void createSurfacePolygons(ShapefileRecord record,
            RenderableLayer layer,
            boolean isHeight, boolean simp) {

        if (record != null) {
            if (record.getAttributes() != null) {
                //Calculate depth max
                entries = record.getAttributes().getEntries();
                entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                    if (e.getKey().equalsIgnoreCase("drval1")) {
                        val1 = (Double) e.getValue();
                    }
                    color = SHOM_LOW_BATHYMETRY_CLUT.getColor(val1);
                });
            }
            createPolygon(layer, record, isHeight, magnify, maxHeight);
        }
    }
}
