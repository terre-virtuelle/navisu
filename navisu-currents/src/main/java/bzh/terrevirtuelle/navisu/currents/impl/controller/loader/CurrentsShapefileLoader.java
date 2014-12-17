/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.currents.impl.controller.loader;

import bzh.terrevirtuelle.navisu.core.util.shapefile.ShapefileLoader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPoint;
import gov.nasa.worldwind.geom.Position;
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

    private final List<Double> latList;
    private final List<Double> lonList;

    public CurrentsShapefileLoader() {
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
            latList.add(point[1]);
            lonList.add(point[0]);
            layer.addRenderable(this.createPoint(record, point[1], point[0], attrs));
        }
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        //  System.out.println(latDegrees + " " + lonDegrees + " " + record.getAttributes().getEntries());
        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setAttributes(attrs);
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
            //  System.out.println("latList.size : " + latList.size() + " lonList.size : " + lonList.size());
            double maxLat = latList.get(0);
            double minLat = latList.get(0);
            double maxLon = lonList.get(0);
            double minLon = lonList.get(0);
            for (double l : latList) {
                if (maxLat < l) {
                    maxLat = l;
                }
                if (minLat > l) {
                    minLat = l;
                }
            }
            for (double l : lonList) {
                if (maxLon < l) {
                    maxLon = l;
                }
                if (minLon > l) {
                    minLon = l;
                }
            }
            System.out.println("minLat " + minLat + "  maxLat " + maxLat);
            System.out.println("minLon " + minLon + "  maxLon " + maxLon);

            // List<Integer> range = IntStream.range(0, 500).boxed().collect(Collectors.toList());
            /*
             Stream<List<Integer>> integerListStream = Stream.of(
             Arrays.asList(1, 2),
             Arrays.asList(3, 4),
             Arrays.asList(5)
             );

             Stream<Integer> integerStream = integerListStream.flatMap((integerList) -> integerList.stream());
             integerStream.forEach(System.out::println);
             */
            return layers;

        } finally {
            WWIO.closeStream(shp, source.toString());
        }

    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected PointPlacemarkAttributes createPointAttributes(ShapefileRecord record) {
        record.getAttributes().getEntries().stream().filter((e) -> (e.getKey().contains("vit"))).forEach((e) -> {
            System.out.println("vit : " + (Double)e.getValue() * 2); //en Noeuds
        });
        PointPlacemarkAttributes attributes = new PointPlacemarkAttributes();
        attributes.setUsePointAsDefaultImage(true);
        attributes.setLineMaterial(new Material(Color.RED));
        attributes.setScale(7d);
        return attributes;
    }
}
