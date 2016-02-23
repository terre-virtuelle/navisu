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
import bzh.terrevirtuelle.navisu.navigation.sailingdirections.impl.controller.SailingDirectionsEditorComponentController;
import bzh.terrevirtuelle.navisu.navigation.sailingdirections.impl.controller.SailingDirectionsViewerComponentController;
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

    private final String COMPONENT_KEY_NAME_0 = "SailingDirectionsViewer";
    private final String COMPONENT_KEY_NAME_1 = "SailingDirectionsEditor";

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    LayersManagerServices layersManagerServices;

    private SailingDirectionsViewerComponentController controller0;
    private SailingDirectionsEditorComponentController controller1;

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            if (cmd[0].equals(COMPONENT_KEY_NAME_0)) {
                controller0 = SailingDirectionsViewerComponentController.getInstance(this, KeyCode.S, KeyCombination.CONTROL_DOWN,
                        guiAgentServices, s57ChartComponentServices, layersManagerServices);
                controller0.setVisible(true);

            } else if (cmd[0].equals(COMPONENT_KEY_NAME_1)) {
                controller1 = SailingDirectionsEditorComponentController.getInstance(this, KeyCode.S, KeyCombination.CONTROL_DOWN,
                        guiAgentServices, s57ChartComponentServices, layersManagerServices);
                controller1.setVisible(true);

            }
        }

    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0) || category.equals(COMPONENT_KEY_NAME_1)) {
            canOpen = true;
        }
        return canOpen;
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
        if (controller0 != null) {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller0);
            guiAgentServices.getRoot().getChildren().remove(controller0);
            controller0.setVisible(false);
        }
        if (controller1 != null) {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller0);
            guiAgentServices.getRoot().getChildren().remove(controller1);
            controller1.setVisible(false);
        }
    }
}
