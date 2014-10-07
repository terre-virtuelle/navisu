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

    private PointPlacemarkAttributes attrs;
    private Set<Entry<String, Object>> entries;
    private final Map<Pair, String> topMarks;

    public TOPMAR_ShapefileLoader(Map<Pair, String> topMarks) {
        this.topMarks = topMarks;
    }

    @Override
    protected void addRenderablesForPoints(Shapefile shp, RenderableLayer layer) {

        while (shp.hasNext()) {
            ShapefileRecord record = shp.nextRecord();
            if (!Shapefile.isPointType(record.getShapeType())) {
                continue;
            }
            double[] point = ((ShapefileRecordPoint) record).getPoint();
            entries = record.getAttributes().getEntries();
            this.createPoint(record, point[1], point[0], attrs);
        }
    }

    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        entries = record.getAttributes().getEntries();
        entries.stream().forEach((e) -> {
            if (e.getKey().equals("TOPSHP")) {
                topMarks.put(new Pair(latDegrees, lonDegrees), ((Long) e.getValue()).toString());
            }
        });
        return null;
    }
}
