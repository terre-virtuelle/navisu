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
package bzh.terrevirtuelle.navisu.nmea.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * UTC and Date response
 * 
 */
@XmlRootElement(name="ais11")
@XmlAccessorType(XmlAccessType.FIELD)
public class AISMessageType11 extends AISMessage {

    private Calendar ETADate;
    private int year,  month,  day;
    private int hour,  minute,  second;
    private float latitude,  longitude;

    public AISMessageType11() {
    }

    public Calendar getETADate() {
        return ETADate;
    }

    public void setETADate(Calendar ETADate) {
        this.ETADate = ETADate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * decodeFrame : decode AIS message of type 11 (idem message 4)
     *
     */
    @Override
    public void decodeFrame() {



        if (messageAisBinary.length() == 167) {

            MMSI = binaryToInt(messageAisBinary,8, 38);
            year = binaryToInt(messageAisBinary,38, 52);
            month = binaryToInt(messageAisBinary,52, 56);
            day =binaryToInt(messageAisBinary,56, 61);
            hour = binaryToInt(messageAisBinary,61, 67);
            minute = binaryToInt(messageAisBinary,67, 73);
            second = binaryToInt(messageAisBinary,73, 79);
            ETADate = new GregorianCalendar(year, month, day, hour, minute, second);
            longitude = -((float) (0.0001 * complementToInt(messageAisBinary,80, 107))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary,107, 134))) / 60;
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
        return "(MESSAGEAISTYPE11) ISMM=" + MMSI + ", LAT=" + latitude + ", LONG=" + longitude + ", ETA=" + dateFormat.format(ETADate.getTime());
    }
}
/** end AISMessageType11 */
