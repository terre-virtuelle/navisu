/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import com.vividsolutions.jts.algorithm.CentroidArea;
import com.vividsolutions.jts.algorithm.CentroidPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Serge Morvan
 * @date 10 juin 2014 NaVisu project
 */
@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Location
        extends Geo
        implements Serializable {

    protected double lat = 0.0;
    protected double lon = 0.0;
    protected String geometry = "";

    public Location() {
    }

    public Location(long id) {
        super(id);
    }

    public Location(long id, double lat, double lon) {
        super(id);
        this.lat = lat;
        this.lon = lon;
        this.geometry = "POINT(" + lon + " " + lat + ")";
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
        this.lat = lat;
        this.lon = lon;
        this.geometry = "POINT(" + lon + " " + lat + ")";
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
        this.geometry = "POINT(" + lon + " " + lat + ")";
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
        this.geometry = "POINT(" + lon + " " + lat + ")";
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
                lat = coordinates[0].y;
                lon = coordinates[0].x;//Simple Point
            } else {
                CentroidPoint centroid = new CentroidPoint(); //Multi point
                centroid.add(geometryJTS);
                Coordinate coord = centroid.getCentroid();
                lat = coord.y;
                lon = coord.x;
            }
        }
    }

    private void createCentroidArea(Geometry geometryJTS) {
        CentroidArea centroid = new CentroidArea();
        centroid.add(geometryJTS);
        Coordinate coord = centroid.getCentroid();
        lat = coord.y;
        lon = coord.x;
    }

    @Override
    public String toString() {
        return "Location{" + super.toString() + "lat=" + lat + ", lon=" + lon + ", geometry=" + geometry + '}';
    }

}
