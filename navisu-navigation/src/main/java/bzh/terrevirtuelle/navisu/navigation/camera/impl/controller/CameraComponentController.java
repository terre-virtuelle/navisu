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
import gov.nasa.worldwind.geom.Frustum;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.globes.Globe;
import java.util.ArrayList;
import java.util.List;
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
    private List<Buoyage> simpleS57Buoyages;
    private final double width;
    private final double height;
    private Frustum frustum;
    private double lat;
    private double lon;
    private Position orgPos;
    private Vec4 cartesianLoc;
    private Vec4 screenLoc;

    private CameraComponentController() {
        this.wwd = GeoWorldWindViewImpl.getWW();
        this.viewWW = wwd.getView();
        navigationViewSet = new NavigationViewSet();
        globe = wwd.getModel().getGlobe();
        width = viewWW.getViewport().width;
        height = viewWW.getViewport().height;
        simpleS57Buoyages = new ArrayList<>();
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
                //  first = false;
            } else {
                viewWW.goTo(new Position(Angle.fromDegrees(camera.getLatitude()),
                        Angle.fromDegrees(camera.getLongitude()), 100.0), 100.0);
            }
            wwd.redrawNow();
        }
        frustum = viewWW.getFrustumInModelCoordinates();
        s57Controllers = s57ChartComponentServices.getS57Controllers();
        navigationViewSet.clear();
        if (first) {
            s57Controllers.stream().forEach((S57Controller s) -> {
                Buoyage buoyage = (Buoyage) s.getNavigationData();
                simpleS57Buoyages.add(new Buoyage(buoyage.getId(), buoyage.getLatitude(),buoyage.getLongitude()));
            });
            first = false;
        }
        /*
        simpleS57Buoyages.stream().forEach((Buoyage s) -> {
            lat = s.getLatitude();
            lon = s.getLongitude();
            orgPos = new Position(
                    Angle.fromDegrees(lat),
                    Angle.fromDegrees(lon), 0.0);
            cartesianLoc = globe.computePointFromPosition(orgPos);
            if (frustum.contains(cartesianLoc)) {
                screenLoc = viewWW.project(cartesianLoc);
                if (screenLoc.x >= 0 && screenLoc.y >= 0 && screenLoc.x <= 1080 && screenLoc.y <= 700) {
                    navigationViewSet.add(new BuoyageView(s, (int) screenLoc.x, (int) screenLoc.y));
                }
            }
        });
*/
        s57Controllers.stream().forEach((S57Controller s0) -> {
            Buoyage s = (Buoyage) s0.getNavigationData();
            lat = s.getLatitude();
            lon = s.getLongitude();
            orgPos = new Position(
                    Angle.fromDegrees(lat),
                    Angle.fromDegrees(lon), 0.0);
            cartesianLoc = globe.computePointFromPosition(orgPos);
            if (frustum.contains(cartesianLoc)) {
                screenLoc = viewWW.project(cartesianLoc);
                if (screenLoc.x >= 0 && screenLoc.y >= 0 && screenLoc.x <= 1080 && screenLoc.y <= 700) {
                    navigationViewSet.add(new BuoyageView(s, (int) screenLoc.x, (int) screenLoc.y));
                }
            }
        });
      //  System.out.println("navigationViewSet "+ navigationViewSet);
        return navigationViewSet;
    }

    public void setS57ChartComponentServices(S57ChartComponentServices s57ChartComponentServices) {
        this.s57ChartComponentServices = s57ChartComponentServices;
    }
}
