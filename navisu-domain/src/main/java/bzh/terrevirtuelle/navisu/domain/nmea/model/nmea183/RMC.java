package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Recommended minimum navigation information.
 *
 * @author Serge MORVAN
 */
@XmlRootElement(name = "RMC")
@XmlAccessorType(XmlAccessType.FIELD)
public class RMC
        extends NMEA {

    private String eastOrWestVariation;
    private Calendar date;
    private String status;
    private float latitude;
    private float longitude;
    private float variation;
    private float track;
    private float sog;

    /**
     *
     * @param device
     * @param sentence
     * @param date
     * @param status
     * @param latitude
     * @param longitude
     * @param sog
     * @param track
     * @param variation
     * @param eastOrWestVariation
     */
    public RMC(
            String device,
            String sentence,
            Calendar date,
            String status,
            float latitude,
            float longitude,
            float sog,
            float track,
            float variation,
            String eastOrWestVariation) {
        super(device, sentence);
        this.date = date;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sog = sog;
        this.track = track;
        this.date = date;
        this.variation = variation;
        this.eastOrWestVariation = eastOrWestVariation;
    }

    /**
     *
     */
    public RMC() {
    }

    /**
     *
     * @return
     */
    public String getEastOrWestVariation() {
        return eastOrWestVariation;
    }

    /**
     *
     * @param eastOrWestVariation
     */
    public void setEastOrWestVariation(String eastOrWestVariation) {
        this.eastOrWestVariation = eastOrWestVariation;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss");
        return formater.format(date.getTime());
    }

    public Calendar getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public float getVariation() {
        return variation;
    }

    /**
     *
     * @param variation
     */
    public void setVariation(float variation) {
        this.variation = variation;
    }

    /**
     *
     * @return
     */
    public float getTrack() {
        return track;
    }

    /**
     *
     * @param track
     */
    public void setTrack(float track) {
        this.track = track;
    }

    /**
     *
     * @return
     */
    public float getCog() {
        return track;
    }

    /**
     *
     * @param track
     */
    public void setCog(float track) {
        this.track = track;
    }

    /**
     *
     * @return
     */
    public float getSog() {
        return sog;
    }

    /**
     *
     * @param sog
     */
    public void setSog(float sog) {
        this.sog = sog;
    }

    @Override
    public boolean equals(Object obj) {
        // Vérification de l'égalité des références
        if (obj == this) {
            return true;
        }
        if (obj instanceof RMC) {
            RMC other = (RMC) obj;
            return !(this.latitude != other.latitude || this.longitude != other.longitude);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Float.floatToIntBits(this.latitude);
        hash = 79 * hash + Float.floatToIntBits(this.longitude);
        return hash;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/YY", Locale.FRENCH);
        simpleDateFormat.setLenient(false);
        return "RMC{" + "eastOrWestVariation=" + eastOrWestVariation
                + ", date=" + simpleDateFormat.format(date.getTime())
                + ", status=" + status + ", latitude=" + latitude + ", longitude=" + longitude + ", variation=" + variation + ", track=" + track + ", sog=" + sog + '}';
    }
}
