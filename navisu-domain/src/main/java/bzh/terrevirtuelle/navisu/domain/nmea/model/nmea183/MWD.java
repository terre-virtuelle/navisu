package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  Wind Direction and Speed, with respect to north. 
 * @author Serge Morvan
 */
@XmlRootElement(name="MWD")
@XmlAccessorType(XmlAccessType.FIELD)
public class MWD
        extends NMEA {

    private float windDirectionTrue;
    private float windDirectionMagnetic;
    private float windSpeed;

    public MWD(String device, 
            String sentence,
            float windDirectionTrue, 
            float windDirectionMagnetic, 
            float windSpeed) {
        super(device, sentence);
        this.windDirectionTrue = windDirectionTrue;
        this.windDirectionMagnetic = windDirectionMagnetic;
        this.windSpeed = windSpeed;
    }

    public MWD() {
    }

    /**
     * Get the value of windSpeed
     *
     * @return the value of windSpeed
     */
    public float getWindSpeed() {
        return windSpeed;
    }

    /**
     * Set the value of windSpeed
     *
     * @param windSpeed new value of windSpeed
     */
    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Get the value of windDirectionMagnetic
     *
     * @return the value of windDirectionMagnetic
     */
    public float getWindDirectionMagnetic() {
        return windDirectionMagnetic;
    }

    /**
     * Set the value of windDirectionMagnetic
     *
     * @param windDirectionMagnetic new value of windDirectionMagnetic
     */
    public void setWindDirectionMagnetic(float windDirectionMagnetic) {
        this.windDirectionMagnetic = windDirectionMagnetic;
    }

    /**
     * Get the value of windDirectionTrue
     *
     * @return the value of windDirectionTrue
     */
    public float getWindDirectionTrue() {
        return windDirectionTrue;
    }

    /**
     * Set the value of windDirectionTrue
     *
     * @param windDirectionTrue new value of windDirectionTrue
     */
    public void setWindDirectionTrue(float windDirectionTrue) {
        this.windDirectionTrue = windDirectionTrue;
    }

    @Override
    public String toString() {
        return "MWD{" + "windDirectionTrue=" + windDirectionTrue + ", windDirectionMagnetic=" + windDirectionMagnetic + ", windSpeed=" + windSpeed + '}';
    }

   
}
