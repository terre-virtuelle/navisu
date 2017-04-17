/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.n2k;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Apr 10, 2017
 */
@XmlRootElement(name = "pgn129025")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN129025
        extends N2K {

    private double latitude;
    private double longitude;

    public PGN129025() {
    }

    public PGN129025(String sentence, String timeStamp,
            int priority, String src, int dst, 
            int pgn, String description,
            double latitude, double longitude) {
        super(sentence, timeStamp, priority, src, dst, pgn, description);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "PGN129025{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
