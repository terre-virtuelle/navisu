/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.AisRadar;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.AisRadarServices;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller.AisRadarAisEventsController;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller.AisRadarController;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller.AisRadarGpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class AisRadarImpl
        implements AisRadar, AisRadarServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;

    @UsedService
    AisServices aisServices;

    private AisRadarController aisRadarController;
    private AisEventsController aisEventsController;
    private GpsEventsController gpsEventsController;
    private final String NAME = "AisRadar";
    protected Ship ship;
    protected BaseStation station;
    protected Map<Integer, Ship> ships;
    protected Map<Integer, BaseStation> stations;
    protected boolean on = false;
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
        aisRadarController = new AisRadarController(this, KeyCode.A, KeyCombination.CONTROL_DOWN);
        aisRadarController.setScale(0.4);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisRadarController);
        guiAgentServices.getRoot().getChildren().add(aisRadarController); //Par defaut le radar n'est pas visible Ctrl-A
        aisRadarController.setVisible(true);
        aisRadarController.start();
        timestamps = aisServices.getTimestamps();
        aisRadarController.setTimestamps(timestamps);
        midMap = aisServices.getMidMap();
        aisRadarController.setMidMap(midMap);
        
        if (!aisServices.isOn()) {
            aisServices.on();
        }
        if (on == false) {
            on = true;
            ships = aisServices.getShips();
            Set<Integer> shipSet = ships.keySet();
            shipSet.stream().forEach((i) -> {
                aisRadarController.createTarget(ships.get(i));
            });
            stations = aisServices.getStations();
            Set<Integer> stationSet = stations.keySet();
            
            aisEventsController = new AisRadarAisEventsController(aisRadarController);
            aisEventsController.subscribe();
            gpsEventsController = new AisRadarGpsEventsController(aisRadarController);
            gpsEventsController.subscribe();
        }
    }

    @Override
    public void off() {
        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, aisRadarController);
        guiAgentServices.getRoot().getChildren().remove(aisRadarController);
        aisRadarController.setVisible(false);
        aisRadarController.stop();
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(NAME);
    }

    public GuiAgentServices getGuiAgentServices() {
        return guiAgentServices;
    }

}
