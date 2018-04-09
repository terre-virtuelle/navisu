/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.SurfacePolygons;
import java.awt.Color;
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
        extends PolygonView {

    protected double val1;
    protected double val2;
    protected RenderableLayer layer;
    protected RenderableLayer simpleDeparelayer;
    protected RenderableLayer depare3DLayer;
    protected ShapefileObjectServices shapefileObjectServices;
    protected JTSServices jtsServices;
    protected TopologyServices topologyServices;
    protected DelaunayServices delaunayServices;
    protected DisplayServices displayServices;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected List<Path> paths = new ArrayList<>();
    protected List<Polygon> polygons = new ArrayList<>();
    protected List<SurfacePolygons> surfacePolygons = new ArrayList<>();

    int altitudeMode = WorldWind.RELATIVE_TO_GROUND;
    protected double latMin;
    protected double lonMin;
    protected double latMax;
    protected double lonMax;
    double simplify;
    double magnify;
    double maxHeight = 0.0;
    boolean createElevation;

    public DepareView(ShapefileObjectServices shapefileObjectServices,
            JTSServices jtsServices,
            TopologyServices topologyServices,
            DelaunayServices delaunayServices,
            DisplayServices displayServices,
            double latMin, double lonMin, double latMax, double lonMax,
            RenderableLayer layer, RenderableLayer simpleDeparelayer, RenderableLayer depare3DLayer,
            double simplify, double magnify, boolean showElevation) {
        this.shapefileObjectServices = shapefileObjectServices;
        this.jtsServices = jtsServices;
        this.topologyServices = topologyServices;
        this.delaunayServices = delaunayServices;
        this.displayServices = displayServices;
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

    protected void createSurfacePolygons(ShapefileRecord record, RenderableLayer layer, boolean isHeight, boolean simp) {

        if (record != null) {

            if (record.getAttributes() != null) {
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
                    color = defineColor(val1, val2);
                });
            }

            createPolygon(layer, record, isHeight, magnify, maxHeight, simp);
            shape.setValue("drval1", val1);
            shape.setValue("drval2", val2);
            shape.setValue(AVKey.DISPLAY_NAME,
                    "[" + Double.toString(val1) + ", " + Double.toString(val2) + "]");
            setPolygonAttributes(shape, color);
            surfacePolygons.add(shape);
        }
    }

    @Override
    protected void setPolygonAttributes(SurfacePolygons shape, Color color) {

        normalAttributes.setDrawInterior(true);
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(Color.BLACK));
        normalAttributes.setEnableLighting(true);
        shape.setAttributes(normalAttributes);

        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(Color.WHITE));
        highlightAttributes.setInteriorOpacity(.5);
        highlightAttributes.setEnableLighting(true);

        shape.setHighlightAttributes(highlightAttributes);
    }

    @Override
    protected void setExtrudedPolygonAttributes(ExtrudedPolygon ep) {
        capAttrs.setDrawOutline(true);
        capAttrs.setDrawInterior(true);
        capAttrs.setOutlineMaterial(Material.BLUE);
        capAttrs.setInteriorMaterial(Material.CYAN);
        capAttrs.setEnableLighting(true);
        ep.setCapAttributes(capAttrs);

        sideAttrs.setOutlineWidth(3);
        sideAttrs.setDrawOutline(true);
        sideAttrs.setDrawInterior(true);
        sideAttrs.setOutlineMaterial(Material.BLUE);
        sideAttrs.setInteriorMaterial(Material.BLUE);
        sideAttrs.setEnableLighting(true);
        ep.setSideAttributes(sideAttrs);
    }

    protected Color defineColor(double val1, double val2) {
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
