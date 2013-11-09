/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.controller.simulator;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import bzh.terrevirtuelle.navisu.instruments.instrument.view.gps.GPS;

/**
 *
 * @author Serge Morvan
 */
public class GPSSimulator
        extends SwingWorker<Integer, String> {

    private GPS gps;
    private float latitude;
    private float longitude;
    private float range;

    public GPSSimulator(GPS gps, float latitude, float longitude, float range) {
        this.gps = gps;
        this.latitude = latitude;
        this.longitude = longitude;
        this.range = range;
    }

    /**
     * Get the value of range
     *
     * @return the value of range
     */
    public float getRange() {
        return range;
    }

    /**
     * Set the value of range
     *
     * @param range new value of range
     */
    public void setRange(float range) {
        this.range = range;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the value of gps
     *
     * @return the value of gps
     */
    public GPS getGps() {
        return gps;
    }

    /**
     * Set the value of gps
     *
     * @param gps new value of gps
     */
    public void setGps(GPS gps) {
        this.gps = gps;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        float lat;
        float lon;
        float j = 0;
        float k = 0;
        GregorianCalendar dt;
        for (int i = 0; i < 100000; i++) {
            lat = latitude + (float) Math.sin(j) * range;
            lon = longitude + (float) Math.cos(j) * range;
            dt = new GregorianCalendar();
            if (gps != null) {
                gps.setLocation(lat, lon);
              
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GPSSimulator.class.getName()).log(Level.SEVERE, null, ex);
                }
               //System.out.println("dat : " + dt.getTime());
              gps.setDate(dt.getTime());
                j += 0.1;
                k += 0.2;
            } 
        }
     return 0;   
    }
}