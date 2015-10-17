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
public class CameraController
        extends AisEventsController {

    private static CameraController INSTANCE;
    private View view;

    private CameraController() {
        view = (BasicOrbitView) GeoWorldWindViewImpl.getWW().getView();
    }

    public static CameraController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CameraController();
        }
        return INSTANCE;
    }

    @Override
    public void updateTarget(Ship ship) {
        if (ship.isGpsTarget()) {
            view.setPitch(Angle.fromDegrees(90.0));
            view.setHeading(Angle.fromDegrees(ship.getHeading() + 90.0));
            view.goTo(
                    new Position(new LatLon(Angle.fromDegrees(ship.getLatitude()),
                                    Angle.fromDegrees(ship.getLongitude() - 0.0010)), 100.0), 100
            );
        }
    }
}
