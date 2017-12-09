/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author serge
 * @date Dec 8, 2017
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "target", propOrder = {
    "latitude",
    "longitude",
    "distance",
    "heading",
    "id"
})
@XmlRootElement
public class Target
        implements NavigationData {

    private NavigationData navigationData;

    private double latitude;

    private double longitude;

    private long id;

    private double distance;

    private double azimuth;

    public Target() {
    }

    public Target(NavigationData navigationData, double latitude, double longitude, long id, double distance, double azimuth) {
        this.navigationData = navigationData;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.distance = distance;
        this.azimuth = azimuth;
    }

    /**
     * Get the value of azimuth
     *
     * @return the value of azimuth
     */
    public double getAzimuth() {
        return azimuth;
    }

    /**
     * Set the value of azimuth
     *
     * @param azimuth new value of azimuth
     */
    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

  
    /**
     * Get the value of distance
     *
     * @return the value of distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Set the value of distance
     *
     * @param distance new value of distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
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

    /**
     * Get the value of navigationData
     *
     * @return the value of navigationData
     */
    public NavigationData getNavigationData() {
        return navigationData;
    }

    /**
     * Get the value of navigationData
     *
     * @return the value of navigationData
     */
    public NavigationData getTarget() {
        return navigationData;
    }

    /**
     * Set the value of navigationData
     *
     * @param navigationData new value of navigationData
     */
    public void setNavigationData(NavigationData navigationData) {
        this.navigationData = navigationData;
    }

    /**
     * Set the value of navigationData
     *
     * @param navigationData new value of navigationData
     */
    public void setTarget(NavigationData navigationData) {
        this.navigationData = navigationData;
    }
}
