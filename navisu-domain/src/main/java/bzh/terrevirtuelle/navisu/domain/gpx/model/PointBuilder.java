/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.model;

import java.util.GregorianCalendar;

/**
 *
 * @author Serge
 */
public class PointBuilder {

    protected double ele;
    protected GregorianCalendar time=new GregorianCalendar();
    protected double latitude;
    protected double longitude;

    private PointBuilder() {
    }

    public static PointBuilder create() {
        return new PointBuilder();
    }

    public Point build() {
        return new Point(ele, time, latitude, longitude);
    }

    public PointBuilder ele(double ele) {
        this.ele = ele;
        return this;
    }

    public PointBuilder time(GregorianCalendar time) {
        this.time = time;
        return this;
    }

    public PointBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public PointBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

}
