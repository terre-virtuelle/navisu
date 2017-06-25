/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.domain;

/**
 *
 * @author Serge
 */
public class OwnerShipOptionBuilder {

    private String mmsi = " ";
    private String name = " ";
    private String country = " ";
    private String latitude = " ";
    private String longitude = " ";
    private String cog = " ";
    private String sog = " ";
    private String width = " ";
    private String length = " ";
    private String draught = " ";
    private String shipType = " ";
    private String navigationalStatus = " ";
    private String callSign = " ";
    private String daeModelPath = " ";
    private String scale = " ";

    private OwnerShipOptionBuilder() {
    }

    public static OwnerShipOptionBuilder create() {
        return new OwnerShipOptionBuilder();
    }

    public OwnerShipOption build() {
        return new OwnerShipOption(name,
                mmsi,
                country,
                length, width,
                draught,
                shipType,
                navigationalStatus,
                callSign,
                latitude, longitude,
                cog, sog,
                daeModelPath, scale
        );

    }

    public OwnerShipOptionBuilder mmsi(String mmsi) {
        this.mmsi = mmsi;
        return this;
    }

    public OwnerShipOptionBuilder name(String name) {
        this.name = name;
        return this;
    }

    public OwnerShipOptionBuilder country(String country) {
        this.country = country;
        return this;
    }

    public OwnerShipOptionBuilder latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public OwnerShipOptionBuilder longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public OwnerShipOptionBuilder cog(String cog) {
        this.cog = cog;
        return this;
    }

    public OwnerShipOptionBuilder sog(String sog) {
        this.sog = sog;
        return this;
    }

    public OwnerShipOptionBuilder width(String width) {
        this.width = width;
        return this;
    }

    public OwnerShipOptionBuilder length(String length) {
        this.length = length;
        return this;
    }

    public OwnerShipOptionBuilder draught(String draught) {
        this.draught = draught;
        return this;
    }

    public OwnerShipOptionBuilder shipType(String shipType) {
        this.shipType = shipType;
        return this;
    }

    public OwnerShipOptionBuilder navigationalStatus(String navigationalStatus) {
        this.navigationalStatus = navigationalStatus;
        return this;
    }

    public OwnerShipOptionBuilder callSign(String callSign) {
        this.callSign = callSign;
        return this;
    }

    public OwnerShipOptionBuilder daeModelPath(String daeModelPath) {
        this.daeModelPath = daeModelPath;
        return this;
    }

    public OwnerShipOptionBuilder scale(String scale) {
        this.scale = scale;
        return this;
    }
}
