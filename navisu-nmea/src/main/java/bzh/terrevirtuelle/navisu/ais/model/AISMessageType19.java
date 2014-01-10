/******************************************************************************
 * (c) Copyright 2008, IRENav. All rights reserved.
 * Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only
 * Unauthorized reproduction and/or distribution is strictly prohibited.
 * This product is protected under copyright law and trade secret law as an
 * unpublished Work.
 *
 * Modified in 07/2008
 *
 * Original Designers : RAY
 *
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.ais.model;

//import objects.gps.WGS84Location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Extended Class B Equipment Position Report
 * 
 */
@XmlRootElement(name="AISMessageType19")
@XmlAccessorType(XmlAccessType.FIELD)
public class AISMessageType19 extends AISMessage {

    private float sog;
    private float cog;
    private float trueHeading;
    private float latitude,  longitude;

    public AISMessageType19() {
    }

    public float getTrueHeading() {
        return trueHeading;
    }

    public void setTrueHeading(float trueHeading) {
        this.trueHeading = trueHeading;
    }

    /**
     * decodeFrame : decode AIS message of type 19
     *
     */
    @Override
    public void decodeFrame() {
        if (messageAisBinary.BinaryFrame.length() >= 167) {
            MMSI = messageAisBinary.binaryToInt(8, 38);
            sog = (float) (0.1 * messageAisBinary.binaryToInt(46, 56));
            cog = (float) (0.1 * messageAisBinary.binaryToInt(112, 124));
            trueHeading = (float) messageAisBinary.binaryToInt(124, 133);
            longitude = ((float) (0.0001 * messageAisBinary.complementToInt(57, 85))) / 60;
            latitude = ((float) (0.0001 * messageAisBinary.complementToInt(85, 112))) / 60;
        }
    }

    @Override
    public String toString() {
        return new String("(MESSAGEAISTYPE19) ISMM=" + MMSI + ", HEAD=" + trueHeading + ", COG=" + cog + ", LAT=" + latitude + ", LONG=" + longitude);
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

}
/** end AISMessageType18 */
