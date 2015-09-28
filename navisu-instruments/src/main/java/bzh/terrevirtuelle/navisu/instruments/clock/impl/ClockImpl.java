 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.clock.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.instruments.clock.Clock;
import bzh.terrevirtuelle.navisu.instruments.clock.ClockServices;
import bzh.terrevirtuelle.navisu.instruments.clock.impl.controller.ClockController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class ClockImpl
        implements Clock, ClockServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "Clock";
    @UsedService
    GuiAgentServices guiAgentServices;
    ComponentManager cm;
    private ClockController controller;

    @Override
    public void componentInitiated() {
        // controller = new ClockController(this, KeyCode.T, KeyCombination.CONTROL_DOWN);
        cm = ComponentManager.componentManager;
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
        controller = new ClockController(this, KeyCode.T, KeyCombination.CONTROL_DOWN);
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
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
