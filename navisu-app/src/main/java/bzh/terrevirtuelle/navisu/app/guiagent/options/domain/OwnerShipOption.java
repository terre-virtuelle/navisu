/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.domain;

/**
 *
 * @author serge
 * @date Jun 25, 2017
 */
public class OwnerShipOption
        extends Option {

    /*
    properties.setProperty("name", name);
            properties.setProperty("mmsi", mmsi);
            properties.setProperty("country", country);
            properties.setProperty("length", length);
            properties.setProperty("width", width);
            properties.setProperty("draught", draught);
            properties.setProperty("shipType", shipType);
            properties.setProperty("navigationalStatus", navigationalStatus);
            properties.setProperty("callSign", callSign);
            properties.setProperty("latitude", latitude);
            properties.setProperty("longitude", longitude);
            properties.setProperty("cog", cog);
            properties.setProperty("sog", sog);
            properties.setProperty("daeModelPath", daeModelPath);
            properties.setProperty("scale", scale);
     */

    private String name;
    private String mmsi;
    private String country;
    private String length;
    private String width;
    private String draught;
    private String shipType;
    private String navigationalStatus;
    private String callSign;
    private String latitude;
    private String longitude;
    private String cog;
    private String sog;
    private String daeModelPath;
    private String scale;

    public OwnerShipOption(String name, String mmsi, String country, String length, String width, String draught, String shipType, String navigationalStatus, String callSign, String latitude, String longitude, String cog, String sog, String daeModelPath, String scale) {
        this.name = name;
        this.mmsi = mmsi;
        this.country = country;
        this.length = length;
        this.width = width;
        this.draught = draught;
        this.shipType = shipType;
        this.navigationalStatus = navigationalStatus;
        this.callSign = callSign;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cog = cog;
        this.sog = sog;
        this.daeModelPath = daeModelPath;
        this.scale = scale;
    }

    /**
     * Get the value of scale
     *
     * @return the value of scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * Set the value of scale
     *
     * @param scale new value of scale
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * Get the value of daeModelPath
     *
     * @return the value of daeModelPath
     */
    public String getDaeModelPath() {
        return daeModelPath;
    }

    /**
     * Set the value of daeModelPath
     *
     * @param daeModelPath new value of daeModelPath
     */
    public void setDaeModelPath(String daeModelPath) {
        this.daeModelPath = daeModelPath;
    }

    /**
     * Get the value of sog
     *
     * @return the value of sog
     */
    public String getSog() {
        return sog;
    }

    /**
     * Set the value of sog
     *
     * @param sog new value of sog
     */
    public void setSog(String sog) {
        this.sog = sog;
    }

    /**
     * Get the value of cog
     *
     * @return the value of cog
     */
    public String getCog() {
        return cog;
    }

    /**
     * Set the value of cog
     *
     * @param cog new value of cog
     */
    public void setCog(String cog) {
        this.cog = cog;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the value of callSign
     *
     * @return the value of callSign
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Set the value of callSign
     *
     * @param callSign new value of callSign
     */
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    /**
     * Get the value of navigationalStatus
     *
     * @return the value of navigationalStatus
     */
    public String getNavigationalStatus() {
        return navigationalStatus;
    }

    /**
     * Set the value of navigationalStatus
     *
     * @param navigationalStatus new value of navigationalStatus
     */
    public void setNavigationalStatus(String navigationalStatus) {
        this.navigationalStatus = navigationalStatus;
    }

    /**
     * Get the value of shipType
     *
     * @return the value of shipType
     */
    public String getShipType() {
        return shipType;
    }

    /**
     * Set the value of shipType
     *
     * @param shipType new value of shipType
     */
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    /**
     * Get the value of draught
     *
     * @return the value of draught
     */
    public String getDraught() {
        return draught;
    }

    /**
     * Set the value of draught
     *
     * @param draught new value of draught
     */
    public void setDraught(String draught) {
        this.draught = draught;
    }

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public String getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * Get the value of length
     *
     * @return the value of length
     */
    public String getLength() {
        return length;
    }

    /**
     * Set the value of length
     *
     * @param length new value of length
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * Get the value of mmsi
     *
     * @return the value of mmsi
     */
    public String getMmsi() {
        return mmsi;
    }

    /**
     * Set the value of mmsi
     *
     * @param mmsi new value of mmsi
     */
    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OwnerShipOption{" + "name=" + name + ", mmsi=" + mmsi + ", country=" + country + ", length=" + length + ", width=" + width + ", draught=" + draught + ", shipType=" + shipType + ", navigationalStatus=" + navigationalStatus + ", callSign=" + callSign + ", latitude=" + latitude + ", longitude=" + longitude + ", cog=" + cog + ", sog=" + sog + ", daeModelPath=" + daeModelPath + ", scale=" + scale + '}';
    }

}
