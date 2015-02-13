/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * BWC Bearing and Distance to Waypoint - great circle
 * @author Camille AUBRY, Alexis BURGER, Chintana CHANTHABOURY, Teddy VALLEE
 * @author Serge MORVAN
 */
@XmlRootElement(name="BWC")
@XmlAccessorType(XmlAccessType.FIELD)
public class BWC
        extends NMEA {

    private Calendar utc;
    private float latitude;
    private float longitude;
    private float bearingDegreesTrue;
    private float bearingDegreesMagnetic;
    private float distanceToWayPoint;
    private String unitsOfDistanceToWayPoint;
    private String waypointID;

    /**
     * Bearing & Distance to Waypoint using a Great Circle route. Time (UTC) and
     * distance & bearing to, and location of, a specified waypoint from present
     * position along the great circle path.
     *
     * $GPBWC,225444,4917.24,N,12309.57,W,051.9,T,031.6,M,001.3,N,004*29
     *
     * where: BWC Bearing and distance to waypoint - great circle 225444 UTC
     * time of fix 22:54:44 4917.24,N Latitude of waypoint 12309.57,W Longitude
     * of waypoint 051.9,T Bearing to waypoint, degrees true 031.6,M Bearing to
     * waypoint, degrees magnetic 001.3,N Distance to waypoint, Nautical miles
     * 004 Waypoint ID 29 checksum
     *
     *
     * @param device
     * @param sentence
     * @param utc
     * @param latitude
     * @param longitude
     * @param bearingDegreesTrue
     * @param bearingDegreesMagnetic
     * @param distanceToWayPoint
     * @param unitsOfDistanceToWayPoint
     * @param waypointID
     */
    public BWC(
            String device,
            String sentence,
            Calendar utc,
            float latitude,
            float longitude,
            float bearingDegreesTrue,
            float bearingDegreesMagnetic,
            float distanceToWayPoint,
            String unitsOfDistanceToWayPoint,
            String waypointID) {
        super(device, sentence);
        this.utc = utc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bearingDegreesTrue = bearingDegreesTrue;
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
        this.distanceToWayPoint = distanceToWayPoint;
        this.unitsOfDistanceToWayPoint = unitsOfDistanceToWayPoint;
        this.waypointID = waypointID;
    }

    public BWC() {
    }

    /**
     *
     * @param utc
     * @param unitsOfDistanceToWayPoint
     */
    public BWC(Calendar utc, String unitsOfDistanceToWayPoint) {
        this.utc = utc;
        this.unitsOfDistanceToWayPoint = unitsOfDistanceToWayPoint;
    }

    

    /**
     *
     * @return
     */
    public float getBearingDegreesMagnetic() {
        return bearingDegreesMagnetic;
    }

    /**
     *
     * @param bearingDegreesMagnetic
     */
    public void setBearingDegreesMagnetic(float bearingDegreesMagnetic) {
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
    }

    /**
     *
     * @return
     */
    public float getBearingDegreesTrue() {
        return bearingDegreesTrue;
    }

    /**
     *
     * @param bearingDegreesTrue
     */
    public void setBearingDegreesTrue(float bearingDegreesTrue) {
        this.bearingDegreesTrue = bearingDegreesTrue;
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
    public String getWaypointID() {
        return waypointID;
    }

    /**
     *
     * @param waypointID
     */
    public void setWaypointID(String waypointID) {
        this.waypointID = waypointID;
    }

    /**
     *
     * @return
     */
    public float getDistanceToWayPoint() {
        return distanceToWayPoint;
    }

    /**
     *
     * @param distanceToWayPoint
     */
    public void setDistanceToWayPoint(float distanceToWayPoint) {
        this.distanceToWayPoint = distanceToWayPoint;
    }

    /**
     *
     * @return
     */
    public String getUnitsOfDistanceToWayPoint() {
        return unitsOfDistanceToWayPoint;
    }

    /**
     *
     * @param unitsOfDistanceToWayPoint
     */
    public void setUnitsOfDistanceToWayPoint(String unitsOfDistanceToWayPoint) {
        this.unitsOfDistanceToWayPoint = unitsOfDistanceToWayPoint;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss"); 
        return formater.format(utc.getTime());
    }

    public Calendar getUtc() {
        return utc;
    }

    /**
     *
     * @param utc
     */
    public void setDate(Calendar date) {
        this.utc = date;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "BWC{" +  "UTC=" + formater.format(utc.getTime()) + ", latitude=" + latitude + ", longitude=" + longitude + ", bearingDegreesTrue=" + bearingDegreesTrue + ", bearingDegreesMagnetic=" + bearingDegreesMagnetic + ", distanceToWayPoint=" + distanceToWayPoint + ", unitsOfDistanceToWayPoint=" + unitsOfDistanceToWayPoint + ", waypointID=" + waypointID + '}';
    }
}
