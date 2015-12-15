/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Location;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.util.JTS;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author serge
 */
@XmlRootElement
@XmlType(name = "s57Chart",
        propOrder = {"id", "number", "description", "lat", "lon", "geometry", "centroid"})
@XmlAccessorType(XmlAccessType.FIELD)
public class S57Chart
        extends Location
        implements NavigationData {

    private String description;
    private String geometry;
    private String number;

    public S57Chart() {
    }

    public S57Chart(long id) {
        this.id = id;
    }

    public S57Chart(long id, String description, String wkt) {
        this.id = id;
        this.description = description;
        this.geometry = wkt;
        Pair<Double, Double> lonLat = JTS.getCentroid(wkt);
        if (lonLat != null) {
            setLatlon(lonLat.getY(), lonLat.getX());
        }
    }

    public S57Chart(long id, double lat, double lon, String description, String wkt) {
        this.id = id;
        this.description = description;
        this.geometry = wkt;
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(String number) {
        this.number = number;
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
        Pair<Double, Double> lonLat = JTS.getCentroid(geometry);
        if (lonLat != null) {
            setLatlon(lonLat.getY(), lonLat.getX());
        }
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
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "S57Chart{"
                + super.toString() + " description=" + description + ", geometry=" + geometry + ", number=" + number + '}';
    }

}
