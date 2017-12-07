/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @date 15 mars 2015
 * @author Serge Morvan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "point3d", propOrder = {
    "latitude",
    "longitude",
    "elevation",
    "id"
})
@XmlRootElement
public class Point3D
        implements NavigationData {

    private long id = 0;

    public double longitude = 0.0;

    public double latitude = 0.0;

    public double elevation = 0.0;

    public Point3D() {
    }

    public Point3D(double lat, double lon) {
        this.longitude = lon;
        this.latitude = lat;
    }

    public Point3D(double lat, double lon, double elevation) {
        this.longitude = lon;
        this.latitude = lat;
        this.elevation = elevation;
    }

    public Point3D(int id, double lat, double lon) {
        this.id = id;
        this.longitude = lon;
        this.latitude = lat;
    }

    public Point3D(int id, double lat, double lon, double elevation) {
        this.id = id;
        this.longitude = lon;
        this.latitude = lat;
        this.elevation = elevation;
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
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double lon) {
        this.longitude = lon;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "{" + latitude + ", " + longitude + ", " + elevation + "}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point3D other = (Point3D) obj;
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        return Double.doubleToLongBits(this.elevation) == Double.doubleToLongBits(other.elevation);
    }
}
