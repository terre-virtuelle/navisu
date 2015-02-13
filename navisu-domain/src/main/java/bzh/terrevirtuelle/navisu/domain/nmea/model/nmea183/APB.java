/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Autopilot format B
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="APB")
@XmlAccessorType(XmlAccessType.FIELD)
public class APB
        extends APA {

    private int bearingPresentPositionToDestination;
    private String bearingPresentPositionToDestinationType;
    private int headingToSteerToDestination;
    private String headingToSteerToDestinationType;

    public APB(String device, String sentence, 
            String status0, String status1, 
            float crossTrackErrorMagnitude, String directionToSteer, String crossTrackUnits, 
            String status2, String status3, 
            int bearingOriginToDestination, String bearingOriginToDestinationType, 
            String destinationWaypointID,
            int bearingPresentPositionToDestination, String bearingPresentPositionToDestinationType, 
            int headingToSteerToDestination, String headingToSteerToDestinationType) {
        super(device, sentence, status0, status1, crossTrackErrorMagnitude, directionToSteer, crossTrackUnits, status2, status3, bearingOriginToDestination, bearingOriginToDestinationType, destinationWaypointID);
        this.bearingPresentPositionToDestination = bearingPresentPositionToDestination;
        this.bearingPresentPositionToDestinationType = bearingPresentPositionToDestinationType;
        this.headingToSteerToDestination = headingToSteerToDestination;
        this.headingToSteerToDestinationType = headingToSteerToDestinationType;
    }

    public APB() {
    }

    

    /**
     * Get the value of headingToSteerToDestinationType
     *
     * @return the value of headingToSteerToDestinationType
     */
    public String getHeadingToSteerToDestinationType() {
        return headingToSteerToDestinationType;
    }

    /**
     * Set the value of headingToSteerToDestinationType
     *
     * @param headingToSteerToDestinationType new value of
     * headingToSteerToDestinationType
     */
    public void setHeadingToSteerToDestinationType(String headingToSteerToDestinationType) {
        this.headingToSteerToDestinationType = headingToSteerToDestinationType;
    }

    /**
     * Get the value of headingToSteerToDestination
     *
     * @return the value of headingToSteerToDestination
     */
    public int getHeadingToSteerToDestination() {
        return headingToSteerToDestination;
    }

    /**
     * Set the value of headingToSteerToDestination
     *
     * @param headingToSteerToDestination new value of
     * headingToSteerToDestination
     */
    public void setHeadingToSteerToDestination(int headingToSteerToDestination) {
        this.headingToSteerToDestination = headingToSteerToDestination;
    }

    /**
     * Get the value of bearingPresentPositionToDestinationType
     *
     * @return the value of bearingPresentPositionToDestinationType
     */
    public String getBearingPresentPositionToDestinationType() {
        return bearingPresentPositionToDestinationType;
    }

    /**
     * Set the value of bearingPresentPositionToDestinationType
     *
     * @param bearingPresentPositionToDestinationType new value of
     * bearingPresentPositionToDestinationType
     */
    public void setBearingPresentPositionToDestinationType(String bearingPresentPositionToDestinationType) {
        this.bearingPresentPositionToDestinationType = bearingPresentPositionToDestinationType;
    }

    /**
     * Get the value of bearingPresentPositionToDestination
     *
     * @return the value of bearingPresentPositionToDestination
     */
    public int getBearingPresentPositionToDestination() {
        return bearingPresentPositionToDestination;
    }

    /**
     * Set the value of bearingPresentPositionToDestination
     *
     * @param bearingPresentPositionToDestination new value of
     * bearingPresentPositionToDestination
     */
    public void setBearingPresentPositionToDestination(int bearingPresentPositionToDestination) {
        this.bearingPresentPositionToDestination = bearingPresentPositionToDestination;
    }

    @Override
    public String toString() {
        
        return "APB{" 
                + super.toString()
                + "bearingPresentPositionToDestination=" + bearingPresentPositionToDestination 
                + ", bearingPresentPositionToDestinationType=" + bearingPresentPositionToDestinationType 
                + ", headingToSteerToDestination=" + headingToSteerToDestination 
                + ", headingToSteerToDestinationType=" + headingToSteerToDestinationType + '}';
    }
    
}
