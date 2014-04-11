/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Serge
 */
public class MetadataBuilder {

    protected String name = "";
    protected String desc = "";
    protected Person author = new Person();
    protected Copyright copyright = new Copyright();
    protected List<Link> link = new ArrayList<>();
    protected GregorianCalendar time = new GregorianCalendar();
    protected String keywords = "";
    protected Bounds bounds = new Bounds();
    protected Extensions extensions = new Extensions();

    private MetadataBuilder() {
    }

    public static MetadataBuilder create() {
        return new MetadataBuilder();
    }

    public Metadata build() {
        return new Metadata(name, desc, author, copyright, link, time, keywords, bounds, extensions);
    }

    public MetadataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MetadataBuilder desc(String desc) {
        this.desc = desc;
        return this;
    }

    public MetadataBuilder author(Person author) {
        this.author = author;
        return this;
    }

    public MetadataBuilder setCopyright(Copyright copyright) {
        this.copyright = copyright;
        return this;
    }

    public MetadataBuilder time(GregorianCalendar time) {
        this.time = time;
        return this;
    }

    public MetadataBuilder keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public MetadataBuilder bounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public MetadataBuilder extensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

}
