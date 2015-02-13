package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Depth Below Surface value expressed in feet, metres and fathoms.
 */
@XmlRootElement(name="DBS")
@XmlAccessorType(XmlAccessType.FIELD)
public class DBS
        extends NMEA {

    private float depthInFeet;
    private float depthInMeters;
    private float depthInFathoms;

    public DBS(String device, 
            String sentence,
            float depthInFeet, 
            float depthInMeters, 
            float depthInFathoms ) {
        super(device, sentence);
        this.depthInFeet = depthInFeet;
        this.depthInMeters = depthInMeters;
        this.depthInFathoms = depthInFathoms;
    }

    public DBS() {
    }

    /**
     * Get the value of depthInFathoms
     *
     * @return the value of depthInFathoms
     */
    public float getDepthInFathoms() {
        return depthInFathoms;
    }

    /**
     * Set the value of depthInFathoms
     *
     * @param depthInFathoms new value of depthInFathoms
     */
    public void setDepthInFathoms(float depthInFathoms) {
        this.depthInFathoms = depthInFathoms;
    }

    /**
     * Get the value of depthInMeters
     *
     * @return the value of depthInMeters
     */
    public float getDepthInMeters() {
        return depthInMeters;
    }

    /**
     * Set the value of depthInMeters
     *
     * @param depthInMeters new value of depthInMeters
     */
    public void setDepthInMeters(float depthInMeters) {
        this.depthInMeters = depthInMeters;
    }

    /**
     * Get the value of depthInFeet
     *
     * @return the value of depthInFeet
     */
    public float getDepthInFeet() {
        return depthInFeet;
    }

    /**
     * Set the value of depthInFeet
     *
     * @param depthInFeet new value of depthInFeet
     */
    public void setDepthInFeet(float depthInFeet) {
        this.depthInFeet = depthInFeet;
    }

    @Override
    public String toString() {
        return "DBS{" + "depthInFeet=" + depthInFeet + ", depthInMeters=" + depthInMeters + ", depthInFathoms=" + depthInFathoms + '}';
    }
    
}
