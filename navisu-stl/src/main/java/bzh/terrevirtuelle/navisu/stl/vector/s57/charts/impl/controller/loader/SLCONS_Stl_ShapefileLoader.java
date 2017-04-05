/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SLCONS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.util.Pair;
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
    protected double height = 1;
    String result = "";
    int i = 0;
    Position orig;

    public SLCONS_Stl_ShapefileLoader(String filename, RenderableLayer layer, Polygon polyEnveloppe) {
        this.layer = layer;
        this.filename = filename;
        this.polyEnveloppe = polyEnveloppe;
        List<List<? extends Position>> positions = polyEnveloppe.getBoundaries();
        System.out.println("positions : " + positions);
        System.out.println("getOuterBoundary : "+polyEnveloppe.getOuterBoundary());
        orig = positions.get(0).get(0);
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
        normalAttributes.setOutlineMaterial(Material.BLUE);
        normalAttributes.setOutlineWidth(2.0);

        return normalAttributes;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        Color color = Color.BLACK;
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        return normalAttributes;
    }

    protected void createBuffers(Iterable<? extends LatLon> latLon) {

        List<Position> positionList = new ArrayList<>();

        for (LatLon l : latLon) {
            positionList.add(new Position(l, 10.0));
        }

        Path path = new Path(positionList);
        layer.addRenderable(path);

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.GREEN);
        normalAttributes.setOutlineWidth(2.0);

        if (positionList.size() > 3 && positionList.get(0).equals(positionList.get(positionList.size() - 1))) {
            Polygon polygon = new Polygon(positionList);
            polygon.setAttributes(normalAttributes);
            layer.addRenderable(polygon);
            //  if(polyEnveloppe.outerBoundary().contains(polygon.getBoundaries())){
            createSlCons(positionList);
            // }
        }

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

    protected void createSlCons(List<Position> positionList) {
        /*
        List<LatLon> latLonList = new ArrayList<>();
        for (Position p : positionList) {
            latLonList.add(new LatLon(p.getLatitude(), p.getLongitude()));
        }
        Geometry geo = WwjJTS.filter(WwjJTS.PolygonToGeometry(polyEnveloppe), latLonList);
        List<Position> ptsFiltered = null;
        if (!geo.toString().contains("EMPTY") && !geo.toString().contains("MULTIPOLYGON")) {
            ptsFiltered = WwjJTS.wktPolygonToPositionList(geo.toString());
        }
        if (ptsFiltered != null) {
            if (!ptsFiltered.isEmpty()) {
         */
        //"<Transform rotation='0 0 1 3.14'> \n"
        result
                += " <Shape>\n"
                + "<Appearance>\n"
                + " <Material diffuseColor='1.0 1. 1.0'/> \n"
                + "</Appearance>\n"
                + "<Extrusion convex='true' \n"
                + " crossSection='";

        positionList.stream().map((p) -> WwjGeodesy.getXYM(orig, p)).forEachOrdered((xy) -> {
            double x = 200 - xy.getX()*11.85;//*11.85;
            double y = -200 + xy.getY()*11.85;//*11.85;
            result += x + " " + y + (",");
        });

        result += "'\n "
                + "solid='false' \n"
                + "spine='0 0 0 0 " + height + " 0'/>\n"
                + "</Shape>\n";
    }

    public String compute() {
        return result;
    }
}
