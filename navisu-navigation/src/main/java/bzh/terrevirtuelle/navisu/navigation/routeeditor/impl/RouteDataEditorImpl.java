/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteDataEditor;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteDataEditorServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RouteDataEditorController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RouteDataEditorImpl
        implements RouteDataEditor, RouteDataEditorServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    S57ChartComponentServices s57ChartServices;
    @UsedService
    private LayersManagerServices layersManagerServices;

    private final String COMPONENT_KEY_NAME = "RouteDataEditor";
    private final String LAYER_NAME = "Highway";
    private final String GROUP_NAME = "Navigation";
    private RouteDataEditorController routeDataEditorController;

    @Override
    public void componentInitiated() {
    }

    @Override
    public void on(String... files) {
        routeDataEditorController = new RouteDataEditorController(
                layersManagerServices,
                guiAgentServices,
                s57ChartServices,
                GROUP_NAME, LAYER_NAME,
                KeyCode.D, KeyCombination.CONTROL_DOWN);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, routeDataEditorController);
        guiAgentServices.getRoot().getChildren().add(routeDataEditorController);
        routeDataEditorController.setVisible(true);

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
    public void off() {

        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, routeDataEditorController);
        guiAgentServices.getRoot().getChildren().remove(routeDataEditorController);
        routeDataEditorController.setVisible(false);

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

    public S57ChartComponentServices getS57ChartServices() {
        return s57ChartServices;
    }

}
