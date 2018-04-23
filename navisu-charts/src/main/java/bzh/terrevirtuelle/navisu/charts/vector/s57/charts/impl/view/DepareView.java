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

    double simplify;
    double magnify;
    double maxHeight = 0.0;
    boolean createElevation;

    public DepareView( double latMin, double lonMin, double latMax, double lonMax,
            RenderableLayer layer, RenderableLayer simpleDeparelayer, RenderableLayer depare3DLayer,
            double simplify, double magnify, boolean showElevation) {
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latMax = latMax;
        this.lonMax = lonMax;
        this.layer = layer;
        this.simpleDeparelayer = simpleDeparelayer;
        this.depare3DLayer = depare3DLayer;
        this.simplify = simplify;
        this.magnify = magnify;
        this.createElevation = showElevation;
    }

    public void display(Shapefile shp) {

        while (shp.hasNext()) {
            try {
                //Create classical chart
                record = shp.nextRecord();
                createSurfacePolygons(record, layer, false, false);
                if (!Shapefile.isPolygonType(record.getShapeType())) {
                    continue;
                }

                Polygon p;
                for (int i = 0; i < shape.getBuffer().size(); i++) {
                    p = new Polygon(shape.getBuffer().subBuffer(i).getPositions());
                    p.setValue(AVKey.SHORT_DESCRIPTION, ((Double) shape.getValue("drval1")).toString());
                    p.setValue(AVKey.BALLOON_TEXT, ((Double) shape.getValue("drval2")).toString());
                    p.setValue(AVKey.ABOVE_MEAN_SEA_LEVEL, ((Double) shape.getValue("drval2")).toString());
                    p.setAltitudeMode(altitudeMode);
                    polygons.add(p);
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

        //Create kml output.kml file
        Polygon[] array = new Polygon[polygons.size()];
        for (int i = 0; i < polygons.size(); i++) {
            array[i] = polygons.get(i);
        }
        
        creatKML(array);
        
        //Simplify data and create depare.shp
        String path = Proc.getProperty("gdalPath");
        String command = path + "/ogr2ogr -f 'ESRI Shapefile' cmd/output.shp cmd/output.kml \n"
                + path + "/ogr2ogr cmd/outfileSimplify.shp cmd/depare.shp -simplify " + simplify;
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        //Create objectShapefile with data simplified
        //Create extruded polygons with data simplified
        Shapefile simplifiedShape = new Shapefile("cmd/outfileSimplify.shp");
        while (simplifiedShape.hasNext()) {
            try {
                record = simplifiedShape.nextRecord();
                createSurfacePolygons(record, simpleDeparelayer, false, true);
                if (createElevation) {
                    createSurfacePolygons(record, depare3DLayer, true, false);
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

        wwd.redrawNow();

        /*
        //Test lancement Ulhysses
        String ulhyssesPath = "/opt/ULHYSSES/app/";
        command
                = "cd " + ulhyssesPath + " \n"
                + "java "
                + "-Dlog4j.configuration=file:" + ulhyssesPath + "conf-tools/toolsLog4j.properties "
                + "-Xmx14g -Xms1024m -jar " + ulhyssesPath + "ULHYSSES.jar "
                + "--outputDirectory=" + System.getProperty("user.dir")+"/cmd "
                + "--inputFile=/home/serge/Data/navisu/arnaud/testBathy_RADE_100_xyz.csv "
                + "--compilationScale=1000 --fileType=0 --isoValues='0;2;4;6;8;10;12;14;16;18;20' "
                + "--codeAgency=4G --baseName=0001";

        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
         */
    }

    protected void createSurfacePolygons(ShapefileRecord record, RenderableLayer layer,
            boolean isHeight, boolean simp) {

        if (record != null) {
            if (record.getAttributes() != null) {
                //Calculate depth max
                entries = record.getAttributes().getEntries();
                entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {

                    if (e.getKey().equalsIgnoreCase("drval1")) {
                        val1 = (Double) e.getValue();
                    }
                    if (e.getKey().equalsIgnoreCase("drval2")) {
                        val2 = (Double) e.getValue();
                        if (val2 > maxHeight) {
                            maxHeight = val2;
                        }
                    }
                     color = SHOM_LOW_BATHYMETRY_CLUT.getColor(val1);
                });
            }

            createPolygon(layer, record, isHeight, magnify, maxHeight, simp);
            shape.setValue("drval1", val1);
            shape.setValue("drval2", val2);
            shape.setValue(AVKey.DISPLAY_NAME,
                    "[" + Double.toString(val1) + ", " + Double.toString(val2) + "]");
            setPolygonAttributes(color);
            layer.addRenderable(shape);
        }
    }

}
