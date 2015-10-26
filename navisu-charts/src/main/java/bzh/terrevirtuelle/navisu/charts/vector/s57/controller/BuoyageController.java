/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.controller;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;

/**
 * NaVisu
 *
 * @date 12 sept. 2015
 * @author Serge Morvan
 */
public class BuoyageController
        extends S57Controller {

    private ShapeAttributes normalAttributes = new BasicShapeAttributes();

    public BuoyageController() {
    }

    public BuoyageController(Buoyage buoyage, S57Behavior s57Behavior) {
        this.navigationData = buoyage;
        this.s57Behavior = s57Behavior;
        s57Behavior.setS57Controller(this);
        id = buoyage.getId();
        lat = buoyage.getLat();
        lon = buoyage.getLon();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(false);
    }

    @Override
    public void updateTarget(Ship ship) {
        if (ship.isGpsTarget()) {
            distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
            azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
            if (first == true) {
                first = false;
                shape = new SurfaceCircle(LatLon.fromDegrees(navigationData.getLocation().getLat(), navigationData.getLocation().getLon()), s57Behavior.getRange());
                shape.setAttributes(normalAttributes);
                s57Behavior.setShape(shape);
                layer.addRenderable(shape);
            }
            s57Behavior.doIt(distance, azimuth);
            shape.setValue(AVKey.DISPLAY_NAME, ((Buoyage) navigationData).getObjectName() + "\n distance :  "
                    + String.format("%.2f", distance) + " Nm"
                    + "\nazimuth :  " + String.format("%d", (int) azimuth) + " °  ");
        }
    }

    @Override
    public void activate() {
        subscribe();
    }

    @Override
    public void deactivate() {
        unsubscribe();
    }

    @Override
    public String toString() {
        return ((Buoyage) navigationData).getLabel();
    }

}
