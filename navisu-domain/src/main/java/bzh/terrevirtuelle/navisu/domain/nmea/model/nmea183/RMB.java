package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Recommended Minimum Navigation Information
 * @author Serge
 */
@XmlRootElement(name="RMB")
@XmlAccessorType(XmlAccessType.FIELD)
public class RMB
        extends NMEA {

    private String status;
    private float crossTrackError;
    private String directionToSteer;
    private String toWaypointId;
    private String fromWaypointId;
    private float destinationWaypointLatitude;
    private float destinationWaypointLongitude;
    private float rangeToDestination;
    private float bearingToDestination;
    private float destinationClosingVelocity;
    private String arrivalStatus;
    private String faaModeIndicator;

    /**
     *
     * @param device
     * @param sentence
     * @param status
     * @param crossTrackError
     * @param directionToSteer
     * @param toWaypointId
     * @param fromWaypointId
     * @param destinationWaypointLatitude
     * @param destinationWaypointLongitude
     * @param rangeToDestination
     * @param bearingToDestination
     * @param destinationClosingVelocity
     * @param arrivalStatus
     */
    public RMB(
            String device,
            String sentence,
            String status,
            float crossTrackError,
            String directionToSteer,
            String fromWaypointId,
            String toWaypointId,
            float destinationWaypointLatitude,
            float destinationWaypointLongitude,
            float rangeToDestination,
            float bearingToDestination,
            float destinationClosingVelocity,
            String arrivalStatus) {
        super(device, sentence);
        this.status = status;
        this.crossTrackError = crossTrackError;
        this.directionToSteer = directionToSteer;
        this.toWaypointId = toWaypointId;
        this.fromWaypointId = fromWaypointId;
        this.destinationWaypointLatitude = destinationWaypointLatitude;
        this.destinationWaypointLongitude = destinationWaypointLongitude;
        this.rangeToDestination = rangeToDestination;
        this.bearingToDestination = bearingToDestination;
        this.destinationClosingVelocity = destinationClosingVelocity;
        this.arrivalStatus = arrivalStatus;
    }

    /**
     *
     */
    public RMB() {
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return this.status;
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     *
     * @return
     */
    public float getCrossTrackError() {
        return this.crossTrackError;
    }

    /**
     *
     * @param value
     */
    public void setCrossTrackError(float value) {
        this.crossTrackError = value;
    }

    /**
     *
     * @return
     */
    public String getDirectionToSteer() {
        return this.directionToSteer;
    }

    /**
     *
     * @param value
     */
    public void setDirectionToSteer(String value) {
        this.directionToSteer = value;
    }

    /**
     *
     * @return
     */
    public String getToWaypointId() {
        return this.toWaypointId;
    }

    /**
     *
     * @param value
     */
    public void setToWaypointId(String value) {
        this.toWaypointId = value;
    }

    /**
     *
     * @return
     */
    public String getFromWaypointId() {
        return this.fromWaypointId;
    }

    /**
     *
     * @param value
     */
    public void setFromWaypointId(String value) {
        this.fromWaypointId = value;
    }

    /**
     *
     * @return
     */
    public float getDestinationWaypointLatitude() {
        return this.destinationWaypointLatitude;
    }

    /**
     *
     * @param value
     */
    public void setDestinationWaypointLatitude(float value) {
        this.destinationWaypointLatitude = value;
    }

    /**
     *
     * @return
     */
    public float getDestinationWaypointLongitude() {
        return this.destinationWaypointLongitude;
    }

    /**
     *
     * @param value
     */
    public void setDestinationWaypointLongitude(float value) {
        this.destinationWaypointLongitude = value;
    }

    /**
     *
     * @return
     */
    public float getRangeToDestination() {
        return this.rangeToDestination;
    }

    /**
     *
     * @param value
     */
    public void setRangeToDestination(float value) {
        this.rangeToDestination = value;
    }

    /**
     *
     * @return
     */
    public float getBearingToDestination() {
        return this.bearingToDestination;
    }

    /**
     *
     * @param value
     */
    public void setBearingToDestination(float value) {
        this.bearingToDestination = value;
    }

    /**
     *
     * @return
     */
    public float getDestinationClosingVelocity() {
        return this.destinationClosingVelocity;
    }

    /**
     *
     * @param value
     */
    public void setDestinationClosingVelocity(float value) {
        this.destinationClosingVelocity = value;
    }

    /**
     *
     * @return
     */
    public String getArrivalStatus() {
        return this.arrivalStatus;
    }

    /**
     *
     * @param value
     */
    public void setArrivalStatus(String value) {
        this.arrivalStatus = value;
    }

    /**
     *
     * @return
     */
    public String getFaaModeIndicator() {
        return this.faaModeIndicator;
    }

    /**
     *
     * @param value
     */
    public void setFaaModeIndicator(String value) {
        this.faaModeIndicator = value;
    }

    @Override
    public String toString() {
        return "RMB{" + "status=" + status + ", crossTrackError=" + crossTrackError + ", directionToSteer=" + directionToSteer + ", toWaypointId=" + toWaypointId + ", fromWaypointId=" + fromWaypointId + ", destinationWaypointLatitude=" + destinationWaypointLatitude + ", destinationWaypointLongitude=" + destinationWaypointLongitude + ", rangeToDestination=" + rangeToDestination + ", bearingToDestination=" + bearingToDestination + ", destinationClosingVelocity=" + destinationClosingVelocity + ", arrivalStatus=" + arrivalStatus + ", faaModeIndicator=" + faaModeIndicator + '}';
    }
    
}
