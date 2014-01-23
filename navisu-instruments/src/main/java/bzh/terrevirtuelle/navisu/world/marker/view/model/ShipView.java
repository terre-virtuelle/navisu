/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.marker.view.model;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Serge
 */
public class ShipView
        extends Polygon {

    private double latitude;
    private double longitude;
    private final double[] ship = {
        longitude, latitude + .0015,
        longitude + .001, latitude - .0015,
        longitude - .001, latitude - .0015
    };
    private ShapeAttributes pathAttrs;

    public ShipView() {
        this(0.0, 0.0);
    }

    public ShipView(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
        init();
    }

    private void init() {
        setOuterBoundary(makePositionList(ship));
        makeAttributes();
        setAttributes(pathAttrs);
    }

    private void makeAttributes() {
        pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.BLACK);
        pathAttrs.setOutlineOpacity(0.8);
        pathAttrs.setOutlineWidth(3);
        pathAttrs.setInteriorMaterial(Material.GREEN);
        pathAttrs.setDrawInterior(true);
        pathAttrs.setInteriorOpacity(1.0);
    }

    protected final List<Position> makePositionList(double[] src) {
        int numCoords = src.length / 2;
        Position[] array = new Position[numCoords];

        for (int i = 0; i < numCoords; i++) {
            double lonDegrees = src[2 * i];
            double latDegrees = src[2 * i + 1];
            array[i] = Position.fromDegrees(latDegrees, lonDegrees, 0);
        }

        return Arrays.asList(array);
    }

    public void setHeading(double heading) {
        setRotation(heading - 90);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
       // moveTo(referencePosition);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ShapeAttributes getPathAttrs() {
        return pathAttrs;
    }

    public void setLatLon(double lat, double lon) {
      
    }

    public void setPathAttrs(ShapeAttributes pathAttrs) {
        this.pathAttrs = pathAttrs;
    }

}
