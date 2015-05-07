/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VTGEvent;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.AisRadar;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.AisRadarServices;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller.AisRadarController;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateTargetEvent;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

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
    ComponentManager cm;
    ComponentEventSubscribe<AisCreateStationEvent> aisCSEvent;
    ComponentEventSubscribe<AisCreateTargetEvent> aisCTEvent;
    ComponentEventSubscribe<AisDeleteStationEvent> aisDSEvent;
    ComponentEventSubscribe<AisDeleteTargetEvent> aisDTEvent;
    ComponentEventSubscribe<AisUpdateStationEvent> aisUSEvent;
    ComponentEventSubscribe<AisUpdateTargetEvent> aisUTEvent;
    ComponentEventSubscribe<GGAEvent> ggaES;
    ComponentEventSubscribe<RMCEvent> rmcES;
    ComponentEventSubscribe<VTGEvent> vtgES;

    private AisRadarController controller;
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
        cm = ComponentManager.componentManager;
        aisCSEvent = cm.getComponentEventSubscribe(AisCreateStationEvent.class);
        aisCTEvent = cm.getComponentEventSubscribe(AisCreateTargetEvent.class);
        aisDSEvent = cm.getComponentEventSubscribe(AisDeleteStationEvent.class);
        aisDTEvent = cm.getComponentEventSubscribe(AisDeleteTargetEvent.class);
        aisUSEvent = cm.getComponentEventSubscribe(AisUpdateStationEvent.class);
        aisUTEvent = cm.getComponentEventSubscribe(AisUpdateTargetEvent.class);
        ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
        rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
        vtgES = cm.getComponentEventSubscribe(VTGEvent.class);

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on(String... files) {
        controller = new AisRadarController(this, KeyCode.A, KeyCombination.CONTROL_DOWN);
        controller.setScale(0.7);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().add(controller); //Par defaut le radar n'est pas visible Ctrl-A
        controller.setVisible(true);
        controller.start();
        timestamps = aisServices.getTimestamps();
        controller.setTimestamps(timestamps);
        midMap = aisServices.getMidMap();
        controller.setMidMap(midMap);
        if (!aisServices.isOn()) {
            aisServices.on();
        }
        if (on == false) {
            on = true;
            ships = aisServices.getShips();
            Set<Integer> shipSet = ships.keySet();
            shipSet.stream().forEach((i) -> {
                controller.createTarget(ships.get(i));
            });
            stations = aisServices.getStations();
            Set<Integer> stationSet = stations.keySet();

            aisCTEvent.subscribe((AisCreateTargetEvent) (Ship updatedData) -> {
                controller.createTarget(updatedData);
            });
            aisUTEvent.subscribe((AisUpdateTargetEvent) (Ship updatedData) -> {
                controller.updateTarget(updatedData);
            });
            aisDTEvent.subscribe((AisDeleteTargetEvent) (Ship updatedData) -> {
                System.out.println(updatedData);
            });
            aisCSEvent.subscribe((AisCreateStationEvent) (BaseStation updatedData) -> {
            });
            aisUSEvent.subscribe((AisUpdateStationEvent) (BaseStation updatedData) -> {
                //  System.out.println(updatedData);
            });
            aisDSEvent.subscribe((AisDeleteStationEvent) (BaseStation updatedData) -> {
                System.out.println(updatedData);
            });
            ggaES.subscribe(new GGAEvent() {
                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                    GGA data = (GGA) d;
                    controller.notifyNmeaMessageChanged(data);

                }
            });
            vtgES.subscribe(new VTGEvent() {
                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    VTG data = (VTG) d;
                    controller.notifyNmeaMessageChanged(data);
                }
            });
            rmcES.subscribe(new RMCEvent() {
                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    RMC data = (RMC) d;
                    controller.notifyNmeaMessageChanged(data);

                }
            });
        }
    }

    @Override
    public void off() {
        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().remove(controller);
        controller.setVisible(false);
        controller.stop();
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
