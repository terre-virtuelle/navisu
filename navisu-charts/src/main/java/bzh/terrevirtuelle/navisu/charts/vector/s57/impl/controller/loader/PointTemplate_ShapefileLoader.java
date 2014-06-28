/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPoint;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class PointTemplate_ShapefileLoader
        extends ShapefileLoader {

    PointPlacemark placemark;
    List<PointPlacemark> placemarks;
    PointPlacemarkAttributes attrs;
    StringBuilder label;
    String objName;
    String imgStr = "img/wrecks/danger02.png";
    private Set<Entry<String, Object>> entries;

    public PointTemplate_ShapefileLoader() {
        placemarks = new ArrayList<>();
    }

    public PointTemplate_ShapefileLoader(String objName) {
        this.objName = objName;
        placemarks = new ArrayList<>();
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
            layer.addRenderable(this.createPoint(record, point[1], point[0], attrs));
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
        label = new StringBuilder();
        label.append(objName).append("\n");
        label.append("Lat : ").append(new Float(latDegrees)).append("\n");
        label.append("Lon : ").append(new Float(lonDegrees)).append("\n");
        placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        placemarks.add(placemark);
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        entries.stream().forEach((e) -> {
            if (e.getKey() != null && e.getValue() != null) {
                label.append(e.getKey()).append("=").append(e.getValue()).append("\n");
            }
        });
        placemark.setValue(AVKey.DISPLAY_NAME, label.toString());
        attrs.setScale(1.0);
        placemark.setAttributes(attrs);
        return placemark;
    }

    public List<PointPlacemark> getPlacemarks() {
        return placemarks;
    }

}
