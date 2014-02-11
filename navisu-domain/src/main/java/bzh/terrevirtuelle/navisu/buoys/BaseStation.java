/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.buoys;

import java.io.Serializable;
import java.util.Calendar;
import javafx.beans.property.DoubleProperty;

/**
 *
 * @author Serge
 */
public class BaseStation implements Serializable {

    /**
     * MMSI number :1-999999999; 0 = not available = default
     */
    private int mmsi;
    private DoubleProperty latitude;
    private DoubleProperty longitude;
    private Calendar date;
    /**
     * Electronic Position Fixing Device 
     * 0 Undefined (default) 
     * 1 GPS 
     * 2 GLONASS 
     * 3 Combined GPS/GLONASS 
     * 4 Loran-C 
     * 5 Chayka 
     * 6 Integrated navigation system 
     * 7 Surveyed 
     * 8 Galileo
     */
    private int epfd;

    public BaseStation() {
    }

    public BaseStation(int mmsi, DoubleProperty latitude, DoubleProperty longitude, Calendar date, int epfd) {
        this.mmsi = mmsi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.epfd = epfd;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public DoubleProperty getLatitude() {
        return latitude;
    }

    public void setLatitude(DoubleProperty latitude) {
        this.latitude = latitude;
    }

    public DoubleProperty getLongitude() {
        return longitude;
    }

    public void setLongitude(DoubleProperty longitude) {
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
    
}
