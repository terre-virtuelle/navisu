/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Serge Morvan
 * @date 10 juin 2014 NaVisu project
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "location", propOrder = {"lat", "lon", "wkt"})

public class Location extends Geo
        implements Serializable {

    protected double lat;
    protected double lon;
    protected String wkt;

    public Location() {
        this.wkt = "POINT(0.0 0.0)";
    }

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.wkt = "POINT(" + lon + " " + lat + ")";
    }

    // Warning : Longitude Latitude format
    // Example : POINT(-4.410364 48.289336)
    public Location(String wkt) {
        parse(wkt);
    }

    private void parse(String wkt) {
        if (wkt != null) {
            String[] t0;
            t0 = wkt.split("\\(");
            if (t0.length == 2) {
                if (t0[0].trim().equalsIgnoreCase("POINT")) {
                    t0[1] = t0[1].replace(")", "");
                    String[] t1;
                    t1 = t0[1].split(" ");
                    if (t1.length == 2) {
                        this.lon = Double.parseDouble(t1[0]);
                        this.lat = Double.parseDouble(t1[1]);
                        this.wkt = wkt;
                    }
                }
            }
        }
    }

    /**
     * Get the value of wkt
     *
     * @return the value of wkt
     */
    public String getWkt() {
        return wkt;
    }

    /**
     * Set the value of wkt
     *
     * @param wkt new value of wkt
     */
    public void setWkt(String wkt) {
        parse(wkt);
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" + "lat=" + lat + ", lon=" + lon + ", wkt=" + wkt + '}' + super.toString() ;
    }

}
