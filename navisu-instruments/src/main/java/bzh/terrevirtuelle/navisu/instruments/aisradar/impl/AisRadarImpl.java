/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.aisradar.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.instruments.aisradar.AisRadar;
import bzh.terrevirtuelle.navisu.instruments.aisradar.AisRadarServices;
import bzh.terrevirtuelle.navisu.instruments.aisradar.impl.controller.AisRadarController;
import javafx.event.EventType;
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
        implements AisRadar, AisRadarServices, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    private AisRadarController radarController;

    @Override
    public void componentInitiated() {
        radarController = new AisRadarController(KeyCode.A, KeyCombination.CONTROL_DOWN);
        radarController.setScale(0.5);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on() {
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, radarController);
        guiAgentServices.getRoot().getChildren().add(radarController); //Par defaut le radar n'est pas visible Ctrl-A
        radarController.setVisible(true);
        radarController.start();
    }

    @Override
    public void off() {
        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, radarController);
        guiAgentServices.getRoot().getChildren().remove(radarController); 
        radarController.setVisible(false);
        radarController.stop();
    }
}
