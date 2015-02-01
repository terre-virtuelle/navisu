/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.controller.loader;

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
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Serge
 */
public class BathymetryLocalCatalogLoader {

    List<String> lines;
    List<Sector> sectors;
    List<Layer> layers;
    RenderableLayer layer;

    public BathymetryLocalCatalogLoader() {
        this.lines = new ArrayList<>();
        this.sectors = new ArrayList<>();
        this.layers = new ArrayList<>();
        layer = new RenderableLayer();
    }

    public List<Layer> createLayersFromSource(String source) {
        if (WWUtil.isEmpty(source)) {
            String message = Logging.getMessage("nullValue.SourceIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }
        try {
            lines = Files.lines(Paths.get(source)).collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(BathymetryLocalCatalogLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] tab;
        ArrayList<Position> pathPositions;
        // Create and set an attribute bundle.
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.YELLOW);
        normalAttributes.setOutlineWidth(1);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        normalAttributes.setEnableLighting(true);

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineMaterial(Material.WHITE);
        highlightAttributes.setDrawInterior(false);
        highlightAttributes.setDrawOutline(true);
        highlightAttributes.setOutlineOpacity(1);
        for (String s : lines) {
            tab = s.split(" ");
            pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(new Double(tab[2]), new Double(tab[0]), 2500));
            pathPositions.add(Position.fromDegrees(new Double(tab[3]), new Double(tab[0]), 2500));
            pathPositions.add(Position.fromDegrees(new Double(tab[3]), new Double(tab[1]), 2500));
            pathPositions.add(Position.fromDegrees(new Double(tab[2]), new Double(tab[1]), 2500));
            Polygon pgon = new Polygon(pathPositions);
            pgon.setValue(AVKey.DISPLAY_NAME, tab[4]);
             pgon.setAltitudeMode(WorldWind.GREAT_CIRCLE);
            pgon.setAttributes(normalAttributes);
            pgon.setHighlightAttributes(highlightAttributes);
            layer.addRenderable(pgon);
        }
        layers.add(layer);
        lines.clear();
        return layers;
    }
}
