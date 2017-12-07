/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.bathymetry.model;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "depth", propOrder = {
    "latitude",
    "longitude",
    "elevation",
    "id"
})
@XmlRootElement
public class Depth
        implements NavigationData {

    private double latitude;
    private double longitude;
    private double elevation;
    private long id;

    public Depth() {
    }

    public Depth(long id, double latitude, double longitude, double depth) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = depth;
        this.id = id;
    }

    public Depth(double latitude, double longitude, double depth) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = depth;
    }

    public Depth(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get the value of depth
     *
     * @return the value of depth
     */
    public double getElevation() {
        return elevation;
    }

    /**
     * Set the value of depth
     *
     * @param depth new value of depth
     */
    public void setElevation(double depth) {
        this.elevation = depth;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    @Override
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
    @Override
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
        return "Depth{id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", depth=" + elevation + '}';
    }

}
