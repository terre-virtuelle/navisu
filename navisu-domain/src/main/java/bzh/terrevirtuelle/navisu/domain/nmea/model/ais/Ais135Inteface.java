/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais;

import java.util.Calendar;

/**
 *
 * @author serge
 */
public interface Ais135Inteface {

    /**
     *
     */
    void display();

    /**
     *
     * @return
     */
    String getCallSign();

    /**
     *
     * @return
     */
    float getCog();

    /**
     *
     * @return
     */
    int getDay();

    /**
     *
     * @return
     */
    String getDestination();

    /**
     *
     * @return draught
     */
    float getDraught();

    /**
     *
     * @return
     */
    Calendar getETA();

    int getElectronicPositionDevice();

    /**
     *
     * @return
     */
    float getHeading();

    /**
     *
     * @return
     */
    int getHour();

    /**
     *
     * @return
     */
    int getIMO();

    /**
     *
     * @return
     */
    float getLatitude();

    /**
     *
     * @return
     */
    float getLength();

    /**
     *
     * @return
     */
    float getLongitude();

    /**
     *
     * @return
     */
    int getMinute();

    /**
     *
     * @return
     */
    int getMonth();

    /**
     *
     * @return
     */
    int getNavigationTool();

    /**
     *
     * @return
     */
    int getNavigationalStatus();

    /**
     *
     * @return
     */
    float getRot();

    /**
     * Get the value of second
     *
     * @return the value of second
     */
    int getSecond();

    /**
     *
     * @return
     */
    int getShipType();

    /**
     *
     * @return
     */
    String getShipName();

    /**
     *
     * @return
     */
    float getSog();

    /**
     *
     * @return
     */
    float getWidth();

    /**
     *
     * @return
     */
    int getYear();

    /**
     *
     * @param callsign
     */
    void setCallSign(String callsign);

    /**
     *
     * @param cog
     */
    void setCog(float cog);

    /**
     *
     * @param day
     */
    void setDay(int day);

    /**
     *
     * @param destination
     */
    void setDestination(String destination);

    /**
     *
     * @param draught
     */
    void setDraught(float draught);

    /**
     *
     * @param ETA
     */
    void setETA(Calendar ETA);

    void setElectronicPositionDevice(int electronicPositionDevice);

    /**
     *
     * @param heading
     */
    void setHeading(float heading);

    /**
     *
     * @param hour
     */
    void setHour(int hour);

    /**
     *
     * @param imo
     */
    void setIMO(int imo);

    /**
     *
     * @param latitude
     */
    void setLatitude(float latitude);

    /**
     *
     * @param length
     */
    void setLength(float length);

    /**
     *
     * @param longitude
     */
    void setLongitude(float longitude);

    /**
     *
     * @param minute
     */
    void setMinute(int minute);

    /**
     *
     * @param month
     */
    void setMonth(int month);

    /**
     *
     * @param navigationTool
     */
    void setNavigationTool(int navigationTool);

    /**
     *
     * @param navigationalStatus
     */
    void setNavigationalStatus(int navigationalStatus);

    /**
     *
     * @param rot
     */
    void setRot(float rot);

    /**
     * Set the value of second
     *
     * @param second new value of second
     */
    void setSecond(int second);

    /**
     *
     * @param shipType
     */
    void setShipType(int shipType);

    /**
     *
     * @param name
     */
    void setShipName(String name);

    /**
     *
     * @param sog
     */
    void setSog(float sog);

    /**
     *
     * @param width
     */
    void setWidth(float width);

    /**
     *
     * @param year
     */
    void setYear(int year);

    /**
     *
     * @return
     */
    String toHTML();

    String toString();
    
}
