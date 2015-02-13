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
 * Bearing Origin to Destination 
 * @author Serge MORVAN
 */
@XmlRootElement(name="BOD")
@XmlAccessorType(XmlAccessType.FIELD)
public class BOD
        extends NMEA {

    private float bearingTrue;
    private float bearingMagnetic;
    private String destinationWaypointID;
    private String originWaypointID;

    public BOD(String device, 
            String sentence, 
            float bearingTrue, 
            float bearingMagnetic, 
            String destinationWaypointID, 
            String originWaypointID) {
        super(device, sentence);
        this.bearingTrue = bearingTrue;
        this.bearingMagnetic = bearingMagnetic;
        this.destinationWaypointID = destinationWaypointID;
        this.originWaypointID = originWaypointID;
    }

    public BOD() {
    }

    /**
     * Get the value of originWaypointID
     *
     * @return the value of originWaypointID
     */
    public String getOriginWaypointID() {
        return originWaypointID;
    }

    /**
     * Set the value of originWaypointID
     *
     * @param originWaypointID new value of originWaypointID
     */
    public void setOriginWaypointID(String originWaypointID) {
        this.originWaypointID = originWaypointID;
    }

    /**
     * Get the value of destinationWaypointID
     *
     * @return the value of destinationWaypointID
     */
    public String getDestinationWaypointID() {
        return destinationWaypointID;
    }

    /**
     * Set the value of destinationWaypointID
     *
     * @param destinationWaypointID new value of destinationWaypointID
     */
    public void setDestinationWaypointID(String destinationWaypointID) {
        this.destinationWaypointID = destinationWaypointID;
    }

    /**
     * Get the value of bearingMagnetic
     *
     * @return the value of bearingMagnetic
     */
    public float getBearingMagnetic() {
        return bearingMagnetic;
    }

    /**
     * Set the value of bearingMagnetic
     *
     * @param bearingMagnetic new value of bearingMagnetic
     */
    public void setBearingMagnetic(float bearingMagnetic) {
        this.bearingMagnetic = bearingMagnetic;
    }

    /**
     * Get the value of bearingTrue
     *
     * @return the value of bearingTrue
     */
    public float getBearingTrue() {
        return bearingTrue;
    }

    /**
     * Set the value of bearingTrue
     *
     * @param bearingTrue new value of bearingTrue
     */
    public void setBearingTrue(float bearingTrue) {
        this.bearingTrue = bearingTrue;
    }

    @Override
    public String toString() {
        return "BOD{" + "bearingTrue=" + bearingTrue + ", bearingMagnetic=" + bearingMagnetic + ", destinationWaypointID=" + destinationWaypointID + ", originWaypointID=" + originWaypointID + '}';
    }

       

}
