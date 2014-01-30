/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.world.ais.locator;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.world.ais.locator.controller.AisLocatorController;

/**
 *
 * @author Serge
 */
public class AisLocator {
    protected AisLocatorController aisLocatorController;

    public AisLocator(GeoViewServices geoViewServices) {
        aisLocatorController = new AisLocatorController(geoViewServices);
    }
}
