/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf;

/**
 *
 * @author serge
 * @date Sep 8, 2016
 */
public class GeoData {

    private double latitude;

    private double longitude;

    private double data;

    public GeoData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoData(double latitude, double longitude, double data) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.data = data;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    final public double getData() {
        return data;
    }

    final public void setData(double data) {
        this.data = data;
    }

}
