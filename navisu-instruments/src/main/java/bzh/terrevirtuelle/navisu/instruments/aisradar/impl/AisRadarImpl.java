/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.aisradar.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.instruments.aisradar.AisRadar;
import bzh.terrevirtuelle.navisu.instruments.aisradar.AisRadarServices;
import bzh.terrevirtuelle.navisu.instruments.aisradar.impl.controller.AisRadarController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class AisRadarImpl
        implements AisRadar, AisRadarServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    private AisRadarController controller;
    private final String NAME = "AisRadar";

    @Override
    public void componentInitiated() {
        controller = new AisRadarController(this, KeyCode.A, KeyCombination.CONTROL_DOWN);
        controller.setScale(0.5);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on(String ... files) {
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().add(controller); //Par defaut le radar n'est pas visible Ctrl-A
        controller.setVisible(true);
        controller.start();
    }

    @Override
    public void off() {
        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().remove(controller);
        controller.setVisible(false);
        controller.stop();
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public boolean canOpen(String category) {

        return category.equals(NAME);
    }

}
