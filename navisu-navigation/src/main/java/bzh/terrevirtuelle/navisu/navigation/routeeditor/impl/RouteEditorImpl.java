/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteEditor;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RouteEditorController;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteEditorServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RouteDataEditorController;
import com.vividsolutions.jts.geom.Geometry;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.BSplineComponentServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;

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
    S57ChartComponentServices s57ChartServices;
    @UsedService
    private LayersManagerServices layersManagerServices;
    @UsedService
    private BSplineComponentServices bsplineComponentServices;
    @UsedService
    private DisplayServices displayServices;

    private final String KEY_NAME = "RouteEditor";
    private final String LAYER_NAME = "Highway";
    private final String GROUP_NAME = "Navigation";
    private RouteEditorController routeEditorController;
    private RouteDataEditorController routeDataEditorController;

    @Override
    public void componentInitiated() {
    }

    @Override
    public void on(String... files) {
        routeEditorController = new RouteEditorController(this,
                layersManagerServices, bsplineComponentServices, displayServices,
                KeyCode.M, KeyCombination.CONTROL_DOWN);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, routeEditorController);
        guiAgentServices.getRoot().getChildren().add(routeEditorController);
        routeEditorController.setVisible(true);

        routeDataEditorController = new RouteDataEditorController(
                layersManagerServices,
                guiAgentServices,
                s57ChartServices,
                GROUP_NAME, LAYER_NAME,
                KeyCode.D, KeyCombination.CONTROL_DOWN);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, routeDataEditorController);
        guiAgentServices.getRoot().getChildren().add(routeDataEditorController);
        routeDataEditorController.setVisible(false);

        routeEditorController.setRouteDataEditorController(routeDataEditorController);
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

    @Override
    public Geometry getBuffer() {
        if (routeEditorController != null) {
            return routeEditorController.getBuffer();
        } else {
            return null;
        }
    }

    @Override
    public void showBuffer() {
        if (routeEditorController != null) {
            routeEditorController.showBuffer();
        }
    }

    public S57ChartComponentServices getS57ChartServices() {
        return s57ChartServices;
    }

}
