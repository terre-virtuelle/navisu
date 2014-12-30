/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.radar.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.radar.Radar;
import bzh.terrevirtuelle.navisu.radar.RadarServices;
import bzh.terrevirtuelle.navisu.radar.impl.controller.RadarController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class RadarImpl
        implements Radar, RadarServices, ComponentState {

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
    public void on() {
        RadarController radarController = new RadarController(KeyCode.A, KeyCombination.CONTROL_DOWN);

    }
}
