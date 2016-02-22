/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.sailingdirections.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;

import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.navigation.sailingdirections.SailingDirectionsComponent;
import bzh.terrevirtuelle.navisu.navigation.sailingdirections.SailingDirectionsComponentServices;
import bzh.terrevirtuelle.navisu.navigation.sailingdirections.impl.controller.SailingDirectionsComponentController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author serge
 * @date Feb 22, 2016
 *
 */
public class SailingDirectionsComponentImpl
        implements SailingDirectionsComponent, SailingDirectionsComponentServices, InstrumentDriver, ComponentState {

    private final String COMPONENT_KEY_NAME = "SailingDirections";
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    LayersManagerServices layersManagerServices;

    private SailingDirectionsComponentController controller;

    @Override
    public void on(String... files) {
        controller = SailingDirectionsComponentController.getInstance(this, KeyCode.S, KeyCombination.CONTROL_DOWN,
                guiAgentServices, s57ChartComponentServices, layersManagerServices);
        controller.setVisible(true);
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(COMPONENT_KEY_NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

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
    public void off() {
        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().remove(controller);
        controller.setVisible(false);
    }
}
