/**
 * ****************************************************************************
 * (c) Copyright 2007, IRENav. All rights reserved. Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only Unauthorized reproduction and/or
 * distribution is strictly prohibited. This product is protected under
 * copyright law and trade secret law as an unpublished Work.
 *
 * Modified in 05/2007.
 *
 * Original Designers : RAY
 *
 *****************************************************************************
 */
package bzh.terrevirtuelle.navisu.nmea.model;

//import objects.gps.WGS84Location;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Standard SAR Aircraft position report
 *
 */
@XmlRootElement(name = "ais9")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS9 extends AISMessage {

    private float cog;
    private int altitude; // TODO: check conversion to float
    private float speed;
    private float latitude, longitude;

    public AIS9() {
    }

    /**
     * decodeFrame : decode AIS message of type 9
     *
     */
    @Override
    public void decodeFrame() {
        if (messageAisBinary.length() == 167) {

            MMSI = binaryToInt(messageAisBinary, 8, 38);
            altitude = binaryToInt(messageAisBinary, 38, 50);
            speed = (float) (0.1 * binaryToInt(messageAisBinary, 50, 60));
            cog = (float) (0.1 * binaryToInt(messageAisBinary, 116, 128));
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary, 61, 89))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 89, 116))) / 60;
        }
    }

    /**
     * displayFrame : print AIS message of type 9
     *
     * @return 
     */
    @Override
    public String toString() {
        return ("AIS9{ISMM=" + MMSI + ", ALT=" + altitude + ", COG=" + cog + ", SPEED=" + speed + ", LAT=" + latitude + ", LONG=" + longitude + "}");
    }

    /**
     *
     * @return
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     *
     * @param altitude
     */
    public void setAltitude(int altitude) {
        this.altitude = altitude;
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
    public float getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
/**
 * end AISMessageType9
 */
