/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.view.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.locators.view.ShipTypeColor;
import bzh.terrevirtuelle.navisu.locators.ais.view.ShipView;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;

/**
 *
 * @author Serge
 */
public class ShipViewImpl
        extends Polygon
        implements ShipView {

    private double latitude;
    private double longitude;
    private double heading;
    private final int ALTITUDE = 100;
    private final WorldWindow wwd;

    public ShipViewImpl(Iterable<? extends Position> positions, double latitude, double longitude, double heading) {
        super(positions);
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        wwd = GeoWorldWindViewImpl.getWW();
    }

    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
        moveTo(Position.fromDegrees(latitude, longitude, ALTITUDE));
        wwd.redrawNow();
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
        moveTo(Position.fromDegrees(latitude, longitude, ALTITUDE));
        wwd.redrawNow();
    }

    @Override
    public void setLatLon(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }

    @Override
    public void setType(int type) {
        getAttributes().setInteriorMaterial(ShipTypeColor.VIEW.get(type));
    }

    @Override
    public void setHeading(double angle) {
        this.heading = angle;
        setRotation(-angle);
        wwd.redrawNow();
    }

    public void setCog(double angle) {

    }
}
