/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Serge
 */
public class WaypointBuilder {

    protected double ele;
    protected GregorianCalendar time = new GregorianCalendar();
    protected double magvar;
    protected double geoidheight;
    protected String name = "noname";
    protected String cmt;
    protected String desc;
    protected String src;
    protected List<Links> link = new ArrayList<>();
    protected String sym;
    protected String type;
    protected String fix;
    protected int sat;
    protected double hdop;
    protected double vdop;
    protected double pdop;
    protected double ageofdgpsdata;
    protected int dgpsid;
    protected float course;
    protected float speed;
    protected Extensions extensions = null;
    protected double latitude;
    protected double longitude;

    private WaypointBuilder() {
    }

    public static WaypointBuilder create() {
        return new WaypointBuilder();
    }

    public Waypoint build() {
        return new Waypoint(ele, time, magvar, geoidheight,
                name, cmt, desc, src, link,
                sym, type, fix, sat, hdop,
                vdop, pdop, ageofdgpsdata, course, speed,
                dgpsid, extensions, latitude, longitude);
    }

    public WaypointBuilder ele(double ele) {
        this.ele = ele;
        return this;
    }

    public WaypointBuilder time(GregorianCalendar time) {
        this.time = time;
        return this;
    }

    public WaypointBuilder magvar(double magvar) {
        this.magvar = magvar;
        return this;
    }

    public WaypointBuilder geoidheight(double geoidheight) {
        this.geoidheight = geoidheight;
        return this;
    }

    public WaypointBuilder name(String name) {
        this.name = name;
        return this;
    }

    public WaypointBuilder cmt(String cmt) {
        this.cmt = cmt;
        return this;
    }

    public WaypointBuilder desc(String desc) {
        this.desc = desc;
        return this;
    }

    public WaypointBuilder src(String src) {
        this.src = src;
        return this;
    }

    public WaypointBuilder link(List<Links> link) {
        this.link = link;
        return this;
    }

    public WaypointBuilder sym(String sym) {
        this.sym = sym;
        return this;
    }

    public WaypointBuilder type(String type) {
        this.type = type;
        return this;
    }

    public WaypointBuilder fix(String fix) {
        this.fix = fix;
        return this;
    }

    public WaypointBuilder sat(int sat) {
        this.sat = sat;
        return this;
    }

    public WaypointBuilder hdop(double hdop) {
        this.hdop = hdop;
        return this;
    }

    public WaypointBuilder vdop(double vdop) {
        this.vdop = vdop;
        return this;
    }

    public WaypointBuilder pdop(double pdop) {
        this.pdop = pdop;
        return this;
    }

    public WaypointBuilder ageofdgpsdata(double ageofdgpsdata) {
        this.ageofdgpsdata = ageofdgpsdata;
        return this;
    }

    public WaypointBuilder course(float course) {
        this.course = course;
        return this;
    }

    public WaypointBuilder speed(float speed) {
        this.speed = speed;
        return this;
    }

    public WaypointBuilder dgpsid(int dgpsid) {
        this.dgpsid = dgpsid;
        return this;
    }

    public WaypointBuilder extensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

    public WaypointBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public WaypointBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

}
