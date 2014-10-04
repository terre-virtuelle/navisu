 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPoint;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class TOPMAR_ShapefileLoader
        extends ShapefileLoader {

    PointPlacemark placemark;
    List<PointPlacemark> placemarks;
    PointPlacemarkAttributes attrs;
    StringBuilder label;
    String objName;
    private Set<Entry<String, Object>> entries;
    private Map<Pair, String> topMarks;

    public TOPMAR_ShapefileLoader() {
        placemarks = new ArrayList<>();
    }

    public TOPMAR_ShapefileLoader(String objName) {
        this.objName = objName;
        placemarks = new ArrayList<>();
    }

    public TOPMAR_ShapefileLoader(Map<Pair, String> topMarks) {
        placemarks = new ArrayList<>();
        this.topMarks = topMarks;
    }

    @Override
    protected void addRenderablesForPoints(Shapefile shp, RenderableLayer layer) {

        while (shp.hasNext()) {

            ShapefileRecord record = shp.nextRecord();

            if (!Shapefile.isPointType(record.getShapeType())) {
                continue;
            }
            attrs = this.createPointAttributes(record);
            double[] point = ((ShapefileRecordPoint) record).getPoint();
            // System.out.println("point " + point.length);
            entries = record.getAttributes().getEntries();

            this.createPoint(record, point[1], point[0], attrs);
        }
    }

    @Override
    protected PointPlacemarkAttributes createPointAttributes(ShapefileRecord record) {
        PointPlacemarkAttributes normalAttributes = new PointPlacemarkAttributes();
        return normalAttributes;
    }

    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        entries = record.getAttributes().getEntries();
        entries.stream().forEach((e) -> {
            if (e.getKey().equals("TOPSHP")) {
                topMarks.put(new Pair(latDegrees,lonDegrees),
                        ((Long) e.getValue()).toString());
            }
        });
        placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        return placemark;
    }

    public List<PointPlacemark> getPlacemarks() {
        return placemarks;
    }

}
