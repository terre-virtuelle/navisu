/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.world.Widget3D;
import bzh.terrevirtuelle.navisu.world.Widget3DServices;
import bzh.terrevirtuelle.navisu.world.gps.locator.GpsLocator;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class Widget3DImpl implements Widget3D, Widget3DServices, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void createLocator() {
        GpsLocator locator = new GpsLocator(geoViewServices);
    }
}
