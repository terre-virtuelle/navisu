/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.devices;

import java.io.Serializable;
import java.util.Calendar;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Serge
 */
public class Transceiver 
        implements Serializable {

    /**
     * MMSI number :1-999999999; 0 = not available = default
     */
    private int mmsi;
    private DoubleProperty latitude;
    private DoubleProperty longitude;
    private Calendar date;
    /**
     * Electronic Position Fixing Device 0 Undefined (default) 1 GPS 2 GLONASS 3
     * Combined GPS/GLONASS 4 Loran-C 5 Chayka 6 Integrated navigation system 7
     * Surveyed 8 Galileo
     */
    private int epfd;

    public Transceiver(){
    }

    public Transceiver(int mmsi, double latitude, double longitude, Calendar date, int epfd) {
        this.mmsi = mmsi;
        this.latitude = new SimpleDoubleProperty(latitude);
        this.longitude = new SimpleDoubleProperty(longitude);
        this.date = date;
        this.epfd = epfd;
    }

    public Transceiver(int mmsi, double latitude, double longitude) {
        this.mmsi = mmsi;
        this.latitude = new SimpleDoubleProperty(latitude);
        this.longitude = new SimpleDoubleProperty(longitude);
    }

    public Transceiver(int mmsi, double latitude, double longitude, Calendar date) {
        this.mmsi = mmsi;
         this.latitude = new SimpleDoubleProperty(latitude);
        this.longitude = new SimpleDoubleProperty(longitude);
        this.date = date;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public DoubleProperty latitudeProperty() {
        return this.latitude;
    }

    /**
     *
     * @return
     */
    public double getLatitude() {
        return this.latitudeProperty().get();
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitudeProperty().set(latitude);
    }

    public DoubleProperty longitudeProperty() {
        return this.longitude;
    }

    /**
     *
     * @return
     */
    public double getLongitude() {
        return longitudeProperty().get();
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {

        this.longitudeProperty().set(longitude);
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
