/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.writer.charts;

import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import bzh.terrevirtuelle.navisu.charts.util.WwjJTS;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.PONTON_ShapefileLoader;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
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
public class PONTON_Stl_ShapefileWriter
        extends PONTON_ShapefileLoader {

    protected String filename;
    protected Polygon polyEnveloppe;
    protected Shapefile shapefile;
    protected Polygon offset;
    protected double bufferDistance = 10;
    protected double height = 1;
    protected String result = "";
    protected int i = 0;
    protected Position orig;
    protected double scaleLatFactor;
    protected double scaleLonFactor;
    protected double tileSide;

    public PONTON_Stl_ShapefileWriter(String filename, Polygon polyEnveloppe,
            double scaleLatFactor, double scaleLonFactor, double tileSide) {
        this.filename = filename;
        this.polyEnveloppe = polyEnveloppe;
        this.scaleLatFactor = scaleLatFactor;
        this.scaleLonFactor = scaleLonFactor;
        this.tileSide = tileSide;
        List<List<? extends Position>> positions = polyEnveloppe.getBoundaries();
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

        //PointPlacemark pp = new PointPlacemark(orig);
        //layer.addRenderable(pp);
        if (positionList.size() > 3 && positionList.get(0).equals(positionList.get(positionList.size() - 1))) {
            Polygon polygon = new Polygon(positionList);
            polygon.setAttributes(normalAttributes);
            layer.addRenderable(polygon);
            createSlCons(positionList);
        }

    }

    protected void createSlCons(List<Position> positionList) {

        List<LatLon> latLonList = new ArrayList<>();
        positionList.forEach((p) -> {
            latLonList.add(new LatLon(p.getLatitude(), p.getLongitude()));
        });
        Geometry geo = WwjJTS.filter(WwjJTS.PolygonToGeometry(polyEnveloppe), latLonList);
        List<Position> ptsFiltered = null;
        if (!geo.toString().contains("EMPTY") && !geo.toString().contains("MULTIPOLYGON")) {
            ptsFiltered = WwjJTS.wktPolygonToPositionList(geo.toString());
        }
        if (ptsFiltered != null) {
            if (!ptsFiltered.isEmpty()) {
                result
                        += " <Shape>\n"
                        + "<Appearance>\n"
                        + " <Material diffuseColor='1.0 1. 1.0'/> \n"
                        + "</Appearance>\n"
                        + "<Extrusion convex='true' \n"
                        + " crossSection='";
//positionList

                ptsFiltered.stream().map((p) -> WwjGeodesy.getXYM(orig, p)).forEachOrdered((xy) -> {
                    double x = tileSide - xy.getX() * scaleLonFactor;
                    double y = -tileSide + xy.getY() * scaleLatFactor;
                    result += x + " " + y + (",");
                });
                result += "'\n "
                        + "solid='false' \n"
                        + "spine='0 0 0 0 " + height + " 0'/>\n"
                        + "</Shape>\n";
            }
        }
    }

    public String compute() {
        return result;
    }
}
