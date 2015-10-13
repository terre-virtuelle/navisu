/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller.AisRadarController;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TargetPanel;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.GpsPlotterImpl;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class GpsPlotterController {
    
    protected String name1;
    protected String name2;
    protected String group;
    protected WorldWindow wwd;
    protected RenderableLayer gpsLayer;
    protected RenderableLayer aisSurveyZoneLayer;
    protected GeoViewServices geoViewServices;
    protected LayerTreeServices layerTreeServices;
    protected GuiAgentServices guiAgentServices;
    protected KmlObjectServices kmlObjectServices;
    protected AisServices aisServices;
    protected TargetPanel targetPanel;
    protected Ship ownerShip;
    protected double initRotation;
    protected Properties properties;
    protected ColladaRoot ownerShipView;
    protected boolean withRoute = false;
    protected List<String> s57Controllers;
    protected GpsPlotterImpl component;
    
    public GpsPlotterController(GpsPlotterImpl component,
            GeoViewServices geoViewServices,
            LayerTreeServices layerTreeServices,
            GuiAgentServices guiAgentServices,
            KmlObjectServices kmlObjectServices,
            AisServices aisServices,
            boolean withRoute,
            String name1, String name2,
            String group) {
        this.component = component;
        this.geoViewServices = geoViewServices;
        this.layerTreeServices = layerTreeServices;
        this.guiAgentServices = guiAgentServices;
        this.kmlObjectServices = kmlObjectServices;
        this.aisServices = aisServices;
        this.withRoute = withRoute;
        this.name1 = name1;
        this.name2 = name2;
        this.group = group;
        wwd = GeoWorldWindViewImpl.getWW();
        List<String> groups = layerTreeServices.getGroupNames();
        if (!groups.contains(group)) {
            layerTreeServices.createGroup(group);
            geoViewServices.getLayerManager().createGroup(group);
        }
        this.gpsLayer = new RenderableLayer();
        gpsLayer.setName(name1);
        geoViewServices.getLayerManager().insertGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(gpsLayer));
        layerTreeServices.addGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(gpsLayer));
        this.aisSurveyZoneLayer = new RenderableLayer();
        aisSurveyZoneLayer.setName(name2);
        geoViewServices.getLayerManager().insertGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(aisSurveyZoneLayer));
        layerTreeServices.addGeoLayer(group, GeoLayer.factory.newWorldWindGeoLayer(aisSurveyZoneLayer));
        
        addPanelController();
        addListeners();
        createOwnerShip();
        activateS57Controllers();
    }
    
    private void addPanelController() {
        Platform.runLater(() -> {
            targetPanel = new TargetPanel(guiAgentServices, KeyCode.B, KeyCombination.CONTROL_DOWN);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, targetPanel);
            guiAgentServices.getRoot().getChildren().add(targetPanel);
            targetPanel.setScale(1.0);
            targetPanel.setVisible(false);
        });
    }
    
    protected final void updateAisPanel(Ship ship) {
        Platform.runLater(() -> {
            targetPanel.updateAisPanel(ship);
        });
    }
    
    private void addListeners() {
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass() == ColladaRoot.class) {
                    Object object = ((ColladaRoot) o).getField("Ship");
                    if (object != null && object.getClass() == Ship.class) {
                        Ship ship = (Ship) object;
                        updateAisPanel(ship);
                    }
                }
            }
        });
    }
    
    private void createOwnerShip() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/domain.properties"));
        } catch (IOException ex) {
            Logger.getLogger(AisRadarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // creation de l'objet metier central
        ownerShip = ShipBuilder.create()
                .mmsi(new Integer(properties.getProperty("mmsi")))
                .name(properties.getProperty("name"))
                .latitude(new Float(properties.getProperty("latitude")))
                .longitude(new Float(properties.getProperty("longitude")))
                .cog(new Float(properties.getProperty("cog")))
                .sog(new Float(properties.getProperty("sog")))
                .heading(new Float(properties.getProperty("heading")))
                .country(properties.getProperty("country"))
                .width(new Float(properties.getProperty("width")))
                .length(new Float(properties.getProperty("length")))
                .draught(new Float(properties.getProperty("draught")))
                .shipType(new Integer(properties.getProperty("shipType")))
                .navigationalStatus(new Integer(properties.getProperty("navigationalStatus")))
                .electronicPositionDevice(new Integer(properties.getProperty("electronicPositionDevice")))
                .callSign(properties.getProperty("callSign"))
                .target(true)
                .build();
    }
    
    public void createTarget() {
        initRotation = new Double(properties.getProperty("initRotation"));
        ownerShipView = kmlObjectServices.openColladaFile(gpsLayer, properties.getProperty("dae"));
        ownerShipView.setModelScale(new Vec4(new Double(properties.getProperty("scale"))));
        ownerShipView.setPosition(Position.fromDegrees(ownerShip.getLatitude(), ownerShip.getLongitude(), 1000.0));
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
        ownerShipView.setField("Ship", ownerShip);
        aisServices.aisCreateTargetEvent(ownerShip);
    }
    
    public void notifyNmeaMessage(GGA data) {
        ownerShip.setLatitude(data.getLatitude());
        ownerShip.setLongitude(data.getLongitude());
        ownerShipView.setPosition(Position.fromDegrees(ownerShip.getLatitude(), ownerShip.getLongitude(), 1000.0));
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
        aisServices.aisUpdateTargetEvent(ownerShip);
    }
    
    public void notifyNmeaMessage(VTG data) {
        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
        aisServices.aisUpdateTargetEvent(ownerShip);
    }
    
    public void notifyNmeaMessage(RMC data) {
        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        ownerShip.setLatitude(data.getLatitude());
        ownerShip.setLongitude(data.getLongitude());
        ownerShipView.setPosition(Position.fromDegrees(ownerShip.getLatitude(), ownerShip.getLongitude(), 1000.0));
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
        aisServices.aisUpdateTargetEvent(ownerShip);
    }
    
    private void activateS57Controllers() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("POI files (*.poi)", "*.poi", "*.POI");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("data/poi"));
        File file = fileChooser.showOpenDialog(guiAgentServices.getStage());
        if (file != null) {
            Path path = Paths.get(file.getAbsolutePath());
            try {
                s57Controllers = Files.readAllLines(path);
            } catch (IOException ex) {
                Logger.getLogger(GpsPlotterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        component.notifyAisActivateEvent(aisSurveyZoneLayer, s57Controllers);
    }
}
