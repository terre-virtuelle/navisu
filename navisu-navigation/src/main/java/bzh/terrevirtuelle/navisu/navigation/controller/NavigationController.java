/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Behavior;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import bzh.terrevirtuelle.navisu.widgets.textArea.TextAreaController;
import com.vividsolutions.jts.algorithm.CentroidArea;
import com.vividsolutions.jts.algorithm.CentroidPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class NavigationController
        extends S57Controller {

    private GuiAgentServices guiAgentServices;
    private String displayName;
    private String description;
    private WKTReader wktReader;
    private String wkt;
    private Coordinate[] coordinates = null;
    private Geometry geometry = null;
    private Polygon pgon = null;
    protected PointPlacemarkAttributes placemarkNormalAttributes;
    protected ShapeAttributes polygonNormalAttributes;
    protected ShapeAttributes polygonHighlightAttributes;
    protected TextAreaController textAreaController;

    public NavigationController(S57Behavior s57Behavior, NavigationData navigationData, double range,
            String displayName, String description) {
        super(s57Behavior, navigationData, range);
        createAttributes();
        wkt = navigationData.getGeometry();
        wktReader = new WKTReader();
        try {
            geometry = wktReader.read(wkt);
            coordinates = geometry.getCoordinates();
        } catch (ParseException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (wkt.contains("POINT") || wkt.contains("Point")) {
            createMultiPoint();
        }
        if (wkt.contains("POLYGON") || wkt.contains("Polygon")) {
            createMultiPolygon();
        }
    }

    private void createMultiPoint() {
        if (coordinates != null) {
            if (coordinates.length == 1) {
                setLat(coordinates[0].y);
                setLon(coordinates[0].x);
                createPointPlacemark(coordinates[0]);
            } else {
                for (Coordinate coordinate : coordinates) {
                    createPointPlacemark(coordinate);
                }
                CentroidPoint centroid = new CentroidPoint();
                centroid.add(geometry);
                Coordinate coord = centroid.getCentroid();
                setLat(coord.y);
                setLon(coord.x);
                createPointPlacemark(coord);
            }
        }
    }

    private void createMultiPolygon() {
        ArrayList<Position> pathPositions = new ArrayList<>();
        if (coordinates != null) {
            for (Coordinate coordinate : coordinates) {
                pathPositions.add(Position.fromDegrees(coordinate.y, coordinate.x, 200));
            }
            pgon = new Polygon(pathPositions);
            pgon.setAttributes(polygonNormalAttributes);
            pgon.setHighlightAttributes(polygonHighlightAttributes);
            pgon.setValue(AVKey.DISPLAY_NAME, displayName);

            //TODO desarmer sur le layer tree
            CentroidArea centroid = new CentroidArea();
            centroid.add(geometry);
            Coordinate coord = centroid.getCentroid();
            createPointPlacemark(coord);
        }
    }

    protected void createAttributes() {
        placemarkNormalAttributes = new PointPlacemarkAttributes();
       String []t = navigationData.getClass().getName().split("\\.");
        placemarkNormalAttributes.setImageAddress(
                NavigationIcons.ICONS.get(t[t.length-1]));
        placemarkNormalAttributes.setImageOffset(Offset.BOTTOM_CENTER);
        placemarkNormalAttributes.setScale(0.3);

        polygonNormalAttributes = new BasicShapeAttributes();
        polygonNormalAttributes.setInteriorMaterial(new Material(Color.LIGHT_GRAY));
        polygonNormalAttributes.setDrawInterior(true);
        polygonNormalAttributes.setInteriorOpacity(0.2);
        polygonNormalAttributes.setOutlineMaterial(new Material(Color.LIGHT_GRAY));
        polygonNormalAttributes.setEnableLighting(true);

        polygonHighlightAttributes = new BasicShapeAttributes(polygonNormalAttributes);
        polygonHighlightAttributes.setOutlineOpacity(1);
        polygonHighlightAttributes.setDrawInterior(true);
        polygonHighlightAttributes.setInteriorMaterial(new Material(Color.LIGHT_GRAY));
        polygonHighlightAttributes.setInteriorOpacity(0.5);

    }

    private void createPointPlacemark(Coordinate coordinate) {
        pointPlacemark = new PointPlacemark(Position.fromDegrees(coordinate.y, coordinate.x, 0));
        pointPlacemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        pointPlacemark.setValue(AVKey.DISPLAY_NAME, displayName);
        pointPlacemark.setValue("TYPE", navigationData.getClass().getName());
        pointPlacemark.setValue("TITLE", displayName);
        pointPlacemark.setValue("TEXT", description);
        pointPlacemark.setAttributes(placemarkNormalAttributes);
    }

    private void addListeners() {

        wwd.addSelectListener((SelectEvent event) -> {
            PickedObject po = event.getTopPickedObject();
            if (po != null && po.getObject() instanceof PointPlacemark) {
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
                    PointPlacemark placemark1 = (PointPlacemark) po.getObject();
                    if (placemark1.getValue("TYPE") != null) {
                        if (placemark1.getValue("TYPE").equals(navigationData.getClass().getName())) {
                            Platform.runLater(() -> {
                                textAreaController = new TextAreaController();
                                textAreaController.getDataTextArea().setWrapText(true);
                                textAreaController.getTitleText().setText((String) placemark1.getValue("TITLE"));
                                textAreaController.getDataTextArea().setText((String) placemark1.getValue("TEXT"));
                                guiAgentServices.getRoot().getChildren().add(textAreaController);
                            });
                        }
                    }
                }
                event.consume();
            }
        });
    }

    @Override
    public void activate() {
        if (layer != null && first == true) {
            layer.addRenderable(surveyZone);
            layer.addRenderable(pointPlacemark);
            if (pgon != null) {
                layer.addRenderable(pgon);
            }
            first = false;
        }
        subscribe();
    }

    @Override
    public void deactivate() {
        if (layer != null) {
            layer.removeAllRenderables();
        }
        unsubscribe();
    }

    @Override
    public void updateTarget(Ship ship) {
        s57Behavior.doIt(distance, azimuth);
    }

    public PointPlacemarkAttributes getPlacemarkNormalAttributes() {
        return placemarkNormalAttributes;
    }

    public void setPlacemarkNormalAttributes(PointPlacemarkAttributes placemarkNormalAttributes) {
        this.placemarkNormalAttributes = placemarkNormalAttributes;
    }

    public ShapeAttributes getPolygonNormalAttributes() {
        return polygonNormalAttributes;
    }

    public void setPolygonNormalAttributes(ShapeAttributes polygonNormalAttributes) {
        this.polygonNormalAttributes = polygonNormalAttributes;
    }

    public ShapeAttributes getPolygonHighlightAttributes() {
        return polygonHighlightAttributes;
    }

    public void setPolygonHighlightAttributes(ShapeAttributes polygonHighlightAttributes) {
        this.polygonHighlightAttributes = polygonHighlightAttributes;
    }

    public TextAreaController getTextAreaController() {
        return textAreaController;
    }

    public void setTextAreaController(TextAreaController textAreaController) {
        this.textAreaController = textAreaController;
    }
    
}
