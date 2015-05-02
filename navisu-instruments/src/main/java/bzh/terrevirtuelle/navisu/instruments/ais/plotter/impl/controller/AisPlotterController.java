/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.plotter.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.view.targets.GShip;
import bzh.terrevirtuelle.navisu.instruments.ais.view.targets.Shape;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import java.util.Calendar;
import java.util.HashMap;
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
    protected WorldWindow wwd;
    protected RenderableLayer aisLayer;
    protected RenderableLayer aisStationLayer;
    protected GeoViewServices geoViewServices;
    protected LayerTreeServices layerTreeServices;
    protected GuiAgentServices guiAgentServices;
    protected AisPanelController aisPanelController;
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
        wwd = GeoWorldWindViewImpl.getWW();
        layerTreeServices.createGroup(group);
        geoViewServices.getLayerManager().createGroup(group);
        this.aisLayer = new RenderableLayer();
        aisLayer.setName(name);
        geoViewServices.getLayerManager().insertGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));
        layerTreeServices.addGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(aisLayer));
        addPanelController();
        addListeners();
    }

    public void createTarget(Ship target) {
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

    public void updateTarget(Ship target) {
        GShip gShip = gShips.get(target.getMMSI());
        gShip.update();
        wwd.redrawNow();
    }

    public void deleteTarget(Ship target) {

        wwd.redrawNow();
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
            aisPanelController = new AisPanelController(guiAgentServices, KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisPanelController);
            guiAgentServices.getRoot().getChildren().add(aisPanelController); //Par defaut le radar n'est pas visible Ctrl-A
            aisPanelController.setScale(1.0);
            aisPanelController.setVisible(false);
        });
    }

    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            aisPanelController.updateAisPanel(ship, timestamps, midMap);
        });
    }

    public void setTimestamps(Map<Integer, Calendar> timestamps) {
        this.timestamps = timestamps;
    }

    public void setMidMap(Map<Integer, String> midMap) {
        this.midMap = midMap;
    }

}
