/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.media.bathysounds.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.media.bathysounds.impl.controller.BathySoundsComponentController;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.media.bathysounds.BathySounds;
import bzh.terrevirtuelle.navisu.media.bathysounds.BathySoundsServices;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class BathySoundsImpl
        implements BathySounds, BathySoundsServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "BathySounds";
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    
    BathySoundsComponentController controller;

    @Override
    public void componentInitiated() {

    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(KEY_NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void on(String... files) {
        controller = new BathySoundsComponentController(layersManagerServices, 
                instrumentDriverManagerServices,
                "data/sounds/bathy/soundsAM.csv");
        controller.on();
    }

    @Override
    public void off() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
