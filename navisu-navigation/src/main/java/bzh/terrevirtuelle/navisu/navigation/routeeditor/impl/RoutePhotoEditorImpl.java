/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RoutePhotoEditor;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RoutePhotoEditorServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RoutePhotoEditorController;
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
public class RoutePhotoEditorImpl
        implements RoutePhotoEditor, RoutePhotoEditorServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    S57ChartServices s57ChartServices;

    private final String KEY_NAME = "RoutePhotoEditor";
    private RoutePhotoEditorController routePhotoEditorController;
    

    @Override
    public void componentInitiated() {
    }

    @Override
    public void on(String... files) {
        routePhotoEditorController = new RoutePhotoEditorController(this, KeyCode.M, KeyCombination.CONTROL_DOWN);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, routePhotoEditorController);
        guiAgentServices.getRoot().getChildren().add(routePhotoEditorController);
        routePhotoEditorController.setVisible(true); 
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
        
        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, routePhotoEditorController);
        guiAgentServices.getRoot().getChildren().remove(routePhotoEditorController);
        routePhotoEditorController.setVisible(false);
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

    public S57ChartServices getS57ChartServices() {
        return s57ChartServices;
    }
    
}
