/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.NavigationLine;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolylines;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class NAVLNE_ShapefileLoader
        extends ShapefileLoader
        implements S57ShapeFileLoader {

    Path path;
    Shapefile shapefile;
    private ShapefileRecord record;
    private NavigationLine navigationLine;
    private final List<NavigationLine> navigationLines;
 //   private Set<Map.Entry<String, Object>> entries;
    RenderableLayer layer;
    List< List<LatLon>> coords;
    List<LatLon> latLonList;
    Map<Integer, String> pins = new HashMap<>();

    public NAVLNE_ShapefileLoader() {
        navigationLines = new ArrayList<>();
        latLonList = new ArrayList<>();
        coords = new ArrayList<>();
        /*
         pins.put(0, "castshadow-black.png");
         pins.put(1, "castshadow-blue.png");
         pins.put(2, "castshadow-brown.png");
         pins.put(3, "castshadow-gray.png");
         pins.put(4, "castshadow-green.png");
         pins.put(5, "castshadow-orange.png");
         pins.put(6, "castshadow-purple.png");
         pins.put(7, "castshadow-red.png");
         pins.put(8, "castshadow-teal.png");
         pins.put(9, "castshadow-white.png");
         */
        pins.put(0, "plain-black.png");
        pins.put(1, "plain-blue.png");
        pins.put(2, "plain-brown.png");
        pins.put(3, "plain-gray.png");
        pins.put(4, "plain-green.png");
        pins.put(5, "plain-orange.png");
        pins.put(6, "plain-purple.png");
        pins.put(7, "plain-red.png");
        pins.put(8, "plain-teal.png");
        pins.put(9, "plain-white.png");
        pins.put(10, "plain-yellow.png");
        pins.put(11, "push-pin-red.png");
        pins.put(12, "push-pin-yellow-32.png");
        pins.put(13, "push-pin-yellow.png");
    }

    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        this.record = record;
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineStipplePattern((short) 0xAAAA);
        normalAttributes.setOutlineStippleFactor(5);
        normalAttributes.setOutlineMaterial(new Material(Color.RED));
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

    @Override
    public List<Layer> createLayersFromShapefile(Shapefile shp) {
        this.shapefile = shp;

        //  navigationLine = new NavigationLine();
        return super.createLayersFromShapefile(shp);
    }

    @Override
    protected void addRenderablesForPolylines(Shapefile shp, RenderableLayer layer) {
        ShapeAttributes attrs = this.createPolylineAttributes(null);
        this.layer = layer;
        layer.addRenderable(this.createPolyline(shp, attrs));
    }

    @Override
    protected Renderable createPolyline(Shapefile shp, ShapeAttributes attrs) {
        List<Integer> numberOfPoints = new ArrayList<>();
        while (shp.hasNext()) {
            record = shp.nextRecord();
            //record.getAttributes().getEntries().stream().forEach((e) -> {
            //    System.out.print(e.getKey()+ "="+e.getValue()+" ");
            //});
            //System.out.println("");
            numberOfPoints.add(record.getNumberOfPoints());
        }//OK
        SurfacePolylines shape = new SurfacePolylines(Sector.fromDegrees(shp.getBoundingRectangle()),
                shp.getPointBuffer());

        shape.setAttributes(attrs);
        for (LatLon l : shp.getPointBuffer().getLocations()) {
            latLonList.add(l);
        }
        List<LatLon> tmpList;
        int kk = 0;
        for (int i : numberOfPoints) {
            tmpList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                tmpList.add(latLonList.get(kk));
                kk++;
            }
            coords.add(tmpList);
        }

        ArrayList<Position> pathPositions;

        ShapeAttributes shapeAttributes0 = new BasicShapeAttributes();
        shapeAttributes0.setOutlineMaterial(Material.RED);
        shapeAttributes0.setOutlineWidth(2d);
        ShapeAttributes shapeAttributes1 = new BasicShapeAttributes();
        shapeAttributes1.setOutlineMaterial(Material.GREEN);
        shapeAttributes1.setOutlineWidth(2d);

        int i = 0;
        for (List<LatLon> l : coords) {
            pathPositions = new ArrayList<>();
            pathPositions.add(new Position(l.get(l.size() - 1), 2.0));
            pathPositions.add(new Position(l.get(l.size() - 2), 2.0));
            path = new Path(pathPositions);
            path.setVisible(true);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            path.setPathType(AVKey.GREAT_CIRCLE);
            path.setAttributes(shapeAttributes0);
            path.setValue("E", l);
            layer.addRenderable(path);
        }

        for (List<LatLon> l : coords) {
            pathPositions = new ArrayList<>();
            pathPositions.add(new Position(l.get(0), 2.0));
            pathPositions.add(new Position(l.get(1), 2.0));
            path = new Path(pathPositions);
            path.setVisible(true);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            path.setPathType(AVKey.GREAT_CIRCLE);
            path.setAttributes(shapeAttributes1);
            layer.addRenderable(path);
        }

        return path;
    }

}
