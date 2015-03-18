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
public class Point3D {

    private int id = 0;

    public double lon = 0.0;

    public double lat = 0.0;

    public double elevation = 0.0;

    public Point3D() {
    }

    public Point3D(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    public Point3D(double lat, double lon, double elevation) {
        this.lon = lon;
        this.lat = lat;
        this.elevation = elevation;
    }

    public Point3D(int id, double lat, double lon) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public Point3D(int id, double lat, double lon, double elevation) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
        this.elevation = elevation;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
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
