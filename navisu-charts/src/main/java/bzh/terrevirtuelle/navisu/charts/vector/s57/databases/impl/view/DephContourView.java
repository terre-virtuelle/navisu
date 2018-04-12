/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DephContourView
        extends PolylineView {

    protected RenderableLayer layer;
    protected Path path;
    protected List<Path> paths;
    protected double val;

    public DephContourView(RenderableLayer layer) {
        this.layer = layer;
        paths = new ArrayList<>();
    }

    public void display(Shapefile shp) {

        while (shp.hasNext()) {
            try {
                //Create classical chart
                record = shp.nextRecord();
                createSurfacePolylines(record, layer);
                /*
                Path p;
                for (int i = 0; i < shape.getBuffer().size(); i++) {
                    p = new Path(shape.getBuffer().subBuffer(i).getPositions());
                    p.setValue(AVKey.SHORT_DESCRIPTION, ((Double) shape.getValue("valdco")).toString());
                    p.setValue(AVKey.ABOVE_MEAN_SEA_LEVEL, ((Double) shape.getValue("valdco")).toString());
                    p.setAltitudeMode(altitudeMode);
                    paths.add(p);
                
                }
                 */
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

        //Create kml output.kml file
        /*
        Path[] array = new Path[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            array[i] = paths.get(i);
        }
        creatKML(array);
         */
        wwd.redrawNow();
    }

    protected void createSurfacePolylines(ShapefileRecord record, RenderableLayer layer) {

        if (record != null) {
            if (record.getAttributes() != null) {
                entries = record.getAttributes().getEntries();
                entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                    if (e.getKey().equalsIgnoreCase("valdco")) {
                        val = (Double) e.getValue();
                    }
                    color = SHOM_LOW_BATHYMETRY_CLUT.getColor(val);
                });
            }
            createPolyline(record);
            shape.setValue("valdco", val);
            shape.setValue(AVKey.DISPLAY_NAME, Double.toString(val));
            setPolylineAttributes(color);
            layer.addRenderable(shape);
        }
    }
}
