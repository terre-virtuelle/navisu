/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.currents.impl.controller.loader;

import bzh.terrevirtuelle.navisu.core.util.shapefile.ShapefileLoader;
//import bzh.terrevirtuelle.navisu.currents.impl.controller.GridFactory;
import bzh.terrevirtuelle.navisu.domain.currents.Current;
import bzh.terrevirtuelle.navisu.domain.currents.CurrentBuilder;
import bzh.terrevirtuelle.navisu.domain.currents.parameters.SHOM_CURRENTS_CLUT;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPoint;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwind.util.WWUtil;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge
 */
public class CurrentsShapefileLoader
        extends ShapefileLoader {

    private final List<Current> currents;
    private final List<Double> latList;
    private final List<Double> lonList;
    private double speed = 0.0;
    private double depth = 0.0;
    private double direction = 0.0;
    private Color color;
    private Sector sector;
    private double latRange;
    private double lonRange;
    private double minLat;
    private double minLon;

    public CurrentsShapefileLoader() {
        currents = new ArrayList<>();
        latList = new ArrayList<>();
        lonList = new ArrayList<>();
    }

    @Override
    protected void addRenderablesForPoints(Shapefile shp, RenderableLayer layer) {
        while (shp.hasNext()) {
            ShapefileRecord record = shp.nextRecord();
            PointPlacemarkAttributes attrs = this.createPointAttributes(record);
            if (!Shapefile.isPointType(record.getShapeType())) {
                continue;
            }
            double[] point = ((ShapefileRecordPoint) record).getPoint();

            lonList.add(point[0]);
            latList.add(point[1]);
            layer.addRenderable(this.createPoint(record, point[1], point[0], attrs));
        }
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {

        Set<Entry<String, Object>> entries = record.getAttributes().getEntries();

        entries.stream().map((e) -> {
            if (((String) e.getKey()).contains("vit")) {
                this.speed = (Double) e.getValue();
            }
            return e;
        }).map((e) -> {
            if (((String) e.getKey()).contains("dir")) {
                this.direction = (Double) e.getValue();
            }
            return e;
        }).filter((e) -> (((String) e.getKey()).contains("ELEVATION"))).forEach((e) -> {
            this.depth = (Double) e.getValue();
        });
        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setAttributes(attrs);
        NumberFormat nf = new DecimalFormat("0.#");

        String label = "Lat : " + latDegrees + " °\n"
                + "Lon : " + lonDegrees + " °\n"
                + "Speed : " + nf.format(speed * 2) + " NM\n"
                + "Direction : " + nf.format(direction) + " °\n";
        currents.add(CurrentBuilder.create()
                .latitude(latDegrees)
                .longitude(lonDegrees)
                .speed(speed)
                .direction(direction)
                .build());
        placemark.setValue(AVKey.DISPLAY_NAME, label);

        return placemark;
    }

    @Override
    public List<Layer> createLayersFromSource(Object source) {
        if (WWUtil.isEmpty(source)) {
            String message = Logging.getMessage("nullValue.SourceIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }
        Shapefile shp = null;
        try {
            shp = new Shapefile(source);
            List<Layer> layers = this.createLayersFromShapefile(shp);

            //    GridFactory gridFactory = new GridFactory();
            //     System.out.println("minLat " + gridFactory.getMin(latList) + "  maxLat " + gridFactory.getMax(latList));
            //    System.out.println("minLon " + gridFactory.getMin(lonList) + "  maxLon " + gridFactory.getMax(lonList));
            // Sector.fromDegrees(48.2639, 48.5954, -5.37804, -4.75236
            minLat = latList.stream().min(Double::compare).get();
            double maxLat = latList.stream().max(Double::compare).get();
            minLon = lonList.stream().min(Double::compare).get();
            double maxLon = lonList.stream().max(Double::compare).get();
            latRange = Math.abs(Math.abs(maxLat) - Math.abs(minLat));
            lonRange = Math.abs(Math.abs(maxLon) - Math.abs(minLon));
            sector = Sector.fromDegrees(minLat, maxLat, minLon, maxLon);
            return layers;
        } finally {
            WWIO.closeStream(shp, source.toString());
        }

    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected PointPlacemarkAttributes createPointAttributes(ShapefileRecord record) {

        record.getAttributes().getEntries().stream().filter((e) -> (e.getKey().contains("vit"))).forEach((e) -> {
            //   System.out.println("vit : " + (Double) e.getValue() * 2); //en Noeuds
            this.speed = (Double) e.getValue();
        });
        PointPlacemarkAttributes attributes = new PointPlacemarkAttributes();
        attributes.setUsePointAsDefaultImage(true);
        color = SHOM_CURRENTS_CLUT.getColor(speed);
        if (color != null) {
            attributes.setLineMaterial(new Material(color));
        }
        attributes.setScale(5d);
        return attributes;
    }

    public List<Current> getCurrents() {
        return currents;
    }

    public Sector getSector() {
        return sector;
    }

    public double getLatRange() {
        return latRange;
    }

    public double getLonRange() {
        return lonRange;
    }

    public List<Double> getLatList() {
        return latList;
    }

    public List<Double> getLonList() {
        return lonList;
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMinLon() {
        return minLon;
    }

}
