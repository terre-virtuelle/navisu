/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.view.impl;

import bzh.terrevirtuelle.navisu.locators.view.ShipTypeColor;
import bzh.terrevirtuelle.navisu.locators.ais.view.ShipView;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.SurfaceCircle;

/**
 *
 * @author Serge
 */
public class ShipDefaultViewImpl
        extends SurfaceCircle
        implements ShipView {

    private double latitude;
    private double longitude;
    private double cog;

    public ShipDefaultViewImpl(LatLon latLon, double radius) {
        super(latLon, radius);
        this.latitude = latLon.getLatitude().degrees;
        this.longitude = latLon.getLongitude().degrees;
    }

    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
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
    public void setCog(double cog) {
    }

    @Override
    public void setHeading(double angle) {
    }
}
