/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.ship.model;

import java.util.Calendar;

/**
 *
 * @author Serge
 */
public class ShipBuilder {

    private int mmsi;
    private int imo;
    private String name;
    private String country;
    private float latitude;
    private float longitude;
    private float heading;
    private float cog;
    private float sog;
    private float rot;
    private float width;
    private float length;
    private float draught;
    private int shipType;
    private int navigationalStatus;
    private int electronicPositionDevice;
    private String callSign;
    private Calendar ETA;
    private String destination;
    
    private ShipBuilder() {
    }

    public static ShipBuilder create() {
        return new ShipBuilder();
    }

    public Ship build() {
        return new Ship(mmsi, imo, name,
                heading, cog, sog, rot,
                latitude, longitude,
                width, length, draught,
                shipType, navigationalStatus, electronicPositionDevice, callSign,
                ETA, destination, country);
    }

    public ShipBuilder eta(Calendar ETA) {
        this.ETA = ETA;
        return this;
    }

    public ShipBuilder mmsi(int mmsi) {
        this.mmsi = mmsi;
        return this;
    }

    public ShipBuilder imo(int imo) {
        this.imo = imo;
        return this;
    }

    public ShipBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ShipBuilder country(String country) {
        this.country = country;
        return this;
    }

    public ShipBuilder latitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public ShipBuilder longitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public ShipBuilder heading(float heading) {
        this.heading = heading;
        return this;
    }

    public ShipBuilder cog(float cog) {
        this.cog = cog;
        return this;
    }

    public ShipBuilder sog(float sog) {
        this.sog = sog;
        return this;
    }

    public ShipBuilder rot(float rot) {
        this.rot = rot;
        return this;
    }

    public ShipBuilder width(float width) {
        this.width = width;
        return this;
    }

    public ShipBuilder length(float length) {
        this.length = length;
        return this;
    }

    public ShipBuilder draught(float draught) {
        this.draught = draught;
        return this;
    }

    public ShipBuilder shipType(int shipType) {
        this.shipType = shipType;
        return this;
    }

    public ShipBuilder navigationalStatus(int navigationalStatus) {
        this.navigationalStatus = navigationalStatus;
        return this;
    }

    public ShipBuilder electronicPositionDevice(int electronicPositionDevice) {
        this.electronicPositionDevice = electronicPositionDevice;
        return this;
    }

    public ShipBuilder callSign(String callSign) {
        this.callSign = callSign;
        return this;
    }

    public ShipBuilder destination(String destination) {
        this.destination = destination;
        return this;
    }

}
