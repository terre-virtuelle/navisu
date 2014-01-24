/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model.misc;

/**
 *
 * @author Morvan
 */
public class GPSDataShip {

    private int hours;
    private int minutes;
    private int seconds;
    private float latitude;
    private float longitude;
    private float cog;
    private float sog;

    /**
     *
     */
    public GPSDataShip() {
    }

    /**
     *
     *
     * @param hours
     * @param minutes
     * @param seconds
     * @param sog
     */
    public GPSDataShip(int hours, int minutes, int seconds, float sog) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.sog = sog;
    }

    /**
     *
     * @param hours
     * @param minutes
     * @param seconds
     * @param latitude
     * @param longitude
     */
    public GPSDataShip(int hours, int minutes, int seconds, float latitude, float longitude) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param hours
     * @param minutes
     * @param seconds
     * @param latitude
     * @param longitude
     * @param cog
     */
    public GPSDataShip(int hours, int minutes, int seconds, float latitude, float longitude, float cog) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cog = cog;
    }

    /**
     *
     * @param hours
     * @param minutes
     * @param seconds
     * @param latitude
     * @param longitude
     * @param cog
     * @param sog
     */
    public GPSDataShip(int hours, int minutes, int seconds, float latitude, float longitude, float cog, float sog) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cog = cog;
        this.sog = sog;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "GPSDataShip{" + "hours=" + hours + "minutes=" + minutes + "seconds=" + seconds + "latitude=" + latitude + "longitude=" + longitude + "cog=" + cog + "sog=" + sog + '}';
    }

    /**
     *
     * @return
     */
    public float getCog() {
        return cog;
    }

    /**
     *
     * @param cog
     */
    public void setCog(float cog) {
        this.cog = cog;
    }

    /**
     *
     * @return
     */
    public int getHours() {
        return hours;
    }

    /**
     *
     * @param hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     *
     * @return
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     *
     * @param minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     *
     * @return
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     *
     * @param seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     *
     * @return
     */
    public float getSog() {
        return sog;
    }

    /**
     *
     * @param sog
     */
    public void setSog(float sog) {
        this.sog = sog;
    }

}
