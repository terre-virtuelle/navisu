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
 * Autopilot format A
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="APA")
@XmlAccessorType(XmlAccessType.FIELD)
public class APA
        extends NMEA {

    private String status0;
    private String status1;
    private float crossTrackErrorMagnitude;
    private String directionToSteer;
    private String crossTrackUnits;
    private String status2;
    private String status3;
    private int bearingOriginToDestination;
    private String bearingOriginToDestinationType;
    private String destinationWaypointID;

    public APA(String device, String sentence, String status0, String status1, float crossTrackErrorMagnitude, String directionToSteer, String crossTrackUnits, String status2, String status3, int bearingOriginToDestination, String bearingOriginToDestinationType, String destinationWaypointID) {
        super(device, sentence);
        this.status0 = status0;
        this.status1 = status1;
        this.crossTrackErrorMagnitude = crossTrackErrorMagnitude;
        this.directionToSteer = directionToSteer;
        this.crossTrackUnits = crossTrackUnits;
        this.status2 = status2;
        this.status3 = status3;
        this.bearingOriginToDestination = bearingOriginToDestination;
        this.bearingOriginToDestinationType = bearingOriginToDestinationType;
        this.destinationWaypointID = destinationWaypointID;
    }

    public APA() {
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
     * Get the value of bearingOriginToDestinationType
     *
     * @return the value of bearingOriginToDestinationType
     */
    public String getBearingOriginToDestinationType() {
        return bearingOriginToDestinationType;
    }

    /**
     * Set the value of bearingOriginToDestinationType
     *
     * @param bearingOriginToDestinationType new value of
     * bearingOriginToDestinationType
     */
    public void setBearingOriginToDestinationType(String bearingOriginToDestinationType) {
        this.bearingOriginToDestinationType = bearingOriginToDestinationType;
    }

    /**
     * Get the value of bearingOriginToDestination
     *
     * @return the value of bearingOriginToDestination
     */
    public int getBearingOriginToDestination() {
        return bearingOriginToDestination;
    }

    /**
     * Set the value of bearingOriginToDestination
     *
     * @param bearingOriginToDestination new value of bearingOriginToDestination
     */
    public void setBearingOriginToDestination(int bearingOriginToDestination) {
        this.bearingOriginToDestination = bearingOriginToDestination;
    }

    /**
     * Get the value of status3
     *
     * @return the value of status3
     */
    public String getStatus3() {
        return status3;
    }

    /**
     * Set the value of status3
     *
     * @param status3 new value of status3
     */
    public void setStatus3(String status3) {
        this.status3 = status3;
    }

    /**
     * Get the value of status2
     *
     * @return the value of status2
     */
    public String getStatus2() {
        return status2;
    }

    /**
     * Set the value of status2
     *
     * @param status2 new value of status2
     */
    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    /**
     * Get the value of crossTrackUnits
     *
     * @return the value of crossTrackUnits
     */
    public String getCrossTrackUnits() {
        return crossTrackUnits;
    }

    /**
     * Set the value of crossTrackUnits
     *
     * @param crossTrackUnits new value of crossTrackUnits
     */
    public void setCrossTrackUnits(String crossTrackUnits) {
        this.crossTrackUnits = crossTrackUnits;
    }

    /**
     * Get the value of directionToSteer
     *
     * @return the value of directionToSteer
     */
    public String getDirectionToSteer() {
        return directionToSteer;
    }

    /**
     * Set the value of directionToSteer
     *
     * @param directionToSteer new value of directionToSteer
     */
    public void setDirectionToSteer(String directionToSteer) {
        this.directionToSteer = directionToSteer;
    }

    /**
     * Get the value of crossTrackErrorMagnitude
     *
     * @return the value of crossTrackErrorMagnitude
     */
    public float getCrossTrackErrorMagnitude() {
        return crossTrackErrorMagnitude;
    }

    /**
     * Set the value of crossTrackErrorMagnitude
     *
     * @param crossTrackErrorMagnitude new value of crossTrackErrorMagnitude
     */
    public void setCrossTrackErrorMagnitude(float crossTrackErrorMagnitude) {
        this.crossTrackErrorMagnitude = crossTrackErrorMagnitude;
    }

    /**
     * Get the value of status1
     *
     * @return the value of status1
     */
    public String getStatus1() {
        return status1;
    }

    /**
     * Set the value of status1
     *
     * @param status1 new value of status1
     */
    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    /**
     * Get the value of status0
     *
     * @return the value of status0
     */
    public String getStatus0() {
        return status0;
    }

    /**
     * Set the value of status0
     *
     * @param status0 new value of status0
     */
    public void setStatus0(String status0) {
        this.status0 = status0;
    }

    @Override
    public String toString() {
        return "APA{" + "status0=" + status0 + ", status1=" + status1 + ", crossTrackErrorMagnitude=" + crossTrackErrorMagnitude + ", directionToSteer=" + directionToSteer + ", crossTrackUnits=" + crossTrackUnits + ", status2=" + status2 + ", status3=" + status3 + ", bearingOriginToDestination=" + bearingOriginToDestination + ", bearingOriginToDestinationType=" + bearingOriginToDestinationType + ", destinationWaypointID=" + destinationWaypointID + '}';
    }
    
}
