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
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.BuoyageView;
import bzh.terrevirtuelle.navisu.domain.navigation.view.NavigationViewSet;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.globes.Globe;
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
    private final Globe globe;
    private S57ChartComponentServices s57ChartComponentServices;
    private boolean first = true;
    private final NavigationViewSet navigationViewSet;
    private Set<S57Controller> s57Controllers;
    double width;
    double height;

    private CameraComponentController() {
        this.wwd = GeoWorldWindViewImpl.getWW();
        this.viewWW = wwd.getView();
        navigationViewSet = new NavigationViewSet();
        globe = wwd.getModel().getGlobe();
        width = wwd.getView().getViewport().width;
        height = wwd.getView().getViewport().height;

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

    public NavigationViewSet updateTarget(Camera camera) {
        if (camera != null) {
            viewWW.setHeading(Angle.fromDegrees(camera.getHeading()));
            viewWW.setFieldOfView(Angle.fromDegrees(camera.getFieldOfView()));
            viewWW.setPitch(Angle.fromDegrees(camera.getPitch()));
            if (first == true) {
                viewWW.setEyePosition(new Position(Angle.fromDegrees(camera.getLatitude()),
                        Angle.fromDegrees(camera.getLongitude()), 100.0));
                first = false;
            } else {
                viewWW.goTo(new Position(Angle.fromDegrees(camera.getLatitude()),
                        Angle.fromDegrees(camera.getLongitude()), 100.0), 100.0);
            }
            wwd.redrawNow();
        }
        s57Controllers = s57ChartComponentServices.getS57Controllers();
        navigationViewSet.clear();
        s57Controllers.stream().forEach((s) -> {
            Buoyage buoyage = (Buoyage) s.getNavigationData();
            Position orgPos = new Position(
                    Angle.fromDegrees(buoyage.getLatitude()),
                    Angle.fromDegrees(buoyage.getLongitude()), 0.0);
            Vec4 cartesianLoc = globe.computePointFromPosition(orgPos);
            Vec4 screenLoc = viewWW.project(cartesianLoc);
            if (screenLoc.x >= 0 && screenLoc.y >= 0 && screenLoc.x<= 1080 && screenLoc.y<=700) {
                navigationViewSet.add(new BuoyageView(buoyage, (int) screenLoc.x, (int) screenLoc.y));
            }
        });

        return navigationViewSet;
    }

    public void setS57ChartComponentServices(S57ChartComponentServices s57ChartComponentServices) {
        this.s57ChartComponentServices = s57ChartComponentServices;
    }

}
