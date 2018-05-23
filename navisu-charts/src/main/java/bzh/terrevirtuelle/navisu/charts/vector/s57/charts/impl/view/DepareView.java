/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polygon;
import java.io.File;
import java.io.IOException;
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
            double maxHeight,  double magnify,
            boolean isSimplify, boolean isCreateElevation) {
        this.layer = layer;
        this.simpleDeparelayer = simpleDeparelayer;
        this.depare3DLayer = depare3DLayer;
        this.maxHeight=maxHeight;
        this.simplify = simplify;
        this.isSimplify = isSimplify;
        this.magnify = magnify;
        this.isCreateElevation = isCreateElevation;
        new File("cmd").mkdir();
    }

    public void display(Shapefile shp) {
        //  System.out.println("shp : " + shp);OK
        while (shp.hasNext()) {
            try {
                //Create classical chart
                record = shp.nextRecord();
                createSurfacePolygons(record, layer, false, false);
                /*
                if (!Shapefile.isPolygonType(record.getShapeType())) {
                    continue;
                }
                */
                /*
                Polygon p;
                for (int i = 0; i < shape.getBuffer().size(); i++) {
                    p = new Polygon(shape.getBuffer().subBuffer(i).getPositions());
                    p.setValue(AVKey.SHORT_DESCRIPTION, ((Double) shape.getValue("drval1")).toString());
                    p.setValue(AVKey.BALLOON_TEXT, ((Double) shape.getValue("drval2")).toString());
                    p.setValue(AVKey.ABOVE_MEAN_SEA_LEVEL, ((Double) shape.getValue("drval2")).toString());
                    p.setAltitudeMode(altitudeMode);
                    polygons.add(p);
                    
                }
                */
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
/*
        if (isSimplify == true) {
            //Create kml output.kml file
            Polygon[] array = new Polygon[polygons.size()];
            for (int i = 0; i < polygons.size(); i++) {
                array[i] = polygons.get(i);
            }

            creatKML(array);

            //Simplify data and create depare.shp
            String path = Proc.getProperty("gdalPath");
            String command = path + sep +"ogr2ogr -f 'ESRI Shapefile' cmd"+sep+"output.shp cmd"+sep+"output.kml \n"
                    + path + sep +"ogr2ogr cmd"+sep+"outfileSimplify.shp cmd"+sep+"depare.shp -simplify " + simplify;
            try {
                Proc.BUILDER.create()
                        .setCmd(command)
                        .execSh();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }

            //Create objectShapefile with data simplified
            //Create extruded polygons with data simplified
            Shapefile simplifiedShape = new Shapefile("cmd"+sep+"outfileSimplify.shp");
            while (simplifiedShape.hasNext()) {
                try {
                    record = simplifiedShape.nextRecord();
                    createSurfacePolygons(record, simpleDeparelayer, false, true);
                    if (isCreateElevation) {
                        createSurfacePolygons(record, depare3DLayer, true, false);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
        } else {
            while (shp.hasNext()) {
                try {
                    record = shp.nextRecord();
                    createSurfacePolygons(record, simpleDeparelayer, false, true);
                    if (isCreateElevation) {
                        System.out.println("isShowElevation" + record + " " + depare3DLayer);
                        createSurfacePolygons(record, depare3DLayer, true, false);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
        }
*/
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
         //   System.out.println("shape 1 : " + shape);
         /*
         shape.setValue("drval1", val1);
            shape.setValue("drval2", val2);
            shape.setValue(AVKey.DISPLAY_NAME,
                    "[" + Double.toString(val1) + ", " + Double.toString(val2) + "]");
            setPolygonAttributes(color);
           layer.addRenderable(shape);
           */
        }
    }

}
