/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Speed – Measured Parallel to Wind
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="VPW")
@XmlAccessorType(XmlAccessType.FIELD)
public class VPW
        extends NMEA {

    private float speed;

    public VPW(String device,
            String sentence,
            float speed) {
        super(device, sentence);
        this.speed = speed;
    }

    public VPW() {
    }

    /**
     * Get the value of speed
     *
     * @return the value of speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Set the value of speed
     *
     * @param speed new value of speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "VPW{" + "speed=" + speed + '}';
    }
    private static final Logger LOG = Logger.getLogger(VPW.class.getName());

    public static Logger getLOG() {
        return LOG;
    }

}
