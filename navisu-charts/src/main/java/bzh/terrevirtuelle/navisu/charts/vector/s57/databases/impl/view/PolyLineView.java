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

public class PolyLineView
        extends ShapeFileView {

    public PolyLineView() {
    }

    protected void createPolyline(RenderableLayer layer, ShapefileRecord record) {
        SurfacePolylines shape = new SurfacePolylines(
                Sector.fromDegrees(((ShapefileRecordPolyline) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        layer.addRenderable(shape);

    }

    protected void createPolyline(RenderableLayer layer, Shapefile shp) {
        SurfacePolylines shape = new SurfacePolylines(Sector.fromDegrees(shp.getBoundingRectangle()),
                shp.getPointBuffer());
        layer.addRenderable(shape);
    }

}
