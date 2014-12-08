/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.Logging;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge
 */
public class LayerShapefileLoader
        extends ShapefileLoader {

    protected RenderableLayer layer;

    public LayerShapefileLoader() {
    }

    public LayerShapefileLoader(RenderableLayer layer) {
        this.layer = layer;
    }

    /**
     * Get the value of layer
     *
     * @return the value of layer
     */
    public Layer getLayer() {
        return layer;
    }

    /**
     * Set the value of layer
     *
     * @param layer new value of layer
     */
    public void setLayer(RenderableLayer layer) {
        this.layer = layer;
    }

    @Override
    public Layer createLayerFromShapefile(Shapefile shp) {
        if (shp == null) {
            String message = Logging.getMessage("nullValue.ShapefileIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }
        if (Shapefile.isPointType(shp.getShapeType())) {
            this.addRenderablesForPoints(shp, (RenderableLayer) layer);
        } else if (Shapefile.isMultiPointType(shp.getShapeType())) {
            this.addRenderablesForMultiPoints(shp, (RenderableLayer) layer);
        } else if (Shapefile.isPolylineType(shp.getShapeType())) {
            this.addRenderablesForPolylines(shp, (RenderableLayer) layer);
        } else if (Shapefile.isPolygonType(shp.getShapeType())) {
            List<Layer> layers = new ArrayList<>();
            this.addRenderablesForPolygons(shp, layers);
            layers.add(layer);
        } else {
            Logging.logger().warning(Logging.getMessage("generic.UnrecognizedShapeType", shp.getShapeType()));
        }

        return layer;
    }

    @Override
    protected void addRenderablesForPolygons(Shapefile shp, List<Layer> layers) {
        int recordNumber = 0;
        while (shp.hasNext()) {
            try {
                ShapefileRecord record = shp.nextRecord();
                recordNumber = record.getRecordNumber();
                if (!Shapefile.isPolygonType(record.getShapeType())) {
                    continue;
                }
                ShapeAttributes attrs = this.createPolygonAttributes(record);
                this.createPolygon(record, attrs, layer);
            } catch (Exception e) {
              //  Logging.logger().warning(Logging.getMessage("SHP.ExceptionAttemptingToConvertShapefileRecord",
                //          recordNumber, e));
                // continue with the remaining records
            }
        }
    }

    @Override
    protected void addRenderablesForPolylines(Shapefile shp, RenderableLayer layer) {

        while (shp.hasNext()) {
            ShapefileRecord record = shp.nextRecord();
            if (!Shapefile.isPolylineType(record.getShapeType())) {
                continue;
            }
            ShapeAttributes attrs = this.createPolylineAttributes(record);
            layer.addRenderable(this.createPolyline(record, attrs));
        }
    }
}
