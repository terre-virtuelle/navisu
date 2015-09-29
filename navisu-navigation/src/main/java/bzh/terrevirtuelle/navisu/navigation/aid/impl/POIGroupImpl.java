/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.aid.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.model.POI;
import bzh.terrevirtuelle.navisu.navigation.aid.POIGroup;
import bzh.terrevirtuelle.navisu.navigation.aid.POIGroupServices;
import bzh.terrevirtuelle.navisu.navigation.aid.impl.controller.POIGroupController;
import bzh.terrevirtuelle.navisu.widgets.dialogs.DialogController;
import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class POIGroupImpl
        implements POIGroup, POIGroupServices, InstrumentDriver, ComponentState {

    @UsedService
    S57ChartServices s57ChartServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    protected boolean on = false;
    private final String NAME = "POIGroup";
    protected final String GROUP = "Devices";
    POIGroupController poiGroupController;
    DialogController dialogController;
    List<POI> pois;

    @Override
    public void componentInitiated() {
        poiGroupController = POIGroupController.getInstance();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void on(String... files) {
        if (s57ChartServices.isChartsOpen() == false) {
            dialogController = new DialogController(guiAgentServices.getScene(),
                    guiAgentServices.getRoot(), "Attention au moins une carte doit être chargée");
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, dialogController);
            guiAgentServices.getRoot().getChildren().add(dialogController);
            dialogController.setVisible(true);
        } else {
            pois = s57ChartServices.getPOIList();
            System.out.println("pois " + pois);
        }
    }

    @Override
    public void off() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on() {
        this.on("");
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }
}
