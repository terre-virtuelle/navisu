/******************************************************************************
 * Original Designers : RAY
 *
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Standard Class B Position Report
 * 
 */
@XmlRootElement(name="ais18")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS18 extends AISMessage {

    private float sog;
    private float cog;
    private float trueHeading;
    private float latitude, longitude;

    private int second;

    /**
     * Get the value of second
     *
     * @return the value of second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Set the value of second
     *
     * @param second new value of second
     */
    public void setSecond(int second) {
        this.second = second;
    }

    public AIS18() {
    }

    public AIS18(int MMSI, String device, 
            float sog, float cog, float trueHeading, 
            float latitude, float longitude, int second) {
        super(MMSI, device);
        this.sog = sog;
        this.cog = cog;
        this.trueHeading = trueHeading;
        this.latitude = latitude;
        this.longitude = longitude;
        this.second = second;
    }

    /**
     * decodeFrame : decode AIS message of type 18
     *
     */
    @Override
    public void decodeFrame() {

        // TODO : v�rifier le compte de bits

        if (messageAisBinary.length() >= 167) {

            MMSI = binaryToInt(messageAisBinary,8, 38);
            sog = (float) (0.1 * binaryToInt(messageAisBinary,46, 56));
            cog = (float) (0.1 * binaryToInt(messageAisBinary,112, 124));
            trueHeading = (float)binaryToInt(messageAisBinary,124, 133);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary,57, 85))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary,85, 112))) / 60;
        }
    }


    @Override
    public String toString() {
        return new String("AIS18{MMSI = " + MMSI + ", HEAD=" + trueHeading + ", COG=" + cog + ", LAT=" + latitude + ", LONG=" + longitude + "}");
    }

    /**
     *
     * @return
     */
    public float getCog() {
        return cog;
    }

    /**
     *
     * @param cog
     */
    public void setCog(float cog) {
        this.cog = cog;
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

    /**
     *
     * @return
     */
    public float getTrueHeading() {
        return trueHeading;
    }

    /**
     *
     * @param trueHeading
     */
    public void setTrueHeading(float trueHeading) {
        this.trueHeading = trueHeading;
    }

}
/** end AISMessageType18 */
