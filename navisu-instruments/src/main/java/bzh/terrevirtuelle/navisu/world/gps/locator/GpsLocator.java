/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.gps.locator;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.world.gps.locator.controller.GpsLocatorController;

/**
 *
 * @author Serge
 */
public class GpsLocator {

    protected GpsLocatorController aisLocatorController;

    public GpsLocator(GeoViewServices geoViewServices) {
        aisLocatorController = new GpsLocatorController(geoViewServices);
    }

}
