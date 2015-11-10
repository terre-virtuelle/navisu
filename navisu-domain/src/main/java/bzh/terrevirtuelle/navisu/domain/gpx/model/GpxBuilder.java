/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge
 */
public class GpxBuilder {

    protected List<Waypoint> wpt = new ArrayList<>();
    protected List<Route> rte = new ArrayList<>();
    protected List<Track> trk = new ArrayList<>();
    protected Extensions extensions = new Extensions();
    protected Boundaries boundaries = new Boundaries();
    protected Highway highway = new Highway();
    protected String version = "notdefined";
    protected String creator = "unknow";

    private GpxBuilder() {
        wpt = new ArrayList<>();
        rte = new ArrayList<>();
        trk = new ArrayList<>();
        extensions = new Extensions();
        boundaries = new Boundaries();
        highway = new Highway();
        version = "notdefined";
        creator = "unknow";
    }

    public static GpxBuilder create() {
        return new GpxBuilder();
    }

    public Gpx build() {
        return new Gpx(wpt, rte, trk, extensions, boundaries, highway, version, creator);
    }

    public GpxBuilder wpt(List<Waypoint> wpt) {
        this.wpt = wpt;
        return this;
    }

    public GpxBuilder rte(List<Route> rte) {
        this.rte = rte;
        return this;
    }

    public GpxBuilder trk(List<Track> trk) {
        this.trk = trk;
        return this;
    }

    public GpxBuilder extensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

    public GpxBuilder boundaries(Boundaries boundaries) {
        this.boundaries = boundaries;
        return this;
    }

    public GpxBuilder highway(Highway highway) {
        this.highway = highway;
        return this;
    }

    public GpxBuilder version(String version) {
        this.version = version;
        return this;
    }

    public GpxBuilder creator(String creator) {
        this.creator = creator;
        return this;
    }

}
