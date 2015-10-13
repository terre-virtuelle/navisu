/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteEditor;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RouteEditorController;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteEditorServices;
import com.vividsolutions.jts.geom.Geometry;
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

    
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    S57ChartServices s57ChartServices;
    
    private final String KEY_NAME = "RouteEditor";
    private RouteEditorController controller;

    @Override
    public void componentInitiated() {    
    }

    @Override
    public void on(String... files) {
        controller = new RouteEditorController(this, s57ChartServices, KeyCode.M, KeyCombination.CONTROL_DOWN);
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

    @Override
    public Geometry getBuffer() {
        if (controller != null) {
            return controller.getBuffer();
        } else {
            return null;
        }
    }

    @Override
    public void showBuffer() {
        if (controller != null) {
            controller.showBuffer();
        }
    }
}
