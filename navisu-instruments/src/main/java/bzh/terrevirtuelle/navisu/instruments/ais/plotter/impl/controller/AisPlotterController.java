/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.plotter.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TargetPanel;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.view.targets.GShip;
import bzh.terrevirtuelle.navisu.instruments.common.view.targets.GStation;
import bzh.terrevirtuelle.navisu.instruments.common.view.targets.Shape;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * NaVisu
 *
 * @date 2 mai 2015
 * @author Serge Morvan
 */
public class AisPlotterController {

    protected String name;
    protected String group;
    protected Map<Integer, GShip> gShips;
    protected Map<Integer, GStation> gStations;
    protected WorldWindow wwd;
    protected RenderableLayer aisLayer;
    protected RenderableLayer aisStationLayer;
    protected GeoViewServices geoViewServices;
    protected LayerTreeServices layerTreeServices;
    protected GuiAgentServices guiAgentServices;
    protected TargetPanel targetPanel;
    protected Map<Integer, Calendar> timestamps;
    protected Map<Integer, String> midMap;

    public AisPlotterController(GeoViewServices geoViewServices,
            LayerTreeServices layerTreeServices,
            GuiAgentServices guiAgentServices,
            String name, String group) {

        this.geoViewServices = geoViewServices;
        this.layerTreeServices = layerTreeServices;
        this.guiAgentServices = guiAgentServices;
        this.name = name;
        this.group = group;
        gShips = new HashMap<>();
        gStations = new HashMap<>();
        wwd = GeoWorldWindViewImpl.getWW();
        List<String> groups = layerTreeServices.getGroupNames();
        if (!groups.contains(group)) {
            layerTreeServices.createGroup(group);
            geoViewServices.getLayerManager().createGroup(group);
        }
        this.aisLayer = new RenderableLayer();
        aisLayer.setName(name);
        geoViewServices.getLayerManager().insertGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));
        layerTreeServices.addGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));
        addPanelController();
        addListeners();
    }

    public void createTarget(Ship target) {
        if (!target.isGpsTarget()) {
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
    }

    public void createBaseStation(BaseStation target) {

        GStation gTarget = new GStation(target);
        gStations.put(target.getMMSI(), gTarget);
        if (target.getLatitude() != 0.0 && target.getLongitude() != 0.0) {
            Renderable[] renderables = gTarget.getRenderables();
            for (Renderable r : renderables) {
                aisLayer.addRenderable(r);
            }
            wwd.redrawNow();
        }
    }

    public void updateTarget(Ship target) {
        if (!target.isGpsTarget()) {
            GShip gTarget = gShips.get(target.getMMSI());
            gTarget.update();
            wwd.redrawNow();
        }
    }

    public void updateBaseStation(BaseStation target) {
        GStation gTarget = gStations.get(target.getMMSI());
        gTarget.update();
        wwd.redrawNow();
    }

    public void deleteTarget(Ship target) {
        // System.out.println("target " +target);
        GShip gTarget = gShips.get(target.getMMSI());
        //System.out.println("gTarget " + gTarget);
        Renderable[] renderables = gTarget.getRenderables();
        for (Renderable r : renderables) {
            aisLayer.removeRenderable(r);
        }
        gShips.remove(target.getMMSI());
        wwd.redrawNow();
    }

    public void deleteBaseStation(BaseStation target) {
        GStation gTarget = gStations.get(target.getMMSI());
        Renderable[] renderables = gTarget.getRenderables();
        for (Renderable r : renderables) {
            aisLayer.removeRenderable(r);
        }
        gStations.remove(target.getMMSI());
        wwd.redrawNow();
    }

    private void addListeners() {
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
            targetPanel = new TargetPanel(guiAgentServices, KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, targetPanel);
            guiAgentServices.getRoot().getChildren().add(targetPanel); //Par defaut le radar n'est pas visible Ctrl-A
            targetPanel.setScale(1.0);
            targetPanel.setVisible(false);
        });
    }

    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            targetPanel.setLocation(100, 100);
            targetPanel.updateAisPanel(ship, timestamps, midMap);
        });
    }

    public void setTimestamps(Map<Integer, Calendar> timestamps) {
        this.timestamps = timestamps;
    }

    public void setMidMap(Map<Integer, String> midMap) {
        this.midMap = midMap;
    }

}
