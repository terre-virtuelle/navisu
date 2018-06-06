/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.geometry;

/**
 * @date 12 mars 2015
 * @author Serge Morvan
 */
public class Point3Df {

    public float lon;

    public float lat;

    public float elevation;

    public Point3Df() {
    }

    
    public Point3Df(float lat, float lon, float elevation) {
        this.lon = lon;
        this.lat = lat;
        this.elevation = elevation;
    }

    /**
     * Get the value of elevation
     *
     * @return the value of elevation
     */
    public float getElevation() {
        return elevation;
    }

    /**
     * Set the value of elevation
     *
     * @param elevation new value of elevation
     */
    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    /**
     * Get the value of lat
     *
     * @return the value of lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * Set the value of lat
     *
     * @param lat new value of lat
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * Get the value of lon
     *
     * @return the value of lon
     */
    public float getLon() {
        return lon;
    }

    /**
     * Set the value of lon
     *
     * @param lon new value of lon
     */
    public void setLon(float lon) {
        this.lon = lon;
    }

}
