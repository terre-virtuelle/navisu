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
 * Cross-Track Error – Measured     
 * @author Serge Morvan
 */
@XmlRootElement(name="XTE")
@XmlAccessorType(XmlAccessType.FIELD)
public class XTE
        extends NMEA {
    
    private String generalWarning;
    private String status;
    private float crossTrackError;
    private String directionToSteer;

    public XTE(String device, 
            String sentence,
            String generalWarning, 
            String status, 
            float crossTrackError, 
            String directionToSteer) {
        super(device, sentence);
        this.generalWarning = generalWarning;
        this.status = status;
        this.crossTrackError = crossTrackError;
        this.directionToSteer = directionToSteer;
    }

    public XTE() {
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
     * Get the value of crossTrackError
     *
     * @return the value of crossTrackError
     */
    public float getCrossTrackError() {
        return crossTrackError;
    }

    /**
     * Set the value of crossTrackError
     *
     * @param crossTrackError new value of crossTrackError
     */
    public void setCrossTrackError(float crossTrackError) {
        this.crossTrackError = crossTrackError;
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the value of generalWarning
     *
     * @return the value of generalWarning
     */
    public String getGeneralWarning() {
        return generalWarning;
    }

    /**
     * Set the value of generalWarning
     *
     * @param generalWarning new value of generalWarning
     */
    public void setGeneralWarning(String generalWarning) {
        this.generalWarning = generalWarning;
    }

    @Override
    public String toString() {
        return "XTE{" + "generalWarning=" + generalWarning + ", status=" + status + ", crossTrackError=" + crossTrackError + ", directionToSteer=" + directionToSteer + '}';
    }

}
