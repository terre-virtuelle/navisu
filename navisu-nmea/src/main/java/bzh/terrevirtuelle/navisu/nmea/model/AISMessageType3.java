/******************************************************************************
 * (c) Copyright 2007, IRENav. All rights reserved.
 * Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only
 * Unauthorized reproduction and/or distribution is strictly prohibited.
 * This product is protected under copyright law and trade secret law as an
 * unpublished Work.
 *
 * Modified in 05/2007.
 *
 * Original Designers : RAY, STROH, ALESSIO
 * Modified : Serge Morvan Enib 09/2009
 *
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.nmea.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Position Report with ITDMA
 * 
 */
@XmlRootElement(name="ais3")
@XmlAccessorType(XmlAccessType.FIELD)
public class AISMessageType3 extends AISMessageType135 {

    public AISMessageType3() {
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
        return new String("(MESSAGEAISTYPE3) MMSI=" + MMSI 
                + ", STATUS = " + navigationalStatus
                + ", HEAD = " + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", ROT = " + rot);
    }

}
/** end AISMessageType3 */
