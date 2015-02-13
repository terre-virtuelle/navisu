package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Water Speed and Heading
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="VHW")
@XmlAccessorType(XmlAccessType.FIELD)
public class VHW
        extends NMEA {

    private float degreesTrue;
    private float degreesMagnetic;
    private float speedInKnots;
    private float speedInKilometers;

    public VHW(String device,
            String sentence,
            float degreesTrue,
            float degreesMagnetic,
            float speedInKnots,
            float speedInKilometers) {
        super(device, sentence);
        this.degreesTrue = degreesTrue;
        this.degreesMagnetic = degreesMagnetic;
        this.speedInKnots = speedInKnots;
        this.speedInKilometers = speedInKilometers;
    }

    public VHW() {
    }

    public float getDegreesTrue() {
        return degreesTrue;
    }

    public void setDegreesTrue(float degreesTrue) {
        this.degreesTrue = degreesTrue;
    }

    public float getDegreesMagnetic() {
        return degreesMagnetic;
    }

    public void setDegreesMagnetic(float degreesMagnetic) {
        this.degreesMagnetic = degreesMagnetic;
    }

    /**
     * Get the value of speedInKilometers
     *
     * @return the value of speedInKilometers
     */
    public float getSpeedInKilometers() {
        return speedInKilometers;
    }

    /**
     * Set the value of speedInKilometers
     *
     * @param speedInKilometers new value of speedInKilometers
     */
    public void setSpeedInKilometers(float speedInKilometers) {
        this.speedInKilometers = speedInKilometers;
    }

    /**
     * Get the value of speedInKnots
     *
     * @return the value of speedInKnots
     */
    public float getSpeedInKnots() {
        return speedInKnots;
    }

    /**
     * Set the value of speedInKnots
     *
     * @param speedInKnots new value of speedInKnots
     */
    public void setSpeedInKnots(float speedInKnots) {
        this.speedInKnots = speedInKnots;
    }

    @Override
    public String toString() {
        return "VHW{" + "degreesTrue=" + degreesTrue + ", degreesMagnetic=" + degreesMagnetic + ", speedInKnots=" + speedInKnots + ", speedInKilometers=" + speedInKilometers + '}';
    }
}
