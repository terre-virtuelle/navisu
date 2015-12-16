/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57BasicBehavior;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Chart;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyInstallation;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.MooringWarpingFacility;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller.AisRadarController;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TargetPanel;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import bzh.terrevirtuelle.navisu.navigation.controller.AvurnavController;
import bzh.terrevirtuelle.navisu.navigation.controller.S57ChartController;
import bzh.terrevirtuelle.navisu.navigation.controller.SailingDirectionsController;
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.GpsPlotterWithRouteImpl;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import bzh.terrevirtuelle.navisu.widgets.textArea.TextAreaController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.PointPlacemark;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javax.xml.bind.JAXBException;
import org.apache.commons.collections4.queue.CircularFifoQueue;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class GpsPlotterWithRouteController {

    protected String name1;
    protected String name2;
    protected String name3;
    protected String name4;
    protected String group;
    protected WorldWindow wwd;
    protected RenderableLayer gpsLayer;
    protected RenderableLayer navigationPgonLayer;
    protected RenderableLayer navigationIconsLayer;
    protected RenderableLayer transponderZoneLayer;
    protected GuiAgentServices guiAgentServices;
    protected KmlObjectServices kmlObjectServices;
    protected TargetPanel targetPanel;
    protected Ship ownerShip;
    protected double initRotation;
    protected Properties properties;
    protected ColladaRoot ownerShipView;
    protected boolean withTarget = true;
    protected List<String> s57ControllerIdList;
    protected GpsPlotterWithRouteImpl component;
    protected CircularFifoQueue<RMC> sentenceQueue;
    protected NavigationDataSet navigationDataSet = null;
    static private GpsPlotterWithRouteController instance = null;
    protected TextAreaController textAreaController;
    protected S57GlobalCatalogServices s57GlobalCatalogServices;
    protected S57ChartComponentServices s57ChartComponentServices;

    public static GpsPlotterWithRouteController getInstance(
            GpsPlotterWithRouteImpl component,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            KmlObjectServices kmlObjectServices,
            S57ChartComponentServices s57ChartComponentServices,
            S57GlobalCatalogServices s57GlobalCatalogServices,
            AisServices aisServices,
            boolean withTarget,
            NavigationDataSet navigationDataSet,
            String name1, String name2, String name3, String name4,
            String group) {
        if (instance == null) {
            instance = new GpsPlotterWithRouteController(component,
                    layersManagerServices,
                    guiAgentServices,
                    kmlObjectServices,
                    s57ChartComponentServices,
                    s57GlobalCatalogServices,
                    aisServices,
                    withTarget,
                    navigationDataSet,
                    name1, name2, name3, name4,
                    group);
        }
        return instance;
    }

    private GpsPlotterWithRouteController(GpsPlotterWithRouteImpl component,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            KmlObjectServices kmlObjectServices,
            S57ChartComponentServices s57ChartComponentServices,
            S57GlobalCatalogServices s57GlobalCatalogServices,
            AisServices aisServices,
            boolean withTarget,
            NavigationDataSet navigationDataSet,
            String name1, String name2, String name3, String name4,
            String group) {
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.kmlObjectServices = kmlObjectServices;
        this.navigationDataSet = navigationDataSet;
        this.s57ChartComponentServices = s57ChartComponentServices;
        this.s57GlobalCatalogServices = s57GlobalCatalogServices;
        this.name1 = name1;
        this.name2 = name2;
        this.group = group;
        sentenceQueue = new CircularFifoQueue<>(6);
        wwd = GeoWorldWindViewImpl.getWW();
        gpsLayer = layersManagerServices.initLayer(group, name1);
        navigationPgonLayer = layersManagerServices.initLayer(group, name2);
        navigationPgonLayer.setPickEnabled(false);
        navigationIconsLayer = layersManagerServices.initLayer(group, name4);
        transponderZoneLayer = layersManagerServices.initLayer(group, name3);
        addPanelController();
        addListeners();
        createOwnerShip();
        activateControllers();
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
        wwd.addSelectListener((SelectEvent event) -> {
            PickedObject po = event.getTopPickedObject();
            if (po != null && po.getObject() instanceof PointPlacemark) {
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
                    PointPlacemark placemark = (PointPlacemark) po.getObject();
                    if (placemark.getValue("TYPE") != null) {
                        String type = (String) placemark.getValue("TYPE");
                        if (type.equals("Avurnav") || type.equals("SailingDirections")) {
                            Platform.runLater(() -> {
                                textAreaController = new TextAreaController();
                                textAreaController.getDataTextArea().setWrapText(true);
                                textAreaController.getTitleText().setText((String) placemark.getValue("TITLE"));
                                textAreaController.getDataTextArea().setText((String) placemark.getValue("TEXT"));
                                textAreaController.setVisible(true);
                                guiAgentServices.getRoot().getChildren().add(textAreaController);
                            });
                            event.consume();
                        }
                        if (type.equals("S57Chart")) {
                            Path path = s57GlobalCatalogServices.getChartPath((String) placemark.getValue(AVKey.DISPLAY_NAME));
                            s57ChartComponentServices.openChart(path.toString());
                            event.consume();
                        }
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
    }

    public void notifyNmeaMessage(GGA data) {
        ownerShip.setLatitude(data.getLatitude());
        ownerShip.setLongitude(data.getLongitude());
        ownerShipView.setPosition(Position.fromDegrees(ownerShip.getLatitude(), ownerShip.getLongitude(), 1000.0));
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
    }

    public void notifyNmeaMessage(VTG data) {
        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
        ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
    }

    public void notifyNmeaMessage(RMC data) {
        //filtre sur les doublons
        if (!sentenceQueue.contains(data)) {
            sentenceQueue.add(data);
            RMC d = sentenceQueue.element();
            if (d != null) {
                ownerShip.setCog(d.getCog());
                ownerShip.setSog(d.getSog());
                ownerShip.setLatitude(d.getLatitude());
                ownerShip.setLongitude(d.getLongitude());
                ownerShipView.setPosition(Position.fromDegrees(ownerShip.getLatitude(), ownerShip.getLongitude(), 1000.0));
                ownerShipView.setHeading(Angle.fromDegrees(ownerShip.getCog() + initRotation));
            }
        }
    }

    public final void activateControllers() {
        File file = IO.fileChooser(guiAgentServices.getStage(),
                "privateData/nds/", "NDS files (*.nds)", "*.nds", "*.NDS");
        navigationDataSet = new NavigationDataSet();
        try {
            navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(GpsPlotterWithRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        activateS57Controllers();
        activateNavigationControllers();
    }

    private void activateS57Controllers() {
        List<Class> s57ConList = Arrays.asList(BeaconIsolatedDanger.class,
                BeaconLateral.class,
                BeaconSafeWater.class,
                BeaconSpecialPurpose.class,
                BuoyCardinal.class,
                BuoyInstallation.class,
                BuoyIsolatedDanger.class,
                BuoyLateral.class,
                BuoySafeWater.class,
                BuoySpecialPurpose.class,
                MooringWarpingFacility.class,
                Landmark.class);

        List<NavigationData> s57NavigationDataList = new ArrayList<>();
        s57ConList.stream().forEach((claz) -> {
            s57NavigationDataList.addAll(navigationDataSet.get(claz));
        });

        s57ControllerIdList = new ArrayList<>();
        s57NavigationDataList.stream().forEach((s) -> {
            s57ControllerIdList.add(Long.toString(s.getId()));
        });

        component.notifyTransponderActivateEvent(transponderZoneLayer, s57NavigationDataList);
    }

    private void activateNavigationControllers() {
        List<S57Chart> chartList = navigationDataSet.get(S57Chart.class);
        chartList.stream().forEach((S57Chart a) -> {
            String displayName = a.getNumber();
            String description = a.getDescription();
            S57ChartController sc = new S57ChartController(new S57BasicBehavior(),
                    guiAgentServices,
                    a,
                    926, displayName, description);
            sc.setLayer(navigationPgonLayer);
            sc.setIconsLayer(navigationIconsLayer);
            sc.activate();
        });
        List<Avurnav> avurnavList = navigationDataSet.get(Avurnav.class);
        avurnavList.stream().forEach((Avurnav a) -> {
            String displayName = "Avurnav N°" + Long.toString(a.getId());
            String description = a.getDescription();
            AvurnavController sc = new AvurnavController(new S57BasicBehavior(),
                    guiAgentServices, a,
                    926, displayName, description);
            sc.setLayer(navigationPgonLayer);
            sc.setIconsLayer(navigationIconsLayer);
            sc.activate();
        });

        List<SailingDirections> sailingDirectionsList = navigationDataSet.get(SailingDirections.class);
        sailingDirectionsList.stream().forEach((SailingDirections a) -> {
            String displayName = "SailingDirections N°" + Long.toString(a.getId());
            String description = a.getDescription();
            SailingDirectionsController sc = new SailingDirectionsController(new S57BasicBehavior(),
                    guiAgentServices, a,
                    926, displayName, description);
            sc.setLayer(navigationPgonLayer);
            sc.setIconsLayer(navigationIconsLayer);
            sc.activate();
        });
    }
}
