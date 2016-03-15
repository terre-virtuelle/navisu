/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotter;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotterServices;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterController;
import bzh.terrevirtuelle.navisu.instruments.gps.track.GpsTrackServices;
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
    GuiAgentServices guiAgentServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    KmlObjectServices kmlObjectServices;
    @UsedService
    GpsTrackServices gpsTrackServices;

    protected boolean on = false;
    private final String NAME = "GpsPlotter";
    private GpsPlotterController gpsPlotterController;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void on(String... files) {
        if (on == false) {
            on = true;
            gpsPlotterController = new GpsPlotterController(layersManagerServices, guiAgentServices, kmlObjectServices, NAME);
            gpsPlotterController.init();
        }
    }

    @Override
    public void off() {
        on = false;
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

    @Override
    public double getLatitude() {
        return gpsPlotterController.getLatitude();
    }

    @Override
    public double getLongitude() {
        return gpsPlotterController.getLongitude();
    }

    @Override
    public Ship getOwnerShip() {
        return gpsPlotterController.getOwnerShip();
   }
}
