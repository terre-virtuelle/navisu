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
 * Original Designers : RAY
 *
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.ais.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * UTC and Date response
 * 
 */
public class AISMessageType11 extends AISMessage {

    private Calendar ETADate;
    private int year,  month,  day;
    private int hour,  minute,  second;
    private float latitude,  longitude;

    /**
     * decodeFrame : decode AIS message of type 11 (idem message 4)
     *
     */
    @Override
    public void decodeFrame() {



        if (messageAisBinary.BinaryFrame.length() == 167) {

            MMSI = messageAisBinary.binaryToInt(8, 38);
            year = messageAisBinary.binaryToInt(38, 52);
            month = messageAisBinary.binaryToInt(52, 56);
            day = messageAisBinary.binaryToInt(56, 61);
            hour = messageAisBinary.binaryToInt(61, 67);
            minute = messageAisBinary.binaryToInt(67, 73);
            second = messageAisBinary.binaryToInt(73, 79);
            ETADate = new GregorianCalendar(year, month, day, hour, minute, second);
            longitude = -((float) (0.0001 * messageAisBinary.complementToInt(80, 107))) / 60;
            latitude = ((float) (0.0001 * messageAisBinary.complementToInt(107, 134))) / 60;
            try {
                //	position = new WGS84Location (latitude, longitude);
            } catch (NumberFormatException e) {
                throw (new IllegalArgumentException());
            }
        }
    }

    @Override
    public String toString() {
         DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        return new String("(MESSAGEAISTYPE11) ISMM=" + MMSI + ", LAT=" + latitude + ", LONG=" + longitude + ", ETA=" + dateFormat.format(ETADate.getTime()));
    }
}
/** end AISMessageType11 */
