/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolyline;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.awt.Color;

public class ShapefilePolylineView
        extends ShapefileView {

    protected SurfacePolylines shape;

    public ShapefilePolylineView() {
    }

    protected void createPolyline(ShapefileRecord record) {
        shape = new SurfacePolylines(
                Sector.fromDegrees(((ShapefileRecordPolyline) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
    }

    protected void setPolylineAttributes(Color col) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setDrawOutline(true);
        normAttributes.setOutlineMaterial(new Material(col));
        normAttributes.setEnableLighting(true);
        shape.setAttributes(normAttributes);

        shape.setHighlightAttributes(highlightAttributes);
    }
}
