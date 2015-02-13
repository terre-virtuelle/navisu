package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * GLL Geographic position, Latitude and Longitude 
 * @author Serge Morvan
 */
@XmlRootElement(name="GLL")
@XmlAccessorType(XmlAccessType.FIELD)
public class GLL
        extends NMEA {

    private float latitude;
    private float longitude;
    private String status;
    private Calendar utc;

    public GLL(String device, 
            String sentence,
            float latitude, 
            float longitude, 
            Calendar utc, 
            String status) {
        super(device, sentence);
        this.latitude = latitude;
        this.longitude = longitude;
        this.utc = utc;
        this.status = status;
    }

    /**
     *
     */
    public GLL() {
    }

    public Calendar getUtc() {
        return utc;
    }

    /**
     * Get the value of utc
     *
     * @return the value of utc
     */
    public String getTime() {
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss");
        return formater.format(utc.getTime());
    }

    /**
     * Set the value of utc
     *
     * @param utc new value of utc
     */
    public void setDate(Calendar date) {
        this.utc = date;
    }

    /**
     *
     * @return
     */
    public float getLatitude() {
        return this.latitude;
    }

    /**
     *
     * @param value
     */
    public void setLatitude(float value) {
        this.latitude = value;
    }

    /**
     *
     * @return
     */
    public float getLongitude() {
        return this.longitude;
    }

    /**
     *
     * @param value
     */
    public void setLongitude(float value) {
        this.longitude = value;
    }

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
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "GLL{" + "latitude=" + latitude + ", longitude=" + longitude 
                + ", status=" + status 
                + ", UTC=" + formater.format(utc.getTime()) + '}';
    }
    
}
