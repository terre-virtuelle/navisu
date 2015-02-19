/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.charts.impl.controller;

import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Depth;
import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_BATHYMETRY_CLUT;
import bzh.terrevirtuelle.navisu.util.Pair;
import bzh.terrevirtuelle.navisu.util.latLon.GridFactory;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.analytics.AnalyticSurface;
import gov.nasa.worldwindx.examples.analytics.AnalyticSurfaceAttributes;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Serge
 */
public class BathymetryAnalyticController {

    private static final BathymetryAnalyticController INSTANCE;
    private final List<Layer> layers;
    private final List<Depth> depths;
    private final List<Double> latDepths;
    private final List<Double> lonDepths;
    private final Map<Pair<Double, Double>, Double> depthmap;
    private Color color;
    private RenderableLayer layer;
    private int counter = 0;
    private GridFactory gridFactory;
    private double minLat;
    private double maxLat;
    private double minLon;
    private double maxLon;
    private String fileName;
    private final int WIDTH = 132;//132
    private final int HEIGHT = 132;
    private final double[][] gridPoints;
    private double cache;

    static {
        INSTANCE = new BathymetryAnalyticController();
    }

    private BathymetryAnalyticController() {
        this.layers = new ArrayList<>();
        this.depths = new ArrayList<>();
        this.depthmap = new HashMap<>(250000);
        this.latDepths = new ArrayList<>();
        this.lonDepths = new ArrayList<>();
        this.gridPoints = new double[10200][9900];
    }

    public static BathymetryAnalyticController getInstance() {
        return INSTANCE;
    }

    public final Layer init(String fileName) {
        latDepths.clear();
        lonDepths.clear();
        layer = new RenderableLayer();
        try {
            Path path = Paths.get(fileName);
            this.fileName = path.getFileName().toString();
            Stream<String> lines = Files.lines(path);
            lines.forEach(s -> build(s));
        } catch (IOException ex) {
            Logger.getLogger(BathymetryAnalyticController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getBoundaries();
        analytic();
        return layer;
    }

    private void filter(String s) {
        if (counter == 1) {
            build(s);
            counter = 0;
        }
        counter++;
    }

    private void build(String s) {
        String[] tmp = s.split(" ");
        double lat = new Double(tmp[1]);
        double lon = new Double(tmp[0]);
        double depth = new Double(tmp[2]);
        if (cache != lat) {
            cache = lat;
            counter++;
        }
         depths.add(new Depth(lat, lon, depth));
        latDepths.add(lat);
        lonDepths.add(lon);
        // depthmap.put(new Pair(lat, lon), depth);
    }

    public void getBoundaries() {
        gridFactory = new GridFactory(latDepths, lonDepths);
        minLat = gridFactory.getMinLat();
        maxLat = gridFactory.getMaxLat();
        minLon = gridFactory.getMinLon();
        maxLon = gridFactory.getMaxLon();
    //    System.out.println("minLat " + minLat);
    //    System.out.println("maxLat " + maxLat);
    //    System.out.println("minLon " + minLon);
    //    System.out.println("maxLon " + maxLon);
    //    System.out.println("latRange " + gridFactory.getLatRange());
    //    System.out.println("lonRange " + gridFactory.getLonRange());
    //    System.out.println("latDepths " + latDepths.size() + "  lonDepths " + lonDepths.size());
    //    System.out.println("counter " + counter);
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(Material.YELLOW);
        //  normalAttributes.setOutlineOpacity(0.5);
        //  normalAttributes.setInteriorOpacity(0.8);
        normalAttributes.setOutlineMaterial(Material.GREEN);
        //   normalAttributes.setOutlineWidth(2);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        normalAttributes.setEnableLighting(true);

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineMaterial(Material.WHITE);
        highlightAttributes.setOutlineOpacity(1);
        ArrayList<Position> pathPositions = new ArrayList<>();

        pathPositions.add(Position.fromDegrees(minLat, minLon, 2e4));
        pathPositions.add(Position.fromDegrees(maxLat, minLon, 2e4));
        pathPositions.add(Position.fromDegrees(maxLat, maxLon, 2e4));
        pathPositions.add(Position.fromDegrees(minLat, maxLon, 2e4));

        Polygon polygon = new Polygon(pathPositions);
        polygon.setAttributes(normalAttributes);
        polygon.setHighlightAttributes(highlightAttributes);
        // polygon.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        polygon.setValue(AVKey.DISPLAY_NAME, this.fileName);
       //  layer.addRenderable(polygon);
    }

    private void analytic() {
        AnalyticSurface surface = new AnalyticSurface();
        surface.setSector(new Sector(Sector.fromDegrees(gridFactory.getSector()[0],
                gridFactory.getSector()[1],
                gridFactory.getSector()[2],
                gridFactory.getSector()[3])));
        double lonRange=gridFactory.getLatRange();
        double latRange= gridFactory.getLonRange();
        surface.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        surface.setDimensions(WIDTH, HEIGHT);
        surface.setClientLayer(layer);
        layer.addRenderable(surface);
        layer.setEnabled(true);
        int tmpI;
        double tmpD;
        int line = 0;
        int col = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                gridPoints[i][j] = SHOM_BATHYMETRY_CLUT.MAX;
            }
        }
       
        for (Depth c : depths) {
            tmpD = Math.abs((Math.abs(c.getLongitude()) - minLon) / lonRange);
            tmpI = (int) (tmpD);
            if (tmpD - tmpI > 0) {
                if (tmpI < WIDTH - 1) {//-2
                    col = tmpI;//1
                } else {
                    col = tmpI;
                }
            }
            tmpD = Math.abs((Math.abs(c.getLatitude()) - minLat) / latRange);
            tmpI = (int) (tmpD);

            if (tmpD - tmpI > 0) {
                if (tmpI < HEIGHT - 1) {//-2
                    line = HEIGHT - tmpI;//+1
                } else {
                    line = HEIGHT - tmpI;
                }
            }
            if (line < WIDTH && col < HEIGHT) {
                gridPoints[line][col] = c.getDepth();
            }
        }
         ArrayList<AnalyticSurface.GridPointAttributes> attributesList = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tmpD = gridPoints[i][j];
              //  if (tmpD != 0.0) {
                    attributesList.add(AnalyticSurface.createGridPointAttributes(tmpD, SHOM_BATHYMETRY_CLUT.getColor(tmpD)));
             //   }
            }
        }
        surface.setValues(attributesList);
        AnalyticSurfaceAttributes attr = new AnalyticSurfaceAttributes();
        attr.setDrawShadow(false);
        attr.setInteriorOpacity(1);
        attr.setDrawOutline(false);
        attr.setOutlineWidth(0);
        surface.setSurfaceAttributes(attr);

    }
}
