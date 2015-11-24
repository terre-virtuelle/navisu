/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @date 12 sept. 2015
 * @author Serge Morvan
 */
public class S57AvurnavController
        extends S57Controller {

    private ShapeAttributes normalAttributes = null;
    private PointPlacemark pointPlacemark = null;
    private String wkt;

    public S57AvurnavController() {
    }

    public S57AvurnavController(Avurnav avurnav, S57Behavior s57Behavior) {
        super(avurnav);
        WKTReader wktReader = new WKTReader();
        Point point = null;
        this.s57Behavior = s57Behavior;
        setId(avurnav.getId());
        wkt = avurnav.getGeometry();
        if (wkt.contains("POINT") || wkt.contains("Point")) {
            try {
                point = (Point) wktReader.read(wkt);
            } catch (ParseException ex) {
                Logger.getLogger(S57AvurnavController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (point != null) {
                setLat(point.getX());
                setLon(point.getY());
            }
            PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(point.getY(), point.getX(), 0));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            placemark.setValue(AVKey.DISPLAY_NAME, "Avurnav : " + avurnav.getId());
            PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
            String imageAddress = "imgages/avurnav.png";
            attrs.setImageAddress(imageAddress);
            attrs.setImageOffset(Offset.BOTTOM_CENTER);
            attrs.setScale(0.65);//0.9
            placemark.setAttributes(attrs);
            setPointPlacemark(placemark);
        }

        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(false);

        shape = new SurfaceCircle(LatLon.fromDegrees(avurnav.getLocation().getLat(), avurnav.getLocation().getLon()), s57Behavior.getRange());
        shape.setAttributes(normalAttributes);
        this.s57Behavior.setShape(shape);
    }

    @Override
    public void updateTarget(Ship ship) {
        if (first == true) {
            layer.addRenderable(shape);
            first = false;
        }
        if (ship.isGpsTarget()) {
            distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
            azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
            s57Behavior.doIt(distance, azimuth, pointPlacemark);
            shape.setValue(AVKey.DISPLAY_NAME, ((Avurnav) navigationData).getDescription() + "\n distance :  "
                    + String.format("%.2f", distance) + " Nm"
                    + "\nazimuth :  " + String.format("%d", (int) azimuth) + " °  ");
        }
    }

    @Override
    public void activate() {
        subscribe();
    }

    @Override
    public void deactivate() {
        unsubscribe();
    }

    @Override
    public String toString() {
        return ((Buoyage) navigationData).getLabel();
    }

    public PointPlacemark getPointPlacemark() {
        return pointPlacemark;
    }

    public void setPointPlacemark(PointPlacemark pointPlacemark) {
        this.pointPlacemark = pointPlacemark;
    }

}
