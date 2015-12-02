/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller;

import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import gov.nasa.worldwind.avlist.AVKey;

/**
 * NaVisu
 *
 * @date 12 sept. 2015
 * @author Serge Morvan
 */
public class AvurnavController
        extends NavigationController {

    public AvurnavController(NavigationData avurnav, double range,
            String displayName, String description) {
        super(avurnav, range,displayName,  description);    
    }

    @Override
    public void updateTarget(Ship ship) {
            distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
            azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
            s57Behavior.doIt(distance, azimuth);
            surveyZone.setValue(AVKey.DISPLAY_NAME, ((Avurnav) navigationData).getDescription() + "\n distance :  "
                    + String.format("%.2f", distance) + " Nm"
                    + "\nazimuth :  " + String.format("%d", (int) azimuth) + " °  ");
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

}
