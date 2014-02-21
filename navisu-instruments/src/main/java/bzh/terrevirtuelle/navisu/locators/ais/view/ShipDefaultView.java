/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.view;

import bzh.terrevirtuelle.navisu.locators.view.ShipTypeColor;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;

/**
 *
 * @author Serge
 */
public class ShipDefaultView
        extends SurfaceCircle {
    
    private double latitude;
    private double longitude;
    private double cog;
    private ShapeAttributes pathAttrs;
    
    public ShipDefaultView(LatLon latLon, double radius) {
        super(latLon, radius);
        /*
        this.latitude = latitude;
        this.longitude = longitude;
        this.cog = cog;
                */
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
        moveTo(Position.fromDegrees(latitude, longitude, 100));
    }
    
    public void setLatLon(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        
    }
    
    public void setPathAttrs(ShapeAttributes pathAttrs) {
        this.pathAttrs = pathAttrs;
        setAttributes(pathAttrs);
    }
    
    public void setType(int type) {
        pathAttrs.setInteriorMaterial(ShipTypeColor.VIEW.get(type));
    }
}
