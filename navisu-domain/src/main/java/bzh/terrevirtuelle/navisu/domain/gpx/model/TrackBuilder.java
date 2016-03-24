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
public class TrackBuilder {

    protected String name = "noname";
    protected String cmt = "";
    protected String desc = "";
    protected String src = "";
    protected List<Links> link = new ArrayList<>();
    protected int number;
    protected String type = "";
    protected Extensions extensions = new Extensions();
    protected List<TrackSegment> trkseg = new ArrayList<>();

    public TrackBuilder() {
    }

    public static TrackBuilder create() {
        return new TrackBuilder();
    }

    public Track build() {
        return new Track(name, cmt, desc, src,
                link, number, type, extensions, trkseg);
    }

    public TrackBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TrackBuilder cmt(String cmt) {
        this.cmt = cmt;
        return this;
    }

    public TrackBuilder v(String desc) {
        this.desc = desc;
        return this;
    }

    public TrackBuilder src(String src) {
        this.src = src;
        return this;
    }

    public TrackBuilder link(List<Links> link) {
        this.link = link;
        return this;
    }

    public TrackBuilder number(int number) {
        this.number = number;
        return this;
    }

    public TrackBuilder type(String type) {
        this.type = type;
        return this;
    }

    public TrackBuilder extensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

    public TrackBuilder trkseg(List<TrackSegment> trkseg) {
        this.trkseg = trkseg;
        return this;
    }

}
