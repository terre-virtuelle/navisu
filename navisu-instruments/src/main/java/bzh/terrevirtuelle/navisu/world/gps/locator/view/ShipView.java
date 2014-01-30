/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.gps.locator.view;

import bzh.terrevirtuelle.navisu.ship.Ship;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.List;

/**
 *
 * @author Serge
 */
public class ShipView
        extends Polygon {

    private double latitude;
    private double longitude;
    private double cog;

    public ShipView(List<Position> positions, double latitude, double longitude,double cog) {
        super(positions);
        this.latitude = latitude;
        this.longitude = longitude;
        this.cog = cog;
    }

    public void setCog(double cog) {
        setRotation(cog - 90);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
    }

    public void setLatLon(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void setPathAttrs(ShapeAttributes pathAttrs) {
        setAttributes(pathAttrs);
    }

}
