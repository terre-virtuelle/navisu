package bzh.terrevirtuelle.navisu.instruments.ais.plotter.impl;

import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TargetPanel;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.AisPlotter;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.AisPlotterServices;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.impl.controller.AisPlotterAisEventsController;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.impl.controller.AisPlotterController;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class AisPlotterImpl
        implements AisPlotter, AisPlotterServices, InstrumentDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    GuiAgentServices guiAgentServices;

    @UsedService
    LayerTreeServices layerTreeServices;

    @UsedService
    AisServices aisServices;

    protected final String NAME = "AisPlotter";
    protected final String GROUP = "Devices";
    protected Map<Integer, Ship> ships;
    protected Map<Integer, BaseStation> stations;
    protected boolean on = false;
    protected AisPlotterController aisPlotterController;
    protected AisEventsController aisEventsController;
    protected TargetPanel aisPanelController;
    protected Map<Integer, Calendar> timestamps;
    protected Map<Integer, String> midMap;

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
    public void on(String... files) {
        aisPlotterController = new AisPlotterController(geoViewServices, 
                layerTreeServices, guiAgentServices,
                NAME, GROUP);
        if (!aisServices.isOn()) {
            aisServices.on();
        }
        if (on == false) {
            on = true;
            ships = aisServices.getShips();
            Set<Integer> shipSet = ships.keySet();
            shipSet.stream().forEach((i) -> {
                aisPlotterController.createTarget(ships.get(i));
            });
            stations = aisServices.getStations();
            Set<Integer> stationSet = stations.keySet();
            stationSet.stream().forEach((i) -> {
                aisPlotterController.createBaseStation(stations.get(i));
            });
            timestamps = aisServices.getTimestamps();
            aisPlotterController.setTimestamps(timestamps);
            midMap = aisServices.getMidMap();
            aisPlotterController.setMidMap(midMap);
            aisEventsController = new AisPlotterAisEventsController(aisPlotterController);
            aisEventsController.subscribe();
        }
    }

    @Override
    public void off() {
        if (on == true) {
            on = false;
        }
    }

    @Override
    public void on() {
        this.on("");
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(NAME);
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
