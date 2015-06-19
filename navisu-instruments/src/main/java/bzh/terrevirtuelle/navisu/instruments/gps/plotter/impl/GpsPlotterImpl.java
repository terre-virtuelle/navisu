/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotter;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotterServices;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterController;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterGpsEventsController;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class GpsPlotterImpl
        implements GpsPlotter, GpsPlotterServices, InstrumentDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    GuiAgentServices guiAgentServices;

    @UsedService
    LayerTreeServices layerTreeServices;

    @UsedService
    KmlObjectServices kmlObjectServices;

    protected boolean on = false;
    private final String NAME = "GpsPlotter";
    protected final String GROUP = "Devices";
    private GpsPlotterController gpsPlotterController;
    private GpsEventsController gpsEventsController;

    @Override
    public void componentInitiated() {
      
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void on(String... files) {
        gpsPlotterController = new GpsPlotterController(geoViewServices, layerTreeServices,
                guiAgentServices, kmlObjectServices,
                NAME, GROUP);
        gpsPlotterController.createTarget();
        gpsEventsController = new GpsPlotterGpsEventsController(gpsPlotterController);
        gpsEventsController.subscribe();
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
