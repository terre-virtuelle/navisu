/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    private final List<String> lines;
    private final List<Sector> sectors;
    private final List<Layer> layers;
    private List<Point3D> points3d;
    private final RenderableLayer layer;
    private ShapeAttributes normalAttributes;
    private ShapeAttributes highlightAttributes;
    protected static final Logger LOGGER = Logger.getLogger(BathymetryLocalCatalogLoader.class.getName());

    public BathymetryLocalCatalogLoader() {
        this.lines = new ArrayList<>();
        this.sectors = new ArrayList<>();
        this.layers = new ArrayList<>();
        layer = new RenderableLayer();
    }

    public List<Layer> createLayersFromSource(String source) {
        readfile(source);
        createAttributes();
        // createPolygons();
        //createPoints();
        return createShomBoundary();
    }

    private List<Layer> createShomBoundary() {
        ArrayList<Position> pathPositions = new ArrayList<>();
        pathPositions.add(Position.fromDegrees(points3d.get(8).getLat(), points3d.get(8).getLon(), 250));
        pathPositions.add(Position.fromDegrees(points3d.get(6).getLat(), points3d.get(6).getLon(), 250));
        pathPositions.add(Position.fromDegrees(points3d.get(4).getLat(), points3d.get(4).getLon(), 250));
        pathPositions.add(Position.fromDegrees(points3d.get(0).getLat(), points3d.get(0).getLon(), 250));
        for (int i = 1; i <= 157; i += 2) {
            pathPositions.add(Position.fromDegrees(points3d.get(i).getLat(), points3d.get(i).getLon(), 500));
        }
        Path pgon = new Path(pathPositions);
        pgon.setAltitudeMode(WorldWind.GREAT_CIRCLE);
        pgon.setAttributes(normalAttributes);
        pgon.setHighlightAttributes(highlightAttributes);
        layer.addRenderable(pgon);
        layer.setName("Shom");
        layers.add(layer);
        return layers;
    }

    private List<Layer> createPolygons() {
        ArrayList<Position> pathPositions;
        for (String s : lines) {
            String[] tab = s.split(" ");
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

    private List<Layer> createPoints() {
        PointPlacemarkAttributes attributes = new PointPlacemarkAttributes();

        //  attributes.setUsePointAsDefaultImage(true);
        PointPlacemark placemark;
        for (Point3D p : points3d) {
            placemark = new PointPlacemark(Position.fromDegrees(p.getLat(), p.getLon(), 100));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            placemark.setEnableBatchPicking(true);
            placemark.setAttributes(attributes);

            String label = "Id : " + p.getId()
                    + "Lat : " + p.getLat() + " °\n"
                    + "Lon : " + p.getLon() + " °\n";
            placemark.setValue(AVKey.DISPLAY_NAME, label);
            layer.addRenderable(placemark);
        }
        layers.add(layer);
        return layers;
    }

    private void readfile(String source) {
        try {
            points3d = Files.lines(new File(source).toPath())
                    .map(line -> line.trim())
                    .map(line -> line.split(" "))
                    .map(tab -> new Point3D(Integer.parseInt(tab[0]),
                                    Double.parseDouble(tab[1]),
                                    Double.parseDouble(tab[2])))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private void createAttributes() {
        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.YELLOW);
        normalAttributes.setOutlineWidth(1);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        normalAttributes.setEnableLighting(true);

        highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineMaterial(Material.WHITE);
        highlightAttributes.setDrawInterior(false);
        highlightAttributes.setDrawOutline(true);
        highlightAttributes.setOutlineOpacity(1);
    }
}
