/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.track.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.instruments.gps.track.plotter.GpsTrackPlotter;
import bzh.terrevirtuelle.navisu.instruments.gps.track.GpsTrackServices;
import bzh.terrevirtuelle.navisu.instruments.gps.track.impl.controller.GpsTrackController;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class GpsTrackImpl
        implements GpsTrackPlotter, GpsTrackServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayersManagerServices layersManagerServices;

    protected boolean on = false;
    private final String NAME = "GpsTrack";
   
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
    public void on(String... files) {
        if (on == false) {
            on = true;
            new GpsTrackController(layersManagerServices, guiAgentServices, NAME).init();
        }
    }

    @Override
    public void off() {
        // Pb dans la lib C3 ? objet non retiré de la liste
        if (on == true) {
            on = false;
        }
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(NAME);
    }

    @Override
    public boolean isOn() {
        return on;
    }

}
