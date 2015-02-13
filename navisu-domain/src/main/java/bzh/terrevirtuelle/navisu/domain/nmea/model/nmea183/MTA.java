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
 * Air Temperature Obsolete 2009
 *
 * @author Serge
 */
@XmlRootElement(name="MTA")
@XmlAccessorType(XmlAccessType.FIELD)
public class MTA
        extends NMEA {

    private float degrees;
    private String unit;

    public MTA(String device,
            String sentence,
            float degrees,
            String unit) {
        super(device, sentence);
        this.degrees = degrees;
        this.unit = unit;
    }

    public MTA() {
    }

    /**
     * Get the value of unit
     *
     * @return the value of unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Set the value of unit
     *
     * @param unit new value of unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Get the value of degrees
     *
     * @return the value of degrees
     */
    public float getDegrees() {
        return degrees;
    }

    /**
     * Set the value of degrees
     *
     * @param degrees new value of degrees
     */
    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return "MTA{" + "degrees=" + degrees + ", unit=" + unit + '}';
    }

}
