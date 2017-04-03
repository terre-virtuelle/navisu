/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SLCONS_ShapefileLoader;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.operation.buffer.BufferOp;
import com.vividsolutions.jts.operation.buffer.BufferParameters;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.CompoundVecBuffer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Mar 20, 2017
 */
public class SLCONS_Stl_ShapefileLoader
        extends SLCONS_ShapefileLoader {

    protected String filename;
    protected Polygon polyEnveloppe;
    protected Shapefile shapefile;
    protected Polygon offset;
    protected RenderableLayer layer;
    // protected List<Polygon> polygonList;
    protected double bufferDistance = 10;
    int i = 0;

    public SLCONS_Stl_ShapefileLoader(String filename, RenderableLayer layer, Polygon polyEnveloppe) {
        this.layer = layer;
        this.filename = filename;
        this.polyEnveloppe = polyEnveloppe;

    }

    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        this.record = record;
        shapefile = record.getShapeFile();

        CompoundVecBuffer buffer = record.getCompoundPointBuffer();
        createBuffers(buffer.getLocations());

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        // Color color = Color.WHITE;
        //  float[] cc = color.getRGBComponents(null);
        //  color = new Color(cc[0] * (float) Math.random(), cc[1] * (float) Math.random(), cc[2] * (float) Math.random(),cc[3]);
        //  normalAttributes.setOutlineMaterial(new Material(color));//Material.BLUE);
        normalAttributes.setOutlineMaterial(Material.BLUE);
        normalAttributes.setOutlineWidth(2.0);

        return normalAttributes;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        // System.out.println("SLCONS_Stl_ShapefileLoader createPolygonAttributes");
        Color color = Color.BLACK;

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        return normalAttributes;
    }

    protected void createBuffers(Iterable<? extends LatLon> latLon) {

        List<Position> positionList = new ArrayList<>();
       
        for (LatLon l : latLon) {
            positionList.add(new Position(l, 100.0));
        }
        
        Path path = new Path(positionList);
        layer.addRenderable(path);
        /*
        Coordinate[] coordinates = new Coordinate[latLonList.size()];
        for (int i = 0; i < latLonList.size(); i++) {
            coordinates[i] = new Coordinate(latLonList.get(i).getLongitude().getDegrees(),
                    latLonList.get(i).getLatitude().getDegrees());
        }
        Geometry geom = new GeometryFactory().createLineString(coordinates);

        Geometry offsetBuffer;
        BufferOp bufferOp = new BufferOp(geom);
        bufferOp.setEndCapStyle(BufferParameters.CAP_FLAT);//CAP_ROUND);
        offsetBuffer = bufferOp.getResultGeometry(bufferDistance);
        List<Position> offsetPathPositions = new ArrayList<>();
        for (Coordinate c : offsetBuffer.getCoordinates()) {
            offsetPathPositions.add(Position.fromDegrees(c.y, c.x, 100));
        }
        if (!offsetPathPositions.isEmpty()) {
            offset = new Polygon(offsetPathPositions);
        }
        //  offset.setValue(AVKey.DISPLAY_NAME, routeName);
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.GREEN);
        normalAttributes.setOutlineWidth(2.0);

        offset.setAttributes(normalAttributes);
        layer.addRenderable(offset);
*/
        
        
    }
}
