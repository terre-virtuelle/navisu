/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.devices.model;

import java.io.Serializable;
import java.util.Calendar;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Serge
 */
public class BaseStation
        implements Serializable {

    /**
     * MMSI number :1-999999999; 0 = not available = default
     */
    private int mmsi;
    private double latitude;
    private double longitude;
    private Calendar date;
    /**
     * Electronic Position Fixing Device 0 Undefined (default) 1 GPS 2 GLONASS 3
     * Combined GPS/GLONASS 4 Loran-C 5 Chayka 6 Integrated navigation system 7
     * Surveyed 8 Galileo
     */
    private int epfd;

    public BaseStation() {
    }

    public BaseStation(int mmsi, double latitude, double longitude, Calendar date, int epfd) {
        this.mmsi = mmsi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.epfd = epfd;
    }

    public BaseStation(int mmsi, double latitude, double longitude) {
        this.mmsi = mmsi;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BaseStation(int mmsi, double latitude, double longitude, Calendar date) {
        this.mmsi = mmsi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public int getMMSI() {
        return mmsi;
    }

    public void setMMSI(int mmsi) {
        this.mmsi = mmsi;
    }

    /**
     *
     * @return
     */
    public double getLatitude() {
        return this.latitude;

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getEpfd() {
        return epfd;
    }

    public void setEpfd(int epfd) {
        this.epfd = epfd;
    }

    @Override
    public String toString() {
        return "Station{" + "mmsi=" + mmsi + ", latitude=" + latitude + ", longitude=" + longitude + ", date=" + date + ", epfd=" + epfd + '}';
    }

}
