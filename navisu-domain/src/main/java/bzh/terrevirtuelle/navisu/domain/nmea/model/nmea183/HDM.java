package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * HDM Heading – Magnetic 
 * @author Serge Morvan
 */
@XmlRootElement(name="HDM")
@XmlAccessorType(XmlAccessType.FIELD)
public class HDM
        extends NMEA {

    private float heading;

    /**
     *
     * @param device
     * @param sentence
     * @param heading
     */
    public HDM(String device,
            String sentence,
            float heading) {
        super(device, sentence);
        this.heading = heading;
    }

    /**
     * Heading True
     */
    public HDM() {
    }

    /**
     * Track
     *
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

    @Override
    public String toString() {
        return "HDM{" + "heading=" + heading + '}';
    }
    
}
