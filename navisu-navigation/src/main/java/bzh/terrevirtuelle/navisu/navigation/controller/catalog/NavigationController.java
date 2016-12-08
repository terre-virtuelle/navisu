/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.catalog;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Behavior;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.widgets.textArea.TextAreaController;
import com.vividsolutions.jts.algorithm.Centroid;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public abstract class NavigationController
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
    private TextAreaController textAreaController;
    private String[] navClassNameT;
    private String navClassName;
    protected RenderableLayer iconsLayer;

    public NavigationController(S57Behavior s57Behavior, GuiAgentServices guiAgentServices,
            NavigationData navigationData, double range,
            String displayName, String description) {
        super(s57Behavior, navigationData, range);
        this.displayName = displayName;
        this.description = description;
        this.guiAgentServices = guiAgentServices;
        navClassNameT = navigationData.getClass().getName().split("\\.");
        navClassName = navClassNameT[navClassNameT.length - 1];
        createAttributes();
       // createPointPlacemark(navigationData.getLocation().getLat(), navigationData.getLocation().getLon());

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
                Centroid centroid = new Centroid(geometry);
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
            Centroid centroid = new Centroid(geometry);
            Coordinate coord = centroid.getCentroid();
            createPointPlacemark(coord);
        }
    }

    protected abstract void createAttributes();
   
    private void createPointPlacemark(Coordinate coordinate) {
        pointPlacemark = new PointPlacemark(Position.fromDegrees(coordinate.y, coordinate.x, 0));
        pointPlacemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        pointPlacemark.setValue(AVKey.DISPLAY_NAME, displayName);
        pointPlacemark.setValue("TYPE", navClassName);
        pointPlacemark.setValue("TITLE", displayName);
        pointPlacemark.setValue("TEXT", description);
        pointPlacemark.setAttributes(placemarkNormalAttributes);
    }

    @Override
    public void activate() {
        if (layer != null && first == true) {
            layer.addRenderable(surveyZone);
            iconsLayer.addRenderable(pointPlacemark);
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
        if (iconsLayer != null) {
            iconsLayer.removeAllRenderables();
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

    public void setIconsLayer(RenderableLayer iconsLayer) {
        this.iconsLayer = iconsLayer;
    }

}
