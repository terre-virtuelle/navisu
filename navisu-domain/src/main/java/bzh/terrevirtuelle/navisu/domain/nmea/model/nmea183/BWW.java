/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * BWW Bearing – Waypoint to Waypoint
 * @author Serge Morvan
 */
@XmlRootElement(name="BWW")
@XmlAccessorType(XmlAccessType.FIELD)
public class BWW
        extends NMEA {
    
    private float bearingDegreesTrue;
    private float bearingDegreesMagnetic;
    private String toWaypoint;
    private String fromWaypoint;

    public BWW(String device, 
            String sentence,
            float bearingDegreesTrue, 
            float bearingDegreesMagnetic, 
            String toWaypoint, 
            String fromWaypoint) {
        super(device, sentence);
        this.bearingDegreesTrue = bearingDegreesTrue;
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
        this.toWaypoint = toWaypoint;
        this.fromWaypoint = fromWaypoint;
    }

    public BWW() {
    }

    /**
     * Get the value of fromWaypoint
     *
     * @return the value of fromWaypoint
     */
    public String getFromWaypoint() {
        return fromWaypoint;
    }

    /**
     * Set the value of fromWaypoint
     *
     * @param fromWaypoint new value of fromWaypoint
     */
    public void setFromWaypoint(String fromWaypoint) {
        this.fromWaypoint = fromWaypoint;
    }

    /**
     * Get the value of toWaypoint
     *
     * @return the value of toWaypoint
     */
    public String getToWaypoint() {
        return toWaypoint;
    }

    /**
     * Set the value of toWaypoint
     *
     * @param toWaypoint new value of toWaypoint
     */
    public void setToWaypoint(String toWaypoint) {
        this.toWaypoint = toWaypoint;
    }

    /**
     * Get the value of bearingDegreesMagnetic
     *
     * @return the value of bearingDegreesMagnetic
     */
    public float getBearingDegreesMagnetic() {
        return bearingDegreesMagnetic;
    }

    /**
     * Set the value of bearingDegreesMagnetic
     *
     * @param bearingDegreesMagnetic new value of bearingDegreesMagnetic
     */
    public void setBearingDegreesMagnetic(float bearingDegreesMagnetic) {
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
    }

    /**
     * Get the value of bearingDegreesTrue
     *
     * @return the value of bearingDegreesTrue
     */
    public float getBearingDegreesTrue() {
        return bearingDegreesTrue;
    }

    /**
     * Set the value of bearingDegreesTrue
     *
     * @param bearingDegreesTrue new value of bearingDegreesTrue
     */
    public void setBearingDegreesTrue(float bearingDegreesTrue) {
        this.bearingDegreesTrue = bearingDegreesTrue;
    }

    @Override
    public String toString() {
        return "BWW{" + "bearingDegreesTrue=" + bearingDegreesTrue + ", bearingDegreesMagnetic=" + bearingDegreesMagnetic + ", toWaypoint=" + toWaypoint + ", fromWaypoint=" + fromWaypoint + '}';
    }

}
