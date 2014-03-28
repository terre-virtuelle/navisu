/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.impl;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.locators.Widget3D;
import bzh.terrevirtuelle.navisu.locators.Widget3DServices;
import bzh.terrevirtuelle.navisu.locators.ais.AisLocator;
import bzh.terrevirtuelle.navisu.locators.gps.GpsLocator;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class Widget3DImpl
        implements Widget3D, Widget3DServices, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    DpAgentServices dpAgentServices;
    
    @UsedService
    GuiAgentServices guiAgentServices;

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
    public void createGpsLocator() {
        GpsLocator locator = new GpsLocator(geoViewServices, dpAgentServices);
    }

    @Override
    public void createAisLocator() {
        AisLocator locator = new AisLocator(geoViewServices, dpAgentServices, guiAgentServices);
    }
}
