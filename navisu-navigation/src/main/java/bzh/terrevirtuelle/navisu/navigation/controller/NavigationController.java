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
import static com.hp.hpl.jena.vocabulary.DCTerms.description;
import com.vividsolutions.jts.algorithm.CentroidArea;
import com.vividsolutions.jts.algorithm.CentroidPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;
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
    private Point point;
    private PointPlacemarkAttributes placemarkNormalAttributes;
    private ShapeAttributes polygonNormalAttributes;
    private ShapeAttributes polygonHighlightAttributes;
    private TextAreaController textAreaController;

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

    private void createMultiPolygon(String geom, String label, String globalZone, String text,
            ShapeAttributes nAttrs, ShapeAttributes hAttrs, PointPlacemarkAttributes attrs) {
        ArrayList<Position> pathPositions = new ArrayList<>();
        if (coordinates != null) {
            for (Coordinate coordinate : coordinates) {
                pathPositions.add(Position.fromDegrees(coordinate.y, coordinate.x, 200));
            }
            Polygon pgon = new Polygon(pathPositions);
            pgon.setAttributes(nAttrs);
            pgon.setHighlightAttributes(hAttrs);
            pgon.setValue(AVKey.DISPLAY_NAME, label + "\n" + globalZone);

            //TODO desarmer sur le layer tree
            CentroidArea centroid = new CentroidArea();
            centroid.add(geometry);
            Coordinate coord = centroid.getCentroid();
            createPointPlacemark(coord);
        }
    }

    private void createAttributes() {
        placemarkNormalAttributes = new PointPlacemarkAttributes();
        placemarkNormalAttributes.setImageAddress(NavigationIcons.ICONS.get(navigationData.getClass().getName()));
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
        pointPlacemark = new PointPlacemark(Position.fromDegrees(point.getY(), point.getX(), 0));
        pointPlacemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        pointPlacemark.setValue(AVKey.DISPLAY_NAME, displayName);
        pointPlacemark.setValue("TYPE", navigationData.getClass().getName());
        pointPlacemark.setValue("TITLE", displayName);
        pointPlacemark.setValue("TEXT", description);
        
        pointPlacemark.setAttributes(placemarkNormalAttributes);
        layer.addRenderable(pointPlacemark);
    }

    private String createText(Avurnav a) {
        String tmp = Translator.tr("navigation.avurnav.globalZone") + " : " + a.getGlobalZone() + "\n"
                + Translator.tr("navigation.avurnav.broadcastTime") + " : " + a.getBroadcastTime() + "\n"
                + Translator.tr("navigation.avurnav.expirationDate") + " : " + a.getExpirationDate() + "\n"
                + Translator.tr("navigation.avurnav.description") + " : " + a.getDescription() + "\n\n";
        tmp = tmp.replace("\t", "");
        return tmp;
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
}
