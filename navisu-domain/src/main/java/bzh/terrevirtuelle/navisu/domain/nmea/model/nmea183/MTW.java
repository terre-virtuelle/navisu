package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Water temperature
 */
@XmlRootElement(name="MTW")
@XmlAccessorType(XmlAccessType.FIELD)
public class MTW
        extends NMEA {

    private float degrees;
    private String unit;

    /**
     *
     * @param device
     * @param sentence
     * @param degrees
     * @param unit
     */
    public MTW(
            String device,
            String sentence,
            float degrees,
            String unit) {
        super(device, sentence);
        this.degrees = degrees;
        this.unit = unit;
    }

    public MTW() {
    }

    /**
     *
     * @return
     */
    public float getDegrees() {
        return this.degrees;
    }

    /**
     *
     * @param value
     */
    public void setDegrees(float value) {
        this.degrees = value;
    }

    /**
     *
     * @return
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     *
     * @param value
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    @Override
    public String toString() {
        return "MTW{" + "degrees=" + degrees + ", unit=" + unit + '}';
    }
    
}
