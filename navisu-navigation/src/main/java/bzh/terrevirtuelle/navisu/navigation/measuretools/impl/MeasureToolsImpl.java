/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.measuretools.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.navigation.measuretools.MeasureTools;
import bzh.terrevirtuelle.navisu.navigation.measuretools.MeasureToolsServices;
import bzh.terrevirtuelle.navisu.navigation.measuretools.impl.controller.MeasureToolsController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 18 juil. 2015
 * @author Serge Morvan
 */
public class MeasureToolsImpl
        implements MeasureTools, MeasureToolsServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "MeasureTools";
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    
    private MeasureToolsController controller;

    @Override
    public void componentInitiated() {
    }

    @Override
    public void on(String... files) {
        controller = MeasureToolsController.getInstance(this, KeyCode.M, KeyCombination.CONTROL_DOWN, 
                guiAgentServices, s57ChartComponentServices, layersManagerServices);
        controller.setVisible(true);
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
    public void off() {
        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().remove(controller);
        controller.setVisible(false);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
