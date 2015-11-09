/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57Behavior;
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
public class S57BuoyageController
        extends S57Controller {

    private ShapeAttributes normalAttributes = null;

    public S57BuoyageController() {
    }

    public S57BuoyageController(Buoyage buoyage, S57Behavior s57Behavior) {
        super(buoyage);
        this.s57Behavior = s57Behavior;
        setId(buoyage.getId());
        setLat(buoyage.getLat());
        setLon(buoyage.getLon());

        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(false);

        shape = new SurfaceCircle(LatLon.fromDegrees(buoyage.getLocation().getLat(), buoyage.getLocation().getLon()), s57Behavior.getRange());
        shape.setAttributes(normalAttributes);
        this.s57Behavior.setShape(shape);
    }

    @Override
    public void updateTarget(Ship ship) {
        if (first == true) {
            layer.addRenderable(shape);
            first = false;
        }
        if (ship.isGpsTarget()) {
            distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
            azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
            /*
             if (first == true) {
             first = false;
             shape = new SurfaceCircle(LatLon.fromDegrees(navigationData.getLocation().getLat(), navigationData.getLocation().getLon()), s57Behavior.getRange());
             shape.setAttributes(normalAttributes);
             s57Behavior.setShape(shape);
             layer.addRenderable(shape);
             }
             */
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
