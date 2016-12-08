/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry.model;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author serge
 * @date Feb 13, 2016
 *
 */
@XmlRootElement
@XmlType(name = "area", propOrder = {"id", "name",
    "description", "latitude", "longitude", "geometry"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Area
        implements NavigationData {

    private long id;
    private double latitude;
    private double longitude;
    private String geometry;
    private String name;
    private String description;

    public Area() {
    }

    public Area(long id) {
        this.id = id;
    }

    public Area(long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Area(long id, double latitude, double longitude, String geometry) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geometry = geometry;
    }

    public Area(long id, double latitude, double longitude, String geometry, String name) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geometry = geometry;
        this.name = name;
    }

    public Area(long id, double latitude, double longitude, String geometry, String name, String description) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geometry = geometry;
        this.name = name;
        this.description = description;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of geometry
     *
     * @return the value of geometry
     */
    @Override
    public String getGeometry() {
        return geometry;
    }

    /**
     * Set the value of geometry
     *
     * @param geometry new value of geometry
     */
    public void setGeometry(String geometry) {
        this.geometry = geometry;
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

    @SuppressWarnings("unchecked")
    public Pair<Double, Double> getCentroid() {
        return new Pair(getLatitude(), getLongitude());
    }
}
