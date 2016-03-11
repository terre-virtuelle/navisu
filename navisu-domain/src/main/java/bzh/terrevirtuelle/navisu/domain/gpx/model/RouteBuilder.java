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
public class RouteBuilder {

    protected String name = "noname";
    protected String cmt="";
    protected String desc="";
    protected String src="";
    protected List<Links> link = new ArrayList<>();
    protected int number;
    protected String type="";
    protected Extensions extensions = new Extensions();
    protected List<Waypoint> rtept = new ArrayList<>();

    private RouteBuilder() {
    }

    public static RouteBuilder create() {
        return new RouteBuilder();
    }

    public Route build() {
        return new Route(name, cmt, desc, src, link, number, type, extensions, rtept);
    }

    public RouteBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RouteBuilder cmt(String cmt) {
        this.cmt = cmt;
        return this;
    }

    public RouteBuilder desc(String desc) {
        this.desc = desc;
        return this;
    }

    public RouteBuilder src(String src) {
        this.src = src;
        return this;
    }

    public RouteBuilder link(List<Links> link) {
        this.link = link;
        return this;
    }

    public RouteBuilder number(int number) {
        this.number = number;
        return this;
    }

    public RouteBuilder type(String type) {
        this.type = type;
        return this;   
    }

    public RouteBuilder extensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

    public RouteBuilder rtept(List<Waypoint> rtept) {
        this.rtept = rtept;
        return this;
    }

}
