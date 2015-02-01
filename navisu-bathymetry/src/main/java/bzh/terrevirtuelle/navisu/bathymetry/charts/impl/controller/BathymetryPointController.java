/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.charts.impl.controller;

import bzh.terrevirtuelle.navisu.domain.bathymetry.Depth;
import bzh.terrevirtuelle.navisu.domain.bathymetry.parameters.SHOM_BATHYMETRY_CLUT;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
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
public class BathymetryPointController {

    private static final BathymetryPointController INSTANCE;
    private List<Layer> layers;
    private List<Depth> depths;
    private Map<Pair<Double,Double>, Double> depthmap;
    private Color color;
    RenderableLayer layer;
    int counter = 0;

    static {
        INSTANCE = new BathymetryPointController();
    }

    private BathymetryPointController() {
        this.layers = new ArrayList<>();
        this.depths = new ArrayList<>();
        this.depthmap=new HashMap<>();
    }

    public static BathymetryPointController getInstance() {
        return INSTANCE;
    }

    public final Layer init(String fileName) {
        layer = new RenderableLayer();
        try {
            Path path = Paths.get(fileName);
            Stream<String> lines = Files.lines(path);
            lines.forEach(s -> build(s));
        } catch (IOException ex) {
            Logger.getLogger(BathymetryPointController.class.getName()).log(Level.SEVERE, null, ex);
        }
     //   System.out.println(counter);
        return layer;
    }

    private void filter(String s) {
        if (counter == 10) {
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
      // depths.add(new Depth(tmp[3], lat, lon, depth));
       //new Pair(lat, lon);
        depthmap.put(new Pair(lat, lon), depth);
        
        PointPlacemarkAttributes attributes = new PointPlacemarkAttributes();
        attributes.setUsePointAsDefaultImage(true);
        color = SHOM_BATHYMETRY_CLUT.getColor(depth);
        if (color != null) {
            attributes.setLineMaterial(new Material(color));
        }
        attributes.setScale(.5d);
        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setAttributes(attributes);
        layer.addRenderable(placemark);
           placemark.setValue(AVKey.DISPLAY_NAME,Double.toString(depth)+" m");
    }
}
