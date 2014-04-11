/******************************************************************************
 * Original Designers : RAY, STROH, ALESSIO
 * Modified : Serge Morvan Enib 09/2009
 *
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.domain.nmea.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Position Report with ITDMA
 * Incremental Time Division Multiple Access
 * 
 */
@XmlRootElement(name="ais3")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS3 extends AIS135 {

    public AIS3() {
    }

    /**
     * decodeFrame : decode AIS message of type 3 (idem type 1 with ITDMA)
     *
     */
    @Override
    public void decodeFrame() {

        if (messageAisBinary.length() == 167) {
            MMSI = binaryToInt(messageAisBinary,8, 38);
            navigationalStatus = binaryToInt(messageAisBinary,38, 42);
            sog = (float) (0.1 * binaryToInt(messageAisBinary,50, 60));
            cog = (float) (0.1 * binaryToInt(messageAisBinary,116, 128));
            heading = (float) binaryToInt(messageAisBinary,128, 137);
            rot = (float) complementToInt(messageAisBinary,42, 50);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary,61, 89))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary,89, 116))) / 60;
        }
    }

    @Override
    public String toString() {
        return new String("AIS3{MMSI=" + MMSI 
                + ", STATUS = " + navigationalStatus
                + ", HEAD = " + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", ROT = " + rot + "}");
    }

}
/** end AISMessageType3 */
