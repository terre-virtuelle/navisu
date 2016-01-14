/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.camera.impl.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;

/**
 * NaVisu
 *
 * @date 15 oct. 2015
 * @author Serge Morvan
 */
public class CameraComponentController
        extends AisEventsController {

    private static CameraComponentController INSTANCE;
    private View viewWW;

    private CameraComponentController() {
        viewWW = (BasicOrbitView) GeoWorldWindViewImpl.getWW().getView();
    }

    public static CameraComponentController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CameraComponentController();
        }
        return INSTANCE;
    }

    @Override
    public void updateTarget(Ship ship) {
        if (ship.isGpsTarget()) {
            viewWW.setPitch(Angle.fromDegrees(90.0));
            viewWW.setHeading(Angle.fromDegrees(ship.getHeading() + 90.0));
            viewWW.goTo(
                    new Position(new LatLon(Angle.fromDegrees(ship.getLatitude()),
                            Angle.fromDegrees(ship.getLongitude() - 0.0010)), 100.0), 100
            );
        }
    }

    public void updateTarget() {
        /*
        viewWW.setHeading(Angle.fromDegrees(heading));
        viewWW.setFieldOfView(Angle.fromDegrees(fieldOfView));
        viewWW.setPitch(Angle.fromDegrees(90.0));
        viewWW.goTo(new Position(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude), altitude), altitude);
   */
}
}
