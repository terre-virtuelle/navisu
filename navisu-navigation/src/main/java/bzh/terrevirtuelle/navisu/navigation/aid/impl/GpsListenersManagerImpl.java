/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.aid.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.model.ExtendedBuoyage;
import bzh.terrevirtuelle.navisu.navigation.aid.GpsListenersManager;
import bzh.terrevirtuelle.navisu.navigation.aid.GpsListenersManagerServices;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class GpsListenersManagerImpl
        implements GpsListenersManager, GpsListenersManagerServices, InstrumentDriver, ComponentState {

    @UsedService
    S57ChartServices s57ChartServices;

    protected boolean on = false;
    private final String NAME = "GpsListenersManager";
    protected final String GROUP = "Devices";
    List<ExtendedBuoyage> extendedBuoyages;

    @Override
    public void componentInitiated() {
        extendedBuoyages = s57ChartServices.getExtendedBuoyageList();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void on(String... files) {

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
