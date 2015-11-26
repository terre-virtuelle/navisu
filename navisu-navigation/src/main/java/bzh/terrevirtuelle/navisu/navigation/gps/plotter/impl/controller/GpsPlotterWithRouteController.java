/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Location;
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
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.GpsPlotterWithRouteImpl;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import bzh.terrevirtuelle.navisu.widgets.textArea.TextAreaController;
import com.vividsolutions.jts.algorithm.CentroidArea;
import com.vividsolutions.jts.algorithm.CentroidPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.Placemarks;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    protected String group;
    protected WorldWindow wwd;
    protected RenderableLayer gpsLayer;
    protected RenderableLayer aisSurveyZoneLayer;
    protected GuiAgentServices guiAgentServices;
    protected KmlObjectServices kmlObjectServices;
    protected AisServices aisServices;
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
    private final int LIMIT = 926; // distance of perception
    protected WKTReader wktReader;
    protected Point point;
    protected PointPlacemark placemark;
    protected PointPlacemarkAttributes avurnavPlacemarkNormalAttributes;
    protected PointPlacemarkAttributes avurnavMultiPlacemarkNormalAttributes;
    protected PointPlacemarkAttributes sailingDirectionsMultiPlacemarkNormalAttributes;
    protected ShapeAttributes avurnavPolygonNormalAttributes;
    protected ShapeAttributes avurnavPolygonHighlightAttributes;
    protected ShapeAttributes sailingDirectionsPolygonNormalAttributes;
    protected ShapeAttributes sailingDirectionsPolygonHighlightAttributes;
    protected String AVURNAV_IMAGE_ADDRESS = "images/avurnav.png";
    protected String DEFAULT_AVURNAV_IMAGE_ADDRESS = "images/pushpins/castshadow-gray.png";
    protected String SAILING_DIRECTIONS_IMAGE_ADDRESS = "images/sailingDirections.png";
    protected String DIRECTIONS_IMAGE_ADDRESS = "images/sailingDirections.png";
    protected String DEFAULT_DESCRIPTION = "Pas de description";
    static private GpsPlotterWithRouteController instance = null;
    protected TextAreaController textAreaController;

    public static GpsPlotterWithRouteController getInstance(
            GpsPlotterWithRouteImpl component,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            KmlObjectServices kmlObjectServices,
            AisServices aisServices,
            boolean withTarget,
            NavigationDataSet navigationDataSet,
            String name1, String name2,
            String group) {
        if (instance == null) {
            instance = new GpsPlotterWithRouteController(component,
                    layersManagerServices,
                    guiAgentServices,
                    kmlObjectServices,
                    aisServices,
                    withTarget,
                    navigationDataSet,
                    name1, name2,
                    group);
        }
        return instance;
    }

    private GpsPlotterWithRouteController(GpsPlotterWithRouteImpl component,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            KmlObjectServices kmlObjectServices,
            AisServices aisServices,
            boolean withTarget,
            NavigationDataSet navigationDataSet,
            String name1, String name2,
            String group) {
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.kmlObjectServices = kmlObjectServices;
        this.aisServices = aisServices;
        this.navigationDataSet = navigationDataSet;
        this.name1 = name1;
        this.name2 = name2;
        this.group = group;
        sentenceQueue = new CircularFifoQueue<>(6);
        wwd = GeoWorldWindViewImpl.getWW();
        gpsLayer = layersManagerServices.initLayer(group, name1);
        aisSurveyZoneLayer = layersManagerServices.initLayer(group, name2);
        createAttributes();
        addPanelController();
        addListeners();
        if (withTarget == true) {
            createOwnerShip();
        }
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

        wwd.addSelectListener(new SelectListener() {
            @Override
            public void selected(SelectEvent event) {
                PickedObject po = event.getTopPickedObject();
                if (po != null && po.getObject() instanceof PointPlacemark) {
                    if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
                        PointPlacemark placemark = (PointPlacemark) po.getObject();
                        if (placemark.getValue("TYPE") != null) {
                            if (placemark.getValue("TYPE").equals("Avurnav")) {
                                Platform.runLater(() -> {
                                    textAreaController = new TextAreaController();
                                    textAreaController.getDataTextArea().setWrapText(true);
                                    textAreaController.getTitleText().setText((String) placemark.getValue("TITLE"));
                                    textAreaController.getDataTextArea().setText((String) placemark.getValue("TEXT"));
                                    guiAgentServices.getRoot().getChildren().add(textAreaController);
                                });
                            }
                        }
                    }
                    event.consume();
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
                aisServices.aisUpdateTargetEvent(ownerShip);
            }
        }
    }

    public final void activateControllers() {
        activateS57Controllers();
        activateNavigationControllers();
    }

    private void activateS57Controllers() {
        File file = IO.fileChooser(guiAgentServices.getStage(),
                "privateData/nds/", "NDS files (*.nds)", "*.nds", "*.NDS");
        navigationDataSet = new NavigationDataSet();
        try {
            navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(GpsPlotterWithRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        s57ControllerIdList = new ArrayList<>();
        List<Location> locations = navigationDataSet.getLocations();
        locations.stream().forEach((l) -> {
            long id = l.getId();
            s57ControllerIdList.add(Long.toString(id));
        });
        component.notifyAisActivateEvent(aisSurveyZoneLayer, s57ControllerIdList);
    }

    private void activateNavigationControllers() {
        List<Avurnav> avurnavList = navigationDataSet.get(Avurnav.class);
        wktReader = new WKTReader();
        /* Avurnav */

        avurnavList.stream().forEach((Avurnav a) -> {
            String geom = a.getGeometry();
            String label = "Avurnav N°" + Long.toString(a.getId());
            String globalZone = a.getGlobalZone() == null ? "" : a.getGlobalZone();
            geom = geom.toUpperCase();
            String text = createText(a);
            if (geom.contains("POINT")) {
                createMultiPoint(geom, label, globalZone, text,
                        avurnavPlacemarkNormalAttributes, avurnavMultiPlacemarkNormalAttributes);
            } else if (geom.contains("POLYGON")) {
                createMultiPolygon(geom, label, globalZone, text,
                        avurnavPolygonNormalAttributes, avurnavPolygonHighlightAttributes, avurnavPlacemarkNormalAttributes);
            } else if (geom.contains("LINESTRING")) {
                createMultiLineString(geom);
            }
        });
        /* SailingDirections */
        List<SailingDirections> sailingDirectionsList = navigationDataSet.get(SailingDirections.class);
        sailingDirectionsList.stream().forEach((SailingDirections a) -> {
            String geom = a.getGeometry();
            String label = "SailingDirections N°" + Long.toString(a.getId());
            String globalZone = a.getZoneName() == null ? "" : a.getZoneName();
            geom = geom.toUpperCase();
            // String text = createText(a);
            if (geom.contains("POLYGON")) {
                createMultiPolygon(geom, label, a.getBook(), a.getDescription(),
                       sailingDirectionsPolygonNormalAttributes, sailingDirectionsPolygonHighlightAttributes, sailingDirectionsMultiPlacemarkNormalAttributes);
            }
        });
    }

    private void createMultiPoint(String geom, String label, String globalZone, String description,
            PointPlacemarkAttributes nAttrs, PointPlacemarkAttributes mAttrs) {
        Coordinate[] coordinates = null;
        Geometry geometry = null;
        try {
            geometry = wktReader.read(geom);
            coordinates = geometry.getCoordinates();
        } catch (ParseException ex) {
            Logger.getLogger(GpsPlotterWithRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (coordinates != null) {
            if (coordinates.length == 1) {
                createPointPlacemark(coordinates[0].y, coordinates[0].x, label, globalZone, description);
                placemark.setAttributes(nAttrs);
            } else {
                for (Coordinate coordinate : coordinates) {
                    createPointPlacemark(coordinate.y, coordinate.x, label, globalZone, description);
                    placemark.setAttributes(mAttrs);
                }
                CentroidPoint centroid = new CentroidPoint();
                centroid.add(geometry);
                Coordinate coord = centroid.getCentroid();
                createPointPlacemark(coord.y, coord.x, label, globalZone, description);
                placemark.setAttributes(nAttrs);
            }
        }
    }

    private void createMultiPolygon(String geom, String label, String globalZone, String text,
            ShapeAttributes nAttrs, ShapeAttributes hAttrs, PointPlacemarkAttributes attrs) {
        Coordinate[] coordinates = null;
        Geometry geometry = null;
        try {
            geometry = wktReader.read(geom);
            coordinates = geometry.getCoordinates();
        } catch (ParseException ex) {
            Logger.getLogger(GpsPlotterWithRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Position> pathPositions = new ArrayList<>();
        if (coordinates != null) {
            for (Coordinate coordinate : coordinates) {
                pathPositions.add(Position.fromDegrees(coordinate.y, coordinate.x, 200));
            }
            Polygon pgon = new Polygon(pathPositions);
            pgon.setAttributes(nAttrs);
            pgon.setHighlightAttributes(hAttrs);
            pgon.setValue(AVKey.DISPLAY_NAME, label + "\n" + globalZone);
            aisSurveyZoneLayer.addRenderable(pgon);

            CentroidArea centroid = new CentroidArea();
            centroid.add(geometry);
            Coordinate coord = centroid.getCentroid();
            createPointPlacemark(coord.y, coord.x, label, globalZone, text);
            placemark.setAttributes(attrs);
        }
    }

    private void createMultiLineString(String geom) {
        System.out.println("not yet implemented");
    }

    private void createAttributes() {
        avurnavPlacemarkNormalAttributes = new PointPlacemarkAttributes();
        avurnavPlacemarkNormalAttributes.setImageAddress(AVURNAV_IMAGE_ADDRESS);
        avurnavPlacemarkNormalAttributes.setImageOffset(Offset.BOTTOM_CENTER);
        avurnavPlacemarkNormalAttributes.setScale(0.3);

        avurnavMultiPlacemarkNormalAttributes = new PointPlacemarkAttributes();
        avurnavMultiPlacemarkNormalAttributes.setImageAddress(DEFAULT_AVURNAV_IMAGE_ADDRESS);
        avurnavMultiPlacemarkNormalAttributes.setImageOffset(Offset.BOTTOM_CENTER);
        avurnavMultiPlacemarkNormalAttributes.setScale(0.3);

        sailingDirectionsMultiPlacemarkNormalAttributes = new PointPlacemarkAttributes();
        sailingDirectionsMultiPlacemarkNormalAttributes.setImageAddress(DIRECTIONS_IMAGE_ADDRESS);
        sailingDirectionsMultiPlacemarkNormalAttributes.setImageOffset(Offset.BOTTOM_CENTER);
        sailingDirectionsMultiPlacemarkNormalAttributes.setScale(0.6);

        avurnavPolygonNormalAttributes = new BasicShapeAttributes();
        avurnavPolygonNormalAttributes.setInteriorMaterial(new Material(Color.LIGHT_GRAY));
        avurnavPolygonNormalAttributes.setDrawInterior(true);
        avurnavPolygonNormalAttributes.setInteriorOpacity(0.2);
        avurnavPolygonNormalAttributes.setOutlineMaterial(new Material(Color.LIGHT_GRAY));
        avurnavPolygonNormalAttributes.setEnableLighting(true);

        avurnavPolygonHighlightAttributes = new BasicShapeAttributes(avurnavPolygonNormalAttributes);
        avurnavPolygonHighlightAttributes.setOutlineOpacity(1);
        avurnavPolygonHighlightAttributes.setDrawInterior(true);
        avurnavPolygonHighlightAttributes.setInteriorMaterial(new Material(Color.LIGHT_GRAY));
        avurnavPolygonHighlightAttributes.setInteriorOpacity(0.5);

        sailingDirectionsPolygonNormalAttributes = new BasicShapeAttributes();
        sailingDirectionsPolygonNormalAttributes.setInteriorMaterial(new Material(Color.GREEN));
        sailingDirectionsPolygonNormalAttributes.setDrawInterior(true);
        sailingDirectionsPolygonNormalAttributes.setInteriorOpacity(0.03);
        sailingDirectionsPolygonNormalAttributes.setDrawOutline(false);
        sailingDirectionsPolygonNormalAttributes.setOutlineMaterial(new Material(Color.GREEN));
        sailingDirectionsPolygonNormalAttributes.setOutlineOpacity(0.03);
        sailingDirectionsPolygonNormalAttributes.setEnableLighting(true);

        sailingDirectionsPolygonHighlightAttributes = new BasicShapeAttributes(avurnavPolygonNormalAttributes);
        sailingDirectionsPolygonHighlightAttributes.setOutlineOpacity(.2);
        sailingDirectionsPolygonHighlightAttributes.setDrawInterior(true);
        sailingDirectionsPolygonHighlightAttributes.setInteriorMaterial(new Material(Color.GREEN));
        sailingDirectionsPolygonHighlightAttributes.setInteriorOpacity(0.2);
    }

    private void createPointPlacemark(double lat, double lon, String label, String globalZone, String description) {
        placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 10));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setValue(AVKey.DISPLAY_NAME, label + "\n" + globalZone);
        placemark.setValue("TYPE", "Avurnav");
        placemark.setValue("TITLE", label);
        placemark.setValue("TEXT", description == null ? DEFAULT_DESCRIPTION : description);
        aisSurveyZoneLayer.addRenderable(placemark);
    }

    private String createText(Avurnav a) {
        String tmp = Translator.tr("navigation.avurnav.globalZone") + " : " + a.getGlobalZone() + "\n"
                + Translator.tr("navigation.avurnav.broadcastTime") + " : " + a.getBroadcastTime() + "\n"
                + Translator.tr("navigation.avurnav.expirationDate") + " : " + a.getExpirationDate() + "\n"
                + Translator.tr("navigation.avurnav.description") + " : " + a.getDescription() + "\n\n";
        tmp = tmp.replace("\t", "");
        return tmp;
    }
}
