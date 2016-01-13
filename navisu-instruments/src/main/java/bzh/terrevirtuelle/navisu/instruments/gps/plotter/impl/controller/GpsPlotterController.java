/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller.AisRadarController;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.common.controller.ShipController;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TargetPanel;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.apache.commons.collections4.queue.CircularFifoQueue;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class GpsPlotterController
        extends GpsEventsController {

    protected final String GROUP = "Navigation";
    protected GuiAgentServices guiAgentServices;
    protected KmlObjectServices kmlObjectServices;
    protected LayersManagerServices layersManagerServices;
    protected String name;
    protected WorldWindow wwd;
    protected RenderableLayer gpsLayer;
    protected TargetPanel targetPanel;
    protected Ship ownerShip;
    protected double initRotation;
    protected Properties properties;
    protected String PROPERTIES_FILE_NAME = "properties/domain.properties";
    protected ColladaRoot ownerShipView;
    protected CircularFifoQueue<RMC> sentenceQueue;

    public GpsPlotterController(LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            KmlObjectServices kmlObjectServices,
            String name) {
        this(layersManagerServices, guiAgentServices, name);
        this.kmlObjectServices = kmlObjectServices;
    }

    public GpsPlotterController(LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            String name) {
        this.layersManagerServices = layersManagerServices;
        this.guiAgentServices = guiAgentServices;
        this.name = name;
    }

    public void init() {
        sentenceQueue = new CircularFifoQueue<>(6);
        wwd = GeoWorldWindViewImpl.getWW();
        gpsLayer = layersManagerServices.getInstance(GROUP, name);
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(AisRadarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addPanelController();
        addListeners();
        ownerShip = ShipController.createOwnerShip(properties);
        createTarget();
        subscribe();
    }

    protected void addPanelController() {
        Platform.runLater(() -> {
            targetPanel = new TargetPanel(guiAgentServices, KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, targetPanel);
            guiAgentServices.getRoot().getChildren().add(targetPanel);
            targetPanel.setScale(1.0);
            targetPanel.setVisible(false);
        });
    }

    protected void addListeners() {
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass() == ColladaRoot.class) {
                    Object object = ((ColladaRoot) o).getField("Ship");
                    if (object != null && object.getClass() == Ship.class) {
                        Ship ship = (Ship) object;
                        updateShipPanel(ship);
                    }
                }
            }
        });
    }

    protected void updateShipPanel(Ship ship) {
        Platform.runLater(() -> {
            targetPanel.updatePanel(ship);
        });
    }

    protected void createTarget() {
        initRotation = new Double(properties.getProperty("initRotation"));
        ownerShipView = kmlObjectServices.openColladaFile(gpsLayer, properties.getProperty("dae"));
        ownerShipView.setModelScale(new Vec4(new Double(properties.getProperty("scale"))));
        ownerShipView.setPosition(Position.fromDegrees(ownerShip.getLatitude(), ownerShip.getLongitude(), 1000.0));
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
        ownerShipView.setField("Ship", ownerShip);
    }

    private void updateTarget(double latitude, double longitude) {
        ownerShip.setLatitude(latitude);
        ownerShip.setLongitude(longitude);
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
        ownerShipView.setPosition(Position.fromDegrees(ownerShip.getLatitude(), ownerShip.getLongitude(), 1000.0));
    }

    @Override
    protected void notifyNmeaMessage(GGA data) {
        updateTarget(data.getLatitude(), data.getLongitude());
    }

    @Override
    protected void notifyNmeaMessage(VTG data) {
        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
    }

    @Override
    protected void notifyNmeaMessage(RMC data) {
        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        updateTarget(data.getLatitude(), data.getLongitude());
    }
}
