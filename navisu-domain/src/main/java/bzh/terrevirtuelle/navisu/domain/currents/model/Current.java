/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.currents.model;

/**
 *
 * @author Serge
 */
public class Current {

    private double lat;

    private double lon;

    private double speed;

    private double direction;

    private double depth = 0.0;

    public Current() {
    }

    public Current(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Current(double lat, double lon, double speed) {
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
    }

    public Current(double lat, double lon, double speed, double direction) {
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
        this.direction = direction;
    }

    public Current(double lat, double lon, double speed, double direction, double depth) {
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
        this.direction = direction;
        this.depth = depth;
    }

    /**
     * Get the value of depth
     *
     * @return the value of depth
     */
    public double getDepth() {
        return depth;
    }

    /**
     * Set the value of depth
     *
     * @param depth new value of depth
     */
    public void setDepth(double depth) {
        this.depth = depth;
    }

    /**
     * Get the value of direction
     *
     * @return the value of direction
     */
    public double getDirection() {
        return direction;
    }

    /**
     * Set the value of direction
     *
     * @param direction new value of direction
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Get the value of speed
     *
     * @return the value of speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Set the value of speed
     *
     * @param speed new value of speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Get the value of lon
     *
     * @return the value of lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * Set the value of lon
     *
     * @param lon new value of lon
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * Get the value of lat
     *
     * @return the value of lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * Set the value of lat
     *
     * @param lat new value of lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Current{" + "lat=" + lat + ", lon=" + lon + ", speed=" + speed + ", direction=" + direction + ", depth=" + depth + '}';
    }

}
