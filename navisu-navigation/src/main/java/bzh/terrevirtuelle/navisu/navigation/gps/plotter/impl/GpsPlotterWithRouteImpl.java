/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.track.GpsTrackServices;
import bzh.terrevirtuelle.navisu.instruments.transponder.TransponderServices;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.GpsPlotterWithRoute;
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.GpsPlotterWithRouteServices;
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.controller.GpsPlotterWithRouteController;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.ProducedEvent;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.events.TransponderActivateEvent;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class GpsPlotterWithRouteImpl
        implements GpsPlotterWithRoute, GpsPlotterWithRouteServices, InstrumentDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    KmlObjectServices kmlObjectServices;
    @UsedService
    S57GlobalCatalogServices s57GlobalCatalogServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    GpsTrackServices gpsTrackServices;
    @UsedService
    TransponderServices transponderServices;
    
    @ProducedEvent
    protected TransponderActivateEvent transponderActivateEvent;

    protected boolean on = false;
    private final String NAME0 = "GpsPlotterWithRoute";
    private final String NAME1 = "GpsPlotter";

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
            new GpsPlotterWithRouteController(this,
                    layersManagerServices,
                    guiAgentServices, kmlObjectServices,
                    s57ChartComponentServices, s57GlobalCatalogServices, 
                    transponderServices,
                    NAME1).init();
            gpsTrackServices.on(files);
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
        return category.equals(NAME0);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    public void notifyTransponderActivateEvent(RenderableLayer layer, List<NavigationData> targets) {
        transponderActivateEvent.notifyActivateMessageChanged(layer, targets);
    }
}
