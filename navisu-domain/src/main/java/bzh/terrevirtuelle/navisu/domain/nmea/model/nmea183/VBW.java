package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dual Ground/Water Speed 
 * @author Serge Morvan
 */
@XmlRootElement(name="VBW")
@XmlAccessorType(XmlAccessType.FIELD)
public class VBW
        extends NMEA {
   
    private float longitudinalWaterSpeed;
    private float transverseWaterSpeed;
    private String wStatus;
    private float longitudinalGroundSpeed;
    private float transverseGroundSpeed;
    private String gStatus;

    public VBW(String device, 
            String sentence,
            float longitudinalWaterSpeed, 
            float transverseWaterSpeed, 
            String wStatus, 
            float longitudinalGroundSpeed, 
            float transverseGroundSpeed, 
            String gStatus) {
        super(device, sentence);
        this.longitudinalWaterSpeed = longitudinalWaterSpeed;
        this.transverseWaterSpeed = transverseWaterSpeed;
        this.wStatus = wStatus;
        this.longitudinalGroundSpeed = longitudinalGroundSpeed;
        this.transverseGroundSpeed = transverseGroundSpeed;
        this.gStatus = gStatus;
    }

    public VBW() {
    }

    /**
     * Get the value of gStatus
     *
     * @return the value of gStatus
     */
    public String getGStatus() {
        return gStatus;
    }

    /**
     * Set the value of gStatus
     *
     * @param gStatus new value of gStatus
     */
    public void setGStatus(String gStatus) {
        this.gStatus = gStatus;
    }

    /**
     * Get the value of transverseGroundSpeed
     *
     * @return the value of transverseGroundSpeed
     */
    public float getTransverseGroundSpeed() {
        return transverseGroundSpeed;
    }

    /**
     * Set the value of transverseGroundSpeed
     *
     * @param transverseGroundSpeed new value of transverseGroundSpeed
     */
    public void setTransverseGroundSpeed(float transverseGroundSpeed) {
        this.transverseGroundSpeed = transverseGroundSpeed;
    }

    /**
     * Get the value of longitudinalGroundSpeed
     *
     * @return the value of longitudinalGroundSpeed
     */
    public float getLongitudinalGroundSpeed() {
        return longitudinalGroundSpeed;
    }

    /**
     * Set the value of longitudinalGroundSpeed
     *
     * @param longitudinalGroundSpeed new value of longitudinalGroundSpeed
     */
    public void setLongitudinalGroundSpeed(float longitudinalGroundSpeed) {
        this.longitudinalGroundSpeed = longitudinalGroundSpeed;
    }

    /**
     * Get the value of wStatus
     *
     * @return the value of wStatus
     */
    public String getWStatus() {
        return wStatus;
    }

    /**
     * Set the value of wStatus
     *
     * @param wStatus new value of wStatus
     */
    public void setWStatus(String wStatus) {
        this.wStatus = wStatus;
    }

    /**
     * Get the value of transverseWaterSpeed
     *
     * @return the value of transverseWaterSpeed
     */
    public float getTransverseWaterSpeed() {
        return transverseWaterSpeed;
    }

    /**
     * Set the value of transverseWaterSpeed
     *
     * @param transverseWaterSpeed new value of transverseWaterSpeed
     */
    public void setTransverseWaterSpeed(float transverseWaterSpeed) {
        this.transverseWaterSpeed = transverseWaterSpeed;
    }

    /**
     * Get the value of longitudinalWaterSpeed
     *
     * @return the value of longitudinalWaterSpeed
     */
    public float getLongitudinalWaterSpeed() {
        return longitudinalWaterSpeed;
    }

    /**
     * Set the value of longitudinalWaterSpeed
     *
     * @param longitudinalWaterSpeed new value of longitudinalWaterSpeed
     */
    public void setLongitudinalWaterSpeed(float longitudinalWaterSpeed) {
        this.longitudinalWaterSpeed = longitudinalWaterSpeed;
    }

    @Override
    public String toString() {
        return "VBW{" + "longitudinalWaterSpeed=" + longitudinalWaterSpeed + ", transverseWaterSpeed=" + transverseWaterSpeed + ", wStatus=" + wStatus + ", longitudinalGroundSpeed=" + longitudinalGroundSpeed + ", transverseGroundSpeed=" + transverseGroundSpeed + ", gStatus=" + gStatus + '}';
    }
    
}
