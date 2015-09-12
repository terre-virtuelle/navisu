/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.routeeditor.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.instruments.routeeditor.RouteEditor;
import bzh.terrevirtuelle.navisu.instruments.routeeditor.RouteEditorServices;
import bzh.terrevirtuelle.navisu.instruments.routeeditor.impl.controller.RouteEditorController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RouteEditorImpl
        implements RouteEditor, RouteEditorServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "RouteEditor";
    @UsedService
    GuiAgentServices guiAgentServices;
    private RouteEditorController controller;

    @Override
    public void componentInitiated() {
        controller = new RouteEditorController(this, KeyCode.M, KeyCombination.CONTROL_DOWN);
    }

    @Override
    public void on(String... files) {
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().add(controller);
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

    public GuiAgentServices getGuiAgentServices() {
        return guiAgentServices;
    }
    
}
