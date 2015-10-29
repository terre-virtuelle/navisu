/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.photos.exif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 29 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "position")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position {

    private String gpsMapDatum;

    private double gpsLatitude;

    private double gpsLongitude;

    private String gpsAltitudeRef;

    private double gpsAltitude;

    private String gpsImgDirectionRef;

    private int gpsImgDirection;

    public Position() {
    }

    public Position(String gpsMapDatum, double gpsLatitude, double gpsLongitude, 
            String gpsAltitudeRef, double gpsAltitude, String gpsImgDirectionRef, 
            int gpsImgDirection) {
        this.gpsMapDatum = gpsMapDatum;
        this.gpsLatitude = gpsLatitude;
        this.gpsLongitude = gpsLongitude;
        this.gpsAltitudeRef = gpsAltitudeRef;
        this.gpsAltitude = gpsAltitude;
        this.gpsImgDirectionRef = gpsImgDirectionRef;
        this.gpsImgDirection = gpsImgDirection;
    }

    /**
     * Get the value of gpsImgDirection
     *
     * @return the value of gpsImgDirection
     */
    public int getGpsImgDirection() {
        return gpsImgDirection;
    }

    /**
     * Set the value of gpsImgDirection
     *
     * @param gpsImgDirection new value of gpsImgDirection
     */
    public void setGpsImgDirection(int gpsImgDirection) {
        this.gpsImgDirection = gpsImgDirection;
    }

    /**
     * Get the value of gpsImgDirectionRef
     *
     * @return the value of gpsImgDirectionRef
     */
    public String getGpsImgDirectionRef() {
        return gpsImgDirectionRef;
    }

    /**
     * Set the value of gpsImgDirectionRef
     *
     * @param gpsImgDirectionRef new value of gpsImgDirectionRef
     */
    public void setGpsImgDirectionRef(String gpsImgDirectionRef) {
        this.gpsImgDirectionRef = gpsImgDirectionRef;
    }

    /**
     * Get the value of gpsAltitude
     *
     * @return the value of gpsAltitude
     */
    public double getGpsAltitude() {
        return gpsAltitude;
    }

    /**
     * Set the value of gpsAltitude
     *
     * @param gpsAltitude new value of gpsAltitude
     */
    public void setGpsAltitude(double gpsAltitude) {
        this.gpsAltitude = gpsAltitude;
    }

    /**
     * Get the value of gpsAltitudeRef
     *
     * @return the value of gpsAltitudeRef
     */
    public String getGpsAltitudeRef() {
        return gpsAltitudeRef;
    }

    /**
     * Set the value of gpsAltitudeRef
     *
     * @param gpsAltitudeRef new value of gpsAltitudeRef
     */
    public void setGpsAltitudeRef(String gpsAltitudeRef) {
        this.gpsAltitudeRef = gpsAltitudeRef;
    }

    /**
     * Get the value of gpsLongitude
     *
     * @return the value of gpsLongitude
     */
    public double getGpsLongitude() {
        return gpsLongitude;
    }

    /**
     * Set the value of gpsLongitude
     *
     * @param gpsLongitude new value of gpsLongitude
     */
    public void setGpsLongitude(double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    /**
     * Get the value of gpsLatitude
     *
     * @return the value of gpsLatitude
     */
    public double getGpsLatitude() {
        return gpsLatitude;
    }

    /**
     * Set the value of gpsLatitude
     *
     * @param gpsLatitude new value of gpsLatitude
     */
    public void setGpsLatitude(double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    /**
     * Get the value of gpsMapDatum
     *
     * @return the value of gpsMapDatum
     */
    public String getGpsMapDatum() {
        return gpsMapDatum;
    }

    /**
     * Set the value of gpsMapDatum
     *
     * @param gpsMapDatum new value of gpsMapDatum
     */
    public void setGpsMapDatum(String gpsMapDatum) {
        this.gpsMapDatum = gpsMapDatum;
    }

    @Override
    public String toString() {
        return "Position{" + "gpsMapDatum=" + gpsMapDatum + ", gpsLatitude=" + gpsLatitude + ", gpsLongitude=" + gpsLongitude + ", gpsAltitudeRef=" + gpsAltitudeRef + ", gpsAltitude=" + gpsAltitude + ", gpsImgDirectionRef=" + gpsImgDirectionRef + ", gpsImgDirection=" + gpsImgDirection + '}';
    }

}
