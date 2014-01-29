/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.gps.locator.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.ship.Ship;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.List;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Serge
 */
public class ShipView
        extends Polygon {
    
    private double latitude;
    private double longitude;
    private double cog;
    private final Ship ship;
    
    private ShapeAttributes pathAttrs;
    
    public ShipView(List<Position> positions, Ship ship) {
        super(positions);
        this.ship = ship;
        latitude = ship.getLatitude();
        longitude = ship.getLongitude();
        cog = ship.getCog();
        ship.latitudeProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            latitude = t1.doubleValue();
            moveTo(Position.fromDegrees(latitude, longitude, 100));
          //  GeoWorldWindViewImpl.getWW().redrawNow();
        });
        ship.longitudeProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            longitude = t1.doubleValue();
            moveTo(Position.fromDegrees(latitude, longitude, 100));
          //  GeoWorldWindViewImpl.getWW().redrawNow();
        });
        ship.cogProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            cog = t1.doubleValue();
            setRotation(cog - 90);
          //  GeoWorldWindViewImpl.getWW().redrawNow();
        });
    }
    
    public void setCog(double cog) {
        System.out.println("Cog : " + (int)cog);
        setRotation(cog - 90);
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
    }
    
    public ShapeAttributes getPathAttrs() {
        return pathAttrs;
    }
    
    public void setLatLon(double lat, double lon) {
        
    }
    
    public void setPathAttrs(ShapeAttributes pathAttrs) {
        setAttributes(pathAttrs);
    }
    
    public Ship getShip() {
        return ship;
    }
    
}
