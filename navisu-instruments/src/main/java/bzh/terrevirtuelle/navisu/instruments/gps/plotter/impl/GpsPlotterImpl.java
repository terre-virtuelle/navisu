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
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotter;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotterServices;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterController;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterGpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.events.AisActivateEvent;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.track.GpsTrackServices;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.ProducedEvent;
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

    @UsedService
    GpsTrackServices gpsTrackServices;

    @UsedService
    AisServices aisServices;

    @ProducedEvent
    protected AisActivateEvent aisActivateEvent;

    protected boolean on = false;
    private final String NAME1 = "GpsPlotter";
    private final String NAME2 = "AisSurvey";
    private final String NAME3 = "GpsPlotterWithRoute";
    protected final String GROUP = "Devices";
    private GpsPlotterController gpsPlotterController;
    private GpsEventsController gpsEventsController;
    private boolean withRoute = false;
    protected List<String> s57Controllers;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void on(String... files) {
        if (files[0].equals("NAME3")) {
            withRoute = true;
        }
        gpsPlotterController = new GpsPlotterController(this, geoViewServices, layerTreeServices,
                guiAgentServices, kmlObjectServices, aisServices, withRoute,
                NAME1, NAME2, GROUP);
        gpsPlotterController.createTarget();
        gpsEventsController = new GpsPlotterGpsEventsController(gpsPlotterController);
        gpsEventsController.subscribe();
        gpsTrackServices.on(files);
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
        return category.equals(NAME1) || category.equals(NAME3);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    public void notifyAisActivateEvent(RenderableLayer layer, List<String> targets) {
        aisActivateEvent.notifyAisActivateMessageChanged(layer, targets);
    }
}
