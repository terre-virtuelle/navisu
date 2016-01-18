/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.camera.impl.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @date 15 oct. 2015
 * @author Serge Morvan
 */
public class CameraComponentController
        extends AisEventsController {

    private static CameraComponentController INSTANCE;
    private final View viewWW;
    private final WorldWindow wwd;
   
    private CameraComponentController() {
        this.wwd = GeoWorldWindViewImpl.getWW();
        this.viewWW = wwd.getView();
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

    public void updateTarget(Camera camera) {                        
        if (camera != null) {
            viewWW.setHeading(Angle.fromDegrees(camera.getHeading()));
            viewWW.setFieldOfView(Angle.fromDegrees(camera.getFieldOfView()));
            viewWW.setPitch(Angle.fromDegrees(camera.getPitch()));
            viewWW.goTo(new Position(Angle.fromDegrees(camera.getLatitude()),
                    Angle.fromDegrees(camera.getLongitude()), 100.0), 100.0);
            wwd.redrawNow();
        }
    }

}
