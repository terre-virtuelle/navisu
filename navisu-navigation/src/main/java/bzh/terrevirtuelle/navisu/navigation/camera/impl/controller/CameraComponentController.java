/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.camera.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import java.util.Set;

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
    private S57ChartComponentServices s57ChartComponentServices;
    private boolean first = true;
    private final NavigationDataSet navigationDataSet;
    private Set<S57Controller> s57Controllers;
   

    private CameraComponentController() {
        this.wwd = GeoWorldWindViewImpl.getWW();
        this.viewWW = wwd.getView();
        navigationDataSet = new NavigationDataSet();
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

    public NavigationDataSet updateTarget(Camera camera) {
        if (camera != null) {
            viewWW.setHeading(Angle.fromDegrees(camera.getHeading()));
            viewWW.setFieldOfView(Angle.fromDegrees(camera.getFieldOfView()));
            viewWW.setPitch(Angle.fromDegrees(camera.getPitch()));
            if (first == true) {
                viewWW.setEyePosition(new Position(Angle.fromDegrees(camera.getLatitude()),
                        Angle.fromDegrees(camera.getLongitude()), 100.0));
            } else {
                viewWW.goTo(new Position(Angle.fromDegrees(camera.getLatitude()),
                        Angle.fromDegrees(camera.getLongitude()), 100.0), 100.0);
            }
            wwd.redrawNow();
        }
        if (first) {
            navigationDataSet.clear();
            s57Controllers = s57ChartComponentServices.getS57Controllers();
            s57Controllers.stream().forEach((S57Controller s) -> {
                Buoyage buoyage = (Buoyage) s.getNavigationData();
                navigationDataSet.add(buoyage);
            });
            first = false;
        }
        return navigationDataSet;
    }

    public void setS57ChartComponentServices(S57ChartComponentServices s57ChartComponentServices) {
        this.s57ChartComponentServices = s57ChartComponentServices;
    }

    @Override
    public void createTarget(Ship ship) {
    }

    
}
