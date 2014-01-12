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
 * Original Designers : RAY Modified : Serge Morvan Enib 09/2009
 *
 *****************************************************************************
 */
package bzh.terrevirtuelle.navisu.nmea.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Base Station Report
 *
 */
@XmlRootElement(name = "ais4")
@XmlAccessorType(XmlAccessType.FIELD)
public class AISType4 extends AISMessage {

    private Calendar date;
    private int year, month, day;
    private int hour, minute, second;
    private float latitude, longitude;

    public AISType4() {
    }

    /**
     * decodeFrame : decode AIS message of type 4 (idem message 11)
     *
     */
    @Override
    public void decodeFrame() {
        // System.out.println("messageAisBinary : " + messageAisBinary);
        if (messageAisBinary.length() == 167) {
            MMSI = binaryToInt(messageAisBinary, 8, 38);
            year = binaryToInt(messageAisBinary, 38, 52);
            month = binaryToInt(messageAisBinary, 52, 56);
            day = binaryToInt(messageAisBinary, 56, 61);
            hour = binaryToInt(messageAisBinary, 61, 67);
            minute = binaryToInt(messageAisBinary, 67, 73);
            second = binaryToInt(messageAisBinary, 73, 79);
            date = new GregorianCalendar(year, month, day, hour, minute, second);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary, 80, 107))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 107, 134))) / 60;
        }
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        return "AISType4{ISMM=" + MMSI
                + ", LAT=" + latitude + ", LONG=" + longitude
                + ", DATE=" + date != null ? dateFormat.format(date.getTime()) : ""
                +"}";
    }

    /**
     *
     * @return
     */
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
    public int getDay() {
        return day;
    }

    /**
     *
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     *
     * @return
     */
    public int getHour() {
        return hour;
    }

    /**
     *
     * @param hour
     */
    public void setHour(int hour) {
        this.hour = hour;
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
    public int getMonth() {
        return month;
    }

    /**
     *
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     *
     * @return
     */
    public int getSecond() {
        return second;
    }

    /**
     *
     * @param second
     */
    public void setSecond(int second) {
        this.second = second;
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }
}
/**
 * end AISMessageType4
 */
