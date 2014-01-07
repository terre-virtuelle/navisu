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
package bzh.terrevirtuelle.navisu.ais.model;

/**
 * Position Report with SOTDMA
 * 
 */
public class AISMessageType1 extends AISMessageType135 {

    
    /**
     * decodeFrame : decode AIS message of type 1
     *
     */
    @Override
    public void decodeFrame() {
        if (messageAisBinary.BinaryFrame.length() == 167) {
            MMSI = messageAisBinary.binaryToInt(8, 38);
            navigationalStatus = messageAisBinary.binaryToInt(38, 42);
            sog = (float) (0.1 * messageAisBinary.binaryToInt(50, 60));
            cog = (float) (0.1 * messageAisBinary.binaryToInt(116, 128));
            heading = (float) messageAisBinary.binaryToInt(128, 137);
            rot = (float) messageAisBinary.complementToInt(42, 50);
            longitude = ((float) (0.0001 * messageAisBinary.complementToInt(61, 89))) / 60;
            latitude = ((float) (0.0001 * messageAisBinary.complementToInt(89, 116))) / 60;
        }
    }


    @Override
    public String toString() {
        return "(MESSAGEAISTYPE1) MMSI=" + MMSI  
                + ", STATUS = " + navigationalStatus
                + ", HEAD =" + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", ROT = " + rot;
    }

}
/** end AISMessageType1 */
