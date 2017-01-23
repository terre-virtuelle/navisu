/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import gov.nasa.worldwind.avlist.AVKey;

/**
 * NaVisu
 *
 * @date 12 sept. 2015
 * @author Serge Morvan
 */
public class S57BuoyageController
        extends S57Controller {
    public S57BuoyageController(S57Behavior s57Behavior, NavigationData buoyage, double range) {
        super(s57Behavior, buoyage, range);
    }

    @Override
    public void updateTarget(Ship ship) {
        
        distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
        azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);

        s57Behavior.doIt(distance, azimuth);
        surveyZone.setValue(AVKey.DISPLAY_NAME, ((Buoyage) navigationData).getObjectName() + "\n distance :  "
                + String.format("%.2f", distance) + " Nm"
                + "\nazimuth :  " + String.format("%d", (int) azimuth) + " °  ");
       // System.out.println("S57BuoyageController updateTarget " + ship);/OK, pb sur s57Behavior
    }

    @Override
    public void activate() {
        if (layer != null && first == true) {
            layer.addRenderable(surveyZone);
            first = false;
        }
        subscribe();
    }

    @Override
    public void deactivate() {
        if (layer != null) {
            layer.removeAllRenderables();
        }
        unsubscribe();
    }

    @Override
    protected void notifyNmeaMessage(GGA data) {
        System.out.println("notifyNmeaMessage RMC");
        //   updateTarget(data.getLatitude(), data.getLongitude());
    }

    @Override
    protected void notifyNmeaMessage(VTG data) {
        System.out.println("notifyNmeaMessage RMC");
        //   ownerShip.setCog(data.getCog());
        //   ownerShip.setSog(data.getSog());
        //   ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
    }

    @Override
    protected void notifyNmeaMessage(RMC data) {
        System.out.println("notifyNmeaMessage RMC");
        //   ownerShip.setCog(data.getCog());
        //   ownerShip.setSog(data.getSog());
        //   updateTarget(data.getLatitude(), data.getLongitude());
    }

    @Override
    public String toString() {
        return ((Buoyage) navigationData).getLabel();
    }

}
