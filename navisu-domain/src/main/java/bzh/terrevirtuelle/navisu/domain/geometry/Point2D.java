/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

/**
 * @date 15 mars 2015
 * @author Serge Morvan
 */
public class Point2D {

    public double lon;

    public double lat;

    public double elevation;

    public Point2D() {
    }

    public Point2D(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    public Point2D(double lat, double lon, double elevation) {
        this.lon = lon;
        this.lat = lat;
        this.elevation = elevation;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

}
