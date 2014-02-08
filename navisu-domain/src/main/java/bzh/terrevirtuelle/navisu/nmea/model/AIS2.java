/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge
 */
@XmlRootElement(name = "ais2")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS2
        extends AIS135 {

    public AIS2() {
    }

    public AIS2(float rot, float cog, float sog, int navigationalStatus, float heading, float latitude, float longitude, int second, int MMSI, String device) {
        super(rot, cog, sog, navigationalStatus, heading, latitude, longitude, second, MMSI, device);
    }

    /**
     * decodeFrame : decode AIS message of type 1
     *
     */
    @Override
    public void decodeFrame() {
        if (messageAisBinary.length() == 167) {
            MMSI = binaryToInt(messageAisBinary, 8, 38);
            navigationalStatus = binaryToInt(messageAisBinary, 38, 42);
            sog = (float) (0.1 * binaryToInt(messageAisBinary, 50, 60));
            cog = (float) (0.1 * binaryToInt(messageAisBinary, 116, 128));
            heading = (float) binaryToInt(messageAisBinary, 128, 137);
            rot = (float) complementToInt(messageAisBinary, 42, 50);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary, 61, 89))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 89, 116))) / 60;
        }
    }

    @Override
    public String toString() {
        return "AIS1{" + "Device = " + device
                + "MMSI = " + MMSI
                + ", STATUS = " + navigationalStatus
                + ", HEAD =" + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", ROT = " + rot + '}';
    }

}
