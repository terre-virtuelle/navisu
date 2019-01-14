/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import com.vividsolutions.jts.algorithm.Centroid;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge Morvan
 * @date 10 juin 2014 NaVisu project
 */
//@XmlTransient
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Location
        extends Geo
        implements Serializable {

    @XmlElement(name = "lat")
    protected double latitude = 0.0;
    @XmlElement(name = "lon")
    protected double longitude = 0.0;
    @XmlElement(name = "geom")
    protected String geometry;

    public Location() {
    }

    public Location(long id) {
        super(id);
    }

    public Location(long id, double lat, double lon) {
        super(id);
        this.latitude = lat;
        this.longitude = lon;
    }

    public Location(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public Location(long id, String geometry) {
        super(id);
        this.geometry = geometry;
        if (geometry != null) {
            parse(this.geometry);
        }
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
        if (geometry != null) {
            parse(this.geometry);
        }
    }

    public final void setLatLon(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
        this.geometry = "POINT(" + lon + " " + lat + ")";
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double lat) {
        this.latitude = lat;
        this.geometry = "POINT(" + longitude + " " + lat + ")";
    }

    public void setLongitude(double lon) {
        this.longitude = lon;
        this.geometry = "POINT(" + lon + " " + latitude + ")";
    }

    private void parse(String wkt) {
        Geometry geometryJTS = null;
        WKTReader wktReader = new WKTReader();
        try {
            geometryJTS = wktReader.read(wkt);
        } catch (ParseException ex) {
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (wkt.contains("POINT") || wkt.contains("Point")) {
            createCentroidPoint(geometryJTS);
        }
        if (wkt.contains("POLYGON") || wkt.contains("Polygon")) {
            createCentroidArea(geometryJTS);
        }
    }

    private void createCentroidPoint(Geometry geometryJTS) {
        Coordinate[] coordinates = geometryJTS.getCoordinates();
        if (coordinates != null) {
            if (coordinates.length == 1) {
                latitude = coordinates[0].y;
                longitude = coordinates[0].x;//Simple Point
            } else {
                Centroid centroid = new Centroid(geometryJTS); //Multi point
                Coordinate coord = centroid.getCentroid();
                latitude = coord.y;
                longitude = coord.x;
            }
        }
    }

    private void createCentroidArea(Geometry geometryJTS) {
        Centroid centroid = new Centroid(geometryJTS);
        Coordinate coord = centroid.getCentroid();
        latitude = coord.y;
        longitude = coord.x;
    }

    @Override
    public String toString() {
        return "Location{" + "lat=" + latitude + ", lon=" + longitude + ", geometry=" + geometry + '}';
    }

}
