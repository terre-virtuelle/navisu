/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolyline;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolylines;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwind.util.WWUtil;





public class PolyLineView {

   
    public PolyLineView() {
    }

    public Layer createLayerFromSource(Object source) {
        if (WWUtil.isEmpty(source)) {
            String message = Logging.getMessage("nullValue.SourceIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }
        Shapefile shp = null;
        Layer layer = null;
        try {
            shp = new Shapefile(source);
            layer = this.createLayerFromShapefile(shp);
        } finally {
            WWIO.closeStream(shp, source.toString());
        }

        return layer;
    }


    public Layer createLayerFromShapefile(Shapefile shp) {
        if (shp == null) {
            String message = Logging.getMessage("nullValue.ShapefileIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }
        Layer layer = null;
        if (Shapefile.isPolylineType(shp.getShapeType())) {
            layer = new RenderableLayer();
            this.addRenderablesForPolylines(shp, (RenderableLayer) layer);
        } else {
            Logging.logger().warning(Logging.getMessage("generic.UnrecognizedShapeType", shp.getShapeType()));
        }

        return layer;
    }

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

    protected Renderable createPolyline(ShapefileRecord record, ShapeAttributes attrs) {
        SurfacePolylines shape = new SurfacePolylines(
                Sector.fromDegrees(((ShapefileRecordPolyline) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        shape.setAttributes(attrs);
        return shape;
    }

    protected Renderable createPolyline(Shapefile shp, ShapeAttributes attrs) {
        SurfacePolylines shape = new SurfacePolylines(Sector.fromDegrees(shp.getBoundingRectangle()),
                shp.getPointBuffer());
        shape.setAttributes(attrs);
        return shape;
    }

   
    @SuppressWarnings({"UnusedDeclaration"})
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        if (record != null) {
          //  System.out.println("createPolylineAttributes " + record.getAttributes().getEntries());
        }
        return null;
    }    
}
