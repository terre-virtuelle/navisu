/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.plotter.impl;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisCreateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisDeleteStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisDeleteTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisUpdateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.controller.events.AisUpdateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.AisPlotter;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.AisPlotterServices;
import bzh.terrevirtuelle.navisu.instruments.ais.view.targets.GShip;
import bzh.terrevirtuelle.navisu.instruments.ais.view.targets.Shape;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class AisPlotterImpl
        implements AisPlotter, AisPlotterServices, InstrumentDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    DpAgentServices dpAgentServices;

    @UsedService
    GuiAgentServices guiAgentServices;

    @UsedService
    LayerTreeServices layerTreeServices;

    @UsedService
    AisServices aisServices;

    ComponentManager cm;
    ComponentEventSubscribe<AisCreateStationEvent> aisCSEvent;
    ComponentEventSubscribe<AisCreateTargetEvent> aisCTEvent;
    ComponentEventSubscribe<AisDeleteStationEvent> aisDSEvent;
    ComponentEventSubscribe<AisDeleteTargetEvent> aisDTEvent;
    ComponentEventSubscribe<AisUpdateStationEvent> aisUSEvent;
    ComponentEventSubscribe<AisUpdateTargetEvent> aisUTEvent;

    protected WorldWindow wwd;
    protected RenderableLayer aisLayer;
    protected RenderableLayer aisStationLayer;
    protected static final String GROUP = "Devices";
    protected AisPanelController aisPanelController;
    protected Ship ship;
    protected BaseStation station;
    protected Map<Integer, Ship> ships;
    protected Map<Integer, GShip> gShips;
    protected Map<Integer, BaseStation> stations;
    protected final String NAME = "AisPlotter";
    protected boolean on = false;

    @Override
    public void componentInitiated() {
        gShips = new HashMap<>();

        wwd = GeoWorldWindViewImpl.getWW();
        layerTreeServices.createGroup(GROUP);
        geoViewServices.getLayerManager().createGroup(GROUP);

        this.aisLayer = new RenderableLayer();
        aisLayer.setName(NAME);
        geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));
        layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));

        cm = ComponentManager.componentManager;
        aisCSEvent = cm.getComponentEventSubscribe(AisCreateStationEvent.class);
        aisCTEvent = cm.getComponentEventSubscribe(AisCreateTargetEvent.class);
        aisDSEvent = cm.getComponentEventSubscribe(AisDeleteStationEvent.class);
        aisDTEvent = cm.getComponentEventSubscribe(AisDeleteTargetEvent.class);
        aisUSEvent = cm.getComponentEventSubscribe(AisUpdateStationEvent.class);
        aisUTEvent = cm.getComponentEventSubscribe(AisUpdateTargetEvent.class);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on(String... files) {
        if (!aisServices.isOn()) {
            aisServices.on();
        }
        if (on == false) {
            on = true;
            ships = aisServices.getShips();
            Set<Integer> shipSet = ships.keySet();
            shipSet.stream().forEach((i) -> {
                createTarget(ships.get(i));
            });
            stations = aisServices.getStations();
            Set<Integer> stationSet = stations.keySet();

            aisCTEvent.subscribe((AisCreateTargetEvent) (Ship updatedData) -> {
                createTarget(updatedData);
            });
            aisUTEvent.subscribe((AisUpdateTargetEvent) (Ship updatedData) -> {
                updateTarget(updatedData);
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
        }
    }

    @Override
    public void off() {
        if (on == true) {
            on = false;
            aisCTEvent.unsubscribe((AisCreateTargetEvent) (Ship updatedDate) -> {
                System.out.println(updatedDate);
            });
            aisUTEvent.unsubscribe((AisUpdateTargetEvent) (Ship updatedData) -> {
                System.out.println(updatedData);
            });
            aisDTEvent.unsubscribe((AisDeleteTargetEvent) (Ship updatedDate) -> {
                System.out.println(updatedDate);
            });

            aisCSEvent.unsubscribe((AisCreateStationEvent) (BaseStation updatedData) -> {
                System.out.println(updatedData);
            });
            aisUSEvent.unsubscribe((AisUpdateStationEvent) (BaseStation updatedData) -> {
                System.out.println(updatedData);
            });
            aisDSEvent.unsubscribe((AisDeleteStationEvent) (BaseStation updatedDate) -> {
                System.out.println(updatedDate);
            });
        }
    }

    private void addListeners() {
        /*
         wwd.addPositionListener((PositionEvent event) -> {
         float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
         if (altitude >= 30000) {
         clip();
         } else {
         unClip();
         }
         });
         */
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                Class[] i = o.getClass().getInterfaces();
                if (i.length != 0) {
                    if (i[0].equals(Shape.class)) {
                        updateAisPanel(((Shape) o).getShip());
                    }
                }
            }
        });
    }

    private void addPanelController() {
        Platform.runLater(() -> {
            aisPanelController = new AisPanelController(KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisPanelController);
            guiAgentServices.getRoot().getChildren().add(aisPanelController); //Par defaut le radar n'est pas visible Ctrl-A
            aisPanelController.setScale(1.0);
            aisPanelController.setVisible(false);
        });
    }

    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            //   aisPanelController.updateAisPanel(ship);
        });
    }

    private void createTarget(Ship target) {
        GShip gShip = new GShip(target);
        gShips.put(target.getMMSI(), gShip);
        if (target.getLatitude() != 0.0 && target.getLongitude() != 0.0) {
            Renderable[] renderables = gShip.getRenderables();
            for (Renderable r : renderables) {
                aisLayer.addRenderable(r);
            }
            wwd.redrawNow();
        }
    }

    private void updateTarget(Ship target) {
        GShip gShip = gShips.get(target.getMMSI());
        gShip.update();
        wwd.redrawNow();
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
