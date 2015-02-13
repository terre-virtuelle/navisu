package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wind Speed and Angle 
 * @author  Serge morvan
 */
@XmlRootElement(name="MWV")
@XmlAccessorType(XmlAccessType.FIELD)
public class MWV
        extends NMEA {

    /**
     *
     * @param device
     * @param sentence
     * @param windAngle
     * @param reference
     * @param windSpeed
     * @param windSpeedUnit
     * @param status
     */
    public MWV(
            String device,
            String sentence,
            float windAngle,
            String reference,
            float windSpeed,
            String windSpeedUnit,
            String status) {
        super(device, sentence);
        this.windAngle = windAngle;
        this.reference = reference;
        this.windSpeed = windSpeed;
        this.windSpeedUnit = windSpeedUnit;
        this.status = status;
    }

    /**
     *
     */
    public MWV() {
    }
    private float windAngle;

    /**
     *
     * @return
     */
    public float getWindAngle() {
        return this.windAngle;
    }

    /**
     *
     * @param value
     */
    public void setWindAngle(float value) {
        this.windAngle = value;
    }
    private String reference;

    /**
     *
     * @return
     */
    public String getReference() {
        return this.reference;
    }

    /**
     *
     * @param value
     */
    public void setReference(String value) {
        this.reference = value;
    }
    private float windSpeed;

    /**
     *
     * @return
     */
    public float getWindSpeed() {
        return this.windSpeed;
    }

    /**
     *
     * @param value
     */
    public void setWindSpeed(float value) {
        this.windSpeed = value;
    }
    private String windSpeedUnit;

    /**
     *
     * @return
     */
    public String getWindSpeedUnit() {
        return this.windSpeedUnit;
    }

    /**
     *
     * @param value
     */
    public void setWindSpeedUnit(String value) {
        this.windSpeedUnit = value;
    }
    private String status;

    /**
     *
     * @return
     */
    public String getStatus() {
        return this.status;
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        this.status = value;
    }

    @Override
    public String toString() {
        return "MWV{" + "windAngle=" + windAngle + ", reference=" + reference + ", windSpeed=" + windSpeed + ", windSpeedUnit=" + windSpeedUnit + ", status=" + status + '}';
    }
    
}
