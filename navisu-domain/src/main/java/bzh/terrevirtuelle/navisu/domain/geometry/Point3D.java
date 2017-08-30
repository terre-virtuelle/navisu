/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

/**
 * @date 15 mars 2015
 * @author Serge Morvan
 */
public class Point3D {

    private int id = 0;

    public double lon = 0.0;

    public double lat = 0.0;

    public double elevation = 0.0;

    public Point3D() {
    }

    public Point3D(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    public Point3D(double lat, double lon, double elevation) {
        this.lon = lon;
        this.lat = lat;
        this.elevation = elevation;
    }

    public Point3D(int id, double lat, double lon) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public Point3D(int id, double lat, double lon, double elevation) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
        this.elevation = elevation;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
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

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "{" + lat + ", " + lon + ", " + elevation + "}";
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
        if (Double.doubleToLongBits(this.lon) != Double.doubleToLongBits(other.lon)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lat) != Double.doubleToLongBits(other.lat)) {
            return false;
        }
        if (Double.doubleToLongBits(this.elevation) != Double.doubleToLongBits(other.elevation)) {
            return false;
        }
        return true;
    }
    
}
