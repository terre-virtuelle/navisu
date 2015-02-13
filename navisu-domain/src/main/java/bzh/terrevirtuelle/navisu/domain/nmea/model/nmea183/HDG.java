package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * HCHDG    Magnetic heading, deviation, variation
 * @author Serge morvan
 */
@XmlRootElement(name="HDG")
@XmlAccessorType(XmlAccessType.FIELD)
public class HDG
        extends NMEA {

    private float heading;
    private float magneticDeviation;
    private float magneticVariation;

    /**
     *
     * @param device
     * @param sentence
     * @param heading
     * @param magneticDeviation
     * @param magneticVariation
     */
    public HDG(String device,
            String sentence,
            float heading, 
            float magneticDeviation, 
            float magneticVariation) {
        super(device, sentence);
        this.heading = heading;
        this.magneticDeviation = magneticDeviation;
        this.magneticVariation = magneticVariation;
    }

    /**
     * Heading True
     */
    public HDG() {
    }

    /**
     * Track
     * @return 
     */
    public float getHeading() {
        return this.heading;
    }

    /**
     *
     * @param value
     */
    public void setHeading(float value) {
        this.heading = value;
    }

    /**
     * Magnetic Deviation, degrees
     * @return 
     */
    public float getMagneticDeviation() {
        return this.magneticDeviation;
    }

    /**
     *
     * @param value
     */
    public void setMagneticDeviation(float value) {
        this.magneticDeviation = value;
    }

    

    /**
     * Magnetic Variation, degrees
     * @return 
     */
    public float getMagneticVariation() {
        return this.magneticVariation;
    }

    /**
     *
     * @param value
     */
    public void setMagneticVariation(float value) {
        this.magneticVariation = value;
    }

    @Override
    public String toString() {
        return "HDG{" + "heading=" + heading + ", magneticDeviation=" + magneticDeviation + ", magneticVariation=" + magneticVariation + '}';
    }

}
